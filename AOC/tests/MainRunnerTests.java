import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;


@RunWith(Theories.class)
public class MainRunnerTests {
    @DataPoints("example")
    public static boolean[] example() {
        return new boolean[]{true, false};
    }

    //region Day1
    @Theory
    public void Day1Part1() {

        int actual = Day1.Part1.Run(false);

        Assertions.assertEquals(53651, actual);
    }

    @Theory
    public void Day1Part2(@FromDataPoints("example") boolean example) {

        int actual = Day1.Part2.Run(example);

        Assertions.assertEquals(example ? 281 : 53894, actual);
    }

    //endregion
    //region Day2
    @Theory
    public void Day2Part1(@FromDataPoints("example") boolean example) {

        int actual = Day2.Part1.Run(example);

        Assertions.assertEquals(example ? 8 : 2207, actual);
    }

    @Theory
    public void Day2Part2(@FromDataPoints("example") boolean example) {

        int actual = Day2.Part2.Run(example);

        Assertions.assertEquals(example ? 2286 : 62241, actual);
    }

    //endregion
    //region Day3
    @Theory
    public void Day3Part1(@FromDataPoints("example") boolean example) {

        int actual = Day3.Part1.Run(example);

        Assertions.assertEquals(example ? 4361 : 544664, actual);
    }

