package graph.disconnectedGraphAlgorithem;

import java.util.LinkedList;
import java.util.Queue;

// Graph traversal (DFS and BFS) implementation for a graph to check Disconnected Graph using adjacency matrix representation
public class GraphTraversalAdjacencyMatrix {

    // Depth-First Search (Recursive)
    public void dfs(int node, boolean[] visited, int[][] adjMatrix) {
        visited[node] = true;
        System.out.print(node+" ");
        for (int neighbor = 0; neighbor<adjMatrix.length; neighbor++) {
            if(adjMatrix[node][neighbor] ==1 && !visited[neighbor]) {
                dfs(neighbor, visited, adjMatrix);
            }
        }
    }

    // Breadth-First Search (Iterative)
    public void bfs(int start, boolean[] visited, int[][] adjMatrix) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            System.out.print(currentNode+" ");
            for (int neighbor =0; neighbor< adjMatrix.length; neighbor++) {
                if (adjMatrix[currentNode][neighbor] ==1 && !visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

    }

    public static void main(String[] args) {
        // Sample graph with 6 nodes and 3 components
        int[][] adjMatrix = {
                // 0  1  2  3  4  5
                {0, 1, 1, 0, 0, 0}, // 0
                {1, 0, 0, 0, 0, 0}, // 1
                {1, 0, 0, 0, 0, 0}, // 2
                {0, 0, 0, 0, 1, 0}, // 3
                {0, 0, 0, 1, 0, 0}, // 4
                {0, 0, 0, 0, 0, 0}  // 5 (isolated)
        };

        GraphTraversalAdjacencyMatrix graph = new GraphTraversalAdjacencyMatrix();
        System.out.println("DFS Traversal of Disconnected Graph:");
        boolean[] visitedDFS = new boolean[adjMatrix.length];
        for (int i = 0; i < adjMatrix.length; i++) {
            if (!visitedDFS[i]) {
                System.out.print("Component: ");
                graph.dfs(i, visitedDFS, adjMatrix);
                System.out.println();
            }
        }


        System.out.println("\nBFS Traversal of Disconnected Graph:");
        boolean[] visitedBFS = new boolean[adjMatrix.length];
        for (int i = 0; i < adjMatrix.length; i++) {
            if (!visitedBFS[i]) {
                System.out.print("Component: ");
                graph.bfs(i, visitedBFS, adjMatrix);
                System.out.println();
            }
        }
    }
}

/*
* Output:
*
* DFS Traversal of Disconnected Graph:
Component: 0 1 2
Component: 3 4
Component: 5

* BFS Traversal of Disconnected Graph:
Component: 0 1 2
Component: 3 4
Component: 5
* */
