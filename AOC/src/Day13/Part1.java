package Day13;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(13, 1, example);

        List<String> input = LoadInput(13, example);
        var patterns = ProcessInput(input);

        var linesLeftOfMirror = 0;
        var linesAboveMirror = 0;

        for(var pattern: patterns) {
            var lines = FindMirror(pattern);
            if(lines > 0) linesLeftOfMirror += lines;
            else linesAboveMirror -= lines;
        }

        return linesLeftOfMirror + (100 * linesAboveMirror);
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

    private static int FindMirror(Pattern pattern) {
        var lines = FindHorizontalMirror(pattern);
        if(lines >= 0) return -lines;
        return FindVerticalMirror(pattern);
    }

    private static int FindVerticalMirror(Pattern pattern) {
        return 0;
    }

    private static int FindHorizontalMirror(Pattern pattern) {
        var length = pattern.pattern.size();
        var shortSide = 1;
        for(int i = 1; i < length-1; i++) {
            for(int x = 0; x < shortSide; x++) {

            }

            if(shortSide < length/2) shortSide++;
            else shortSide--;
        }

        return 0;
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