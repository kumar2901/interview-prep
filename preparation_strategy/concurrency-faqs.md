# Concurrency FAQs — senior software engineer depth

Questions and answers recruiters and staff+ engineers often probe. Answers are **language-agnostic** unless noted; map primitives to your stack (`pthread`, Java `java.util.concurrent`, C++ `std::mutex`, Go channels + mutexes, Rust ownership/`Arc`, etc.).

---

## 1. Foundations

### What is the difference between **concurrency** and **parallelism**?

**Concurrency** is *dealing with* many tasks in overlapping time (scheduling, interleaving on fewer cores). **Parallelism** is *doing* multiple tasks at the same instant (multiple cores/hardware units). A single-core scheduler can be highly concurrent but not parallel; embarrassingly parallel workloads need parallelism to finish faster.

**Senior angle:** Design for concurrency (structure, isolation, backpressure); measure speedup when parallelism actually exists (avoid **Amdahl’s law** bottlenecks—serial sections cap gains).

---

### What is the difference between a **race condition** and a **data race**?

A **race condition** is a *logical* flaw: program behavior depends on timing/order of events you didn’t control (e.g., two requests both “check balance then debit” interleave wrongly).

A **data race** is a *formal* memory-model concept: two unsynchronized accesses to the same location, at least one write, with no **happens-before** ordering between them. Languages like C++/Rust/Java define data races as undefined behavior or forbidden patterns.

**Senior angle:** Removing data races (atomics, synchronization) does **not** automatically fix race conditions—you still need **invariants** and correct locking granularity (e.g., two locks updating related fields can still break business rules).

---

### Why is unsynchronized **check-then-act** unsafe?

Thread A reads state, decides to act; before it acts, Thread B changes state; A still acts on stale reasoning → broken invariant.

**Senior angle:** Prefer **compare-and-swap (CAS)** loops for single-word updates, **transactions** (DB or STM rarely in systems interviews), or **one authoritative serializer** (actor/queue per shard) so check-and-act is atomic at the right boundary.

---

## 2. Primitives: mutex, semaphore, monitor, RW lock

### When do you use a **mutex** vs a **semaphore**?

**Mutex:** mutual exclusion—at most one owner; maps to “critical section” protecting an invariant.

**Semaphore (counting):** N permits—limits concurrency (pool size, throttle), not always tied to one invariant.

**Senior angle:** Mutex misuse is usually *wrong lock* or *too coarse*; semaphore misuse is often *forgotten release* or *wrong permit count*. Binary semaphore ≈ mutex semantically but mutex often has **ownership** (only releaser is acquirer)—cleaner for structured critical sections.

---

### What is a **reentrant** (recursive) lock?

Same thread can acquire the lock again without deadlocking itself. Useful when **call graphs** re-enter synchronized APIs.

**Senior angle:** Non-reentrant locks force refactor or accidental deadlock when helpers call back into public locked methods—know your API layering.

---

### **Read–write lock** vs mutex?

Many concurrent readers **or** exclusive writers—good when reads dominate and critical section is non-trivial.

**Senior angle:** RW locks can **underperform** mutexes under moderate contention (extra bookkeeping; readers can starve writers if unfair). Profile before assuming “more granular = faster.”

---

### What is a **monitor**?

Abstract pattern: mutex + **condition variable(s)** + wait/notify discipline—Encapsulates “wait until predicate true while holding mutex structure correctly.” Most OO locks (`synchronized` + `wait`/`notify` in Java) are monitor-style.

**Senior angle:** **Spurious wakeup** mandate: always wait in a **loop** rechecking the predicate.

---

### **Spinlock** vs **blocking** mutex?

**Spinlock:** burns CPU until lock free—only sane for **very short** holds on cores you “own” (kernel/driver, dedicated pinning).

**Blocking mutex:** yields/schedules—better for user-space long sections.

**Senior angle:** Lock convoys—many threads wake for one lock; fairness vs throughput tradeoffs.

---

## 3. Deadlock, livelock, starvation

### What are the **four Coffman conditions** for deadlock?

1. Mutual exclusion  
2. Hold and wait  
3. No preemption of locks  
4. Circular wait  

Break any one (e.g., **total lock ordering**, **try-lock + backoff**, **lock timeouts** with careful rollback) to prevent classic deadlock.

---

### **Deadlock** vs **livelock** vs **starvation**?

- **Deadlock:** cyclic waiting; no progress.  
- **Livelock:** threads keep changing state “politely” yielding—no useful progress (e.g., both retry symmetrically forever).  
- **Starvation:** some threads rarely get the resource (unfair lock, priority scheduler abuse).

**Senior angle:** Production mitigations: **lock ordering docs**, **deadlock detection** in diagnostics, **avoid nested locks** across layers or invert dependency graph.

---

### What is **priority inversion**?

Low-priority thread holds lock needed by high-priority thread; medium tasks preempt low → high waits indirectly.

**Mitigation:** **priority inheritance** or **priority ceiling** (protocols in RTOS/real-time Java); in servers often “don’t mix strict priorities with arbitrary locks without policy.”

---

## 4. Memory model, visibility, ordering

### What does **happens-before** mean?

A partial order guaranteeing: if A happens-before B, then A’s effects are **visible** to B for correctly synchronized programs. Without HB, compilers/CPUs **reorder** loads/stores.

