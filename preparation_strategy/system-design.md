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

### Top 25 System Design Questions for Senior Engineers

| # | System | Key talking points | Difficulty |
|---|--------|-------------------|------------|
| 1 | URL Shortener | Base62 ID, collision handling, redirect SLA, analytics | Medium |
| 2 | Notification System | Multi-channel, delivery guarantees, retries, compliance | Hard |
| 3 | Rate Limiter | Token bucket, distributed coordination, hierarchical limits | Medium |
| 4 | Cache System (Redis) | Eviction policies, replication, thundering herd, consistency | Hard |
| 5 | Search/Autocomplete | Trie/B-tree, ranking, sharding by prefix, fuzzy matching | Hard |
| 6 | Chat System | Presence, ordering, deduplication, WebSocket, encryption | Hard |
| 7 | Video Streaming | CDN, transcoding, adaptive bitrate, recommendation | Hard |
| 8 | File Storage | Chunking, deduplication, sync, version history, sharing | Hard |
| 9 | Payment Processing | Idempotency, PCI compliance, fraud detection, reconciliation | Hard |
| 10 | Social Feed | Fan-out strategy, ranking, real-time updates, moderation | Hard |
| 11 | Ride-Sharing | Geospatial indexing, matching algorithm, surge pricing, ETA | Hard |
| 12 | Distributed Database | Sharding, replication, quorum, anti-entropy repair | Hard |
| 13 | CDN | Edge placement, cache invalidation, origin shield, DDoS | Hard |
| 14 | Recommendation Engine | Collaborative filtering, cold start, diversity, A/B testing | Hard |
| 15 | Monitoring/Alerting | TSDB design, metrics aggregation, anomaly detection | Hard |
| 16 | Job Scheduler | DAG workflows, dependencies, retries, resource allocation | Hard |
| 17 | Real-Time Analytics | Event ingestion, aggregation, privacy, multi-tenancy | Hard |
| 18 | Distributed Lock | Consensus (Raft/Paxos), leader election, network partitions | Hard |
| 19 | API Gateway | Routing, auth/z, rate limiting, transformation | Medium |
| 20 | Message Queue | Partitioning, consumer groups, exactly-once, retention | Hard |
| 21 | Secrets Management | Encryption, rotation, audit logging, access control | Medium |
| 22 | Distributed Tracing | Span propagation, sampling, storage, integration | Hard |
| 23 | Feature Flags | Evaluation, rollout strategies, A/B testing, governance | Medium |
| 24 | Container Orchestration | Scheduling, resource mgmt, service discovery, deployments | Hard |
| 25 | Search Engine | Inverted index, query optimization, sharding, relevance | Hard |

**Additional Classic Problems**

| System | Key talking points |
|--------|-------------------|
| Pastebin | TTL, abuse detection, blob storage |
| News feed | Fan-out on write vs read, ranking, cache |

---

## Numbers to internalize (order-of-magnitude)

- ~1M seconds ≈ 11.5 days; use for QPS × payload ≈ bandwidth.
- SSD random read ~100 µs–ms scale; cross-region RTT ~30–150 ms.
- JSON overhead vs protobuf; compression on wire vs CPU.

**Back-of-envelope sizing guide:** [back-of-envelope-sizing.md](back-of-envelope-calculations.md) ([alternate path](back-of-envelope-calculations.md))

**Quick formula:** RPS = DAU × requests_per_user / 86,400 → Peak = RPS × 5 → Servers = Peak / 10K

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

---

## Top 25 System Design Questions for Senior Engineers (FAANG)

**These are the most frequently asked at L5+ interviews.** Each includes key talking points and senior-level depth. Practice explaining architecture, tradeoffs, and operational concerns.

### 1. **Design a URL Shortener (TinyURL)**
- Base62 encoding (62^7 ≈ 3.5T URLs)
- Collision handling via counter or UUID
- Sharding strategy by ID range
- Redirect SLA (< 100ms, 99.9% availability)
- Analytics layer for click tracking
- Rate limiting and abuse detection
- **Senior angle:** Global CDN for redirects, cost optimization, data durability

### 2. **Design a Notification System (Email/Push/SMS)**
- Device/user preference management
- Multi-channel delivery (FCM, APNs, Twilio)
- Message queuing with reliability guarantees
- Exponential backoff retry strategy
- Per-user and global rate limiting
- Real-time delivery status tracking
- **Senior angle:** Multi-tenant isolation, compliance (GDPR), A/B testing

