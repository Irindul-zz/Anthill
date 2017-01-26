package main.View;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Mapping.Map;

import static java.lang.Thread.sleep;


public class ColonyDisplay extends Application{

    public static Map map;
    public static AntDisplay[] antsDisplay;

    @Override
    public void start(Stage stage) throws Exception {
        //sleep(10);
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

        //ant.setOnKeyPressed(keyEvent -> ant.setPosition(p2));



        stage.setScene(scene);
        stage.show();


    }
}