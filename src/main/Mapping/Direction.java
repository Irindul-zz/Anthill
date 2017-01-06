package main.Mapping;

/**
 * Created by Irindul on 02/01/2017.
 */
public enum Direction {

    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public  static  Direction reverse(Direction d){

        return Direction.values()[(d.ordinal() + 4) % 8];

    }



}
