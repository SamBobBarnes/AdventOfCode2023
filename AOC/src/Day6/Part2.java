package Day6;

import Base.AdventBase;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Part2 extends AdventBase {
    public static long Run(boolean example) {
        Start(6, 2, example);

        List<String> input = LoadInput(6, example);

        var time = parseLong(input.getFirst().substring(input.getFirst().indexOf(':')+1).trim().replaceAll(" +",""));
        var distance = parseLong(input.getLast().substring(input.getLast().indexOf(':')+1).trim().replaceAll(" +",""));

        long winners = 0;

        for(long i = 0; i < time; i++) {
            var timeRemaining = time - i;
            var distanceTraveled = GetDistanceTraveled(i,timeRemaining);
            if(distanceTraveled > distance) winners++;
        }

        return winners;
    }

    private static long GetDistanceTraveled(long holdTime, long timeRemaining) {
        return holdTime * timeRemaining;
    }
}