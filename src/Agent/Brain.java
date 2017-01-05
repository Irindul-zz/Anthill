package Agent;

import Mapping.Direction;

/**
 * Created by Irindul on 25/12/2016.
 */
public interface Brain {

    void detectEnvironement();
    void processProba();
    Direction executeProba();
}
