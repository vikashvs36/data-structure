package graph;

import java.util.*;

// Depth-First Search (DFS) implementation for a graph using adjacency list representation
public class DfsOfGraph {

    public void dfs(int v, Set<Integer> visited, List<List<Integer>> adjList) {
        visited.add(v);
        System.out.print(v+" ");
        for(Integer neighbor : adjList.get(v)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited, adjList);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> adjList = List.of(
                List.of(1, 2),    // Neighbors of vertex 0
                List.of(0, 3, 4), // Neighbors of vertex 1
                List.of(0, 4),    // Neighbors of vertex 2
                List.of(1, 5),    // Neighbors of vertex 3
                List.of(1, 2, 5), // Neighbors of vertex 4
                List.of(3, 4)     // Neighbors of vertex 5
        );

        DfsOfGraph graph = new DfsOfGraph();
        System.out.print("Final DFS traversal order: ");
        graph.dfs(0, new HashSet<>(), adjList);
    }
}

/*
* Output
* Final DFS traversal order: 0 1 3 5 4 2
 * */
