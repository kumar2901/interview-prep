# Top 25 Most Frequently Asked System Design Questions (Google / Microsoft / Meta / Amazon / Uber)

## Senior Engineer (L5 / SDE3 / Staff-Equivalent) Interview Guide

---

# How Senior System Design Interviews Are Evaluated

At senior level, interviewers are NOT evaluating whether you know technologies.

They evaluate:

* scalability thinking
* distributed systems depth
* tradeoff analysis
* operational maturity
* failure handling
* API and data modeling
* evolution strategy
* prioritization
* product thinking
* cost awareness

A strong answer should evolve from:

```text
Requirements
→ Estimation
→ High-Level Design
→ Data Model
→ APIs
→ Scaling
→ Bottlenecks
→ Fault Tolerance
→ Tradeoffs
→ Operational Excellence
```

---

# Recommended Study Order (VERY IMPORTANT)

Do NOT study system design randomly.

Strong candidates learn concepts progressively.

The best preparation path is:

```text
Single Node
→ Caching
→ Load Balancing
→ Replication
→ Messaging
→ Sharding
→ Real-Time Systems
→ Distributed Coordination
→ Multi-Region Systems
→ ML/Ranking Systems
```

# Suggested Study Priority (Most Asked)

Focus on these first:

1. Social Feed
2. Chat System
3. Search/Autocomplete
4. Notification System
5. Distributed Cache
6. Payment System
7. Rate Limiter
8. Distributed Queue
9. Ride Sharing
10. Recommendation Engine

---

The table below is organized intentionally so that each design teaches concepts required for later designs.

---

# Top 25 Most Frequently Asked Senior System Design Questions

| Study Order | System Design Problem                | Why You Should Study It                   | Most Important Concepts Learned                   | Companies           | Difficulty |
| ----------- | ------------------------------------ | ----------------------------------------- | ------------------------------------------------- | ------------------- | ---------- |
| 1           | URL Shortener                        | Best beginner distributed system          | caching, DB sharding, ID generation, redirects    | Google, Amazon      | Medium     |
| 2           | Distributed Rate Limiter             | Introduces distributed coordination       | Redis, token bucket, consistency, atomic counters | Google, Uber        | Medium     |
| 3           | API Gateway                          | Foundation for microservices architecture | auth, throttling, routing, observability          | Amazon              | Medium     |
| 4           | Distributed Cache (Redis)            | Core building block used everywhere       | replication, eviction, hot keys, persistence      | Google, Amazon      | Hard       |
| 5           | Notification System                  | First event-driven architecture           | Kafka, retries, DLQ, async processing             | Meta, Amazon        | Hard       |
| 6           | Distributed Message Queue            | Foundation for streaming systems          | partitions, ordering, replay, consumer groups     | LinkedIn, Amazon    | Hard       |
| 7           | Chat System (WhatsApp/Slack)         | Best introduction to real-time systems    | WebSockets, ordering, presence, fanout            | Meta, Microsoft     | Hard       |
| 8           | Google Docs / Collaborative Editing  | Most important consistency problem        | OT, CRDT, synchronization, conflict resolution    | Google, Microsoft   | Very Hard  |
| 9           | Social Feed (Twitter/Facebook)       | Teaches large-scale fanout systems        | feed ranking, caching, celebrity problem          | Meta                | Hard       |
| 10          | Search Autocomplete                  | Excellent for ranking + caching           | trie, ranking, personalization, streaming         | Google, Microsoft   | Hard       |
| 11          | Search Engine                        | Core search infrastructure knowledge      | inverted index, relevance, indexing pipeline      | Google              | Hard       |
| 12          | Recommendation Engine                | Introduces ML system design               | embeddings, ranking, candidate generation         | Netflix, Meta       | Hard       |
| 13          | Video Streaming Platform             | Teaches CDN and media systems             | transcoding, edge caching, adaptive bitrate       | Netflix, Google     | Hard       |
| 14          | CDN                                  | Best system for edge architecture         | edge routing, invalidation, DDoS protection       | Cloudflare, Netflix | Hard       |
| 15          | File Storage (Dropbox/GDrive)        | Introduces metadata/blob separation       | chunking, sync, deduplication, versioning         | Google, Dropbox     | Hard       |
| 16          | Payment System                       | Most important reliability system         | idempotency, reconciliation, ledger design        | Stripe, Amazon      | Very Hard  |
| 17          | Ride Sharing System                  | Strong real-time geo system               | geospatial indexing, dispatching, ETA             | Uber                | Hard       |
| 18          | Real-Time Analytics                  | Best streaming architecture problem       | Flink/Kafka, aggregation, late events             | Meta, Uber          | Hard       |
| 19          | Monitoring & Alerting                | Critical operational system               | TSDB, metrics, tracing, SLOs                      | Datadog, Google     | Hard       |
| 20          | Distributed Database                 | Most important distributed systems topic  | quorum, replication, CAP theorem                  | Amazon, Google      | Very Hard  |
| 21          | Distributed Lock Service             | Deep distributed coordination problem     | Raft, Paxos, leader election                      | Google              | Very Hard  |
| 22          | Feature Flag System                  | Strong operational excellence problem     | rollout safety, targeting, consistency            | Meta, Uber          | Medium     |
| 23          | Job Scheduler                        | Good orchestration system                 | DAGs, retries, resource management                | Airbnb, Uber        | Hard       |
| 24          | Secrets Management                   | Critical infrastructure system            | encryption, KMS, secret rotation                  | Amazon, Hashicorp   | Medium     |
| 25          | Container Orchestration (Kubernetes) | One of the strongest L6 topics            | scheduling, orchestration, multi-cluster          | Google              | Very Hard  |

