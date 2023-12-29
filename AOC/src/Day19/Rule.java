package Day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            for(var operation: operations) {
                ops.add(new Operation(operation));
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

    @Override
    public String toString() {
        return name;
    }
}

class Operation {
    public char compare;
    public int compareAgainst;
    public boolean gt;
    public String destString;
    public Rule dest;
    public Operation(String line) {
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

    @Override
    public String toString() {
        if(compareAgainst == 0) {
            return destString;
        }
        return compare + (gt ? ">" : "<") + compareAgainst + ":" + destString;
    }
}
