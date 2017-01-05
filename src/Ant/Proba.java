package Ant;

/**
 * Created by Irindul on 05/01/2017.
 */
public class Proba {

    int probas[];
    int cumFreq[];

    public Proba() {

        probas = new int[8];
        cumFreq = new int[8];

    }

    public void initialize(){
        for(int a: probas){
            a = 0;
        }
    }

    public void calulateFrequencies(){

        cumFreq[0] = probas[0]*100; //Probas will be converted in %
        for (int i = 1; i < 8;i++) {
            cumFreq[i] = cumFreq[i-1] + probas[i]*100;
        }
    }
}
