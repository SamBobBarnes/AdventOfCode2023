package Day11;

class Vert {
    public Vert(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x;
    public int y;
}

class VertPair {
    public VertPair(Vert a, Vert b) {
        this.a = a;
        this.b = b;
    }

    public Vert a;
    public Vert b;

    public int GetDistanceBetween() {
        return Math.abs(a.x-b.x) + Math.abs(a.y- b.y);
    }
}