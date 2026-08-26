# Distributed cache — requirements and design

Diagram: `Distributed cache.drawio.png` · Package: `com.kumar.interview.prep.system.design.cache`

---

## Detailed description

### Purpose

Serve **read-heavy workloads** with **sub-millisecond to low-ms latency** while keeping a **single durable source of truth** in a persistent database. The cache tier absorbs traffic spikes; the database tier guarantees recoverability and supports complex queries not suited to RAM.

The diagram shows **two evolutionary stages**: (1) replicated single-shard persistence, and (2) **sharded persistence** with **consistent hashing** at the cache layer for horizontal scale.

### Key ideas

| Idea | Role |
|------|------|
| **Cache-aside / read-through** | On miss, backing store loads data and populates cache for subsequent hits. |
| **Write path** | Writes go to persistence first (or write-through cache); cache invalidation or update policy must be explicit to avoid stale reads. |
| **Consistent hashing** | Maps keys to cache nodes so adding/removing nodes reshuffles only a **fraction** of keys. |
| **Leader + followers** | Writes on leader; reads can scale on replicas (with replication lag caveats). |

### Evolution (what the two drawings imply)

1. **Start**: one logical database with leader-follower replication and a pool of cache nodes behind a load balancer.
2. **Scale writes / data size**: shard the persistent layer (multiple primaries each with replicas); cache still fronts traffic but routing must align with shard keys or a routing layer must resolve key → shard.

---

## Scope

**In scope**

- Client-facing path through load balancer → **cache cluster** → **persistent layer**.
- **Leader–follower** replication model for durability and read scaling.
- **Sharding** of the persistence tier with primaries per shard.
- Use of **consistent hashing** to assign keys to cache nodes (labeled in diagram).
- Separation of concerns: cache for hot data, DB for authoritative state.

**Out of scope (not specified in diagram)**

- Exact cache coherence protocol (invalidate vs TTL vs versioning).
- Cross-region multi-master or CRDT-backed caches.
- Full security / auth story at the edge.

---

## Functional requirements

1. **Read path with cache** — Clients read through the balanced cache layer; on **miss**, data is loaded from persistence and optionally written to cache.

2. **Write path to persistence** — Mutations are applied to the **authoritative** persistent store (policy for cache update/invalidate is an implementation choice but must be defined).

3. **Load distribution** — A load balancer spreads requests across cache server instances to avoid hotspots.

4. **Replication** — Persistent tier supports **one write leader** and **follower replicas** for read scaling and failover (with documented consistency for reads).

5. **Horizontal scale (advanced)** — System can add **database shards**, each with its own primary and followers, when data or write throughput exceeds single-shard limits.

6. **Stable cache placement** — Cache cluster uses **consistent hashing** (or equivalent) so membership changes cause **bounded** key movement.

7. **Degraded operation** — If cache is unavailable, clients can still be served from persistence (higher latency), without silent data loss from the DB's perspective.

## Non-functional requirements

1. **Latency** — P99 read latency dominated by cache hits under normal conditions; DB hit path remains bounded and monitored.

2. **Throughput** — Cache cluster scales out with QPS; persistence scales via replicas (reads) and shards (writes/data).

3. **Availability** — Survive loss of individual cache nodes and **single replica** failures in DB; leader failure has RTO/RPO aligned with failover automation.

4. **Consistency** — Document **replication lag** for follower reads; define whether strong reads must hit leader or use sync boundaries.

5. **Elasticity** — Add/remove cache nodes without full cluster flush (consistent hashing motivation).

6. **Operability** — Metrics on hit ratio, eviction, latency, replication lag, shard load imbalance.

---

## Design explanation

### Architecture (two-tier + optional sharding)

```mermaid
flowchart TB
  C[Client]
  LB[Load balancer]
  subgraph cache["Cache server cluster"]
    N1[Cache 1]
    N2[Cache 2]
    N3[Cache 3]
  end

  subgraph db_single["Persistent layer — replicated"]
    L[Leader / primary]
    S1a[Follower s1]
    S2a[Follower s2]
  end

  subgraph db_shard["Persistent layer — sharded"]
    P1[Shard A primary]
    P1r1[followers]
    P2[Shard B primary]
    P2r1[followers]
  end

  C --> LB
  LB --> N1
  LB --> N2
  LB --> N3
  N1 --> L
  N2 --> L
  N3 --> L
  L --> S1a
  L --> S2a

  N1 -. sharded evolution .-> P1
  N2 -. .-> P2
```

*(Solid flow matches the simple case; dashed suggests evolution to per-shard routing.)*

### Component notes

- **Load balancer** — Session stickiness may or may not be required depending on cache model; consistent hashing can be done **client-side** or **proxy-side**.
- **Cache servers** — In-memory store; eviction (LRU/LFU/TTL), memory limits, and **stampede** protection on popular keys matter in production.
- **Leader–follower DB** — Classic pattern: all writes serialized on leader; async replication to followers introduces **staleness** for read-your-writes unless routed to leader.
- **Sharding** — Partition by key range or hash; **resharding** and **rebalancing** are operational concerns; cache may need **shard-aware routing** or a unified proxy.

### Tradeoffs

| Choice | Benefit | Cost |
|--------|---------|------|
| Cache-aside | Simple, DB stays source of truth | Stale reads unless TTL/invalidate disciplined |
| Write-through | Fresher cache | Write latency + coupling |
| Follower reads | Scale reads | Laggy reads under load |
| Sharding | Write + storage scale | Cross-shard queries harder, ops complexity |

### Failure modes

- **Cache failure** → thundering herd on DB unless rate limits and request coalescing exist.
- **Split brain / leader loss** → depends on consensus (Raft/Paxos) or manual failover.
- **Hot key** → single hash slot overloaded; mitigation: local in-process cache, key replication, or application sharding of hot entities.
