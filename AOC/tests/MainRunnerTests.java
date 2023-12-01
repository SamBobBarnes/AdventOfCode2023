import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MainRunnerTests {
    @Test
    public void Day1Part1() {

        int actual = Day1.Part1.Run();

        Assertions.assertEquals(53651, actual);
    }
    @Test
    public void Day1Part2() {

        int actual = Day1.Part2.Run();

        Assertions.assertEquals(281, actual);
    }
}
