package Element;

import Mapping.Position;

/**
 * Created by Irindul on 25/12/2016.
 */
public interface Element extends AbstractElement {

    //May be deleted
    Position getPos();
    boolean isWalkable();
    void setPos(Position pos);
    void setWalkable(boolean walkable);

}
