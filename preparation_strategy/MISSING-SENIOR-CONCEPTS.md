# Missing Senior Concepts for FAANG/MAANG Interviews

## Executive Summary

After reviewing all preparation strategy documents and existing implementations, this document identifies **25+ critical gaps** that senior software engineers (L5+) should master but are currently **under-represented** or **entirely missing** from the prep materials.

---

## 1. COST OPTIMIZATION & RESOURCE EFFICIENCY ⚠️ CRITICAL

### Why It Matters
- Senior roles include **business acumen** and **ROI thinking**
- You'll be asked: "How would you reduce costs by 30% without sacrificing quality?"
- Amazon Leadership Principle: "Frugality"

### Missing Topics
- **Cost-aware architecture decisions**
  - Compute (on-demand vs reserved vs spot instances)
  - Storage tiering (hot/warm/cold) with cost implications
  - Network egress costs and data transfer patterns
  - Caching ROI calculations
- **Capacity planning & forecasting**
  - Predicting load growth and reserved capacity
  - When to scale vs when to optimize
  - Cost projections over 6/12/24 months
- **Optimization tradeoffs**
  - Latency vs cost per request
  - Consistency vs compute (eventual consistency saves money)
  - Batch processing vs real-time (cost differential)

### Interview Hook
> "Design a system for 1M users today but will scale to 100M. How does that change your cost strategy vs just optimizing for now?"

### Prep Action
- [ ] Study cloud TCO (total cost of ownership) models
- [ ] Learn resource rightsizing techniques
- [ ] Practice "reduce this by 40%" prompt for existing designs

---

## 2. MACHINE LEARNING SYSTEMS & INFERENCE 🔴 HIGH PRIORITY

### Why It Matters
- **Every large company** has ML/AI in production
- Senior engineers need to discuss **model serving**, **feature stores**, **training pipelines**
- Not about being an ML expert but understanding **architecture** and **scale**

### Missing Topics
- **Model serving infrastructure**
  - Online scoring latency requirements
  - Batch inference vs real-time
  - Model versioning and canary rollouts
  - A/B testing for ML models
  - Inference optimization (quantization, pruning)
- **Feature engineering infrastructure**
  - Feature stores (where/how features live)
  - Feature freshness vs latency
  - Training-serving skew mitigation
- **Data pipelines for ML**
  - ETL → model training → inference serving loop
  - Data quality and drift detection
  - Monitoring model performance in prod

### Interview Hook
> "How would you design a recommendation system that serves personalized content to 100M users daily?"

### Prep Action
- [ ] Review ML system design patterns (Uber, Airbnb style talks)
- [ ] Learn feature store concepts (Feast, Tecton)
- [ ] Understand training/serving architecture separation

---

## 3. DATA PIPELINES & ANALYTICS ARCHITECTURE 🔴 HIGH PRIORITY

### Why It Matters
- **Data-driven decisions** are central to modern systems
- You need to know: ETL/ELT, data lakes, warehouses, governance
- Increasingly expected at senior levels

### Missing Topics
- **ETL/ELT patterns**
  - When to use Extract-Transform-Load vs Extract-Load-Transform
  - Incremental vs full refresh
  - Exactly-once processing guarantees
- **Data warehouse design**
  - Fact tables, dimension tables, slowly changing dimensions
  - Star schema vs snowflake
  - Materialized views and aggregate tables
- **Real-time analytics**
  - Streaming analytics (Kafka → Flink/Spark → warehouse)
  - Lambda architecture (batch + real-time layer merge)
  - Kappa architecture (streaming-only)
- **Data quality & governance**
  - Validation pipelines
  - Schema evolution and data lineage
  - PII masking and compliance

### Interview Hook
> "Design the analytics infrastructure for an e-commerce platform. Users want real-time dashboards, but also need historical trend analysis."

### Prep Action
- [ ] Learn dimensional modeling (Kimball methodology)
- [ ] Understand stream processing (Kafka, Flink, Spark)
- [ ] Review dbt (data build tool) and orchestration patterns (Airflow)

---

## 4. PRODUCT SENSE & METRICS FRAMEWORK 🟡 MEDIUM PRIORITY

### Why It Matters
- Senior engineers **define success metrics**
- You must map features to business KPIs
- FAANG heavily tests "product thinking"

### Missing Topics
- **OKR/metric hierarchy**
  - Company goals → team goals → system metrics
  - Why vanity metrics mislead
  - Leading vs lagging indicators
- **Instrumentation & data collection**
  - What to log, sample rates, PII handling
  - Event schemas and versioning
  - Taxonomy for consistent naming
- **A/B testing & experimentation**
  - Statistical significance and power
  - Network effects and interference
  - Multi-armed bandit vs controlled experiments
- **Dashboard design**
  - What metrics to surface at each level
  - Alert thresholds and on-call implications
  - Real-time dashboards vs batch reports

### Interview Hook
> "We want to improve user engagement by 20%. What metrics do you instrument? How do you know if your changes work?"

