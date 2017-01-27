package main.View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Anthill.Colony;
import main.Mapping.Map;

import static java.lang.Thread.sleep;


public class ColonyDisplay extends Application{

    public static Map map;
    public static AntDisplay[] antsDisplay;
    private Colony c;

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

        Group group_menu = new Group();
        Scene scene_menu = new Scene(group_menu);
        scene_menu.setFill(Color.AZURE);
        scene_menu.getStylesheets().add("style.css");

        Group group_sim = new Group();
        Scene scene_sim = new Scene(group_sim);



        MapDisplay mapD = new MapDisplay(map);
        group_sim.getChildren().add(mapD);
        for (AntDisplay antD: antsDisplay) {
            group_sim.getChildren().add(antD);
        }

        Text text = new Text("Please choose the intelligens for the ants");


        Label label_chooseAnts = new Label(text.getText());
            text.setId("menuTitle");
            text.applyCss();
            text.setLayoutY(40);
            text.setLayoutX(90);
        group_menu.getChildren().add(text);

        VBox vbButtons = new VBox();
            vbButtons.setSpacing(80);
            vbButtons.setLayoutY(text.getLayoutBounds().getHeight()+200);
            vbButtons.setPadding(new Insets(0, 20, 10, 20));
            vbButtons.setPrefWidth(stage.getWidth()-16);
            vbButtons.setAlignment(Pos.CENTER);

            Button button_basicAnts = new Button("Basic Ants");
            button_basicAnts.setId("dark-blue");
            button_basicAnts.applyCss();
            button_basicAnts.setMaxWidth(Double.MAX_VALUE);
            button_basicAnts.setPrefHeight(100);

            Button button_brainyAnts = new Button("Brainy Ants");
            button_brainyAnts.setId("dark-blue");
            button_brainyAnts.applyCss();
            button_brainyAnts.setMaxWidth(Double.MAX_VALUE);
            button_brainyAnts.setPrefHeight(100);

            vbButtons.getChildren().addAll(button_basicAnts,button_brainyAnts);
        group_menu.getChildren().add(vbButtons);

        button_basicAnts.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                ///////SET LEVEL OF ANTS
                stage.setScene(scene_sim);
            }
        });

        button_brainyAnts.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                stage.setScene(scene_sim);
            }
        });

        //ColonyDisplay.antsDisplay[i].setPosition(ant.getPosition());
        //ant.setOnKeyPressed(keyEvent -> ant.setPosition(p2));

        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();

        stage.setScene(scene_menu);
        stage.show();


    }
}