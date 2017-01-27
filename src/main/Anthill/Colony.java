package main.Anthill;

import main.Ant.Ant;
import main.Brain.Brain;
import main.Collections.FoodSupplyCol;
import main.Collections.PheromoneCol;
import main.Element.Cell;
import main.Element.FoodSupply;
import main.Mapping.Direction;
import main.Mapping.Map;
import main.Mapping.Position;
import main.Mapping.ReadFiles;
import main.View.AntDisplay;
import main.View.ColonyDisplay;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Irindul on 25/12/2016.
 */
public class Colony {

    private FoodSupplyCol foodSupplies;
    private PheromoneCol pheromones;
    private Anthill anthill;
    private Map map;
    //AntDisplay[] antsDisplay;
    private ColonyDisplay colonyDisplay;
    private ReadFiles reader;




    public Colony() throws FileNotFoundException {
        map = new Map();
        // map = new Map("src" + File.separator + "main/map" + File.separator + "map1.txt");
        foodSupplies = new FoodSupplyCol();
        anthill = new Anthill(10);

        try {
            reader = new ReadFiles("src" + File.separator + "main/map" + File.separator + "map1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.readFile(this.map, this.foodSupplies, this.anthill);




        pheromones = new PheromoneCol();

        //Ants only stay in map if they start at (1, 1) Position
        //anthill.setPosition(new Position(1, 1));
        //anthill.declareAnts();


        ColonyDisplay.map = map;
        colonyDisplay = new ColonyDisplay();
        ColonyDisplay.antsDisplay = new AntDisplay[anthill.getAnts().size()];


        for (int i =0 ; i < anthill.getAnts().size() ; i++){
            ColonyDisplay.antsDisplay[i] = new AntDisplay(new Position(anthill.getAntIndcex(i).getPosition().getX(), anthill.getAntIndcex(i).getPosition().getY()), i);
        }

    }

    public void addFoodSupply(FoodSupply fs){

    }

    /*public void run(){

        while (!end()){
        int i = 0;
            for (Ant ant: anthill.ants) {
                detectFood(ant);
                dropPheromone(ant);
                detectPheromone(ant);
                detectObstacle(ant);
                move(ant);
                ColonyDisplay.antsDisplay[i].setPosition(ant.getPosition());
                i++;
            }
       
        }
    }*/

    public void update()
    {
        int i = 0;
        for (Ant ant: anthill.ants) {
            //detectFood(ant);
            //dropPheromone(ant);
            detectPheromone(ant);
            detectObstacle(ant);
            move(ant);
            ColonyDisplay.antsDisplay[i].setPosition(ant.getPosition());
            i++;
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
           // Brain brain = ((BrainyAnt) ant).getBrain(); //The upcast allow us to get the brain of the ant
        Brain brain =  ant.getBrain();
        brain.processProba(ant); //We can now call the corresponding methods
            brain.executeProba(ant);
    }

    public void detectPheromone(Ant ant){
            ant.getSensor().detectPheromones(ant.getPosition(), pheromones);

    }

    public void detectObstacle(Ant ant) {
        Cell[] cells = new Cell[8];

        Position pos = ant.getPosition(); //TODO May be refactored inside EvolvedSensor
        int x = pos.getX();
        int y = pos.getY();
        // We store every surrounding cells in a Cell array.
        // the try expression is used to handle the ArrayIndexOutOfBoundsException exception => the cell out of bound is stored as non walkable
        try {
            Position north = new Position(x, y - 1);
            cells[Direction.NORTH.ordinal()] = map.getCellXY(north.getX(), north.getY());
        } catch (ArrayIndexOutOfBoundsException e) {
            cells[Direction.NORTH.ordinal()] = new Cell(false);
        }

        try {
            Position northeast = new Position(x + 1, y - 1);
            cells[Direction.NORTHEAST.ordinal()] = map.getCellXY(northeast.getX(), northeast.getY());
        } catch (ArrayIndexOutOfBoundsException e) {
            cells[Direction.NORTHEAST.ordinal()] = new Cell(false);
        }

        try {
            Position east = new Position(x + 1, y);
            cells[Direction.EAST.ordinal()] = map.getCellXY(east.getX(), east.getY());
        } catch (ArrayIndexOutOfBoundsException e) {
            cells[Direction.EAST.ordinal()] = new Cell(false);

        }

        try {
            Position southeast = new Position(x + 1, y + 1);
            cells[Direction.SOUTHEAST.ordinal()] = map.getCellXY(southeast.getX(), southeast.getY());


        } catch (ArrayIndexOutOfBoundsException e) {
            cells[Direction.SOUTHEAST.ordinal()] = new Cell(false);

        }

        try {
            Position south = new Position(x, y + 1);
            cells[Direction.SOUTH.ordinal()] = map.getCellXY(south.getX(), south.getY());

        } catch (ArrayIndexOutOfBoundsException e) {
            cells[Direction.SOUTH.ordinal()] = new Cell(false);

        }
        try {
            Position southwest = new Position(x - 1, y + 1);
            cells[Direction.SOUTHWEST.ordinal()] = map.getCellXY(southwest.getX(), southwest.getY());

        } catch (ArrayIndexOutOfBoundsException e) {
            cells[Direction.SOUTHWEST.ordinal()] = new Cell(false);

        }

        try {
            Position west = new Position(x - 1, y);
            cells[Direction.WEST.ordinal()] = map.getCellXY(west.getX(), west.getY());

        } catch (ArrayIndexOutOfBoundsException e) {
            cells[Direction.WEST.ordinal()] = new Cell(false);

        }

        try {
            Position northwest = new Position(x - 1, y - 1);
            cells[Direction.NORTHWEST.ordinal()] = map.getCellXY(northwest.getX(), northwest.getY());


        } catch (ArrayIndexOutOfBoundsException e) {
            cells[Direction.NORTHWEST.ordinal()] = new Cell(false);

        }

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
