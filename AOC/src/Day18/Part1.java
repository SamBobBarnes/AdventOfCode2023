package Day18;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(18, 1, example);

        List<String> input = LoadInput(18, example);

        List<DigPlan> plans = input.stream().map(x -> new DigPlan(x.split(" ")[0].charAt(0),parseInt(x.split(" ")[1]))).toList();

        var result = FindArea(plans);

        return result;
    }

    private static int FindArea(List<DigPlan> plans) {
        var x = 0;
        var y = 0;

        for(var plan: plans) {
            plan.SetStart(x,y);
            for(int i = 0; i < plan.distance; i++) {
                switch(plan.direction) {
                    case 'U':
                        y--;
                        break;
                    case 'D':
                        y++;
                        break;
                    case 'L':
                        x--;
                        break;
                    case 'R':
                        x++;
                        break;
                }
            }
            plan.SetEnd(x,y);
        }

        return Shoelace(plans);
    }

    private static int Shoelace(List<DigPlan> plans) {
        var temp = new ArrayList<>(plans);
        Collections.reverse(temp);
        var result = 0;
        var perimeter = 0;
        for(var plan: temp) {
            var x0 = plan.endX;
            var y0 = plan.endY;
            var x1 = plan.startX;
            var y1 = plan.startY;

            result += (int) ((x0 * y1) - (x1*y0));
            perimeter += (int) plan.length();
        }
        return (Math.abs(result)/2 + perimeter/2) + 1;
    }
}

