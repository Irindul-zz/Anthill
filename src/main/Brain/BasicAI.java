package main.Brain;

import main.Ant.Ant;
import main.Ant.Mind;
import main.Ant.Proba;
import main.Mapping.Direction;
import main.Ant.Sense;
import main.Mapping.Map;
import main.Mapping.Position;

/**
 * Created by Irindul on 24/01/2017.
 */
public class BasicAI implements Brain {

    protected Proba proba;


    public BasicAI() {
        proba = new Proba();

    }

    @Override
    public void detectEnvironement() {

    }

    @Override
    public void processProba(Ant ant) {
        proba.initialize();

        if (ant.getHasFood()){ //If we have food, then we have to go back.
            ant.getMind().setKeeptrack(false);

            Direction toGo = ant.getMind().rollBack(); // We get the next direction.
            proba.makeSure(toGo); // We tweak the probas so that this outcome is certain.

        } else {
            ant.getMind().setKeeptrack(true);
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
        int stop = (start +7) % 8 ;
        //We use modulo here because of the cycle of the cardinal points.
        int i = start; // We start 1 after "start", to ignore the cell right behind the ant
        int j = 0;

        while (i != stop){
            phero = sensor.getResults(i);
            obstacle = sensor.getObstacle(i);

            if(obstacle) {
                proba.setProba(i, (coefs[j] + phero) /sum); //We set the probas corresponding to the current direction.

            }

            else {
                proba.setProba(i, 0);

            }
            i = (i + 1) % 8;
            j++;
        }
        phero = sensor.getResults(i);
        obstacle = sensor.getObstacle(i);
        if(obstacle)
            proba.setProba(i, (coefs[j] + phero) /sum); //The loop stop one item before so we need to do it one more time.
        else
            proba.setProba(i, 0);
    }





    @Override
    public Direction executeProba(Ant ant) {
        proba.computesFrequencies(); // We calculate the cumulated frequencies
        int dir = proba.randomWithProba();

        Direction direction = Direction.values()[dir];  //We deduce a direction from the int value.





        return direction;

    }
}
