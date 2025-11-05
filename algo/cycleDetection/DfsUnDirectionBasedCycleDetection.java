package graph.algo.cycleDetection;

import java.util.*;
import java.util.function.Predicate;

// Dfs UnDirection Based Cycle Detection Graph
public class DfsUnDirectionBasedCycleDetection {

    public boolean dfsCycleDetection(int startNode, int parentNode, Set<Integer> visited, Map<Integer, List<Integer>> adjList) {
        visited.add(startNode);
        for (int neighbor : adjList.getOrDefault(startNode, List.of())) {
            if (Predicate.not(visited::contains).test(neighbor)) {
                if (dfsCycleDetection(neighbor, startNode, visited, adjList)) {
                    return true;
                }
            } else if (neighbor != parentNode){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        adjList.put(0, List.of(1,3));
        adjList.put(1, List.of(0));

        adjList.put(3, List.of(0, 2, 4));
        adjList.put(2, List.of(3, 4));
        adjList.put(4, List.of(2, 3));

        DfsUnDirectionBasedCycleDetection graph = new DfsUnDirectionBasedCycleDetection();
        boolean isCyclicGraph = graph.dfsCycleDetection(0,-1, new HashSet<>(), adjList);
        System.out.println("is this graph detected as Cyclic : "+isCyclicGraph);
    }
}


/*
* Output
*
* is this graph detected as Cyclic : true
* */
