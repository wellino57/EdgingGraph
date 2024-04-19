public class Main {
    public static void main(String[] args) {

        Graph g = new Graph();

        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);
        g.addVertex(7);
        g.addVertex(8);
        g.addVertex(9);
        g.addVertex(10);
        g.addVertex(11);
        g.addVertex(12);
        g.addVertex(13);

        g.addEdge(1,2,2);
        g.addEdge(1,3,6);
        g.addEdge(2,3,1);
        g.addEdge(2,4,1);
        g.addEdge(3,4,3);
        g.addEdge(3,5,1);
        g.addEdge(4,5,5);
        g.addEdge(4,8,4);
        g.addEdge(5,6,7);
        g.addEdge(6,7,2);
        g.addEdge(8,9,5);
        g.addEdge(8,10,1);
        g.addEdge(9,10,3);
        g.addEdge(10,11,8);
        g.addEdge(12,13,0);

        /*g.calculatePath(1,10);
        g.calculatePath(11,7);
        g.calculatePath(11,12);*/

        g.addVertex(51);
        g.addVertex(52);
        g.addVertex(53);
        g.addVertex(54);
        g.addVertex(55);
        g.addVertex(56);
        g.addVertex(57);

        g.addEdge(51,52,3);
        g.addEdge(52,53,11);
        g.addEdge(53,54,1);
        g.addEdge(51,55,7);
        g.addEdge(55,56,1);
        g.addEdge(56,57,1);
        g.addEdge(57,54,1);

        g.calculatePath(51,54);
    }
}