package main.Ant;

import main.Brain.Brain;
import main.Brain.Memory;
import main.Mapping.Direction;

/**
 * Created by Irindul on 05/01/2017.
 */
public class BrainyAnt extends Ant {

    Brain brain;


    public BrainyAnt(Direction direction, Brain brain) {
        this.direction = direction;
        this.brain = brain;
    }

    public Brain getBrain() {
        return brain;
    }
}
