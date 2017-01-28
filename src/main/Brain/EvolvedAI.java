package main.Brain;

import main.Ant.Ant;
import main.Graph.Dijkstra;
import main.Graph.Node;
import main.Mapping.Direction;
import main.Mapping.Position;

import java.util.List;

/**
 * Created by Irindul on 28/01/2017.
 */
public class EvolvedAI extends BasicAI {

    @Override
    public void processProba(Ant ant) {
        super.processProba(ant);
        ant.getMind().setKeeptrack(false);
        //TODO see how I can put Dikstra to computes the frequency here without any outside call
        //TODO Bad way : Dijkstra at every moment I'm goiing back, working but not good.
    }


    private void dijkstra(Ant ant){
        List<Node> pathNode = Dijkstra.search(ant.getPosition(), ant.getAnhillPosition());
        Position current = ant.getPosition();

        while( ! pathNode.isEmpty()){

        }
    }

    private Direction deduceDirection(Position start, Position goal){

    }
}
