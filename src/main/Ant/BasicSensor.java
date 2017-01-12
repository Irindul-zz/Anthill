package main.Ant;

import main.Mapping.Direction;
import main.Mapping.Map;
import main.Mapping.Position;

/**
 * Created by Irindul on 09/01/2017.
 */
public class BasicSensor implements Sense {

    double[] results;

    public BasicSensor() {
        results = new double[8];
    }

    @Override
    public boolean detectFood(Position pos, Map map) {

        return true;
    }

    @Override
    public void detectPheromones(Position pos, Map map) {
        int x = pos.getX();
        int y = pos.getY();

        Position north = new Position(x+1, y);
        Position northeast = new Position(x+1, y+1);
        Position east = new Position(x, y+1);
        Position southeast = new Position(x-1, y+1);
        Position south = new Position(x-1, y);
        Position southwest = new Position(x-1, y-1);
        Position west = new Position(x, y-1);
        Position northwest = new Position(x+1, y-1);

        results[Direction.NORTH.ordinal()] = map.getPheromoneQuantityAt(north);
        results[Direction.NORTHEAST.ordinal()] = map.getPheromoneQuantityAt(northeast);
        results[Direction.EAST.ordinal()] = map.getPheromoneQuantityAt(east);
        results[Direction.SOUTHEAST.ordinal()] = map.getPheromoneQuantityAt(southeast);
        results[Direction.SOUTH.ordinal()] = map.getPheromoneQuantityAt(south);
        results[Direction.SOUTHWEST.ordinal()] = map.getPheromoneQuantityAt(southwest);
        results[Direction.WEST.ordinal()] = map.getPheromoneQuantityAt(west);
        results[Direction.NORTHWEST.ordinal()] = map.getPheromoneQuantityAt(northwest);


    }

    @Override
    public double getResults(int i) {
        return results[i];
    }
}
