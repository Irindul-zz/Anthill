package main.Element;

import main.Mapping.Direction;
import main.Mapping.Position;

/**
 * Created by Irindul on 25/12/2016.
 */
public class Pheromone{

    private int lifeTime;
    private Position pos;
    private Direction direction;

    public Pheromone(Position position, int lifeTime, Direction direction) {
        this.lifeTime = lifeTime;
        pos = position;
        this.direction = direction;
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


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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
