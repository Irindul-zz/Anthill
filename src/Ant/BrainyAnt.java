package Ant;

import Brain.Brain;
import Mapping.Direction;

/**
 * Created by Irindul on 05/01/2017.
 */
public class BrainyAnt extends Ant implements Brain {

    private int[] probas;

    public BrainyAnt(Direction direction) {
        super(direction);
        probas = new int[8];
        initializeProba();
    }


    private void initializeProba(){

    }

    public BrainyAnt() {
        super();
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

    }
}
