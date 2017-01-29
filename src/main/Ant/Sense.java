package main.Ant;

import main.Collections.FoodSupplyCol;
import main.Collections.PheromoneCol;
import main.Element.Cell;
import main.Mapping.Direction;
import main.Mapping.Position;


public interface Sense {

    boolean detectFood(Position pos, FoodSupplyCol f);
    void detectPheromones(Position pos, PheromoneCol p);
    double getResults(int i);
    void detectObstacles(Position pos, Cell[] cells);
    boolean getObstacle(int i);
    Direction getPheromoneDir();

    boolean getFoodsuply(int i);

}
