package main.View;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Mapping.Position;

/**
 * Created by Eriwyr on 27/01/2017.
 */
public class FoodSupplyDisplay extends Parent{

    private Position position;
    private int id;

    public FoodSupplyDisplay(Position position, int id ){
        this.id=id;
        this.position = position;
        Rectangle foodSupply = new Rectangle();
        foodSupply.setHeight(20);
        foodSupply.setWidth(20);
        foodSupply.setX((position.getX()*30)+5);
        foodSupply.setY((position.getY()*30)+5);
        foodSupply.setFill(Color.STEELBLUE);
        this.getChildren().add(foodSupply);
    }

    public Position getPosition() {
        return position;
    }
}
