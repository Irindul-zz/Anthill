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

        this.setTranslateX((position.getX()*30)+10);
        this.setTranslateY((position.getY()*30)+10);

        ant.setRadius(10);
        ant.setFill(Color.RED);
        this.getChildren().add(ant);
    }

    public void setPosition(Position position){
        this.setTranslateX((position.getX()*30)+15);
        this.setTranslateY((position.getY()*30)+15);
    }

    public Position getPosition() {
        return position;
    }
}
