package main.Anthill;

import main.Ant.Ant;
import main.Mapping.Position;

import java.util.ArrayList;
import java.util.List;

public class Anthill{
    private List<Ant> ants;
    private int nbAnts;
    private Position position;

    public Anthill() {
        ants = new ArrayList<>();
        position = new Position(3,3);
        nbAnts = 10;
    }

    public Anthill(int nbAnts) {
        ants = new ArrayList<>();
        position = new Position(3,3);
        this.nbAnts = nbAnts;
    }

    public Anthill(Position position){
        ants = new ArrayList<>();
        this.position=position;
        nbAnts = 10;
    }
    public void declareAnts() {
        ants.clear();
        for (int i = 0; i <nbAnts; i++) {
            ants.add(new Ant(this.position, this.position));
            //ants.get(i).setBrain(new EvolvedAI());
        }
    }

    public List<Ant> getAnts()
    {
        return this.ants;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Ant getAntIndcex(int i) {
        return ants.get(i);
    }
}

