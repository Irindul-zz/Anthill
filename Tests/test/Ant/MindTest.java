package test.Ant;

import main.Model.Ant.Mind;
import main.Model.Mapping.Direction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Irindul on 06/01/2017.
 */
public class MindTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void keepTrack() throws Exception {
        Direction d = Direction.NORTH;
        Direction d2 = Direction.NORTHEAST;

        Mind mind = new Mind();

        mind.keepTrack(d);
        mind.keepTrack(d2);

        assertEquals(mind.rollBack(), Direction.reverse(d2));
        assertEquals(mind.rollBack(), Direction.reverse(d));

    }

    @Test
    public void rollBack() throws Exception {

    }

}