import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainRunnerTests {
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
    @Test
    public void Day3Part1() {

        int actual = Day3.Part1.Run();

        Assertions.assertEquals(0, actual);
    }
    @Test
    public void Day3Part2() {

        int actual = Day3.Part2.Run();

        Assertions.assertEquals(0, actual);
    }
    //endregion
}
