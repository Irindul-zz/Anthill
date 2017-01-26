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

    public Map(String fileName) throws FileNotFoundException {
        fileMap = new File(fileName);
        FileReader fr = new FileReader(fileMap);
        BufferedReader br = new BufferedReader(fr);

        try{
            //we get the size main.map
            sizeX = Integer.parseInt(br.readLine());
            sizeY = Integer.parseInt(br.readLine());

            // We initialize the main.map at the correct size
            map = new Cell[sizeX][sizeY];

            //we get the number of food supply on the main.map
            nbFoodSupply = Integer.parseInt(br.readLine());

            // We get for each food supply the quantity in int array.
            foodSupplyQuantities = new int[nbFoodSupply];
            Scanner scan = new Scanner(br.readLine());
            int i =0;
            while(scan.hasNext()){
                foodSupplyQuantities[i]=scan.nextInt();
                i++;
            }

            //We fill the cells array with wall or ground
            for(i = 0 ; i <sizeY ; i++){
                String line = br.readLine();
                int j =0;
                for (char ch: line.toCharArray()) {
                    switch (ch){ //We have used a switch if one day we want to add another element on the main.map (water, fire, snow etc...)
                        case '#':
                            map[j][i] = new Cell(false); //if is '#' we have a wall, Cell => is Not Walkable
                            break;
                        default :
                            map[j][i] = new Cell(true); //if is ' ' we have ground, Cell => is Walkable
                            break;
                    }
                    j++;
                }
            }
        }
        catch(IOException e){
            System.out.println ("Erreur lors de la lecture : " + e.getMessage());

        }
    }

    public void display(){
        for(int i = 0; i<sizeX;i++){
            for(int j =0; j<sizeY; j++){
                System.out.print(map[i][j].getChar());
            }
            System.out.println("\n");
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
}
