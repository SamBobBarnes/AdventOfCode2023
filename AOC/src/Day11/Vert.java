package Day11;

class Vert {
    public Vert(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long x;
    public long y;
}

class VertPair {
    public VertPair(Vert a, Vert b) {
        this.a = a;
        this.b = b;
    }

    public Vert a;
    public Vert b;

    public long GetDistanceBetween() {
        return Math.abs(a.x-b.x) + Math.abs(a.y-b.y);
    }
}