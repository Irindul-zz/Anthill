package main.View;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Mapping.Position;

/**
 * Created by Eriwyr on 28/01/2017.
 */
public class AnthillDisplay extends Parent {
    private Position position;

    public AnthillDisplay(Position position){
        this.position = position;
        Rectangle anthill = new Rectangle();
        anthill.setHeight(30);
        anthill.setWidth(30);
        anthill.setX((position.getX()*30));
        anthill.setY((position.getY()*30));
        anthill.setFill(Color.DARKOLIVEGREEN);
        this.getChildren().add(anthill);
    }
}
