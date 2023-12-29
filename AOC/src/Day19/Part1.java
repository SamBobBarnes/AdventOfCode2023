package Day19;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(19, 1, example);

        List<String> input = LoadInput(19, example);
        var ruleStrings = new ArrayList<String>();
        var partStrings = new ArrayList<String>();

        var isRule = true;
        for(var line: input) {
            if(line.isEmpty()) isRule = false;
            else {
                if(isRule) ruleStrings.add(line);
                else partStrings.add(line);
            }
        }

        var rules = new ArrayList<Rule>();
        var parts = new ArrayList<Part>();

        for(var rule: ruleStrings) {
            rules.add(new Rule(rule));
        }

        rules.add(new Rule("A"));
        rules.add(new Rule("R"));


        for(var rule: rules) {
            rule.MapOperations(rules);
        }

        for(var part: partStrings) {
            parts.add(new Part(part));
        }

        var acceptedParts = new ArrayList<Part>();
        var in = rules.stream().filter(x -> x.name.equals("in")).findFirst().get();

        for(var part: parts) {
            if(in.Evaluate(part) == 1) acceptedParts.add(part);
        }

        var result = 0;
        for(var part: acceptedParts) {
            result += part.x + part.m + part.a + part.s;
        }

        return result;
    }
}

class Part {
    public int x;
    public int m;
    public int a;
    public int s;
    public Part(String line) {
        var values = line.substring(1,line.length()-1).split(",");
        this.x = parseInt(values[0].substring(2));
        this.m = parseInt(values[1].substring(2));
        this.a = parseInt(values[2].substring(2));
        this.s = parseInt(values[3].substring(2));
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x + "," +
                "m=" + m + "," +
                "a=" + a + "," +
                "s=" + s +
                '}';
    }
}