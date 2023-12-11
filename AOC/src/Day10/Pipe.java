package Day10;

import java.util.List;

class Pipe {
    public Pipe(char pipe, int x, int y) {
        this.angle = pipe;
        this.x = x;
        this.y = y;
    }

    public int x;
    public int y;
    public char angle;

    public Pipe next;
    public Pipe previous;
    public boolean loop;

    public String toString() {
        return angle + ": " + x + "," + y;
    }

    public void MapToPipe(List<Pipe> pipes) {
        int xp = 0;
        int yp = 0;
        int xn = 0;
        int yn = 0;

        switch(this.angle) {
            case '|':
                xp = this.x;
                yp = this.y+1;
                xn = this.x;
                yn = this.y-1;
                break;
            case '-':
                xp = this.x-1;
                yp = this.y;
                xn = this.x+1;
                yn = this.y;
                break;
            case 'F':
                xp = this.x;
                yp = this.y+1;
                xn = this.x+1;
                yn = this.y;
                break;
            case 'L':
                xp = this.x+1;
                yp = this.y;
                xn = this.x;
                yn = this.y-1;
                break;
            case 'J':
                xp = this.x;
                yp = this.y-1;
                xn = this.x-1;
                yn = this.y;
                break;
            case '7':
                xp = this.x-1;
                yp = this.y;
                xn = this.x;
                yn = this.y+1;
                break;
            default:
                return;
        }

        int finalXp = xp;
        int finalYp = yp;
        var previous = pipes.stream().filter(p -> p.x == finalXp && p.y == finalYp).findFirst();
        previous.ifPresent(x -> this.previous = x);
        int finalYn = yn;
        int finalXn = xn;
        var next = pipes.stream().filter(p -> p.x == finalXn && p.y == finalYn).findFirst();
        next.ifPresent(x -> this.next = x);
    }

    public void FindStartNeighbors(List<Pipe> pipes) {
        var up = pipes.stream().filter(p -> p.x == this.x && p.y == this.y-1).findFirst();
        var down = pipes.stream().filter(p -> p.x == this.x && p.y == this.y+1).findFirst();
        var left = pipes.stream().filter(p -> p.x == this.x-1 && p.y == this.y).findFirst();
        var right = pipes.stream().filter(p -> p.x == this.x+1 && p.y == this.y).findFirst();

        up.ifPresent(x -> {
            if(x.previous == this || x.next == this) this.next = x;
        });
        right.ifPresent(x -> {
            if(x.previous == this || x.next == this) {
                if(this.next == null) this.next = x;
                else this.previous = x;
            }

        });
        down.ifPresent(x -> {
            if(x.previous == this || x.next == this) {
                if(this.next == null) this.next = x;
                else this.previous = x;
            }
        });
        left.ifPresent(x -> {
            if(x.previous == this || x.next == this) {
                if(this.next == null) this.next = x;
                else this.previous = x;
            }
        });
    }
}