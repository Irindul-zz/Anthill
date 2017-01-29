package main.Model.Graph;

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

