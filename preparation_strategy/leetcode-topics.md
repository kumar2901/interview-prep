# Topic-wise LeetCode problems

Solve in this general order: arrays/hash maps → two pointers/sliding window → stacks/queues → linked lists → trees/graphs → heaps → binary search → recursion/backtracking → DP → greedy → union-find → tries → bit manipulation → math.

For each topic: learn the template, do 2–3 easies, 5–8 mediums, 2–4 hards, then one timed revisit without hints.

---

## Arrays & Hash maps

| Problem | ID | Why |
|---------|-----|-----|
| Two Sum | 1 | Hash map baseline |
| Group Anagrams | 49 | Frequency / sorting tradeoff |
| Longest Consecutive Sequence | 128 | Hash set + streak |
| Product of Array Except Self | 238 | Prefix/suffix |
| Subarray Sum Equals K | 560 | Prefix sum + map |
| Trapping Rain Water | 42 | Two pointers / stack |

## Two pointers & Sliding window

| Problem | ID | Why |
|---------|-----|-----|
| Container With Most Water | 11 | Two pointers |
| 3Sum | 15 | Sort + avoid duplicates |
| Minimum Window Substring | 76 | Sliding window template |
| Longest Substring Without Repeating Characters | 3 | Window + map |
| Sliding Window Maximum | 239 | Monotonic deque |

## Stack & Queue

| Problem | ID | Why |
|---------|-----|-----|
| Valid Parentheses | 20 | Classic stack |
| Daily Temperatures | 739 | Monotonic stack |
| Largest Rectangle in Histogram | 84 | Stack → hard but common |
| Evaluate Reverse Polish Notation | 150 | Stack evaluation |
| Implement Queue using Stacks | 232 | Amortized analysis |

## Linked List

| Problem | ID | Why |
|---------|-----|-----|
| Reverse Linked List | 206 | Pointer manipulation |
| Merge Two Sorted Lists | 21 | Dummy node |
| Linked List Cycle II | 142 | Floyd’s cycle |
| Merge k Sorted Lists | 23 | Heap / divide & conquer |
| Copy List with Random Pointer | 138 | Hash map or interleaving |

## Trees & BST

| Problem | ID | Why |
|---------|-----|-----|
| Maximum Depth of Binary Tree | 104 | DFS/BFS |
| Same Tree | 100 | Recursion shape |
| Lowest Common Ancestor of BST | 235 | BST property |
| Binary Tree Maximum Path Sum | 124 | DFS return value |
| Serialize and Deserialize Binary Tree | 297 | Design on tree |
| Kth Smallest Element in BST | 230 | Inorder |

## Graphs

| Problem | ID | Why |
|---------|-----|-----|
| Number of Islands | 200 | Grid DFS/BFS |
| Course Schedule | 207 | Topo sort / cycle |
| Clone Graph | 133 | BFS/DFS + map |
| Word Ladder | 127 | BFS shortest path |
| Network Delay Time | 743 | Dijkstra |

## Heap / Priority queue

| Problem | ID | Why |
|---------|-----|-----|
| Kth Largest Element in Array | 215 | Heap size k |
| Top K Frequent Elements | 347 | Bucket / heap |
| Find Median from Data Stream | 295 | Two heaps |
| Merge k Sorted Lists | 23 | Heap |

## Binary search

| Problem | ID | Why |
|---------|-----|-----|
| Binary Search | 704 | Boundary conditions |
| Search in Rotated Sorted Array | 33 | Split logic |
| Find Minimum in Rotated Sorted Array | 153 | Compare mid |
| Median of Two Sorted Arrays | 4 | Partition (hard) |

## Backtracking

| Problem | ID | Why |
|---------|-----|-----|
| Permutations | 46 | Swap / used[] |
| Combination Sum | 39 | Pruning |
| Word Search | 79 | Grid backtracking |
| N-Queens | 51 | Constraint propagation |

## Dynamic programming

| Problem | ID | Why |
|---------|-----|-----|
| Climbing Stairs | 70 | 1D DP |
| Coin Change | 322 | Unbounded knapsack style |
| Longest Increasing Subsequence | 300 | DP + binary search variant |
| Unique Paths | 62 | Grid DP |
| Edit Distance | 72 | 2D classic |
| Word Break | 139 | Hash set + DP |
| Burst Balloons | 312 | Interval DP (hard) |

## Greedy & intervals

| Problem | ID | Why |
|---------|-----|-----|
| Jump Game | 55 | Greedy reach |
| Merge Intervals | 56 | Sort + sweep |
| Meeting Rooms II | 253 | Sweep line / heaps |
| Non-overlapping Intervals | 435 | Greedy end time |

## Union-Find

| Problem | ID | Why |
|---------|-----|-----|
| Number of Connected Components | 547 | DSU |
| Redundant Connection | 684 | Cycle detection |
| Satisfiability of Equality Equations | 990 | DSU with types |

## Trie

| Problem | ID | Why |
|---------|-----|-----|
| Implement Trie | 208 | Insert/search |
| Word Search II | 212 | Trie + DFS pruning |

## Bit manipulation & misc

| Problem | ID | Why |
|---------|-----|-----|
| Single Number | 136 | XOR |
| Number of 1 Bits | 191 | Brian Kernighan |
| Reverse Bits | 190 | Bit shifts |

## High-frequency “company-style” extras

| Problem | ID | Why |
|---------|-----|-----|
| LRU Cache | 146 | Linked hash map design |
| Design Twitter | 355 | Feed / merge K streams |
| Serialize Deserialize BST | 449 | Compact encoding |

## Senior supplement problems

| Problem | ID | Why |
|---------|-----|-----|
| Range Sum Query – Mutable | 307 | Fenwick / segment tree template |
| Count of Smaller Numbers After Self | 315 | Merge sort / Fenwick inversion |
| Alien Dictionary | 269 | Topo sort + cycle detection |
| Sequence Reconstruction | 444 | Topo uniqueness check |
| Design Hit Counter | 362 | Buckets / sliding window (premium tag varies—practice pattern) |
| Insert Delete GetRandom O(1) | 380 | Hash map + dynamic array swap-remove |
| Design Search Autocomplete System | 642 | Trie + ranking / hot cache (premium pattern—substitute with 208 + discussion) |
| Course Schedule II | 210 | Topo order + cycle (follow-up to 207) |

If premium-locked, replicate patterns with **segment tree** tutorials + **Course Schedule II** (210) for topo variants.

---

## Practice discipline

- Time-box: easy 15 min, medium 25–35 min, hard 40–50 min before peeking.
- After solving: write complexity, one alternate approach, and one variant you’d expect in follow-up.
- Weekly: 1 timed contest or mixed sheet (5 problems in 90 min).
