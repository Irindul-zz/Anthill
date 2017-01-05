package Ant;

import Brain.Brain;
import Mapping.Direction;

/**
 * Created by Irindul on 05/01/2017.
 */
public class BrainyAnt extends Ant implements Brain {

    Proba proba;

    public BrainyAnt(Direction direction) {
        super(direction);
        proba = new Proba();
        initializeProba();
    }


    private void initializeProba(){
        proba.initialize();
    }

    public BrainyAnt() {
        super();
        proba = new Proba();
    }

    @Override
    public void detectEnvironement() {

    }

    @Override
    public void processProba() {
        initializeProba();

    }

    @Override
    public void executeProba() {
        proba.computesFrequencies();
        int dir = proba.randomWithProba();

        Direction direction = Direction.values()[dir];

        this.moveTo(direction);
    }
}
