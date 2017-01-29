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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class ColonyDisplay extends Application{

    public static Map map;
    public static double heightRectangle;
    public static double widthRectangle;
    private Group group_sim;
    private Scene scene_sim;
    private Scene scene_menu;
    private Stage stage;
    public static AntDisplay[] antsDisplay;
    public static AnthillDisplay anthillDisplay;
    public static List<FoodSupplyDisplay> foodSuppliesDisplay;
    public static List<PheromoneDisplay> pheromonesDisplay;
    private Text text_mapReader;
    private Button button_basicAnts;
    private Button button_brainyAnts;
    private Text text_iterations;
    private Colony c;
    private double simulationSpeed = 0.5;
    private String mapName;
    private int antAmount = 50;
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
                text_iterations.setText((int)c.getIterations() + " iterations");
            }
            else
            {
                System.out.println("Simulation finished after " + c.getIterations() + " iterations");
                colonyTimer.stop();
            }
        }
    }));

    @Override
    public void start(Stage stage) throws Exception {
        //sleep(10);
        this.stage = stage;

        stage.setWidth(750);
        stage.setHeight(750);

        stage.setResizable(false);
        stage.setTitle("Anthill");

        Group group_menu = new Group();
        scene_menu = new Scene(group_menu);
        scene_menu.setFill(Color.AZURE);
        scene_menu.getStylesheets().add("style.css");

        //// SETTING THE MAIN MENU ////
        Text text = new Text("Please choose the intelligence for the ants");
        Label label_chooseAnts = new Label(text.getText());
        text.setId("menuTitle");
        text.applyCss();
        text.setLayoutY(40);
        text.setLayoutX(45);
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

            HBox hb_antAmount = new HBox();
                hb_antAmount.setAlignment(Pos.CENTER);

                Text text_antAmountTitle = new Text("Number of ants: ");
                text_antAmountTitle.setId("text_antAmountTitle");
                text_antAmountTitle.applyCss();

                TextField text_antAmount = new TextField(Integer.toString(this.antAmount));
                text_antAmount.setPrefWidth(100);
                text_antAmount.setId("text_antAmount");
                text_antAmount.applyCss();

                Text text_antAmountWarning = new Text("  WARNING: more than 100 ants could cause lag on some PCs");
                text_antAmountWarning.setVisible(false);
                text_antAmountWarning.setLayoutY(20);
                text_antAmountWarning.setId("text_antAmountWarning");
                text_antAmountWarning.applyCss();

                text_antAmount.textProperty().addListener((observable, oldValue, newValue) -> {
                    if(newValue.matches("[0-9]+"))
                    {
                        this.antAmount = Integer.parseInt(newValue);
                    }
                    else if(newValue.isEmpty())
                    {
                        this.antAmount = Integer.parseInt(oldValue);
                    }
                    else
                    {
                        text_antAmount.setText(oldValue);
                    }

                    if(this.antAmount > 100)
                    {
                        text_antAmountWarning.setVisible(true);
                    }
                    else
                    {
                        text_antAmountWarning.setVisible(false);
                    }

                });

            hb_antAmount.getChildren().addAll(text_antAmountTitle, text_antAmount, text_antAmountWarning);

            // Map select box
            HBox hb_mapSelect = new HBox();
                hb_mapSelect.setAlignment(Pos.CENTER);

                Text text_mapSelect = new Text("Map: ");
                text_mapSelect.setLayoutY(20);
                text_mapSelect.setId("text_mapSelect");
                text_mapSelect.applyCss();

                ChoiceBox cb_mapSelect = new ChoiceBox<String>(selections);

                cb_mapSelect.getSelectionModel().selectedIndexProperty()
                        .addListener(new ChangeListener<Number>() {
                            public void changed(ObservableValue ov, Number value, Number new_value) {
                                setMapName(selections.get(new_value.intValue()).toString());
                            }
                        });
                if(selections.size() > 0)
                {
                    cb_mapSelect.getSelectionModel().select(0);
                }

                this.text_mapReader = new Text("");
                this.text_mapReader.setId("text_mapReader");
                this.text_mapReader.applyCss();

        hb_mapSelect.getChildren().addAll(text_mapSelect, cb_mapSelect, this.text_mapReader);


        vbButtons.getChildren().addAll(button_basicAnts,button_brainyAnts, hb_antAmount, hb_mapSelect);
        group_menu.getChildren().add(vbButtons);



        //// SETTING LISTENERS FOR MAIN MENU ////
        button_basicAnts.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                ///////SET LEVEL OF ANTS
                startSimulation(0);
            }
        });

        button_brainyAnts.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                startSimulation(1);

            }
        });


        //initializeSimulationDisplay();
        colonyTimer.setCycleCount(Timeline.INDEFINITE);

        stage.setScene(scene_menu);
        stage.show();

    }

    public void setMapName(String mapName)
    {
        this.mapName = mapName;
    }
    public void startSimulation(int AILevel)
    {
        startNewColony();

        if(this.c.getMapHalth())
        {

            if(AILevel == 1)
            {
                System.out.println("Setting to intelligent ant brain");
                this.c.changeAntBrain();
            }
            stage.setScene(scene_sim);
            colonyTimer.play();
        }
    }


    public void startNewColony() {
        text_mapReader.setVisible(true);
        text_mapReader.setText("Loading map...");
        text_mapReader.setId("text_mapReader");
        text_mapReader.applyCss();
        button_basicAnts.setDisable(true);
        button_brainyAnts.setDisable(true);

        try {
            this.c = new Colony(this.mapName, this.antAmount);
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong when reading the map");
        }
        if(this.c.getMapHalth())
        {
            text_mapReader.setVisible(false);
            initializeSimulationDisplay();
        }
        else
        {
            text_mapReader.setText("Sorry, but the map is not valid");
            text_mapReader.setId("text_mapError");
            text_mapReader.applyCss();
        }
        button_basicAnts.setDisable(false);
        button_brainyAnts.setDisable(false);
    }

    public void initializeSimulationDisplay()
    {
        heightRectangle = 750/map.getSizeY();
        widthRectangle =  750/map.getSizeX();
        pheromonesDisplay = new ArrayList<>();
        foodSuppliesDisplay = new ArrayList<>();

        stage.setWidth(widthRectangle*map.getSizeX()+(scene_menu.getWindow().getWidth() - scene_menu.getWidth()));
        stage.setHeight(heightRectangle*map.getSizeY()+(scene_menu.getWindow().getHeight() - scene_menu.getHeight()));
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
            hBox_speedSelector.setAlignment(Pos.CENTER);
            hBox_speedSelector.setSpacing(10);
            hBox_speedSelector.setLayoutY(10);
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

        HBox hb_iterations = new HBox();
            hb_iterations.setAlignment(Pos.CENTER);
            hb_iterations.setSpacing(10);
            hb_iterations.setLayoutY(stage.getHeight()-50);
            hb_iterations.setPadding(new Insets(0, 20, 10, 20));
            hb_iterations.setPrefWidth(stage.getWidth()-16);

            this.text_iterations = new Text(0 + " iterations");
            text_iterations.setId("text_iterations");
            text_iterations.applyCss();
        hb_iterations.getChildren().add(text_iterations);
        group_sim.getChildren().add(hb_iterations);

        Button button_mainMenu = new Button("Main menu");
        button_mainMenu.setId("dark-blue");
        button_mainMenu.applyCss();
        group_sim.getChildren().add(button_mainMenu);
        //// SETTING LISTENERS FOR SIMULATOR

        button_mainMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                colonyTimer.stop();
                stage.setWidth(750);
                stage.setHeight(750);
                stage.setScene(scene_menu);
            }
        });

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
    }

}