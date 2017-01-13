package main.Ant;

import main.Collections.FoodSupplyCol;
import main.Mapping.Direction;
import main.Mapping.Map;
import main.Mapping.Position;

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
        this(Direction.NORTH);
    }

    public void moveTo(Direction d){

    }

    public boolean takeFood(Position pos, FoodSupplyCol f){
        f.getFoodSupplyAt(pos).removeFood();
        hasFood=true;
    }

    public void dropFood(){
        hasFood=false;

    }

    public void dropPheromone(){
        
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

    
    

    
