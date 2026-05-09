# Quick Reference: Study Map for Senior Engineers

This page ties **existing prep materials** to **missing concepts** and provides a study sequence.

---

## How This Repo is Organized

```
✅ = Already well covered
⚠️ = Partially covered, needs depth
❌ = Missing or minimal coverage
```

### Existing Materials (✅ Strong Foundation)

| Topic | File | Coverage |
|-------|------|----------|
| Core Data Structures & Algorithms | leetcode-topics.md | ✅ Comprehensive |
| Low-Level Design (OOP, design patterns) | lld.md | ✅ Good |
| System Design Basics | system-design.md | ✅ Good (but see gaps below) |
| ACID/Consistency Models | core-concepts.md | ✅ Solid |
| Concurrency & Threads | concurrency-faqs.md | ✅ Deep |
| Messaging & Queues | core-concepts.md | ✅ Basics covered |
| Caching | core-concepts.md | ✅ Patterns covered |
| Behavioral & Leadership | overview.md | ✅ STAR format good |

### New/Missing Materials (Created alongside this document)

| Topic | File | Level |
|-------|------|-------|
| **+25 Missing Senior Concepts** | MISSING-SENIOR-CONCEPTS.md | 🔴 CRITICAL |
| **Role & Company Tailoring** | ROLE-AND-COMPANY-GUIDE.md | 🟡 MEDIUM |
| **This Quick Reference** | (you are here) | 🟢 GUIDE |

---

## Study Sequence Recommendation

### Phase 1: Assessment (Before you start)
**Time: 1-2 days**

1. Read [overview.md](00-overview.md) (behavioral + weekly template)
2. Skim **[SENIOR-INTERVIEW-GAP-REVIEW.md](SENIOR-INTERVIEW-GAP-REVIEW.md)** (audit + extra question checklist)
3. Review [MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md) **Priority Matrix** section
4. Find your role in [ROLE-AND-COMPANY-GUIDE.md](ROLE-AND-COMPANY-GUIDE.md)
5. Identify 5-7 topics most relevant to **your target role + company**

### Phase 2: Gap Identification (1 week)

Review your target topics in this order:
1. Read the "Why It Matters" section
2. Assess: Do I know this?
3. If yes: Note for interview polish
4. If no: Add to study plan

### Phase 3: Deep Study (Varies: 2-12 weeks)

For each topic:

**Step 1: Read (30-60 min)**
- Read the concepts from [MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md)
- Correlate to existing material where applicable
- Read linked resources (books, papers, blogs)

**Step 2: Implement (30-90 min)**
- Code or design the concept
- Don't just read about it—build something
- Examples per topic below

**Step 3: Practice (20 min)**
- Use the "Interview Hook" question
- Record yourself explaining (2 minutes)
- Identify tradeoffs and failure modes

**Step 4: Connect (10 min)**
- Link to your experience (what have you done?)
- Link to well-known company (how does Netflix do it?)
- Link to existing prep materials (connect concepts)

---

## Topic Study Guide with Examples

### 1. Cost Optimization & Resource Efficiency

**Existing material:** None (❌)

