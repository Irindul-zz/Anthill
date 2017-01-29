package main.Model.Ant;

import main.Model.Element.Cell;
import main.Model.Mapping.Direction;
import main.Model.Mapping.Position;


class EvolvedSensor extends BasicSensor {
    @Override
    public void detectObstacles(Position pos, Cell[] cells) {

        //Foreach direction possible, we check if the cell is walkable, it is, then it's not an obstacle
        obstacles[Direction.NORTH.ordinal()] = !(cells[Direction.NORTH.ordinal()].isWalkable());
        obstacles[Direction.NORTHEAST.ordinal()] = !(cells[Direction.NORTHEAST.ordinal()].isWalkable());
        obstacles[Direction.EAST.ordinal()] = !(cells[Direction.EAST.ordinal()].isWalkable());
        obstacles[Direction.SOUTHEAST.ordinal()] = !(cells[Direction.SOUTHEAST.ordinal()].isWalkable());
        obstacles[Direction.SOUTH.ordinal()] = !(cells[Direction.SOUTH.ordinal()].isWalkable());
        obstacles[Direction.SOUTHWEST.ordinal()] = !(cells[Direction.SOUTHEAST.ordinal()].isWalkable());
        obstacles[Direction.WEST.ordinal()] = !(cells[Direction.WEST.ordinal()].isWalkable());
        obstacles[Direction.NORTHEAST.ordinal()] = !(cells[Direction.NORTHEAST.ordinal()].isWalkable());
    }
}
