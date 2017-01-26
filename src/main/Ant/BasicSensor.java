package main.Ant;

import main.Collections.FoodSupplyCol;
import main.Collections.PheromoneCol;
import main.Element.Cell;
import main.Mapping.Direction;
import main.Mapping.Map;
import main.Mapping.Position;

public class BasicSensor implements Sense {

    protected double[] results;
    protected boolean[] obstacles;

    public BasicSensor() {
        results = new double[8];
        obstacles = new boolean[8];
    }

    @Override
    public boolean detectFood(Position pos, FoodSupplyCol f) {
        if (f.size() != 0) {
            if (f.getFoodSupplyAt(pos) != null) {
                if (f.getFoodSupplyAt(pos).getQuantity() > 0) return true;
            }
        }
        return false;
    }


    @Override
    public void detectPheromones(Position pos, PheromoneCol p) {
        int x = pos.getX();
        int y = pos.getY();

        Position north = new Position(x, y-1);
        Position northeast = new Position(x+1, y-1);
        Position east = new Position(x+1, y);
        Position southeast = new Position(x+1, y+1);
        Position south = new Position(x, y+1);
        Position southwest = new Position(x-1, y+1);
        Position west = new Position(x-1, y);
        Position northwest = new Position(x-1, y-1);

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

    @Override
    public void detectObstacles(Position pos, Cell[] cells) {
        for (int i =0; i<8; i++ ) {
           obstacles[i] = cells[i].isWalkable();
        }

    }

    @Override
    public boolean getObstacle(int i) {
        return obstacles[i];
    }
}
