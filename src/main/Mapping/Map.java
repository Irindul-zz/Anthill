package main.Mapping;

import main.Anthill.Anthill;
import main.Collections.FoodSupplyCol;
import main.Collections.PheromoneCol;
import main.Element.Cell;

/**
 * Created by Irindul on 25/12/2016.
 */
public class Map {

    private Cell[][] cells;

    /* Might be some things to add here*/
    /* Don't know what constructors to add*/
    public void generateMap() {

    }

    public Cell getCell(int i, int j){
        return cells[i][j];
    }

}
