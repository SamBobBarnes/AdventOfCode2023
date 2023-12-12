package Day10;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
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
//        for(int j = 0; j < input.size(); j++) {
//            AtomicInteger crossedLines = new AtomicInteger();
//            for(int i = 0; i < input.getFirst().length; i++) {
//                int finalJ = j;
//                int finalI = i;
//                var pipe = pipes.stream().filter(p -> p.x == finalI && p.y == finalJ).findFirst();
//
//                pipe.ifPresentOrElse(
//                    p -> {
//                        if(p.loop && (p.angle == '|' || p.angle == 'J' || p.angle == 'L' || p.angle == '7' || p.angle == 'F')) {
//                            crossedLines.getAndIncrement();
//                        }
//                        else if(!p.loop) {
//                            var dict = FindPipesToTheLeft(finalI,finalJ,pipes);
//
//                            if(dict.get("Vertical") % 2 == 1 && dict.get("Horizontal") == 0) {
//                                result.getAndIncrement();
//                            }
//
//
//                        }
//                    },
//                    () -> {
//                        if(crossedLines.get() % 2 == 1) result.getAndIncrement();
//                    }
//                );
//            }
//        }

        var try1 = ShoeLace(start, true);
        var try2 = ShoeLace(start, false);

        return try1 > 0 ? try1 : try2;
    }

    private static int ShoeLace(Pipe start, boolean next) {
        var verts = new ArrayList<Vert>();

        verts.add(new Vert(start.x,start.y));

        var last = start;
        var current = start.next;
        if(!next) current = start.previous;

        while(!current.equals(start)) {
            verts.add(new Vert(current.x,current.y));
            if(current.next.equals(last)) {
                last = current;
                current = current.previous;
            }
            else {
                last = current;
                current = current.next;
            }
        }

        var loopArea = 0;

        for(int i = 0; i < verts.size()-1; i++) {
            loopArea += verts.get(i).X * verts.get(i+1).Y;
            loopArea -= verts.get(i).Y * verts.get(i+1).X;
        }

        loopArea /= 2;

        var result = Math.abs(loopArea) - (verts.size()/2) + 1;

        return result;
    }

    private static Dictionary<String, Integer> FindPipesToTheLeft(int x, int y, List<Pipe> pipes) {
        var dict =  new Hashtable<String, Integer>();
        dict.put("Vertical", 0);
        dict.put("Horizontal", 0);
        var precedingPipes = new ArrayList<Pipe>();
        for(int i = 0; i < x; i++) {
            int finalI = i;
            precedingPipes.add(pipes.stream().filter(p -> p.x == finalI && p.y == y).findFirst().get());
        }

        for(var pipe: precedingPipes) {
            switch(pipe.angle) {
                case '|', 'J', 'F', 'L', '7':
                    dict.put("Vertical",dict.get("Vertical")+1);
                    break;
                case '-':
                    dict.put("Horizontal",dict.get("Horizontal")+1);
                    break;
            }
        }

        return dict;
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

class Vert {
    public Vert(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
    public int X;
    public int Y;
}