package main.Brain;

import main.Ant.Ant;
import main.Ant.Proba;
import main.Ant.Sense;
import main.Mapping.Direction;

public class BasicAI implements Brain {

    protected Proba proba;


    public BasicAI() {
        proba = new Proba();

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

        //The coefficient of the probabilities
        int coefs[] = {0, 5, 10, 20, 50, 20, 10 ,5};
        int sum = 0;
        int sumObs = 0;
        int nbObs = 0;
        int sumPhero = 0;
        double phero;
        boolean obstacle;
        boolean foodsupply;
        Sense sensor = ant.getSensor();
        for (int coef: coefs) { //We initialise the coeficient sum
            sum += coef;
        }
        for (int i = 0; i < 8; i++) {
            sumPhero += (int)sensor.getResults(i);
        }

        //We reverse the direction to start the distribution of the coeficients
        int start = Direction.reverse(ant.getDirection()).ordinal();
        proba.setProba(start, 0); //We can't go back.

        start = (start + 1) % 8; //We start one after (we already checked this one)


        int stop = (start +7) % 8 ; //We stop seven later

        //We use modulo here because of the cycle of the cardinal points.

        int i = start;
        int j = 1;

        while (i != stop){
            phero = sensor.getResults(i); //Getting sensor information
            obstacle = sensor.getObstacle(i);
            foodsupply = sensor.getFoodsuply(i);

            if(foodsupply){
                proba.makeSure(Direction.values()[i]); //If there is a food supply nearby, we need to go there.
                break;
            }
            if(!obstacle) {
                proba.setProba(i, (coefs[j] + phero) / (sum + sumPhero)); //We set the probas corresponding to the current direction.
            }
            else {
                sumObs += (coefs[j] + phero) / (sum + sumPhero); //We store the proba that should have been set
                nbObs++; //We count the number of obstacles
                proba.setProba(i, 0);
            }
            i = (i + 1) % 8; //We increment the cycle
            j++;
        }


        if(nbObs > 0){
            int nbNonObs = 7 - nbObs;
            sumObs /= nbNonObs; //We redivide the sum
            for (int k = 0; k < 8; k++) {
                obstacle = sensor.getObstacle(k);
                if(!obstacle){
                    proba.setProba(k, proba.getProbas()[k] + sumObs ); //We redistribute the proba if there was an obstacle
                }
            }
        }


    }





    @Override
    public Direction executeProba(Ant ant) {
        proba.computesFrequencies(); // We calculate the cumulated frequencies
        int dir = proba.randomWithProba();
        Direction direction;

        if(dir != -1) {
             direction = Direction.values()[dir];  //We deduce a direction from the int value.
        } else {
            direction =ant.getDirection(); //If there was a problem, we keep the same direction.
        }


        return direction;

    }
}
