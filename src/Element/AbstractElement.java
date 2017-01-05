package Element;

import Mapping.Position;

/**
 * Created by Irindul on 25/12/2016.
 */
public class AbstractElement {

    protected Position pos;

    public Position getPos(){
        return pos;
    }

    public boolean isWalkable(){
        return Boolean.parseBoolean(null);
    }


}
