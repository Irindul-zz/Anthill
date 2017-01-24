package main.Anthill;

import main.Ant.Ant;
import main.Ant.BrainyAnt;
import main.Brain.Brain;
import main.Collections.FoodSupplyCol;
import main.Collections.PheromoneCol;
import main.Element.Cell;
import main.Element.FoodSupply;
import main.Mapping.Direction;
import main.Mapping.Map;
import main.Mapping.Position;
/**
 * Created by Irindul on 25/12/2016.
 */
public class Colony {

    private FoodSupplyCol foodSupplies;
    private PheromoneCol pheromones;
    private Anthill anthill;
    private Map map;


    public Colony(){
        foodSupplies = new FoodSupplyCol();
        pheromones = new PheromoneCol();
        anthill = new Anthill();
        Map map = new Map();
    }

    public void addFoodSupply(FoodSupply fs){

    }

    public void run(){

        while (!end()){
            for (Ant ant: anthill.ants) {
                detectFood(ant);
                dropPheromone(ant);
                detectPheromone(ant);
                detectObstacle(ant);
                move(ant);
            }
        }


    }

    public void addObstacle(int x, int y){

    }

    public void nextCycle(){

    }

    public int getPheromoneQuantityAt(Position p){
        return 0;
    }

    public void detectFood(Ant ant){

            if(ant.getSensor().detectFood(ant.getPosition(),foodSupplies)) {
                ant.takeFood(ant.getPosition(), foodSupplies);
            }
    }

    public void dropPheromone(Ant ant){
            if(ant.getHasFood()){
                pheromones.add(ant.dropPheromone());
            }
    }

    public void move(Ant ant){
            Brain brain = ((BrainyAnt) ant).getBrain(); //The upcast allow us to get the brain of the ant
            brain.processProba(ant); //We can now call the corresponding methods
            brain.executeProba(ant);
    }

    public void detectPheromone(Ant ant){
            ant.getSensor().detectPheromones(ant.getPosition(), pheromones);

    }

    public void detectObstacle(Ant ant){
        Cell[] cells = new Cell[8];
            Position pos = ant.getPosition(); //TODO May be refactored inside EvolvedSensor
            int x = pos.getX();
            int y = pos.getY();

            Position north = new Position(x, y-1);
            Position northeast = new Position(x+1, y-1);
            Position east = new Position(x+1, y);
            Position southeast = new Position(x+1, y+1);
            Position south = new Position(x, y+1);
            Position southwest = new Position(x-1, y+1);
            Position west = new Position(x-1, y);
            Position northwest = new Position(x-1, y-1);

            cells[Direction.NORTH.ordinal()] = map.getCell(north.getX(), north.getY());
            cells[Direction.NORTHEAST.ordinal()] = map.getCell(northeast.getX(), northeast.getY());
            cells[Direction.EAST.ordinal()] = map.getCell(east.getX(), east.getY());
            cells[Direction.SOUTHEAST.ordinal()] = map.getCell(southeast.getX(), southeast.getY());
            cells[Direction.SOUTH.ordinal()] = map.getCell(south.getX(), south.getY());
            cells[Direction.SOUTHWEST.ordinal()] = map.getCell(southwest.getX(), southwest.getY());
            cells[Direction.WEST.ordinal()] = map.getCell(west.getX(), west.getY());
            cells[Direction.NORTHWEST.ordinal()] = map.getCell(northwest.getX(), northwest.getY());
            ant.getSensor().detectObstacles(ant.getPosition(), cells);
    }

    public boolean end(){
        boolean end = false;
        for (FoodSupply f: foodSupplies.getSupplies()) {
            if(f.getQuantity() != 0)
                end = true;
        }

        for (Ant ant: anthill.ants) {
            if(ant.getHasFood())
            {
               end = true;
            }
        }

        return end;
    }
}
