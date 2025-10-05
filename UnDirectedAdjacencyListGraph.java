import java.util.ArrayList;
import java.util.stream.Stream;

public class UnDirectedAdjacencyListGraph {
    private int E; // Number of edges
    private final int V; // Number of vertices
    private final ArrayList<ArrayList<Integer>> ADJ; // Adjacency lists

    // Constructor
    public UnDirectedAdjacencyListGraph(int V) {
        this.V = V;
        this.E = 0;
        ADJ = new ArrayList<>(V);
        for (int v = 0; v < V; v++) {
            ADJ.add(new ArrayList<>());
        }
    }

    // Add an undirected edge
    public void addEdge(int v, int w) {
        ADJ.get(v).add(w);
        ADJ.get(w).add(v);
        E++;
    }

    // Get adjacent vertices
    public Stream<Integer> getAdj(int v) {
        return ADJ.get(v).stream();
    }

    // Get number of vertices
    public int V() {
        return V;
    }

    // Get number of edges
    public int E() {
        return E;
    }

    public static void main(String[] args) {
        UnDirectedAdjacencyListGraph graph = new UnDirectedAdjacencyListGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);

        System.out.println("Number of vertices: " + graph.V());
        System.out.println("Number of edges: " + graph.E());

        for (int v=0; v<graph.V; v++) {
            System.out.print("Adjacency list of vertex " + v + ": ");
            graph.getAdj(v).forEach(w -> System.out.print(w + " "));
            System.out.println();
        }
    }

}
