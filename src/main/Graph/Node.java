package main.Graph;

import main.Mapping.Position;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Edge> edges;
    private Position position;

    public Node(Position position) {
        this.position = position;
        edges = new ArrayList<>();
    }

    public void link(Node n, int w){
        edges.add(new Edge(this, n, w));
    }

    public List<Edge> getEdges(){
        return edges;
    }

    public Position getPosition() {
        return position;
    }
}
