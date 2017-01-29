package main.Model.Graph;

import main.Model.Mapping.Position;

import java.util.*;

public class Pathfinding {

    public static Graph graph;

    public Pathfinding() {
        graph = new Graph();
    }

    public static List<Node> search(Position start, Position goal){
        LinkedList<Node> frontier = new LinkedList<>(); //Cells to explore (Queue)
        Node nodeStart = graph.nodeFromPosition(start); //Starting point converted to node
        Node nodeGoal = graph.nodeFromPosition(goal); //Same with goal
        frontier.add(nodeStart); //We first start with the start
        HashMap<Node, Node> cameFrom = new HashMap<>(); //Will link node so that we can trace the path
        cameFrom.put(nodeStart, null); //We came from nowhere on the starting point
        while(! frontier.isEmpty()){
            Node current = frontier.removeFirst(); //We get the first item of the queue
            if(current == nodeGoal)
                break; //We stop if we are at the goal

            for(Node next: graph.neighbours(current)){ //For each neigbor node
                if( ! cameFrom.containsKey(next)){ //If we don't have a link with this
                    frontier.add(next); //We must explore it
                    cameFrom.put(next, current); //We link
                }
            }


        }


        Node current = nodeGoal;
        List<Node> path = new ArrayList<>();
        //Now we can reconstruct the path
        path.add(current);
        int i = 0;
        while (current != nodeStart &&  i < graph.size()){
            current = cameFrom.get(current); //We follow the links
            path.add(current);
            i++;
        }

        return path;

    }

    public static boolean isThereAPath(Position start, Position goal){
        List<Node> path = search(start, goal);

       if(path.get(path.size()-1) == null)
           return false;
       else if(path.get(path.size()-1).getPosition().equals(start))
            return true;
           //The last position of the past must equal the starting point so that there is a path.

        return false;
    }
}
