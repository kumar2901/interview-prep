# Billing system — requirements and design

This note matches the architecture in `Billing Service_.drawio.png` under the Java package `com.kumar.interview.prep.system.design.billing`.

---

## Detailed description

### Purpose

The system solves **metered billing at scale**: many small usage signals over time must be turned into **auditable aggregates** and then into **commercially correct invoices**, while **subscriptions and catalog data** evolve on a different cadence than raw usage.

Typical motivating examples: API metering, CDN/data transfer, seat-based plus overage, marketplace take rates, credits, and prepaid accounts.

### Key concepts

| Concept | Meaning |
|--------|---------|
| **Usage event** | Immutable fact: who, what meter, how much, when (and optional dimensions: region, tier, feature key). |
| **Aggregate** | Summarized usage for a window and grouping key (e.g. account + meter + day). |
| **Commercial snapshot** | Effective-dated view of what the customer is entitled to and at what price. |
| **Rating** | Applying price, discounts, credits, and policies to quantities to produce monetary line items. |
| **Invoice / ledger** | Durable, strongly consistent record of what is owed and how it was computed. |

### End-to-end lifecycle (narrative)

1. Producers emit usage continuously; volume is high and spiky.
2. Ingestion normalizes and stores each event as the **system of record for raw usage** (subject to retention policy).
3. A streaming layer projects that stream into **pre-aggregated tables** suitable for analytics and for feeding rating jobs.
4. Commercial changes arrive asynchronously; they are lower volume but drive **which rules apply** when usage is rated.
5. Billing runs (batch, micro-batch, or triggered) read **aggregates** and **commercial context**, write **invoices and balances** to an OLTP database, and emit downstream events (e.g. “invoice finalized”).

---

## Scope

**In scope (as reflected in the diagram):**

- Ingestion of high-volume usage events from heterogeneous producers.
- Durable persistence of acceptable raw events for replay and investigation.
- Stream-oriented rollups across **hourly**, **daily**, and **monthly** windows into an analytical tier.
- Ingest of lower-volume **commercial** events from an ecommerce domain (orders, plan changes, entitlements—exact entities are domain-specific).
- A **billing core** that fuses aggregates with commercial truth and persists **authoritative billing state**.

**Explicitly bounded (diagram does not prescribe implementation detail):**

- Payment capture (Stripe, ACH, PSP webhooks)—often modeled as downstream of invoice issuance.
- Full tax engine—referenced only as something the billing tier may orchestrate.

---

## Functional requirements

1. **Usage event capture** — Upstream services (e.g. `service1`, `service2`) must be able to publish usage or metered events to a shared messaging layer without tight coupling to billing internals.

2. **Durable raw event storage** — The events ingestion service must persist every accepted event (or an auditable representation) so events are not lost if downstream processing lags or fails.

3. **Validated ingestion** — The ingestion path must reject or quarantine malformed events, enforce required fields, and support traceability (e.g. source, timestamp, tenant).

4. **Time-windowed aggregation** — A streaming process must compute rollups for at least **hourly**, **daily**, and **monthly** windows from the event stream (or from raw storage via replay), suitable for reporting and rating.

5. **Analytical store for aggregates** — Aggregated usage must be available in a warehouse (e.g. BigQuery) for analytics, reconciliation, and large historical queries without overloading operational databases.

6. **Commerce / subscription integration** — The system must incorporate commercial context (orders, subscriptions, contracts, SKUs) from an e‑commerce-oriented service (`Ecomm service`) via asynchronous messages.

7. **Rating and invoicing** — The billing service must combine aggregated usage with commercial rules to produce authoritative billing artifacts (e.g. invoice line items, balances, billing periods) stored in its primary transactional database (`DB`).

8. **Idempotent processing** — Replayed or duplicate messages must not double-charge customers; ingestion and downstream consumers must support deduplication keys or equivalent safeguards.

9. **Reconciliation support** — Operators must be able to compare raw events, aggregates, and final invoices for a given customer and period.

## Non-functional requirements

1. **Scalability** — Ingestion and stream processing must scale independently with event volume; the billing OLTP tier must sustain invoice generation peaks without collapsing shared infrastructure.

2. **Availability** — Core paths (publish, ingest, eventual aggregation) must meet agreed SLOs; billing DB and rating logic should degrade gracefully under partial outages (clear failure modes, retries).

3. **Latency** — Near–real-time aggregates are desirable for dashboards; final invoicing may be batch or periodic, with bounded delay from period close to invoice availability.

4. **Durability and consistency** — No silent loss of billable events; strong consistency expectations for persisted invoices and ledger state in `DB`; clear eventual-consistency boundaries for aggregates.

