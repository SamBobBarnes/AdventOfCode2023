package Day3;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Part2 extends AdventBase {
    public static int Run(boolean example) {
        Start(3,2, example);

        List<String> input = LoadInput(3,example);

        List<Number> numbers = new ArrayList<>();
        List<Symbol> symbols = new ArrayList<>();

        int yIndex = 0;
        for(String line: input) {
            symbols.addAll(FindSymbols(line, yIndex));
            numbers.addAll(FindNumbers(line,yIndex));

            yIndex++;
        }

        int result = 0;
        for(Symbol gear: symbols) {
            result += GearRatio(gear, numbers);
        }

        return result;
    }

    private static List<Symbol> FindSymbols(String line, int yIndex) {
        List<Symbol> symbols = new ArrayList<>();

        char[] chars = line.toCharArray();

        int xIndex = 0;
        for(char character: chars) {
            if(character == '*') {
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

    private static int GearRatio(Symbol symbol, List<Number> numbers) {
        List<Number> limitedNumbers = numbers.stream().filter(n -> n.y >= symbol.y-1 && n.y <= symbol.y+1).toList();

        List<Number> foundNumbers = new ArrayList<>();
        List<Symbol> symbols = new ArrayList<>();
        symbols.add(symbol);

        for(Number number: limitedNumbers) {
            if(number.IsTouchingSymbol(symbols)) foundNumbers.add(number);
        }

        if((long) foundNumbers.size() == 2) return foundNumbers.get(0).value * foundNumbers.get(1).value;

        return 0;
    }
}

