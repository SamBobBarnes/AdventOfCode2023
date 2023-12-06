package Day6;

import Base.AdventBase;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(6, 1, example);

        List<String> input = LoadInput(6, example);

        var time = Arrays.stream(input.getFirst().substring(input.getFirst().indexOf(':')+1).trim().replaceAll(" +"," ").split(" ")).flatMapToInt(num -> IntStream.of(Integer.parseInt(num))).toArray();
        var distance = Arrays.stream(input.getLast().substring(input.getLast().indexOf(':')+1).trim().replaceAll(" +"," ").split(" ")).flatMapToInt(num -> IntStream.of(Integer.parseInt(num))).toArray();

        int[] winners = new int[time.length];

        for(int r = 0; r < time.length; r ++) {
            for(int i = 0; i < time[r]; i++) {
                var timeRemaining = time[r] - i;
                var distanceTraveled = GetDistanceTraveled(i,timeRemaining);
                if(distanceTraveled > distance[r]) winners[r]++;
            }
        }

        int result=1;
        for(int winner : winners) {
            result *= winner;
        }

        return result;
    }

    private static int GetDistanceTraveled(int holdTime, int timeRemaining) {
        return holdTime * timeRemaining;
    }
}