package Day13;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(13, 1, example);

        List<String> input = LoadInput(13, example);
        var patterns = ProcessInput(input);

        return 0;
    }

    private static List<Pattern> ProcessInput(List<String> list) {
        var patterns = new ArrayList<Pattern>();
        var temp = new ArrayList<String>();

        for(var line: list) {
            if(line.isEmpty()) {
                patterns.add(new Pattern(temp));
                temp = new ArrayList<>();
            }
            else {
                temp.add(line);
            }
        }

        patterns.add(new Pattern(temp));

        return patterns;
    }
}

class Pattern {
    public Pattern(List<String> list) {
        this.height = list.size();
        this.width = list.getFirst().length();
        this.pattern = new ArrayList<>();
        for(var line: list) {
            this.pattern.add(line.toCharArray());
        }
    }
    public List<char[]> pattern;
    public int width;
    public int height;
}