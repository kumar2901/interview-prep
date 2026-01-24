package com.kumar.interview.prep.dsa.graph;

import java.util.*;

public class TopologicalSortExamples {

    /**
     * Performs Topological Sort using Kahn's Algorithm (BFS-based approach).
     *
     * <p>
     * Kahn's Algorithm processes nodes with in-degree 0, removes their outgoing edges, and continues until all nodes
     * are processed. If at the end some nodes still have non-zero in-degree, the graph contains a cycle and a valid
     * topological ordering does not exist.
     * </p>
     *
     * <p>
     * <b>Steps:</b>
     * </p>
     * <ol>
     * <li>Compute in-degree for every vertex.</li>
     * <li>Add all vertices with in-degree 0 to a queue.</li>
     * <li>While the queue is not empty:
     * <ul>
     * <li>Pop a vertex from the queue and add it to the result.</li>
     * <li>Reduce the in-degree of each of its neighbors by 1.</li>
     * <li>If any neighbor's in-degree becomes 0, push it to the queue.</li>
     * </ul>
     * </li>
     * <li>If all vertices are processed → return valid topological order.</li>
     * <li>If not → return empty list (graph contains a cycle).</li>
     * </ol>
     *
     * <p>
     * <b>Time Complexity:</b> O(V + E) <br>
     * <b>Space Complexity:</b> O(V)
     * </p>
     *
     * @param n
     *            number of vertices in the graph
     * @param adj
     *            adjacency list representing the directed graph
     * @return list of vertices in topologically sorted order, or empty list if cycle detected
     */
    private List<Integer> kahnAlgorithm(int n, List<List<Integer>> adj) {

        int[] indegree = new int[n];

        // Step 1: Compute in-degree for all nodes
        for (int u = 0; u < n; u++) {
            for (int v : adj.get(u)) {
                indegree[v]++;
            }
        }

        // Step 2: Add all nodes with in-degree 0 to queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        List<Integer> topo = new ArrayList<>();

        // Step 3: Process the queue
        while (!q.isEmpty()) {
            int node = q.poll();
            topo.add(node);

            // Reduce in-degree of neighbors
            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        // Step 4: Check if topological sort includes all nodes
        if (topo.size() != n) {
            return Collections.emptyList(); // cycle detected
        }

        return topo;

    }

    /**
     * Determines if all courses can be completed given the prerequisite constraints.
     *
     * <p>
     * This problem is solved using Kahn's Algorithm for Topological Sorting. Each course represents a node, and each
     * prerequisite pair (a, b) represents a directed edge b → a (to take course 'a', you must complete course 'b').
     * </p>
     *
     * <p>
     * If the graph has a cycle, it is impossible to complete all courses. If no cycle exists, all courses can be
     * finished.
     * </p>
     *
     * <p>
     * <b>Algorithm (Kahn's Algorithm - BFS):</b>
     * </p>
     * <ol>
     * <li>Build an adjacency list from prerequisites.</li>
     * <li>Compute in-degree for all courses.</li>
     * <li>Add all courses with in-degree 0 (no prerequisites) into a queue.</li>
     * <li>Process the queue:
     * <ul>
     * <li>Remove a course from the queue.</li>
     * <li>Decrease the in-degree of all dependent courses.</li>
     * <li>If any dependent course's in-degree becomes 0, add it to queue.</li>
     * </ul>
     * </li>
     * <li>If the number of processed courses equals numCourses → all courses can be completed.</li>
     * <li>Otherwise → a cycle exists, so return false.</li>
     * </ol>
     *
     * <p>
     * <b>Time Complexity:</b> O(V + E) <br>
     * <b>Space Complexity:</b> O(V + E)
     * </p>
     *
     * @param numCourses
     *            total number of courses labeled from 0 to numCourses - 1
     * @param prerequisites
     *            array where each entry prerequisites[i] = [a, b] meaning course 'a' requires course 'b' first
     * @return true if all courses can be completed, false otherwise
     */
    private boolean courseScheduleI(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adjGraph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjGraph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int u = edge[1];
            int v = edge[0];
            adjGraph.get(u).add(v);
            indegree[v] = indegree[v] + 1;;
        }
        // System.out.println("adjacencyGraph "+adjGraph);
        // System.out.println("indegree "+Arrays.toString(indegree));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }
        int count = 0;
        while (!pq.isEmpty()) {
            int u = pq.poll();
            count++;
            for (int nei : adjGraph.get(u)) {
                indegree[nei]--;
                if (indegree[nei] == 0) {
                    pq.offer(nei);
                }
            }
        }
        return count == numCourses;
    }

