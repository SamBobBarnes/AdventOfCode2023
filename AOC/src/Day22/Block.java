package Day22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class Block implements Comparable<Block> {
    public Coordinate origin;
    public Coordinate extent;
    public int length; // y
    public int width; // x
    public int height; // z
    public int lowestPoint;
    public int highestPoint;
    public List<Block> restsOn;
    public List<Block> supporting;
    public boolean resting;
    public Block(Block block) {
        this(block.BlockString());
    }
    public Block(String line) {
        var coords = line.split("~");
        int[] coord0 = Arrays.stream(coords[0].split(",")).mapToInt(Integer::parseInt).toArray();
        int[] coord1 = Arrays.stream(coords[1].split(",")).mapToInt(Integer::parseInt).toArray();
        origin = new Coordinate(coord0[0], coord0[1], coord0[2]);
        extent = new Coordinate(coord1[0], coord1[1], coord1[2]);
        length = Math.abs(origin.y - extent.y) + 1;
        width = Math.abs(origin.x - extent.x) + 1;
        height = Math.abs(origin.z - extent.z) + 1;
        lowestPoint = Math.min(origin.z, extent.z);
        highestPoint = Math.max(origin.z, extent.z);
        restsOn = new ArrayList<>();
        resting = false;
        supporting = new ArrayList<>();
    }

    public String BlockString() {
        return origin.x +
                "," +
                origin.y +
                "," +
                origin.z +
                "~" +
                extent.x +
                "," +
                extent.y +
                "," +
                extent.z;
    }

    public List<Coordinate> GetXYMap() {
        var list = new ArrayList<Coordinate>();
        if(height > 1) {
            list.add(origin);
            return list;
        }
        if(length > 1) {
            var high = Math.max(origin.y, extent.y);
            var low = Math.min(origin.y, extent.y);
            for(int i = low; i <= high; i++) {
                list.add(new Coordinate(origin.x,i, origin.z));
            }
            return list;
        }
        if(width > 1) {
            var high = Math.max(origin.x, extent.x);
            var low = Math.min(origin.x, extent.x);
            for(int i = low; i <= high; i++) {
                list.add(new Coordinate(i,origin.y, origin.z));
            }
            return list;
        }
        list.add(origin);
        return list;
    }

    public void Fall() {
        origin.z--;
        extent.z--;
        lowestPoint--;
        highestPoint--;
    }

    public boolean CanFall(List<Block> blocks) {
        if(lowestPoint == 1) {
            resting = true;
            return false;
        }
        var nextCubes = blocks.stream().filter(x -> x.highestPoint == lowestPoint-1).toList();
        var thisCoords = GetXYMap();
        for(var cube: nextCubes) {
            if(cube.equals(this)) continue;
            var matching = false;
            var cubeCoords = cube.GetXYMap();
            for(var coord: thisCoords) {
                for(var cubeCoord: cubeCoords) {
                    if(coord.CompareXY(cubeCoord)) matching = true;
                }
            }
            if(matching) {
                restsOn.add(cube);
                cube.supporting.add(this);
            }
        }
        if(!restsOn.isEmpty()) {
            resting = true;
            return false;
        }
        return true;
    }

    public boolean CanDisintegrate() {
        for(var block: supporting) {
            if(block.restsOn.size() == 1) return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return origin.equals(block.origin) && extent.equals(block.extent);
    }

    @Override
    public int compareTo(Block o)
    {
        return lowestPoint - o.lowestPoint;
    }

    @Override
    public String toString()
    {
        return lowestPoint + " : " + (resting ? "resting : ": "") + (CanDisintegrate() ? "Disintegrate" : "");
    }
}

class Coordinate {
    public int x;
    public int y;
    public int z;
    public Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString()
    {
        return  x + "," + y + "," + z;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate coordinate = (Coordinate) o;
        return Objects.equals(x, coordinate.x) && Objects.equals(y, coordinate.y) && Objects.equals(z, coordinate.z);
    }

    public boolean CompareXY(Coordinate c) {
        return x == c.x && y == c.y;
    }
}