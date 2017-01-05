package Ant;

import Agent.Brain;
import Mapping.Direction;

/**
 * Created by Irindul on 05/01/2017.
 */
public class BrainyAnt extends Ant implements Brain {
    @Override
    public void detectEnvironement() {

    }

    @Override
    public void processProba() {

    }

    @Override
    public Direction executeProba() {
            return Direction.NORTH;
    }
}
