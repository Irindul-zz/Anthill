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
    private Position position;

    public Anthill() {
        ants = new ArrayList<Ant>();
        position = new Position(1,1);
    }

    public Anthill(Position position){
        ants = new ArrayList<Ant>();
        this.position=position;
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


}

