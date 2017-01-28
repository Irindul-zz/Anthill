package main.Element;

import main.Mapping.Position;

/**
 * Created by Irindul on 25/12/2016.
 */
public class FoodSupply{

    private int quantity;
    private Position position;

    public FoodSupply(Position position, int quantity) {
        this.position = position;
        this.quantity = quantity;
    }
    

    public int getQuantity(){
        return quantity;
    }

    public Position getPosition(){
        return this.position;
    }

    public void removeFood() {

        quantity --;
    	
    }

    public void removeFood(int quantity) {
        this.quantity -= quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
