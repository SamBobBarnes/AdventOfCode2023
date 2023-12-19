package Day14;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(14, 1, example);

        var input = LoadInputChars(14, example).toArray(char[][]::new);

        var width = input[0].length;
        var height = input.length;

        List<Rock> rocks = new ArrayList<Rock>();

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                var rock = input[y][x];
                if(rock != '.') rocks.add(new Rock(rock, x, y));
            }
        }

        rocks = RollNorth(rocks, width);

        return 0;
    }

    private static List<Rock> RollNorth(List<Rock> rocks, int width) {
        List<Rock> newList = new ArrayList<>();
        for(int i = 0; i < width; i++) {
            int finalI = i;
            var column = rocks.stream().filter(r -> r.x == finalI).toList();
            // need to overwrite rocks
            for(var rock: column) {
                if(rock.round) {
                    var finalRestingY = rock.y;
                    for(int y = rock.y; y >= 0; y--) {
                        int finalY = y;
                        if(column.stream().noneMatch(r -> r.y == finalY)) finalRestingY = y;
                        else break;
                    }
                    rock.y = finalRestingY;
                }
                newList.add(rock);
            }
        }
        return newList;
    }
}

class Rock {
    public Rock(char rock, int x, int y) {
        if(rock == 'O') this.round = true;
        else this.round = false;
        this.x = x;
        this.y = y;
    }
    public int x;
    public int y;
    public boolean round;
}