package Day15;

import java.util.ArrayList;
import java.util.Objects;

class Lens
{
    public Lens(String label, boolean insert, int hash, int lensValue) {
        this.Label = label;
        this.Insert = insert;
        this.Hash = hash;
        this.Lens = lensValue;
    }
    public String Label;
    public boolean Insert;
    public int Hash;
    public int Lens;
    public String toString() {
        if(this.Insert) return this.Label + " " + this.Lens + " " + this.Hash;
        else return this.Label + " -";
    }
}

class LensBox
{
    public LensBox() {
        this.Box = new ArrayList<>();
        for(int i = 0; i < 256; i++) {
            this.Box.add(new ArrayList<>());
        }
    }
    public ArrayList<ArrayList<Lens>> Box;

    public void Remove(Lens lens) {
        Box.get(lens.Hash).removeIf(l -> Objects.equals(l.Label, lens.Label));
    }

    public void Insert(Lens lens) {
        var oldLens = Box.get(lens.Hash).stream().filter(l -> Objects.equals(l.Label, lens.Label)).findFirst();
        if(oldLens.isPresent()) {
            var index = Box.get(lens.Hash).indexOf(oldLens.get());
            Box.get(lens.Hash).set(index, lens);
        }
        else Box.get(lens.Hash).add(lens);


    }
}
