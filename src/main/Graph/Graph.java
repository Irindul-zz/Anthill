package main.Graph;

import main.Mapping.Map;
import main.Mapping.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irindul on 27/01/2017.
 */
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
                nodes[i][j] = new Node(new Position(i, j));
            }
        }

        for (int i = 0;i < map.getSizeX(); i++){
            for (int j = 0; j < map.getSizeY(); j++) {
                this.add(nodes[i][j]);

               /*




                i j+1;
                i j-1:
                i+1 j-1;
                i - 1 j+1;*/
                if(map.getCellXY(i, j).isWalkable()) {
                    x = i+1;
                    y = j;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }

                    x = i+1;
                    y = j+1;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }

                    x = i;
                    y = j+1;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }

                    x = i-1;
                    y = j+1;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }

                    x = i-1;
                    y = j;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }

                    x = i-1;
                    y = j-1;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }

                    x = i;
                    y = j-1;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }

                    x = i+1;
                    y = j-1;
                    if(isInRange(map, x, y)){

                        if(map.getCellXY(x, y).isWalkable()){
                            nodes[i][j].link(nodes[x][y], 1);
                        }
                    }
                }

            }
        }

    }

    public void add(Node node) {
        nodes.add(node);
    }

    public List<Node> neighbours(Node n){
        List<Node> neighbors = new ArrayList<>();

        for (Edge e: n.getEdges()) {
            neighbors.add(e.getNext());
        }

        return neighbors;

    }

    private boolean isInRange(Map map, int x, int y){
        if(x >= 0 && y >= 0 && x < map.getSizeX() && y < map.getSizeY())
            return true;
        return false;
    }

    public Node nodeFromPosition(Position pos){
        for (Node n: nodes) {
            if(n.getPosition().equals(pos))
                return n;
        }

        return null;
    }
}
