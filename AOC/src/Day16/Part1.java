package Day16;

import Base.AdventBase;

import java.util.ArrayList;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(16, 1, example);

        var input = LoadInputChars(16, example).toArray(char[][]::new);

        var width = input[0].length;
        var height = input.length;

        var energizedTiles = new boolean[height][width];
        var history = new ArrayList<RayHistory>();

        var currentDirection = Direction.Right;

        FollowLight(energizedTiles,input,0,0,currentDirection,history);

        return CountEnergy(energizedTiles);
    }

    private static void FollowLight(boolean[][] energizedTiles, char[][] map, int x, int y, Direction currentDirection, ArrayList<RayHistory> history) {
        var width = map[0].length;
        var height = map.length;

        if(x == width || x < 0 || y == height || y < 0) return;
        if(history.stream().anyMatch(h -> h.Matches(x,y,currentDirection))) return;
        history.add(new RayHistory(x,y,currentDirection));
        energizedTiles[y][x] = true;

        switch(map[y][x]) {
            case '.':
                switch(currentDirection) {
                    case Direction.Up -> FollowLight(energizedTiles,map,x,y-1,Direction.Up,history);
                    case Direction.Down -> FollowLight(energizedTiles,map,x,y+1,Direction.Down,history);
                    case Direction.Left -> FollowLight(energizedTiles,map,x-1,y,Direction.Left,history);
                    case Direction.Right -> FollowLight(energizedTiles,map,x+1,y,Direction.Right,history);
                }
                break;
            case '\\':
                switch(currentDirection) {
                    case Direction.Up -> FollowLight(energizedTiles,map,x-1,y,Direction.Left,history);
                    case Direction.Down -> FollowLight(energizedTiles,map,x+1,y,Direction.Right,history);
                    case Direction.Left -> FollowLight(energizedTiles,map,x,y-1,Direction.Up,history);
                    case Direction.Right -> FollowLight(energizedTiles,map,x,y+1,Direction.Down,history);
                }
                break;
            case '/':
                switch(currentDirection) {
                    case Direction.Up -> FollowLight(energizedTiles,map,x+1,y,Direction.Right,history);
                    case Direction.Down -> FollowLight(energizedTiles,map,x-1,y,Direction.Left,history);
                    case Direction.Left -> FollowLight(energizedTiles,map,x,y+1,Direction.Down,history);
                    case Direction.Right -> FollowLight(energizedTiles,map,x,y-1,Direction.Up,history);
                }
                break;
            case '|':
                switch(currentDirection) {
                    case Direction.Up -> FollowLight(energizedTiles,map,x,y-1,Direction.Up,history);
                    case Direction.Down -> FollowLight(energizedTiles,map,x,y+1,Direction.Down,history);
                    case Direction.Left, Direction.Right -> {
                        FollowLight(energizedTiles, map, x, y-1,Direction.Up,history);
                        FollowLight(energizedTiles, map, x, y+1,Direction.Down,history);
                    }
                }
                break;
            case '-':
                switch(currentDirection) {
                    case Direction.Left -> FollowLight(energizedTiles,map,x-1,y,Direction.Left,history);
                    case Direction.Right -> FollowLight(energizedTiles,map,x+1,y,Direction.Right,history);
                    case Direction.Up, Direction.Down -> {
                        FollowLight(energizedTiles, map, x-1,y,Direction.Left,history);
                        FollowLight(energizedTiles, map, x+1,y,Direction.Right,history);
                    }
                }
        }
    }

    private static int CountEnergy(boolean[][] energizedTiles) {
        var count = 0;
        for (boolean[] energizedTile : energizedTiles) {
            for (int x = 0; x < energizedTiles[0].length; x++) {
                if(energizedTile[x]) count++;
            }
        }

        return count;
    }

    private static String PrintEnergy(boolean[][] energizedTiles) {
        StringBuilder print = new StringBuilder();
        for (boolean[] energizedTile : energizedTiles) {
            for (int x = 0; x < energizedTiles[0].length; x++) {
                print.append(energizedTile[x] ? '#' : '.');
            }
            print.append("\n");
        }

        return print.toString();
    }
}

enum Direction {
    Up,
    Down,
    Left,
    Right
}

class RayHistory {
    public RayHistory(int x, int y, Direction dir) {
        this.dir = dir;
        this.x = x;
        this.y = y;
    }
    public Direction dir;
    public int x;
    public int y;
    public boolean Matches(int x, int y, Direction dir) {
        return this.x == x && this.y == y && this.dir == dir;
    }
}