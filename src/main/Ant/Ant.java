package main.Ant;

import javafx.geometry.Pos;
import main.Brain.BasicAI;
import main.Brain.Brain;
import main.Brain.EvolvedAI;
import main.Brain.Memory;
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
    protected Brain brain;
    protected Memory mind;
    protected Position anhillPosition;

    public Ant(Direction direction, Position position) {
        brain = new EvolvedAI();
        this.direction = direction;
        this.position = position;
        mind = new Mind();
        sensor = new EvolvedSensor(); //this line must be overwritten for different sensors.
    }

    public Ant(Position position, Position anthillPosition) {
        direction = Direction.NORTH;
        brain = new EvolvedAI();
        this.position = position;
        mind = new Mind();
        anhillPosition = anthillPosition;
        sensor = new BasicSensor(); //this line must be overwritten for different sensors.
    }
    public Ant() {
        direction = Direction.NORTH;
        brain = new EvolvedAI();
        position = new Position(0,0);
        mind = new Mind();
        sensor = new BasicSensor(); //this line must be overwritten for different sensors.

    }

    public Brain getBrain() {
        return brain;
    }


    public Memory getMind() {
        return mind;
    }

    public void moveTo(Position position, Direction direction){
        this.direction = direction;
        this.position = position;
    }

    public void takeFood(Position posistion, FoodSupplyCol f){
        f.removeFoodAt(position);
        hasFood=true;
    }

    public void dropFood(){
        hasFood=false;

    }

    public Pheromone dropPheromone(){
        Pheromone pheromone = new Pheromone(position, 200, direction); //TODO : add position in paramater when kriss have changed is constructor
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

    public Position getAnhillPosition() {
        return anhillPosition;
    }
}

    
    

    
