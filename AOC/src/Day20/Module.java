package Day20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class Module
{
    public String Name;
    public char Type;
    protected List<String> DownstreamModuleNames;
    protected List<Module> DownstreamModules;
    public Module(String line) {
        var components = Arrays.stream(line.split(" ")).toList();
        DownstreamModuleNames = new ArrayList<>();
        if(components.size() > 1) {
            var moduleNames = components.subList(2,components.size());
            for(var name: moduleNames) {
                DownstreamModuleNames.add(name.replace(',', ' ').trim());
            }
            var nameString = components.getFirst();
            if(nameString.equals("broadcaster")) {
                Type = 'B';
                Name = "Broadcaster";
            }
            else {
                Type = nameString.charAt(0);
                Name = nameString.substring(1);
            }
        }

    }
    abstract List<Pulse> Pulse(Pulse pulse);
    public void MapModules(List<Module> modules) {
        DownstreamModules = new ArrayList<>();
        for(var module: DownstreamModuleNames) {
            var dsmod = modules.stream().filter(x -> x.Name.equals(module)).findFirst();
            dsmod.ifPresentOrElse(
                    x -> DownstreamModules.add(x),
                    () -> DownstreamModules.add(new Output("Output")));
        }
    }

    protected List<Pulse> SendToAllDownstream(int Frequency) {
        var newPulses = new ArrayList<Pulse>();
        for(var module: DownstreamModules) {
            newPulses.add(new Pulse(this,module,Frequency));
        }
        return newPulses;
    }

    @Override
    public String toString()
    {
        return Type + Name;
    }
}

class Output extends Module {
    public Output(String line) {
        super(line);
    }

    public List<Pulse> Pulse(Pulse pulse) {
        return new ArrayList<>();
    }
}

class Broadcaster extends Module {
    public Broadcaster(String line) {
        super(line);
    }

    public List<Pulse> Pulse(Pulse pulse) {

        return SendToAllDownstream(pulse.Frequency);
    }
}

class FlipFlop extends Module {
    private boolean On;
    public FlipFlop(String line) {
        super(line);
        On = false;
    }

    public List<Pulse> Pulse(Pulse pulse) {
        if(pulse.Frequency < 0) {
            On = !On;
            return SendToAllDownstream(On ? 1 : -1);
        }
        return new ArrayList<>();
    }
}

class Conjunction extends Module {
    public List<Pulse> StoredPulses;
    public Conjunction(String line) {
        super(line);
    }

    public void MapInputs(List<Module> modules) {
        StoredPulses = new ArrayList<>();
        for(var module: modules) {
            if(module.DownstreamModuleNames.stream().anyMatch(x -> x.equals(Name))) {
                StoredPulses.add(new Pulse(module,this,-1));
            }
        }
    }

    public List<Pulse> Pulse(Pulse pulse) {
        var index = StoredPulses.indexOf(StoredPulses.stream().filter(x -> x.From.Name.equals(pulse.From.Name)).findFirst().get());
        StoredPulses.set(index, pulse);
        if(CheckForHighs()) return SendToAllDownstream(-1);
        return SendToAllDownstream(1);
    }

    private boolean CheckForHighs() {
        for(var module: StoredPulses) {
            if(module.Frequency < 0) return false;
        }
        return true;
    }
}

class Pulse {
    public Module SentTo;
    public Module From;
    public int Frequency;
    public Pulse(Module from, Module sendTo, int frequency) {
        From = from;
        SentTo = sendTo;
        Frequency = frequency;
    }

    @Override
    public String toString()
    {
        return From.toString() + " -(" + Frequency + ")> " + SentTo.toString();
    }
}
