# Key Value Store vs Cache


| Aspect | Cache | Key-Value Store |
|---|---|---|
| **Primary purpose** | Reduce latency / database load | Persist application data |
| **Data durability** | Usually not guaranteed | Usually durable |
| **Source of truth** | Usually **No** | Usually **Yes** |
| **Data loss** | Acceptable in many cases | Usually not acceptable |
| **TTL** | Very common | Optional |
| **Eviction** | Very common (LRU, LFU, etc.) | Usually less important / configurable |
| **Persistence** | Often memory-first | Disk/SSD persistence is common |
| **Consistency** | Often eventual / stale data acceptable | Stronger consistency options generally available |
| **Typical use** | Sessions, frequently accessed data, DB query results | User profiles, orders, configurations, metadata |
| **Examples** | Redis used as a cache, Memcached | DynamoDB, Cassandra, RocksDB, Redis used as a KV database |

---
## Simple distinction

**Cache:** If I lose it, I can fetch it again.

**Key-value store:** If I lose it, I've potentially lost application data.

## Important: Redis can be both

Redis can be used as a cache:

```text
Application
    ↓
Redis
    ↓ cache miss
Database
```

Or as a key-value store / temporary application-state store:

```text
Redis
  |
  +-- seat:event123:A10 → RESERVED
  +-- seat:event123:A11 → AVAILABLE
  +-- reservation:user123 → ...
```

In a ticket-booking system:

```text
DB    = durable source of truth
Redis = temporary reservation / coordination layer
```

## Interview answer

> A cache is an optimization layer whose data can generally be reconstructed from a source of truth, while a key-value store is a storage system where the key-value data itself is the primary application data. A cache typically emphasizes low latency, TTL and eviction, whereas a key-value store emphasizes durability, availability, partitioning, replication and consistency. Technologies like Redis can serve both roles depending on how they're used.