### Prep Action
- [ ] Study Airbnb/Netflix metric systems (public talks)
- [ ] Learn A/B test design fundamentals
- [ ] Practice OKR decomposition

---

## 5. PERFORMANCE PROFILING & OPTIMIZATION 🟡 MEDIUM PRIORITY

### Why It Matters
- "Is it fast enough?" requires evidence
- Profilers, flame graphs, and benchmarking are **concrete skills**
- Optimization without data is wasted effort

### Missing Topics
- **Profiling techniques**
  - CPU profilers (flame graphs, per-function time)
  - Memory profilers (heap dumps, allocation tracking)
  - I/O profilers (disk, network latency)
  - Wall-clock time vs user time vs system time
- **Benchmarking**
  - Microbenchmarks (JMH, Go bench, etc.)
  - Macrobenchmarks (realistic workload)
  - Avoiding benchmark pitfalls (warmup, GC, CPU frequency)
- **Common bottlenecks & fixes**
  - Database query optimization (EXPLAIN ANALYZE)
  - Serialization overhead
  - Allocation hot spots and object pooling
  - Lock contention (false sharing, lock timing)
- **Latency debugging**
  - P99 vs average (tail latency matters)
  - Bimodal distributions and stratification by request type
  - Service mesh and RPC latency breakdown

### Interview Hook
> "Your API has 100ms p50, but 1s p99. What's happening and how do you narrow it down?"

### Prep Action
- [ ] Practice with local profilers (perf, YourKit, etc.)
- [ ] Learn flame graph interpretation
- [ ] Study common Java/Go/Python performance anti-patterns

---

## 6. CHAOS ENGINEERING & RESILIENCE TESTING 🟠 MEDIUM-HIGH PRIORITY

### Why It Matters
- Netflix/Uber/Amazon actively use chaos engineering
- Tells interviewers you **embrace failure as a design tool**
- Beyond just "write a circuit breaker"

### Missing Topics
- **Chaos engineering practice**
  - Blast radius scope (single AZ, region, partial failure)
  - Steady state observability before chaos
  - Killing instances, latency injection, bandwidth throttling
  - Documenting hypotheses and findings
- **Fault injection testing**
  - Network faults (latency, jitter, packet loss)
  - Dependency failures (timeouts, errors)
  - Cascading failure scenarios
  - Test automation (not just manual)
- **Resilience patterns applied**
  - When circuit breakers actually help vs hurt
  - Bulkhead sizing under chaos (thread pool depletion)
  - Retry storms and how to prevent (deadline, exponential backoff + jitter)
- **Game day simulations**
  - Structured war games
  - Incident response practice
  - Postmortem culture and action item tracking

### Interview Hook
> "How would you test that your system survives a region going down? Walk me through your approach."

### Prep Action
- [ ] Review Chaos Toolkit or Gremlin documentation
- [ ] Study Netflix's Chaos Engineering approach (public blogs)
- [ ] Design a fault injection strategy for a familiar system

---

## 7. SECURITY AT SCALE 🔴 HIGH PRIORITY

### Why It Matters
- Security is now a **senior engineer responsibility**
- Not just "use HTTPS" but **threat modeling, supply chain, data governance**
- Every FAANG company asks this

### Missing Topics (expand beyond core-concepts.md basics)
- **Authentication & authorization evolution**
  - OAuth 2.0 resource owner password credential (deprecated) vs modern flows
  - API key management at scale
  - Workload identity (service-to-service without keys)
  - SSO / SAML / OpenID Connect integration
- **Supply chain security**
  - Dependency vulnerability scanning (SBOM, SCA tools)
  - Securing the build pipeline (artifact signing, provenance)
  - Runtime security (CVE remediation SLAs)
- **Data security & compliance**
  - Encryption at rest and in transit (key rotation, KMS policies)
  - Field-level encryption for PII
  - Secrets rotation (no hardcoded secrets, automatic rollover)
  - Audit logging and compliance trails
  - Data residency and cross-region replication
- **Threat modeling & response**
  - STRIDE in depth (Spoofing, Tampering, Repudiation, Information Disclosure, Denial of Service, Elevation)
  - Security incident response playbooks
  - Vulnerability disclosure and SLA
- **Compliance & governance**
  - SOC 2, HIPAA, PCI-DSS, GDPR requirements at system design level
  - Data retention policies and deletion at scale
  - Audit trails and access logging

### Interview Hook
> "Walk me through your security architecture. An attacker gains AWS credentials for a service account—what's contained?"

### Prep Action
- [ ] Review OWASP Top 10 + modern additions (API security)
- [ ] Understand workload identity and ephemeral credentials
- [ ] Study a real breach postmortem (Okta, Uber, etc.)

---

## 8. MICROSERVICES PATTERNS BEYOND SAGA 🟠 MEDIUM-HIGH PRIORITY

### Why It Matters
- Most FAANG companies are microservices-heavy
- Saga is covered, but many patterns are missing
- This is where "senior design" vs "mid-level design" emerges

