package Day22;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Part2 extends AdventBase {
    public static int Run(boolean example) {
        Start(22, 2, example);

        List<String> input = LoadInput(22, example);

        List<Block> blocks = new ArrayList<>();


        //region Init
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

        //endregion

        var result = 0;
        for(var block: blocks) {
            if(!block.CanDisintegrate()) {
                var count = CountBlocksAbove(block);
                result += count;
            }
        }

        return result;
    }

    private static boolean BlocksLeftToFall(List<Block> blocks) {
        return blocks.stream().anyMatch(x -> !x.resting);
    }

    private static List<Block> DuplicateList(List<Block> list) {
        var blocks = new ArrayList<Block>();
        for(var block: list) {
            blocks.add(new Block(block));
        }

        while(BlocksLeftToFall(blocks)) {
            var falling = FallingBlocks(blocks);
            var current = falling.getFirst();
            if(current.CanFall(blocks)) {
                current.Fall();
            }
        }

        return blocks;
    }

    private static int CountBlocksAbove(Block block) {
        var blocksCounted = new ArrayList<Block>();
        var queue = new PriorityQueue<Block>();
        queue.add(block);

        while(!queue.isEmpty()) {
            var current = queue.poll();
            if(!blocksCounted.contains(current)) {
                blocksCounted.add(current);
                if(RestsOnAreInStack(current, blocksCounted)) {
                    queue.addAll(current.supporting);
                } else {
                    blocksCounted.remove(current);
                }
            }
        }

        return blocksCounted.size()-1;
    }

    private static boolean RestsOnAreInStack(Block current, ArrayList<Block> blocksCounted)
    {
        if(current.restsOn.size() > 1 && blocksCounted.size() > 1) {
            for (var supporter : current.restsOn) {
                if (!blocksCounted.contains(supporter)) return false;
            }
        }
        return true;
    }

    private static List<Block> FallingBlocks(List<Block> blocks) {
        return blocks.stream().filter(x -> !x.resting).toList();
    }
}