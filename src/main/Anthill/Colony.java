package main.Anthill;

import main.Ant.Ant;
import main.Ant.BrainyAnt;
import main.Collections.FoodSupplyCol;
import main.Collections.PheromoneCol;
import main.Element.FoodSupply;
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

    public void addFoodSupply(FoodSupply fs){

    }

    public void addObstacle(int x, int y){

    }

    public void nextCycle(){

    }

    public int getPheromoneQuantityAt(Position p){
        return 0;
    }

    public void detectFood(){
        for(Ant ant : anthill.ants){

            if(ant.getSensor().detectFood(ant.getPosition(),foodSupplies)) {
                ant.takeFood(ant.getPosition(), foodSupplies);
            }

        }
    }

    public void dropPheromone(){
        for(Ant ant : anthill.ants){
            if(ant.getHasFood()){
                pheromones.add(ant.dropPheromone());
            }
        }
    }

    public void move(){
        for(Ant ant : anthill.ants){
            ((BrainyAnt)ant).processProba(); //We upcast ant to a BrainyAnt so we are able to call processProba and executeProba.
            ((BrainyAnt)ant).executeProba();
        }
    }


}
