import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Directed graph implementation using adjacency list representation
public class DirectedAdjacencyListGraph {
    private int E;
    private final int V;
    private final ArrayList<ArrayList<Integer>> ADJ;

    public DirectedAdjacencyListGraph(int V) {
        this.E = 0;
        this.V = V;
        this.ADJ = new ArrayList<>(V);
        for (int v=0; v<V; v++) {
            ADJ.add(new ArrayList<>());
        }
    }

    public void addEdge(int v, int w) {
        ADJ.get(v).add(w);
        E++;
    }

    public Stream<Integer> getAdj(int v) {
        return ADJ.get(v).stream();
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public static void main(String[] args) {
        DirectedAdjacencyListGraph graph = new DirectedAdjacencyListGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);

        System.out.println("Number of vertices: " + graph.getV());
        System.out.println("Number of edges: " + graph.getE());

        for (int v=0; v<graph.getV(); v++) {
            System.out.print("Adjacency list of vertex " + v + ": ");

            String innerValue = graph.getAdj(v)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", "[", "]"));

            System.out.print(innerValue+"\n");
        }
    }
}


/*
* Output:
* 
* Number of vertices: 5
Number of edges: 5
Adjacency list of vertex 0: [1, 2]
Adjacency list of vertex 1: [2, 3]
Adjacency list of vertex 2: []
Adjacency list of vertex 3: [4]
Adjacency list of vertex 4: []
* */
