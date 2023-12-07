package Day7;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Part2 extends AdventBase {
    public static int Run(boolean example) {
        Start(7, 2, example);

        List<String> input = LoadInput(7, example);

        var hands = new ArrayList<Hand2>();
        for(var line: input) {
            hands.add(new Hand2(line));
        }

        hands.sort(new HandComparator2());

        return DetermineWinnings(hands);
    }

    private static int DetermineWinnings(List<Hand2> hands) {
        var total = 0;
        for(int i = 0; i < hands.size(); i++) {
            total += hands.get(i).bid * (i+1);
        }
        return total;
    }
}

class HandComparator2 implements java.util.Comparator<Hand2> {
    @Override
    public int compare(Hand2 a, Hand2 b) {
        if(a.handType != b.handType) {
            return a.handType.value > b.handType.value ? 1:-1;
        }

        for(int i = 0; i < 5; i++) {
            if(a.cards.get(i) == b.cards.get(i)) {
                continue;
            }
            return a.cards.get(i).value > b.cards.get(i).value ? 1:-1;
        }
        return 0;
    }
}

class Hand2 {
    public Hand2(String line) {
        this.bid = parseInt(line.split(" ")[1]);
        var hand = line.split(" ")[0].toCharArray();
        this.cards = new ArrayList<>();
        for(var card: hand) {
            cards.add(MapToCard(card));
        }

        this.handType = TranslateHandType();
    }

    public HandType handType;
    public List<Card2> cards;
    public int bid;

    private Card2 MapToCard(char letter) {
        return switch (letter) {
            case 'A' -> Card2.A;
            case 'K' -> Card2.K;
            case 'Q' -> Card2.Q;
            case 'J' -> Card2.J;
            case 'T' -> Card2.T;
            case '9' -> Card2._9;
            case '8' -> Card2._8;
            case '7' -> Card2._7;
            case '6' -> Card2._6;
            case '5' -> Card2._5;
            case '4' -> Card2._4;
            case '3' -> Card2._3;
            case '2' -> Card2._2;
            default -> null;
        };
    }

    private HandType TranslateHandType() {
        int[] count = new int[12];
        int jokers = 0;

        for(var card: cards) {
            switch(card) {
                case Card2.A: count[0]++; break;
                case Card2.K: count[1]++; break;
                case Card2.Q: count[2]++; break;
                case Card2.J: jokers++; break;
                case Card2.T: count[3]++; break;
                case Card2._9: count[4]++; break;
                case Card2._8: count[5]++; break;
                case Card2._7: count[6]++; break;
                case Card2._6: count[7]++; break;
                case Card2._5: count[8]++; break;
                case Card2._4: count[9]++; break;
                case Card2._3: count[10]++; break;
                case Card2._2: count[11]++; break;
            }
        }

        var setOf5 = Arrays.stream(count).filter(x -> x == 5).count();
        var setOf4 = Arrays.stream(count).filter(x -> x == 4).count();
        var setOf3 = Arrays.stream(count).filter(x -> x == 3).count();
        var setOf2 = Arrays.stream(count).filter(x -> x == 2).count();
        var setOf1 = Arrays.stream(count).filter(x -> x == 1).count();

        if(
            setOf5 == 1
            || setOf4 == 1 && jokers == 1
            || setOf3 == 1 && jokers == 2
            || setOf2 == 1 && jokers == 3
            || setOf1 == 1 && jokers == 4
            || jokers == 5
        ) return HandType.FiveOfAKind;

        if(
            setOf4 == 1
            || setOf3 == 1 && jokers == 1
            || setOf2 == 1 && jokers == 2
            || setOf1 == 2 && jokers == 3
        ) return HandType.FourOfAKind;

        if(
            setOf3 == 1 && setOf2 == 1
            || setOf2 == 2 && jokers == 1
        ) return HandType.FullHouse;

        if(
            setOf3 == 1
            || setOf2 == 1 && jokers == 1
            || jokers == 2
        ) return HandType.ThreeOfAKind;

        if(
            setOf2 == 2
        ) return HandType.TwoPair;

        if(
            setOf2 == 1
            || jokers == 1
        ) return HandType.OnePair;

        return HandType.HighCard;
    }
}

enum Card2 {
    A(12),
    K(11),
    Q(10),
    T(9),
    _9(8),
    _8(7),
    _7(6),
    _6(5),
    _5(4),
    _4(3),
    _3(2),
    _2(1),
    J(0);

    public final int value;
    Card2(int value) {
        this.value = value;
    }
}