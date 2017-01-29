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
    protected boolean goBack;

    public Ant(Direction direction, Position position) {
        this();
        this.direction = direction;
        this.position = position;
    }

    public Ant(Position position, Position anthillPosition) {
        this();
        this.position = position;
        anhillPosition = anthillPosition;

    }
    public Ant() {
        direction = Direction.SOUTHWEST;
        brain = new BasicAI();
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
        goBack = true;
    }

    public void dropFood(){
        hasFood=false;

    }

    public int dropPheromone(int quantity){
        if(sensor.getPheromoneDir() == null)
            return quantity;
        else
            return 5*quantity;
        //TODO 2 si phéromone 1 sinon
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

    public boolean goBack() {
        return goBack;
    }

    public void setGoBack(boolean b){
        goBack = b;
    }

    public void setBrain(Brain brain) {
        this.brain = brain;
        if(brain instanceof EvolvedAI){
            this.sensor = new EvolvedSensor();
        } else {
            this.sensor = new BasicSensor();

        }
    }
}

    
    

    
