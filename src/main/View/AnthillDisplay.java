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
        anthill.setHeight((ColonyDisplay.heightRectangle));
        anthill.setWidth((ColonyDisplay.widthRectangle));
        anthill.setX((position.getX()*(ColonyDisplay.heightRectangle)));
        anthill.setY((position.getY()*(ColonyDisplay.widthRectangle)));
        anthill.setFill(Color.DARKOLIVEGREEN);
        this.getChildren().add(anthill);
    }
}
