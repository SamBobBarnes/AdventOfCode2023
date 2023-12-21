package Day17;

import java.util.ArrayList;
import java.util.List;

class Node implements Comparable<Node>
{
    public Node(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.neighbors = new ArrayList<>();
        this.visited = false;
        this.distance = Double.POSITIVE_INFINITY;
    }
    public boolean visited;
    public List<Node> neighbors;
    public int value;
    public int x;
    public int y;
    public double distance;

    public void AddNeighbor(Node node) {
        this.neighbors.add(node);
    }

    public List<Node> UnvisitedNeighbors() {
        return this.neighbors.stream().filter(n -> !n.visited).toList();
    }

    public void Visit() {
        this.visited = true;
    }

    public String toString() {
        return x + "," + y + " " + value + ":" + distance;
    }

    @Override
    public int compareTo(Node o)
    {
        return (int)(this.distance - o.distance);
    }
}

enum Direction {
    North('N'),
    South('S'),
    East('E'),
    West('W');

    public final char value;

    Direction(char value) {
        this.value = value;
    }
}