---

# Why This Study Order Works

The order is intentional.

Each system introduces concepts needed for future systems.

Example:

## URL Shortener

Teaches:

* caching
* replication
* sharding

which are prerequisites for:

* social feed
* search systems
* distributed DBs

---

## Chat System

Teaches:

* WebSockets
* ordering
* fanout
* real-time communication

which are required before:

* Google Docs
* ride sharing
* collaborative systems

---

## Google Docs

Teaches:

* synchronization
* distributed consistency
* CRDT/OT
* conflict resolution

which are some of the hardest distributed systems topics.

---

## Distributed Database + Lock Service

Should be studied later because they require understanding:

* replication
* consensus
* consistency
* partitions
* failover

These are advanced topics.

---

# Most Valuable Questions For Google L5/L6

If time is limited, prioritize these:

| Priority | Problem               | Why Important                 |
| -------- | --------------------- | ----------------------------- |
| 1        | Social Feed           | Fanout + ranking + caching    |
| 2        | Google Docs           | Distributed consistency       |
| 3        | Chat System           | Real-time distributed systems |
| 4        | Search Autocomplete   | Ranking + streaming + caching |
| 5        | Distributed Database  | Deep distributed systems      |
| 6        | Recommendation Engine | ML ranking systems            |
| 7        | Payment System        | Reliability + consistency     |
| 8        | Distributed Queue     | Messaging architecture        |
| 9        | CDN                   | Edge systems                  |
| 10       | Monitoring System     | Operational excellence        |

---

# Most Important Concepts Learned Per Stage

| Stage        | Topics                                            |
| ------------ | ------------------------------------------------- |
| Beginner     | caching, replication, APIs, DB basics             |
| Intermediate | messaging, queues, retries, fanout                |
| Advanced     | sharding, ranking, consistency, real-time sync    |
| Senior       | consensus, multi-region, operational excellence   |
| Staff/L6     | evolution strategy, cost optimization, ML systems |

---

# Biggest Mistake Candidates Make

Weak candidates:

* memorize architectures
* memorize diagrams

Strong candidates:

* understand tradeoffs
* identify bottlenecks
* reason about scaling
* discuss operational risks
* explain evolution path

Interviewers care far more about:

```text
WHY you chose something
```

than:

