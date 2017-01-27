package main.Graph;

import main.Mapping.Position;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Irindul on 27/01/2017.
 */
public class Dijkstra {

    private static Graph graph;

    public Dijkstra() {
        graph = new Graph();
    }

    public static void search(Position start, Position goal){
        LinkedList<Node> frontier = new LinkedList<>();
        Node nodeStart = graph.nodeFromPosition(start);
        Node nodeGoal = graph.nodeFromPosition(goal);
        frontier.add(nodeStart);
        Set<Edge> cameFrom = new HashSet<>();

        while(! frontier.isEmpty()){
            Node current = frontier.removeFirst();

            if(current == nodeGoal)
                break;

            for(Node next: graph.neighbours(current)){

            }
        }



    }
}
