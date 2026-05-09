# Core concepts (FAANG interview map)

High-signal topics beyond coding—often mixed into system design, backend, and “fundamentals” rounds. Use **interview hooks** as prompts to practice explaining tradeoffs out loud.

---

## ACID (transactions)

| Property | Meaning |
|----------|---------|
| **Atomicity** | All operations in a transaction commit or none do. |
| **Consistency** | Database moves from one valid state to another (constraints preserved). |
| **Isolation** | Concurrent transactions don’t corrupt each other’s reads/writes (levels: Read Uncommitted … Serializable). |
| **Durability** | After commit, survives crashes (WAL, replication). |

**Interview hooks:** isolation anomalies (dirty read, phantom read); when to shorten transactions; **2PC** vs **Saga** for distributed workflows.

---

## BASE (NoSQL / distributed stores — contrast to ACID)

| Idea | Meaning |
|------|---------|
| **Basically Available** | System stays up under faults/partitions; responses may degrade. |
| **Soft state** | State may lag and converge over time without constant writes. |
| **Eventually consistent** | Replicas converge if updates stop; not linearizable by default. |

**Interview hooks:** when BASE is acceptable (feeds, counters, caches); user-visible inconsistency vs latency.

---

## SOLID (object-oriented design)

| Principle | One-line idea |
|-----------|----------------|
| **S** Single Responsibility | One reason to change per module/class. |
| **O** Open/Closed | Extend behavior without modifying stable core. |
| **L** Liskov Substitution | Subtypes honor contracts of supertypes. |
| **I** Interface Segregation | Small, focused interfaces over fat ones. |
| **D** Dependency Inversion | Depend on abstractions; inject implementations. |

**Interview hooks:** testing with mocks; avoiding god classes; plugin/extension points.

---

## Saga pattern (distributed transactions)

**Problem:** ACID across independent services/databases is expensive or impossible.

**Idea:** sequence of **local transactions**, each publishing events; **compensating actions** undo prior steps on failure.

**Styles:** **Choreography** (event-driven, decentralized) vs **Orchestration** (central coordinator).

**Interview hooks:** idempotent handlers; duplicate delivery; **outbox / transactional inbox**; visibility into partial completion; vs **2PC** cost and availability.

---

## CAP theorem (distributed systems)

Under **network partition**, you cannot have both **strong consistency** and **full availability** for reads/writes—pick tradeoffs per product.

**Interview hooks:** “CP vs AP” labels are shorthand; real systems tune **latency**, **durability**, and **SLAs** per operation; **PACELC** extension (latency vs consistency even without partition).

---

## Consistency models (quick ladder)

| Level | One-line |
|-------|-----------|
| **Strong / linearizable** | Every read sees latest completed write (expensive globally). |
| **Sequential** | Operations appear in some global order (weaker than linearizable). |
| **Causal** | Related writes observed in causal order. |
| **Eventual** | Replicas converge without concurrent conflicts resolved by rules (LWW, CRDTs, etc.). |

**Interview hooks:** why chat/presence might tolerate eventual; payment ledger vs social like-count.

---

## Idempotency & delivery semantics

| Concept | Meaning |
|---------|---------|
| **Idempotent API** | Same logical request applied twice has same effect as once (keys, dedup store). |
| **At-most-once** | May drop; no duplicates (risk lost messages). |
| **At-least-once** | Retries → duplicates unless consumers idempotent. |
| **Exactly-once** | End-to-end illusion—usually **at-least-once + idempotent processing + dedup**. |

**Interview hooks:** **Idempotency-Key** header; wallet/charge flows; message processing dedup window.

---

## Sharding, replication, quorum

| Concept | Meaning |
|---------|---------|
| **Sharding / partitioning** | Split data by key range or hash across nodes for scale. |
| **Replication** | Copies for durability and read scaling; **leader-follower** vs multi-leader. |
| **Read/write quorum** | **R + W > N** style rules for tunable consistency vs durability (conceptual; varies by system). |

**Interview hooks:** hot keys / resharding; cross-shard joins avoided; **split brain** mitigations.

---

## Consensus & leadership (high level)

**Consensus** (e.g., Raft/Paxos family): replicated log agreed despite faults—powers **leader election**, **metadata stores**, some databases.

**Interview hooks:** why strong consistency clusters elect a leader; cost of coordination; **etcd/ZooKeeper/KRaft**-style use cases vs application data plane.

---

## Caching patterns

| Pattern | Behavior |
|---------|-----------|
| **Cache-aside** | App reads cache miss → loads DB → fills cache. |
| **Read-through** | Cache sits in front of loader; miss triggers load transparently. |
| **Write-through** | Write goes to cache + backing store together (consistent, slower writes). |
| **Write-behind / write-back** | Write acks after cache; async flush (fast, risk loss without care). |

**Interview hooks:** **TTL**, eviction (**LRU/LFU**); **thundering herd** (singleflight, jittered TTL); stale reads vs latency.

---

## HTTP / APIs

| Topic | What to know |
|-------|----------------|
| **REST** | Resources, statelessness, **idempotent verbs** (GET/PUT/DELETE) vs POST; status codes (4xx vs 5xx). |
| **GraphQL** | Client-shaped queries; **N+1** and complexity limits; caching harder than REST CDN. |
| **gRPC** | Binary **Protobuf**, streaming, strong for internal RPC; versioning/contracts. |

**Interview hooks:** pagination (**cursor** vs offset); versioning (**/v1**, headers); errors as machine-readable bodies.

---

## AuthN vs AuthZ & tokens

