package Day18;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Part2 extends AdventBase {
    public static long Run(boolean example) {
        Start(18, 2, example);

        List<String> input = LoadInput(18, example);

        List<DigPlan> plans = input.stream().map(x -> new DigPlan(x.split(" ")[2].substring(1,8))).toList();

        var result = FindArea(plans);

        return result;
    }

    private static long FindArea(List<DigPlan> plans) {
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

    private static long Shoelace(List<DigPlan> plans) {
        var temp = new ArrayList<>(plans);
        Collections.reverse(temp);
        long result = 0;
        long perimeter = 0;
        for(var plan: temp) {
            var x0 = plan.endX;
            var y0 = plan.endY;
            var x1 = plan.startX;
            var y1 = plan.startY;

            result += (x0 * y1) - (x1*y0);
            perimeter += plan.length();
        }
        return (Math.abs(result)/2 + perimeter/2) + 1;
    }
}

