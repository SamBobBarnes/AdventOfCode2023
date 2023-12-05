package Day5;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

public class Part2 extends AdventBase {
    public static Long Run(boolean example) {
        Start(5, 2, example);

        List<String> input = LoadInput(5, example);

        var seedStrings = input.get(0).substring(input.get(0).indexOf(':') + 2).split(" ");

        input.removeFirst();
        input.removeFirst();

        //region Seeds
        List<Range> seeds = new ArrayList<>();

        for(int i = 0; i < seedStrings.length; i += 2) {
            var seed = Long.parseLong(seedStrings[i]);
            var range = Long.parseLong(seedStrings[i+1]);
            seeds.add(new Range(seed,range));
        }
        //endregion

        //region ConversionRanges
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
        //endregion

        var locations = GetLocations(seeds,ranges);

        long min = locations.stream().mapToLong(x -> x.start).min().getAsLong();

        return min;
    }

    private static ConversionRange GetRanges(List<String> conversionTable) {
        var source = conversionTable.get(0).split(" ")[0].split("-")[0];
        var destination = conversionTable.get(0).split(" ")[0].split("-")[2];

        var range = new ConversionRange(source, destination);

        conversionTable.remove(0);

        for(var line: conversionTable) {
            var numbers = line.split(" ");
            range.AddConversion(Long.parseLong(numbers[1]),Long.parseLong(numbers[0]),Long.parseLong(numbers[2]));
        }


        return range;
    }

    private static List<Range> GetLocations(List<Range> seeds, List<ConversionRange> conversions) {
        var soil = new ArrayList<Range>();
        for(var range: seeds) {
            soil.addAll(conversions.get(0).ConvertRange(range));
        }

        var fertilizer = new ArrayList<Range>();
        for(var range: soil) {
            fertilizer.addAll(conversions.get(1).ConvertRange(range));
        }

        var water = new ArrayList<Range>();
        for(var range: fertilizer) {
            water.addAll(conversions.get(2).ConvertRange(range));
        }

        var light = new ArrayList<Range>();
        for(var range: water) {
            light.addAll(conversions.get(3).ConvertRange(range));
        }

        var temp = new ArrayList<Range>();
        for(var range: light) {
            temp.addAll(conversions.get(4).ConvertRange(range));
        }

        var humidity = new ArrayList<Range>();
        for(var range: temp) {
            humidity.addAll(conversions.get(5).ConvertRange(range));
        }

        var location = new ArrayList<Range>();
        for(var range: humidity) {
            location.addAll(conversions.get(6).ConvertRange(range));
        }

        return location;
    }
}

class Range {
    public Range(long start, long range) {
        this.start = start;
        this.range = range;
        this.end = start + range -1;
    }
    public long start;
    public long range;
    public long end;
}

class ConversionRange {
    public ConversionRange(String sourceType, String destinationType) {
        this.sourceType = sourceType;
        this.destinationType = destinationType;
        this.source = new ArrayList<>();
        this.destination = new ArrayList<>();
        this.range = new ArrayList<>();
    }
    public List<Long> source;
    public List<Long> destination;
    public List<Long> range;
    public String destinationType;
    public String sourceType;

    public void AddConversion(Long source, Long destination, Long range) {
        this.source.add(source);
        this.destination.add(destination);
        this.range.add(range);
    }

    public Long Convert(Long source) {
        for(int i = 0; i < this.source.size(); i++) {
            if(source >= this.source.get(i) && source <= this.source.get(i) + this.range.get(i)) {
                return this.destination.get(i) + (source - this.source.get(i));
            }
        }
        return source;
    }

    public List<Range> ConvertRange(Range source) {
        var tempRanges = new ArrayList<Range>();
        tempRanges.add(source);

        var sourceRanges = new ArrayList<Range>();
        var destinationRanges = new ArrayList<Range>();

        while(!tempRanges.isEmpty()) {
            var outside = false;
            var tempSource = tempRanges.getFirst();
            for(int i = 0; i < this.source.size(); i++) {
                var conversionSource = this.source.get(i);
                var conversionSourceEnd = conversionSource + this.range.get(i)-1;
                //full match
                if(tempSource.start >= conversionSource
                        && tempSource.end <= conversionSourceEnd) {
                    sourceRanges.add(tempSource);
                    tempRanges.remove(tempSource);
                    break;
                }
                //start matches
                else if(tempSource.start >= conversionSource && tempSource.start <= conversionSourceEnd) {
                    sourceRanges.add(new Range(source.start, conversionSourceEnd - tempSource.start + 1));
                    tempRanges.add(new Range(conversionSourceEnd + 1,tempSource.end - conversionSourceEnd));
                    tempRanges.remove(tempSource);
                    break;
                }
                //end matches
                else if(tempSource.end >= conversionSource && tempSource.end <= conversionSourceEnd){
                    sourceRanges.add(new Range(conversionSource, tempSource.end - conversionSource + 1));
                    tempRanges.add(new Range(tempSource.start, conversionSource - tempSource.start));
                    tempRanges.remove(tempSource);
                    break;
                }
                //else outside
                else {
                    if(i == this.source.size()-1)
                        outside = true;
                }
            }
            if(outside) {
                sourceRanges.add(tempSource);
                tempRanges.remove(tempSource);
            }

        }

        for(var range: sourceRanges) {
            destinationRanges.add(new Range(this.Convert(range.start),range.range));
        }

        return destinationRanges;
    }
}