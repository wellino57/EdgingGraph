import java.util.List;

public class Edge {
    Vertex v1;
    Vertex v2;
    int weight;

    public Edge(Vertex p1, Vertex p2, int wei){
        this.v1 = p1;
        this.v2 = p2;
        this.weight = wei;
    }
}
