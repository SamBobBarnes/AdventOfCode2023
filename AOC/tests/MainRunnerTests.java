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

        Assertions.assertEquals(example ? 0:0, actual);
    }
    //endregion
}