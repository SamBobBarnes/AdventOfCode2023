package Day2;

import Base.AdventBase;

import java.util.List;

import static java.lang.Integer.parseInt;

public class Part2 extends AdventBase {
    public static int Run(boolean example) {
        Start(2,2, example);

        List<String> input = LoadInput(2,example);

        int result = 0;

        for(String line: input) {
            int minRed = 0;
            int minGreen = 0;
            int minBlue = 0;

            String setString = line.substring(line.indexOf(':')+1).replaceAll(" ","");
            String[] sets = setString.split(";");
            for(String set: sets) {
                String[] cubes = set.split(",");

                for(String count: cubes) {
                    if(count.contains("red")) {
                        int red = parseInt(count.substring(0,count.indexOf("red")));
                        if (red > minRed) minRed = red;
                    }
                    else if (count.contains("green")) {
                        int green = parseInt(count.substring(0,count.indexOf("green")));
                        if (green > minGreen) minGreen = green;
                    }
                    else {
                        int blue = parseInt(count.substring(0,count.indexOf("blue")));
                        if (blue > minBlue) minBlue = blue;
                    }
                }
            }

            int power = minRed * minBlue * minGreen;

            result += power;
        }

        return result;
    }
}
