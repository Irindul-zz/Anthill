package main.View;

import com.sun.javafx.geom.Rectangle;
import com.sun.org.apache.xpath.internal.operations.And;
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
        ant.setCenterX(position.getX()*15);
        ant.setCenterY(position.getY()*15);
        ant.setRadius(10);
        ant.setFill(Color.RED);

        this.getChildren().add(ant);
    }

    public void setPosition(Position position){
        this.setTranslateX(position.getX()*30);
        this.setTranslateY(position.getY()*30);
    }
}
