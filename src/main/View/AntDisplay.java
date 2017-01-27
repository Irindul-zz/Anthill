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
        
        this.setTranslateX((position.getX()*30)+15);
        this.setTranslateY((position.getY()*30)+15);
        System.out.println("cneter :"+(ant.getCenterX()-15)/30+" "+(ant.getCenterY()-15)/30);
        ant.setRadius(15);
        ant.setFill(Color.RED);
        this.getChildren().add(ant);
    }

    public void setPosition(Position position){
        System.out.println("Position de la fourmis "+position.getX()+" "+position.getY());

        this.setTranslateX((position.getX()*30)+15);
        this.setTranslateY((position.getY()*30)+15);
    }

    public Position getPosition() {
        return position;
    }
}
