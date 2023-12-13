package Day11;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

public class Part1 extends AdventBase {
    public static long Run(boolean example) {
        Start(11, 1, example);

        char[][] input = LoadInputChars(11, example).toArray(char[][]::new);

        List<Integer> colsToExpand = new ArrayList<>();
        List<Integer> rowsToExpand = new ArrayList<>();

        for(int i = 0; i < input.length; i++) {
            if (CheckForBlankRow(input, i)) {
                rowsToExpand.add(i);
            }
        }
        for(int i = 0; i < input[0].length; i++) {
            if (CheckForBlankColumn(input, i)) {
                colsToExpand.add(i);
            }
        }

        var galaxies = new ArrayList<Vert>();

        for(int j = 0; j < input.length; j++) {
            for(int i = 0; i < input[0].length; i++) {
                if(input[j][i] == '#') galaxies.add(new Vert(i,j));
            }
        }

        for(var galaxy: galaxies) {
            var yOffset = rowsToExpand.stream().filter(r -> r < galaxy.y).count();
            var xOffset = colsToExpand.stream().filter(r -> r < galaxy.x).count();

            galaxy.x += xOffset;
            galaxy.y += yOffset;
        }

        long result = 0;

        var pairs = GetPairs(galaxies);

        for(var pair: pairs) {
            result += pair.GetDistanceBetween();
        }

        return result;
    }

    private static boolean CheckForBlankColumn(char[][] grid, int x) {
        for(int i = 0; i < grid[0].length; i++) {
            if(grid[i][x] == '#') return false;
        }
        return true;
    }

    private static boolean CheckForBlankRow(char[][] grid, int y) {
        for(int i = 0; i < grid.length; i++) {
            if(grid[y][i] == '#') return false;
        }
        return true;
    }

    private static List<VertPair> GetPairs(List<Vert> verts) {
        var pairs = new ArrayList<VertPair>();
        for (int i = 0; i < verts.size(); i++) {
            for (int j = i + 1; j < verts.size(); j++) {
                pairs.add(new VertPair(verts.get(i),verts.get(j)));
            }
        }
        return pairs;
    }
}
