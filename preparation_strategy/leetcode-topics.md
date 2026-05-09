# Topic-wise LeetCode problems

Solve in this general order: arrays/hash maps → two pointers/sliding window → stacks/queues → linked lists → trees/graphs → heaps → binary search → recursion/backtracking → DP → greedy → union-find → tries → bit manipulation → math.

For each topic: learn the template, do 2–3 easies, 5–8 mediums, **3–5 hards** (or medium-hard), then one timed revisit without hints.

**Difficulty** uses LeetCode labels (`E` / `M` / `H`). Focus extra time on **H** rows before senior-panel style loops.

---

## Arrays & Hash maps

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Two Sum | 1 | E | Hash map baseline |
| Group Anagrams | 49 | M | Frequency / sorting tradeoff |
| Longest Consecutive Sequence | 128 | M | Hash set + streak |
| Product of Array Except Self | 238 | M | Prefix/suffix |
| Subarray Sum Equals K | 560 | M | Prefix sum + map |
| Trapping Rain Water | 42 | H | Two pointers / monotonic stack |
| First Missing Positive | 41 | H | In-place indexing / pigeonhole |
| Sliding Window Median | 480 | H | Two heaps / lazy deletion |
| Minimum Number of Refueling Stops | 871 | H | Greedy + heap (trip planning) |

## Two pointers & Sliding window

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Container With Most Water | 11 | M | Two pointers |
| 3Sum | 15 | M | Sort + avoid duplicates |
| Longest Substring Without Repeating Characters | 3 | M | Window + map |
| Minimum Window Substring | 76 | H | Sliding window template |
| Sliding Window Maximum | 239 | H | Monotonic deque |
| Substring with Concatenation of All Words | 30 | H | Fixed window + frequency map |
| Longest Repeating Character Replacement | 424 | M | Window; BS on k variant often paired |
| Trapping Rain Water II | 407 | H | Multi-source BFS + heap (2D “water”) |
| Max Sum of Rectangle No Larger Than K | 363 | H | Compress 2D → 1D + prefix + ordered set |

## Stack & Queue

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Valid Parentheses | 20 | E | Classic stack |
| Implement Queue using Stacks | 232 | E | Amortized analysis |
| Evaluate Reverse Polish Notation | 150 | M | Stack evaluation |
| Daily Temperatures | 739 | M | Monotonic stack |
| Largest Rectangle in Histogram | 84 | H | Monotonic stack |
| Basic Calculator | 224 | H | Stack + sign/number |
| Basic Calculator II | 227 | M | Stack, no parens |
| Longest Valid Parentheses | 32 | H | Stack or two-pointer |
| Remove Duplicate Letters | 316 | M | Monotonic stack + greedy |
| Parse Lisp Expression | 736 | H | Recursive descent / stack |

## Linked List

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Reverse Linked List | 206 | E | Pointer manipulation |
| Merge Two Sorted Lists | 21 | E | Dummy node |
| Linked List Cycle II | 142 | M | Floyd’s cycle |
| Copy List with Random Pointer | 138 | M | Hash map or interleaving |
| Merge k Sorted Lists | 23 | H | Heap / divide & conquer |
| Reverse Nodes in k-Group | 25 | H | Iterative reversal blocks |
| Merge Two Sorted Lists (recursion depth) → also **Sort List** | 148 | M | Merge sort on linked list |
| Reverse Linked List II | 92 | M | Subrange reversal |
| Design Skiplist | 1206 | H | Probabilistic levels / comparison to balanced tree |
| LRU Cache | 146 | M | DDL + hash map (design on list nodes) |

## Trees & BST

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Maximum Depth of Binary Tree | 104 | E | DFS/BFS |
| Same Tree | 100 | E | Recursion shape |
| Lowest Common Ancestor of BST | 235 | M | BST property |
| Kth Smallest Element in BST | 230 | M | Inorder |
| Binary Tree Maximum Path Sum | 124 | H | DFS global max |
| Serialize and Deserialize Binary Tree | 297 | H | Design / traversal codes |
| Diameter of Binary Tree | 543 | M | Height helper (path sum warm-up) |
| Vertical Order Traversal of a Binary Tree | 987 | H | BFS + column index + tie-break |
| Recover Binary Search Tree | 99 | M | Two wrong nodes / inorder |
| Binary Tree Cameras | 968 | H | Tree DP / coverage greedy |
| Number of Ways to Rearrange Sticks With K Sticks Visible | 1866 | H | DP / visible-permutation |

