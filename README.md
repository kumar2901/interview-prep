# interview-prep
Backend Developer interview preparation

## Coding Problems Implemented

### Arrays
- **VisaCodeSignal.java**
  - E-Scooter Distance on a Line
  - Text Justification
- **MaxProdSubarray.java**
  - Maximum product subarray

### Intervals
- **IntervalsBasics.java**
  - Merge overlapping intervals
  - Insert interval into a set
  - Print utility

### Recursion and Backtracking
- **NQueens.java**
  - Place n queens so that no two attack each other
- **NKnights.java**
  - Place N knights so that no two attack each other
- **RecursionBasics.java**
  - Factorial calculation
  - Sum of digits
  - Reverse number
  - Palindrome check
  - Letter combinations (phone keypad)
  - Ways to roll dice for a target
  - String/array permutations
  - Reverse string (recursive)
- **Patterns.java**
  - Print triangle pattern
  - Print pyramid pattern
- **Sorting.java**
  - Recursive bubble sort
  - Selection sort
  - Merge sort
- **BinarySearch.java**
  - Rotated sorted array search (with duplicates)
- **SubsetsAndSubsequence.java**
  - Subsets
  - Subsets with/without duplicates
  - String subsequences
  - Subset sum
  - Combination sum (several variants)
  - Non-decreasing subsequences

### Binary Trees
- **recursion/BinaryTree.java**
  - Find Lowest Common Ancestor in a binary tree (if both nodes present)
- **Tree/binary/BinaryTree.java**
  - Right-side view of a binary tree
  - Check if two binary trees are the same
  - Invert a binary tree (mirror)
  - Pre-order print of a binary tree
- **Tree/binary/BinaryTreeIntermediate.java**
  - Find Lowest Common Ancestor (LCA) only if both nodes exist in the tree

### Two Pointer / Sliding Window
- **LongestSubstringWithoutRepeatingCharacters.java**
  - Standard longest substring without repeating characters
  - Longest substring with at most k distinct characters
  - Longest substring where every character appears at least k times
- **ContainerWithMostWater.java**
  - Container with most water (two pointer approach)

### Dynamic Programming
- **WordBreak.java**
  - Determine if a string can be segmented into dictionary words

### Graph Algorithms
- **DijkstraAlgoExamples.java**
  - Shortest path/network delay (Dijkstra's algorithm)
- **GraphExamples.java**
  - Valid tree check in undirected graphs (DFS for cycles + connectivity)
  - Number of islands (DFS)
- **TopologicalSortExamples.java**
  - Kahn's Algorithm for topological sorting of a directed acyclic graph (DAG)
  - Cycle detection in a directed graph using Kahn's Algorithm (Course Schedule I)
  - Topological sort using Kahn's Algorithm (Course Schedule II)

### Trie (Prefix Tree)
- **Trie.java** & **TrieNode.java**
  - Insert word into trie
  - Search for complete word
  - Check if prefix exists (startsWith)
- **TrieWithCount.java** (LeetCode 1804)
  - Insert word with duplicate support
  - Count exact word occurrences (countWordsEqualTo)
  - Count words starting with prefix (countWordsStartingWith)
  - Erase one occurrence of a word
  - Tracks prefix counts and word counts using prefixCount and wordCount fields
- **WordWithAllPrefixes.java**
  - Find longest word where all prefixes exist in the array
  - Two approaches: Trie with DFS and HashSet-based solution
  - Returns lexicographically smallest word if multiple have same length
- **WordSuggestions.java** (LeetCode 1268 - Search Suggestions System)
  - **SearchSuggestions.java**: Classic approach using DFS for on-the-fly suggestions
  - **SearchSuggestionsV2.java**: Optimized approach with precomputed suggestions at each node
  - Returns top 3 lexicographically sorted products matching each prefix of search word

---

## Game Projects

### Tic-Tac-Toe with AI
- **TicTocToe.java**
  - Main entry point with mode selection (Human vs Human or Human vs AI)
- **Game.java**
  - Game flow and logic
  - Player turn management
  - Win/draw detection
- **MinimaxAI.java**
  - AI player implementation using Minimax algorithm
  - Optimal move calculation for unbeatable AI
- **model/Board.java**
  - 3x3 game board representation
  - Move placement and validation
  - Win condition checking
- **model/Player.java**
  - Player representation with symbol (X/O)
  - AI flag for distinguishing human vs AI players
- **model/GameMode.java**
  - Game mode enumeration (HUMAN vs AI)

---
**DSA Directory:** `src/main/java/com/kumar/interview/prep/interview_prep/dsa/`  
**Game Directory:** `src/main/java/com/kumar/interview/prep/game/`
