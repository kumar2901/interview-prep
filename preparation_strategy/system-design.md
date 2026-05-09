# System Design

**Progression:** single machine → replication → sharding → caching → messaging → observability.

---

## Foundational reads / drills

- CAP intuition (pick two under partition), **latency vs consistency** sliders.
- Strong vs eventual consistency; **read/write quorums**.
- **Idempotency** keys; **exactly-once** vs **at-least-once** in practice.
- Load balancing (L4/L7), sticky sessions tradeoffs.
- DB choice: OLTP row store vs wide-column vs document vs OLAP (when mentioned).

---

## Classic problems (draw diagrams + back-of-envelope math)

| System | Key talking points |
|--------|-------------------|
| URL shortener | Base62 ID, collision handling, redirect SLA, analytics |
| Pastebin | TTL, abuse detection, blob storage |
| News feed | Fan-out on write vs read, ranking, cache |
| Chat (WhatsApp-like) | Presence, delivery receipts, WebSocket/gRPC, ordering |
| Search / typeahead | Trie/B-tree, ranking, sharding by prefix |
| Ride matching | Geo indexes, supply/demand matching, surge |
| Video streaming | CDN, transcoding pipeline, adaptive bitrate |
| Distributed rate limiter | Redis sliding window, coordination |
| Distributed cache | Eviction, thundering herd, consistency |

---

## Numbers to internalize (order-of-magnitude)

- ~1M seconds ≈ 11.5 days; use for QPS × payload ≈ bandwidth.
- SSD random read ~100 µs–ms scale; cross-region RTT ~30–150 ms.
- JSON overhead vs protobuf; compression on wire vs CPU.

---

## Edge and traffic management

- **DNS:** failover, TTL tradeoffs (low TTL ↔ more churn and load on DNS).
- **Load balancing:** L4 (connection-aware) vs L7 (routing by path/host/header); health checks (**liveness vs readiness**).
- **Sticky sessions:** when needed (cart colocation caution) vs preference for **stateless** tiers + shared session store.
- **TLS termination** at LB/ingress; optionally **mTLS** service-to-service.
- **CDN** for static and cacheable APIs; origin shield; cache keys and **purge** semantics.
- **API gateway:** authN/Z at edge, **rate limiting**, request size limits—see **§24** in [MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md) for hierarchical limits.

---

## Deployment and change safety

- **Rolling:** incremental instance replacement—simple; watch **draining** during deploy.
- **Blue/green:** two environments; fast cutover + fast rollback—cost of duplicate capacity.
- **Canary / progressive rollout:** subset of traffic/version; automate promotion on **SLO/regression guards**; ties to **feature flags** ([MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md) §19).
- **Schema changes:** **expand / contract** pattern for zero/low downtime ([MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md) §13).
- **Backward compatibility:** version negotiation, deprecation windows ([MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md) §16).

---

## Streaming and long-running work

- Partitioned logs (**Kafka**/Pulsar-style): ordering **per partition**, replay, retention.
- **Consumers:** consumer groups, **rebalancing** cost, poison messages → [**DLQ**](core-concepts.md#messaging--queues).
- **Stream processors:** stateful aggregation, **watermarks** / late events—see [MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md) §12.
- **Backpressure:** slow consumers must not wedge producers—bounded queues, drop/shed policy explicit.

---

## Observability and SLOs

- **Golden signals:** latency, traffic, errors, saturation (Google SRE framing).
- **SLI:** raw metric proxy for user happiness; **SLO:** target + window; **SLA:** customer-facing consequence.
- **Error budgets:** tie release velocity and change risk to remaining budget.
- **Logging:** structured, correlation/request IDs → pair with **distributed tracing** ([MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md) §17).
- **Metrics:** counters/gauges/histograms; **percentiles** (p99) vs averages; cardinality risk ([MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md) §9).
- **Dashboards vs alerts:** alert on **symptoms** user-impacting, reduce noise/flap (same §9).

---

## Reliability and operations

- **Multi-AZ vs multi-region:** RTO/RPO, failover drills, **chaos / game days** ([MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md) §6).
- **Runbooks**, on-call rotations, escalation; **severity** definitions.
- **Blameless postmortems:** contributing factors, action items with owners ([MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md) §22).
- **DR patterns:** backups + restore drills; replicated DB with failover caveats (**split brain** awareness from [core-concepts.md](core-concepts.md)).
