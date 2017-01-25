package main;

import javafx.application.Application;
import main.Mapping.Map;
import main.View.Display;
import main.View.MapScene;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here


        Map map = new Map("src"+ File.separator+ "main" + File.separator + "map"+ File.separator + "map1.txt");

        MapScene mapScene = new MapScene(map);
        try {
            Application.launch(Display.class);

        } catch (Exception e) {

        }
    }
}
