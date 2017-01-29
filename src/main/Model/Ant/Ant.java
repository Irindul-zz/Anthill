package main.Model.Ant;

import main.Model.Brain.BasicAI;
import main.Model.Brain.Brain;
import main.Model.Brain.EvolvedAI;
import main.Model.Brain.Memory;
import main.Model.Collections.FoodSupplyCol;
import main.Model.Mapping.Direction;
import main.Model.Mapping.Position;

public class Ant{

    private boolean hasFood;
    private Direction direction;
    private Sense sensor;
    private Position position;
    private Brain brain;
    private Memory mind;
    private Position anthillPosition;
    private boolean goBack;

    public Ant(Direction direction, Position position) {
        this();
        this.direction = direction;
        this.position = position;
    }

    public Ant(Position position, Position anthillPosition) {
        this();
        this.position = position;
        this.anthillPosition = anthillPosition;

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

    public void takeFood(Position position, FoodSupplyCol f){
        f.removeFoodAt(position);
        hasFood=true;
        goBack = true;
    }

    public void dropFood(){
        hasFood=false;

    }

    public int dropPheromone(int quantity){
        if(sensor.getPheromoneDir() == null) //If there is no pheromones at this position we will add a quantity of 1
            return quantity;
        else
            return 3*quantity; // we add three time the quantity otherwise.
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


    public Position getAnhillPosition() {
        return anthillPosition;
    }

    public boolean goBack() {
        return goBack;
    }

    public void setGoBack(boolean b){
        goBack = b;
    }

    public void setBrain(Brain brain) {
        this.brain = brain;
        if(brain instanceof EvolvedAI){ //We also need to change the Sensor accordingly to the AI.
            this.sensor = new EvolvedSensor();
        } else {
            this.sensor = new BasicSensor();

        }
    }
}

    
    

    
