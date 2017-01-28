package main.View;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.Mapping.Position;

/**
 * Created by Eriwyr on 25/01/2017.
 */
public class AntDisplay extends Parent{
    private Position position;
    private int id;

    public AntDisplay(Position position, int id){

        Circle ant = new Circle();
        this.id=id;
        this.position = position;

        double offsetHeight = ((ColonyDisplay.heightRectangle)/2);
        double offsetWidth = ((ColonyDisplay.widthRectangle)/2);

        this.setTranslateX((position.getX()*ColonyDisplay.heightRectangle)+offsetHeight);
        this.setTranslateY((position.getY()*ColonyDisplay.widthRectangle)+offsetWidth);

        ant.setRadius((ColonyDisplay.heightRectangle)/3);
        ant.setFill(Color.FIREBRICK);
        this.getChildren().add(ant);
    }

    public void setPosition(Position position){
        double offsetHeight = ((ColonyDisplay.heightRectangle)/2);
        double offsetWidth = ((ColonyDisplay.widthRectangle)/2);
        this.setTranslateX((position.getX()*ColonyDisplay.heightRectangle)+offsetHeight);
        this.setTranslateY((position.getY()*ColonyDisplay.widthRectangle)+offsetWidth);
    }

    public Position getPosition() {
        return position;
    }
}
