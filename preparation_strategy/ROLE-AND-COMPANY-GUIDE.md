# Senior Interview Prep — Tailored by Role & Company

This is a **companion guide** to customize your prep based on your target role and company. Use this alongside [MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md).

---

## Role-Based Emphasis

### Backend Engineer (L5+)

**Must-focus topics (from MISSING-SENIOR-CONCEPTS):**
1. **Data Pipelines & Analytics** (🔴 Critical) — backend powers analytics
2. **Microservices Patterns** (🟠 High) — likely working with 20+ services
3. **Machine Learning Systems** (🔴 Critical) — increasingly backend's work
4. **Security at Scale** (🔴 Critical) — you hold the keys
5. **Payment Systems** (🔴 Critical if fintech) — if e-commerce/financial domain
6. **Database Depth** (existing core-concepts) — is your specialty
7. **Incident Management** (🟠 High) — on-call heavily on you
8. **Distributed Tracing** (🟡 Medium) — debugging your mess

**Standard focus (already covered):**
- System design fundamentals ✓
- Concurrency & threading ✓
- Caching patterns ✓
- Queues & messaging ✓
- ACID/transactions ✓

**Can deprioritize:**
- Frontend considerations
- Mobile offline sync
- UI/UX

**Company-specific variations:**
- **Amazon:** Cost optimization ⬆️⬆️, operational metrics
- **Google:** Scale at distributed systems, observability
- **Meta:** Real-time systems, messaging, efficiency
- **Netflix:** Chaos engineering, operational resilience
- **Uber/Lyft:** Geo-spatial, real-time dispatch, payments

---

### Frontend Engineer (L5+)

**Must-focus topics:**
1. **Product Sense & Metrics** (🟡 Medium) ← you own user experience metrics
2. **Performance Optimization** (🟠 High) ← frontend is user's bottleneck
3. **Backward Compatibility & API Evolution** (🟡 Medium) ← breaking backend changes affect you
4. **Feature Flags & Progressive Delivery** (🟡 Medium) ← critical for A/B testing
5. **Security at Scale** (🔴 Critical) — XSS, CSRF, data leaks
6. **Real-time APIs & Webhooks** (existing system-design) ✓
7. **Caching** (existing core-concepts) ✓ — browser + CDN + local
8. **Testing at Scale** (🟡 Medium) — visual regression, E2E

**Standard focus:**
- System design (cache-heavy, API patterns) ✓
- Observability from user perspective
- Network optimization

**Can deprioritize:**
- Payment systems details
- Backend database design depth
- Server-side locking

---

### Full-Stack Engineer (L5+)

**Must-focus topics:**
1. **All backend topics** (see Backend section)
2. **All frontend topics** (see Frontend section)
3. **Product Sense & Metrics** (🟡 Medium) — tie backend to user value
4. **Incident Management** (🟠 High) — you're owning end-to-end
5. **Onboarding & Documentation** (🟡 Medium) — you have to understand both worlds

---

### Infrastructure / Platform Engineer (L5+)

**Must-focus topics:**
1. **Resource Quota & Multi-Tenancy** (🟡 Medium) ← infrastructure problem
2. **Observability & Time-Series Data** (🟠 High) — you're building it
3. **Chaos Engineering** (🟠 High) — you test infrastructure
4. **Performance Profiling** (🟠 High) — deep dive on kernel, hardware
5. **Security at Scale** (🔴 Critical) — network, compliance, secrets
6. **Networking** (existing core-concepts TCP/UDP/TLS) ✓
7. **Kubernetes-style thinking** (similar to resource quotas)
8. **Cost Optimization** (🔴 Critical) — you're the costkeeper

**Deep dives:**
- Concurrency & memory models (system level)
- Consensus algorithms (etcd, Raft)
- Service mesh (if applicable)

---

### ML Systems Engineer / ML Ops (L5+)

**Must-focus topics:**
1. **Machine Learning Systems** (🔴 Critical) ← obviously
2. **Data Pipelines & Analytics** (🔴 Critical) — data is everything
3. **Feature Engineering Infrastructure** (part of ML Systems)
4. **Real-time Analytics & Streaming** (🟡 Medium) — real-time features
5. **Performance Optimization** (🟠 High) — inference latency is critical
6. **Testing at Scale** (🟡 Medium) — ML-specific validation
7. **Observability** (existing, but model-monitored metrics)
8. **Recommendation Systems** (🟡 Medium) — common ML problem

**New additions:**
- Model training infrastructure
- Feature stores
- Model governance & versioning
- A/B testing (especially with interference)

---

### Data Engineer (L5+)

**Must-focus topics:**
1. **Data Pipelines & Analytics** (🔴 Critical) ← this is you
2. **Real-Time Analytics & Streaming** (🟡 Medium) — Kafka, Spark, Flink
3. **Reliability & Operations** (existing system-design) ✓
4. **Cost Optimization** (🔴 Critical) — cloud costs dominate
5. **Testing at Scale** (🟡 Medium) — data quality validation
6. **Machine Learning Systems** (🟡 Medium) — producing data for ML
7. **Observability** (existing, but data pipeline specific)
8. **Databases & Transactions** (existing, but OLAP focus)

