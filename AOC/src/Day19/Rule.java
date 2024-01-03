package Day19;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

class Rule {
    public String name;
    public List<Operation> ops;
    public char ender;
    public Rule(String line) {
        ops = new ArrayList<>();
        if(line.length() == 1) {
            ender = line.charAt(0);
            name = line;
        }
        else {
            var sections = line.split("\\{");
            name = sections[0];
            var operations = sections[1].substring(0,sections[1].length()-1).split(",");
            var index = 0;
            for(var operation: operations) {
                ops.add(new Operation(operation, this, index));
                index++;
            }
        }
    }

    public void MapOperations(List<Rule> rules) {
        for(var op: ops) {
            op.dest = rules.stream().filter(x -> x.name.equals(op.destString)).findFirst().get();
        }
    }

    public int Evaluate(Part part) {
        if(ender == 'A' || ender == 'R') return ender == 'A' ? 1 : -1;
        for(var op: ops) {
            var result = op.Evaluate(part);
            if(result != 0) return result;
        }
        return 0;
    }

    public int EvaluateRange(Step step, ArrayList<Step> steps, ArrayList<PartRange> accepted) {
        if(ender == 'R') return -1;
        if(ender == 'A') {
            accepted.add(step.parts);
            return 1;
        }
        for(var op: ops) {
            var result = op.EvaluateRange(step, steps, accepted);
            if(result != 0) return result;
        }
        return 0;
    }

    public int EvaluateRange(Step step, ArrayList<Step> steps, ArrayList<PartRange> accepted, int index) {
        if(ender == 'R') return -1;
        if(ender == 'A') {
            accepted.add(step.parts);
            return 1;
        }
        for(int i = index; i < ops.size(); i++) {
            var result = ops.get(i).EvaluateRange(step, steps, accepted);
            if(result != 0) return result;
        }
        return 0;
    }

    public static void EvaluateRangeAtStep(Step step, ArrayList<Step> steps, ArrayList<PartRange> accepted) {
        step.rule.EvaluateRange(step, steps, accepted,step.step);
    }

    @Override
    public String toString() {
        return name;
    }
}

class Operation {
    public Rule parent;
    public int parentIndex;
    public char compare;
    public int compareAgainst;
    public boolean gt;
    public String destString;
    public Rule dest;
    public Operation(String line, Rule parent, int parentIndex) {
        var ops = line.split(":");
        if(ops.length > 1) {
            destString = ops[1];
            var op = ops[0].charAt(1);
            gt = op == '>';
            compare = ops[0].charAt(0);
            compareAgainst = parseInt(ops[0].substring(2));
        }
        else {
            destString = ops[0];
        }
        this.parent = parent;
        this.parentIndex = parentIndex;
    }

    public int Evaluate(Part part) {
        var compareValue = 0;
        switch (compare) {
            case 'x': compareValue = part.x; break;
            case 'm': compareValue = part.m; break;
            case 'a': compareValue = part.a; break;
            case 's': compareValue = part.s; break;
            default: return dest.Evaluate(part);
        }
        if(gt) {
            if(compareValue > compareAgainst) return dest.Evaluate(part);
            else return 0;
        }
        else {
            if(compareValue < compareAgainst) return dest.Evaluate(part);
            else return 0;
        }
    }

    public int EvaluateRange(Step step, ArrayList<Step> steps, ArrayList<PartRange> accepted) {
        Range compareValue;
        int variable;
        switch (compare) {
            case 'x': compareValue = step.parts.x; variable = 0; break;
            case 'm': compareValue = step.parts.m; variable = 1; break;
            case 'a': compareValue = step.parts.a; variable = 2; break;
            case 's': compareValue = step.parts.s; variable = 3; break;
            default: return dest.EvaluateRange(step, steps, accepted);
        }

        if(gt) {
            if(compareValue.start > compareAgainst) {
                //send on
                return dest.EvaluateRange(step, steps, accepted);
            }
            if(compareValue.end <= compareAgainst) {
                //send back
                return 0;
            }
            else {
                //split
                var newRange = step.parts.Split(variable, compareAgainst+1);
                steps.add(new Step(newRange,parent,parentIndex));
                return 0;
            }
        }
        else {
            if(compareValue.start >= compareAgainst) {
                //send back
                return 0;

            }
            if(compareValue.end < compareAgainst) {
                //send on
                return dest.EvaluateRange(step, steps, accepted);
            }
            else {
                //split
                var newRange = step.parts.Split(variable, compareAgainst);
                steps.add(new Step(newRange,parent,parentIndex));
                return dest.EvaluateRange(step, steps, accepted);
            }
        }
    }

    @Override
    public String toString() {
        if(compareAgainst == 0) {
            return destString;
        }
        return compare + (gt ? ">" : "<") + compareAgainst + ":" + destString;
    }
}
