package main.Graph;

import main.Mapping.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irindul on 27/01/2017.
 */
public class Graph {

    private List<Node> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public List<Node> neighbours(Node n){
        List<Node> neighbors = new ArrayList<>();

        for (Edge e: n.getEdges()) {
            neighbors.add(e.getNext());
        }

        return neighbors;

    }

    public Node nodeFromPosition(Position pos){
        for (Node n: nodes) {
            if(n.getPosition().equals(pos))
                return n;
        }

        return null;
    }
}
