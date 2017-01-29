package main.Model.Ant;

import main.Model.Collections.FoodSupplyCol;
import main.Model.Collections.PheromoneCol;
import main.Model.Element.Cell;
import main.Model.Element.FoodSupply;
import main.Model.Element.Pheromone;
import main.Model.Mapping.Direction;
import main.Model.Mapping.Position;

public class BasicSensor implements Sense {

    //Contains pheromone quantity on the 8 adjacent cells
    private double[] results;

    //Contains obstacles on the 8 adjacent cells
    protected boolean[] obstacles;
    private Direction direction;

    //Contains foodsupplies on the 8 adjacent cells
    private boolean[] foodsupply;

    public BasicSensor() {
        results = new double[8];
        obstacles = new boolean[8];
        foodsupply = new boolean[8];
    }

    @Override
    public boolean detectFood(Position pos, FoodSupplyCol f) {
        boolean ret = false;
        if (f.size() != 0) {
            if (f.getFoodSupplyAt(pos) != null) {
                if (f.getFoodSupplyAt(pos).getQuantity() > 0) ret =  true; //Return true if there is food on pos.
            }
        }
        int x = pos.getX();
        int y = pos.getY();
        //All 8 directions possibles
        Position north = new Position(x, y-1);
        Position northeast = new Position(x+1, y-1);
        Position east = new Position(x+1, y);
        Position southeast = new Position(x+1, y+1);
        Position south = new Position(x, y+1);
        Position southwest = new Position(x-1, y+1);
        Position west = new Position(x-1, y);
        Position northwest = new Position(x-1, y-1);

        //By default there is no food supply arround pos
        for (int i = 0; i < 8; i++) {
            foodsupply[i] = false;
        }

        for (FoodSupply fo: f.getSupplies()) { //We check foreach food supply if it's around the position
            if(fo.getPosition().equals(north)){
                foodsupply[Direction.NORTH.ordinal()] = true;
            }
            if(fo.getPosition().equals(east)){
                foodsupply[Direction.EAST.ordinal()] = true;
            }
            if(fo.getPosition().equals(northeast)){
                foodsupply[Direction.NORTHEAST.ordinal()] = true;
            }
            if(fo.getPosition().equals(southeast)){
                foodsupply[Direction.SOUTHEAST.ordinal()] = true;
            }
            if(fo.getPosition().equals(south)){
                foodsupply[Direction.SOUTH.ordinal()] = true;
            }
            if(fo.getPosition().equals(southwest)){
                foodsupply[Direction.SOUTHWEST.ordinal()] = true;
            }
            if(fo.getPosition().equals(west)){
                foodsupply[Direction.WEST.ordinal()] = true;
            }
            if(fo.getPosition().equals(northwest)){
                foodsupply[Direction.NORTHWEST.ordinal()] = true;
            }
        }

        return ret;
    }


    @Override
    public void detectPheromones(Position pos, PheromoneCol p) {
        int x = pos.getX();
        int y = pos.getY();

        //The 8 directions possible
        Position north = new Position(x, y-1);
        Position northeast = new Position(x+1, y-1);
        Position east = new Position(x+1, y);
        Position southeast = new Position(x+1, y+1);
        Position south = new Position(x, y+1);
        Position southwest = new Position(x-1, y+1);
        Position west = new Position(x-1, y);
        Position northwest = new Position(x-1, y-1);

        //This is a coefficient to make the pheromones count more or less in the proba.
        int coef = 100;

        results[Direction.NORTH.ordinal()] = p.getPheromoneQuantityAt(north) * coef;
        results[Direction.NORTHEAST.ordinal()] = p.getPheromoneQuantityAt(northeast) * coef;
        results[Direction.EAST.ordinal()] = p.getPheromoneQuantityAt(east) * coef;
        results[Direction.SOUTHEAST.ordinal()] = p.getPheromoneQuantityAt(southeast) * coef;
        results[Direction.SOUTH.ordinal()] = p.getPheromoneQuantityAt(south) * coef;
        results[Direction.SOUTHWEST.ordinal()] = p.getPheromoneQuantityAt(southwest) * coef;
        results[Direction.WEST.ordinal()] = p.getPheromoneQuantityAt(west) * coef;
        results[Direction.NORTHWEST.ordinal()] = p.getPheromoneQuantityAt(northwest) * coef;

        Pheromone ph = p.get(pos);
        if( ph != null) { //If we are on a pheromone
            direction = ph.getDirection(); //We set the direction to the one of the pheromone
        }
        else {
            direction = null;
        }


    }

    @Override
    public double getResults(int i) {
        return results[i];
    }

    @Override
    public void detectObstacles(Position pos, Cell[] cells) {
        for (int i = 0; i<8; i++) {

            obstacles[i] = false;
        }
    }

    @Override
    public boolean getObstacle(int i) {
        return obstacles[i];
    }

    @Override
    public Direction getPheromoneDir() {
        return direction;
    }

    @Override
    public boolean getFoodsuply(int i) {
        return foodsupply[i];
    }
}
