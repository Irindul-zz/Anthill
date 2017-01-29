package main.Element;

import main.Mapping.Position;


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

    public void setPosition(Position position) {
        this.position = position;
    }
}
