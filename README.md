# interview-prep
Backend Developer interview preparation

## Coding Problems Implemented

### Arrays
- **VisaCodeSignal.java**
  - E-Scooter Distance on a Line
  - Text Justification
- **MaxProdSubarray.java**
  - Maximum product subarray in an integer array

### Sliding Window
- **LongestSubstringWithoutRepeatingCharacters.java**
  - Find the length of the longest substring without repeating characters
  - Length of longest substring with at most k distinct characters
  - Length of longest substring where every character appears at least k times
- **ContainerWithMostWater.java**
  - Find the maximum area of water that can be contained using vertical lines as sides

### Trie
- **WordWithAllPrefixes.java**
  - Longest word such that every prefix is also present in the array (Trie & non-Trie solutions)

### Sliding Window
- **LongestSubstringWithoutRepeatingCharacters.java**
  - Find the length of the longest substring without repeating characters
  - Length of longest substring with at most k distinct characters
  - Length of longest substring where every character appears at least k times

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
  - Find Lowest Common Ancestor in a binary tree (if both nodes present)
  - Right-side view of a binary tree
  - Check if two binary trees are the same
  - Invert a binary tree (mirror)
  - Pre-order print of a binary tree

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

---

### Games

#### Battleship (in `game.battleship`)
- **BattleShipGameApplication.java** - Main entry point to initialize and start the game
- **BattleShipGameService.java** - Service layer to manage game initialization, ship placement, and gameplay
- **GameManager.java** - Core game logic: manages two battlefields, turn-based play, and win detection
- **BattleField.java** - Represents a player's grid, handles ship placement, firing, and state
- **Ship.java** - Ship model with id, size, cell positions, and destroyed status
- **Coordinate.java** - Record for (x, y) coordinate representation
- **MissileTracker.java** - Tracks fired coordinates to prevent duplicate shots
- **FiringStrategy.java** - Interface for pluggable firing strategies
- **RandomFiringStrategy.java** - Random coordinate selection for AI firing

#### Tic Tac Toe (in `game.tictoctoe`)
- **TicTocToe.java** - Main entry point allowing user to choose game mode (Human or AI)
- **Game.java** - Core game loop: manages turns, input, win/draw detection
- **MinimaxAI.java** - AI opponent using Minimax algorithm for optimal move selection
- **Board.java** - 3x3 grid model with move placement, win check, and print utilities
- **Player.java** - Player model with symbol (X/O) and AI flag
- **GameMode.java** - Enum for HUMAN and AI modes

**Directory:** `src/main/java/com/kumar/interview/prep/interview_prep/dsa/`
