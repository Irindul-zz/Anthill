package main;

import javafx.application.Application;
import main.View.ColonyDisplay;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        //Colony c = new Colony();

        Application.launch(ColonyDisplay.class);
        //c.run();
    }
}