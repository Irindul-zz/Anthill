package main.Model.Element;


public class Cell {
    private boolean isWalkable;

    public Cell(boolean isWalkable){
        this.isWalkable = isWalkable;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public char getChar(){
        if (!isWalkable) return '#';
        else return ' ';
    }

}
