package graph.algo.connectedGraph;

import java.util.*;

// Strongly Connected Components:
public class TarjanSCC {
    private int time = 0;
    private int[] low;
    private int[] disc;
    private boolean[] inStack;
    private Deque<Integer> stack;
    private List<List<Integer>> sccs;
    private List<List<Integer>> graph;

    public TarjanSCC(int vertices) {
        low = new int[vertices];
        disc = new int[vertices];
        inStack = new boolean[vertices];
        stack = new ArrayDeque<>();
        sccs = new ArrayList<>();

        // Initialize graph
        graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
            disc[i] = -1; // Unvisited
            low[i] = -1;
        }
    }

    public void addEdge(int u, int v) {
        graph.get(u).add(v); // Directed edge
    }

    public List<List<Integer>> findSCCs() {
        int V = graph.size();
        for (int i = 0; i < V; i++) {
            if (disc[i] == -1) {
                dfs(i);
            }
        }
        return sccs;
    }

    private void dfs(int u) {
        disc[u] = low[u] = time++;
        stack.push(u);
        inStack[u] = true;

        for (int v : graph.get(u)) {
            if (disc[v] == -1) {
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (inStack[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        // If u is root of an SCC
        if (disc[u] == low[u]) {
            List<Integer> scc = new ArrayList<>();
            int v;
            do {
                v = stack.pop();
                inStack[v] = false;
                scc.add(v);
            } while (u != v);
            sccs.add(scc);
        }
    }

    public static void main(String[] args) {
        TarjanSCC tarjan = new TarjanSCC(8);

        tarjan.addEdge(0, 1);
        tarjan.addEdge(1, 2);
        tarjan.addEdge(2, 0);
        tarjan.addEdge(2, 3);
        tarjan.addEdge(3, 4);
        tarjan.addEdge(4, 5);
        tarjan.addEdge(5, 3);
        tarjan.addEdge(6, 5);
        tarjan.addEdge(6, 7);

        List<List<Integer>> sccs = tarjan.findSCCs();

        System.out.println("Strongly Connected Components:");
        for (List<Integer> scc : sccs) {
            System.out.println(scc);
        }
    }
}

/*
* Output
*
* [5, 4, 3]
* [2, 1, 0]
* [7]
* [6]
* */
