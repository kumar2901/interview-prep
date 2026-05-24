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

These appear extremely frequently across:

* Google
* Meta
* Amazon
* Uber
* Microsoft
* LinkedIn
* Airbnb
* Stripe
* Netflix

---

Updated for 2026 Senior Engineer Interviews (Google L5/L6, Microsoft Senior, Meta E5/E6, Amazon SDE3/Principal Prep)
