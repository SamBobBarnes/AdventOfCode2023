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

        var result = ScoreRocks(rocks,height);
        return result;
    }

    private static List<Rock> RollNorth(List<Rock> rocks, int width) {
        List<Rock> newList = new ArrayList<>();
        for(int i = 0; i < width; i++) {
            int finalI = i;
            var column = new ArrayList<>(rocks.stream().filter(r -> r.x == finalI).toList());
            // need to overwrite rocks
            var index = 0;
            for(var rock: column) {
                if(rock.round) {
                    var finalRestingY = rock.y;
                    for(int y = rock.y-1; y >= 0; y--) {
                        int finalY = y;
                        if(column.stream().noneMatch(r -> r.y == finalY)) finalRestingY = y;
                        else break;
                    }
                    rock.y = finalRestingY;
                    column.set(index, rock);
                }
                newList.add(rock);
                index++;
            }
        }
        return newList;
    }

    private static int ScoreRocks(List<Rock> rocks, int height) {
        var score = 0;
        for(var rock: rocks) {
            if(rock.round) score += height - rock.y;
        }
        return score;
    }
}