package Day9;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Part2 extends AdventBase {
    public static int Run(boolean example) {
        Start(9, 2, example);

        List<String> input = LoadInput(9, example);

        List<List<Integer[]>> arrays = new ArrayList<>();

        for(var line: input) {
            var list = new ArrayList<Integer[]>();
            list.add(Stream.of(line.split(" ")).map(Integer::valueOf).toArray(Integer[]::new));
            arrays.add(list);
        }

        List<List<Integer[]>> finalArrays = new ArrayList<>();

        for(var set: arrays) {
            finalArrays.add(FindAllSetsSequence(set));
        }

        var result = 0;

        for(var set: finalArrays) {
            var lastNum = 0;
            for(int i = set.size()-2; i >= 0; i--) {
                var list = set.get(i);
                lastNum = list[0] - lastNum;
            }
            result += lastNum;
        }

        return result;
    }

    private static List<Integer[]> FindAllSetsSequence(List<Integer[]> set) {
        if(isAllZeros(set.getLast())) return set;
        set.add(GetDifferenceList(set.getLast()));
        return FindAllSetsSequence(set);
    }

    private static Integer[] GetDifferenceList(Integer[] list) {
        var subList = new Integer[list.length-1];

        for(int i = 1; i < list.length; i++) {
            subList[i-1] = list[i]-list[i-1];
        }

        return subList;
    }

    private static boolean isAllZeros(Integer[] list) {
        for(int i: list) {
            if(i != 0) return false;
        }
        return true;
    }
}