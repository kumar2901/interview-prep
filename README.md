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
- **Trie.java**
  - Basic Trie (Prefix Tree) implementation
  - insert(): adds word to trie
  - search(): checks if exact word exists
  - startsWith(): checks if any word starts with given prefix
- **TrieNode.java**
  - Node class with children array (26 letters), isEndOfWord flag, wordCount, prefixCount
- **TrieWithCount.java** (LeetCode 1804 - Implement Trie II)
  - insert(): adds word with count tracking
  - countWordsEqualTo(): returns count of exact word occurrences
  - countWordsStartingWith(): returns count of words with given prefix
  - erase(): removes one occurrence of word
- **TrieNodeWithCount.java**
  - Extended TrieNode with wordCount and prefixCount fields
- **SearchSuggestions.java** (LeetCode 1268 - Search Suggestions System)
  - Classic approach using startsWith + DFS
  - Returns top 3 lexicographically smallest suggestions for each prefix
- **SearchSuggestionsV2.java**
  - Optimized approach with precomputed suggestions stored at each node
  - O(1) lookup for suggestions per prefix
- **WordSuggestions.java**
  - Main entry point demonstrating both SearchSuggestions approaches
- **WordWithAllPrefixes.java**
  - Find longest word where every prefix is also present in the array
  - Trie-based solution using DFS
  - Non-Trie solution using sorting and HashSet

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

#### Battleship (in `game.battleship`) -Kotak Bank Interview Problem
- **BattleShipGameApplication.java**
  - Entry point: initializes game service, adds ships to both players, views battlefield, starts game
- **BattleShipGameService.java**
  - Validates grid size (must be even)
  - Creates GameManager with RandomFiringStrategy
  - Exposes initGame(), addShip(), viewBattleField(), startGame()
- **GameManager.java**
  - Creates two BattleFields (PlayerA and PlayerB) by splitting the grid
  - Adds ships to both player fields
  - Prints individual and combined battlefield views
  - Runs turn-based game loop alternating between players
  - Detects winner when all enemy ships are destroyed
  - Delegates firing to FiringStrategy
- **BattleField.java**
  - Stores ships in a HashMap
  - Validates ship placement (bounds check, overlap detection)
  - Handles fire() to check hit/miss and mark ship as destroyed
  - Checks if all ships are destroyed
  - Prints grid state
- **Ship.java**
  - Stores id, destroyed status, and set of cell coordinates
  - Constructor generates cells based on size and starting position
- **Coordinate.java**
  - Java record for (x, y) coordinate representation
- **MissileTracker.java**
  - Static tracker storing fired coordinates
  - alreadyFired() checks if coordinate was already targeted
  - markFired() records new shot
- **FiringStrategy.java**
  - Interface defining fire() method returning Coordinate
- **RandomFiringStrategy.java**
  - Generates random coordinates within enemy field bounds
  - Uses MissileTracker to avoid duplicate shots

#### Tic Tac Toe (in `game.tictoctoe`)
- **TicTocToe.java**
  - Entry point: prompts user to choose Human vs Human or Human vs AI mode
  - Creates and starts Game with selected mode
- **Game.java**
  - Creates Board and two Players (X=human, O=human or AI based on mode)
  - Alternates turns between players
  - For AI turn: calls MinimaxAI.bestMove()
  - For human turn: reads input and validates move
  - Checks win/draw condition after each move
- **MinimaxAI.java**
  - bestMove(): tries all empty cells, evaluates with minimax, returns optimal position
  - minimax(): recursive algorithm scoring +10 for AI win, -10 for human win, 0 for draw
  - Maximizes AI score, minimizes human score for unbeatable play
- **Board.java**
  - 3x3 char grid for game state
  - reset(): clears grid to empty
  - placeMove(): validates position (1-9), places symbol if valid
  - isFull(): checks if board is full (draw condition)
  - checkWin(): checks rows, columns, and diagonals for three in a row
  - print(): displays formatted grid with separators
- **Player.java**
  - Stores symbol (X or O) and isAI flag
- **GameMode.java**
  - Enum: HUMAN, AI

**Directory:** `src/main/java/com/kumar/interview/prep/interview_prep/dsa/`

---

## Design Patterns

### Creational Patterns

#### Singleton Pattern (in `design_pattern/creational/singleton/`)
- **LoggerSingleThread.java**
  - Lazy initialization singleton for single-threaded environments
  - Creates instance only when first requested
- **LoggerMultiThread.java**
  - Eager initialization singleton (thread-safe)
  - Instance created at class loading time
- **LoggerMultiThreadDoubleLocking.java**
  - Double-checked locking with volatile keyword
  - Thread-safe lazy initialization with minimal synchronization overhead
- **SingletonExample.java**
  - Demo for single-threaded singleton usage
- **MultiThreadExample.java**
  - Demo for multi-threaded singleton implementations

#### Simple Factory Pattern (in `design_pattern/creational/factory/simple/`)
- **DatabaseConnectionFactory.java**
  - Static factory method with enum-based type selection
  - Creates MySQL, PostgreSQL, or MongoDB connections
  - Encapsulates object creation logic
- **DatabaseConnection.java**
  - Interface defining connect(), disconnect(), executeQuery(), getDatabaseType()
- **MySQLConnection.java**
  - MySQL implementation with host/database configuration
- **PostgreSQLConnection.java**
  - PostgreSQL implementation
- **MongoDBConnection.java**
  - MongoDB implementation
- **SimpleFactoryExample.java**
  - Demo showcasing factory usage with different database types

#### Abstract Factory Pattern (in `design_pattern/creational/factory/abstract_factory/`)
- **CarFactory.java**
  - Abstract factory interface: createCar(), createSpecification()
- **NorthAmericaCarFactory.java**
  - Concrete factory creating Sedan cars with North America specifications
- **EuropeCarFactory.java**
  - Concrete factory creating Hatchback cars with Europe specifications
- **Car.java**
  - Product interface with Sedan and Hatchback implementations
- **CarSpecification.java**
  - Product interface with NorthAmericaSpecification and EuropeSpecification
- **CarFactoryClient.java**
  - Demo client showing region-specific car creation

#### Builder Pattern (in `design_pattern/creational/builder/`)
- **House.java**
  - Immutable House object with required (address, rooms) and optional properties
  - Static nested HouseBuilder class with fluent API
  - withGarage(), withSwimmingPool(), withGarden(), withColor(), withSquareFeet()
  - build() returns immutable House instance
- **BuilderPatternExample.java**
  - Demo creating simple, luxury, and standard houses
  - Demonstrates immutability and step-by-step construction

**Directory:** `src/main/java/com/kumar/interview/prep/design_pattern/`
