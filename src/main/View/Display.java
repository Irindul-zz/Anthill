package main.View;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import main.Mapping.Map;


public class Display extends Application{


    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(750+16);
        stage.setHeight(750+38);
        stage.setTitle("Anthill");

        Scene scene = MapScene.getScene();
        stage.setScene(scene);
        stage.show();
    }
}