```text
WHICH technology you used
```

---

# Suggested Weekly Study Plan

## Week 1

* URL Shortener
* Rate Limiter
* API Gateway
* Cache

---

## Week 2

* Notification System
* Kafka / Queue
* Chat System

---

## Week 3

* Google Docs
* Social Feed
* Search Autocomplete

---

## Week 4

* Search Engine
* Recommendation Engine
* CDN

---

## Week 5

* Payment System
* Distributed DB
* Lock Service

---

## Week 6

* Monitoring System
* Kubernetes
* Real-Time Analytics

---

# Top 25 Most Frequently Asked Senior System Design Questions

| #  | System Design Problem             | Companies           | Difficulty | Most Important Discussion Topics    |
| -- | --------------------------------- | ------------------- | ---------- | ----------------------------------- |
| 1  | URL Shortener (TinyURL/Bitly)     | Google, Amazon      | Medium     | Base62, redirects, cache, analytics |
| 2  | Distributed Rate Limiter          | Google, Uber        | Medium     | Token bucket, Redis, consistency    |
| 3  | Notification System               | Meta, Amazon        | Hard       | Push/email/SMS, retries, queues     |
| 4  | Search Autocomplete               | Google, Microsoft   | Hard       | Trie, ranking, personalization      |
| 5  | Chat System (WhatsApp/Slack)      | Meta, Microsoft     | Hard       | WebSockets, ordering, presence      |
| 6  | Social Feed (Twitter/Facebook)    | Meta                | Hard       | Fanout, ranking, caching            |
| 7  | Video Streaming (Netflix/YouTube) | Netflix, Google     | Hard       | CDN, transcoding, adaptive bitrate  |
| 8  | File Storage (Dropbox/GDrive)     | Google, Dropbox     | Hard       | Chunking, sync, metadata            |
| 9  | Payment System                    | Stripe, Amazon      | Hard       | Idempotency, reconciliation         |
| 10 | Ride Sharing (Uber/Lyft)          | Uber                | Hard       | Geo indexing, matching              |
| 11 | Distributed Cache (Redis)         | Amazon, Google      | Hard       | Replication, eviction, persistence  |
| 12 | Distributed Message Queue         | LinkedIn, Amazon    | Hard       | Kafka concepts, ordering            |
| 13 | Search Engine                     | Google              | Hard       | Inverted index, ranking             |
| 14 | Recommendation Engine             | Netflix, Meta       | Hard       | ML ranking, embeddings              |
| 15 | API Gateway                       | Amazon              | Medium     | Routing, auth, throttling           |
| 16 | Monitoring & Alerting             | Datadog, Google     | Hard       | TSDB, metrics, SLOs                 |
| 17 | Job Scheduler                     | Airbnb, Uber        | Hard       | DAGs, retries, orchestration        |
| 18 | Real-Time Analytics               | Meta, Uber          | Hard       | Streaming, aggregation              |
| 19 | Distributed Database              | Amazon, Google      | Hard       | Sharding, quorum, consistency       |
| 20 | CDN                               | Netflix, Cloudflare | Hard       | Edge caching, invalidation          |
| 21 | Feature Flag System               | Meta, Uber          | Medium     | Rollouts, consistency               |
| 22 | Distributed Lock Service          | Google              | Hard       | Raft, leader election               |
| 23 | Container Orchestration           | Google              | Hard       | Kubernetes scheduling               |
| 24 | Secrets Management                | Hashicorp, Amazon   | Medium     | Encryption, rotation                |
| 25 | Distributed Tracing               | Google, Uber        | Hard       | Span propagation, sampling          |

---

# 1. Design URL Shortener (TinyURL)

## Core Requirements

* shorten long URLs
* fast redirects
* custom aliases
* expiration support
* analytics tracking

---

## Senior-Level Discussion Topics

### ID Generation

Options:

* Base62 encoding
* Snowflake IDs
* random hash

Tradeoffs:

* sequential vs random
* predictability
* collision probability

---

## Scaling

### Read Heavy

