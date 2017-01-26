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
    private Position pos;

    public Anthill(Position pos) {
        ants = new ArrayList<>();
        this.pos = pos;
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

    public Position getPos()
    {
        return this.pos;
    }

    public void setPos(Position pos)
    {
        this.pos = pos;
    }


}
