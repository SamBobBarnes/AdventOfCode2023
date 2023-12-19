package Day13;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.Arrays;
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
        if(lines > 0) return -lines;
        return FindVerticalMirror(pattern);
    }

    private static int FindVerticalMirror(Pattern pattern) {
        var newList = new ArrayList<char[]>();
        for(int i = 0; i < pattern.width; i++) {
            var charList = new char[pattern.pattern.size()];
            for(int j = 0; j < pattern.height; j++) {
                charList[j] = pattern.pattern.get(j)[i];
            }
            newList.add(charList);
        }

        var tempPattern = new Pattern(newList, true);

        return FindHorizontalMirror(tempPattern);
    }

    private static int FindHorizontalMirror(Pattern pattern) {
        var length = pattern.pattern.size();
        var shortSide = 0;
        var match = true;
        for(int i = 1; i < length; i++) {
            if(i <= length/2.0) shortSide++;
            if(i > (length+1)/2.0) shortSide--;
            match = true;
            for(int x = 0; x < shortSide; x++) {
                if(!Arrays.equals(pattern.pattern.get(i + x), pattern.pattern.get(i - 1 - x))) {
                    match = false;
                    break;
                }
            }
            if(match) return i;
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

    public Pattern(List<char[]> pattern, boolean CharList) {
        this.pattern = pattern;
        this.width = this.pattern.getFirst().length;
        this.height = this.pattern.size();
    }
    public List<char[]> pattern;
    public int width;
    public int height;
}