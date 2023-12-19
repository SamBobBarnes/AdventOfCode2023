package Day15;

import Base.AdventBase;

import java.util.ArrayList;

public class Part2 extends AdventBase {
    public static long Run(boolean example) {
        Start(15, 2, example);

        String input = LoadInput(15, example).getFirst();
        var strings = input.split(",");
        var lensList = new ArrayList<Lens>();
        for(var string: strings) {
            lensList.add(Hash.parseStringToLens(string));
        }

        var lensBox = new LensBox();

        for(var lens: lensList) {
            if(lens.Insert) lensBox.Insert(lens);
            else lensBox.Remove(lens);
        }

        var result = 0;
        for(var box: lensBox.Box) {
            for(int i = 0; i < box.size(); i++) {
                var focusingPower = (box.get(i).Hash + 1) * (i + 1) * box.get(i).Lens;
                result += focusingPower;
            }
        }

        return result;
    }
}