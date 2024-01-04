package Day20;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Part2 extends AdventBase {
    public static long Run(boolean example) {
        Start(20, 2, example);

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
        var buttonPresses = 100000;
        var buttonPressesUsed = new ArrayList<Integer>();
        var moduleFrom = new ArrayList<String>();

        for(int i = 0; i < buttonPresses; i++) {
            q.add(new Pulse(null,broadcast,-1));
            Pulse lastOutput = null;
            while(!q.isEmpty()) {
                var pulse = q.removeFirst();
                if(Objects.equals(pulse.SentTo.Name, "ll") && pulse.Frequency == 1) {
                    buttonPressesUsed.add(i);
                    moduleFrom.add(pulse.From.Name);
                }
                if(pulse.SentTo.Type == 'O') lastOutput = pulse;
                if(pulse.Frequency < 0) lowTally++;
                else highTally++;
                var newPulses = pulse.SentTo.Pulse(pulse);
                q.addAll(newPulses);
            }
            if(lastOutput != null && lastOutput.Frequency < 0) break;
        }

        var a = buttonPressesUsed.get(0) + 1;
        var b = buttonPressesUsed.get(1) + 1;
        var c = buttonPressesUsed.get(2) + 1;
        var d = buttonPressesUsed.get(3) + 1;

        var aPrimes = primeFactors(a);
        var bPrimes = primeFactors(b);
        var cPrimes = primeFactors(c);
        var dPrimes = primeFactors(d);

        long lcm = (long) a * b * c * d;

        return lcm;
    }

    private static List<Integer> primeFactors(int n)
    {
        var primes = new ArrayList<Integer>();
        while (n%2==0)
        {
            primes.add(2);
            n /= 2;
        }

        for (int i = 3; i <= Math.sqrt(n); i+= 2)
        {
            while (n%i == 0)
            {
                primes.add(i);
                n /= i;
            }
        }

        if (n > 2)
            primes.add(n);

        return primes;
    }
}