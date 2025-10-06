package graph.algo.cycleDetection;

import java.util.*;

public class DfsDirectionBasedCycleDetection {

    public static boolean hasCycle(int node, boolean[] visited, boolean[] recStack, Map<Integer, List<Integer>> graph) {
        visited[node] = true;
        recStack[node] = true;

        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited[neighbor]) {
                if (hasCycle(neighbor, visited, recStack, graph)) {
                    return true;
                }
            } else if (recStack[neighbor]) {
                // Found a back edge → cycle
                return true;
            }
        }

        recStack[node] = false; // backtrack
        return false;
    }

    public static boolean isCyclic(Map<Integer, List<Integer>> graph, int n) {
        boolean[] visited = new boolean[n];
        boolean[] recStack = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (hasCycle(i, visited, recStack, graph)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int n = 5; // number of nodes
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // Example: Directed graph with a cycle: 0 → 1 → 2 → 0
        graph.put(0, List.of(1));
        graph.put(1, List.of(2));
        graph.put(2, List.of(0));
        graph.put(3, List.of(4));
        graph.put(4, List.of()); // no outgoing edges

        boolean result = isCyclic(graph, n);
        System.out.println("Cycle detected: " + result);  // Output: true
    }
}
