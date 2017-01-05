package Ant;

import Element.WalkableElement;
import Mapping.Direction;

/**
 * Created by Irindul on 25/12/2016.
 */
public class Ant extends WalkableElement {

    protected boolean hasFood;
    protected Direction direction;

    public Ant(Direction direction) {
        this.direction = direction;
    }

    public Ant(){
        this(Direction.NORTH);
    }

    public void moveTo(Direction d){

    }

    public void detectFood(){

    }

    public void takeFood(){

    }

    public void dropFood(){

    }

    public void dropPheromone(){

    }

    public void detectPheromone(){

    }

}
