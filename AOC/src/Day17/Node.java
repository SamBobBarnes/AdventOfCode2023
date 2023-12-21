package Day17;

import java.util.ArrayList;
import java.util.List;

class Node
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
    public List<Edge> neighbors;
    public int value;
    public int x;
    public int y;
    public double distance;

    public void AddNeighbor(Node node) {
        Direction direction;
        if(node.x > this.x) direction = Direction.East;
        else if(node.x < this.x) direction = Direction.West;
        else if(node.y > this.y) direction = Direction.South;
        else direction = Direction.North;

        this.neighbors.add(new Edge(direction,this, node));
    }

    public void Visit() {
        this.visited = true;
    }

    public String toString() {
        return x + "," + y + " " + value + ":" + distance;
    }
}

class Edge {
    public Edge(Direction dir, Node node1, Node node2) {
        this.direction = dir;
        this.node1 = node1;
        this.node2 = node2;
    }
    public Direction direction;
    public Node node1;
    public Node node2;

    public Node NextNode(Node node) {
        if(node.equals(node1)) return node2;
        return node1;
    }

    public boolean equals(Edge edge) {
        return (this.node1 == edge.node1 && this.node2 == edge.node2)
                || (this.node1 == edge.node2 && this.node2 == edge.node1);
    }

    public String toString() {
        return node1.x + "," + node1.y + " --" + direction.value + "-> " + node2.x + "," + node2.y;
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