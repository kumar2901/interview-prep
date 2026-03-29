package com.kumar.interview.prep.dsa.ebay_codesignal;

import java.util.*;

public class UserWithAttributes {

    /**
     *
     * u1-> [p1,p2,p4] u2->[p2,p3] u3->[p4,p1] u4->[p5]
     *
     *
     * Answer: u1->[u2,u3] u2->[u1] u3->[u1] u4->[]
     *
     * @param users
     *            user with attirbutes
     */

    public Map<String, Set<String>> buildUsersWithCommonAttributes(Map<String, List<String>> users) {

        Map<String, Set<String>> result = new HashMap<>();

        Set<String> userSet = users.keySet();
        for (String user : userSet) {
            result.put(user, new HashSet<>());
        }

        Map<String, Set<String>> attributeToUserMap = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : users.entrySet()) {

            String user = entry.getKey();

            for (String attribute : entry.getValue()) {
                attributeToUserMap.computeIfAbsent(attribute, k -> new HashSet<>()).add(user);

            }

        }

        // System.out.println("attributesToUserMapping=" + attributeToUserMap);

        for (Map.Entry<String, List<String>> entry : users.entrySet()) {
            String user = entry.getKey();
            List<String> attributes = entry.getValue();

            for (String attribute : attributes) {
                result.get(user).addAll(attributeToUserMap.get(attribute));
            }
            result.get(user).remove(user);

        }

        return result;

    }

    public List<List<String>> connectedUsers(Map<String, List<String>> users) {
        List<List<String>> result = new ArrayList<>();
        Map<String, Set<String>> graph = buildUsersWithCommonAttributes(users);

        Set<String> visited = new HashSet<>();

        for (String user : users.keySet()) {

            if (!visited.contains(user)) {
                List<String> connectedUsers = new ArrayList<>();
                dfs(user, graph, visited, connectedUsers);

                result.add(connectedUsers);
            }
        }

        return result;
    }

    private void dfs(String user, Map<String, Set<String>> graph, Set<String> visited, List<String> connectedUsers) {
        visited.add(user);
        connectedUsers.add(user);

        for (String nei : graph.get(user)) {
            if (!visited.contains(nei)) {
                dfs(nei, graph, visited, connectedUsers);
            }
        }
    }

    /**
     *
     * Problem: Build Maximum Spanning Tree (MST) of Users Based on Common Attributes
     *
     * You are given a mapping of users to a list of attributes:
     *
     * Example: u1 -> [p1, p2, p4] u2 -> [p2, p3] u3 -> [p4, p1] u4 -> [p5]
     *
     * Two users are considered connected if they share at least one common attribute. The strength of connection (edge
     * weight) between two users is defined as:
     *
     * weight(u, v) = number of common attributes between user u and user v
     *
     * Tasks:
     *
     * 1. Construct an undirected weighted graph where: * Each user is a node * An edge exists between two users if they
     * share at least one attribute * The weight of the edge is the count of shared attributes
     *
     * 2. Compute the Maximum Spanning Tree (MaxST) of the graph: * The goal is to connect all users (within each
     * connected component) ``` such that the total weight (sum of selected edges) is maximized ``` * If the graph is
     * disconnected, return a Maximum Spanning Forest
     *
     * Constraints: * 1 <= number of users <= N * 1 <= attributes per user <= K * Attribute values are strings
     *
     * Expected Output: * Return the total weight of the Maximum Spanning Tree (or Forest) OR * Return the list of edges
     * included in the MaxST
     *
     * Example:
     *
     * Input: u1 -> [p1, p2, p4] u2 -> [p2, p3] u3 -> [p4, p1] u4 -> [p5]
     *
     * Graph Edges: (u1, u2) weight = 1 // common: p2 (u1, u3) weight = 2 // common: p1, p4
     *
     * Maximum Spanning Tree: pick (u1, u3) -> weight 2 pick (u1, u2) -> weight 1
     *
     * Total weight = 3
     *
     * Notes: * Users with no shared attributes form isolated components * Avoid O(N^2) pair comparisons by using
     * attribute-to-users mapping * Recommended approach: Kruskal’s Algorithm + Union-Find (Disjoint Set)
     *
     * Follow-up: * Return clusters (connected components) * Handle streaming input of users * Optimize for very large
     * attribute sets
     */

    public int maxSpanningTree(Map<String, List<String>> users) {
        List<Edge> edges = buildEdges(users);
        System.out.println("edges " + edges);

        edges.sort((a, b) -> b.weight - a.weight);

        DSU dsu = new DSU();
        int total = 0;

        for (Edge e : edges) {
            if (dsu.union(e.u, e.v)) {
                total += e.weight;
            }
        }

        return total;

    }

    private List<Edge> buildEdges(Map<String, List<String>> users) {

        Map<String, Set<String>> graph = buildUsersWithCommonAttributes(users);

        // convert users to userToAttribute set
        Map<String, Set<String>> userSets = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : users.entrySet()) {
            String user = entry.getKey();
            List<String> attributes = entry.getValue();
            userSets.put(user, new HashSet<>(attributes));
        }
        List<Edge> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String u : graph.keySet()) {
            for (String v : graph.get(u)) {
                String key = u.compareTo(v) < 0 ? u + "#" + v : v + "#" + u;
                if (!visited.contains(key)) {
                    visited.add(key);

                    Set<String> set = new HashSet<>(userSets.get(u));
                    set.retainAll(userSets.get(v));

                    int weight = set.size();
                    if (weight > 0) {
                        result.add(new Edge(u, v, weight));
                    }
                }
            }

        }

        return result;
    }

    void main() {
        Map<String, List<String>> users = new HashMap<>();
        users.put("user1", Arrays.asList("p1", "p2", "p4"));
        users.put("user2", Arrays.asList("p2", "p3"));
        users.put("user3", Arrays.asList("p5", "p1", "p4"));
        users.put("user4", List.of("p6"));

        System.out.println("Users Graph: " + buildUsersWithCommonAttributes(users));

        System.out.println("connected components: " + connectedUsers(users));

        System.out.println("minimum spanning tree: " + maxSpanningTree(users));
    }

}
