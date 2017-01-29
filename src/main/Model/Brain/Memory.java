package main.Model.Brain;

import main.Model.Mapping.Direction;


public interface Memory {

    void keepTrack(Direction d);
    Direction rollBack();
    void setKeeptrack(boolean keeptrack);
    void empty();
}