## Graphs

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Number of Islands | 200 | M | Grid DFS/BFS |
| Course Schedule | 207 | M | Topo sort / cycle |
| Clone Graph | 133 | M | BFS/DFS + map |
| Network Delay Time | 743 | M | Dijkstra |
| Word Ladder | 127 | H | BFS shortest path |
| Alien Dictionary | 269 | H | Topo on characters (LC Premium for some; same idea as “dict of deps”) |
| Course Schedule IV | 1462 | M | Reachability / transitive closure |
| Cheapest Flights Within K Stops | 787 | M | BF / limited relax |
| Sliding Puzzle | 773 | H | BFS + state hashing |
| Reconstruct Itinerary | 332 | H | Hierholzer Euler path |
| Longest Increasing Path in a Matrix | 329 | H | DFS + memo |
| Cut Off Trees for Golf Event | 675 | H | Multi-target BFS + sort |

## Heap / Priority queue

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Kth Largest Element in Array | 215 | M | Heap size k |
| Top K Frequent Elements | 347 | M | Bucket / heap |
| Merge k Sorted Lists | 23 | H | Heap / D&C |
| Find Median from Data Stream | 295 | H | Two heaps |
| Sliding Window Median | 480 | H | Dual heaps |
| Smallest Range Covering Elements from K Lists | 632 | H | k pointers + heap |
| Trapping Rain Water II | 407 | H | Heap boundary fill |
| Process Tasks Using Servers | 1882 | H | Two heaps time/index |
| Maximum Performance of a Team | 1383 | H | Sort + heap cap |
| Swim in Rising Water | 778 | H | Heap Dijkstra on grid |

## Binary search

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Binary Search | 704 | E | Boundaries |
| Search in Rotated Sorted Array | 33 | M | Split logic |
| Find Minimum in Rotated Sorted Array | 153 | M | Compare mid |
| Median of Two Sorted Arrays | 4 | H | Partition |
| Split Array Largest Sum | 410 | H | Answer monotonic + greedy check |
| Koko Eating Bananas | 875 | M | Min-max binary search |
| Minimum Speed to Arrive on Time | 1870 | M | Fractional hours BS |
| Find in Mountain Array | 1095 | H | Interactive / peak + BS |
| Count of Smaller Numbers After Self | 315 | H | Merge sort / BIT |
| Aggressive Cows | (Spoj style) → **Max Distance to Closest Person** | 849 | M | BS on answer pattern |

## Backtracking

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Combination Sum | 39 | M | Pruning |
| Permutations | 46 | M | Swap / used[] |
| Word Search | 79 | M | Grid backtracking |
| N-Queens | 51 | H | Constraints |
| N-Queens II | 52 | H | Count only |
| Unique Paths III | 980 | H | Hamiltonian-style grid |
| Word Search II | 212 | H | Trie + backtrack |
| Remove Invalid Parentheses | 301 | H | BFS or backtrack pruning |
| Palindrome Partitioning II | 132 | H | DP + backtrack hybrid |
| Matchsticks to Square | 473 | H | Bitmask DFS / subset sum |

## Dynamic programming

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Climbing Stairs | 70 | E | 1D DP |
| Coin Change | 322 | M | Unbounded |
| Unique Paths | 62 | M | Grid DP |
| Longest Increasing Subsequence | 300 | M | DP + patience sort |
| Word Break | 139 | M | Hash + DP |
| Edit Distance | 72 | H | 2D classic |
| Burst Balloons | 312 | H | Interval DP |
| Regular Expression Matching | 10 | H | 2D string DP |
| Wildcard Matching | 44 | H | String DP variants |
| Longest Common Subsequence | 1143 | M | Classic 2D |
| Distinct Subsequences | 115 | H | Subsequence DP |
| Best Time to Buy and Sell Stock IV | 188 | H | txn cap DP |
| Cherry Pickup | 741 | H | Grid two robots |
| Longest Increasing Path in a Matrix | 329 | H | Memorized DFS |

