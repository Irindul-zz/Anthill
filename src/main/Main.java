package main;

import javafx.application.Application;
import main.Anthill.Colony;
import main.Mapping.Map;
import main.View.ColonyDisplay;
import main.View.MapScene;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        Colony c = new Colony();

        c.run();
    }
}