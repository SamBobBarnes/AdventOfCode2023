package Day1;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class Part2 extends AdventBase {
    public static int Run() {
        Start(1,2);

        List<String> input = LoadInput(1, false);
        List<List<Integer>> nums = new ArrayList<>();


        input.forEach(line -> {
            List<Integer> numList = new ArrayList<>();
            for(int i = 0; i < line.length(); i++) {
                if(     line.charAt(i) == '1' ||
                        line.charAt(i) == '2' ||
                        line.charAt(i) == '3' ||
                        line.charAt(i) == '4' ||
                        line.charAt(i) == '5' ||
                        line.charAt(i) == '6' ||
                        line.charAt(i) == '7' ||
                        line.charAt(i) == '8' ||
                        line.charAt(i) == '9') {

                    numList.add(parseInt("" + line.charAt(i)));
                }
                else if(line.startsWith("one", i)) {
                    numList.add(1);
                }
                else if(line.startsWith("two", i)) {
                    numList.add(2);
                }
                else if(line.startsWith("three", i)) {
                    numList.add(3);
                }
                else if(line.startsWith("four", i)) {
                    numList.add(4);
                }
                else if(line.startsWith("five", i)) {
                    numList.add(5);
                }
                else if(line.startsWith("six", i)) {
                    numList.add(6);
                }
                else if(line.startsWith("seven", i)) {
                    numList.add(7);
                }
                else if(line.startsWith("eight", i)) {
                    numList.add(8);
                }
                else if(line.startsWith("nine", i)) {
                    numList.add(9);
                }
            }

            nums.add(numList);
        });

        AtomicInteger result = new AtomicInteger();

        nums.forEach(numList -> {
            int numToAdd = parseInt(numList.get(0).toString() + numList.get(numList.size() - 1).toString());
            result.addAndGet(numToAdd);
        });

        return result.get();
    }
}