### 3. **Design a Rate Limiter (Distributed)**
- Token bucket algorithm with sliding window
- Redis-based coordination for consistency
- Hierarchical limits (global, per-user, per-endpoint)
- Burst allowance and sustained rate handling
- 429 response with Retry-After headers
- **Senior angle:** Dynamic scaling, cost-based throttling, fairness under load

### 4. **Design a Cache (Redis-like)**
- Eviction policies (LRU, LFU, TTL-based)
- Single-threaded vs multi-threaded architecture
- Replication and failover strategy
- Cluster sharding and consistent hashing
- Thundering herd prevention (singleflight)
- **Senior angle:** Memory efficiency, persistence (RDB/AOF), client-side caching

### 5. **Design a Search/Autocomplete System**
- Trie data structure with prefix matching
- Sharding by prefix ranges
- Real-time index updates
- Ranking and relevance scoring
- Fuzzy matching and typo tolerance
- **Senior angle:** Multi-language support, spellcheck integration, ranking personalization

### 6. **Design a Chat System (WhatsApp/Slack)**
- Message ordering and deduplication
- Presence indicators (online/offline/typing)
- WebSocket vs long polling trade-offs
- Group chat fan-out strategy
- Message history with efficient retrieval
- **Senior angle:** End-to-end encryption, offline delivery queue, read receipts

### 7. **Design a Video Streaming Platform (Netflix/YouTube)**
- Adaptive bitrate streaming (HLS/DASH)
- CDN and edge computing strategy
- Transcoding pipeline (batch + on-demand)
- Video recommendation engine
- DRM and content protection
- **Senior angle:** Cost optimization, global distribution, quality metrics (QoE)

### 8. **Design File Storage (Dropbox/Google Drive)**
- File chunking and deduplication
- Metadata store and search indexing
- Sync conflict resolution (CRDTs vs LWW)
- Version history and point-in-time recovery
- Cross-device synchronization protocol
- **Senior angle:** Large file handling, sharing permissions, collaborative editing

### 9. **Design a Payment Processing System**
- PCI-DSS compliance and security
- Idempotency keys for transaction replay
- Payment state machine (pending→captured→settled)
- Fraud detection and risk scoring
- Multi-currency support and exchange rates
- **Senior angle:** Regulatory compliance, chargeback handling, reconciliation

### 10. **Design a Social Feed (Twitter/Facebook)**
- Fan-out strategies (write vs read amplification)
- Timeline ranking and personalization
- Real-time updates via WebSockets
- Content moderation and filtering
- **Senior angle:** Algorithmic ranking, engagement metrics, misinformation detection

### 11. **Design Ride-Sharing (Uber/Lyft)**
- Geospatial indexing (quadtrees, S2 geometry)
- Supply-demand matching algorithm
- Surge pricing and dynamic pricing
- ETA calculation and route optimization
- **Senior angle:** Real-time matching at scale, driver incentives, regulatory compliance

### 12. **Design a Distributed Database (Cassandra/DynamoDB)**
- Consistent hashing for sharding
- Replication factor and consistency levels
- Read/write quorum semantics
- Merkle tree-based anti-entropy repair
- **Senior angle:** Multi-region deployment, backup strategy, operational monitoring

### 13. **Design a CDN (Content Delivery Network)**
- Edge server placement and intelligent routing
- Cache invalidation and purge strategies
- Origin shielding to reduce origin load
- SSL termination and security at edge
- **Senior angle:** Cost-aware routing, DDoS mitigation, performance optimization

### 14. **Design a Recommendation Engine**
- Collaborative filtering (matrix factorization)
- Content-based and hybrid approaches
- Real-time ranking vs batch processing
- Cold start problem mitigation
- **Senior angle:** A/B testing, diversity vs accuracy, user privacy, embedding cache

### 15. **Design a Monitoring/Alerting System (Prometheus/DataDog)**
- Time-series storage (TSDB design)
- Metrics collection and aggregation
- Alert rule evaluation and deduplication
- Anomaly detection algorithms
- **Senior angle:** Cardinality management, multi-tenant isolation, cost scaling

### 16. **Design a Job Scheduler (Airflow/Cron)**
- DAG-based workflow definitions
- Task dependency resolution and ordering
- Failure handling and retry mechanisms
- Resource allocation and queue management
- **Senior angle:** Distributed execution, scaling, monitoring compliance

