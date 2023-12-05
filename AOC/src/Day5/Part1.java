package Day5;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(5, 1, example);

        List<String> input = LoadInput(5, example);

        return 0;
    }

    private static ConversionRange GetRanges(List<String> conversionTable) {
        var source = conversionTable.get(0).split(" ")[0].split("-")[0];
        var destination = conversionTable.get(0).split(" ")[0].split("-")[2];

        var range = new ConversionRange(source, destination);

        conversionTable.remove(0);

        for(var line: conversionTable) {
            var nums = line.split(" ");
            range.AddConversion(parseInt(nums[1]),parseInt(nums[0]),parseInt(nums[2]));
        }


        return range;
    }
}

class ConversionRange {
    public ConversionRange(String sourceType, String destinationType) {
        this.sourceType = sourceType;
        this.destinationType = destinationType;
        this.source = new ArrayList<>();
        this.destination = new ArrayList<>();
        this.range = new ArrayList<>();
    }
    public List<Integer> source;
    public List<Integer> destination;
    public List<Integer> range;
    public String destinationType;
    public String sourceType;

    public void AddConversion(int source, int destination, int range) {
        this.source.add(source);
        this.destination.add(destination);
        this.range.add(range);
    }
}