Redirect traffic >> writes.

Use:

* CDN
* Redis cache
* read replicas

---

## Data Model

```text
ShortURL
LongURL
CreatedAt
TTL
UserId
```

---

## Failure Scenarios

* cache miss storms
* hot URLs
* DB failure
* abuse/spam

---

## Strong Senior Signals

* multi-region redirects
* geo routing
* analytics pipeline
* abuse detection
* hot key mitigation

---

# 2. Design Distributed Rate Limiter

## Algorithms

* token bucket
* leaky bucket
* sliding window
* fixed window

Most common answer:

Token bucket.

---

## Senior-Level Topics

### Distributed Coordination

Use:

* Redis atomic counters
* Lua scripts
* sharded counters

---

## Important Tradeoffs

### Accuracy vs Performance

Global synchronization increases latency.

Eventually consistent counters may slightly over-allow.

---

## Common Follow-Ups

* per user limits
* per API limits
* hierarchical throttling
* burst handling
* Retry-After headers

---

# 3. Design Notification System

## Channels

* push
* email
* SMS
* in-app

---

## Architecture

```text
API Gateway
→ Notification Service
→ Kafka
→ Channel Workers
→ Providers (FCM/APNs/Twilio)
```

---

## Senior-Level Topics

### Reliability

* retries
* DLQ
* idempotency
* delivery guarantees

---

## Important Follow-Ups

* user preferences
* quiet hours
* GDPR deletion
* deduplication
* campaign scheduling

---

# 4. Design Search Autocomplete

## Core Components

* trie
* ranking layer
* cache
* streaming pipeline

---

## MUST DISCUSS

### Ranking

Suggestions based on:

* popularity
* recency
* personalization
* trends
* geo relevance

---

## Scaling Topics

* distributed trie shards
* top-K at trie nodes
* incremental updates
* hot prefixes

---

## Senior-Level Additions

* typo tolerance
* fuzzy search
* ML ranking
* multi-language support

---

# 5. Design Chat System (WhatsApp/Slack)

## Important Topics

* WebSockets
* online presence
* ordering
* offline delivery
* deduplication

---

## Architecture

```text
Gateway
→ Chat Service
→ Kafka
→ Delivery Service
→ WebSocket Gateway
```

---

## Senior-Level Discussion

### Message Ordering

Global ordering impossible at scale.

Use:

* per conversation ordering
* logical timestamps
* sequence IDs

---

## MUST Mention

* read receipts
* typing indicators
* push notifications
* encryption
* multi-device sync

---

# 6. Design Social Feed (Twitter/Facebook)

## Core Debate

### Fanout On Write

vs

### Fanout On Read

This is THE key discussion.

---

## Scaling Topics

* celebrity problem
* timeline cache
* ranking pipeline
* content moderation

---

## Senior-Level Topics

### Hybrid Fanout

Most real systems use hybrid strategy.

---

## MUST Discuss

* recommendation ranking
* spam filtering
* cache invalidation
* eventual consistency

---

# 7. Design Video Streaming Platform

## Core Components

* object storage
* transcoding pipeline
* CDN
* recommendation engine

---

## Important Topics

### Adaptive Bitrate Streaming

* HLS
* DASH

---

## Senior-Level Topics

* edge caching
* transcoding cost
* DRM
* QoE metrics
* upload pipeline

---

# 8. Design File Storage System

## Core Topics

* chunking
* deduplication
* metadata DB
* sync engine

---

## MUST Discuss

### Metadata vs Blob Separation

Metadata:

* SQL/NoSQL

Blobs:

* object storage

---

## Senior-Level Topics

* sync conflicts
* version history
* collaborative editing
* large file optimization

---

# 9. Design Payment Processing System

## Most Important Topic

### Idempotency

Critical for duplicate prevention.

---

## MUST Discuss

* payment state machine
* reconciliation
* fraud detection
* PCI compliance
* ledger design

---

## Strong Senior Signals

* eventual settlement
* double-entry accounting
* distributed transactions avoidance

