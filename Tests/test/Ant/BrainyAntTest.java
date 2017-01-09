package test.Ant;

import main.Ant.BrainyAnt;
import main.Mapping.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Irindul on 09/01/2017.
 */
public class BrainyAntTest {
    BrainyAnt ant;
    double delta;

    @Before
    public void setUp() throws Exception {
        ant = new BrainyAnt();
        delta = 0.1; //High delta because we will have rounded values.
    }

    @Test
    public void detectEnvironement() throws Exception {

    }

    @Test
    public void processProba() throws Exception {
        ant = new BrainyAnt(Direction.EAST);
        ant.processProba();

        assertEquals("East proba must be 50", 0.5, ant.getProba().getProbas()[Direction.EAST.ordinal()], delta);
        assertEquals("SouthEast proba must be 20", 0.2, ant.getProba().getProbas()[Direction.SOUTHEAST.ordinal()], delta);
        assertEquals("South proba must be 10", 0.1, ant.getProba().getProbas()[Direction.SOUTH.ordinal()], delta);
        assertEquals("SouthWest proba must be 5", 0.05, ant.getProba().getProbas()[Direction.SOUTHWEST.ordinal()], delta);
        assertEquals("West proba must be 0", (double)0, ant.getProba().getProbas()[Direction.WEST.ordinal()], delta);
        assertEquals("NorthWest proba must be 5", 0.05, ant.getProba().getProbas()[Direction.NORTHWEST.ordinal()], delta);
        assertEquals("North proba must be 10", 0.1, ant.getProba().getProbas()[Direction.NORTH.ordinal()], delta);
        assertEquals("NorthEast proba must be 20", 0.2, ant.getProba().getProbas()[Direction.NORTHEAST.ordinal()], delta);

    }

}