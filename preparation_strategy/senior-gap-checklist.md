# Senior FAANG / MAANG gap checklist

Use this as a **bar-raiser sweep**: tick boxes when you can explain tradeoffs + a failure mode in ~2 minutes without notes. Detailed notes live in linked docs.

| Theme | Where to study |
|--------|----------------|
| SLOs, error budgets, observability (metrics/logs/traces) | [system-design.md](system-design.md#observability-and-slos) |
| Incidents, DR, multi-region | [system-design.md](system-design.md#reliability-and-operations) |
| Safe deploys: canary, flags, migrations | [system-design.md](system-design.md#deployment-and-change-safety) |
| Real-time and webhooks | [system-design.md](system-design.md#real-time-apis-and-webhooks) |
| Streaming and workflow durability | [system-design.md](system-design.md#streaming-and-long-running-work) |
| Edge, gateway, mesh (one-slide mental model) | [system-design.md](system-design.md#edge-and-traffic-management) |
| Security, privacy, threat basics | [core-concepts.md](core-concepts.md#security-and-privacy-senior-bar) |
| DB nuance: isolation, MVCC, lag, CDC | [core-concepts.md](core-concepts.md#databases-and-transactions-senior-depth) |
| IDs, clocks, ordering | [core-concepts.md](core-concepts.md#distributed-ids-and-clocks) |
| CQRS, event sourcing, schema migrations | [core-concepts.md](core-concepts.md#architecture-and-data-evolution) |
| Leadership and senior behavioral stories | [overview.md](overview.md#senior-behavioral-and-leadership) |
| Extra LLD prompts | [lld.md](lld.md#additional-senior-prompts) |
| Extra coding topics | [leetcode-topics.md](leetcode-topics.md#senior-supplement-problems) |

---

## Sprint suggestion (2 weeks)

1. **Week 1:** Observability + SLOs + one incident story; security row; DB isolation/MVCC/replica lag; one LLD from extra list.
2. **Week 2:** Deploy patterns + streaming basics + real-time modality choice; CQRS/event sourcing “when / when not”; senior behavioral bullets → STAR drafts.

---

## If you only have a weekend

Prioritize: **SLO/error budget**, **tracing vs logs**, **canary + rollback**, **idempotency + webhooks**, **replication lag**, **one security story** (secrets or SSRF), **one leadership STAR**.
