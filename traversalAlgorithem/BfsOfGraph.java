package graph;

import java.util.*;

// Breadth-First Search (BFS) implementation for a graph using adjacency list representation
public class BfsOfGraph {

    public List<Integer> bfs(int start, Map<Integer, List<Integer>> adjList) {
        List<Integer> bfsOrder = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while(!queue.isEmpty()) {
            int currentNode = queue.poll();
            bfsOrder.add(currentNode);
            for (Integer neighbor : adjList.getOrDefault(currentNode, new ArrayList<>())) {
                if(!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return bfsOrder;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        adjList.put(0, Arrays.asList(1, 2));
        adjList.put(1, Arrays.asList(0, 3, 4));
        adjList.put(2, Arrays.asList(0, 4));
        adjList.put(3, Arrays.asList(1, 5));
        adjList.put(4, Arrays.asList(1, 2, 5));
        adjList.put(5, Arrays.asList(3, 4));

        BfsOfGraph graph = new BfsOfGraph();
        System.out.print("Final BFS traversal order: ");
        graph.bfs(0, adjList)
                .forEach(node -> System.out.print(node+ " "));
    }
}


/*
* Output
* 
* Final BFS traversal order: 0 1 2 3 4 5
 * */
