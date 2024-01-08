package Day21;

import Base.AdventBase;

import java.util.PriorityQueue;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(21, 1, example);

        char[][] input = LoadInputChars(21, example).toArray(char[][]::new);

        Node[][] grid = new Node[input.length][input[0].length];

        var startX = 0;
        var startY = 0;
        var width = input[0].length;
        var height = input.length;

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(input[y][x] == 'S') {
                    startX = x;
                    startY = y;
                }
                grid[y][x] = new Node(x,y,input[y][x] == '#');
            }
        }

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(y != 0) grid[y][x].North = grid[y-1][x];
                if(y < height-1) grid[y][x].South = grid[y+1][x];
                if(x != 0) grid[y][x].West = grid[y][x-1];
                if(x < width-1) grid[y][x].East = grid[y][x+1];
            }
        }

        FindDistances(grid, startX, startY);

        return GetValidEndPoints(grid, example ? 6 : 64);
    }

    private static void FindDistances(Node[][] grid, int startX, int startY) {
        var q = new PriorityQueue<Node>();
        var start = grid[startY][startX];
        start.Distance = 0;
        q.add(start);

        while(!q.isEmpty()) {
            var current = q.poll();
            current.Visit();

            for(var neighbor: current.GetUnvisitedNeighbors()) {
                var newDistance = current.Distance + 1;
                if(neighbor.Distance > newDistance) neighbor.Distance = newDistance;
                if(!q.contains(neighbor)) q.add(neighbor);
            }
        }
    }

    private static String PrintGrid(Node[][] grid) {
        StringBuilder text = new StringBuilder();
        var width = grid[0].length;
        for (Node[] nodes : grid) {
            for (int x = 0; x < width; x++) {
                text.append(nodes[x].toString());
            }
            text.append("\n");
        }
        return text.toString();
    }

    private static int GetValidEndPoints(Node[][] grid, int steps) {
        var width = grid[0].length;
        var count = 0;
        for (Node[] nodes : grid) {
            for (int x = 0; x < width; x++) {
                if(nodes[x].Distance <= steps && nodes[x].Distance % 2 == 0) count++;
            }
        }
        return count;
    }
}