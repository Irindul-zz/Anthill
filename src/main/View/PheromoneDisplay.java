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
        pheromone.setHeight(10);
        pheromone.setWidth(10);
        pheromone.setX((position.getX()*30)+10);
        pheromone.setY((position.getY()*30)+10);
        pheromone.setFill(Color.RED);
        this.getChildren().add(pheromone);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setId(int id) {
        this.id = id;
    }
}
