package main.Model.Ant;

import main.Model.Collections.FoodSupplyCol;
import main.Model.Collections.PheromoneCol;
import main.Model.Element.Cell;
import main.Model.Mapping.Direction;
import main.Model.Mapping.Position;


public interface Sense {

    boolean detectFood(Position pos, FoodSupplyCol f);
    void detectPheromones(Position pos, PheromoneCol p);
    double getResults(int i);
    void detectObstacles(Position pos, Cell[] cells);
    boolean getObstacle(int i);
    Direction getPheromoneDir();

    boolean getFoodsuply(int i);

}
