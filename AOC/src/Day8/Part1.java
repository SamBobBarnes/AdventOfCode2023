package Day8;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(8, 1, example);

        List<String> input = LoadInput(8, example);

        var directions = input.getFirst().toCharArray();
        var index = 0;
        //region setup
        input.removeFirst();
        input.removeFirst();

        var maps = new ArrayList<Map>();

        for(var line: input) {
            maps.add(new Map(line.substring(0,3)));
        }

        for(int i = 0; i < maps.size(); i++) {
            var left = input.get(i).substring(7,10);
            var right = input.get(i).substring(12,15);
            maps.get(i).left = (Map) maps.stream().filter(x -> Objects.equals(x.label, left)).toArray()[0];
            maps.get(i).right = (Map) maps.stream().filter(x -> Objects.equals(x.label, right)).toArray()[0];
        }
        //endregion

        var current = maps.stream().filter(x -> Objects.equals(x.label, "AAA")).findFirst().get();
        var steps = 0;

        while(!Objects.equals(current.label, "ZZZ")) {
            var direction = directions[index];

            if(direction == 'L') current = current.left;
            else current = current.right;
            steps++;

            index = AdvanceIndex(index, directions);
        }

        return steps;
    }

    private static int AdvanceIndex(int index, char[] directions) {
        index++;
        if(index == directions.length) return 0;
        return index;
    }
}

class Map {
    public String label;
    public Map left;
    public Map right;

    public Map(String label) {
        this.label = label;
    }

    public String toString() {
        return this.label;
    }
}