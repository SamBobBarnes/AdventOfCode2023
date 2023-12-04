package Day4;

import Base.AdventBase;

import java.util.Arrays;
import java.util.List;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(4, 1, example);

        List<String> input = LoadInput(4, example);

        int result = 0;

        for(String card: input) {
            String[] halves = card.substring(card.indexOf(':')+1).trim().split("\\|");
            halves[0] = halves[0].trim();
            halves[1] = halves[1].trim();
            String[] winners = Arrays.stream(halves[0].split(" ")).filter(x -> !x.isEmpty()).toArray(String[]::new);
            List<String> numbers = Arrays.stream(halves[1].split(" ")).filter(x -> !x.isEmpty()).toList();

            int winningNumbers = 0;
            for(String number: winners){
                boolean winner = numbers.contains(number);
                if(winner && winningNumbers == 0) winningNumbers++;
                else if(winner) winningNumbers = winningNumbers << 1;
            }
            result += winningNumbers;
        }

        return result;
    }
}