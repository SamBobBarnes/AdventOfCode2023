package Day14;

import java.util.Comparator;

class Rock {
    public Rock(char rock, int x, int y) {
        if(rock == 'O') this.round = true;
        else this.round = false;
        this.x = x;
        this.y = y;
    }
    public int x;
    public int y;
    public boolean round;
    public String toString() {
        return x + "," + y + " " + (round ? "O" : "#");
    }
}

class RockComparatorNorth implements Comparator<Rock>
{
    @Override
    public int compare(Rock a, Rock b) {
        return a.y - b.y;
    }
}
class RockComparatorSouth implements Comparator<Rock>
{
    @Override
    public int compare(Rock a, Rock b) {
        return b.y - a.y;
    }
}
class RockComparatorWest implements Comparator<Rock>
{
    @Override
    public int compare(Rock a, Rock b) {
        return b.x - a.x;
    }
}
class RockComparatorEast implements Comparator<Rock>
{
    @Override
    public int compare(Rock a, Rock b) {
        return a.x - b.x;
    }
}