package main.Ant;

import main.Collections.PheromoneCol;
import main.Mapping.Direction;
import main.Mapping.Map;
import main.Mapping.Position;

/**
 * Created by Irindul on 09/01/2017.
 */
public interface Sense {


    boolean detectFood(Position pos, Map map);
    void detectPheromones(Position pos, PheromoneCol p);
    double getResults(int i);
    //maybe detectObstacles(Position pos, Map map);

}
