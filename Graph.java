import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
    List<Vertex> vertices = new ArrayList<Vertex>();
    List<Edge> edges = new ArrayList<Edge>();

    public List<Edge> minimalTreeKruskal(){
        List<Edge> smallestTree = null;
        List<Edge> v1connect = null, v2connect = null;
        List<Edge> unchecked = new ArrayList<Edge>(edges);
        List<List<Edge>> forest = new ArrayList<List<Edge>>();
        Collections.sort(unchecked, new EdgeComparator());
        Edge smol = unchecked.get(0);

        for(Edge e : unchecked) {
            System.out.println("Kolejna tura:");
            System.out.println(e.weight+" "+e.v1.id+" "+e.v2.id);

            if(!forest.isEmpty()) {
                if(e.v1 != e.v2){
                    if(calculatePath(smol.v1.id, e.v1.id) < Integer.MAX_VALUE){
                        for (Edge i : getEdges(e.v1.id)) {
                            System.out.println(v1connect);
                            if (i.tree != null) {
                                v1connect = i.tree;
                                System.out.println("Wykryto drzewo na edgu "+i.v1.id+" "+i.v2.id);
                                break;
                            }
                        }
                        for (Edge i : getEdges(e.v2.id)) {
                            if (i.tree != null) {
                                v2connect = i.tree;
                                System.out.println("Wykryto drzewo na edgu "+i.v1.id+" "+i.v2.id);
                                break;
                            }
                        }
                        System.out.println(v1connect);
                        System.out.println(v2connect);

                        if (v1connect == null && v2connect != null) {//only v2connect isn't null
                            e.tree = v2connect;
                            v2connect.add(e);
                            System.out.println("v2 należy do drzewa");
                        } else if (v2connect == null && v1connect != null) {//only v1connect isn't null
                            e.tree = v1connect;
                            v1connect.add(e);
                            System.out.println("v1 należy do drzewa");
                        } else if (v1connect != null && v2connect != null && v1connect != v2connect) {//neither is null nor same
                            v1connect.addAll(v2connect);
                            v1connect.add(e);
                            forest.remove(v2connect);
                            System.out.println("v1 i v2 należą do odrębnych drzew");
                        } else if(v1connect == null && v2connect == null){//both are null
                            forest.add(new ArrayList<Edge>());
                            forest.get(forest.size() - 1).add(e);
                            System.out.println("v1 i v2 nie należą do drzew");
                        } else if(v1connect != null && v2connect != null && v1connect == v2connect){
                            System.out.println("v1 i v2 należą do tego samego drzewa");
                        }
                    }
                }
            } else {
                forest.add(new ArrayList<Edge>());
                forest.get(0).add(unchecked.get(0));
                e.tree = forest.get(0);
            }

            for(List<Edge> dr : forest){
                System.out.println("Drzewo_____________________");
                for(Edge eee : dr){
                    System.out.println(eee.weight+" "+eee.v1.id+" "+eee.v2.id);
                }
                System.out.println("___________________________");
            }

        }

        for(List<Edge> le : forest){
            if(le.contains(smol)){
                smallestTree = le;
                break;
            }
        }

        int treeWeight = 0;
        for (Edge e : smallestTree){
            treeWeight += e.weight;
        }

        System.out.println("Waga najmniejszego drzewa rozpinającego to " + treeWeight);

        return smallestTree;
    }

    public List<Edge> minimalTreePrim(){
        return null;
    }

    public int calculatePath(int p1, int p2){
        if(getVertex(p1) != null && getVertex(p2) != null){
            for(Vertex i : vertices){
                i.path = Integer.MAX_VALUE;
            }

            List<Vertex> unchecked = new ArrayList<Vertex>(vertices);
            List<Vertex> checked = new ArrayList<Vertex>();
            List<Edge> connections = new ArrayList<Edge>();

            Vertex current = getVertex(p1);
            Vertex next = null;
            int lightestEdge = Integer.MAX_VALUE;

            while(!unchecked.isEmpty()){
                if(current.id == p1){
                    current.path = 0;
                }
                checked.add(current);
                unchecked.remove(current);
                connections = getEdges(current.id);
                for(Edge i : connections){
                    if(i.v1 == current){
                        if(!checked.contains(i)){
                            if(i.weight < lightestEdge){
                                lightestEdge = i.weight;
                                next = i.v2;
                            }
                        }
                        if(current.path+i.weight < i.v2.path){
                            i.v2.path = current.path+i.weight;
                            //System.out.println("Ścieżka do " + i.v2.id + " wynosi " + i.v2.path);
                        }
                    }
                    else{
                        if(!checked.contains(i)){
                            if(i.weight < lightestEdge){
                                lightestEdge = i.weight;
                                next = i.v1;
                            }
                        }
                        if(current.path+i.weight < i.v1.path){
                            i.v1.path = current.path+i.weight;
                            //System.out.println("Ścieżka do " + i.v1.id + " wynosi " + i.v1.path);
                        }
                    }
                }
                if(next == null || next == current){
                    Vertex potentialCurrent = null;
                    for(Vertex i : unchecked){
                        if(potentialCurrent == null){
                            potentialCurrent = i;
                        }
                        else if(i.path < potentialCurrent.path){
                            potentialCurrent = i;
                        }
                    }
                    if(potentialCurrent.path == Integer.MAX_VALUE){
                        break;
                    }
                    else{
                        current = potentialCurrent;
                    }
                }
                else{
                    next.previous = current;
                    current = next;
                }
            }

            if(getVertex(p2).path == Integer.MAX_VALUE){
                System.out.println("∞");
            }
            else{
                System.out.println(getVertex(p2).path);
            }
            return getVertex(p2).path;
        }
        else{
            System.out.println("Punkty nie istnieją");
            return -1;
        }
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

    static class EdgeComparator implements java.util.Comparator<Edge> {
        @Override
        public int compare(Edge e1, Edge e2) {
            return Integer.compare(e1.weight, e2.weight);
        }
    }
}