**New areas:**
- Data modeling (dimensional, fact tables)
- Schema evolution
- Data governance & PII handling
- ETL/ELT patterns

---

## Company-Specific Emphasis

### Amazon

**Emphasis:**
- **Cost optimization** 🔴🔴🔴 (Leadership Principle: Frugality)
- **Operational metrics** (CloudWatch, alarms, dashboards)
- **Reliability & incidents** (they live on-call data)
- **Scaling systems** (from startup to 1B customers)

**Interview pattern:**
- Expect deep follow-ups on cost and resource efficiency
- "How would you do this more cheaply?"
- "What happens when this component fails?"

---

### Google

**Emphasis:**
- **Scale & distributed systems** (data centers, consistency)
- **Observability at scale** (metrics, logging, tracing)
- **Ambiguity & research** (design without specs)
- **Testing & reliability** (chaotic at scale)
- **Collaboration** across teams

**Interview pattern:**
- "Why?" questions (justify every choice)
- "But what if it breaks?"
- Trade-off analysis deeply probed

---

### Meta (Facebook)

**Emphasis:**
- **Real-time systems** (feeds, notifications, live video)
- **Scalability & efficiency** (billions of users, limited resources)
- **Data infrastructure** (analytics stack)
- **Product metrics** (A/B testing, conversion)
- **Resilience** (fail gracefully at scale)

**Interview pattern:**
- Real-time challenges (ordering, consistency)
- "How would you handle 10x load?"
- Product sense questions

---

### Netflix

**Emphasis:**
- **Chaos engineering** (break things in prod safely)
- **Operational excellence** (automation, monitoring)
- **Streaming architecture** (video, CDN, edge)
- **Resilience** (always available for users)
- **Cost efficiency** (AWS-native)

**Interview pattern:**
- "How would you test this for failure?"
- Detailed operational planning
- Incident scenarios

---

### Apple

