
## Introduction
A Typeahead suggestion (or autocomplete) system provides real-time query suggestions as users type in a search box. 
The system generates ranked suggestions based on search history, current context, and trending queries.
This is also called as autocomplete or autosuggest system or top-k query suggestion system.

**Common applications include:**
1. Search engines (e.g., Google, Bing)
2. E-commerce websites (product autocomplete)
3. Text and code editors


** Followup Questions:**
1. Is the matching only supported at the beginning of the query, or can it be anywhere in the query? 
Ans: The matching can be supported anywhere in the query, not just at the beginning.
2. how many suggestions should the system return for each query?
Ans: The system should return the top-k suggestions for each query, where k is a configurable.or top 5-10 suggestions.
3. Should the system support personalized suggestions based on user history?
Ans: Yes, the system should support personalized suggestions based on user history to enhance relevance.
4. Does the system support spelling correction or fuzzy matching for misspelled queries?
Ans: Yes, the system should support spelling correction and fuzzy matching to handle misspelled queries and improve user experience.
5. Should the system consider trending queries or real-time data for generating suggestions?
Ans: Yes, the system should consider trending queries and real-time data to provide up-to-date
6. How many DAU (Daily Active Users) does the system need to support?
Ans: 100 million DAU (Daily Active Users) making 10 search queries per day on average.
7. Do we need to support multiple languages for suggestions?
Ans: English only for now.

** Requirements:**

** Functional Requirements:**
1. The system suggest top k queries based on the user input in real-time.
2. The system should support personalized suggestions based on user history.
3. Search result should be ranked based on relevance, popularity, and recency.

** Non-Functional Requirements:**
1. The system should have low latency (e.g., < 100ms) for generating
2. High availability and fault tolerance to ensure continuous service. 99.99% uptime.
3. Scalability to handle increasing user traffic and data volume. 100 million queries per day.
4. Eventual consistency (few minutes delay for new terms)


**Back of the Envelope Calculations:**
1. **QPS (Queries Per Second)** = (100 million DAU * 10 queries per day) / (24 hours * 3600 seconds) ≈ 1000m/(100k) ≈ 10k QPS. Peak QPS =2* 10k = 20k QPS.
2. **Storage**: Assuming an average of 20 bytes per query and 1000 million queries per day, the daily storage requirement would be:
   - Daily Storage = 20 bytes/query * 1000 million queries = 20 GB/day.
   - Monthly Storage = 20 GB/day * 30 days = 600 GB/month.
   - Yearly Storage = 600 GB/month * 12 months = 7.2 TB/year
3. **Server requirements**:   Assuming 1 server can handle 1000 QPS, so we need at least 20 servers to handle the peak load of 20k QPS. (Medium Server)


**API Design:**
1. **Get Suggestions API**:
    - Endpoint: `GET /v1/suggestions`
    - Query Parameters:
      - `prefix`: The partial query input by the user (required)
      - `user_id`: The ID of the user for personalized suggestions (optional)
      - `limit`: The number of suggestions to return (optional, default is 10)
    - Response:
      ```json
      {
         "suggestions": [
            {
              "query": "example query 1",
              "relevance_score": 0.9,
              "popularity_score": 0.8,
              "recency_score": 0.7
            },
            {
              "query": "example query 2",
              "relevance_score": 0.85,
              "popularity_score": 0.75,
              "recency_score": 0.65
            }
         ]
      }
      ```
2. **Record Query API**:
    - Endpoint: `POST /v1/search`
      - Request Body:
        ```json
        {
           "user_id": "12345",
           "query": "how to make pizza",
           "timestamp": "2024-06-01T12:00:00Z"
        }
        ```

**Data Model:**
**DB Schema:**
1. **query_stats**: { query, global_count, last_updated, language }
2. **prefix_topk**: { prefix → sorted list of (query, score) }  // materialized
3. **user_history**: { user_id → recent queries / weights }
4. **trie_snapshot**: { version, blob_url, built_at }
