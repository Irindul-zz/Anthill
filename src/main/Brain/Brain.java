package main.Brain;

import main.Ant.Ant;
import main.Mapping.Direction;
import main.Mapping.Map;

/**
 * Created by Irindul on 25/12/2016.
 */
public interface Brain {

    void detectEnvironement();
    void processProba(Ant ant);
    Direction executeProba(Ant ant);
}