    /**
     * Returns a valid order in which all courses can be completed based on prerequisite constraints, or an empty array
     * if it is impossible.
     *
     * <p>
     * This problem is solved using Kahn's Algorithm (Topological Sort using BFS). Each course is a node, and each
     * prerequisite pair (a, b) forms a directed edge b → a (meaning course 'a' depends on course 'b').
     * </p>
     *
     * <p>
     * If the graph contains a cycle, no valid ordering exists, so the result array will be empty. If no cycle is
     * present, the topological ordering represents a valid course completion sequence.
     * </p>
     *
     * <p>
     * <b>Algorithm (Kahn’s Algorithm - BFS):</b>
     * </p>
     * <ol>
     * <li>Construct an adjacency list from the prerequisite pairs.</li>
     * <li>Calculate in-degree (number of prerequisites) for each course.</li>
     * <li>Add all courses with in-degree 0 to a queue (courses you can take immediately).</li>
     * <li>While the queue is not empty:
     * <ul>
     * <li>Remove a course and append it to the result ordering.</li>
     * <li>Reduce the in-degree of its neighboring (dependent) courses.</li>
     * <li>If any neighbor’s in-degree becomes 0, add it to the queue.</li>
     * </ul>
     * </li>
     * <li>If all courses are processed, return the result list as an array.</li>
     * <li>If not all courses are processed (a cycle exists), return an empty array.</li>
     * </ol>
     *
     * <p>
     * <b>Time Complexity:</b> O(V + E) <br>
     * <b>Space Complexity:</b> O(V + E)
     * </p>
     *
     * @param numCourses
     *            total number of courses labeled from 0 to numCourses - 1
     * @param prerequisites
     *            array where prerequisites[i] = [a, b] meaning to take course 'a' you must first complete 'b'
     * @return an array representing one valid order to finish all courses, or an empty array if no such ordering exists
     */
    private List<Integer> courseScheduleII(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adjGraph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjGraph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int u = edge[1];
            int v = edge[0];
            adjGraph.get(u).add(v);
            indegree[v] = indegree[v] + 1;;
        }
        // System.out.println("adjacencyGraph "+adjGraph);
        // System.out.println("indegree "+Arrays.toString(indegree));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }
        List<Integer> topo = new ArrayList<>();
        while (!pq.isEmpty()) {
            int u = pq.poll();
            topo.add(u);
            for (int nei : adjGraph.get(u)) {
                indegree[nei]--;
                if (indegree[nei] == 0) {
                    pq.offer(nei);
                }
            }
        }

        if (topo.size() != numCourses) {
            return Collections.emptyList();
        }
        return topo;

    }

    public void main(String[] args) {
        int[][] prerequisite = {{1, 0}};
        System.out.println(courseScheduleI(2, prerequisite));
        System.out.println("Ordering= " + courseScheduleII(2, prerequisite));

        int[][] prerequisite1 = {{1, 0}, {0, 1}};
        System.out.println(courseScheduleI(2, prerequisite1));
        System.out.println("Ordering= " + courseScheduleII(2, prerequisite1));

        int[][] prerequisite2 = {{1, 0}, {2, 0}, {3, 1}};
        System.out.println(courseScheduleI(4, prerequisite2));
        System.out.println("Ordering=" + courseScheduleII(4, prerequisite2));

    }
}
