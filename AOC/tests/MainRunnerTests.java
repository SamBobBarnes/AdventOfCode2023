import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainRunnerTests {
    @Test
    public void Day1Part1() {

        String actual = Day1.Part1.Run();

        Assertions.assertEquals("Day1 Part1 running!", actual);

    }
}
