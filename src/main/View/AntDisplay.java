package main.View;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.Model.Mapping.Position;

public class AntDisplay extends Parent{
    private Position position;

    public AntDisplay(Position position){

        Circle ant = new Circle();
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
