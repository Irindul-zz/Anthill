package main.Graph;

import main.Mapping.Position;

import java.util.HashSet;
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
        Frontier frontier = new Frontier();
        Node nodeStart = graph.nodeFromPosition(start);
        frontier.put(nodeStart);
        Set<Edge> cameFrom = new HashSet<>();




    }
}
