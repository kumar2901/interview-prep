# Alert And Monitoring System Design

---


## Functional Requirement

1. Collect variety of metrics
   1. CPU Usage
   2. Memory Usage
   3. Request Count and time
   4. Application error message like 4XX,5XX errors
2. Send alert for to various device
   3. Email
   4. Phone
   5. PagerDuty
   6. Webhooks
3. Support metrics visualization
4. Data retention policy
   5. Raw data for 7days
   6. 1 min resolution for 30 days
   7. 1 hour resolution for 1 year

---

## Application Qualities/ Non-Functional Requirement

1. Low latency 
   2. Metrics ingestion<300ms
   3. metrics query<300ms
   4. Alert evaluation  99% within 30 seconds
2. Scalability → It should be able to support 100M DAU
3. Availability → Should be highly available
4. Consistency -> 
   5. Alert highly consistency → No Alert should be missed or lost
   6. Eventual consistency on memory and CPU usage


### Question: What will be resolution of metrics and data retention policy?
Ans: raw data 7 days
1 min resolution for 30days
1hr resolution for 1 year

---

## Database Selection

### Relational DB

1. Heavy write overhead: Metrics generations produces high frequency write operations
2. RDBMS spend excessive CPU and I/O resources updating B-Trees, Managing Transaction and writing Write Ahead Logs
3. Expensive Retention and Deletion in RDBMS
4. Lack of built-in Downsampling: RDBMS lacks native feature to aggregate high resolution data like 10 second interval or 1 hrs interval

### NOSQL
1. High memory consumption for indexing
2. Inefficient time range queries
3. poor compression rates

### Why Time Series DB?
1. Time series DB like Prometheus or InfluxDB uses append only  storage engine optimize for high write throughput, sequential time range reads
2. Tag based indexing
3. Fast downsampling

### Final choice: Use Time Series to store metrices

---
## Data Model

### Metrics data model
```text
<metric_name>{<label_1>="<value_1>", <label_2>="<value_2>"} <value> <timestamp>

# Examples:
http_requests_total{method="POST", handler="/login", status="500"} 1028 1710283920000
system_cpu_idle{host="server-01", region="us-east-1"} 42.5 1710283920000
```

### Alerting Data model

```yaml
groups:
   - name: infrastructure_alerts
     rules:
        - alert: HighCpuUsage
          expr: avg(rate(node_cpu_seconds_total{mode="idle"}[5m])) BY (instance) < 0.15
          for: 10m
          labels:
             severity: critical
             team: devops
          annotations:
             summary: "Instance {{ $labels.instance }} has high CPU usage"
             description: "CPU idle time is below 15% for more than 10 minutes. Current value: {{ $value }}%"
             runbook_url: "https://internal.wiki/ops/high-cpu"
```

----
## Types Of Metrics Data Collection 

### Pull based model
1. A Dedicates metric Collectors pull metrics from source periodically via pre-defined REST APIs
2. Metrics Collector will query Service Discovery to get the complete list of service endpoints to pull data from
3. Metrics collector Uses Consistent Hash ring to decide which set of server will fetch data from source
4. Example: Prometheus used Pull based model


### Push Based Model

1. A metrics collection worker or agent is installed on every server being monitored
2. It collects metrics and pushes those to metrics Collector service periodically
3. To prevent the metrics collector from falling behind in push model . it should in auto scaling cluster with load balancer to balance load


### Note → Scalable monitoring systems often use a hybrid of push and pull data collection methods.

---

