public class Main {
    public static void main(String[] args) {

        Graph g = new Graph();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addEdge(1,2,6);
        g.removeEdge(2,3);
        g.removeEdge(2,1);
        g.addEdge(1,3,5);
        g.addEdge(1,2,5);
        g.addEdge(1,4,5);
        g.addEdge(1,5,5);
        g.removeVertex(1);

    }
}