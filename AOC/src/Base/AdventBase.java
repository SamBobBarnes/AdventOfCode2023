package Base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdventBase {
    static String projectPath = System.getProperty("user.dir");
    protected static void Start(int day, int part, boolean example)
    {
        System.out.println(MessageFormat.format("Day {0}, Part {1} {2}",day,part,example ? "example" : ""));
    }

    protected static List<String> LoadInput(int day, boolean example)
    {

        String examplePath = MessageFormat.format("{0}/input/Day{1}/ExampleInput.txt",projectPath,day);
        String path = MessageFormat.format("{0}/input/Day{1}/Input.txt",projectPath,day);

        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(example ? examplePath : path))) {
            result = lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    protected static List<char[]> LoadInputChars(int day) {
        return LoadInputChars(day, false);
    }

    protected static List<char[]> LoadInputChars(int day, boolean example)
    {
        String examplePath = MessageFormat.format("{0}/input/Day{1}/ExampleInput.txt",projectPath,day);
        String path = MessageFormat.format("{0}/input/Day{1}/Input.txt",projectPath,day);

        List<String> stringResult;
        try (Stream<String> lines = Files.lines(Paths.get(example ? examplePath : path))) {
            stringResult = lines.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<char[]> result = new ArrayList<>();

        for(var line: stringResult) {
            result.add(line.toCharArray());
        }

        return result;
    }

    public static void WriteOutput(int day, String output) {
        Path path = Paths.get(MessageFormat.format("{0}/output/Day{1}Output.txt",projectPath,day));
        try {
            Files.writeString(path,output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