### 17. **Design Real-Time Analytics (Segment/Mixpanel)**
- Event ingestion at scale (millions QPS)
- Real-time vs batch aggregation
- Data retention and partitioning strategy
- Privacy (GDPR/CCPA) and PII handling
- **Senior angle:** Cost optimization, multi-tenancy, data quality assurance

### 18. **Design a Distributed Lock Service (etcd/Consul)**
- Consensus algorithm (Raft/Paxos)
- Leader election and failover
- Lock acquisition/release and watch semantics
- Network partition handling
- **Senior angle:** Performance under contention, distributed deadlock prevention

### 19. **Design an API Gateway**
- Request routing and load balancing
- Authentication/authorization enforcement
- Request transformation and validation
- Rate limiting and throttling
- **Senior angle:** Plugin architecture, canary deployments, A/B routing

### 20. **Design a Message Queue (Kafka/RabbitMQ)**
- Topic partitioning and ordering guarantees
- Consumer groups and rebalancing
- Exactly-once delivery semantics
- Durability and retention policies
- **Senior angle:** Cross-datacenter replication, performance optimization, cost

### 21. **Design Secrets/Configuration Management (Vault)**
- Encrypted storage with access control
- Secret rotation and expiration
- Audit logging and compliance
- Multi-datacenter replication
- **Senior angle:** Key rotation strategies, integration with orchestration platforms

### 22. **Design a Distributed Tracing System (Jaeger/Zipkin)**
- Span propagation and trace context
- Sampling strategies (percentage vs tail-based)
- Storage backend (Elasticsearch/Cassandra)
- Integration with logging and metrics
- **Senior angle:** Performance impact, data retention, debugging distributed issues

### 23. **Design Feature Flag System (LaunchDarkly)**
- Flag evaluation and user targeting
- Rollout strategies (percentage, gradual)
- A/B testing and experimentation
- Audit trails and change governance
- **Senior angle:** Performance (edge evaluation), consistency, operational safety

### 24. **Design Container Orchestration (Kubernetes-like)**
- Pod scheduling and bin-packing
- Resource management and QoS tiers
- Service discovery and networking
- Rolling updates and canary deployments
- **Senior angle:** Multi-cluster federation, auto-scaling, security policies

### 25. **Design Search Engine (Elasticsearch)**
- Inverted index construction
- Query parsing and optimization
- Sharding strategy for large indices
- Real-time vs batch indexing
- **Senior angle:** Multi-tenancy isolation, geospatial queries, relevance tuning

---

## Senior-Level Expectations for All Designs

**Always address these topics:**

1. **Scale & Capacity**
   - "How many users/requests can it handle?"
   - "What if it grows 10x?"
   - Back-of-envelope calculations with justification

2. **Reliability & Resilience**
   - "What happens if a component fails?"
   - Redundancy strategy, failover testing
   - SLO/SLA targets and how to achieve them

3. **Cost & Efficiency**
   - "What's the TCO?"
   - Storage, compute, network cost breakdown
   - How to optimize without sacrificing quality

4. **Security**
   - Data encryption (at rest and in transit)
   - Authentication and authorization models
   - Compliance and regulatory requirements

5. **Observability**
   - Monitoring strategy and key metrics
   - Alerting thresholds and on-call runbooks
   - Debugging and incident response

6. **Operational Concerns**
   - Deployment and rollback procedures
   - Operational complexity and manual effort
   - Team ownership and runbook maintenance

7. **Trade-offs & Justification**
   - Why this technology over alternatives?
   - Consistency vs availability vs partition tolerance
   - Latency vs throughput vs cost
   - Complexity vs maintainability

---

## Practice Framework

**For each question (45-60 minutes):**
1. **Clarify requirements** (5-10 min)
   - Scale, SLA, constraints, existing systems
2. **Propose high-level design** (15-20 min)
   - Components, architecture, data flow
3. **Deep dive into specifics** (15-20 min)
   - Database choice, caching strategy, API design
4. **Discuss trade-offs** (10-15 min)
   - Alternative approaches and why you chose yours
5. **Address failures** (5-10 min)
   - Failure modes, monitoring, recovery

**Mock interview tips:**
- Draw diagrams (use [ROLE-AND-COMPANY-GUIDE.md](ROLE-AND-COMPANY-GUIDE.md) for role-specific prompts)
- Mention concrete numbers (not vague "scale")
- Discuss what you'd measure and monitor
- Be honest about trade-offs ("This approach trades consistency for availability because...")
- Mention operational complexity upfront

---

*Updated: May 2026 | Based on FAANG interview experiences and senior engineer expectations*
