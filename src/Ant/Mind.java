package Ant;

import Brain.Memory;
import Mapping.Direction;

/**
 * Created by Irindul on 05/01/2017.
 */
public class Mind implements Memory {


    //Simple implementation of a Stack with linked-list.
    private Node first = null;

    private static class Node {
        private Direction dir;
        private Node next;

        public Node(Direction dir) {
            this.dir = dir;
        }
    }

    @Override
    public void keepTrack(Direction d) {
        Node newFirst = new Node(d);
        newFirst.next = first;
        first = newFirst;

    }

    @Override
    public Direction rollBack() {
        Node oldFirst = first;
        first = first.next;

        return Direction.reverse(oldFirst.dir);
    }
}
