package Day11;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

public class Part2 extends AdventBase {
    public static int Run(boolean example) {
        Start(11, 2, example);

        var expandBy = example ? 100 : 1000000;

        char[][] input = LoadInputChars(11, example).toArray(char[][]::new);

        boolean[] colsToExpand = new boolean[input[0].length];
        boolean[] rowsToExpand = new boolean[input.length];
        int expandVertically = 0;
        int expandHorizontally = 0;

        for(int i = 0; i < input.length; i++) {
            if (CheckForBlankRow(input, i)) {
                rowsToExpand[i] = true;
                expandVertically++;
            }
            else rowsToExpand[i] = false;
        }
        for(int i = 0; i < input[0].length; i++) {
            if (CheckForBlankColumn(input, i)) {
                colsToExpand[i] = true;
                expandHorizontally++;
            }
            else colsToExpand[i] = false;
        }

        char[][] expandedGridStep1 = new char[input.length][input[0].length+expandHorizontally];
        char[][] expandedGrid = new char[input.length+expandVertically][input[0].length+expandHorizontally];

        for(int j = 0; j < input.length; j++) {
            int offset = 0;
            for(int i = 0; i < input[0].length; i++) {
                if(colsToExpand[i]) {
                    expandedGridStep1[j][i+offset] = '.';
                    offset++;
                }
                expandedGridStep1[j][i+offset] = input[j][i];
            }
        }

        int offset = 0;
        for(int j = 0; j < expandedGridStep1.length; j++) {
            if(rowsToExpand[j]) {
                offset++;
            }
            for(int i = 0; i < expandedGridStep1[0].length; i++) {
                if(rowsToExpand[j]) {
                    expandedGrid[j+offset-1][i] = '.';
                }
                expandedGrid[j+offset][i] = expandedGridStep1[j][i];
            }
        }

        var galaxies = new ArrayList<Vert>();

        for(int j = 0; j < expandedGrid.length; j++) {
            for(int i = 0; i < expandedGrid[0].length; i++) {
                if(expandedGrid[j][i] == '#') galaxies.add(new Vert(i,j));
            }
        }

        var result = 0;

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
