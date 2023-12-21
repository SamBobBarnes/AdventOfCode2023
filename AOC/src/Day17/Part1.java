package Day17;

import Base.AdventBase;

import java.util.PriorityQueue;

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

        var start = nodes[0][0];
        var end = nodes[nodes.length-1][nodes[0].length-1];

        var result = Dijkstra(start, end);

        return result;
    }

    private static int Dijkstra(Node start, Node end) {
        start.distance = 0;

        var q = new PriorityQueue<Node>();
        q.add(start);

        var stepsInOneDirection = 0;
        Direction direction;

        while(!q.isEmpty()) {
            var top = q.poll();
            top.Visit();

            if(top.equals(end)) return (int)top.distance;

            for(var neighbor: top.UnvisitedNeighbors()) {
                var newDistance = top.distance + neighbor.value;
                if(neighbor.distance > newDistance) neighbor.distance = newDistance;
                if(!q.contains(neighbor)) q.add(neighbor);
            }
        }

        return -1;
    }
}