package main.Anthill;

import main.Ant.Ant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irindul on 25/12/2016.
 */
public class Anthill{
    List<Ant> ants;

    public Anthill(){
        ants = new ArrayList<>();
    }

    public List getAnts()
    {
        return this.ants;
    }

}
