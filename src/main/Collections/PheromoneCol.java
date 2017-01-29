package main.Collections;

import main.Element.Pheromone;
import main.Mapping.Direction;
import main.Mapping.Position;

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
            if(p.getPos().equals(pos)) {
                exist = true;
                p.setLifeTime(p.getLifeTime() + Pheromone.MAXLIFE/2);
                p.add(quantity);
            }
        }

        if(!exist){
            this.add(new Pheromone(pos, Pheromone.MAXLIFE,  d, quantity));
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
        return phero;
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
