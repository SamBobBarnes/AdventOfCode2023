package Day5;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

public class Part2 extends AdventBase {
    public static Long Run(boolean example) {
        Start(5, 2, example);

        List<String> input = LoadInput(5, example);

        var seedStrings = input.getFirst().substring(input.getFirst().indexOf(':') + 2).split(" ");

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
        var ranges = new ArrayList<Conversion>();

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

        var overlap = false;
        for(var range: ranges) {
            if(range.CheckForOverlap()) overlap = true;
        }

        var locations = GetLocations(seeds,ranges);

        long min = locations.stream().mapToLong(x -> x.start).min().getAsLong();

        return min;
    }

    private static Conversion GetRanges(List<String> conversionTable) {
        var source = conversionTable.get(0).split(" ")[0].split("-")[0];
        var destination = conversionTable.get(0).split(" ")[0].split("-")[2];

        var range = new Conversion(source, destination);

        conversionTable.remove(0);

        for(var line: conversionTable) {
            var numbers = line.split(" ");
            range.AddConversion(Long.parseLong(numbers[1]),Long.parseLong(numbers[0]),Long.parseLong(numbers[2]));
        }


        return range;
    }

    private static List<Range> GetLocations(List<Range> seeds, List<Conversion> conversions) {
        // System.out.println("Soil:");
        var soil = new ArrayList<Range>();
        for(var range: seeds) {
            soil.addAll(conversions.get(0).ConvertRange(range));
        }

        // System.out.println("Fertilizer:");
        var fertilizer = new ArrayList<Range>();
        for(var range: soil) {
            fertilizer.addAll(conversions.get(1).ConvertRange(range));
        }

        // System.out.println("Water:");
        var water = new ArrayList<Range>();
        for(var range: fertilizer) {
            water.addAll(conversions.get(2).ConvertRange(range));
        }

        // System.out.println("Light:");
        var light = new ArrayList<Range>();
        for(var range: water) {
            light.addAll(conversions.get(3).ConvertRange(range));
        }

        // System.out.println("Temp:");
        var temp = new ArrayList<Range>();
        for(var range: light) {
            temp.addAll(conversions.get(4).ConvertRange(range));
        }

        // System.out.println("Humidity:");
        var humidity = new ArrayList<Range>();
        for(var range: temp) {
            humidity.addAll(conversions.get(5).ConvertRange(range));
        }

        // System.out.println("Location:");
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
        this.end = start + range - 1;
    }
    public long start;
    public long range;
    public long end;

    public String toString() {
        return start + "-" + end;
    }
}

class ConversionRange extends Range {
    public ConversionRange(long start, long range, long destination) {
        super(start, range);
        this.destinationStart = destination;
        this.destinationEnd = destination + range - 1;
    }
    public long destinationStart;
    public long destinationEnd;
}

class Conversion {
    public Conversion(String sourceType, String destinationType) {
        this.sourceType = sourceType;
        this.destinationType = destinationType;
        this.ranges = new ArrayList<>();
    }

    public List<ConversionRange> ranges;
    public String destinationType;
    public String sourceType;

    public void AddConversion(Long source, Long destination, Long range) {
        this.ranges.add(new ConversionRange(source,range,destination));
    }

    public boolean CheckForOverlap() {
        for(int i = 0; i < ranges.size(); i++) {
            for(int j = 0; j < ranges.size(); j++) {
                if(i == j) continue;
                var start1 = ranges.get(i).start;
                var end1 = ranges.get(i).end - 1;
                var start2 = ranges.get(j).start;
                var end2 = ranges.get(j).end;

                if(start2 >= start1 && start2 <= end1 || end2 >= start1 && end2 <= end1) return true;
            }
        }
        return false;
    }

    public Long Convert(Long source) {
        for(int i = 0; i < this.ranges.size(); i++) {
            if(source >= this.ranges.get(i).start && source <= this.ranges.get(i).end) {
                return this.ranges.get(i).destinationStart + (source - this.ranges.get(i).start);
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
            var outside = 0;
            var tempSource = tempRanges.getFirst();
            for(int i = 0; i < this.ranges.size(); i++) {
                //outside
                if(tempSource.start < this.ranges.get(i).start && tempSource.end < this.ranges.get(i).start
                        || tempSource.start > this.ranges.get(i).end && tempSource.end > this.ranges.get(i).end) {
                    outside++;
                }
                else if(tempSource.start < this.ranges.get(i).start && tempSource.end > this.ranges.get(i).end) {
                    tempRanges.add(new Range(tempSource.start,this.ranges.get(i).start - tempSource.start));
                    tempRanges.add(new Range(this.ranges.get(i).end+1,tempSource.end - this.ranges.get(i).end));
                    sourceRanges.add(new Range(this.ranges.get(i).start,this.ranges.get(i).range));
                    break;
                }
                //inside
                else if(tempSource.start >= this.ranges.get(i).start
                        && tempSource.end <= this.ranges.get(i).end) {
                    sourceRanges.add(tempSource);
                    break;
                }
                //start overlaps
                else if(tempSource.start >= this.ranges.get(i).start && tempSource.start <= this.ranges.get(i).end && tempSource.end > this.ranges.get(i).end) {
                    sourceRanges.add(new Range(source.start, this.ranges.get(i).end - tempSource.start + 1));
                    tempRanges.add(new Range(this.ranges.get(i).end + 1,tempSource.end - this.ranges.get(i).end));
                    break;
                }
                //end overlaps
                else if(tempSource.end >= this.ranges.get(i).start && tempSource.end <= this.ranges.get(i).end && tempSource.start < this.ranges.get(i).start){
                    sourceRanges.add(new Range(this.ranges.get(i).start, tempSource.end - this.ranges.get(i).start + 1));
                    tempRanges.add(new Range(tempSource.start, this.ranges.get(i).start - tempSource.start));
                    break;
                }
            }
            if(outside == this.ranges.size()) {
                sourceRanges.add(tempSource);
            }

            tempRanges.remove(tempSource);
        }

        // System.out.print(source.toString() + " became ");
        for(var range: sourceRanges) {
            var newRange = new Range(this.Convert(range.start),range.range);
            destinationRanges.add(newRange);
            // System.out.print(newRange.toString() + ", ");
        }
        // System.out.println();

        return destinationRanges;
    }
}