### Missing Topics
- **Service boundaries & decomposition**
  - Domain-driven design (DDD) and bounded contexts in practice
  - When to split a service vs when it's premature
  - Owning a service (on-call, SLA)
- **Communication patterns**
  - Synchronous (REST, gRPC) vs async (events, queues)
  - Request/response with retries vs fire-and-forget
  - RPC error handling and cascade prevention
  - Service mesh vs library-based (Istio vs Envoy vs SDKs)
- **Data consistency across services**
  - Saga pattern — touched in [core-concepts.md](core-concepts.md) (also **transactional outbox** there)
  - **CQRS** — separate read/write models; *not detailed in core-concepts.md* (study §8 below + Greg Young talks)
  - **Event sourcing** — event log as source of truth; *not detailed in core-concepts.md* (pair with Saga/outbox mentally)
  - NEW: **eventual consistency + reconciliation loops**
  - NEW: **compensating transactions at scale** (rollback complexity)
- **Shared libraries & versioning**
  - Proto/schema versioning (backward/forward compatibility)
  - Dependency management (java-commons, go-standard-lib-style)
  - When to build an SDK vs client library
- **Organizational scaling**
  - How team structure affects service design (Conway's Law)
  - API review boards and governance
  - Tech debt accrual across services

### Interview Hook
> "You're splitting a monolith into services. How do you handle data consistency when Order Service and Payment Service must stay in sync?"

### Prep Action
- [ ] Read "Building Microservices" by Sam Newman (2nd ed)
- [ ] Study organizational scaling (two-pizza team concept)
- [ ] Review a real-world microservices migration case study

---

## 9. TIME-SERIES DATA & OBSERVABILITY 🔴 HIGH PRIORITY

### Why It Matters
- Distinct from generic "observability" section
- Time-series databases (TSDB) are increasingly critical
- Metrics routing, retention, and aggregation at scale

### Missing Topics
- **TSDB architecture**
  - Why time-series differs from regular databases (write-heavy, immutable, downsample)
  - Schema (metric name, labels, timestamp, value)
  - Cardinality explosion problem (too many label combinations)
  - Query languages (PromQL, InfluxQL)
- **Retention and sampling**
  - High-res (1s) → 1m → 5m → 1h aggregation (downsampling)
  - Raw retention (14 days) vs archived metrics (1 year)
  - Ingestion sampling strategies
- **Alerting robustness**
  - Alert fatigue (too many false positives)
  - Threshold tuning (static vs dynamic baselines / ML-based)
  - Alert routing and escalation
  - Flap prevention (minimum alert duration)
- **Advanced observability**
  - Percentile tracking (p50, p99, p999) at scale
  - Heatmaps and distributions (vs single numbers)
  - Anomaly detection (statistical + ML approaches)

### Interview Hook
> "Your monitoring system ingests 1M metrics/sec. The dashboard is now slow. How do you diagnose and fix?"

### Prep Action
- [ ] Run a Prometheus/Grafana setup locally
- [ ] Study cardinality management in TSDB
- [ ] Review alerting best practices (Google, Kubernetes docs)

---

## 10. SEARCH SYSTEMS & RANKING 🟠 MEDIUM-HIGH PRIORITY

### Why It Matters
- Increasingly a "separate" system from main app logic
- Google, Amazon, Netflix, Meta all have dedicated search orgs
- Tie to: relevance, ranking, indexing, performance

### Missing Topics
- **Indexing & query processing**
  - Inverted indexes and how they scale
  - Full-text search (Elasticsearch, Solr)
  - Ranking functions (TF-IDF, BM25, learning-to-rank)
- **Relevance & ranking**
  - Collaborative filtering vs content-based vs hybrid
  - Query expansion and intent understanding
  - A/B testing ranking changes (without gaming metrics)
  - Diversity in results (avoid echo chambers)
- **Geo-spatial search**
  - Geohashing and spatial indexes
  - Nearby queries (ride matching, restaurant discovery)
  - Distance calculations at scale
- **Search infrastructure**
  - Multi-datacenter indexing (consistency)
  - Query caching and result cache patterns
  - Real-time vs batch index updates
  - Index shard allocation and rebalancing

### Interview Hook
> "Design a search system for an e-commerce platform with 1M products. Users want fast, relevant results. How do you rank results?"

### Prep Action
- [ ] Deploy Elasticsearch locally
- [ ] Learn ranking algorithms (TF-IDF, BM25)
- [ ] Study Airbnb/Uber search architectures (public talks)

---

## 11. PAYMENT SYSTEMS & FINANCIAL CORRECTNESS 🔴 HIGH PRIORITY

### Why It Matters
- E-commerce, fintech, subscriptions all rely on this
- Unique concerns: **race conditions lose money**, **compliance**, **audit trails**
- Rarely gets full system design treatment

### Missing Topics
- **Payment state machine**
  - Pending → authorized → captured → settled
  - Authorization expiry and timeout handling
  - Retry semantics for idempotency
  - Partial refunds and void/refund races
- **Reconciliation**
  - Settlement from payment processor (daily batch)
  - Detecting discrepancies (payment recorded locally but not captured)
  - Reversal flows (fraud, chargebacks)
- **Wallet/balance systems**
  - Atomic balance updates (row-level locks or compare-and-swap)
  - Ledger-based vs account-based (history tracking)
  - Balance visibility and eventual consistency implications
- **Subscription billing**
  - Recurring charges (daily/weekly/monthly)
  - Proration on plan changes
  - Failed payment retry policies (exponential backoff)
  - Cycle-based billing and grace periods
- **PCI compliance**
  - Never touch raw card data (tokenization)
  - SAQ compliance levels
  - Secure transmission (TLS, key rotation)

### Interview Hook
> "Design a billing system for a SaaS platform with thousands of paying customers. How do you ensure charges are correct and audit trails exist?"

### Prep Action
- [ ] Review Stripe/Square API documentation
- [ ] Study idempotency in financial systems
- [ ] Learn basic PCI compliance requirements

---

## 12. REAL-TIME ANALYTICS & STREAMING ARCHITECTURES 🟡 MEDIUM PRIORITY

### Why It Matters
- Beyond "Kafka topics"—how to actually **serve** insights in real-time
- Kappa vs Lambda architecture decisions
- Window semantics and late-arriving data

### Missing Topics
- **Stream processing frameworks**
  - Kafka Streams, Apache Flink, Apache Spark Streaming
  - Stateful processing (joins, aggregations, sessionization)
  - Watermarks and late-arriving event handling
  - Windowing (tumbling, sliding, session windows)
- **Event time vs processing time vs ingestion time**
  - Why wall-clock time is unreliable
  - Out-of-order delivery handling
  - Timestamp propagation and allowed lateness
- **Serving real-time insights**
  - Stream → cache (Redis) → dashboard (WebSocket)
  - Push vs pull models
  - Backpressure under load
- **Kappa vs Lambda architecture**
  - Kappa: recomputability from immutable log (simpler)
  - Lambda: batch + real-time layers (complex but handles historical correction)
  - When each is appropriate

### Interview Hook
> "Design a real-time dashboard showing top-10 products trending in the last hour, updated every minute globally."

### Prep Action
- [ ] Run a Kafka + Spark Streaming pipeline locally
- [ ] Study stateful stream processing
- [ ] Compare windowing semantics across frameworks

---

## 13. MIGRATION STRATEGIES (Monolith → Microservices, DB, etc.) 🟡 MEDIUM PRIORITY

### Why It Matters
- You'll be asked about **data migration** and **zero-downtime deployment**
- Shows you understand operational risk
- Every senior engineer does migrations

### Missing Topics
- **Monolith to microservices**
  - Strangler pattern (new service handles subset, revert traffic gradually)
  - Database coupling (shared DB vs per-service)
  - Big bang vs incremental (incremental much safer)
- **Database migrations**
  - Zero-downtime schema changes (expand/contract pattern)
  - Data backfill strategies (batches, async workers)
  - Validation and dual-write during transition
  - Rollback scenarios
- **Storage layer migrations**
  - Old DB → new DB (Redis cache → Memcached, MySQL → Spanner, etc.)
  - Dual-write + verification phase
  - Cutover (parallel reads, then write cutover, then cleanup)
- **API versioning during migration**
  - Old client behavior vs new (adapter pattern)
  - Feature flags to toggle implementations
  - Sunset timelines and deprecation cycles
- **Verification and rollback**
  - Canary migration (small % of data/traffic first)
  - Shadow traffic (read consistency checks)
  - Rollback procedures and decision criteria

### Interview Hook
> "You need to migrate 100GB of user data from MySQL to a new NoSQL store with zero downtime and no data loss. Walk me through it."

### Prep Action
- [ ] Study expand/contract pattern in depth
- [ ] Review feature flag strategies
- [ ] Plan a hypothetical migration (write it out)

---

## 14. FRAUD DETECTION & ANOMALY DETECTION SYSTEMS 🟡 MEDIUM PRIORITY

### Why It Matters
- Fintech, e-commerce, ads all need this
- Combines real-time, ML, and heuristics
- High false positive/negative tradeoffs

### Missing Topics
- **Rule-based fraud detection**
  - Velocity checks (too many transactions in short window)
  - Geographic anomalies (impossible travel)
  - Device fingerprinting and changes
  - Size anomalies (unusual charge amount)
- **ML-based detection**
  - Training data (balanced vs imbalanced classes)
  - Real-time scoring (low latency required)
  - Model decay and retraining frequency
  - Explainability (why was transaction flagged?)
- **Feedback loops**
  - Chargebacks and fraud labels → retraining
  - Customer disputes and feedback
  - Query delays in training data
- **Risk scoring & friction**
  - Allowing risky transactions with monitoring
  - Challenging user with verification (2FA) vs blocking
  - Conversion rate impact analysis

### Interview Hook
> "Design a fraud detection system for a payment network. How do you balance false positives (rejecting good customers) vs true positives (catching fraud)?"

### Prep Action
- [ ] Learn anomaly detection algorithms (isolation forest, LOF)
- [ ] Study imbalanced classification techniques
- [ ] Review fraud detection trade-off analysis

---

## 15. RECOMMENDATION & PERSONALIZATION SYSTEMS 🟡 MEDIUM PRIORITY

### Why It Matters
- Core competency at Meta, Netflix, Amazon, TikTok
- Combines ML, real-time, and ranking
- Interview differentiator

### Missing Topics
- **Recommendation algorithms**
  - Collaborative filtering (user-user, item-item)
  - Content-based filtering
  - Hybrid approaches
  - Matrix factorization and embeddings
- **Real-time personalization**
  - Real-time feature computation
  - Cold start problem (new users, new items)
  - Exploration vs exploitation (bandit algorithms)
  - Diversity and serendipity (avoid only recommending similar items)
- **Ranking & re-ranking**
  - Combining multiple signals (collaboration + content + business)
  - Learning-to-rank models
  - Best-seller bias and fairness
- **A/B testing recommendations**
  - Network effects (your recommendations affect others' data)
  - Interference between users
  - Metric selection (click-through rate vs long-term engagement)
- **Scalability**
  - Computing recommendations for millions of users (batch)
  - Serving at low latency (caching, filtering)
  - Model serving and inference optimization

### Interview Hook
> "Design Netflix's recommendation system for 200M users. How do you balance personalization with computational cost?"

### Prep Action
- [ ] Learn collaborative filtering algorithms
- [ ] Study embeddings and representation learning basics
- [ ] Review contextual bandits for exploration/exploitation

---

## 16. BACKWARD COMPATIBILITY & API EVOLUTION 🟡 MEDIUM PRIORITY

### Why It Matters
- Not covered well in core-concepts (just API versioning snippet)
- Senior engineers own **API contracts** and breaking changes
- Large-scale systems can't afford breaking everything

### Missing Topics
- **Additive vs breaking changes**
  - New optional fields (safe) vs removed fields (breaking)
  - Enum extensions (implications for clients)
  - Default values and versioning
- **Deprecation workflows**
  - Sunset headers and timelines
  - Client compatibility matrix (% of clients on old version)
  - Forced migration dates
- **JSON/Proto versioning tactics**
  - Field renaming without breaking (dual support)
  - Schema evolution (forward/backward/full compatibility)
  - Serialization format changes
- **Client-side resilience**
  - Graceful degradation when new fields missing
  - Default values for optional fields
  - Client-side versioning and feature detection
- **Coordinated deployment**
  - New server version → must support old clients (version negotiation)
  - Client upgrades can happen in parallel
  - No "flag day" cutover if avoidable

### Interview Hook
> "Your API currently returns `{'status': 'active'}` for posts. You want to add `{'status': 'active'} → {'lifecycle_state': {status: 'active', archived_at: null}`. How do you migrate 1M clients without breaking?"

### Prep Action
- [ ] Study Stripe's API versioning (public docs)
- [ ] Review Protocol Buffers backward/forward compatibility
- [ ] Plan API evolution for a hypothetical service

---

## 17. DISTRIBUTED TRACING & REQUEST CORRELATION 🟡 MEDIUM PRIORITY

### Why It Matters
- Beyond "distributed traces" in observability
- Request ID propagation and context is a senior skill
- Debugging across 50 services needs this

### Missing Topics
- **Trace context standards**
  - W3C Trace Context (traceparent, tracestate)
  - Correlation ID propagation (every service must pass it)
  - Baggage (lightweight context like user ID)
- **Span semantics**
  - Parent-child relationships in distributed calls
  - Span attributes (user ID, error, business context)
  - Sampling strategies (% sampling vs tail-based sampling)
- **Instrumentation patterns**
  - Automatic instrumentation (agents) vs manual
  - Framework-level tracing (Spring, Express, etc.)
  - Database query tracing
  - RPC library tracing
- **Trace analysis & debugging**
  - Finding slow requests (latency distribution)
  - Error traces (stack traces across services)
  - Comparing traces (what changed between versions?)
  - Cost of tracing (storage, ingestion)
- **Privacy in traces**
  - Scrubbing PII from traces
  - Customer data in debug context

### Interview Hook
> "A user reports their dashboard took 5 seconds to load. Walk me through how you'd debug this across 20 microservices."

### Prep Action
- [ ] Deploy Jaeger or Zipkin locally
- [ ] Instrument a multi-service application
- [ ] Review trace context propagation libraries

---

## 18. RESOURCE QUOTA & MULTI-TENANCY 🟡 MEDIUM PRIORITY

### Why It Matters
- Cloud-native thinking (Kubernetes), SaaS platforms
- Shows you understand **noisy neighbor** problem
- Not covered in current materials

### Missing Topics
- **Quota enforcement patterns**
  - Per-tenant rate limits
  - Resource quota (CPU, memory, storage per customer)
  - Enforcement points (API gateway, service level, DB level)
- **Resource isolation**
  - Bulkheads (separate thread pools per tenant)
  - Database-level multi-tenancy (shared vs per-tenant DB)
  - Blast radius containment
- **Fair queuing & scheduling**
  - Weighted fair queuing (premium tier gets more)
  - Priority queues (important requests first)
  - Starvation prevention
- **Billing implications**
  - Usage tracking (API calls, CPU/memory, storage)
  - Quota vs billing (soft limits, overages)
  - Meter accuracy and audit trails

### Interview Hook
> "You run a SaaS platform used by 1000 companies. One customer's job floods your system with requests, affecting others. How do you isolate and fairly allocate resources?"

### Prep Action
- [ ] Study Kubernetes resource quotas and LimitRanges
- [ ] Learn fair queuing algorithms
- [ ] Design multi-tenant isolation for a familiar service

---

## 19. FEATURE FLAGS & PROGRESSIVE DELIVERY 🟡 MEDIUM PRIORITY

### Why It Matters
- Beyond canary deployments
- Allows decoupling **deployment** from **feature release**
- Enables quick rollback and experimentation

### Missing Topics
- **Feature flag varieties**
  - Kill switches (emergency off)
  - Gradual rollout (% of users)
  - Targeted flags (by user segment, region, client version)
  - Experiment flags (A/B test)
- **Flag infrastructure**
  - Central control (feature flag service)
  - Evaluation latency (in-app evaluation vs service call?)
  - Flag dependencies (one flag requires another)
  - Flag testing (unit tests + integration tests)
- **Operational practices**
  - Flag lifespan (clean up old flags)
  - Documentation (why this flag exists)
  - Access control (who can toggle production flags?)
  - Audit trail
- **SDK patterns**
  - Local evaluation (low latency)
  - Caching and staleness tolerance
  - Fallback defaults (flag service down)

### Interview Hook
> "How would you deploy a new ranking algorithm to 50% of users, measure impact, and rollback if metrics degrade—without redeploying code?"

### Prep Action
- [ ] Deploy LaunchDarkly or Unleash locally
- [ ] Design a feature flag strategy document
- [ ] Understand flag rollout math (% calculation)

---

## 20. TESTING AT SCALE 🟠 MEDIUM-HIGH PRIORITY

### Why It Matters
- Not enough coverage of **integration testing, contract testing, chaos**
- Senior engineers define testing strategy
- Not just "write unit tests"

### Missing Topics (beyond core-concepts testing mention)
- **Contract testing**
  - API contracts between services
  - Schema validation (request/response)
  - Backward compatibility validation
  - Consumer-driven contracts
- **Integration testing**
  - Test containers (Testcontainers)
  - Real DB vs mock DB (when each?)
  - Test data generation and seeding
  - Flaky test detection and remediation
- **Load testing & capacity testing**
  - When to run (before major release)
  - Realistic payload and access patterns
  - Resource limits discovery (at what QPS does DB max out?)
  - Ramp-up vs sustained load
- **Chaos engineering (tactical testing)**
  - Fault injection in testing (network delay, errors)
  - Simulation of unavailability
  - Blast radius scoping
- **Property-based testing**
  - Defining invariants (what must always be true?)
  - Generative testing frameworks
  - Metamorphic relationships (if A works, then B must work too)

### Interview Hook
> "How do you ensure a system scales 10x under load? What tests do you run before declaring it safe?"

### Prep Action
- [ ] Study contract testing frameworks (PACT, Spring Cloud Contract)
- [ ] Run Testcontainers for local integration tests
- [ ] Design a chaos test scenario

---

## 21. ARCHITECTURE DECISION RECORDS (ADRs) & GOVERNANCE 🟡 MEDIUM PRIORITY

### Why It Matters
- Shows **judgment** and **communication skills**
- Decisions without rationale = tribal knowledge = hard to scale
- Knowledge transfer to new team members

### Missing Topics
- **ADR format & best practices**
  - Title, context, decision, consequences, alternatives considered
  - Why the chosen approach (tradeoffs)
  - When to revisit (sunset dates)
  - Who signs off (architect, leads)
- **RFC (Request for Comments) process**
  - Large decisions with team input
  - Open discussion on design before implementation
  - Async working (not just meetings)
  - Level of formality by impact
- **Decision tracking & lifecycle**
  - Decisions that evolve (original intent vs current reality)
  - Reversing decisions (with cost analysis)
  - Communicating decisions widely
- **Governance vs velocity**
  - When heavy process hurts (over-architecture)
  - When light process hurts (chaos)
  - Scaling governance as team grows

### Interview Hook
> "Walk me through an important architectural decision your team made. Why did you choose the approach over alternatives?"

### Prep Action
- [ ] Write an ADR for a familiar system
- [ ] Review real RFC examples (Kubernetes KEP, Rust RFCs)
- [ ] Study governance scaling

---

## 22. INCIDENT MANAGEMENT AT SCALE 🟠 MEDIUM-HIGH PRIORITY

### Why It Matters
- Senior engineers **define incident response**
- Not just "be on-call" but **process, tooling, learning**
- Under-discussed in current prep

### Missing Topics (expand on brief section)
- **Incident classification & escalation**
  - Severity levels (P1 vs P4)
  - Escalation criteria (who gets paged?)
  - Runbook-driven vs ad-hoc
- **Incident command system (ICS)**
  - Incident commander role (leads response)
  - Communications lead (status updates)
  - Ops/engineering lead (does work)
  - Rotating responsibilities
- **Blameless postmortem culture**
  - Focus on systems, not individuals
  - Root cause vs contributing factors
  - Action items with owners (not vague)
  - Metrics (MTTR, incident frequency, resolution time)
- **On-call burden management**
  - Alert noise reduction
  - Oncall shift handoff
  - Compensation (extra time off, pay)
  - Burnout prevention
- **Communication during incidents**
  - Status page (external customers)
  - Slack/IRC (internal team)
  - Customer updates (template + frequency)
  - Post-incident summary

### Interview Hook
> "Your main service had an outage today. Walk me through how you'd respond, who you'd involve, and how you'd learn from it."

### Prep Action
- [ ] Review incident severity matrix
- [ ] Draft an incident response runbook
- [ ] Study blameless postmortem examples

---

## 23. ONBOARDING & DOCUMENTATION 🟡 MEDIUM PRIORITY

### Why It Matters
- Senior engineers **own knowledge transfer**
- "Self-documenting code" isn't enough
- Scales poorly without good onboarding

### Missing Topics
- **Required documentation**
  - Architecture diagram (C4 model layers)
  - Data schema and relationships
  - API reference (OpenAPI/GraphQL schema)
  - Operational runbook (how to deploy, troubleshoot)
  - Decision records (why things are the way they are)
- **Runbook patterns**
  - "System is slow" → investigate steps
  - "Service failing to start" → debug steps
  - Common issues and resolutions
- **Onboarding checklist**
  - Environment setup (getting dependencies running)
  - First contribution (good starter tasks)
  - Code review standards
  - On-call runbook
  - Team norms and culture
- **Keeping docs current**
  - Docs as code (version controlled with code)
  - Automated testing of examples
  - Link rot detection
  - Assigned owner per section

### Interview Hook
> "You're spinning up a team of 5 new engineers from scratch on a critical service. What documentation and process do you put in place?"

### Prep Action
- [ ] Review good documentation examples (GitHub's Accelerator, etc.)
- [ ] Write a system architecture document
- [ ] Create an operational runbook

---

## 24. RATE LIMITING & THROTTLING (deeper than basics) 🟡 MEDIUM PRIORITY

### Why It Matters
- Covered briefly in core-concepts but needs **production depth**
- Resource protection is critical at scale
- Different at global vs local scope

### Missing Topics
- **Rate limiting algorithms** (covered: token bucket, sliding window)
  - NEW: **Hierarchical rate limiting** (global limit + per-user limit)
  - NEW: **Fair queuing** under limits (not just reject)
  - NEW: **Burst allowance** (allow spikes, then throttle)
- **Distributed rate limiting**
  - Coordinating across multiple services/regions
  - Inconsistency windows (converged state)
  - Redis-based implementation (Lua scripts)
- **Rate limit response**
  - 429 status code semantics
  - **Retry-After** header (when can they retry?)
  - Backpressure feedback (upstream can adjust)
  - Client-side backoff strategies
- **Multi-tier limits**
  - Global limits (total service capacity)
  - Per-tenant limits (fair allocation)
  - Per-endpoint limits (API endpoint-specific)
  - Per-header limits (API key, user, IP)
- **Monitoring limits**
  - Are we rejecting too much? (not using capacity)
  - Are limits being hit? (capacity pressure)
  - Legitimate traffic vs attack traffic

### Interview Hook
> "Your API gets 10M requests/sec globally. Design a rate limiting system that protects the service but doesn't unfairly punish legitimate traffic."

### Prep Action
- [ ] Study token bucket implementation
- [ ] Design distributed rate limiter architecture
- [ ] Review AWS API Gateway throttling policies

---

## 25. ENVIRONMENTAL THINKING & SUSTAINABILITY 🟢 EMERGING

### Why It Matters
- Growing concern at tech companies
- Shows **responsibility** and **long-term thinking**
- Some companies explicitly ask

### Missing Topics
- **Energy efficiency**
  - Code optimizations that reduce compute (less CO2)
  - Data center efficiency (PUE metric)
  - Scheduling workloads (off-peak, renewable energy zones)
- **Data minimization**
  - Retention policies (don't keep forever)
  - Compression strategies
  - Tiering cold data to cheaper storage
- **Sustainable infrastructure choices**
  - Cloud provider carbon commitments
  - Regional choices based on energy mix
  - Hardware lifecycle and e-waste

### Interview Hook
> "How would you design a system to minimize environmental impact while maintaining performance?"

### Prep Action
- [ ] Review carbon footprint of cloud providers
- [ ] Consider energy efficiency in a design discussion

---

## 26. ORGANIZATIONAL & TEAM SCALING 🟡 MEDIUM PRIORITY

### Why It Matters
- Staff/senior engineer interviews ask about **scaling teams**
- System design should reflect org structure (Conway's Law)
- Missed in current prep

### Missing Topics
- **Team structure & service ownership**
  - 1 team per service, 1 service per team (ideal?)
  - Shared service ownership (complexity)
  - Cross-functional pods (product + eng + data)
- **Communication patterns**
  - Trunk-based development (vs long branches)
  - Code review SLA and turnaround
  - Architectural review boards
  - Cross-team dependencies
- **Knowledge sharing**
  - Tech talks and lunch-and-learns
  - Rotation programs (engineers try different teams)
  - Documentation quality
- **Scaling culture**
  - Hiring without sacrificing culture
  - Promotion ladders (IC vs manager track)
  - Career development conversations

### Interview Hook
> "You have one team of 3 engineers owning a critical service. As the business grows, you need to scale to 10 teams. How do you structure this? Any risks?"

### Prep Action
- [ ] Study Conway's Law application
- [ ] Review team scaling case studies (Uber, Airbnb)
- [ ] Discuss org structure tradeoffs

---

## Quick Prep Priority Matrix

### 🔴 CRITICAL (Do these within 1-2 weeks before interview)
1. **Cost Optimization & Resource Efficiency**
2. **Security At Scale** (expand existing section)
3. **Machine Learning Systems**
4. **Data Pipelines & Analytics**
5. **Product Sense & Metrics** (at least basics)
6. **Payment Systems** (if fintech-adjacent role)

### 🟠 HIGH PRIORITY (2-3 weeks)
7. **Performance Profiling & Optimization**
8. **Chaos Engineering & Resilience Testing**
9. **Microservices Patterns (beyond Saga)**
10. **Time-Series Data & Observability**
11. **Incident Management at Scale**

### 🟡 MEDIUM PRIORITY (3-4 weeks)
12. **Search Systems & Ranking**
13. **Real-time Analytics & Streaming**
14. **Migration Strategies**
15. **Fraud Detection Systems**
16. **Recommendation Systems**
17. **Backward Compatibility & API Evolution**
18. **Distributed Tracing & Request Correlation**
19. **Feature Flags & Progressive Delivery**
20. **Rate Limiting (deeper)**
21. **Testing at Scale**
22. **ADRs & Governance**
23. **Onboarding & Documentation**
24. **Resource Quota & Multi-Tenancy**

### 🟢 NICE-TO-HAVE (Time permitting)
25. **Environmental Thinking & Sustainability**
26. **Organizational & Team Scaling**

---

## Action Plan Template

For each missing concept, use this template:

```
## TOPIC: [Name]

### Core Concepts (30 min read)
- [ ] [Link to resource]
- [ ] [Link to resource]

### Implementation Drill (60 min)
- [ ] Implement [concrete example] locally
- [ ] Compare with [alternative approach]

### Design Interview Practice (30 min)
- [ ] Practice answer to standard hook question
- [ ] Record yourself and review

### Production Depth (30 min)
- [ ] Study a real-world case study
- [ ] Review architecture decision
```

---

## Recommended Reading & Resources

### Books (deeper than prep docs)
- **"Designing Machine Learning Systems"** by Chip Huyen (ML ops)
- **"Fundamentals of Data Engineering"** by Joe Reis & Matt Housley (data pipelines)
- **"Microservices Patterns"** by Chris Richardson (microservices)
- **"Site Reliability Engineering"** (Google) (observe, incident, chaos)
- **"Building Secure & Reliable Systems"** (Google) (security, resilience)

### Online Courses & Talks
- **Goku Mohandas** - MLOps course (free, excellent)
- **Alex Xu System Design** - TikTok, Uber, Airbnb design talks
- **Netflix Tech Blog** - Chaos monkey, microservices evolution
- **Honeycomb.io** - Observability deep dives
- **CQRS/Event Sourcing talks** - Greg Young

### Hands-On Practice
- **Playground projects:**
  - [ ] Build a simple recommendation system (collaborative filtering)
  - [ ] Design a feature flag service with rollout
  - [ ] Implement distributed rate limiter with Redis
  - [ ] Set up observability stack (Prometheus, Grafana, Jaeger)
  - [ ] Create ETL pipeline (Airflow + Spark)
  - [ ] Design schema migration (expand/contract)

---

## Next Steps

1. **Scan** this document and identify 3-5 topics most relevant to your target role
2. **Prioritize** these topics by company (different companies emphasize different areas)
3. **Deep dive** into 1-2 per week before your interview
4. **Practice** explaining each concept out loud (2 min version, + tradeoffs + failure modes)
5. **Connect** each concept to real examples from your experience or public companies

Good luck! 🚀


