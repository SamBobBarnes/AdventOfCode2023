package Day15;

import Base.AdventBase;

import java.util.ArrayList;

public class Part1 extends AdventBase {
    public static long Run(boolean example) {
        Start(15, 1, example);

        String input = LoadInput(15, example).getFirst();
        var strings = input.split(",");
        var hashList = new ArrayList<Integer>();
        for(var string: strings) {
            hashList.add(Hash.parseString(string));
        }
        var result = 0L;

        for(var hash: hashList) {
            result += hash;
        }

        return result;
    }
}