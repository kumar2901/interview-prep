
## What is a rate limiter?
A rate limiter limits the number of requests a service can fulfill within a specific timeframe. It throttles traffic that exceeds a predefined limit.

## Need for a rate limiter 
1. **Preventing resource starvation:** Software errors or misconfigurations can trigger “friendly-fire” denial of service incidents. Rate limiters prevent these events from exhausting system resources.
2. **Managing policies and quotas:** Rate limiters ensure fair resource usage in multi-tenant environments. They enforce policies based on time duration or allocated quotas.
3. **Controlling data flow:** In systems processing large data volumes, rate limiters regulate flow to prevent overloading specific machines, helping distribute the workload evenly.
4. **Avoiding excess costs:** Rate limiting controls operational costs by preventing runaway experiments or processes. Cloud providers often use this to limit freemium tiers or cap billable usage.