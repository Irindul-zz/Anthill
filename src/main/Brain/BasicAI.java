package main.Brain;

import main.Ant.Ant;
import main.Ant.Proba;
import main.Mapping.Direction;
import main.Ant.Sense;

/**
 * Created by Irindul on 24/01/2017.
 */
public class BasicAI implements Brain {

    protected Proba proba;
    protected Memory mind;


    @Override
    public void detectEnvironement() {

    }

    @Override
    public void processProba(Ant ant) {
        proba.initialize();

        if (ant.getHasFood()){ //If we have food, then we have to go back.


            Direction toGo = mind.rollBack(); // We get the next direction.
            proba.makeSure(toGo); // We tweak the probas so that this outcome is certain.

        } else {

            processPheromones(ant);

        }



    }


    protected void processPheromones(Ant ant){
        int coefs[] = {0, 5, 10, 20, 50, 20, 10 ,5};
        int sum = 0;
        double phero;
        boolean obstacle;
        Sense sensor = ant.getSensor();
        for (int coef: coefs) {
            sum += coef;
        }

        int start = Direction.reverse(ant.getDirection()).ordinal();
        int stop = (start + 7) % 8 ; //We use modulo here because of the cycle of the cardinal points.
        int i = start;
        int j = 0;
        while (i != stop){
            phero = sensor.getResults(i);
            obstacle = sensor.getObstacle(i);
            if(!obstacle)
                proba.setProba(i, (coefs[j] + phero) /sum); //We set the probas corresponding to the current direction.
            else
                proba.setProba(i, 0);
            i = (i + 1) % 8;
            j++;
        }
        phero = sensor.getResults(i);
        obstacle = sensor.getObstacle(i);
        if(!obstacle)
            proba.setProba(i, (coefs[j] + phero) /sum); //The loop stop one item before so we need to do it one more time.
        else
            proba.setProba(i, 0);
    }


    @Override
    public void executeProba(Ant ant) {
        proba.computesFrequencies(); // We calculate the cumulated frequencies
        int dir = proba.randomWithProba();

        Direction direction = Direction.values()[dir];  //We deduce a direction from the int value.
        if(!ant.getHasFood()) //If we do not carry food the we must keep track of the way.
            mind.keepTrack(direction); // TODO: 05/01/2017 Change moveTo to bool and if moveTo suceed, then we keep track
        ant.moveTo(direction); //We move.
    }
}
