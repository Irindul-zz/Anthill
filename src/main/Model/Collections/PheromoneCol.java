package main.Model.Collections;

import main.Model.Element.Pheromone;
import main.Model.Mapping.Direction;
import main.Model.Mapping.Position;

import java.util.ArrayList;
import java.util.List;


public class PheromoneCol {

    private List<Pheromone> pheromones;

    public PheromoneCol(){
        pheromones = new ArrayList<>();
    }


    public void add(Pheromone p, int i){
        this.pheromones.add(i, p);
    }

    public void add(Pheromone p){
        pheromones.add(p);
    }

    public void add(Position pos, int quantity, Direction d){
        boolean exist = false;
        for (Pheromone p: pheromones) {
            if(p.getPos().equals(pos)) { //If there was already a pheromone
                exist = true;
                p.setLifeTime(p.getLifeTime() + Pheromone.MAXLIFE/2); //We increment the lifeTime
                p.add(quantity); //We add the quantity
            }
        }

        if(!exist){ //If there was no pheromone
            this.add(new Pheromone(pos, Pheromone.MAXLIFE,  d, quantity)); //We create a new one
        }


    }
    

    public int size(){
        return this.pheromones.size();
    }

    public Pheromone get(Position p){
        Pheromone phero = null;
        for (Pheromone ph: pheromones) {
            if(ph.getPos().equals(p)){
                phero = ph;
            }
        }
        return phero; //Null will be checked
    }

    public int getPheromoneQuantityAt(Position p){

        int sum = 0;
        for (Pheromone pheromone : pheromones) {
            if (pheromone.getPos().equals(p)) {
                sum += pheromone.getQuantity();
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
