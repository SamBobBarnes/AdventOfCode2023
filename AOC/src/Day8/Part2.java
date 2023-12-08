package Day8;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Part2 extends AdventBase {
    public static long Run(boolean example) {
        Start(8, 2, example);

        List<String> input = LoadInput(8, example);

        var directions = input.getFirst().toCharArray();
        var index = 0;
        //region setup
        input.removeFirst();
        input.removeFirst();

        var maps = new ArrayList<Map>();

        for (var line : input) {
            maps.add(new Map(line.substring(0, 3)));
        }

        for (int i = 0; i < maps.size(); i++) {
            var left = input.get(i).substring(7, 10);
            var right = input.get(i).substring(12, 15);
            maps.get(i).left = (Map) maps.stream().filter(x -> Objects.equals(x.label, left)).toArray()[0];
            maps.get(i).right = (Map) maps.stream().filter(x -> Objects.equals(x.label, right)).toArray()[0];
        }
        //endregion

        var current = maps.stream().filter(x -> Objects.equals(x.label.substring(2), "A")).toArray(Map[]::new);
        var completions = new int[6];
        int steps = 0;

        while (completions[0] == 0
                || completions[1] == 0
                || completions[2] == 0
                || completions[3] == 0
                || completions[4] == 0
                || completions[5] == 0
        ) {
            var direction = directions[index];

            if (direction == 'L') {
                for(int i = 0; i < current.length; i++) {
                    current[i] = current[i].left;
                }
            }
            else {
                for(int i = 0; i < current.length; i++) {
                    current[i] = current[i].right;
                }
            }
            steps++;

            for(int i = 0; i < current.length; i++) {
                if(CheckForCompletion(current,i)) completions[i] = steps;
            }

            index = AdvanceIndex(index, directions);
        }

        List<List<Integer>> primesLists = new ArrayList<>();
        for(var completion: completions) {
            primesLists.add(primeFactors(completion));
        }

        return (long) primesLists.get(0).get(0) * primesLists.get(1).getFirst() * primesLists.get(2).getFirst() * primesLists.get(3).getFirst() * primesLists.get(4).getFirst() * primesLists.get(5).getFirst() * primesLists.get(0).get(1);
    }

    private static int AdvanceIndex(int index, char[] directions) {
        index++;
        if (index == directions.length) return 0;
        return index;
    }

    private static boolean CheckForCompletion(Map[] current, int index) {
        return current[index].label.substring(2).equals("Z");
    }

    private static List<Integer> primeFactors(int n)
    {
        var primes = new ArrayList<Integer>();
        while (n%2==0)
        {
            primes.add(2);
            n /= 2;
        }

        for (int i = 3; i <= Math.sqrt(n); i+= 2)
        {
            while (n%i == 0)
            {
                primes.add(i);
                n /= i;
            }
        }

        if (n > 2)
            primes.add(n);

        return primes;
    }
}