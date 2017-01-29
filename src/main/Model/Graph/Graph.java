package main.Model.Graph;

import main.Model.Mapping.Map;
import main.Model.Mapping.Position;

import java.util.ArrayList;
import java.util.List;


public class Graph {

    private List<Node> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public Graph(Map map){
        this();
        Node[][] nodes = new Node[map.getSizeX()][map.getSizeY()];
        int x, y;
        for (int i = 0; i < map.getSizeX(); i++) {
            for (int j = 0; j < map.getSizeY(); j++) {
                nodes[i][j] = new Node(new Position(i, j)); //We create a node for each cell of the map
            }
        }

        for (int i = 0;i < map.getSizeX(); i++){
            for (int j = 0; j < map.getSizeY(); j++) {

                if(map.getCellXY(i, j).isWalkable()) { //If the element is not a obstacle

                    this.add(nodes[i][j]); //We can add it to our graph
                    x = i+1;
                    y = j;

                    //We then check if the cells of each direction is in the range and is walkable
                    //And if it is we can link the two nodes.
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }


                    //Diagonal is not linked here for optimisation reasons.

                    /*x = i+1;
                    y = j+1;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }*/

                    x = i;
                    y = j+1;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }

                    //Diagonal is not linked here for optimisation reasons.

                    /*x = i-1;
                    y = j+1;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }*/

                    x = i-1;
                    y = j;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }

                    //Diagonal is not linked here for optimisation reasons.

                    /*x = i-1;
                    y = j-1;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }*/

                    x = i;
                    y = j-1;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }

                    //Diagonal is not linked here for optimisation reasons.

                    /*x = i+1;
                    y = j-1;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }*/
                }

            }
        }

    }

    public void add(Node node) {
        nodes.add(node);
    }

    public List<Node> neighbours(Node n){
        List<Node> neighbors = new ArrayList<>();

        for (Edge e: n.getEdges()) { //We list every node that has a link with the node n
            neighbors.add(e.getNext());
        }

        return neighbors;

    }

    private boolean isInRange(Map map, int x, int y){
        return x >= 0 && y >= 0 && x < map.getSizeX() && y < map.getSizeY();
        //Check if the cell at coordonate x and y is in the range
    }

    public Node nodeFromPosition(Position pos){
        for (Node n: nodes) {
            if(n.getPosition().equals(pos))
                return n;
        }
        //Return the node corresponding to the position
        return null;
    }

    public int size(){
        return nodes.size();
    }
}
