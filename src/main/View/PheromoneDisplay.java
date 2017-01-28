package main.View;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Mapping.Position;

/**
 * Created by Eriwyr on 27/01/2017.
 */
public class PheromoneDisplay extends Parent {
    private Position position;
    private int id;

    public PheromoneDisplay(Position position, int id){
        this.id=id;
        this.position = position;
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

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setId(int id) {
        this.id = id;
    }
}