**Where to read:**
- [MISSING-SENIOR-CONCEPTS.md#1-cost-optimization](MISSING-SENIOR-CONCEPTS.md#1-cost-optimization--resource-efficiency)

**Implementation ideas:**
- [ ] Calculate TCO for a design you've done
- [ ] Optimize an existing system (reduce costs by 30%)
- [ ] Design "same functionality, 1/3 the cost"
- [ ] Build cost calculator tool (input: QPS, region, storage → cost projection)

**Practice:**
- Hook: "How would you reduce costs by 30% without sacrificing quality?"
- Time: 2 minutes explaining tradeoffs
- Mention: Reserved vs on-demand, storage tiering, caching ROI

**Connect to existing:**
- System design tradeoffs [system-design.md](system-design.md)
- Caching patterns [core-concepts.md](core-concepts.md#caching-patterns)

---

### 2. Machine Learning Systems & Inference

**Existing material:** None (❌)

**Where to read:**
- [MISSING-SENIOR-CONCEPTS.md#2-machine-learning-systems](MISSING-SENIOR-CONCEPTS.md#2-machine-learning-systems--inference)
- Book: "Designing Machine Learning Systems" by Chip Huyen
- Video: Uber ML architecture talk (YouTube)

**Implementation ideas:**
- [ ] Deploy a model endpoint locally (Hugging Face + FastAPI)
- [ ] Design feature store architecture (low-latency features)
- [ ] Build A/B test framework for ML models
- [ ] Create model versioning + canary strategy

**Practice:**
- Hook: "Design a recommendation system serving 100M users."
- Mention: Model serving latency, feature freshness, A/B testing
- Tradeoff: Real-time vs batch inference, model complexity vs latency

**Connect to existing:**
- Caching (for feature storage) [core-concepts.md](core-concepts.md#caching-patterns)
- Load balancing + routing [system-design.md](system-design.md#edge-and-traffic-management)
- Deployment patterns [system-design.md](system-design.md#deployment-and-change-safety)

---

### 3. Data Pipelines & Analytics

**Existing material:** Minimal (⚠️)

**Where to read:**
- [MISSING-SENIOR-CONCEPTS.md#3-data-pipelines](MISSING-SENIOR-CONCEPTS.md#3-data-pipelines--analytics-architecture)
- Book: "Fundamentals of Data Engineering"
- Tool: dbt documentation, Apache Airflow

**Implementation ideas:**
- [ ] Build ETL pipeline (raw data → warehouse)
- [ ] Deploy dbt project locally
- [ ] Design star schema for e-commerce (facts: orders, dimensions: products, users)
- [ ] Create streaming pipeline (Kafka → Spark → analytics DB)

**Practice:**
- Hook: "Design analytics for real-time dashboards + historical trends."
- Mention: Fact tables, slowly changing dimensions, exactly-once semantics
- Tradeoff: Lambda (batch + real-time) vs Kappa (streaming-only)

**Connect to existing:**
- Messaging [core-concepts.md](core-concepts.md#messaging--queues)
- Streaming and long-running work [system-design.md](system-design.md#streaming-and-long-running-work)
- Observability [system-design.md](system-design.md#observability-and-slos)

---

### 4. Product Sense & Metrics

**Existing material:** Mentioned but shallow (⚠️)

**Where to read:**
- [MISSING-SENIOR-CONCEPTS.md#4-product-sense](MISSING-SENIOR-CONCEPTS.md#4-product-sense--metrics-framework)
- Airbnb Metric Story blog
- Netflix/Uber talks on metrics

**Implementation ideas:**
- [ ] Define OKRs for a feature you built
- [ ] Design A/B test (hypothesis, sample size, duration)
- [ ] Create metric dashboard (what metrics at each level?)
- [ ] Plan alert strategy (thresholds, on-call impact)

**Practice:**
- Hook: "We want to improve user engagement by 20%. What metrics do you instrument?"
- Mention: Leading indicators, A/B testing, SLOs
- Tradeoff: Speed (fast shipping) vs reliability (few bugs)

**Connect to existing:**
- SLOs & error budgets [system-design.md](system-design.md#observability-and-slos)
- Golden signals [system-design.md](system-design.md#observability-and-slos)

---

### 5. Performance Profiling & Optimization

**Existing material:** Minimal (⚠️)

**Where to read:**
- [MISSING-SENIOR-CONCEPTS.md#5-performance-profiling](MISSING-SENIOR-CONCEPTS.md#5-performance-profiling--optimization)
- Brendan Gregg's performance tools
- Julia Evans' systems zines

**Implementation ideas:**
- [ ] Profile a local service (Java: JFR, Go: pprof, Python: cProfile)
- [ ] Generate flame graphs
- [ ] Identify slow queries (EXPLAIN ANALYZE)
- [ ] Benchmark serialization formats (JSON vs Protobuf)

**Practice:**
- Hook: "Your API has 100ms p50 but 1s p99. Debug."
- Mention: Profilers, flame graphs, bimodal latency, stratification
- Tradeoff: Precision (per-function detail) vs cost (profiling overhead)

**Connect to existing:**
- LeetCode complexity analysis (time/space) [leetcode-topics.md](leetcode-topics.md#practice-discipline)
- Concurrency & lock contention [concurrency-faqs.md](concurrency-faqs.md#testing-tooling-observability)

---

### 6. Chaos Engineering & Resilience Testing

**Existing material:** Minimal (⚠️)

**Where to read:**
- [MISSING-SENIOR-CONCEPTS.md#6-chaos-engineering](MISSING-SENIOR-CONCEPTS.md#6-chaos-engineering--resilience-testing)
- Netflix Chaos Monkey (OSS)
- "Chaos Engineering" (O'Reilly)

**Implementation ideas:**
- [ ] Run Gremlin or Chaos Toolkit
- [ ] Design fault injection test (network delay, service down)
- [ ] Create game day scenario (manual chaos exercise)
- [ ] Build chaos testing CI/CD stage

**Practice:**
- Hook: "How would you test that your system survives a region outage?"
- Mention: Blast radius, steady-state hypothesis, automation
- Tradeoff: Safety (controlled blast radius) vs realism

**Connect to existing:**
- Circuit breakers [core-concepts.md](core-concepts.md#resilience-patterns)
- Reliability & DR [system-design.md](system-design.md#reliability-and-operations)
- Incident postmortem [system-design.md](system-design.md#reliability-and-operations)

---

### 7. Security at Scale

**Existing material:** Partial (⚠️)

**Where to read:**
- [core-concepts.md](core-concepts.md) (TLS, AuthN/AuthZ) + [MISSING-SENIOR-CONCEPTS.md#7-security-at-scale](MISSING-SENIOR-CONCEPTS.md#7-security-at-scale)
- OWASP Top 10 (2021)
- "BeyondCorp" (Google whitepaper)

**Implementation ideas:**
- [ ] Threat model a service (STRIDE)
- [ ] Design secret rotation system
- [ ] Build API key management service
- [ ] Create security checklist for deployments

**Practice:**
- Hook: "One service's AWS credentials are compromised. What's contained?"
- Mention: Least privilege, workload identity, audit trails
- Tradeoff: Security (restrictive policies) vs agility (fast deployment)

**Connect to existing:**
- AuthN/AuthZ [core-concepts.md](core-concepts.md#authn-vs-authz--tokens)
- TLS [core-concepts.md](core-concepts.md#tls--https-one-minute-story)

---

### 8. Microservices Beyond Saga

**Existing material:** Partial (⚠️)

**Where to read:**
- [core-concepts.md#saga-pattern](core-concepts.md#saga-pattern-distributed-transactions)
- [MISSING-SENIOR-CONCEPTS.md#8-microservices-patterns](MISSING-SENIOR-CONCEPTS.md#8-microservices-patterns-beyond-saga)
- Book: "Microservices Patterns" by Chris Richardson
- DDD (Domain-Driven Design) resources

**Implementation ideas:**
- [ ] Design service boundary based on DDD
- [ ] Implement choreography saga (event-driven)
- [ ] Implement orchestration saga (coordinator service)
- [ ] Design CQRS (separate read/write models)
- [ ] Design event sourcing (event log as source of truth)

**Practice:**
- Hook: "Order Service and Payment Service must stay consistent. How?"
- Mention: Saga patterns, eventual consistency, event sourcing
- Tradeoff: Simplicity (monolith sync) vs scale (async services)

**Connect to existing:**
- Saga pattern [core-concepts.md](core-concepts.md#saga-pattern-distributed-transactions)
- Event-driven architecture [core-concepts.md](core-concepts.md#messaging--queues) and Saga [core-concepts.md](core-concepts.md#saga-pattern-distributed-transactions)
- Messaging [core-concepts.md](core-concepts.md#messaging--queues)

---

### 9. Time-Series Data & Observability

**Existing material:** Partial (⚠️)

**Where to read:**
- [system-design.md#observability-and-slos](system-design.md#observability-and-slos)
- [MISSING-SENIOR-CONCEPTS.md#9-time-series-data](MISSING-SENIOR-CONCEPTS.md#9-time-series-data--observability)
- Prometheus documentation
- "Observability Engineering" (O'Reilly)

**Implementation ideas:**
- [ ] Deploy Prometheus + Grafana
- [ ] Design alerting rules (static, dynamic, anomaly)
- [ ] Create TSDB schema (metric names, labels)
- [ ] Address cardinality explosion
- [ ] Build heatmap query

**Practice:**
- Hook: "Your monitoring ingests 1M metrics/sec. Dashboard is slow. Fix it."
- Mention: Cardinality, downsampling, retention tiers
- Tradeoff: Resolution (1s vs 5m data) vs storage cost

**Connect to existing:**
- SLO/error budget [system-design.md](system-design.md#observability-and-slos)
- Metrics/logs/traces [system-design.md](system-design.md#observability-and-slos)

---

### 10-26. Other Topics

Refer to [MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md) for:
- Search systems (#10)
- Payment systems (#11)
- Real-time analytics (#12)
- Migrations (#13)
- Fraud detection (#14)
- Recommendations (#15)
- Backward compatibility (#16)
- Distributed tracing (#17)
- Resource quotas (#18)
- Feature flags (#19)
- Testing at scale (#20)
- ADRs & governance (#21)
- Incident management (#22)
- Onboarding (#23)
- Rate limiting depth (#24)
- Sustainability (#25)
- Org scaling (#26)

---

## Integration Matrix: Existing → New Concepts

This shows how new concepts **layer on top** of existing prep materials:

```
EXISTING MATERIALS (Foundation)
├── [leetcode-topics.md] Algorithms & data structures
├── [lld.md] OOP & design patterns
├── [core-concepts.md] 
│   ├── ACID/consistency ────→ [NEW: Microservices beyond Saga]
│   ├── Caching ────────────→ [NEW: Cost optimization]
│   ├── Messaging ──────────→ [NEW: Data pipelines]
│   ├── Concurrency ────────→ [NEW: Performance profiling]
│   └── Security ───────────→ [NEW: Security at scale]
└── [system-design.md]
    ├── Classic systems ────→ [NEW: Search, payment, fraud, recommendations]
    ├── Observability ─────→ [NEW: Time-series TSDB]
    ├── Reliability ───────→ [NEW: Chaos engineering, incident management]
    └── Deployment ───────→ [NEW: Feature flags, migrations]

BEHAVIORAL
└── [overview.md] STAR stories ──→ [NEW: Leadership & org scaling]
```

---

## Pre-Interview Week Timetable

```
Monday      Read 2-3 priority topics from MISSING-SENIOR-CONCEPTS
Tuesday     Implement 1 topic (code/design)
Wednesday   Practice 2 mock interviews
Thursday    Refine behavioral stories, review tradeoffs
Friday      Final review, full mock interview
Saturday    Rest + light review
Sunday      Final prep, get sleep
```

---

## Red Flags: Topics You MUST Know

These are **not optional** for senior interview success:

- [ ] **SLOs & observability** (you own reliability)
- [ ] **Incidents & postmortems** (how you learn)
- [ ] **Security threats** (you're responsible)
- [ ] **Scalability tradeoffs** (your designs must grow)
- [ ] **Testing & quality** (you ship production code)
- [ ] **Backward compatibility** (you don't break other teams)
- [ ] **Cost consciousness** (businesses run on budgets)
- [ ] **Team communication** (you're senior—you lead)

---

## Interview Day Tips

1. **Ask question first** — "Tell me about the product/users/scale?"
2. **Clarify requirements** — Never assume what "design X" means
3. **Mention constraints** — latency budget, consistency requirements
4. **Draw early** — start with simple, iterate to complex
5. **Define metrics** — how do you know your design works?
6. **Explain tradeoffs** — consistency vs latency, cost vs performance
7. **Mention failure modes** — "If this queue fails, we can…"
8. **Reference sizing** — "1M users means ~100 RPS, so 1 server can…"
9. **Discuss monitoring** — "We'd alert if p99 latency > 500ms"
10. **Be honest** — "I haven't worked on this, but here's my approach…"

---

## Good Luck! 🚀

You've got this. Focus on:
1. Understanding **why** decisions matter (not just what)
2. Explaining **tradeoffs** with confidence
3. Connecting theory to **your experience**
4. Showing **judgment** (when to use each pattern)

Remember: Interviewers are assessing if you can:
- ✅ Own a critical system
- ✅ Scale it as business grows
- ✅ Lead engineers and make decisions
- ✅ See around corners (anticipate problems)

---

*This guide is your map. The destination is a strong interview.*

