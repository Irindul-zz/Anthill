package main.Element;

import main.Mapping.Position;

/**
 * Created by Irindul on 25/12/2016.
 */
public class Pheromone{

    private int lifeTime;
    private Position pos;

    public Pheromone(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public Pheromone(Position pos) {
        this.pos = pos;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }



    public void actualize(){
        this.lifeTime--;
    }


    //GET_SET for lifeTime
    public int getLifeTime() {
        return lifeTime;
    }
    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

}
