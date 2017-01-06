package main.Anthill;

import main.Element.WalkableElement;

/**
 * Created by Irindul on 25/12/2016.
 */
public class Pheromone extends WalkableElement{

    private int lifeTime;

    public Pheromone(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public void actualize(){

    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }


}
