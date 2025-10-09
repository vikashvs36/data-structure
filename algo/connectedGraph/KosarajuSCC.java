package graph.algo.connectedGraph;

import java.util.*;

/*
* It works in two DFS passes:
* First DFS: Run DFS on the original graph, keeping track of the finish times (using a stack).
* Transpose Graph: Reverse all edges in the graph.
* Second DFS: Run DFS on the transposed graph in the order defined by the stack (from the first DFS). Each DFS call gives you one SCC.
**/
public class KosarajuSCC {
    private int V;
    private List<List<Integer>> graph;
    private List<List<Integer>> transposeGraph;

    public KosarajuSCC(int V) {
        this.V = V;
        graph = new ArrayList<>();
        transposeGraph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
            transposeGraph.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        graph.get(u).add(v);
    }

    private void fillOrder(int v, boolean[] visited, Deque<Integer> stack) {
        visited[v] = true;
        for (int neighbor : graph.get(v)) {
            if (!visited[neighbor]) {
                fillOrder(neighbor, visited, stack);
            }
        }
        stack.push(v);
    }

    private void dfs(int v, boolean[] visited, List<Integer> component, List<List<Integer>> g) {
        visited[v] = true;
        component.add(v);
        for (int neighbor : g.get(v)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, component, g);
            }
        }
    }

    private void buildTranspose() {
        for (int u = 0; u < V; u++) {
            for (int v : graph.get(u)) {
                transposeGraph.get(v).add(u);
            }
        }
    }

    public List<List<Integer>> getSCCs() {
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[V];

        // 1. Fill vertices in stack according to their finishing times
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }

        // 2. Create transpose graph
        buildTranspose();

        // 3. Process all vertices in order defined by stack
        Arrays.fill(visited, false);
        List<List<Integer>> sccs = new ArrayList<>();

        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                List<Integer> component = new ArrayList<>();
                dfs(v, visited, component, transposeGraph);
                sccs.add(component);
            }
        }
        return sccs;
    }

    public static void main(String[] args) {
        KosarajuSCC kosaraju = new KosarajuSCC(8);

        kosaraju.addEdge(0, 1);
        kosaraju.addEdge(1, 2);
        kosaraju.addEdge(2, 0);
        kosaraju.addEdge(2, 3);
        kosaraju.addEdge(3, 4);
        kosaraju.addEdge(4, 5);
        kosaraju.addEdge(5, 3);
        kosaraju.addEdge(6, 5);
        kosaraju.addEdge(6, 7);

        List<List<Integer>> sccs = kosaraju.getSCCs();

        System.out.println("Strongly Connected Components:");
        for (List<Integer> scc : sccs) {
            System.out.println(scc);
        }
    }
}

/*
* Output
*
* [6]
* [7]
* [0, 2, 1]
* [3, 5, 4]
* */
