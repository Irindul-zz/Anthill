package main.Brain;

import main.Ant.Ant;
import main.Mapping.Direction;


public interface Brain {

    void processProba(Ant ant);
    Direction executeProba(Ant ant);
}
