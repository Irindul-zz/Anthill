package main.Model.Mapping;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public boolean equals(Position pos) {
        return (this.x == pos.x && this.y == pos.y);
    }

    public Position nextPositionAroundMap(Map map) {
        if (y==0 && x<map.getSizeX()-1) {
            return new Position(x+1, y);

        } else if (x == 0 && y>0) {
            return new Position(x, y-1);

        } else if (x == map.getSizeX()-1 && y<map.getSizeY()-1) {
            return new Position(x, y+1);

        } else if (y == map.getSizeY()-1 && x>0) {
            return new Position(x-1, y);
        }
        else return new Position(0, 0);
    }
}
