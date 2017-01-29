package main.Ant;

import main.Mapping.Direction;

import java.util.Random;

/**
 * Created by Irindul on 05/01/2017.
 */
public class Proba {

    private double probas[];
    private double cumFreq[];


    public Proba() {

        probas = new double[8];
        cumFreq = new double[8];

    }

    public void initialize(){
        for (int i = 0; i < 8; i++) {
            probas[i] = 0.125; //We initialize every proba at 1/8.
            cumFreq[i] = 0;
        }
    }

    public void computesFrequencies(){

        cumFreq[0] = probas[0]*100; //Probas will be converted in %
        for (int i = 1; i < 8;i++) {
            cumFreq[i] = cumFreq[i-1] + probas[i]*100;
        }
    }

    public void setProba(Direction d, double prob){
        int i = d.ordinal();

        setProba(i, prob);

    }

    public void setProba(int i, double prob){
        probas[i] = prob;

    }

    public void makeSure(Direction d){
        this.reset();
        setProba(d, 1);
        computesFrequencies();
    }

    public void reset(){
        for (int i = 0; i < 8; i++) {
            probas[i] = 0;
            cumFreq[i] = 0;
        }
    }

    public double[] getProbas(){
        return probas;
    }

    public double[] getFreq(){
        return cumFreq;
    }

    public int randomWithProba(){
        
        Random random = new Random();
        int min = 0;
        int max = 100;

        double rd = min + (max - min) * random.nextDouble(); //We have a random number in [min, max[.
        rd %= 100;

        int returnValue = 7;
        for (int i = 0; i < 8; i++) { //We go through every values of cumFreq, and if the random number is inferior to the freq, then it's the outcome.
            if( rd <= cumFreq[i]) {
                return i;
            }
        }
         return -1;

    }
}
