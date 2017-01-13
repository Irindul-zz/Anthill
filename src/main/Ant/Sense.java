package main.Ant;

import main.Collections.PheromoneCol;
import main.Mapping.Direction;
import main.Mapping.Map;
import main.Mapping.Position;

/**
 * Created by Irindul on 09/01/2017.
 */
public interface Sense {

<<<<<<< HEAD
    boolean detectFood(Position pos, Map map);
    void detectPheromones(Position pos, Map map);
=======
    void detectFood(Position pos, Map map);
    void detectPheromones(Position pos, PheromoneCol p);
>>>>>>> 246b5b64dd973449674bfb16bf5f3fe672031123
    double getResults(int i);
    //maybe detectObstacles(Position pos, Map map);

}
