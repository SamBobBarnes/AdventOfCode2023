package Day22;

import Base.AdventBase;

import java.util.*;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(22, 1, example);

        List<String> input = LoadInput(22, example);

        List<Cube> blocks = new ArrayList<>();

        for(var line: input) {
            blocks.add(new Cube(line));
        }

        Collections.sort(blocks);

        while(BlocksLeftToFall(blocks)) {
            var falling = FallingBlocks(blocks);
            var current = falling.getFirst();
            if(current.CanFall(blocks)) {
                current.Fall();
            }
        }

        var result = 0;
        for(var block: blocks) {
            if(block.CanDisintegrate()) result++;
        }

        return result;
    }

    private static boolean BlocksLeftToFall(List<Cube> blocks) {
        return blocks.stream().anyMatch(x -> !x.resting);
    }

    private static List<Cube> FallingBlocks(List<Cube> blocks) {
        return blocks.stream().filter(x -> !x.resting).toList();
    }
}

class Cube implements Comparable<Cube> {
    public Coordinate origin;
    public Coordinate extent;
    public int length; // y
    public int width; // x
    public int height; // z
    public int lowestPoint;
    public int highestPoint;
    public List<Cube> restsOn;
    public List<Cube> supporting;
    public boolean resting;
    public Cube(String line) {
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

    public boolean CanFall(List<Cube> cubes) {
        if(lowestPoint == 1) {
            resting = true;
            return false;
        }
        var nextCubes = cubes.stream().filter(x -> x.highestPoint == lowestPoint-1 ).toList();
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
        Cube cube = (Cube) o;
        return origin.equals(cube.origin) && extent.equals(cube.extent);
    }

    @Override
    public int compareTo(Cube o)
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