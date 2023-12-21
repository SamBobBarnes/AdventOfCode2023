package Day17;

import Base.AdventBase;

import static java.lang.Integer.parseInt;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(17, 1, example);

        char[][] input = LoadInputChars(17, example).toArray(char[][]::new);

        Node[][] nodes = new Node[input.length][input[0].length];

        for(int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[0].length; x++) {
                nodes[y][x] = new Node(x,y,parseInt(String.valueOf(input[y][x])));
            }
        }

        for(int y = 0; y < nodes.length; y++) {
            for (int x = 0; x < nodes[0].length; x++) {
                if(y > 0) {
                    var north = nodes[y-1][x];
                    nodes[y][x].AddNeighbor(north);
                }
                if(y < nodes.length-1) {
                    var south = nodes[y+1][x];
                    nodes[y][x].AddNeighbor(south);
                }
                if(x > 0) {
                    var west = nodes[y][x-1];
                    nodes[y][x].AddNeighbor(west);
                }
                if(x < nodes[0].length-1) {
                    var east = nodes[y][x+1];
                    nodes[y][x].AddNeighbor(east);
                }
            }
        }

        return 0;
    }
}