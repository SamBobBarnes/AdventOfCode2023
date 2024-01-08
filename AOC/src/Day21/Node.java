package Day21;

import java.util.ArrayList;
import java.util.List;

class NodeBase {
    public Node North;
    public Node East;
    public Node South;
    public Node West;
    public boolean Rock;
    public boolean Visited;

    public double Distance = Double.POSITIVE_INFINITY;
    public int x;
    public int y;
    public NodeBase(int x, int y, boolean rock) {
        this.Visited = false;
        this.x = x;
        this.y = y;
        this.Rock = rock;
    }

    public void Visit() {
        this.Visited = true;
    }

    public List<Node> GetUnvisitedNeighbors() {
        var list = new ArrayList<Node>();
        if(North != null && !North.Rock && !North.Visited) list.add(North);
        if(East != null && !East.Rock && !East.Visited) list.add(East);
        if(South != null && !South.Rock && !South.Visited) list.add(South);
        if(West != null && !West.Rock && !West.Visited) list.add(West);
        return list;
    }

    @Override
    public String toString()
    {
        if(Rock) return " # ";
        if(Distance == Double.POSITIVE_INFINITY) return " . ";
        String text = String.valueOf((int)(Distance));
        if(text.length() == 3) return text;
        if(text.length() == 2) return text + " ";
        return " " + text + " ";
    }
}

class Node extends NodeBase implements Comparable<Node> {
    public Node(int x, int y, boolean rock) {
        super(x,y,rock);
    }
    @Override
    public int compareTo(Node o)
    {
        return (int)(this.Distance - o.Distance);
    }
}

class NodeInfinite extends NodeBase {
    public NodeInfinite(int x, int y, boolean rock) {
        super(x,y,rock);
        this.VisitedList = new ArrayList<>();
        this.DistanceList = new ArrayList<>();
    }

    public NodeInfinite North;
    public NodeInfinite East;
    public NodeInfinite South;
    public NodeInfinite West;
    public List<Point> VisitedList;
    public List<Distance> DistanceList;

    public void Visit(int x, int y) {
        this.VisitedList.add(new Point(x,y));
    }

    public boolean isVisitedInfinite(int x, int y) {
        var point = this.VisitedList.stream().filter(v -> v.x == x && v.y == y).findFirst();
        return point.isPresent();
    }

    public void SetDistancInfinite(int x, int y, int distance) {
        var oldDistance = this.DistanceList.stream().filter(d -> d.x == x && d.y == y).findFirst();
        oldDistance.ifPresentOrElse(
                d -> {
                    d.distance = distance;
                },
                () -> {
                    this.DistanceList.add(new Distance(x,y,distance, this));
                }
                                   );
    }

    public double GetDistanceInfinite(int x, int y) {
        var oldDistance = this.DistanceList.stream().filter(d -> d.x == x && d.y == y).findFirst();
        double distance = Double.POSITIVE_INFINITY;
        if(oldDistance.isPresent()) distance = oldDistance.get().distance;
        return distance;
    }

    public List<NodeInfinite> GetUnvisitedNeighborsInfinite(int x, int y) {
        var list = new ArrayList<NodeInfinite>();
        if(!North.Rock && !North.isVisitedInfinite(x, y-1)) list.add(North);
        if(!East.Rock && !East.isVisitedInfinite(x+1, y)) list.add(East);
        if(!South.Rock && !South.isVisitedInfinite(x, y+1)) list.add(South);
        if(!West.Rock && !West.isVisitedInfinite(x-1, y)) list.add(West);
        return list;
    }
}

class Point {
    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Distance extends Point implements Comparable<Distance>{
    public double distance;
    public NodeInfinite node;
    public Distance(int x, int y, int distance, NodeInfinite node) {
        super(x,y);
        this.node = node;
        this.distance = distance;
    }

    @Override
    public int compareTo(Distance o)
    {
        return (int)(this.distance - o.distance);
    }
}
