package main.Ant;

import main.Brain.Brain;
import main.Mapping.Direction;

/**
 * Created by Irindul on 05/01/2017.
 */
public class BrainyAnt extends Ant implements Brain {

    protected Proba proba;
    protected Mind mind;

    public BrainyAnt(Direction direction) {
        super(direction);
        proba = new Proba();
        mind = new Mind();
        initializeProba();
    }


    private void initializeProba(){
        proba.initialize();
    }

    public BrainyAnt() {
        this(Direction.NORTH);
    }

    @Override
    public void detectEnvironement() {

    }

    @Override
    public void processProba() {
        initializeProba();

        if (hasFood){ //If we have food, then we have to go back.


            Direction toGo = mind.rollBack(); // We get the next direction.
            proba.makeSure(toGo); // We tweak the probas so that this outcome is certain.

        } else {

            processWithoutPheromones();


            //Process proba with pheromones.
        }



    }

    @Override
    public void executeProba() {
        proba.computesFrequencies(); // We calculate the cumulated frequencies
        int dir = proba.randomWithProba();

        Direction direction = Direction.values()[dir];  //We deduce a direction from the int value.
        if(!hasFood) //If we do not carry food the we must keep track of the way.
            mind.keepTrack(direction); // TODO: 05/01/2017 Change moveTo to bool and if moveTo suceed, then we keep track
        this.moveTo(direction); //We move.

    }

    private void processWithoutPheromones(){
        int coefs[] = {0, 5, 10, 20, 50, 20, 10 ,5};
        int sum = 0;
        for (int coef: coefs) {
            sum += coef;
        }

        int start = Direction.reverse(this.direction).ordinal();
        int stop = (start + 7) % 8 ; //We use modulo here because of the cycle of the cardinal points.
        int i = start;
        int j = 0;
        while (i != stop){

            proba.setProba(i, (double) coefs[j]/sum); //We set the probas corresponding to the current direction.
            i = (i + 1) % 8;
            j++;
        }
        proba.setProba(i, (double) coefs[j]/sum); //The loop stop one item before so we need to do it one more time.
    }

    public Proba getProba(){
        return proba;
    }
}
