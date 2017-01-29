package main.Anthill;

import main.Ant.Ant;
import main.Brain.Brain;
import main.Brain.EvolvedAI;
import main.Brain.Memory;
import main.Collections.FoodSupplyCol;
import main.Collections.PheromoneCol;
import main.Element.Cell;
import main.Element.FoodSupply;
import main.Element.Pheromone;
import main.Graph.Dijkstra;
import main.Graph.Graph;
import main.Mapping.Direction;
import main.Mapping.Map;
import main.Mapping.Position;
import main.Mapping.ReadFiles;
import main.View.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Colony {

    private FoodSupplyCol foodSupplies;
    private PheromoneCol pheromones;
    private Anthill anthill;
    private Map map;
    //AntDisplay[] antsDisplay;
    private ColonyDisplay colonyDisplay;
    private ReadFiles reader;
    private boolean mapHealth;

    public static boolean end; //Stop the simulation



    public Colony() throws FileNotFoundException {
        initialize("map1.txt");
    }

    public Colony(String mapName) throws FileNotFoundException {
        initialize(mapName);
    }

    public void initialize(String mapName) throws FileNotFoundException {
        end=false;
        map = new Map();
        // map = new Map("src" + File.separator + "main/map" + File.separator + "map1.txt");
        foodSupplies = new FoodSupplyCol();
        pheromones = new PheromoneCol();

        anthill = new Anthill(500);

        try {
            reader = new ReadFiles("src" + File.separator + "main/map" + File.separator + mapName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!reader.readFile(this.map, this.foodSupplies, this.anthill))
        {
            System.out.println("Something went wrong when reading the map file");
            return;
        }
        pheromones = new PheromoneCol();
        Dijkstra.graph = new Graph(map); //We initialise our graph with the map, as graph is static, no need to re initialize it later


        //checking map
        this.mapHealth = true;
        if (!map.checkMap(foodSupplies, anthill)) {
            this.mapHealth = false;
        }

        //Ants only stay in map if they start at (1, 1) Position
        //anthill.setPosition(new Position(13, 1));
        //anthill.declareAnts();
        ColonyDisplay.map = map;
        colonyDisplay = new ColonyDisplay();
        ColonyDisplay.heightRectangle = 750/map.getSizeY();
        ColonyDisplay.widthRectangle =  750/map.getSizeX();
        ColonyDisplay.antsDisplay = new AntDisplay[anthill.getAnts().size()];

        ColonyDisplay.anthillDisplay = new AnthillDisplay(anthill.getPosition());
        int i;
        for (i =0 ; i < anthill.getAnts().size() ; i++){
            ColonyDisplay.antsDisplay[i] = new AntDisplay(new Position(anthill.getAntIndcex(i).getPosition().getX(), anthill.getAntIndcex(i).getPosition().getY()), i);
        }

    }

    public void update()
    {
        if(!end) {
            int i = 0;
            for (Ant ant : anthill.ants) {
                pheromones.updatePheromone();
                detectFood(ant);
                dropPheromone(ant);
                detectPheromone(ant);
                detectObstacle(ant);
                move(ant, map);
                ColonyDisplay.antsDisplay[i].setPosition(ant.getPosition());
                if (ant.getPosition().getX() == anthill.getPosition().getX() && ant.getPosition().getY() == anthill.getPosition().getY())
                    ant.dropFood();
                i++;
            }

            for (Pheromone pheromone : pheromones.getPheromones()) {
                ColonyDisplay.pheromonesDisplay.add(new PheromoneDisplay(pheromone.getPos(), pheromones.size() + 1));
            }
            for (FoodSupply foodSupply : foodSupplies.getSupplies()) {
                ColonyDisplay.foodSuppliesDisplay.add(new FoodSupplyDisplay(foodSupply.getPosition(), foodSupplies.size() + 1));
            }
        }
        if(foodSupplies.size()==0){
            boolean endAntFood =true;
            for(Ant ant : anthill.ants){
                if(ant.getHasFood()){
                    endAntFood =false;
                }
            }

            if(endAntFood){
                end=true;
            }
        }
    }



    public void detectFood(Ant ant){

            if(ant.getSensor().detectFood(ant.getPosition(),foodSupplies)&& !ant.getHasFood()) {
                ant.takeFood(ant.getPosition(), foodSupplies);
            }
    }

    public void dropPheromone(Ant ant){
            if(ant.getHasFood()){
                pheromones.add(ant.dropPheromone());

            }
    }

    public void move(Ant ant, Map map){

        Brain brain =  ant.getBrain();
        brain.processProba(ant); //We can now call the corresponding methods
        Direction direction = brain.executeProba(ant);
        Memory mind = ant.getMind();
        Position nextPosition =new Position(1, 1);
        int x = ant.getPosition().getX();
        int y = ant.getPosition().getY();

        switch (direction){ //If this direction is valid, ie there is no obstacle, we set nextPosition accordingly
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
        if (!map.getCellXY(nextPosition.getX(), nextPosition.getY()).isWalkable()) {
            nextPosition = ant.getPosition();
            mind.setKeeptrack(false);
        }
        if(ant.getHasFood())
            mind.setKeeptrack(false);


        mind.keepTrack(direction);
        ant.moveTo(nextPosition, direction);

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


    public boolean getMapHalth() {return this.mapHealth;}

    public void changeAntBrain(){ // Change the brain of ant to an evolved brain
        for(Ant ant: anthill.ants){
            ant.setBrain(new EvolvedAI());
        }
    }
}
