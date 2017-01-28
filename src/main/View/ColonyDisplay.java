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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Anthill.Colony;
import main.Mapping.Map;

import java.util.ArrayList;
import java.util.List;


public class ColonyDisplay extends Application{

    public static Map map;
    Group group_sim;
    public static AntDisplay[] antsDisplay;
    public static AnthillDisplay anthillDisplay;
    public static List<FoodSupplyDisplay> foodSuppliesDisplay;
    public static List<PheromoneDisplay> pheromonesDisplay;
    private Colony c;
//TODO : les fourmis sortent de la boite

    Timeline colonyTimer = new Timeline(new KeyFrame(Duration.millis(60), new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            if(!Colony.end) {
                for (PheromoneDisplay pheromoneD : pheromonesDisplay) {
                    group_sim.getChildren().remove(pheromoneD);
                }
                pheromonesDisplay.clear();

                for (FoodSupplyDisplay foodSupplyD : foodSuppliesDisplay) {
                    group_sim.getChildren().remove(foodSupplyD);
                }
                foodSuppliesDisplay.clear();


                c.update();
                for (PheromoneDisplay pheromoneD : pheromonesDisplay) {
                    group_sim.getChildren().add(pheromoneD);
                }
                for (FoodSupplyDisplay foodSupplyD : foodSuppliesDisplay) {
                    group_sim.getChildren().add(foodSupplyD);
                }
            }
        }
    }));

    @Override
    public void start(Stage stage) throws Exception {
        //sleep(10);
        this.c = new Colony();
        pheromonesDisplay = new ArrayList<>();
        foodSuppliesDisplay = new ArrayList<>();
        stage.setWidth(750+16);
        stage.setHeight(750+38);
        stage.setTitle("Anthill");

        Group group_menu = new Group();
        Scene scene_menu = new Scene(group_menu);
        scene_menu.setFill(Color.AZURE);
        scene_menu.getStylesheets().add("style.css");

        double simulationSpeed = 0.5;

        //// SETTING THE MAIN MENU ////
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



        //// SETTING SIMULATOR

        group_sim = new Group();
        Scene scene_sim = new Scene(group_sim);
        scene_sim.getStylesheets().add("style.css");


        MapDisplay mapD = new MapDisplay(map);
        group_sim.getChildren().add(mapD);

        group_sim.getChildren().add(anthillDisplay);

        for (AntDisplay antD: antsDisplay) {
            group_sim.getChildren().add(antD);
        }

        HBox hBox_speedSelector = new HBox();
        hBox_speedSelector.setSpacing(10);
        hBox_speedSelector.setLayoutY(0);
        hBox_speedSelector.setPadding(new Insets(0, 20, 10, 20));
        hBox_speedSelector.setPrefWidth(stage.getWidth()-16);

        Text text_speedTitle = new Text("Simulation Speed: ");
            text_speedTitle.setId("text_speedTitle");
            text_speedTitle.applyCss();

        Button button_decreaseSpeed = new Button("-");
            button_decreaseSpeed.setId("button_decreaseSpeed");
            button_decreaseSpeed.applyCss();

        colonyTimer.setRate(simulationSpeed);
        TextField text_speed = new TextField(Integer.toString((int)(simulationSpeed*100)));
            text_speed.setId("text_speed");
            text_speed.applyCss();

        Button button_increaseSpeed = new Button("+");
            button_increaseSpeed.setId("button_increaseSpeed");
            button_increaseSpeed.applyCss();

        hBox_speedSelector.getChildren().addAll(text_speedTitle, button_decreaseSpeed, text_speed, button_increaseSpeed);
        group_sim.getChildren().add(hBox_speedSelector);

        //// SETTING LISTENERS FOR SIMULATOR
        text_speed.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.matches("[0-9]+"))
            {
                colonyTimer.setRate(Double.parseDouble(newValue)/100);
            }
            else if(newValue.isEmpty())
            {
                text_speed.setText("");
            }
            else
            {
                text_speed.setText(oldValue);
            }
        });

        button_decreaseSpeed.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                int amountToDecrease = -10;
                try {
                    text_speed.setText(Integer.toString(Integer.parseInt(text_speed.getText())+amountToDecrease));
                }
                catch (NumberFormatException e2){
                    text_speed.setText(Integer.toString((int)(colonyTimer.getRate()*100+amountToDecrease)));
                }
            }
        });

        button_increaseSpeed.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                int amountToDecrease = 10;
                try {
                    text_speed.setText(Integer.toString(Integer.parseInt(text_speed.getText()) + amountToDecrease));
                } catch (NumberFormatException e2) {
                    text_speed.setText(Integer.toString((int) (colonyTimer.getRate() * 100 + amountToDecrease)));
                }
            }
        });


        //ColonyDisplay.antsDisplay[i].setPosition(ant.getPosition());
        //ant.setOnKeyPressed(keyEvent -> ant.setPosition(p2));


        //// SETTING BUTTON LISTENERS FOR MAIN MENY ////
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



        colonyTimer.setCycleCount(Timeline.INDEFINITE);
        colonyTimer.play();

        stage.setScene(scene_menu);
        stage.show();


    }
}