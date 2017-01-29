package main.View;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Mapping.Map;

public class MapDisplay extends Parent {
    public static Map map;

    public MapDisplay(Map map) {
    MapDisplay.map =map;

        for (int i = 0; i < map.getSizeX(); i++) {
            for (int j = 0; j < map.getSizeY(); j++) {
                if (map.getCellXY(i, j).isWalkable()) {
                  //  int heightRectangle =  750/map.getSizeX();
                    //int widthRectangle =  750/map.getSizeY();
                    Rectangle wall = new Rectangle();
                    wall.setHeight(ColonyDisplay.widthRectangle);
                    wall.setWidth(ColonyDisplay.heightRectangle);
                    wall.setX(i * ColonyDisplay.widthRectangle);
                    wall.setY(j * ColonyDisplay.heightRectangle);
                    // wall.heightProperty().bind(scene.heightProperty());
                    wall.setFill(Color.LIMEGREEN);
                    this.getChildren().add(wall);

                } else {
                    Rectangle ground = new Rectangle();
                    ground.setHeight(ColonyDisplay.widthRectangle);
                    ground.setWidth(ColonyDisplay.heightRectangle);
                    ground.setX(i *  ColonyDisplay.widthRectangle);
                    ground.setY(j *ColonyDisplay.heightRectangle);
                    ground.setFill(Color.BLACK);
                    this.getChildren().add(ground);
                }
            }
        }
    }
}
