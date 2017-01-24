package main;

import main.Mapping.Map;
import main.View.Display;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        Map map = null;
        try {
            map = new Map("src"+ File.separator+ "main" + File.separator + "map"+ File.separator + "map1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       Display display = new Display(map);
    try {
        Display.launch(Display.class);

    } catch (Exception e) {

    }
    }
}
