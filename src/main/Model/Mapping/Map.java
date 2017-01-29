package main.Model.Mapping;
import main.Model.Anthill.Anthill;
import main.Model.Collections.FoodSupplyCol;
import main.Model.Element.Cell;
import main.Model.Element.FoodSupply;
import main.Model.Graph.Pathfinding;

import java.io.*;

public class Map {
    private File fileMap;
    private int sizeX;
    private int sizeY;
   private int nbFoodSupply;
    private  int[] foodSupplyQuantities;
    private Cell[][] map ;


    public Map() {
    }

    public void display(){

        for(int i = 0; i<sizeX;i++){
            for(int j =0; j<sizeY; j++){
                System.out.print(map[j][i].getChar()+ " ");
            }
            System.out.print("\n");
        }
    }

    public void getNbFoodSupply(){

    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Cell[][] getMap() {
        return map;
    }

    public Cell getCellXY(int x, int y){
        return map[x][y];
    }

    public void setMap(Cell[][] map) {
        this.map = map;
    }
    public void setCellXY(int x, int y, Cell cell) {
        map[x][y] = cell;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public Cell getCellPosition(Position position) {
        return map[position.getX()][position.getY()];
    }

    public boolean checkMap(FoodSupplyCol foodSupplyCol, Anthill anthill) {
        boolean MapValid =true;

        if (checkMapNextCell(new Position(0, 0), 0) == (sizeX*2) + (sizeY-2)*2) {
            for (FoodSupply foodSupply : foodSupplyCol.getSupplies()) {
                if (!Pathfinding.isThereAPath(anthill.getPosition(),foodSupply.getPosition())) {
                    MapValid =false;
                    break;
                }
            }
        } else {
            MapValid =false;
        }
        return MapValid;
    }


    public int checkMapNextCell(Position position, int count){
        Position nextPosition = position.nextPositionAroundMap(this);

        if (count != (sizeX*2) + (sizeY-2)*2) {
            System.out.println(position.getX()+ " "+position.getY());
            if ( !getCellPosition(nextPosition).isWalkable()) {
                return checkMapNextCell(nextPosition, count+1);
            } else {
                return -1;
            }
        } else {
            return count;
        }
    }
}
