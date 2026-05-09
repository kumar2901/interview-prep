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

## Observability and SLOs

**SLI** — quantitative measure of service behavior (e.g., availability = successful requests / total).

**SLO** — internal target on SLIs (e.g., 99.9% success monthly).

**SLA** — contractual promise to customers (often stricter penalties).

**Error budget** — allowable unreliability (\(1 -\) SLO); when exhausted, prioritize reliability over features.

**Golden signals** (Google): **latency**, **traffic**, **errors**, **saturation**. **RED** (microservices): rate, errors, duration.

**Metrics vs logs vs traces:** metrics aggregate (cheap alerts); logs explain *what* happened for a request id; **distributed traces** show cross-service path (spans, parent span id). Propagate **trace context** (e.g., W3C traceparent); **sample** in prod to control cost.

**Interview hooks:** symptom-based alerts (“checkout failing”) vs noisy infra; dashboards per user journey; **correlation IDs** end-to-end.

---

## Reliability and operations

**Incident:** severity, commander/on-call, comms channel, **rollback vs fix-forward**, stakeholder updates.

**Blameless postmortem:** timeline, root cause (technical + systemic), what went well, action items with owners.

**DR:** **RPO** (max acceptable data loss window), **RTO** (max downtime). Patterns: backup/restore; warm standby; **active-passive** vs **active-active** multi-region (conflict cost).

**Interview hooks:** game days / chaos exercises; dependency failure drills; **capacity buffer** for spikes.

---

## Deployment and change safety

**Rolling:** gradual instance replacement—simple; bad deploy poisons subset until halt.

**Blue/green:** two pools; instant switch; needs double capacity or scaled-down idle.

**Canary:** route small % to new version; promote or rollback on metrics—pairs well with **feature flags** for gradual enablement.

**Backward-compatible APIs:** additive changes first; deprecate with sunset headers/docs.

**Zero-downtime schema:** **expand/contract**—add new column/table → dual-write or backfill → switch reads → remove old (never drop in same release as code relying on absence).

---

## Real-time APIs and webhooks

| Mechanism | Tradeoff |
|-----------|-----------|
| **Long polling** | Simple; higher latency / connection overhead at scale |
| **SSE** | One-way server→browser over HTTP; reconnect semantics |
| **WebSocket** | Full duplex; connection state, sticky routing, **backpressure** |

**Webhooks:** **sign** payloads (HMAC shared secret); **retries with backoff**; receivers **idempotent** (event id dedup).

---

## Streaming and long-running work

**Logs vs streams:** bounded retention; **partitions** for parallelism; **key** for per-entity ordering.

**Windows:** tumbling vs sliding; **watermarks** handle lateness (approximate completeness).

**Durability:** compare **at-least-once** processing + idempotent sinks vs managed workflow (**Temporal**-class): sleeps, retries, visibility—pairs well with Saga discussions.

---

## Edge and traffic management

**DNS:** TTL, health-aware routing, latency-based routing.

**CDN:** cache keys, **TTL**, stale-while-revalidate, origin shield, purge API.

**API gateway:** authn/z, rate limits, routing, WAF hook—**edge** vs **mesh** division of labor.

**BFF** (backend-for-frontend): tailor APIs per client; avoids one mega-graph for all surfaces.

**Service mesh** (concept): mTLS between services, traffic policy, retries/timeouts—operational cost vs centralized libs.

---

*Senior sweep checklist:* [senior-gap-checklist.md](senior-gap-checklist.md)
