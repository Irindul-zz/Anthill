package main.Collections;

import main.Element.Pheromone;
import main.Mapping.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irindul on 05/01/2017.
 */
public class PheromoneCol {

    private List<Pheromone> pheromones;

    public PheromoneCol(){
        pheromones = new ArrayList<>();
    }


    public Pheromone get(int i){
        return this.pheromones.get(i);
    }

    public void add(Pheromone p, int i){
        this.pheromones.add(i, p);
    }

    public void add(Pheromone p){
        boolean hasPheromone =false;

        for(Pheromone pheromone: pheromones){
           if(p.getPos()== pheromone.getPos()){
               pheromone.setLifeTime(pheromone.getLifeTime()+10); //TODO change 10 when we have decide the lifetime
               hasPheromone=true;
               break;
           }
       }
       if(!hasPheromone){
           pheromones.add(p);
       }
    }

    public void remove(int i){
        this.pheromones.remove(i);
    }

    public int size(){
        return this.pheromones.size();
    }

    public void actualize() {
        for(int i=0; i<this.pheromones.size(); i++)
        {
            this.pheromones.get(i).actualize();
            if(this.pheromones.get(i).getLifeTime() == 0)
            {
                this.remove(i);
            }
        }
    }

    public int getPheromoneQuantityAt(Position p){
        return 0;
    }

    public List<Pheromone> getPheromones() {
        return pheromones;
    }

    public void updatePheromone(){
        for(int i=0; i<pheromones.size(); i++){
            if(pheromones.get(i).getLifeTime()==0){
                pheromones.remove(i);
            }
            else {
                pheromones.get(i).setLifeTime(pheromones.get(i).getLifeTime()-1);
            }
        }
    }
}
