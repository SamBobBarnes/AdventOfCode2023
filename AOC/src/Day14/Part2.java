package Day14;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Part2 extends AdventBase {
    public static int Run(boolean example) {
        Start(14, 2, example);

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

        var cycles = 1000;
        for(int i = 0; i < cycles; i++) {
            rocks = RollCycle(rocks, width, height);
        }

        var result = ScoreRocks(rocks,height);
        return result;
    }

    private static List<Rock> RollCycle(List<Rock> rocks, int width, int height) {
        rocks = RollNorth(rocks, width);
        rocks = RollWest(rocks, height);
        rocks = RollSouth(rocks, width, height);
        rocks = RollEast(rocks, width, height);
        return rocks;
    }

    private static List<Rock> RollNorth(List<Rock> rocks, int width) {
        List<Rock> newList = new ArrayList<>();
        for(int i = 0; i < width; i++) {
            int finalI = i;
            var column = new ArrayList<>(rocks.stream().filter(r -> r.x == finalI).toList());
            column.sort(new RockComparatorNorth());

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

    private static List<Rock> RollSouth(List<Rock> rocks, int width, int height) {
        List<Rock> newList = new ArrayList<>();
        for(int i = 0; i < width; i++) {
            int finalI = i;
            var column = new ArrayList<>(rocks.stream().filter(r -> r.x == finalI).toList());
            column.sort(new RockComparatorSouth());

            var index = 0;
            for(var rock: column) {
                if(rock.round) {
                    var finalRestingY = rock.y;
                    for(int y = rock.y+1; y < height; y++) {
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

    private static List<Rock> RollWest(List<Rock> rocks, int height) {
        List<Rock> newList = new ArrayList<>();
        for(int i = 0; i < height; i++) {
            int finalI = i;
            var row = new ArrayList<>(rocks.stream().filter(r -> r.y == finalI).toList());
            row.sort(new RockComparatorEast());

            var index = 0;
            for(var rock: row) {
                if(rock.round) {
                    var finalRestingX = rock.x;
                    for(int x = rock.x-1; x >= 0; x--) {
                        int finalX = x;
                        if(row.stream().noneMatch(r -> r.x == finalX)) finalRestingX = x;
                        else break;
                    }
                    rock.x = finalRestingX;
                    row.set(index, rock);
                }
                newList.add(rock);
                index++;
            }
        }
        return newList;
    }

    private static List<Rock> RollEast(List<Rock> rocks, int width, int height) {
        List<Rock> newList = new ArrayList<>();
        for(int i = 0; i < height; i++) {
            int finalI = i;
            var row = new ArrayList<>(rocks.stream().filter(r -> r.y == finalI).toList());
            row.sort(new RockComparatorWest());

            var index = 0;
            for(var rock: row) {
                if(rock.round) {
                    var finalRestingX = rock.x;
                    for(int x = rock.x+1; x < width; x++) {
                        int finalX = x;
                        if(row.stream().noneMatch(r -> r.x == finalX)) finalRestingX = x;
                        else break;
                    }
                    rock.x = finalRestingX;
                    row.set(index, rock);
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

    private static String PrintRocks(List<Rock> rocks, int width, int height) {
        AtomicReference<String> print = new AtomicReference<>("");
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int finalY = y;
                int finalX = x;
                var rock = rocks.stream().filter(r -> r.x == finalX && r.y == finalY).findFirst();
                rock.ifPresentOrElse(
                    r -> {
                        if(r.round) print.updateAndGet(v -> v + 'O');
                        else print.updateAndGet(v -> v + '#');
                    },
                    () -> {
                        print.updateAndGet(v -> v + '.');
                    }
                 );
            }
            print.updateAndGet(v -> v + "\n");
        }
        return print.get();
    }
}