package graph.algo.topologicalSorting;

import java.util.*;

public class DfsTopologicalSorting {

    public List<Integer> sorting(Map<Integer, List<Integer>> adjList) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        for (int node : adjList.keySet()) {
            if (!visited.contains(node)) {
                dfs(node, stack, visited, adjList);
            }
        }
        // Convert stack to list in correct topological order
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private void dfs(int startNode, Stack<Integer> stack, Set<Integer> visited,
                     Map<Integer, List<Integer>> adjList) {
        visited.add(startNode);
        for (int neighbor: adjList.getOrDefault(startNode, List.of())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, stack, visited, adjList);
            }
        }
        stack.push(startNode);
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> adjList =new HashMap<>();
        adjList.put(0, List.of(2, 3));
        adjList.put(1, List.of(0, 2, 4));
        adjList.put(3, List.of(4));

        DfsTopologicalSorting graph = new DfsTopologicalSorting();
        System.out.print("TopologicalSorting in graph using DFS : ");
        graph.sorting(adjList)
                .forEach(node -> System.out.print(node+ " "));
    }
}

/*
* Output:
*
* TopologicalSorting in graph using DFS : 1 0 3 4 2
 * */
