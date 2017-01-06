package main.Ant;

import main.Brain.Memory;
import main.Mapping.Direction;

/**
 * Created by Irindul on 05/01/2017.
 */
public class Mind implements Memory {


    //Simple implementation of a Stack with linked-list.
    private Node first = null;


    //The node is static so that it doesn't take much memory access and assure that it cannot use Mind attributes.
    private static class Node {
        private Direction dir;
        private Node next;

        public Node(Direction dir) {
            this.dir = dir;
        }
    }

    @Override
    public void keepTrack(Direction d) {
        Node newFirst = new Node(d); //We create a new Node
        newFirst.next = first; //We tell the new node that the node next to it is first.
        first = newFirst; //The new first is now the new node created.

    }

    @Override
    public Direction rollBack() {
        Node oldFirst = first; //We save the current first element.
        first = first.next; // We say that the new first is the element right after.

        //We return a Direction reversed so that we can go back.
        return Direction.reverse(oldFirst.dir);
    }
}