5. **Security and compliance** — Encryption in transit and at rest, least-privilege access to PII and payment-related data, audit logs for config and invoice changes, retention aligned with regulation.

6. **Observability** — Metrics, logs, and traces across queues and services to debug lag, skew, and incorrect charges; alerting on backlog growth and failed consumption.

7. **Extensibility** — Schema evolution for events and aggregates; ability to add new meters, pricing dimensions, or tax rules without rewriting the entire pipeline.

8. **Cost efficiency** — Tiered storage (hot operational vs analytical warehouse); avoid unnecessary full scans on hot paths during rating.

---

## Design explanation

### Architecture overview

The design is **event-driven** and **pipeline-oriented**: each stage has a different **throughput, consistency, and query** profile, so it uses different storage and compute patterns.

```mermaid
flowchart LR
  subgraph producers["Usage producers"]
    S1[service1]
    S2[service2]
  end

  Q1[(Message bus 1)]
  ING[events_ingestion Service]
  CASS[(Cassandra — raw events)]

  Q2[(Message bus 2)]
  STR[streaming process]

  subgraph windows["Rollups"]
    H[hourly]
    D[daily]
    M[monthly]
  end

  BQ[(BigQuery — aggregates)]

  EC[Ecomm service]
  Q3[(Message bus 3)]
  BILL[Billing Service]
  DB[(DB — invoices / ledger)]

  S1 --> Q1
  S2 --> Q1
  Q1 --> ING
  ING --> CASS
  ING --> Q2
  Q2 --> STR
  STR --> H
  STR --> D
  STR --> M
  H --> BQ
  D --> BQ
  M --> BQ
  EC --> Q3
  Q3 --> BILL
  BQ --> BILL
  BILL --> DB
```

### Components (what each box is for)

1. **`service1` / `service2`** — Domain services that **emit usage**; they should not know invoice schema. They publish **well-versioned events** with stable identifiers (tenant, account, meter, idempotency key).

2. **First message bus** — **Decouples** producers from ingestion: absorbs spikes, allows fan-out, and supports consumer groups for scaling ingestion without changing producers.

3. **`events_ingestion Service`** — The **edge of trust** for raw usage: schema validation, clock skew policy, dedupe, optional enrichment, dead-lettering bad messages, and **write to durable raw storage**. May **re-publish** a cleaned canonical stream for downstream analytics.

4. **Cassandra (raw events)** — **High write throughput**, horizontal scale, **time-series friendly** access; **evidence trail** for disputes, backfill, recomputing aggregates when logic changes.

5. **Second message bus** — Separates **ingestion completion** from **stream processing** so aggregators scale, upgrade, or replay independently.

6. **`streaming process` (hourly / daily / monthly)** — **Windowed aggregation** over the stream or raw storage replays; multiple granularities for ops vs billing cycles.

7. **BigQuery (aggregates)** — **Analytical warehouse** for reconciliation and reporting; **not** primary **money truth**—that stays in `DB` behind the Billing Service.

8. **`Ecomm service` + third bus** — **Commercial facts** (subscription, contract, credits); often **ordering per customer** matters more than raw QPS.

9. **`Billing Service`** — **Orchestrates rating and persistence**: aggregates + commercial state, **effective-dated** rules, rounding/currency, commits **invoices / ledger** to `DB`.

10. **`DB` (OLTP)** — **Source of truth** for invoices, balances, adjustments, idempotent invoice keys; transactions and audit trails.

### Why this split (tradeoffs)

| Concern | Approach in design |
|--------|---------------------|
| **Write flood on usage** | Bus + Cassandra-style raw store; avoid writing every event into OLTP invoice storage. |
| **Correct money** | Billing Service → transactional `DB`; aggregates are inputs, not ledger. |
| **Replay / recomputation** | Raw store + reproducible jobs heal logic bugs without re-instrumenting producers. |
| **Heavy analytics** | BigQuery keeps billing OLTP predictable. |
| **Coupling** | Async boundaries; versioned event contracts. |

### Data consistency and ordering

- **At-least-once** across buses; **idempotency keys** at ingestion and billing jobs.
- **Usage** and **commerce** independent; rating uses **as-of** / effective-dated rules.
- **Aggregates lag** raw events—invoice policy for watermarks, late data, **credit memos**.

### Failure modes

- **Duplicates** → dedupe at ingestion.
- **Consumer crash** → offset replay; raw store durable after commit.
- **Late events** → next window or adjustments.
- **Warehouse down** → delay rating or fallback aggregates.
- **Commerce reorder** → per-customer partitions or versioning.

### Operational hooks

- **Metrics**: lag per stage, aggregation and billing job health, invoice errors.
- **Tracing**: correlation id from producer → invoice.
- **Backfill**: replay raw → aggregates when definitions change.
