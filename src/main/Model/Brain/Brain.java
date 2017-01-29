package main.Model.Brain;

import main.Model.Ant.Ant;
import main.Model.Mapping.Direction;


public interface Brain {

    void processProba(Ant ant);
    Direction executeProba(Ant ant);
}