| Concept | Meaning |
|---------|---------|
| **Authentication (AuthN)** | Who is this user? |
| **Authorization (AuthZ)** | What may they do? (RBAC/ABAC/ReBAC.) |
| **Session cookie** | Server-side session store; easy revocation; sticky or shared store. |
| **JWT** | Signed (often **JWS**) claims; **stateless** verification; revocation/expiry stories matter. |
| **OAuth 2.0** | Delegated access (authorization server, resource owner, client); not authentication by itself—often paired with **OpenID Connect** for identity. |

**Interview hooks:** never trust client; **CSRF** on cookies; short-lived access + refresh rotation; **scopes**.

---

## Concurrency fundamentals

| Concept | Meaning |
|---------|---------|
| **Race condition** | Outcome depends on interleaving; fix with synchronization or atomic ops. |
| **Mutex / lock** | Mutual exclusion; beware **deadlock** (ordering locks), **livelock**, **priority inversion**. |
| **Semaphore** | Counting permits; rate limiting / pool sizing. |
| **Compare-and-swap (CAS)** | Atomic read-modify-write primitive for lock-free structures. |
| **Deadlock four conditions** | Mutual exclusion, hold-and-wait, no preemption, circular wait—break one to prevent. |

**Interview hooks:** **thread-safe** singleton/lazy init; **producer-consumer**; thread pools and queue depth.

**Senior deep dive:** [concurrency-faqs.md](concurrency-faqs.md) — FAQ-style coverage (memory model, primitives, deadlock/liveness, CAS/ABA, pools, distributed locks, testing).

---

## Process vs thread

| Process | Thread |
|---------|--------|
| Own address space; heavier isolation | Shares address space; lighter context switch |
| IPC cost | Shared-memory races without care |

**Interview hooks:** **GIL** (Python) limits CPU parallelism; **async I/O** vs threads for IO-bound work.

---

## TCP vs UDP

| TCP | UDP |
|-----|-----|
| Connection-oriented, reliable, ordered | Connectionless, best-effort, datagrams |
| Flow/congestion control | Lower latency for loss-tolerant traffic |

**Interview hooks:** QUIC (UDP + reliability at app layer); WebSockets build on TCP; video/voice tradeoffs.

---

## TLS / HTTPS (one-minute story)

**Handshake:** negotiate cipher suite, server cert (chain to CA), key exchange, then symmetric encryption for bulk data.

**Interview hooks:** **TLS termination** at LB; **mTLS** service-to-service; cert rotation.

---

## Rate limiting (conceptual)

**Algorithms:** token bucket, leaky bucket, **fixed window**, **sliding window** (counter or log).

**Interview hooks:** global vs per-user; distributed limiters (**Redis** + Lua/atomic incr); **429** + **Retry-After**.

---

## Resilience patterns

| Pattern | Purpose |
|---------|---------|
| **Circuit breaker** | Fail fast when dependency unhealthy; avoid cascading load. |
| **Bulkhead** | Isolate pools (threads/connections) so one tenant/partition can’t exhaust all. |
| **Retry + backoff + jitter** | Reduce thundering retries; cap max attempts; respect idempotency. |
| **Timeouts & deadlines** | Propagate budget end-to-end; cancel work early. |

**Interview hooks:** **graceful degradation** (feature flags, defaults); **health checks** vs **readiness**.

---

## Messaging & queues

| Concept | Meaning |
|---------|---------|
| **Queue vs topic** | Point-to-point vs publish-subscribe fan-out. |
| **Consumer groups** | Partitioned consumption with scale-out. |
| **Ordering** | Single partition often ordered; cross-partition not guaranteed without keys. |
| **DLQ** | Poison messages isolated for inspection/replay. |

**Interview hooks:** **backpressure**; **poison pill**; ordering vs parallelism; **Kafka** log retention vs **Rabbit** classic queue mental model.

---

## Database & indexing (conceptual)

| Topic | One-line |
|-------|-----------|
| **B-tree family** | Common in OLTP indexes; range scans friendly. |
| **LSM-tree** | Write-optimized (flush/compaction); common in wide-column/KV. |
| **Covering index** | Index contains columns needed—avoids table lookup. |
| **Normalization** | Reduce redundancy; more joins. |
| **Denormalization** | Faster reads; duplication and sync cost. |

**Interview hooks:** **N+1 queries**; **write amplification** on LSM; **optimistic vs pessimistic locking**.

---

## Memory & GC (language-agnostic)

**Stack vs heap:** locals/stack frames vs dynamic allocations.

**GC idea:** tracing/generational collectors reclaim unreachable objects; **stop-the-world** pauses vs throughput tradeoffs.

**Interview hooks:** why **object pooling** sometimes; **OOM** vs leak; **weak refs** for caches (language-specific).

---

## Git workflows (often in “engineering practices”)

**Merge vs rebase**, **squash**, meaning of **fast-forward**; **conflict resolution**.

**Interview hooks:** trunk-based vs long-lived branches; **CODEOWNERS** and review culture (behavioral tie-in).

---

## How to study these

1. Pick **two concepts per day**; explain in **2 minutes** as if to a senior engineer (tradeoffs + failure mode).
2. Tie each concept to **something you built** or a **well-known product** (feeds, checkout, chat).
3. When prepping system design, reuse the same vocabulary (**idempotent**, **eventual**, **quorum**, **backpressure**) so answers sound precise without sounding textbook-only.

---

*Prioritize depth on topics that match your target role: backend-heavy roles skew toward consistency, caching, queues, and DB; infra toward networking, consensus, and resilience; full-stack adds HTTP/auth and client caching.*
