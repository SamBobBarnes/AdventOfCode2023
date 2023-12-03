import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MainRunnerTests {
    @DataPoints("example")
    public static boolean[] example() {
        return new boolean[]{true,false};
    }

    //region Day1
    @Test
    public void Day1Part1() {

        int actual = Day1.Part1.Run();

        Assertions.assertEquals(53651, actual);
    }
    @Test
    public void Day1Part2() {

        int actual = Day1.Part2.Run();

        Assertions.assertEquals(53894, actual);
    }
    //endregion
    //region Day2
    @Test
    public void Day2Part1() {

        int actual = Day2.Part1.Run();

        Assertions.assertEquals(2207, actual);
    }
    @Test
    public void Day2Part2() {

        int actual = Day2.Part2.Run();

        Assertions.assertEquals(62241, actual);
    }
    //endregion
    //region Day3
    @Theory
    public void Day3Part1(@FromDataPoints("example") boolean example) {

        int actual = Day3.Part1.Run(example);

        Assertions.assertEquals(example ? 4361:544664, actual);
    }
    @Theory
    public void Day3Part2(@FromDataPoints("example") boolean example) {

        int actual = Day3.Part2.Run();

        Assertions.assertEquals(0, actual);
    }
    //endregion
}
