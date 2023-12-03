package Day3;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(3,1);

        List<String> input = LoadInput(3,example);

        String symbolRegex = "[^0-9.]+";
        String numberRegex = "[0-9]+";

        List<Number> numbers = new ArrayList<>();
        List<Symbol> symbols = new ArrayList<>();

        int yIndex = 0;
        for(String line: input) {
            symbols.addAll(FindSymbols(line, yIndex));
            numbers.addAll(FindNumbers(line,yIndex));

            yIndex++;
        }

        int result = 0;
        for(Number number: numbers) {
            if(IsPartNumber(number,symbols)) result += number.value;
        }


        return result;
    }

    private static boolean IsPartNumber(Number number, List<Symbol> symbols) {
        int rangeX1 = number.x-1;
        int rangeX2 = number.x+ number.length;
        int rangeY1 = number.y-1;
        int rangeY2 = number.y+1;

        return symbols.stream().anyMatch(s -> s.x >= rangeX1 && s.x <= rangeX2 && s.y >= rangeY1 && s.y <= rangeY2);
    }

    private static List<Symbol> FindSymbols(String line, int yIndex) {
        List<Symbol> symbols = new ArrayList<>();

        char[] chars = line.toCharArray();

        int xIndex = 0;
        for(char character: chars) {
            if((character < 48 || character > 57) && (character != 46)) {
                symbols.add(new Symbol(xIndex,yIndex));
            }
            xIndex++;
        }

        return symbols;
    }

    private static List<Number> FindNumbers(String line, int yIndex) {
        List<Number> numbers = new ArrayList<>();

        char[] chars = line.toCharArray();

        StringBuilder word = new StringBuilder();

        int xIndex = 0;
        for(char character: chars) {
            if(character >= 48 && character <= 57) {
                word.append(character);
            }
            else if(!word.isEmpty()) {
                int length = word.length();
                numbers.add(new Number(xIndex-length,yIndex,parseInt(word.toString()),length));
                word = new StringBuilder();
            }
            xIndex++;
        }

        if(!word.isEmpty()) {
            int length = word.length();
            numbers.add(new Number(xIndex-length,yIndex,parseInt(word.toString()),length));
        }

        return numbers;
    }
}

class Number extends Symbol {
    public Number(int x, int y, int value, int length) {
        super(x,y);
        this.value = value;
        this.length = length;
    }
    public int value;
    public int length;
}

class Symbol {
    public Symbol(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int x;
    public int y;
}
