package Day13;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 extends AdventBase {
    public static int Run(boolean example) {
        Start(13, 2, example);

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
            var differences = 0;
            for(int x = 0; x < shortSide; x++) {
                var top = pattern.pattern.get(i-x-1);
                var bottom = pattern.pattern.get(i+x);
                if(!Arrays.equals(top, bottom)) {
                    match = false;
                    for(int j = 0; j < pattern.width; j++) {
                        if(top[j] != bottom[j]) differences++;
                    }
                }
            }
            if(differences == 1) return i;
        }

        return 0;
    }
}