package main.View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Anthill.Colony;
import main.Mapping.Map;
import main.Mapping.ReadFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ColonyDisplay extends Application{

    public static Map map;
    Group group_sim;
    Scene scene_sim;
    Stage stage;
    public static AntDisplay[] antsDisplay;
    public static AnthillDisplay anthillDisplay;
    public static List<FoodSupplyDisplay> foodSuppliesDisplay;
    public static List<PheromoneDisplay> pheromonesDisplay;
    private Text text_mapReader;
    private Button button_basicAnts;
    private Button button_brainyAnts;
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
            //System.out.println("testing");
        }
    }));

    @Override
    public void start(Stage stage) throws Exception {
        //sleep(10);
        this.stage = stage;


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

            this.button_basicAnts = new Button("Basic Ants");
            this.button_basicAnts.setId("dark-blue");
            this.button_basicAnts.applyCss();
            this.button_basicAnts.setMaxWidth(Double.MAX_VALUE);
            this.button_basicAnts.setPrefHeight(100);

            this.button_brainyAnts = new Button("Brainy Ants");
            this.button_brainyAnts.setId("dark-blue");
            this.button_brainyAnts.applyCss();
            this.button_brainyAnts.setMaxWidth(Double.MAX_VALUE);
            this.button_brainyAnts.setPrefHeight(100);


            this.button_basicAnts.setDisable(true);
            this.button_brainyAnts.setDisable(true);

            this.text_mapReader = new Text("");
            this.text_mapReader.setId("text_mapReader");
            this.text_mapReader.applyCss();

            File mapFolder = new File("src" + File.separator + "main/map");
            File[] listOfMaps = mapFolder.listFiles();
            System.out.println("length: " + mapFolder.getAbsolutePath());

            ObservableList selections = FXCollections.observableArrayList();
            if(mapFolder.exists())
            {
                for(int i=0; i<listOfMaps.length; i++)
                {
                    if(listOfMaps[i].isFile())
                    {
                        selections.add(listOfMaps[i].getName());
                    }
                }
            }
            else
            {
                System.out.println("Map folder does not exist");
            }
            ChoiceBox cb_mapSelect = new ChoiceBox<String>(selections);

            cb_mapSelect.getSelectionModel().selectedIndexProperty()
                    .addListener(new ChangeListener<Number>() {
                        public void changed(ObservableValue ov, Number value, Number new_value) {
                            startNewColony(selections.get(new_value.intValue()).toString());
                        }
                    });
            if(selections.size() > 0)
            {
                cb_mapSelect.getSelectionModel().select(0);
            }


            vbButtons.getChildren().addAll(button_basicAnts,button_brainyAnts, cb_mapSelect, this.text_mapReader);
        group_menu.getChildren().add(vbButtons);



        //// SETTING SIMULATOR

        group_sim = new Group();
        scene_sim = new Scene(group_sim);
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
                startSimulation(0);
            }
        });

        button_brainyAnts.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                startSimulation(1);
            }
        });



        colonyTimer.setCycleCount(Timeline.INDEFINITE);

        stage.setScene(scene_menu);
        stage.show();


    }

    public void startSimulation(int AILevel)
    {
        stage.setScene(scene_sim);
        colonyTimer.play();
    }


    public void startNewColony(String path) {
        System.out.println(path);
        text_mapReader.setVisible(true);
        text_mapReader.setText("Loading map...");
        text_mapReader.setId("text_mapReader");
        text_mapReader.applyCss();
        button_basicAnts.setDisable(true);
        button_brainyAnts.setDisable(true);

        try {
            this.c = new Colony(path);
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong when reading the map");
        }
        if(this.c.getMapHalth())
        {
            text_mapReader.setVisible(false);
            button_basicAnts.setDisable(false);
            button_brainyAnts.setDisable(false);
        }
        else
        {
            text_mapReader.setText("Sorry, but the map is not valid");
            text_mapReader.setId("text_mapError");
            text_mapReader.applyCss();
        }
    }

}