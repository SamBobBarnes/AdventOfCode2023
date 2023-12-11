package Day10;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(10, 1, example);

        List<char[]> input = LoadInputChars(10, example);

        List<Pipe> pipes = new ArrayList<>();

        var x = 0;
        var y = 0;
        Pipe start = null;

        for(var line: input) {
            for(var character: line) {
                if(character != '.' && character != 'S') pipes.add(new Pipe(character,x,y));
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

        var result = FindMiddle(start,true, start, false);

        return result;
    }

    private static int FindMiddle(Pipe pipe1, boolean useNext1, Pipe pipe2, boolean useNext2) {
        if(pipe1.equals(pipe2) && pipe1.angle != 'S') return 0;

        var nextPipe1 = useNext1 ? pipe1.next : pipe1.previous;
        var nextPipe2 = useNext2 ? pipe2.next : pipe2 .previous;

        useNext1 = pipe1.equals(nextPipe1.previous);
        useNext2 = pipe2.equals(nextPipe2.previous);

        return FindMiddle(nextPipe1,useNext1,nextPipe2,useNext2) + 1;
    }
}