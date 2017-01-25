package main.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Mapping.Map;

/**
 * Created by Eriwyr on 25/01/2017.
 */
public class MapScene {

    public static Map map;

    public MapScene(Map map){
        this.map = map;
    }
    public static Scene getScene(){
        Group root = new Group();
        Scene scene = new Scene(root);

        scene.setFill(Color.DARKGREY);
        for(int i = 0; i<map.getSizeX();i++){
            for(int j=0; j<map.getSizeY();j++){
                if(map.getCellXY(i,j).isWalkable()){

                    Rectangle wall = new Rectangle();
                    wall.setHeight(30);
                    wall.setWidth(30);
                    wall.setX(i*30);
                    wall.setY(j*30);
                    // wall.heightProperty().bind(scene.heightProperty());
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

        return scene;

    }
}
