package com.kumar.interview.prep.dsa.graph;

import java.util.*;

public class DijkstraAlgoExamples {

    /**
     * Computes the time required for all nodes in a directed weighted graph to receive a signal sent from a given
     * starting node.
     *
     * <p>
     * The graph is represented by the {@code times} array, where each entry {@code times[i] = (u, v, w)} indicates that
     * a signal takes {@code w} units of time to travel from node {@code u} to node {@code v}. All nodes are labeled
     * from {@code 1} to {@code n}.
     *
     * <p>
     * The method returns the minimum time needed for the signal to reach all nodes using the shortest paths. If any
     * node cannot be reached from the starting node {@code k}, the method returns {@code -1}.
     *
     * <p>
     * This problem is typically solved using Dijkstra's shortest path algorithm.
     *
     * @param times
     *            the list of directed edges, where each edge is represented as {@code [u, v, w]} meaning a travel time
     *            {@code w} from node {@code u} to node {@code v}
     * @param n
     *            the total number of nodes in the graph
     * @param k
     *            the starting node from which the signal is sent
     * @return the time when the last node receives the signal, or {@code -1} if any node is unreachable
     */

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<GraphNode>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        buildGraph(times, graph);
        // System.out.println(graph);

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<GraphNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.offer(new GraphNode(k, 0));
        dist[k] = 0;

        while (!pq.isEmpty()) {

            GraphNode node = pq.poll();

            int curr = node.v;
            int currDist = node.weight;

            if (currDist > dist[curr]) {
                continue;
            }

            // neighbors
            for (GraphNode nei : graph.get(curr)) {
                int next = nei.v;
                int weight = nei.weight;

                if (dist[curr] + weight < dist[next]) {
                    dist[next] = dist[curr] + weight;
                    pq.offer(new GraphNode(next, dist[next]));
                }
            }

        }
        System.out.println(Arrays.toString(dist));

        int result = -1;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            result = Math.max(result, dist[i]);
        }

        return result;
    }

    private void buildGraph(int[][] times, Map<Integer, List<GraphNode>> graph) {

        for (int[] edge : times) {
            int u = edge[0];
            graph.get(u).add(new GraphNode(edge[1], edge[2]));
        }
    }

    public void main(String[] args) {

        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};

        System.out.println(networkDelayTime(times, 4, 2));

    }
}
