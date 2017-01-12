package test;

import main.Collections.PheromoneCol;
import main.Element.Pheromone;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by krister on 12/01/17.
 */
public class PheromoneColTest {
    private PheromoneCol pheromoneCol;


    @Before
    public void setUp() throws Exception {
        pheromoneCol = new PheromoneCol();
    }

    @Test
    public void get() throws Exception {
        for(int i=0; i<pheromoneCol.size(); i++)
        {
            assertNull("Could not get index " + i + " from ArrayList", pheromoneCol.get(i));
        }
    }

    @Test
    public void add() throws Exception {
        Pheromone p = new Pheromone(10);
        pheromoneCol.add(p);
        assertEquals("Object could not be added to the end", p, pheromoneCol.get(pheromoneCol.size()-1));
    }

    @Test
    public void add1() throws Exception {
        Pheromone p;
        for(int i=0; i<10; i++)
        {
            p = new Pheromone(10);
            pheromoneCol.add(p, 0);
        }
    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void size() throws Exception {

    }

    @Test
    public void actualize() throws Exception {

    }

}