**Senior angle:** Interview tests whether you know **“release” writes publish data** and **“acquire” reads** ensure you see prior releases—mutex unlock/lock establishes HB edges.

---

### Why isn’t a single “volatile” / atomic flag enough for **compound updates**?

Volatile/atomic variable gives **visibility + ordering** for *that* variable’s reads/writes—not atomicity across **multiple fields** or **read-modify-write** sequences unless you use stronger atomics or locks.

**Senior angle:** **Publication idioms**: immutable object fully constructed then reference published; or use proper atomic structs / locks.

---

### What is **false sharing**?

Independent variables share a **cache line**; cores invalidate each other’s caches on writes → severe slowdown.

**Mitigation:** **padding**, align to cache line (`64B` typical), separate hot counters per core then aggregate.

---

## 5. Atomics and lock-free structures

### How does **CAS** work, and what is **ABA**?

**CAS(addr, expected, new):** atomically set `*addr = new` if `*addr == expected`; else fail.

**ABA:** Thread 1 sees A, stalls; Thread 2 changes A→B→A; Thread 1’s CAS “succeeds” but **logical structure** may be wrong (e.g., reclaimed memory reused).

**Mitigation:** **versioned pointers** (tagged pointers), **hazard pointers**, **epoch-based reclamation**, **GC**—language/runtime dependent.

---

### **Lock-free** vs **wait-free**?

**Lock-free:** system-wide progress—some thread completes in finite steps (others may starve in theory on some definitions; clarify definition).

**Wait-free:** **every** thread completes in bounded steps independent of others—stronger, harder, rare in practice.

**Senior angle:** Lock-free ≠ faster; often **more complex**, **reclamation hard**, **worse worst-case** latency spikes unless carefully designed.

---

## 6. Patterns seniors should own

### **Producer–consumer**: blocking queue vs condition variable?

**Blocking queue:** bounded queue gives natural **backpressure**—producers block when full.

**Condition variables:** maximum control; easy to get wrong (wrong predicate, missed signal—always use loops).

**Senior angle:** Choose **bounded** queues to prevent unbounded memory growth under burst load.

---

### How do you think about **thread pool sizing**?

Rules of thumb (not laws):  
- **CPU-bound:** threads ≈ cores (± small overhead).  
- **IO-bound:** higher—bounded by memory, downstream limits, and **Little’s Law** intuition (\(L \approx \lambda W\): concurrency × latency ↔ throughput).

**Senior angle:** Separate pools per dependency (**bulkhead**); avoid **single giant pool** saturating one bottleneck.

---

### What is wrong with naive **double-checked locking** for singletons?

Without proper **publication barriers**, another thread can see a **partially constructed** object when reference becomes non-null.

**Fix:** holder idiom (static init guarantees), **volatile**/`atomic` with correct semantics, or language-native once-primitives.

---

## 7. Async I/O, actors, and isolation

### **Threads** vs **async** vs **processes**?

- **Processes:** isolation, fault blast radius, heavier IPC.  
- **Threads:** shared memory, cheaper fork—risk races.  
- **Async (callbacks/Futures/coroutines):** multiplex many logical tasks on few threads—great for IO-bound; **still** need synchronization if touching shared mutable state across tasks.

**Senior angle:** **Structured concurrency** (parent waits for children; cancel propagation) reduces orphan tasks and leaks.

---

### What does the **actor model** buy you?

Single-threaded message processing **per actor** → no locks inside actor; scale by **sharding** actors. Failure as first-class (**supervision**) in Erlang/Akka mental models.

**Senior angle:** Cross-actor **consistency** still hard—need sagas, CRDTs, or accepted eventual consistency.

---

## 8. Distributed concurrency (boundary awareness)

### How is a **distributed lock** different from `mutex`?

Locks don’t fail “cleanly” like on one machine: **process pause**, **GC**, **clock skew**, **network partition** → split brain if naïve.

**Senior angle:** **Fencing tokens**: lock grants monotonic token; storage rejects stale writes—classic Martin Kleppmann framing.

---

## 9. Testing, tooling, observability

### How do you **test** concurrent code?

- **Stress** + **deterministic** harness (seeded scheduler where available).  
- **Property-based** + sequential specification comparators (**linearizability** checks for small models).  
- **Sanitizers:** TSAN (Clang/GCC), Go race detector, Java jcstress for JMM.

**Senior angle:** Prefer **design** that minimizes shared state; **pure functions** + message passing easier to verify than “sprinkle synchronized.”

---

### What do you look for in **production**?

Lock wait time, **thread pool queue depth**, scheduler latency, **context switch** rates, allocation pressure (locks + contention → retries).

---

## 10. Quick “follow-up” checklist before interviews

| Topic | Can you explain in 90 seconds? |
|-------|-------------------------------|
| HB edge from unlock→lock | ✓ |
| Why DCL without barriers breaks | ✓ |
| CAS + ABA | ✓ |
| RW lock pitfalls | ✓ |
| Bounded queue + backpressure | ✓ |
| False sharing | ✓ |
| Distributed lock + fencing | ✓ |

---

## Related material

- Short recap table: [core-concepts.md](core-concepts.md) (Concurrency fundamentals + Process vs thread).