---

# 10. Design Ride Sharing System

## Core Topics

* geospatial indexing
* nearest driver search
* ETA prediction
* surge pricing

---

## Senior-Level Topics

### Geo Indexing

Use:

* S2 geometry
* quadtrees
* geohash

---

## MUST Discuss

* driver/rider matching
* real-time tracking
* dispatch optimization

---

# 11. Design Distributed Cache

## Important Topics

* replication
* eviction policy
* persistence
* consistent hashing

---

## Senior-Level Topics

### Cache Stampede

Solutions:

* request collapsing
* stale reads
* async refresh

---

## MUST Discuss

* hot keys
* failover
* memory fragmentation

---

# 12. Design Distributed Message Queue

## Core Topics

* partitioning
* ordering guarantees
* replay
* retention

---

## Senior-Level Topics

### Exactly Once

Interviewers LOVE this topic.

Reality:

Usually:

* at least once

- idempotency

---

## MUST Discuss

* consumer groups
* rebalancing
* backpressure
* DLQ

---

# 13. Design Search Engine

## Core Topics

* crawling
* indexing
* ranking
* query parsing

---

## MUST Discuss

### Inverted Index

Most important data structure.

---

## Senior-Level Topics

* BM25 ranking
* indexing pipeline
* query optimization
* distributed shards

---

# 14. Design Recommendation Engine

## Important Topics

* collaborative filtering
* embeddings
* ranking models
* candidate generation

---

## Senior-Level Topics

### Multi-Stage Ranking

```text
Candidate Generation
→ Filtering
→ Ranking
→ Re-ranking
```

---

## MUST Discuss

* cold start problem
* diversity
* exploration vs exploitation

---

# 15. Design API Gateway

## Core Topics

* authentication
* authorization
* routing
* throttling

---

## Senior-Level Topics

* plugin architecture
* service mesh integration
* canary routing
* observability

---

# 16. Design Monitoring & Alerting System

## Core Topics

* TSDB
* metrics ingestion
* alert engine

---

## MUST Discuss

### Cardinality Explosion

This is a favorite senior-level topic.

---

## Senior-Level Topics

* p99 latency
* distributed tracing
* anomaly detection
* SLO/error budgets

---

# 17. Design Job Scheduler

## Core Topics

* DAG execution
* retries
* orchestration

---

## Senior-Level Topics

* distributed workers
* fairness
* queue prioritization
* resource isolation

---

# 18. Design Real-Time Analytics

## Core Topics

* event ingestion
* stream processing
* aggregation

---

## MUST Discuss

### Lambda vs Kappa Architecture

Very common follow-up.

---

## Senior-Level Topics

* late events
* watermarks
* exactly once semantics

---

# 19. Design Distributed Database

## Core Topics

* sharding
* replication
* quorum
* consistency

---

## MUST Discuss

### CAP Theorem

and:

### Leader-Based Replication

---

## Senior-Level Topics

* anti-entropy repair
* split brain
* failover
* multi-region replication

---

# 20. Design CDN

## Core Topics

* edge locations
* cache invalidation
* routing

---

## Senior-Level Topics

* origin shielding
* hot object replication
* DDoS mitigation
* edge compute

---

# 21. Design Feature Flag System

## Core Topics

* gradual rollout
* A/B testing
* targeting

---

## Senior-Level Topics

* edge evaluation
* consistency guarantees
* rollback safety
* governance

---

# 22. Design Distributed Lock Service

## Core Topics

* Raft/Paxos
* consensus
* leader election

---

## Senior-Level Topics

* fencing tokens
* lease expiry
* network partitions

---

# 23. Design Container Orchestration System

## Core Topics

* scheduling
* service discovery
* deployment orchestration

---

## MUST Discuss

### Kubernetes Concepts

* control plane
* scheduler
* etcd
* kubelet

---

## Senior-Level Topics

* auto scaling
* bin packing
* multi-cluster federation

---

# 24. Design Secrets Management System

## Core Topics

