package main.Collections;

import javafx.geometry.Pos;
import main.Element.Pheromone;
import main.Mapping.Direction;
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
                sum += pheromones.get(i).getQuantity();
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
