package Day7;

import Base.AdventBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Part1 extends AdventBase {
    public static int Run(boolean example) {
        Start(7, 1, example);

        List<String> input = LoadInput(7, example);

        var hands = new ArrayList<Hand>();
        for(var line: input) {
            hands.add(new Hand(line));
        }

        hands.sort(new HandComparator());

        return DetermineWinnings(hands);
    }

    private static int DetermineWinnings(List<Hand> hands) {
        var total = 0;
        for(int i = 0; i < hands.size(); i++) {
            total += hands.get(i).bid * (i+1);
        }
        return total;
    }
}

class HandComparator implements java.util.Comparator<Hand> {
    @Override
    public int compare(Hand a, Hand b) {
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

class Hand {
    public Hand(String line) {
        this.bid = parseInt(line.split(" ")[1]);
        var hand = line.split(" ")[0].toCharArray();
        this.cards = new ArrayList<>();
        for(var card: hand) {
            cards.add(MapToCard(card));
        }

        this.handType = TranslateHandType();
    }

    public HandType handType;
    public List<Card> cards;
    public int bid;

    private Card MapToCard(char letter) {
        return switch (letter) {
            case 'A' -> Card.A;
            case 'K' -> Card.K;
            case 'Q' -> Card.Q;
            case 'J' -> Card.J;
            case 'T' -> Card.T;
            case '9' -> Card._9;
            case '8' -> Card._8;
            case '7' -> Card._7;
            case '6' -> Card._6;
            case '5' -> Card._5;
            case '4' -> Card._4;
            case '3' -> Card._3;
            case '2' -> Card._2;
            default -> null;
        };
    }

    private HandType TranslateHandType() {
        int[] count = new int[13];

        for(var card: cards) {
            switch(card) {
                case Card.A: count[0]++; break;
                case Card.K: count[1]++; break;
                case Card.Q: count[2]++; break;
                case Card.J: count[3]++; break;
                case Card.T: count[4]++; break;
                case Card._9: count[5]++; break;
                case Card._8: count[6]++; break;
                case Card._7: count[7]++; break;
                case Card._6: count[8]++; break;
                case Card._5: count[9]++; break;
                case Card._4: count[10]++; break;
                case Card._3: count[11]++; break;
                case Card._2: count[12]++; break;
            }
        }

        if(Arrays.stream(count).filter(x -> x == 5).count() == 1) return HandType.FiveOfAKind;
        if(Arrays.stream(count).filter(x -> x == 4).count() == 1) return HandType.FourOfAKind;
        if(Arrays.stream(count).filter(x -> x == 3).count() == 1
                && Arrays.stream(count).filter(x -> x == 2).count() == 1) return HandType.FullHouse;
        if(Arrays.stream(count).filter(x -> x == 3).count() == 1) return HandType.ThreeOfAKind;
        if(Arrays.stream(count).filter(x -> x == 2).count() == 2) return HandType.TwoPair;
        if(Arrays.stream(count).filter(x -> x == 2).count() == 1) return HandType.OnePair;
        return HandType.HighCard;
    }
}

enum Card {
    A(12),
    K(11),
    Q(10),
    J(9),
    T(8),
    _9(7),
    _8(6),
    _7(5),
    _6(4),
    _5(3),
    _4(2),
    _3(1),
    _2(0);

    public final int value;
    Card(int value) {
        this.value = value;
    }
}

enum HandType {
    FiveOfAKind(6),
    FourOfAKind(5),
    FullHouse(4),
    ThreeOfAKind(3),
    TwoPair(2),
    OnePair(1),
    HighCard(0);

    public final int value;

    HandType(int value) {
        this.value = value;
    }
}