package Day19;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

public class Part2 extends AdventBase {
    public static long Run(boolean example) {
        Start(19, 2, example);

        List<String> input = LoadInput(19, example);
        var ruleStrings = new ArrayList<String>();

        var isRule = true;
        for(var line: input) {
            if(line.isEmpty()) isRule = false;
            else {
                if(isRule) ruleStrings.add(line);
            }
        }

        var rules = new ArrayList<Rule>();

        for(var rule: ruleStrings) {
            rules.add(new Rule(rule));
        }

        rules.add(new Rule("A"));
        rules.add(new Rule("R"));


        for(var rule: rules) {
            rule.MapOperations(rules);
        }

        var partsList = new ArrayList<Step>();
        var acceptedParts = new ArrayList<PartRange>();
        var in = rules.stream().filter(x -> x.name.equals("in")).findFirst().get();
        partsList.add(new Step(new PartRange(), in, 0));

        while (!partsList.isEmpty()) {
            var step = partsList.removeFirst();
            in.EvaluateRange(step, partsList, acceptedParts);
        }

        long result = 0;

        for(var parts: acceptedParts) {
            result += parts.Options();
        }

        return result;
    }
}

class Step {
    public PartRange parts;
    public Rule rule;
    public int step;

    public Step(PartRange parts, Rule rule, int step) {
        this.parts = parts;
        this.rule = rule;
        this.step = step;
    }
}

class PartRange {
    public Range x;
    public Range m;
    public Range a;
    public Range s;

    public PartRange() {
        x = new Range(1,4000);
        m = new Range(1,4000);
        a = new Range(1,4000);
        s = new Range(1,4000);
    }

    public PartRange Split(int variable, int splitPoint) {
        var newPart = new PartRange();
        newPart.x = this.x;
        newPart.m = this.m;
        newPart.a = this.a;
        newPart.s = this.s;
        switch(variable) {
            case 0:
                newPart.x = this.x.SplitAt(splitPoint);
                break;
            case 1:
                newPart.m = this.m.SplitAt(splitPoint);
                break;
            case 2:
                newPart.a = this.a.SplitAt(splitPoint);
                break;
            case 3:
                newPart.s = this.s.SplitAt(splitPoint);
                break;
        }
        return newPart;
    }

    public long Options() {
        return (long) x.length() * m.length() * a.length() * s.length();
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

class Range {
    public int start;
    public int end;
    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int length() {
        return (end-start)+1;
    }

    public Range SplitAt(int splitPoint) {
        var newRange = new Range(splitPoint, this.end);
        this.end = splitPoint - 1;
        return newRange;
    }

    @Override
    public String toString() {
        return start + "-" + end;
    }
}