## Greedy & intervals

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Jump Game | 55 | M | Greedy reach |
| Jump Game II | 45 | M | BFS greedy layers |
| Merge Intervals | 56 | M | Sort + sweep |
| Meeting Rooms II | 253 | M | Sweep heap (premium variant: **1353 Maximum Number of Events That Can Be Attended II** harder) |
| Non-overlapping Intervals | 435 | M | Greedy end time |
| Candy | 135 | H | Greedy passes |
| Create Maximum Number | 321 | H | Merge digit stacks greedy |
| Remove Duplicate Letters | 316 | M | Monotonic + greedy stack |
| Valid Permutations for DI Sequence | 903 | H | Interval DP disguised greedy |
| Maximum Profit in Job Scheduling | 1235 | H | Sort + weighted interval DP |

## Union-Find

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Number of Connected Components | 547 | M | DSU |
| Redundant Connection | 684 | M | Cycle |
| Satisfiability of Equality Equations | 990 | M | Typed DSU |
| Number of Islands II | 305 | H | DSU incremental land |
| Last Day Where You Can Still Cross | 1970 | H | Reverse union / binary search |
| Number of Operations to Make Network Connected | 1319 | M | DSU spanning |
| Smallest String With Swaps | 1202 | H | Connected components lexical |
| Largest Component Size by Common Factor | 952 | H | Factor union trick |
| Regions Cut By Slashes | 959 | H | DSU flatten grid |

## Trie

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Implement Trie (Prefix Tree) | 208 | M | Basics |
| Design Add and Search Words Data Structure | 211 | M | Trie + wildcard |
| Word Search II | 212 | H | Trie + DFS pruning |
| Maximum XOR of Two Numbers in Array | 421 | M | Trie on bits |
| Palindrome Pairs | 336 | H | Trie + reversal |
| Prefix and Suffix Search | 745 | H | Trie of suffix+word |
| Stream of Characters | 1032 | H | Trie + stream matching |

## Bit manipulation & misc

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Number of 1 Bits | 191 | E | Brian Kernighan |
| Reverse Bits | 190 | E | Swap halves |
| Single Number | 136 | E | XOR |
| Maximum XOR of Two Numbers in Array | 421 | M | Trie / bit DP |
| Counting Bits | 338 | E | DP popcount |
| Minimum Flips to Make a OR b Equal to c | 1318 | M | Bits |
| Shortest Path Visiting All Nodes | 847 | H | bitmask BFS |

## Graph — more Hard practice (DAG / probability)

| Problem | ID | D | Why |
|---------|-----|---|-----|
| Path With Minimum Effort | 1631 | M | BS on answer + greedy reachability |
| Path With Maximum Probability | 1514 | M | Dijkstra with log-probability edge |
| Parallel Courses III | 2050 | H | Topo order + longest path on DAG |
| Frog Position After T Seconds | 1377 | H | Tree + probability DP |

(Optional tree-query drills: **1483** Kth ancestor, **834** sum of distances.)

## High-frequency “design on whiteboard” (Hard-adjacent)

| Problem | ID | D | Why |
|---------|-----|---|-----|
| LRU Cache | 146 | M | Eviction policy |
| LFU Cache | 460 | H | Frequency list + TTL nuance |
| All One Data Structure | 432 | H | Incremental min/max keyed buckets |
| Design Search Autocomplete System | 642 | H | Trie + hot ranking |
| Design In-Memory File System | 588 | H | Trie / tree of nodes |
| Design Twitter | 355 | M | Merge-k feeds |
| Snapshot Array | 1146 | M | Sparse history per index |

## Practice discipline

- Time-box: **E** ~15 min, **M** ~25–35 min, **H** ~40–50 min before peeking.
- Rotate **mixed Hard** sheets: 2 Hard + 1 Medium in 75–90 minutes.
- After solving: write complexity, one alternate approach, and one variant you’d expect in follow-up.
- Weekly: one timed contest (LeetCode / Codeforces) or company-tagged Hard list—but only after fundamentals are solid.
