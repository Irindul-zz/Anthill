package main.Element;


public class Cell {
    private boolean isWalkable;

    public Cell(boolean isWalkable){
        this.isWalkable = isWalkable;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public char getChar(){ //TODO:remove this function
        if (!isWalkable) return '#';
        else return ' ';
    }
    public void setWalkable(boolean walkable) {
        isWalkable = walkable;
    }
}
