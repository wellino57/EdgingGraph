public class Vertex {
    int id;
    int path = Integer.MAX_VALUE;
    Vertex previous;

    public Vertex(int id){
        this.id = id;
    }
}