**Emphasis:**
- **Security & privacy** 🔴🔴🔴 (core brand value)
- **Performance** (device-first thinking)
- **Reliability** (products don't fail)
- **User experience** (design-centric)

**Interview pattern:**
- Security threat modeling
- "How do you handle data privacy at scale?"
- End-to-end encryption concepts

---

### Microsoft

**Emphasis:**
- **Enterprise & compliance** (SOC 2, HIPAA, GDPR)
- **Multi-tenant systems** (different customers, isolated)
- **Hybrid cloud** (on-prem + cloud)
- **Integration** across products
- **Security & governance**

**Interview pattern:**
- Compliance-first design
- Multi-tenancy isolation
- Enterprise scale (thousands of customers)

---

### Stripe (or payments-focused fintech)

**Emphasis:**
- **Payment systems** 🔴🔴🔴 (obviously)
- **Ledger & reconciliation**
- **Regulatory compliance** (PCI-DSS, etc.)
- **Reliability** (money can't be lost)
- **Fraud detection**
- **Security** (payment data protection)

**Interview pattern:**
- "How do you avoid race conditions in payments?"
- Ledger design and correctness
- "What if reconciliation finds a mismatch?"

---

### Uber / Lyft (or real-time services)

**Emphasis:**
- **Real-time dispatch** (geo-spatial indexing)
- **Matching algorithms** (supply-demand)
- **Payments & multi-party** (driver, user, platform)
- **Surge pricing algorithms**
- **Resilience** (service can't go down)
- **Cost optimization** (tight margins)

**Interview pattern:**
- Geo-spatial systems design
- Real-time consistency challenges
- "How do you prevent gaming the surge pricing?"

---

### Netflix (or streaming-focused)

**Emphasis:**
- **Content delivery** (CDN, transcoding)
- **Recommendation systems**
- **Streaming reliability**
- **Content management**

**See Netflix section above.**

---

## Prep Timeline by Pace

### Aggressive (4 weeks)

**Week 1:** Cost optimization, ML systems, data pipelines, security at scale
**Week 2:** Payment systems (if applicable), microservices, incident management
**Week 3:** Performance, chaos, real-time, testing, migrations
**Week 4:** Role-specific deep dives, behavioral stories, mock interviews

### Standard (8 weeks)

**Weeks 1-2:** Cost + ML + data (critical)
**Weeks 3-4:** Microservices + security + payment
**Weeks 5-6:** Performance + chaos + real-time + testing
**Weeks 7-8:** Migrations, tracing, feature flags, company-specific deep dives

### Leisurely (12 weeks)

**Weeks 1-2:** Cost optimization deep dive with projects
**Weeks 3-4:** ML systems with implementation
**Weeks 5-6:** Data pipelines with actual Airflow/dbt
**Weeks 7-8:** Microservices, incident management, ADRs
**Weeks 9-10:** Role-specific (backend, frontend, infra)
**Weeks 11-12:** Polish, mock interviews, company-specific

---

## Weekly Study Plan Template

### Daily (1.5 hours)
- [ ] 1 concept from MISSING-SENIOR-CONCEPTS (30 min read)
- [ ] 1-2 LeetCode problems if coding round coming (30 min)
- [ ] 1 behavioral story refinement (20 min)
- [ ] Mock question on topic (10 min)

### Weekly Deep Dive (90 min)
- [ ] Technical blog post or paper on one missing concept
- [ ] Implement a small project related to concept
- [ ] Whiteboard design using that concept
- [ ] Record yourself explaining (2 min pitch)

### Monthly
- [ ] Full mock interview with friend
- [ ] Review postmortems of real incidents (public)
- [ ] Read one architecture decision record (Kubernetes KEP, etc.)

---

## Resource Links by Topic

### Cost Optimization
- AWS Compute Optimizer
- GCP Recommender
- "Finops for Engineers" (O'Reilly)
- Segment's cost analysis (open source)

### ML Systems
- Chip Huyen's "Designing Machine Learning Systems"
- Hugging Face Model Hub (examples)
- Feast (feature store)
- Seldon Core (model serving)

### Data Pipelines
- dbt documentation
- Airflow tutorials (official)
- Spark Structured Streaming
- "Fundamentals of Data Engineering"

### Security
- OWASP Top 10
- Google "BeyondCorp" whitepaper
- "Building Secure & Reliable Systems" (Google)
- Snyk security reports

### Microservices
- "Microservices Patterns" by Chris Richardson
- Uber Ringpop (distributed hash ring)
- Amazon Builders blog

### Observability
- Observability Engineering (O'Reilly) by Charity Majors
- Honeycomb.io blog
- "The Site Reliability Workbook" (Google)

### Chaos Engineering
- Gremlin (tool + tutorials)
- Netflix Chaos Monkey (OSS)
- "Chaos Engineering" (O'Reilly)

### Performance Profiling
- Julia Evans' zines (systems)
- "Brendan Gregg's Performance Tuning"
- Linux perf documentation

---

## Mock Interview Prompts by Topic

### Cost Optimization
1. "Design Netflix for 1/10th the current AWS cost. Where do you cut?"
2. "You have $100M infrastructure budget. Allocate across services."
3. "Reduce data storage costs by 50% without deleting data."

### ML Systems
1. "Design a recommendation system for TikTok videos."
2. "ML model latency P99 is 500ms, users see 100ms p50. Why? Debug."
3. "How do you detect and fix model drift in production?"

### Data Pipelines
1. "Design analytics infrastructure for real-time dashboards + historical queries."
2. "Implement exactly-once processing across 10 data centers."
3. "Schema of a fact table just changed. How do you backfill 5 years of history?"

### Security
1. "Design security architecture so one compromised service can't breach everything."
2. "Walk me through a supply chain attack on your dependencies. How do you detect it?"
3. "PII data must be compliant with GDPR. Architect the system."

### Microservices
1. "Split a monolith into microservices. What's your strategy?"
2. "Two services must maintain consistency. How do you design transactions?"
3. "Service A and Service B are in circular dependency. Untangle it."

### Observability
1. "P99 latency spiked from 50ms to 500ms. Walk me through debugging."
2. "Set up monitoring for a new feature. What do you track?"
3. "Design a distributed tracing system for 50 microservices."

### Chaos Engineering
1. "How would you test that your system survives a region outage?"
2. "Design game day exercises for your team."
3. "One dependency failing breaks 3 downstream services. Fault isolate it."

### Performance
1. "API started returning 1s p99 latency from 50ms baseline. Root cause?"
2. "Memory usage of a service is growing 1GB/day. Investigate."
3. "You have 10ms to process each request. How do you optimize?"

---

## Pre-Interview Checklist (1 week before)

- [ ] Pick 3-5 topics most relevant to role
- [ ] Deep read each topic (1 hour each)
- [ ] Implement or sketch 1 design per topic (30 min each)
- [ ] Record yourself explaining each (2 min on video)
- [ ] Review tradeoffs and failure modes aloud
- [ ] Do 3 mock interviews
- [ ] Refine 2-3 behavioral stories with metrics
- [ ] Review your own projects (constraints, metrics, what you'd redo)
- [ ] List questions for interviewer
- [ ] Get 8 hours sleep 2 nights before

---

## Day-of Interview Tips

1. **Listen carefully** — interviewers often hint at what matters
2. **Think out loud** — explain your reasoning as you design
3. **Draw diagrams** — visual > verbal for system design
4. **Ask clarifying questions** — don't assume requirements
5. **State tradeoffs early** — show you've thought about complexity
6. **Use vocabulary precisely** — "eventual consistency" not "sorta consistent"
7. **Tie to failures** — explain failure modes of your design
8. **Mention observability** — senior engineers instrument everything
9. **Discussion vs lecturing** — it's a conversation
10. **Be honest about gaps** — "I haven't done this, but here's my approach..."

---

*Last updated: May 2026*

