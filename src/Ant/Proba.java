package Ant;

import Mapping.Direction;

import java.util.Random;

/**
 * Created by Irindul on 05/01/2017.
 */
public class Proba {

    double probas[];
    double cumFreq[];


    public Proba() {

        probas = new double[8];
        cumFreq = new double[8];

    }

    public void initialize(){
        for (int i = 0; i < 8; i++) {
            probas[i] = 0.125;
            cumFreq[i] = 0;
        }
    }

    public void computesFrequencies(){

        cumFreq[0] = probas[0]*100; //Probas will be converted in %
        for (int i = 1; i < 8;i++) {
            cumFreq[i] = cumFreq[i-1] + probas[i]*100;
        }
    }

    public void setProba(Direction d, int prob){
        int i = d.ordinal();

        setProba(i, prob);

    }

    public void setProba(int i, int prob){
        probas[i] = prob;
    }

    public void reset(){
        for (int i = 0; i < 8; i++) {
            probas[i] = 0;
            cumFreq[i] = 0;
        }
    }

    public int randomWithProba(){

        Random random = new Random();
        int min = 0;
        int max = 100;

        double rd = min + (max - min) * random.nextDouble();
        rd %= 100;
        rd += 1; // rd is between 1 and 100
        int i;
        for (i = 0; i < 8; i++) {
            if( rd <= cumFreq[i])
                break;
        }

        return i;

    }
}
