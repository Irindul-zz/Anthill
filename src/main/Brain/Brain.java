package main.Brain;

import main.Ant.Ant;

/**
 * Created by Irindul on 25/12/2016.
 */
public interface Brain {

    void detectEnvironement();
    void processProba(Ant ant);
    void executeProba(Ant ant);
}
