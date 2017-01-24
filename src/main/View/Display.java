package main.View;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import main.Mapping.Map;


public class Display extends Application{

    private Map map;
    /* public static void main(String[] args) throws FileNotFoundException {
         System.out.println( "Main method inside Thread : " +  Thread.currentThread().getName());
         Map main.map = new Map("C:\\Users\\Eriwyr\\Documents\\3A-info\\Java\\testFX\\src\\sample\\map1.txt");
         main.map.display();
         launch(args);
     }*/
    public Display(Map map) throws FileNotFoundException {
        this.map = map; // File.separtor  : Unix = / ; Windows : \ ;
        //launch(Display.class);
    }
    /* public MyFirstJfxApplication(Map main.map) {
         this.main.map = main.map;
     }*/
    @Override
    public void start(Stage stage) throws Exception {
        // définit la largeur et la hauteur de la fenêtre
        // en pixels, le (0, 0) se situe en haut à gauche de la fenêtre
        System.out.println(map.getSizeX());
        System.out.println(map.getSizeY());
        stage.setWidth(750+16);
        stage.setHeight(750+38);
        // met un titre dans la fenêtre
        stage.setTitle("Anthill");

        // la racine du sceneGraph est le root
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.setFill(Color.DARKGREY);
        System.out.println(map.getSizeX());
        for(int i = 0; i<map.getSizeX();i++){
            for(int j=0; j<map.getSizeY();j++){
                if(map.getCellXY(i,j).isWalkable()){

                    Rectangle wall = new Rectangle();
                    wall.setHeight(30);
                    wall.setWidth(30);
                    wall.setX(i*30);
                    wall.setY(j*30);
                    // wall.heightProperty().bind(scene.heightProperty());
                    System.out.println(wall.getX());
                    System.out.println(wall.getY());
                    wall.setFill(Color.GREEN);
                    root.getChildren().add(wall);

                }
                else{
                    Rectangle ground = new Rectangle();
                    ground.setHeight(30);
                    ground.setWidth(30);
                    ground.setX(i*30);
                    ground.setY(j*30);
                    ground.setFill(Color.BLACK);
                    root.getChildren().add(ground);
                }
            }
        }




        stage.show();
    }
}