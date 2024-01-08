package Day21;

import Base.AdventBase;

import java.util.PriorityQueue;

public class Part2 extends AdventBase {
    public static int Run(boolean example) {
        Start(21, 2, example);

        char[][] input = LoadInputChars(21, example).toArray(char[][]::new);

        NodeInfinite[][] grid = new NodeInfinite[input.length][input[0].length];

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
                grid[y][x] = new NodeInfinite(x,y,input[y][x] == '#');
            }
        }

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(y != 0) grid[y][x].North = grid[y-1][x];
                else grid[y][x].North = grid[height-1][x];
                if(y < height-1) grid[y][x].South = grid[y+1][x];
                else grid[y][x].South = grid[0][x];
                if(x != 0) grid[y][x].West = grid[y][x-1];
                else grid[y][x].West = grid[y][width-1];
                if(x < width-1) grid[y][x].East = grid[y][x+1];
                else grid[y][x].East = grid[y][0];
            }
        }

        FindDistances(grid, startX, startY);

        return GetValidEndPoints(grid, example ? 6 : 64);
    }

    private static void FindDistances(NodeInfinite[][] grid, int startX, int startY) {
        var q = new PriorityQueue<Distance>();
        var start = grid[startY][startX];
        start.SetDistancInfinite(startX, startY, 0);
        q.add(start.DistanceList.getFirst());

        while(!q.isEmpty()) {
            var current = q.poll();
            current.node.Visit(current.x,current.y);

            for(var neighbor: current.node.GetUnvisitedNeighborsInfinite(current.x,current.y)) {
                var newDistance = current.distance + 1;
                if(neighbor.GetDistanceInfinite(current.x,current.y) > newDistance) neighbor.SetDistancInfinite(current.x, current.y, (int)newDistance);
//                q.add(neighbor);
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

    private static int GetValidEndPoints(NodeInfinite[][] grid, int steps) {
        var width = grid[0].length;
        var count = 0;
        for (NodeInfinite[] nodes : grid) {
            for (int x = 0; x < width; x++) {
                if(nodes[x].Distance <= steps && nodes[x].Distance % 2 == 0) count++;
            }
        }
        return count;
    }
}