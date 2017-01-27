package main.View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Anthill.Colony;
import main.Mapping.Map;

import static java.lang.Thread.sleep;


public class ColonyDisplay extends Application{

    public static Map map;
    public static AntDisplay[] antsDisplay;
    private Colony c;
//TODO : les fourmis sortent de la boite
    Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.millis(60), new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            c.update();
        }
    }));

    @Override
    public void start(Stage stage) throws Exception {
        //sleep(10);
        this.c = new Colony();
        stage.setWidth(750+16);
        stage.setHeight(750+38);
        stage.setTitle("Anthill");

        Group root = new Group();
        Scene scene = new Scene(root);


        MapDisplay mapD = new MapDisplay(map);
        root.getChildren().add(mapD);
        for (AntDisplay antD: antsDisplay) {
            root.getChildren().add(antD);
        }
        System.out.println("hej");
        //ColonyDisplay.antsDisplay[i].setPosition(ant.getPosition());
        //ant.setOnKeyPressed(keyEvent -> ant.setPosition(p2));

        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();

        stage.setScene(scene);
        stage.show();


    }
}