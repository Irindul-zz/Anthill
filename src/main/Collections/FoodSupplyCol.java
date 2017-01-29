package main.Collections;

import main.Element.FoodSupply;
import main.Mapping.Position;

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
        getFoodSupplyAt(position).removeFood();

        if(getFoodSupplyAt(position).getQuantity() == 0){
            remove(position);
        }
    }

    public List<FoodSupply> getSupplies(){
        return supplies;
    }

    public int size(){
        return supplies.size();
    }

    private void remove(Position position){
        for(int i =0; i<supplies.size(); i++) {
            if (supplies.get(i).getPosition().getX()==position.getX() && supplies.get(i).getPosition().getY()==position.getY()) {
                supplies.remove(i);
            }
        }
    }


    public FoodSupply getFoodSupplyAt(Position position) {
        for (FoodSupply supply : supplies) {
            if (supply.getPosition().getX() == position.getX() && supply.getPosition().getY() == position.getY()) {
                return supply;
            }
        }
            return null;
        }

}
