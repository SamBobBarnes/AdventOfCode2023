package Day20;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(20, 1, example);

        List<String> input = LoadInput(20, example);

        var modules = new ArrayList<Module>();

        for(var line: input) {
            switch(line.charAt(0)) {
                case 'b': modules.add(new Broadcaster(line)); break;
                case '%': modules.add(new FlipFlop(line)); break;
                case '&': modules.add(new Conjunction(line)); break;
            }
        }

        for(var module: modules) {
            module.MapModules(modules);
        }

        for(var module: modules) {
            if(module.Type == '&') ((Conjunction)module).MapInputs(modules);
        }

        var broadcast = modules.stream().filter(x -> x.Type == 'B').findFirst().get();
        var q = new ArrayList<Pulse>();
        var lowTally = 0;
        var highTally = 0;
        var buttonPresses = 1000;

        for(int i = 0; i < buttonPresses; i++) {
            q.add(new Pulse(null,broadcast,-1));
            while(!q.isEmpty()) {
                var pulse = q.removeFirst();
                if(pulse.Frequency < 0) lowTally++;
                else highTally++;
                var newPulses = pulse.SentTo.Pulse(pulse);
                q.addAll(newPulses);
            }
        }

        return lowTally * highTally;
    }
}