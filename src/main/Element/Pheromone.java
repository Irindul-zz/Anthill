package main.Element;

/**
 * Created by Irindul on 25/12/2016.
 */
public class Pheromone{

    private int lifeTime;

    public Pheromone(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public void actualize(){
        this.lifeTime--;
    }


    //GET_SET for lifeTime
    public int getLifeTime() {
        return lifeTime;
    }
    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

}
