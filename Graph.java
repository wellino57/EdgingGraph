import java.util.ArrayList;
import java.util.List;

public class Graph {
    List<Vertex> vertices = new ArrayList<Vertex>();
    List<Edge> edges = new ArrayList<Edge>();

    public int calculatePath(int p1, int p2){
        List<Edge> unchecked = getEdges(p1);

    }

    public Vertex getVertex(int id){
        if(vertices.size() > 0){
            for(int i =0;i< vertices.size();i++){
                if(vertices.get(i).id == id) {
                    return vertices.get(i);
                }
            }
        }
        return null;
    }

    public void addVertex(int id){
        if(getVertex(id) == null){
            Vertex v = new Vertex(id);
            vertices.add(v);
            System.out.println("Dodano punkt " + id);
        }
        else{
            System.out.println("Punkt o id " + id + " już istnieje");
        }
    }

    public void removeVertex(int id){
        if(getVertex(id) != null){
            List<Edge> removed = new ArrayList<Edge>();
            for(int j=0;j<edges.size();j++){
                if(edges.get(j).v1.id == id || edges.get(j).v2.id == id){
                    removed.add(getEdge(edges.get(j).v1.id, edges.get(j).v2.id));
                }
            }
            for(int k=0;k<removed.size();k++){
                removeEdge(removed.get(k).v1.id, removed.get(k).v2.id);
            }

            for(int i =0;i< vertices.size();i++) {
                if (vertices.get(i).id == id) {
                    System.out.println("Usunięto punkt " + id);
                    vertices.remove(i);
                }
            }
        }
        else{
            System.out.println("Nie ma takiego punktu");
        }
    }

    public Edge getEdge(int p1, int p2){
        if(edges.size() > 0){
            for(int i =0;i< edges.size();i++){
                if(edges.get(i).v1.id == p1 && edges.get(i).v2.id == p2 || edges.get(i).v1.id == p2 && edges.get(i).v2.id == p1) {
                    return edges.get(i);
                }
            }
        }
        return null;
    }

    public List<Edge> getEdges(int id){
        Vertex v = getVertex(id);
        List<Edge> connectedEdges = new ArrayList<Edge>();

        for(int i=0;i< edges.size();i++){
            if(edges.get(i).v1.id == id || edges.get(i).v2.id == id){
                connectedEdges.add(edges.get(i));
            }
        }

        return connectedEdges;
    }

    public void addEdge(int p1, int p2, int wei){
        if(getVertex(p1) != null && getVertex(p2) != null){
            if(getEdge(p1, p2) == null){
                Vertex v1 = getVertex(p1);
                Vertex v2 = getVertex(p2);
                Edge e = new Edge(v1, v2, wei);
                edges.add(e);
                System.out.println("Dodano krawędź między " + p1 + " a " + p2 + " o wadze " + wei);
            }
        }
        else{
            System.out.println("Nie ma punktu " + p1 + " lub punktu " + p2);
        }
    }

    public void removeEdge(int p1, int p2){
        if(edges.size() > 0){
            if(p1 != p2){
                if(getVertex(p1) != null && getVertex(p2) != null){
                    if(getEdge(p1,p2) != null){
                        for(int i =0;i< edges.size();i++){
                            if(edges.get(i).v1.id == p1 && edges.get(i).v2.id == p2 || edges.get(i).v1.id == p2 && edges.get(i).v2.id == p1) {
                                System.out.println("Usunięto krawędź między " + edges.get(i).v1.id + " a " + edges.get(i).v2.id + " o wadze " + edges.get(i).weight);
                                edges.remove(i);
                            }
                        }
                    }
                    else{
                        System.out.println("Nie ma takiej krawędzi");
                    }
                }
                else{
                    System.out.println("Nie ma punktu " + p1 + " lub punktu " + p2);
                }
            }
            else{
                System.out.println("Krwędź nie może istnieć, bo łączy punkt sam z sobą");
            }
        }
        else{
            System.out.println("Zbiór krawędzi jest pusty");
        }
    }
}
