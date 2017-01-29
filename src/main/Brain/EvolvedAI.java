package main.Brain;

import main.Ant.Ant;
import main.Graph.Pathfinding;
import main.Graph.Node;
import main.Mapping.Direction;
import main.Mapping.Position;

import java.util.List;


public class EvolvedAI extends BasicAI {

    @Override
    public void processProba(Ant ant) {


        proba.initialize();

        if (ant.getHasFood()){ //If we have food, then we have to go back.
           if(ant.goBack()) //If we are turning around ( i.e we are at the food supply)
                dijkstra(ant); //We compute the shortest way back
            Direction toGo = ant.getMind().rollBack(); // We get the next direction.

            proba.makeSure(toGo); // We tweak the probas so that this outcome is certain.

        } else {

            processPheromones(ant); //We need to process the probabilities

        }


        ant.getMind().setKeeptrack(false); //We do not keep track for this AI because the way back is computed.
        ant.setGoBack(false); //We set this to false at each turn so we can see when it changes once.
    }


    private void dijkstra(Ant ant){
        //We compute the path
        List<Node> pathNode = Pathfinding.search(ant.getPosition(), ant.getAnhillPosition());
        Position current;
        Position toCheck;
        int i = 0;
        ant.getMind().empty();
        while(i < pathNode.size()-1){

            current = pathNode.get(i).getPosition();
            toCheck = pathNode.get(i+1).getPosition();

            //We deduce a direction to go with two directions
            Direction direction = deduceDirection(current, toCheck);

            ant.getMind().setKeeptrack(true);
            ant.getMind().keepTrack(direction);

            i++;
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
