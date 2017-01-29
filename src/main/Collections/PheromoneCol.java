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
           if(p.getPos().getX()== pheromone.getPos().getX() && p.getPos().getY()== pheromone.getPos().getY()){
              // pheromone.setLifeTime(pheromone.getLifeTime()+200); //TODO change 200 when we have decide the lifetime
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

    public Pheromone get(Position p){
        Pheromone phero = null;
        for (Pheromone ph: pheromones) {
            if(ph.getPos().equals(p)){
                phero = ph;
            }
        }
        return phero;
    }

    public int getPheromoneQuantityAt(Position p){

        int sum = 0;
        for (int i = 0; i < pheromones.size(); i++) {
            if(pheromones.get(i).getPos().equals(p)){
                sum += pheromones.get(i).getLifeTime();
            }
        }
        return sum;
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
                pheromones.get(i).actualize();
            }
        }
    }
}
