package Day5;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

public class Part1 extends AdventBase {
    public static long Run(boolean example) {
        Start(5, 1, example);

        List<String> input = LoadInput(5, example);

        var seeds = input.get(0).substring(input.get(0).indexOf(':') + 2).split(" ");

        input.removeFirst();
        input.removeFirst();

        var ranges = new ArrayList<ConversionRange>();

        var conversionTable = new ArrayList<String>();
        while(!input.isEmpty()) {
            if(!input.get(0).isEmpty()) {
                conversionTable.add(input.get(0));
            }
            else {
                ranges.add(GetRanges(conversionTable));
                conversionTable = new ArrayList<>();
            }
            input.removeFirst();
        }
        ranges.add(GetRanges(conversionTable));

        long min = -1;

        for(var seed: seeds) {
            if(min < 0) min = GetLocation(Long.parseLong(seed), ranges);
            else {
                var location = GetLocation(Long.parseLong(seed), ranges);
                if (location < min) min = location;
            }
        }

        return min;
    }

    private static ConversionRange GetRanges(List<String> conversionTable) {
        var source = conversionTable.get(0).split(" ")[0].split("-")[0];
        var destination = conversionTable.get(0).split(" ")[0].split("-")[2];

        var range = new ConversionRange(source, destination);

        conversionTable.remove(0);

        for(var line: conversionTable) {
            var nums = line.split(" ");
            range.AddConversion(Long.parseLong(nums[1]),Long.parseLong(nums[0]),Long.parseLong(nums[2]));
        }


        return range;
    }

    private static long GetLocation(Long seed, List<ConversionRange> conversions) {
        var soil = conversions.get(0).Convert(seed);
        var fertilizer = conversions.get(1).Convert(soil);
        var water = conversions.get(2).Convert(fertilizer);
        var light = conversions.get(3).Convert(water);
        var temp = conversions.get(4).Convert(light);
        var humidity = conversions.get(5).Convert(temp);
        return conversions.get(6).Convert(humidity);
    }
}