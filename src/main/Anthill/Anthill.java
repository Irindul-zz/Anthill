package main.Anthill;

import main.Ant.Ant;
import main.Mapping.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irindul on 25/12/2016.
 */
public class Anthill{
    List<Ant> ants;
    private int nbAnts;
    private Position position;

    public Anthill() {
        ants = new ArrayList<Ant>();
        position = new Position(3,3);
        nbAnts = 10;
    }

    public Anthill(int nbAnts) {
        ants = new ArrayList<Ant>();
        position = new Position(3,3);
        this.nbAnts = nbAnts;
    }

    public Anthill(Position position){
        ants = new ArrayList<Ant>();
        this.position=position;
        nbAnts = 10;
    }
    public void declareAnts() {
        ants.clear();
        for (int i = 0; i <nbAnts; i++) {
            ants.add(new Ant(this.position, this.position));
        }
    }

    public List getAnts()
    {
        return this.ants;
    }

    public void addAnt(Ant a)
    {
        this.ants.add(a);
    }

    public void sendAnts()
    {
        Ant a = new Ant();
        this.ants.add(a);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getNbAnts() {
        return nbAnts;
    }

    public Ant getAntIndcex(int i) {
        return ants.get(i);
    }
}

