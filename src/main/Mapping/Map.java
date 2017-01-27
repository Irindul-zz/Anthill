package main.Mapping;
import main.Element.Cell;

import java.io.*;
import java.util.Scanner;

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
}
