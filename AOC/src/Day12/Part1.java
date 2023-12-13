package Day12;

import Base.AdventBase;

import java.util.List;

import static java.lang.Integer.parseInt;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(12, 1, example);

        List<String> input = LoadInput(12, example);

        String[][] answerStrings = input.stream().map(x -> x.split(" ")[1].split(",")).toList().toArray(String[][]::new);
        Integer[][] answers = new Integer[answerStrings.length][];

        for(int i = 0; i < answerStrings.length; i++) {
            var temp = new Integer[answerStrings[i].length];
            for(int j = 0; j < answerStrings[i].length; j++) {
                temp[j] = parseInt(answerStrings[i][j]);
            }
            answers[i] = temp;
        }

        char[][] diagrams = input.stream().map(x -> x.split(" ")[0].toCharArray()).toList().toArray(char[][]::new);




        return 0;
    }
}