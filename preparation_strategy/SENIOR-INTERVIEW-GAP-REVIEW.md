# Directory review: senior FAANG / MAANG expectations vs `preparation_strategy/`

This file is the **audit outcome**: what the folder already covers, what [`MISSING-SENIOR-CONCEPTS.md`](MISSING-SENIOR-CONCEPTS.md) fills, and what is **still thin or missing**. Use it to prioritize study; it is not a duplicate of the entire missing-concepts catalog.

---

## 1. Inventory (what lives where)

| File | Role |
|------|------|
| [`faang-interview-preparation-strategy.md`](faang-interview-preparation-strategy.md) | Short index—start here |
| [`overview.md`](00-overview.md) | Timeline, STAR, weekly template |
| [`leetcode-topics.md`](leetcode-topics.md) | DSA topic → problem IDs |
| [`lld.md`](lld.md) | LLD drills + checklist (**add more for staff+**, see §4) |
| [`system-design.md`](system-design.md) | Foundations, classic designs, sizing, observability/deploy/reliability sections |
| [`core-concepts.md`](core-concepts.md) | Broad fundamentals (transactions, CAP, caches, APIs, queues, …) |
| [`concurrency-faqs.md`](concurrency-faqs.md) | Deep concurrency Q&A |
| [`MISSING-SENIOR-CONCEPTS.md`](MISSING-SENIOR-CONCEPTS.md) | **26 senior gap themes** with hooks and prep actions |
| [`ROLE-AND-COMPANY-GUIDE.md`](ROLE-AND-COMPANY-GUIDE.md) | Role + company emphasis, mock prompts |
| [`QUICK-REFERENCE-STUDY-MAP.md`](QUICK-REFERENCE-STUDY-MAP.md) | How to layer new topics on old; study sequence |
| [`00-ANALYSIS-SUMMARY.md`](00-ANALYSIS-SUMMARY.md) | Narrative summary of the gap-analysis effort |

If a link inside an older doc 404s, prefer this index or search the filename in this folder.

---

## 2. What is already “senior-grade” in the core tracks

- **Algorithms:** `leetcode-topics.md` spans the standard interview taxonomy; seniors still benefit from **hard revisit** + **timed communication** + **complexity narration**—the file does not spell that out separately.
- **Concurrency:** `concurrency-faqs.md` covers most **in-process** senior probes (HB, CAS/ABA, pools, testing).
- **Distributed building blocks:** `core-concepts.md` hits CAP/PACELC hooks, consistency ladder, messaging, caching, resilience, TLS/auth basics.
- **Breadth catalogue:** `MISSING-SENIOR-CONCEPTS.md` intentionally carries **business, ML/data, security depth, chaos, incidents, migrations, payments**, etc.—areas core files do **not** fully replace.

---

## 3. Corrections / doc hygiene (read before trusting every “✓ covered”)

- **`MISSING-SENIOR-CONCEPTS.md` § Microservices:** It lists **CQRS** and **event sourcing** as “covered” in core materials—they are **not** explained in [`core-concepts.md`](core-concepts.md) (only **Saga / outbox** adjacent ideas appear). Treat CQRS/ES as **still to study** (MISSING §8 + external resources) unless you add a dedicated note.
- **`QUICK-REFERENCE-STUDY-MAP.md`** previously referenced **non-existent** files/anchors; Phase 1 should use [`MISSING-SENIOR-CONCEPTS.md`](MISSING-SENIOR-CONCEPTS.md) (priority matrix) and [`system-design.md`](system-design.md) section anchors that now exist (see expanded `system-design.md`).
- **`00-ANALYSIS-SUMMARY.md`** mentions a central `README.md` under `preparation_strategy/`—that file may **not** exist; use [`faang-interview-preparation-strategy.md`](faang-interview-preparation-strategy.md) as the hub.

---

## 4. Additional gaps (common senior **questions** not fully captured elsewhere)

Use these as a **checklist** of prompts to practice; several overlap MISSING sections but are phrased as **interview questions**.

### Architecture & delivery

- How do you choose **blue-green vs canary vs rolling** deployment? What signals end a canary?
- What is **graceful shutdown** (drain in-flight, `preStop`, connection draining) and how does K8s affect it?
- How does **load shedding** differ from **rate limiting**? When do you drop work vs queue it?
- Explain **SLI vs SLO vs SLA** and how **error budgets** change release policy.
- What is **cell-based** or **failure-domain** design (zone/region blast radius)?

### Data & correctness

- When would you use **CQRS**? What breaks if the read model lags?
- What is **event sourcing** vs **CDC** vs **changelog** consumption—when use which?
- How do **CRDTs** or **OT** relate to collaborative editing scale?
- Where do **Bloom filters / HyperLogLog** fit (cache, DB, cardinality)?

### Platform & integrations

- Design **webhooks**: signing (HMAC), retries, deduplication, version skew.
- **Leader election** for periodic jobs—why not cron on every instance?
- **Object storage** patterns: multipart upload, TTL lifecycle, eventual listing consistency nuances.

### Networking & edge

- **DNS** failover and TTL tradeoffs; **anycast** vs geo-DNS at a high level.
- **WAF / DDoS** at edge vs origin; bot traffic vs legitimate spikes.
- Connection limits (**ephemeral ports**, **SYN backlog**) at high QPS—when does the LB matter?

### Global & compliance-heavy products

- **Data residency** and **cross-border** replication conflicts with GDPR-style delete.
- **Right to erasure** end-to-end: backups, caches, analytics, audit logs retention.

### People & judgment (often L6+ hints but seniors get seeds)

- **Technical conflict** with PM or security—how you decided and escalated.
- **Tech debt** bet: what you deferred, explicit interest cost, paydown plan.
- **Production incident** you owned: detection, mitigation, communication, long-term fix.

### Coding bar for “senior”

- **Follow-ups:** “How would this run on **disk**?” (external sort, B-tree mental model), **streaming** aggregation, **thread-safe** cache with eviction.
- **Meta:** testing strategy for the solution, invariants, fuzzing idea.

### LLD gaps (beyond `lld.md` list)

- **Configuration / feature flag** client (local eval, stale cache, kill switch).
- **File system** or **key-value store** API (append-only log, compaction story).
- **Distributed lock** façade (timeouts, fencing—ties to [`concurrency-faqs.md`](concurrency-faqs.md) + MISSING tracing).
- **Thread-safe counter / metrics** aggregator with flushing.

---

## 5. How to use this with existing gap docs

1. Read the **priority matrix** in [`MISSING-SENIOR-CONCEPTS.md`](MISSING-SENIOR-CONCEPTS.md).
2. Filter by [**ROLE-AND-COMPANY-GUIDE.md**](ROLE-AND-COMPANY-GUIDE.md).
3. Add **§4 question checklist** above for mock practice (pick 5 that match your target team).
4. Keep **one** place for weekly notes (which hooks you can answer in 2 minutes cold).

---

## 6. Suggested minimal additions to the repo (optional follow-ups)

- Short **CQRS + event sourcing** subsection in `core-concepts.md` *or* a dedicated `data-patterns.md`.
- **`senior-gap-checklist.md`** as a one-page checkbox distilled from MISSING + §4 here (optional convenience).
- **Behavioral appendix** (`behavioral-senior.md`): 8–12 L5 stories with STAR prompts (ownership, disagreement, ambiguity, outage).

---

*Review scope: entire `preparation_strategy/` folder as of the update that added system-design anchors and this file.*
