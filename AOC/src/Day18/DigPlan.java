package Day18;

class DigPlan {
    public char direction;
    public long distance;
    public long startX;
    public long startY;
    public long endX;
    public long endY;
    public DigPlan(char direction, int distance) {
        this.direction = direction;
        this.distance = distance;
    }

    public DigPlan(String hex) {
        var directionChar = hex.charAt(6);
        switch(directionChar) {
            case '0': this.direction = 'R'; break;
            case '1': this.direction = 'D'; break;
            case '2': this.direction = 'L'; break;
            case '3': this.direction = 'U'; break;
        }

        this.distance = Long.decode(hex.substring(0,6));

    }

    public void SetStart(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    public void SetEnd(int x, int y) {
        this.endX = x;
        this.endY = y;
    }

    public long length() {
        if(startY == endY) return Math.abs(endX-startX);
        return Math.abs(endY-startY);
    }

    @Override
    public String toString() {
        return this.endX + "," + this.endY;
    }
}