* encryption
* rotation
* access control

---

## Senior-Level Topics

* KMS integration
* audit logs
* secret leasing
* zero trust architecture

---

# 25. Design Distributed Tracing System

## Core Topics

* trace propagation
* spans
* sampling

---

## Senior-Level Topics

* tail-based sampling
* trace storage scaling
* correlation IDs
* observability integration

---

# Critical Gaps Most Candidates Miss (Strong L5/L6 Signals)

These topics are frequently missed even by experienced engineers.

Interviewers often use these to differentiate:

* mid-level engineers
  from
* strong senior/staff engineers.

---

# 1. Backpressure Handling

Most candidates forget this.

Interviewers LOVE asking:

```text
What happens when downstream becomes slow?
```

You MUST discuss:

* bounded queues
* rate limiting
* load shedding
* retries with jitter
* adaptive throttling
* circuit breakers

Example:

Kafka consumers slower than producers.

What happens?

Strong answer:

* lag monitoring
* autoscaling consumers
* temporary dropping
* DLQ
* replay strategy

---

# 2. Hot Partition / Hot Key Problem

Extremely important at Google/Uber scale.

Examples:

* celebrity users
* viral videos
* trending hashtags
* hot URLs
* hot prefixes

Solutions:

* replication
* key splitting
* request coalescing
* async fanout
* cache prewarming

This is one of the biggest L5 differentiators.

---

# 3. Data Lifecycle Management

Most candidates discuss writes but forget:

* archival
* deletion
* retention
* compaction
* GDPR deletion
* TTL cleanup

Strong engineers discuss:

* cold storage
* retention policies
* legal compliance
* storage cost optimization

---

# 4. Multi-Tenancy Isolation

Very common at:

* Microsoft
* Google Cloud
* AWS
* Datadog

Must discuss:

* noisy neighbor problem
* quota isolation
* tenant-aware rate limits
* tenant-aware storage
* fairness

---

# 5. Deployment Safety

Strong candidates proactively discuss:

* canary rollout
* blue-green deployment
* rollback
* schema migration safety
* feature flags

Especially important for:

* payments
* databases
* infrastructure systems

---

# 6. Data Consistency Nuance

Weak candidates say:

```text
Use eventual consistency
```

Strong candidates explain:

* WHY eventual consistency is acceptable
* WHICH operations require strong consistency
* WHERE staleness is acceptable

Example:

Autocomplete:

* eventual consistency OK

Payment ledger:

* strong consistency required

This depth matters enormously.

---

# 7. Multi-Region Tradeoffs

Most candidates simply say:

```text
Deploy multi-region
```

Weak answer.

Strong answer discusses:

* active-active vs active-passive
* replication lag
* geo routing
* regional failover
* split brain prevention
* write conflicts
* quorum latency

This is a HUGE senior signal.

---

# 8. Observability Maturity

Most candidates only say:

* logs
* metrics

Strong candidates discuss:

## Metrics

* QPS
* p99 latency
* saturation
* cache hit ratio
* replication lag

## Tracing

* distributed tracing
* correlation IDs

## Alerting

* symptom-based alerts
* SLO alerts
* burn-rate alerts

This is very important for Google/Uber interviews.

---

# 9. Cost Optimization

L6 interviewers heavily care about cost.

Strong candidates discuss:

* storage tiering
* cache hit optimization
* network egress reduction
* compression
* batching
* adaptive replication

Especially important at:

* Netflix
* Uber
* AWS

---

# 10. Evolution Strategy

This is a major staff-level signal.

Strong engineers explain:

```text
How does the system evolve over 2 years?
```

Example:

Stage 1:

* monolith

Stage 2:

* caching
* replicas

Stage 3:

* sharding
* async pipelines

Stage 4:

* multi-region
* ML ranking

This demonstrates engineering maturity.

---

# FAANG Company-Specific Interview Focus

# Google

Google heavily emphasizes:

* scalability
* distributed systems depth
* tradeoffs
* consistency
* fault tolerance
* large-scale infrastructure

