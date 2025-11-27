package com.kumar.interview.prep.interview_prep.dsa.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphExamples {

    /**
     * Determines whether a given undirected graph forms a valid tree.
     *
     * <p>
     * The graph consists of {@code n} nodes labeled from {@code 0} to {@code n - 1}, given along with a list of
     * undirected edges. Each edge is represented as {@code [u, v]}, indicating a bidirectional connection between nodes
     * {@code u} and {@code v}.
     *
     * <p>
     * A graph is considered a valid tree if and only if:
     * <ul>
     * <li>It is fully connected — every node is reachable from any other node.</li>
     * <li>It contains no cycles — exactly one simple path exists between any two nodes.</li>
     * </ul>
     *
     * <p>
     * Equivalently, a graph with {@code n} nodes is a valid tree if it has exactly {@code n - 1} edges and all nodes
     * are connected.
     *
     * <p>
     * This method typically uses Depth-First Search (DFS), Breadth-First Search (BFS), or Union-Find (Disjoint Set
     * Union) to verify connectivity and cycle-freeness.
     *
     * @param n
     *            the number of nodes in the graph
     * @param edges
     *            the list of undirected edges represented as {@code [u, v]}
     * @return {@code true} if the graph forms a valid tree, otherwise {@code false}
     */
    public boolean validTree(int n, int[][] edges) {

        if (n != edges.length + 1) {
            return false;

        }

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        buildUndirectedGraph(edges, graph);

        System.out.println("Graph=" + graph);

        boolean[] visited = new boolean[n];
        boolean hasCycle = detectCycle(0, -1, graph, visited);
        if (hasCycle) {
            return false;
        }
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }

        return true;
    }

    /**
     * DFS algorithm
     *
     * @param node
     *            currentNode
     * @param parent
     *            parentNode
     * @param graph
     *            graph
     * @param visited
     *            visited
     * @return cycle exists
     */
    private boolean detectCycle(int node, int parent, Map<Integer, List<Integer>> graph, boolean[] visited) {

        visited[node] = true;
        for (int nei : graph.get(node)) {
            if (nei == parent) {
                continue;
            }
            if (visited[nei]) {
                return true;
            }
            if (detectCycle(nei, node, graph, visited)) {
                return true;
            }

        }
        return false;
    }

    private void buildUndirectedGraph(int[][] edges, Map<Integer, List<Integer>> graph) {

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);

        }
    }

    /**
     * Counts the number of islands in a 2D grid.
     *
     * <p>
     * An island is defined as a group of connected '1's (land cells) that are adjacent horizontally or vertically. The
     * grid contains '1' representing land and '0' representing water.
     * </p>
     *
     * <p>
     * The goal is to determine how many separate land masses (islands) exist in the grid. Each island must be
     * surrounded by water or the grid boundary, and no diagonal connections count as adjacency.
     * </p>
     *
     * <p>
     * <b>Time Complexity:</b> O(m × n) <br>
     * <b>Space Complexity:</b> O(m × n) in the worst case
     * </p>
     *
     * @param grid
     *            a 2D matrix containing '1' for land and '0' for water
     * @return the total number of islands present in the grid
     */
    public int numIslands(char[][] grid) {

        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(i, j, grid);
                    count++;
                }
            }
        }

        return count;

    }

    private void dfs(int i, int j, char[][] grid) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;

        }
        if (grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';

        // move horizontally
        dfs(i, j - 1, grid);
        dfs(i, j + 1, grid);

        // move vertically
        dfs(i + 1, j, grid);
        dfs(i - 1, j, grid);

    }

    public void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {3, 4}};
        System.out.println(validTree(5, edges));

        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        System.out.println(numIslands(grid));

        char[][] grid2 = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        System.out.println(numIslands(grid2));
    }
}
