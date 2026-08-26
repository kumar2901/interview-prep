
## What is a rate limiter?
A rate limiter limits the number of requests a service can fulfill within a specific timeframe. It throttles traffic that exceeds a predefined limit.

## Need for a rate limiter 
1. **Preventing resource starvation:** Software errors or misconfigurations can trigger “friendly-fire” denial of service incidents. Rate limiters prevent these events from exhausting system resources.
2. **Managing policies and quotas:** Rate limiters ensure fair resource usage in multi-tenant environments. They enforce policies based on time duration or allocated quotas.
3. **Controlling data flow:** In systems processing large data volumes, rate limiters regulate flow to prevent overloading specific machines, helping distribute the workload evenly.
4. **Avoiding excess costs:** Rate limiting controls operational costs by preventing runaway experiments or processes. Cloud providers often use this to limit freemium tiers or cap billable usage.

## Requirements
1. **Functional requirements:**
    - **Request counting:** Limit the number of requests a client can send within a time window (identify users by id, ip, or api key)
    - **Customizable policies:** Ensure request limits per window are configurable
    - **Client notification:** Notify the client when a threshold is crossed(return proper error headers and status codes like 429 Too Many Requests)
2. **Non Functional requirements:**
   - **Scalability:**  System should handle increasing traffic without performance degradation.(scale to 1M rps)
   - **Low latency:**  Should respond quickly to requests, ensuring minimal delay.(< 10ms)
   - **High availability:** Should be resilient and available even during high traffic or failures.(availability >> consistency)
   - **Configurability:** Allow flexible configuration of limits and policies.

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


## Rate Limiting Algorithms
1. **Token bucket** - This algorithm uses a bucket that holds a certain number of tokens. Each request requires a token, and tokens are added to the bucket at a fixed rate. If the bucket is empty, requests are denied until new tokens are added.  **Most commonly used algorithm**
2. **Leaking bucket** - This algorithm also uses a bucket, but it leaks tokens at a constant rate. Requests are added to the bucket, and if the bucket overflows, requests are rejected. This smooths out bursts of traffic but can lead to higher latency during peak times. **Good for smoothing traffic**
3. **Fixed window counter** - This algorithm counts the number of requests in a fixed time window (e.g., 1 minute). If the count exceeds the limit, further requests are rejected until the window resets. This can lead to burstiness at the edges of the window. **Least accurate algorithm & Easy to implement**
4. **Sliding window log** - This algorithm maintains a log of request timestamps and counts the number of requests in a sliding time window. It provides a more accurate count but can be memory-intensive.
5. **Sliding window counter** - This algorithm is similar to the sliding window log but uses a counter instead of maintaining a log of timestamps.

## Where should we place the rate limiter?

1. **Basic Solution**: In-process rate limiter -This is implemented within the application itself. It can be simple to implement but may not scale well and can lead to performance issues under high load.
2. **Good Solution**: Dedicated Rate Limiter Service -This is an external service that handles rate limiting for multiple applications. It can be more scalable and flexible but adds complexity and potential latency.
3. **Great Solution**: API Gateway/Load Balancer -Integrate rate limiter with API Gateway/Load Balancer (e.g., AWS API Gateway, Nginx, Envoy). **Good Analogy: Bouncer at a club reject troublemaker at gate**

## How should we identify clients for rate limiting?
1. **IP Address**: This is a common method for identifying clients, but it can lead to issues with shared IP addresses (e.g., behind a NAT) and can be easily spoof
2. **API Key**: This method requires clients to include an API key in their requests. It provides better granularity but requires clients to manage their keys securely.
3. **User ID**: This method identifies clients based on their user accounts. It provides the most accurate way to enforce per-user limits but requires authentication and can be more complex to implement.

**Note**: authenticated or premium users may have higher limits than anonymous users.