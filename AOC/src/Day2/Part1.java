package Day2;

import Base.AdventBase;

import java.util.List;

import static java.lang.Integer.parseInt;

public class Part1 extends AdventBase {
    public static int Run() {
        Start(2,1);

        List<String> input = LoadInput(2,false);

        int maxRed = 12;
        int maxGreen = 13;
        int maxBlue = 14;

        int result = 0;

        for(String line: input) {
            boolean possible = true;
            int game = parseInt(line.substring(5,line.indexOf(':')));
            String setString = line.substring(line.indexOf(':')+1).replaceAll(" ","");
            String[] sets = setString.split(";");
            for(String set: sets) {
                String[] cubes = set.split(",");

                for(String count: cubes) {
                    if(count.contains("red")) {
                        int red = parseInt(count.substring(0,count.indexOf("red")));
                        if (red > maxRed) possible = false;
                    }
                    else if (count.contains("green")) {
                        int green = parseInt(count.substring(0,count.indexOf("green")));
                        if (green > maxGreen) possible = false;
                    }
                    else {
                        int blue = parseInt(count.substring(0,count.indexOf("blue")));
                        if (blue > maxBlue) possible = false;
                    }
                }
            }

            if(possible) result += game;
        }

        return result;
    }
}
