package Day1;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class Part1 extends AdventBase {
    public static int Run() {
        Start(1,1);

        List<String> input = LoadInput(1);
        List<char[]> nums = new ArrayList<char[]>();
        input.forEach(line -> {
            nums.add(line.replaceAll("[^0-9]+", "").toCharArray());
        });

        AtomicInteger result = new AtomicInteger();

        nums.forEach(numList -> {
           result.addAndGet(parseInt("" + numList[0] + numList[numList.length - 1]));
        });

        return result.get();
    }
}