Most common themes:

* autocomplete
* distributed storage
* search
* observability
* Kubernetes-like systems

Google interviewers LOVE:

* bottleneck analysis
* backpressure
* hot partitions
* operational concerns

---

# Meta

Meta heavily emphasizes:

* social systems
* feed ranking
* messaging
* real-time systems
* engagement optimization

Most common themes:

* social feed
* Instagram
* Messenger
* WhatsApp
* recommendation systems

Meta interviewers LOVE:

* fanout tradeoffs
* ranking pipelines
* caching
* real-time updates

---

# Amazon

Amazon heavily emphasizes:

* APIs
* operational excellence
* reliability
* idempotency
* microservices

Most common themes:

* payment systems
* e-commerce
* inventory
* API gateway
* order systems

Amazon interviewers LOVE:

* failure scenarios
* deployment safety
* retries
* monitoring
* operational runbooks

---

# Microsoft

Microsoft heavily emphasizes:

* cloud systems
* distributed services
* enterprise reliability
* multi-tenancy
* observability

Most common themes:

* Teams/chat
* Azure-like systems
* telemetry
* collaboration systems

Microsoft interviewers LOVE:

* service contracts
* backward compatibility
* enterprise scaling
* operational simplicity

---

# Uber

Uber heavily emphasizes:

* real-time systems
* geo systems
* dispatching
* stream processing
* reliability under spikes

Most common themes:

* ride dispatch
* ETA
* pricing
* Kafka pipelines

Uber interviewers LOVE:

* stream processing
* event-driven systems
* hot partition handling
* backpressure

---

# Netflix

Netflix heavily emphasizes:

* CDN
* recommendation systems
* streaming pipelines
* resiliency

Netflix interviewers LOVE:

* chaos engineering
* edge caching
* failover
* cost optimization

---

# Most Important Concepts To Master Across ALL Questions

## 1. Capacity Estimation

You MUST estimate:

* QPS
* storage
* bandwidth
* memory
* server count

---

## 2. Scaling Bottlenecks

Always identify:

* hot partitions
* cache bottlenecks
* DB bottlenecks
* network bottlenecks

---

## 3. Reliability

Always discuss:

* replication
* retries
* failover
* circuit breakers
* deployment safety

---

## 4. Consistency Tradeoffs

Strong candidates explicitly discuss:

* strong consistency
* eventual consistency
* quorum models

---

## 5. Observability

Discuss:

* metrics
* logs
* traces
* alerts
* dashboards
* SLOs

---

## 6. Cost Awareness

Senior engineers discuss:

* storage cost
* network cost
* compute cost
* caching efficiency

---

## 7. Operational Excellence

Mention:

* blue-green deployment
* canary rollout
* rollback
* chaos testing
* runbooks

---

# Most Common Senior-Level Follow-Up Questions

Interviewers almost always ask:

1. What breaks at 10x scale?
2. How do you shard this?
3. What happens during region failure?
4. How do you avoid hot partitions?
5. What are the consistency guarantees?
6. How do you monitor the system?
7. How do you deploy safely?
8. What are the biggest operational risks?
9. How would you reduce cost?
10. Which metrics would you track?

---

# Final Preparation Strategy

For EACH system:

## Step 1

Clarify requirements.

---

## Step 2

Estimate scale.

---

## Step 3

Draw high-level architecture.

---

## Step 4

Deep dive into:

* DB
* cache
* messaging
* APIs
* scaling

---

## Step 5

Discuss:

* bottlenecks
* failures
* tradeoffs
* operational concerns

---

# Final Interview Advice

Senior system design interviews are NOT about perfect architecture.

They are about:

* structured thinking
* prioritization
* identifying bottlenecks early
* reasoning about tradeoffs
* demonstrating operational maturity

Strong candidates proactively discuss:

* failure handling
* scaling limitations
* deployment risks
* observability
* cost

without interviewer prompting.

That is usually the difference between:

* mid-level engineer
  and
* strong senior/staff signal.

---