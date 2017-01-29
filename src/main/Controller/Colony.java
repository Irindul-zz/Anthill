package main.Controller;

import main.Model.Ant.Ant;
import main.Model.Anthill.Anthill;
import main.Model.Brain.Brain;
import main.Model.Brain.EvolvedAI;
import main.Model.Brain.Memory;
import main.Model.Collections.FoodSupplyCol;
import main.Model.Collections.PheromoneCol;
import main.Model.Element.Cell;
import main.Model.Element.FoodSupply;
import main.Model.Element.Pheromone;
import main.Model.Graph.Pathfinding;
import main.Model.Graph.Graph;
import main.Model.Mapping.Direction;
import main.Model.Mapping.Map;
import main.Model.Mapping.Position;
import main.Model.Mapping.ReadFiles;
import main.View.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// ===================================
// ==== Controller of our project ====
// ===================================
public class Colony {

    private FoodSupplyCol foodSupplies;
    private PheromoneCol pheromones;
    private Anthill anthill;
    private Map map;
    private ReadFiles reader;
    private boolean mapHealth; //Attribute about the validity of the map
    private double iterations = 0; //counter for  iterations number

    public static boolean end; //Stop the simulation



    public Colony() throws FileNotFoundException { //Default constructor with a default map
        initialize("map1.txt", 500);
    }

    public Colony(String mapName) throws FileNotFoundException { //Constructor with the map name in parameter
        initialize(mapName, 500);
    }

    public Colony(String mapName, int antAmount) throws FileNotFoundException { // Constructor the map name and ants number
        initialize(mapName, antAmount);
    }


