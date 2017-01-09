package main.Ant;

import main.Mapping.Direction;

/**
 * Created by Irindul on 25/12/2016.
 */
public class Ant{

    protected boolean hasFood;
    protected Direction direction;
    protected Sense sensor;

    public Ant(Direction direction) {
        this.direction = direction;
        sensor = new BasicSensor(); //this line must be overwritten for different sensors.
    }

    public Ant(){
        this(Direction.NORTH);
    }

    public void moveTo(Direction d){

    }

    public void takeFood(){

    }

    public void dropFood(){

    }

    public void dropPheromone(){

    }

}
