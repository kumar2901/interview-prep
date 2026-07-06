# Social Feed System Design

----

**Introduction**

A social feed system aggregates and displays content from friends and followed accounts in chronological or curated order.

-----

**Requirements**

**Functional Requirements:**

1. Create Posts
2. Follow people
3. View feed
4. Page through feed

*Out of scope:*
1. Like and comment on posts
2. Private or restricted visibility posts

**Non-Functional Requirements:**

1. Availability: 99.99% (Availability >> Consistency)
2. Consistency: Posts visible in < 2 minutes
3. Latency: Post and view < 1 sec
4. Scalability: 2 billion users

**Core Entities:**

1. User
2. Post
3. Follow Relationship

**API Design:**

1. create post:
```POST /posts
 Request Body:{
   "userId": "123",
   "content": "Hello World!",
   "media": ["image1.jpg", "video1.mp4"]
 }

Response Body: {
  "postId": "456",
  "timestamp": "2024-06-01T12:00:00Z"
}
```
2. follow user:
```PUT /follow/user/{followerId}/{followeeId}
Response Body: {
  "message": "User 123 is now following User 456"
}
```
3. view feed:
```GET /feed/{userId}?limit=10&cursor=<optional_base64_token>
Response Body: {
  "posts": [
    {
      "postId": "456",
      "userId": "123",
      "content": "Hello World!",
      "media": ["image1.jpg", "video1.mp4"], 
      "timestamp": "2024-06-01T12:00:00Z" 
    }
  ],
  "nextCursor": "2024-06-01T12:00:00Z_456",
  "hasMore": true
}
```

-----

**What DB Should We Use?**

1. **PostgreSQL** — users
2. **Cassandra** — posts, follows, user feed timelines
3. **Redis** — feed cache
4. **S3 + CDN** — media

| Data | Store |
|------|-------|
| Users / profiles | PostgreSQL |
| Posts | Cassandra |
| Follow graph | Cassandra |
| Feed timeline | Cassandra + Redis |
| Media | S3 + CDN |

**Feed model:** fan-out on write (normal users), fan-out on read (celebrities), hybrid.

-----

**DB Schema Design**

1. users (PostgreSQL)
   - user_id (PK)
   - username (unique)
   - email (unique)
   - password_hash
   - profile_pic_url
   - bio
   - status
   - created_at
   - updated_at

2. posts_metadata (Cassandra) — PK: `(author_id)`, CK: `created_at, post_id`
   - author_id
   - post_id
   - content
   - media_count
   - created_at
   
3. **post_media** (Cassandra) — PK: `(post_id)`, CK: `media_index`
   - media_id
   - post_id
   - media_url
   - media_type
   - width
   - height
   - created_at

4. **followers** (Cassandra) — PK: `(follower_id, followee_id)`
   - follower_id
   - followee_id
   - created_at
