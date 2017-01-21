package main.Ant;

import main.Collections.FoodSupplyCol;
import main.Element.Pheromone;
import main.Mapping.Direction;
import main.Mapping.Map;
import main.Mapping.Position;

import static main.Mapping.Direction.*;

/**
 * Created by Irindul on 25/12/2016.
 */
public class Ant{

    protected boolean hasFood;
    protected Direction direction;
    protected Sense sensor;
    protected Position position;

    public Ant(Direction direction) {
        this.direction = direction;
        sensor = new BasicSensor(); //this line must be overwritten for different sensors.
    }

    public Ant(){
        this(NORTH);
    }

    public void moveTo(Direction d){
        int x = position.getX();
        int y = position.getY();

        switch (d){
            case NORTH :
                position = new Position(x,y-1);
                break;
            case SOUTH :
                position = new Position(x,y+1);
                break;
            case NORTHEAST:
                position = new Position(x+1,y-1);
                break;
            case NORTHWEST:
                position = new Position(x-1,y-1);
                break;
            case SOUTHEAST:
                position = new Position(x+1,y+1);
                break;
            case SOUTHWEST:
                position = new Position(x-1,y+1);
                break;
        }
    }

    public void takeFood(Position pos, FoodSupplyCol f){
        f.getFoodSupplyAt(pos).removeFood();
        hasFood=true;
    }

    public void dropFood(){
        hasFood=false;

    }

    public Pheromone dropPheromone(){
        Pheromone pheromone = new Pheromone(10); //TODO : add position in paramater when kriss have changed is constructor
        return pheromone;
    }

    public Position getPosition(){
        return this.position;
    }

    public Sense getSensor() {
        return sensor;
    }

    public boolean getHasFood() {
        return hasFood;
    }
}

    
    

    
