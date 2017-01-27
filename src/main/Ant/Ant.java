package main.Ant;

import main.Brain.BasicAI;
import main.Brain.Brain;
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
    private Brain brain;

    public Ant(Direction direction, Position position) {
        brain = new BasicAI();
        this.direction = direction;
        this.position = position;
        sensor = new BasicSensor(); //this line must be overwritten for different sensors.
    }

    public Ant(Position position) {
        direction = Direction.NORTH;
        brain = new BasicAI();
        this.position = position;
        sensor = new BasicSensor(); //this line must be overwritten for different sensors.
    }
    public Ant() {
        direction = Direction.NORTH;
        brain = new BasicAI();
        position = new Position(0,0);
        sensor = new BasicSensor(); //this line must be overwritten for different sensors.

    }

    public Brain getBrain() {
        return brain;
    }

    public void moveTo(Position position, Direction direction){
        this.direction = direction;
        this.position = position;
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

    public Direction getDirection(){
        return direction;
    }

    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
    }
}

    
    

    
