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


        proba.initialize();

        if (ant.getHasFood()){ //If we have food, then we have to go back.

            dijkstra(ant);
            Direction toGo = ant.getMind().rollBack(); // We get the next direction.
            proba.makeSure(toGo); // We tweak the probas so that this outcome is certain.

        } else {

            processPheromones(ant);

        }


        ant.getMind().setKeeptrack(false);

        //TODO see how I can put Dikstra to computes the frequency here without any outside call
        //TODO Bad way : Dijkstra at every moment I'm goiing back, working but not good.
    }


    private void dijkstra(Ant ant){
        List<Node> pathNode = Dijkstra.search(ant.getPosition(), ant.getAnhillPosition());
        Position current = ant.getPosition();
        Position toCheck;
        int i = pathNode.size()-1;
        ant.getMind().empty();
        while(i >= 1){

            current = pathNode.get(i).getPosition();
            toCheck = pathNode.get(i-1).getPosition();

            Direction direction = deduceDirection(current, toCheck);
            ant.getMind().setKeeptrack(true);
            ant.getMind().keepTrack(Direction.reverse(direction));

            i--;
        }
    }

    private Direction deduceDirection(Position start, Position goal){
        int x = start.getX();
        int y = start.getY();

        int x2 = goal.getX();
        int y2 = goal.getY();

        boolean north = y - y2 > 0;
        boolean south = y - y2 < 0;

        boolean east = x - x2 < 0;
        boolean west = x - x2 > 0;

        if(north){ //If we need to go NORTH
            if(west){
                return Direction.NORTHWEST; //We check if it's northwest or northeast
            } else if(east){
                return Direction.NORTHEAST;
            }
            return Direction.NORTH;
        } else if(south){
            if(west){
                return Direction.SOUTHWEST;
            } else if(east){
                return Direction.SOUTHEAST;
            }

            return Direction.SOUTH;
        } else if(west){ //If we do not need to go north or south then we check east and west
            return Direction.WEST;
        } else if(east){
            return Direction.EAST;
        } else {
            return Direction.NORTH; //Default value
        }
    }
}
