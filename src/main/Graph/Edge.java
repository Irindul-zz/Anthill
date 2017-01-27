package main.Graph;

import main.Mapping.Position;

/**
 * Created by Irindul on 27/01/2017.
 */
public class Edge {

    private Node current;
    private Node next;
    private int weight;

    public Edge(Node current, Node next, int weight) {
        this.current = current;
        this.next = next;
        this.weight = weight;
    }

    public Node getCurrent() {
        return current;
    }

    public Node getNext() {
        return next;
    }

    public int getWeight() {
        return weight;
    }
}

