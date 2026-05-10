
## What is a rate limiter?
A rate limiter limits the number of requests a service can fulfill within a specific timeframe. It throttles traffic that exceeds a predefined limit.

## Need for a rate limiter 
1. **Preventing resource starvation:** Software errors or misconfigurations can trigger “friendly-fire” denial of service incidents. Rate limiters prevent these events from exhausting system resources.
2. **Managing policies and quotas:** Rate limiters ensure fair resource usage in multi-tenant environments. They enforce policies based on time duration or allocated quotas.
3. **Controlling data flow:** In systems processing large data volumes, rate limiters regulate flow to prevent overloading specific machines, helping distribute the workload evenly.
4. **Avoiding excess costs:** Rate limiting controls operational costs by preventing runaway experiments or processes. Cloud providers often use this to limit freemium tiers or cap billable usage.

## Requirements
1. **Functional requirements:**
    - **Request counting:** Limit the number of requests a client can send within a time window
    - **Customizable policies:** Ensure request limits per window are configurable
    - **Client notification:** Notify the client (via error or notification) when a threshold is crossed.
2. **Non Functional requirements:**
   - **Scalability:** The system should handle increasing traffic without performance degradation.
   - **Low latency:** The rate limiter should respond quickly to requests, ensuring minimal delay.
   - **High availability:** The system should be resilient and available even during high traffic or failures.
   - **Configurability:** The rate limiter should allow flexible configuration of limits and policies.

## Types of throttling

1. **Hard throttling:** This enforces a strict limit. Any request exceeding the threshold is discarded.
2. **Soft throttling:** This allows requests to exceed the limit by a specific percentage. For example, a limit of 500 with a 5% buffer allows 525 requests.
3. **Elastic or dynamic throttling:** Allows requests to exceed the limit if the system has free resources, without a fixed upper cap.

## Global vs. Per User Rate Limiting
1. **Global rate limiting:** This applies a single limit across all users. For example, a service might allow 1000 requests per minute regardless of the number of users.
2. **Per user rate limiting:** This applies limits on a per-user basis. For example, each user might be allowed 100 requests per minute. This approach is more granular and can prevent a single user from consuming all resources, but it requires tracking individual user activity.
3. **Hybrid approach:** Some systems use a combination of global and per-user rate limiting. For instance, there might be a global limit to protect overall system resources, while also enforcing per-user limits to ensure fair usage among users.

## Can a load balancer be used as a rate limiter?
A load balancer can perform basic rate limiting by distributing incoming requests across multiple servers and rejecting requests that exceed a certain threshold. However, it may not be sufficient for complex rate limiting needs, such as per-user limits or dynamic throttling based on system load. For more advanced rate limiting features, a dedicated rate limiter service is often necessary.