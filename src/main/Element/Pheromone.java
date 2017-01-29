package main.Element;

import main.Mapping.Direction;
import main.Mapping.Position;

/**
 * Created by Irindul on 25/12/2016.
 */
public class Pheromone{

    private int lifeTime;
    private Position pos;
    private int quantity;
    private Direction direction;
    public static int MAXLIFE;

    public Pheromone(Position position, int lifeTime, Direction direction, int quantity) {
        this.lifeTime = lifeTime;
        pos = position;
        this.direction = direction;
        this.quantity = quantity;
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

    public void add(int quantity){
        this.quantity += quantity;
    }
    public Direction getDirection() {
        return direction;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void actualize(){
        this.lifeTime -= 2;
    }


    //GET_SET for lifeTime
    public int getLifeTime() {
        return lifeTime;
    }
    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
        if(this.lifeTime  > MAXLIFE)
            lifeTime = MAXLIFE;
    }

}
