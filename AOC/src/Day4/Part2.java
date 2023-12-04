package Day4;

import Base.AdventBase;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Part2 extends AdventBase {
    public static int Run(boolean example) {
        Start(4, 2, example);

        List<String> input = LoadInput(4, example);

        int[] cardCount = new int[input.size()];
        Arrays.fill(cardCount, 1);

        int index = 0;
        for(String card: input) {
            String[] halves = card.substring(card.indexOf(':')+1).trim().split("\\|");
            halves[0] = halves[0].trim();
            halves[1] = halves[1].trim();
            String[] winners = Arrays.stream(halves[0].split(" ")).filter(x -> !x.isEmpty()).toArray(String[]::new);
            List<String> numbers = Arrays.stream(halves[1].split(" ")).filter(x -> !x.isEmpty()).toList();

            int winningNumbers = 0;
            for(String number: winners){
                if(numbers.contains(number)) winningNumbers++;
            }

            for(int i = 0; i < cardCount[index]; i++) {
                for(int j = 1; j <= winningNumbers; j++) {
                    cardCount[j + index]++;
                }
            }

            index++;
        }

        return IntStream.of(cardCount).sum();
    }
}