    @Theory
    public void Day3Part2(@FromDataPoints("example") boolean example) {

        int actual = Day3.Part2.Run(example);

        Assertions.assertEquals(example ? 467835 : 84495585, actual);
    }
    //endregion
    //region Day4
    @Theory
    public void Day4Part1(@FromDataPoints("example") boolean example) {

        int actual = Day4.Part1.Run(example);

        Assertions.assertEquals(example ? 13:25004, actual);
    }
    @Theory
    public void Day4Part2(@FromDataPoints("example") boolean example) {

        int actual = Day4.Part2.Run(example);

        Assertions.assertEquals(example ? 30:14427616, actual);
    }
    //endregion
    //region Day5
    @Theory
    public void Day5Part1(@FromDataPoints("example") boolean example) {

        var actual = Day5.Part1.Run(example);

        Assertions.assertEquals(example ? 35:261668924, actual);
    }
    @Theory
    public void Day5Part2(@FromDataPoints("example") boolean example) {

        var actual = Day5.Part2.Run(example);

        Assertions.assertEquals(example ? 46:24261545, actual);
    }
    //endregion
    //region Day6
    @Theory
    public void Day6Part1(@FromDataPoints("example") boolean example) {

        var actual = Day6.Part1.Run(example);

        Assertions.assertEquals(example ? 288:281600, actual);
    }
    @Theory
    public void Day6Part2(@FromDataPoints("example") boolean example) {

        var actual = Day6.Part2.Run(example);

        Assertions.assertEquals(example ? 71503:33875953, actual);
    }
    //endregion
    //region Day7
    @Theory
    public void Day7Part1(@FromDataPoints("example") boolean example) {

        var actual = Day7.Part1.Run(example);

        Assertions.assertEquals(example ? 6440:246424613, actual);
    }
    @Theory
    public void Day7Part2(@FromDataPoints("example") boolean example) {

        var actual = Day7.Part2.Run(example);

        Assertions.assertEquals(example ? 5905:248256639, actual);
    }
    //endregion
    //region Day8
    @Theory
    public void Day8Part1(@FromDataPoints("example") boolean example) {

        var actual = Day8.Part1.Run(example);

        Assertions.assertEquals(example ? 6:17873, actual);
    }
    @Test
    public void Day8Part2() {

        var actual = Day8.Part2.Run(false);

        Assertions.assertEquals(15746133679061L, actual);
    }
    //endregion
    //region Day9
    @Theory
    public void Day9Part1(@FromDataPoints("example") boolean example) {

        var actual = Day9.Part1.Run(example);

        Assertions.assertEquals(example ? 114:1993300041, actual);
    }
    @Theory
    public void Day9Part2(@FromDataPoints("example") boolean example) {

        var actual = Day9.Part2.Run(example);

        Assertions.assertEquals(example ? 2:1038, actual);
    }
    //endregion
    //region Day10
    @Theory
    public void Day10Part1(@FromDataPoints("example") boolean example) {

        var actual = Day10.Part1.Run(example);

        Assertions.assertEquals(example ? 80:7093, actual);
    }
    @Theory
    public void Day10Part2(@FromDataPoints("example") boolean example) {

        var actual = Day10.Part2.Run(example);

        Assertions.assertEquals(example ? 10:0, actual);
    }
    //endregion
    //region Day11
    @Theory
    public void Day11Part1(@FromDataPoints("example") boolean example) {

        var actual = Day11.Part1.Run(example);

        Assertions.assertEquals(example ? 374:9233514, actual);
    }
    @Theory
    public void Day11Part2(@FromDataPoints("example") boolean example) {

        var actual = Day11.Part2.Run(example);

        Assertions.assertEquals(example ? 8410:363293506944L, actual);
    }
    //endregion
    //region Day12
    @Theory
    public void Day12Part1(@FromDataPoints("example") boolean example) {

        var actual = Day12.Part1.Run(example);

        Assertions.assertEquals(example ? 21:0, actual);
    }
    @Theory
    public void Day12Part2(@FromDataPoints("example") boolean example) {

        var actual = Day12.Part2.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    //endregion
    //region Day13
    @Theory
    public void Day13Part1(@FromDataPoints("example") boolean example) {

        var actual = Day13.Part1.Run(example);

        Assertions.assertEquals(example ? 405:29130, actual);
    }
    @Theory
    public void Day13Part2(@FromDataPoints("example") boolean example) {

        var actual = Day13.Part2.Run(example);

        Assertions.assertEquals(example ? 400:33438, actual);
    }
    //endregion
    //region Day14
    @Theory
    public void Day14Part1(@FromDataPoints("example") boolean example) {

        var actual = Day14.Part1.Run(example);

        Assertions.assertEquals(example ? 136:110274, actual);
    }
    @Theory
    public void Day14Part2(@FromDataPoints("example") boolean example) {

        var actual = Day14.Part2.Run(example);

        Assertions.assertEquals(example ? 64:90982, actual);
    }
    //endregion
    //region Day15
    @Theory
    public void Day15Part1(@FromDataPoints("example") boolean example) {

        var actual = Day15.Part1.Run(example);

        Assertions.assertEquals(example ? 1320:521341, actual);
    }
    @Theory
    public void Day15Part2(@FromDataPoints("example") boolean example) {

        var actual = Day15.Part2.Run(example);

        Assertions.assertEquals(example ? 145:252782, actual);
    }
    //endregion
    //region Day16
    @Theory
    public void Day16Part1(@FromDataPoints("example") boolean example) {

        var actual = Day16.Part1.Run(example);

        Assertions.assertEquals(example ? 46:7415, actual);
    }
    @Theory
    public void Day16Part2(@FromDataPoints("example") boolean example) {

        var actual = Day16.Part2.Run(example);

        Assertions.assertEquals(example ? 51:0, actual);
    }
    //endregion
    //region Day17
    @Theory
    public void Day17Part1(@FromDataPoints("example") boolean example) {

        var actual = Day17.Part1.Run(example);

        Assertions.assertEquals(example ? 102:0, actual);
    }
    @Theory
    public void Day17Part2(@FromDataPoints("example") boolean example) {

        var actual = Day17.Part2.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    //endregion
    //region Day18
    @Theory
    public void Day18Part1(@FromDataPoints("example") boolean example) {

        var actual = Day18.Part1.Run(example);

        Assertions.assertEquals(example ? 62:34329, actual);
    }
    @Theory
    public void Day18Part2(@FromDataPoints("example") boolean example) {

        var actual = Day18.Part2.Run(example);

        Assertions.assertEquals(example ? 952408144115L:42617947302920L, actual);
    }
    //endregion
    //region Day19
    @Theory
    public void Day19Part1(@FromDataPoints("example") boolean example) {

        var actual = Day19.Part1.Run(example);

        Assertions.assertEquals(example ? 19114:492702, actual);
    }
    @Theory
    public void Day19Part2(@FromDataPoints("example") boolean example) {

        var actual = Day19.Part2.Run(example);

        Assertions.assertEquals(example ? 167409079868000L:138616621185978L, actual);
    }
    //endregion
    //region Day20
    @Theory
    public void Day20Part1(@FromDataPoints("example") boolean example) {

        var actual = Day20.Part1.Run(example);

        Assertions.assertEquals(example ? 11687500:743090292, actual);
    }
    @Theory
    public void Day20Part2(@FromDataPoints("example") boolean example) {

        var actual = Day20.Part2.Run(false);

        Assertions.assertEquals(241528184647003L, actual);
    }
    //endregion
    //region Day21
    @Theory
    public void Day21Part1(@FromDataPoints("example") boolean example) {

        var actual = Day21.Part1.Run(example);

        Assertions.assertEquals(example ? 16:3814, actual);
    }
    @Theory
    public void Day21Part2(@FromDataPoints("example") boolean example) {

        var actual = Day21.Part2.Run(example);

        Assertions.assertEquals(example ? 16733044:0, actual);
    }
    //endregion
    //region Day22
    @Theory
    public void Day22Part1(@FromDataPoints("example") boolean example) {

        var actual = Day22.Part1.Run(example);

        Assertions.assertEquals(example ? 5:398, actual);
    }
    @Theory
    public void Day22Part2(@FromDataPoints("example") boolean example) {

        var actual = Day22.Part2.Run(example);

        Assertions.assertEquals(example ? 7:70727, actual);
    }
    //endregion
}