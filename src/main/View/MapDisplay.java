package main.View;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Mapping.Map;

/**
 * Created by Eriwyr on 25/01/2017.
 */
public class MapDisplay extends Parent {
    public static Map map;

    public MapDisplay(Map map) {
    this.map=map;

        for (int i = 0; i < map.getSizeX(); i++) {
            for (int j = 0; j < map.getSizeY(); j++) {
                if (map.getCellXY(i, j).isWalkable()) {

                    Rectangle wall = new Rectangle();
                    wall.setHeight(30);
                    wall.setWidth(30);
                    wall.setX(i * 30);
                    wall.setY(j * 30);
                    // wall.heightProperty().bind(scene.heightProperty());
                    wall.setFill(Color.LIMEGREEN);
                    this.getChildren().add(wall);

                } else {
                    Rectangle ground = new Rectangle();
                    ground.setHeight(30);
                    ground.setWidth(30);
                    ground.setX(i * 30);
                    ground.setY(j * 30);
                    ground.setFill(Color.BLACK);
                    this.getChildren().add(ground);
                }
            }
        }
    }
}
