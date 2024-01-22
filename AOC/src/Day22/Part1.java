package Day22;

import Base.AdventBase;

import java.util.*;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(22, 1, example);

        List<String> input = LoadInput(22, example);

        List<Block> blocks = new ArrayList<>();

        for(var line: input) {
            blocks.add(new Block(line));
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

    private static boolean BlocksLeftToFall(List<Block> blocks) {
        return blocks.stream().anyMatch(x -> !x.resting);
    }

    private static List<Block> FallingBlocks(List<Block> blocks) {
        return blocks.stream().filter(x -> !x.resting).toList();
    }
}