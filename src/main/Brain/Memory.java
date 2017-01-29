package main.Brain;

import main.Mapping.Direction;


public interface Memory {

    void keepTrack(Direction d);
    Direction rollBack();
    void setKeeptrack(boolean keeptrack);
    void empty();
}
