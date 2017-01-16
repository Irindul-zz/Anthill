package main.Ant;

import main.Collections.PheromoneCol;
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
    public void detectFood(Position pos, Map map) {

        return;
    }

    @Override
    public void detectPheromones(Position pos, PheromoneCol p) {
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

        results[Direction.NORTH.ordinal()] = p.getPheromoneQuantityAt(north);
        results[Direction.NORTHEAST.ordinal()] = p.getPheromoneQuantityAt(northeast);
        results[Direction.EAST.ordinal()] = p.getPheromoneQuantityAt(east);
        results[Direction.SOUTHEAST.ordinal()] = p.getPheromoneQuantityAt(southeast);
        results[Direction.SOUTH.ordinal()] = p.getPheromoneQuantityAt(south);
        results[Direction.SOUTHWEST.ordinal()] = p.getPheromoneQuantityAt(southwest);
        results[Direction.WEST.ordinal()] = p.getPheromoneQuantityAt(west);
        results[Direction.NORTHWEST.ordinal()] = p.getPheromoneQuantityAt(northwest);


    }

    @Override
    public double getResults(int i) {
        return results[i];
    }
}
