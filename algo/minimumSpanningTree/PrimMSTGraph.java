package graph.algo.minimumSpanningTree;

import java.util.*;

class PrimEdge {
    int vertex, weight;

    PrimEdge(int v, int w) {
        vertex = v;
        weight = w;
    }
}

public class PrimMSTGraph {
    public static void primMST(List<List<PrimEdge>> graph, int V) {
        boolean[] inMST = new boolean[V];
        int[] key = new int[V]; // Weight to include vertex in MST
        int[] parent = new int[V]; // Store MST
        PriorityQueue<PrimEdge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;
        pq.offer(new PrimEdge(0, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;
            inMST[u] = true;

            for (PrimEdge edge : graph.get(u)) {
                int v = edge.vertex;
                int weight = edge.weight;

                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = u;
                    pq.offer(new PrimEdge(v, key[v]));
                }
            }
        }

        System.out.println("Edges in MST (Prim's):");
        int totalWeight = 0;
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + " : " + key[i]);
            totalWeight += key[i];
        }
        System.out.println("Total Weight: " + totalWeight);
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<PrimEdge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());

        // Undirected graph
        graph.get(0).add(new PrimEdge(1, 2));
        graph.get(1).add(new PrimEdge(0, 2));

        graph.get(0).add(new PrimEdge(3, 6));
        graph.get(3).add(new PrimEdge(0, 6));

        graph.get(1).add(new PrimEdge(2, 3));
        graph.get(2).add(new PrimEdge(1, 3));

        graph.get(1).add(new PrimEdge(3, 8));
        graph.get(3).add(new PrimEdge(1, 8));

        graph.get(1).add(new PrimEdge(4, 5));
        graph.get(4).add(new PrimEdge(1, 5));

        graph.get(2).add(new PrimEdge(4, 7));
        graph.get(4).add(new PrimEdge(2, 7));

        primMST(graph, V);
    }
}

/*
* Output
*
* Edges in MST (Prim's):
* 0 - 1 : 2
* 1 - 2 : 3
* 0 - 3 : 6
* 1 - 4 : 5
* Total Weight: 16
* */
