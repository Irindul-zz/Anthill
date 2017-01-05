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

        if (hasFood){

            Direction toGo = mind.rollBack();
            proba.reset();
            proba.setProba(toGo, 1);

        } else {

            int coefs[] = {0, 5, 10, 20, 50, 20, 10 ,5};
            int start = Direction.reverse(this.direction).ordinal();
            int stop = (start + 7) % 8;

            for (int i = start; i != stop; i= (i + 1) % 8){
                proba.setProba(i, coefs[i]);
            }

            //Process proba
        }



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
