package Day10;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Part2 extends AdventBase {
    public static int Run(boolean example) {
        Start(10, 2, example);

        List<char[]> input = LoadInputChars(10, example);

        List<Pipe> pipes = new ArrayList<>();

        var x = 0;
        var y = 0;
        Pipe start = null;

        for(var line: input) {
            for(var character: line) {
                if(character != 'S') pipes.add(new Pipe(character,x,y));
                if(character == 'S') {
                    pipes.add(new Pipe(character, x, y));
                    start = pipes.getLast();
                }
                x++;
            }
            y++;
            x=0;
        }

        for(var pipe: pipes) {
            pipe.MapToPipe(pipes);
        }

        assert start != null;
        start.FindStartNeighbors(pipes);

        FindMiddle(start,true, start, false);

        AtomicInteger result = new AtomicInteger();
        for(int j = 0; j < input.size(); j++) {
            AtomicInteger crossedLines = new AtomicInteger();
            for(int i = 0; i < input.getFirst().length; i++) {
                int finalJ = j;
                int finalI = i;
                var pipe = pipes.stream().filter(p -> p.x == finalI && p.y == finalJ).findFirst();

                // Check for vertical pipe then start counting. stop at second vertical pipe

                pipe.ifPresentOrElse(
                    p -> {
                        if(p.loop && p.angle == '|') {
                            crossedLines.getAndIncrement();
                        }
                        else if(p.loop && crossedLines.get() % 2 == 1) {
                            crossedLines.getAndDecrement();
                        }
                        else if(crossedLines.get() % 2 == 1 && !p.loop) {
                            result.getAndIncrement();
                        }
                    },
                    () -> {
                        if(crossedLines.get() % 2 == 1) result.getAndIncrement();
                    }
                );
            }
        }

        return result.get();
    }

    private static int FindMiddle(Pipe pipe1, boolean useNext1, Pipe pipe2, boolean useNext2) {
        pipe1.loop = true;
        pipe2.loop = true;
        if(pipe1.equals(pipe2) && pipe1.angle != 'S') return 0;

        var nextPipe1 = useNext1 ? pipe1.next : pipe1.previous;
        var nextPipe2 = useNext2 ? pipe2.next : pipe2 .previous;

        useNext1 = pipe1.equals(nextPipe1.previous);
        useNext2 = pipe2.equals(nextPipe2.previous);

        return FindMiddle(nextPipe1,useNext1,nextPipe2,useNext2) + 1;
    }
}