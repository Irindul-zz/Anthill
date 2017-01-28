package main.Mapping;

import main.Anthill.Anthill;
import main.Collections.FoodSupplyCol;
import main.Element.Cell;
import main.Element.FoodSupply;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Eriwyr on 27/01/2017.
 */
public class ReadFiles {
    File fileMap;


    public ReadFiles(String fileName) throws IOException {
        fileMap = new File(fileName);
    }

        public void readFile(Map map, FoodSupplyCol foodSupplies, Anthill anthill) throws FileNotFoundException {



            //Start reading file
            java.io.FileReader fr = new java.io.FileReader(fileMap);
            BufferedReader br = new BufferedReader(fr);

            try{
                //we get the map's sizes
                int sizeX = Integer.parseInt(br.readLine());
                int sizeY = Integer.parseInt(br.readLine());

                map.setSizeX(sizeX);
                map.setSizeY(sizeY);

                // We initialize the map at the correct size
                map.setMap(new Cell[sizeX][sizeY]);

                //we get the number of food supply on the main.map
                int nbFoodSupply = Integer.parseInt(br.readLine());

                // We get for each food supply the quantity in int array.
                int [] foodSupplyQuantities = new int[nbFoodSupply];
                Scanner scan = new Scanner(br.readLine());
                int i =0;
                while(scan.hasNext()){
                   // foodSupplies.add(new FoodSupply());
                    foodSupplyQuantities[i]=scan.nextInt();
                    i++;
                }

                int indexFoosSupplyQty = 0;
                //We fill the cells array with wall or ground
                for(i = 0 ; i <sizeY ; i++){
                    String line = br.readLine();
                    int j =0;
                    for (char ch: line.toCharArray()) {
                        switch (ch){ //We have used a switch if one day we want to add another element on the main.map (water, fire, snow etc...)
                            case '#':
                                map.setCellXY(j, i,  new Cell(false)); //if is '#' we have a wall, Cell => is Not Walkable
                                break;
                            case 'o':
                                foodSupplies.addFoodSupply(new FoodSupply(new Position(j, i), foodSupplyQuantities[indexFoosSupplyQty]));
                                indexFoosSupplyQty++;
                                map.setCellXY(j, i,  new Cell(true));
                                break;
                            case 'x' :
                                anthill.setPosition(new Position(j, i));
                                anthill.declareAnts();
                                map.setCellXY(j, i,  new Cell(true));
                                break;
                            default :
                                map.setCellXY(j, i,  new Cell(true)); //if is ' ' we have ground, Cell => is Walkable
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


}
