package main.Brain;

import main.Mapping.Direction;

/**
 * Created by Irindul on 05/01/2017.
 */
public interface Memory {

    void keepTrack(Direction d);
    Direction rollBack();
    void setKeeptrack(boolean keeptrack);
    void empty();
    boolean getKeeptrack();
}
