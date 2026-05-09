# Low-Level Design (LLD)

**What interviewers want:** clear APIs, sensible classes, extensibility, thread-safety when relevant, and tradeoffs stated aloud.

---

## Core drills (implement + diagram)

1. **LRU Cache** — eviction, O(1) get/put (doubly linked list + map).
2. **Parking lot** — vehicles, spots, floors, pricing; extend for EV chargers.
3. **Elevator system** — requests, scheduling strategy (SCAN/FCFS), state machine.
4. **Library management** — books, copies, users, waitlist, fines.
5. **ATM / vending machine** — states, inventory, change-making (greedy limits).
6. **Chess / tic-tac-toe** — rules engine, move validation, win detection.
7. **Rate limiter** — token bucket, sliding window log; per-user vs global.
8. **Pub/sub in-process** — topics, subscribers, async dispatch option.
9. **Logger** — levels, appenders, formatting; optional async sink.
10. **Task scheduler** — cron-like triggers, dependency between jobs.

---

## Additional senior prompts

11. **Meeting scheduler / calendar** — recurring rules, conflicts, free/busy, time zones.
12. **Hotel / flight / seat booking** — inventory holds (TTL), overbooking policy, concurrency on rows.
13. **Splitwise-style ledger** — balances, simplified debts, idempotent settlements (invariant: sum zero per group).
14. **Notification service** — channels (push/email/SMS), user preferences, dedupe, rate caps.
15. **Ride / delivery matcher (simplified)** — dispatch rules, cancellation, driver state machine.
16. **Consistent hashing ring (API-level)** — add/remove node, virtual nodes—explain how you’d **simulate** or unit-test placement.

---

## LLD checklist

- Nouns → candidate classes; verbs → methods.
- Enumerate **requirements** (functional + non-functional: latency, scale, consistency).
- Call out **design patterns** only when they simplify (Strategy, Factory, Observer, State).
- Discuss **testing**: unit tests around rules and edge cases.
