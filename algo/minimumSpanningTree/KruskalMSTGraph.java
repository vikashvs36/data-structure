package graph.algo.minimumSpanningTree;

import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }

    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class KruskalMSTGraph {
    private int V;
    private List<Edge> edges = new ArrayList<>();

    KruskalMST(int V) {
        this.V = V;
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    // Union-Find Structure
    class Subset {
        int parent, rank;
    }

    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent); // path compression
        return subsets[i].parent;
    }

    void union(Subset[] subsets, int x, int y) {
        int xRoot = find(subsets, x);
        int yRoot = find(subsets, y);

        if (subsets[xRoot].rank < subsets[yRoot].rank) {
            subsets[xRoot].parent = yRoot;
        } else if (subsets[xRoot].rank > subsets[yRoot].rank) {
            subsets[yRoot].parent = xRoot;
        } else {
            subsets[yRoot].parent = xRoot;
            subsets[xRoot].rank++;
        }
    }

    public void kruskalMST() {
        Collections.sort(edges); // sort edges by weight

        Subset[] subsets = new Subset[V];
        for (int v = 0; v < V; v++) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        List<Edge> result = new ArrayList<>();

        for (Edge edge : edges) {
            int x = find(subsets, edge.src);
            int y = find(subsets, edge.dest);

            if (x != y) {
                result.add(edge);
                union(subsets, x, y);
            }
        }

        System.out.println("Edges in MST (Kruskal's):");
        int totalWeight = 0;
        for (Edge e : result) {
            System.out.println(e.src + " - " + e.dest + " : " + e.weight);
            totalWeight += e.weight;
        }
        System.out.println("Total Weight: " + totalWeight);
    }

    public static void main(String[] args) {
        KruskalMST graph = new KruskalMST(6);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 5);
        graph.addEdge(2, 4, 11);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 6);
        graph.addEdge(4, 5, 1);

        graph.kruskalMST();
    }
}


/*
 * Output
 *
 * Edges in MST (Kruskal's):
 * 4 - 5 : 1
 * 1 - 2 : 2
 * 3 - 4 : 2
 * 0 - 1 : 4
 * 1 - 3 : 5
 * Total Weight: 14
 * */
