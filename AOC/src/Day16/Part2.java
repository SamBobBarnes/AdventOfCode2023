package Day16;

import Base.AdventBase;

import java.util.ArrayList;

public class Part2 extends AdventBase {
    public static int Run(boolean example) {
        Start(16, 2, example);

        var input = LoadInputChars(16, example).toArray(char[][]::new);

        var width = input[0].length;
        var height = input.length;

        var energizedTiles = new boolean[height][width];
        var history = new ArrayList<RayHistory>();

        var most = 0;

        for(int y = 0; y < height; y++) {
            energizedTiles = new boolean[height][width];
            history = new ArrayList<>();
            FollowLight(energizedTiles,input,0,y,Direction.Right,history);
            var count = CountEnergy(energizedTiles);
            if(count > most) most = count;


            energizedTiles = new boolean[height][width];
            history = new ArrayList<>();
            FollowLight(energizedTiles,input,width-1,y,Direction.Left,history);
            count = CountEnergy(energizedTiles);
            if(count > most) most = count;
        }

        for(int x = 0; x < width; x++) {
            energizedTiles = new boolean[height][width];
            history = new ArrayList<>();
            FollowLight(energizedTiles,input,x,0,Direction.Down,history);
            var count = CountEnergy(energizedTiles);
            if(count > most) most = count;

            energizedTiles = new boolean[height][width];
            history = new ArrayList<>();
            FollowLight(energizedTiles,input,x,height-1,Direction.Up,history);
            count = CountEnergy(energizedTiles);
            if(count > most) most = count;
        }

        return most;
    }

    private static void FollowLight(boolean[][] energizedTiles, char[][] map, int x, int y, Direction currentDirection, ArrayList<RayHistory> history) {
        var width = map[0].length;
        var height = map.length;

        if(x == width || x < 0 || y == height || y < 0) return;
        if(history.stream().anyMatch(h -> h.Matches(x,y,currentDirection))) return;
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
                history.add(new RayHistory(x,y,currentDirection));
                switch(currentDirection) {
                    case Direction.Up -> FollowLight(energizedTiles,map,x-1,y,Direction.Left,history);
                    case Direction.Down -> FollowLight(energizedTiles,map,x+1,y,Direction.Right,history);
                    case Direction.Left -> FollowLight(energizedTiles,map,x,y-1,Direction.Up,history);
                    case Direction.Right -> FollowLight(energizedTiles,map,x,y+1,Direction.Down,history);
                }
                break;
            case '/':
                history.add(new RayHistory(x,y,currentDirection));
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
                        history.add(new RayHistory(x,y,currentDirection));
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
                        history.add(new RayHistory(x,y,currentDirection));
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