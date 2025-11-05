package graph.algo.connectedGraph;

public class UnionFindGraph {
    private int[] parent;
    private int[] rank;  // Or size, to keep tree shallow

    // Initialize parent and rank arrays
    public UnionFindGraph(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;  // Each node is its own parent initially
            rank[i] = 0;    // Rank is 0 for all initially
        }
    }

    // Find the root parent of node x with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Path compression
        }
        return parent[x];
    }

    // Union two sets by rank
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;  // Already in the same set, union not performed
        }

        // Attach smaller rank tree under root of higher rank tree
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }

    // Check if two nodes are in the same set
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    // Example Usage
    public static void main(String[] args) {
        UnionFindGraph uf = new UnionFindGraph(5);

        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);

        System.out.println(uf.connected(0, 2)); // true
        System.out.println(uf.connected(0, 4)); // false

        uf.union(2, 4);

        System.out.println(uf.connected(0, 4)); // true
    }
}

/*
* Output
*
* true
* false
* true
* */

