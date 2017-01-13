package main.Collections;

import main.Element.FoodSupply;
import main.Mapping.Position;

import java.util.ArrayList;
import java.util.List;
public class FoodSupplyCol {

    private List<FoodSupply> supplies;

    public FoodSupplyCol() {

        //Note for Maxence : Declare a collection as a List and implement it as an ArrayList is the
        //proper way to declare ArrayLists.
        supplies = new ArrayList<FoodSupply>();
    }

    public FoodSupply getFoodSupplyIndex(int i){
        return supplies.get(i);
    }
    public FoodSupply getFoodSupplyAt(Position position) {

        for(int i =0; i<supplies.size(); i++) {
            if (supplies.get(i).getPosition().getX()==position.getX() && supplies.get(i).getPosition().getY()==position.getY()) {
                return supplies.get(i);
            }
        }
        return null;
    }

    public void add(FoodSupply fs, Position position){


    }

    public int size(){
        return supplies.size();
    }

    public void remove(Position position){
        for(int i =0; i<supplies.size(); i++) {
            if (supplies.get(i).getPosition().getX()==position.getX() && supplies.get(i).getPosition().getY()==position.getY()) {
                supplies.remove(i);
            }
        }
    }





}
