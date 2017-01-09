package main.Ant;

import main.Mapping.Map;
import main.Mapping.Position;

/**
 * Created by Irindul on 09/01/2017.
 */
public interface Sense {

    void detectFood(Position pos, Map map);
    void detectPheromones(Position pos, Map map);
    //maybe detectObstacles(Position pos, Map map);

}
