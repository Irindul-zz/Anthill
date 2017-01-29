package main.Model.Collections;

import main.Model.Element.FoodSupply;
import main.Model.Mapping.Position;

import java.util.ArrayList;
import java.util.List;
public class FoodSupplyCol {

    private List<FoodSupply> supplies;

    public FoodSupplyCol() {


        supplies = new ArrayList<>();
    }



    public void addFoodSupply(FoodSupply fs){
        supplies.add(fs);
    }

    public void removeFoodAt(Position position){
        getFoodSupplyAt(position).removeFood(); //We get food at the position

        if(getFoodSupplyAt(position).getQuantity() == 0){ //If there is no more food on this food supply
            remove(position); //We remove it
        }
    }

    public List<FoodSupply> getSupplies(){
        return supplies;
    }

    public int size(){
        return supplies.size();
    }

    private void remove(Position position){
        for(int i =0; i<supplies.size(); i++) { //For each food supply
            if (supplies.get(i).getPosition().equals(position)) { //If we are in the same position
                supplies.remove(i);  //we remove it from the array
            }
        }
    }


    public FoodSupply getFoodSupplyAt(Position position) {
        for (FoodSupply supply : supplies) {
            if (supply.getPosition().equals(position)) {
                return supply;
            }
        }
            return null;
        }

}
