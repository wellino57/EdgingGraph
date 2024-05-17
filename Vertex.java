public class Vertex {
    int id;
    int path = Integer.MAX_VALUE;
    Vertex previous;
    int color = -1;

    public Vertex(int id){
        this.id = id;
    }
}
