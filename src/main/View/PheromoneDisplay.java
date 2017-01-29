package main.View;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Model.Mapping.Position;

public class PheromoneDisplay extends Parent {

    public PheromoneDisplay(Position position, int id){
        Rectangle pheromone = new Rectangle();
        pheromone.setHeight((ColonyDisplay.heightRectangle)/3);
        pheromone.setWidth((ColonyDisplay.widthRectangle)/3);

        double offsetHeight = (ColonyDisplay.heightRectangle/3);
        double offsetWidth = (ColonyDisplay.widthRectangle/3);

        pheromone.setX((position.getX()*(ColonyDisplay.heightRectangle))+offsetHeight);
        pheromone.setY((position.getY()*(ColonyDisplay.heightRectangle))+offsetWidth);
        pheromone.setFill(Color.INDIANRED);
        this.getChildren().add(pheromone);
    }

}
