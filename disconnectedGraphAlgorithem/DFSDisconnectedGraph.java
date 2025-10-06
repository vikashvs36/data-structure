package graph.disconnectedGraphAlgorithem;

import java.util.*;
import java.util.function.Predicate;

// DFS implementation for a disconnected graph using adjacency list representation
public class DFSDisconnectedGraph {

    public void dfs(int start, Set<Integer> visited, Map<Integer, List<Integer>> adjList) {
        visited.add(start);
        System.out.print(start+" ");
        for (int neighbor : adjList.getOrDefault(start, List.of())) {
            if (Predicate.not(visited::contains).test(neighbor)) {
                dfs(neighbor, visited, adjList);
            }
        }
    }

    public static void main(String[] args) {
        // Create adjacency list for a graph with disconnected components
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        // Component 1
        adjList.put(0, List.of(1));
        adjList.put(1, List.of(0, 2));
        adjList.put(2, List.of(1));

        // Component 2
        adjList.put(3, List.of(4));
        adjList.put(4, List.of(3));

        // Component 3 (isolated node)
        adjList.put(5, new ArrayList<>());

        System.out.println("DFS Traversal of Disconnected Graph:");
        DFSDisconnectedGraph graph = new DFSDisconnectedGraph();
        Set<Integer> visited = new HashSet<>();
        for (Integer node: adjList.keySet()) {
            if (Predicate.not(visited::contains).test(node)) {
                System.out.print("Component : ");
                graph.dfs(node, visited, adjList);
            System.out.println();
            }
        }
    }
}

/*
* Output:
*
* DFS Traversal of Disconnected Graph:
* Component : 0 1 2
* Component : 3 4
* Component : 5
* */
