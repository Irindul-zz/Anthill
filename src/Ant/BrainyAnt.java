package Ant;

import Brain.Brain;
import Mapping.Direction;

/**
 * Created by Irindul on 05/01/2017.
 */
public class BrainyAnt extends Ant implements Brain {

    Proba proba;
    Mind mind;

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
        if(!hasFood)
            mind.keepTrack(direction); // TODO: 05/01/2017 Change moveTo to bool and if moveTo suceed, then we keep track
        this.moveTo(direction);
    }
}
