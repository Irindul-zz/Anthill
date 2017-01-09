package test.Ant;

import main.Ant.Proba;
import main.Mapping.Direction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Irindul on 09/01/2017.
 */
public class ProbaTest {
    Proba proba;
    double delta;

    @Before
    public void setUp() throws Exception {
        proba = new Proba();
        proba.initialize();
        delta = 0.00001;
    }

    @Test
    public void initialize() throws Exception {
        for (int i = 0; i < 8; i++) {
            assertEquals("Proba must equal 1/8", (double)1/8, proba.getProbas()[i], delta);

        }

    }

    @Test
    public void computesFrequencies() throws Exception {
        proba.computesFrequencies();
        double test = 0;
        for (int i = 0; i < 8; i++) {
            test += (proba.getProbas()[i] * 100);
            assertEquals("CumFreq must equal 12.5",test, proba.getFreq()[i], delta);
        }

    }

    @Test
    public void setProba() throws Exception {
        Direction dir = Direction.NORTH;
        proba.setProba(dir, 0.5);
        assertEquals("Proba should be equal to the one set", 0.5, proba.getProbas()[dir.ordinal()], delta);

        proba.setProba(Direction.reverse(dir), 1);
        assertEquals("Proba should be equal to the one set", 1, proba.getProbas()[Direction.reverse(dir).ordinal()], delta);

    }


    @Test
    public void makeSure() throws Exception {
        Direction dir = Direction.NORTH;
        proba.makeSure(dir);
        int i = proba.randomWithProba();

        assertEquals("Direction must be the same", dir, Direction.values()[i]);
    }

    @Test
    public void reset() throws Exception {
        proba.reset();
        for (int i = 0; i < 8; i++) {
            assertEquals("Everything should be 0", (double) 0, proba.getProbas()[i], delta);
            assertEquals("Everything should be 0", (double) 0, proba.getFreq() [i], delta);
        }
    }


}