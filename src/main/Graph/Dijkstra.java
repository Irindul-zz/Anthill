package main.Graph;

import main.Mapping.Position;

import java.util.*;

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
        HashMap<Node, Node> cameFrom = new HashMap<>();
        cameFrom.put(nodeStart, null);
        while(! frontier.isEmpty()){
            Node current = frontier.removeFirst();

            if(current == nodeGoal)
                break;

            for(Node next: graph.neighbours(current)){ // TODO: 27/01/2017 change with real Dijkstra
                if( ! cameFrom.containsKey(next)){
                    frontier.add(next);
                    cameFrom.put(next, current);
                }
            }
        }


        Node current = nodeGoal;
        List<Node> path = new ArrayList<>();

        path.add(current);
        while (current != nodeStart){
            current = cameFrom.get(current);
            path.add(current);
        }
        //path.add(nodeStart);

        //TODO convert Node path into direction path => rollBack



    }
}