    private void initialize(String mapName, int antAmount) throws FileNotFoundException { //The controller initialize all elements for the simulation and in particular displayElements
        end=false; // Boolean for the simulation end. end = true => end of simulation
        map = new Map();
        foodSupplies = new FoodSupplyCol();
        pheromones = new PheromoneCol();

        anthill = new Anthill(antAmount); //Declaration of our anthill with a special amount of ants, select by the user in the interface

        //We try to open the map
        try {
            reader = new ReadFiles("src" + File.separator + "main/map" + File.separator + mapName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //If there is a problem in the map's reading
        if(!reader.readFile(this.map, this.foodSupplies, this.anthill))
        {
            System.out.println("Something went wrong when reading the map file");
            return;
        }


        pheromones = new PheromoneCol();
        Pathfinding.graph = new Graph(map); //We initialise our graph with the map, as graph is static, no need to re initialize it later


        //checking map, If mapHealth = true the map pass the test and it is valid. If it's mapHealt=false, the map is invalid
        this.mapHealth = map.checkMap(foodSupplies, anthill);

        Pheromone.MAXLIFE = map.getSizeX();

        ColonyDisplay.map = map; //We give the map to our display to display it

        //We define the reference size of a map rectangle.
        ColonyDisplay.heightRectangle = 750/map.getSizeY();
        ColonyDisplay.widthRectangle =  750/map.getSizeX();

        //We declare the collection which contains the display of each ant
        ColonyDisplay.antsDisplay = new AntDisplay[anthill.getAnts().size()];

        //We declare the display of our anthill
        ColonyDisplay.anthillDisplay = new AnthillDisplay(anthill.getPosition());

        //For each ant, we declare one shape in the display
        int i;
        for (i =0 ; i < anthill.getAnts().size() ; i++){
            ColonyDisplay.antsDisplay[i] = new AntDisplay(new Position(anthill.getAntIndcex(i).getPosition().getX(), anthill.getAntIndcex(i).getPosition().getY()));
        }

    }

    //The most important function, is function that realizes all checks and changes in the simulation. It control all of our program
    public void update()
    {
        //We check if this is the end of simulation, in this case we stopped them
        List<Ant> ants = anthill.getAnts();
        if(!end) {
            incIterations(); //We increment the iteration counter
            int i = 0;
            pheromones.updatePheromone();
            for (Ant ant : ants) {
                detectFood(ant); //We detect everything around the ant
                detectPheromone(ant);
                detectObstacle(ant);

                dropPheromone(ant); //We drop pheromones if necessary
                move(ant, map); //We move

                //We display the ants
                ColonyDisplay.antsDisplay[i].setPosition(ant.getPosition());
                if (ant.getPosition().getX() == anthill.getPosition().getX() && ant.getPosition().getY() == anthill.getPosition().getY())
                    ant.dropFood();
                i++;
            }
            //We display the pheromones
            for (Pheromone pheromone : pheromones.getPheromones()) {
                ColonyDisplay.pheromonesDisplay.add(new PheromoneDisplay(pheromone.getPos(), pheromones.size() + 1));
            }

            //We display the foodsupplies
            for (FoodSupply foodSupply : foodSupplies.getSupplies()) {
                ColonyDisplay.foodSuppliesDisplay.add(new FoodSupplyDisplay(foodSupply.getPosition()));
            }
        }
        if(foodSupplies.size()==0){ //If there is no more food
            boolean endAntFood =true;
            for(Ant ant : ants){ //We check that every ant has no food
                if(ant.getHasFood()){
                    endAntFood =false;
                }
            }

            if(endAntFood){
                end=true; //We can stop the simulation
            }
        }
    }



    private void detectFood(Ant ant){

            if(ant.getSensor().detectFood(ant.getPosition(),foodSupplies)&& !ant.getHasFood()) {
                ant.takeFood(ant.getPosition(), foodSupplies);
            }
    }

    private void dropPheromone(Ant ant){
            if(ant.getHasFood()){
                pheromones.add(ant.getPosition(), ant.dropPheromone(1), Direction.reverse(ant.getDirection()));

            }
    }

    private void move(Ant ant, Map map){

        Brain brain =  ant.getBrain();
        brain.processProba(ant); //We can now call the corresponding methods
        Direction direction = brain.executeProba(ant);
        Memory mind = ant.getMind();
        Position nextPosition =new Position(1, 1);
        int x = ant.getPosition().getX();
        int y = ant.getPosition().getY();

        switch (direction){
            case NORTH :
                nextPosition = new Position(x,y-1);
                break;
            case SOUTH :
                nextPosition = new Position(x,y+1);
                break;
            case EAST :
                nextPosition = new Position(x+1,y);
                break;
            case WEST :
                nextPosition = new Position(x-1,y);
                break;
            case NORTHEAST:
                nextPosition = new Position(x+1,y-1);
                break;
            case NORTHWEST:
                nextPosition = new Position(x-1,y-1);
                break;
            case SOUTHEAST:
                nextPosition = new Position(x+1,y+1);
                break;
            case SOUTHWEST:
                nextPosition = new Position(x-1,y+1);
                break;
        }
        if (!map.getCellXY(nextPosition.getX(), nextPosition.getY()).isWalkable()) { //If this direction is valid, ie there is no obstacle, we set nextPosition accordingly
            nextPosition = ant.getPosition();
            mind.setKeeptrack(false);
        }
        if(ant.getHasFood()) //We keep track only if the ant has no food
            mind.setKeeptrack(false);


        mind.keepTrack(direction);
        ant.moveTo(nextPosition, direction);

    }

    private void detectPheromone(Ant ant){
            ant.getSensor().detectPheromones(ant.getPosition(), pheromones);


    }

    private void detectObstacle(Ant ant) {
        Cell[] cells = new Cell[8];

        Position pos = ant.getPosition();
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


    public boolean getMapHalth() {return this.mapHealth;}

    public void changeAntBrain(){ // Change the brain of ant to an evolved brain
        List<Ant> ants = anthill.getAnts();
        for(Ant ant: ants){
            ant.setBrain(new EvolvedAI());
        }
    }

    public double getIterations()
    {
        return this.iterations;
    }

    private void incIterations()
    {
        this.iterations++;
    }
}
