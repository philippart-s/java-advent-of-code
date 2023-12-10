package com.ovhcloud.java;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "newDay")
public class NewDay implements Callable<Integer> {

  private static String UNIT_TEST = """
    package com.ovhcloud.java;

    import org.junit.jupiter.api.Assertions;
    import org.junit.jupiter.api.Test;
    import io.quarkus.test.junit.QuarkusTest;
    
    @QuarkusTest
    class DayNTest {
    
      @Test
      void testPuzzleOne() {
        int result = new DayN().puzzle1("/input-dayN-test.txt");
    
        Assertions.assertEquals(0, result);
      }
    
      @Test
      void testPuzzleTwo() {
        int result = new DayN().puzzle2("/input-dayN-test.txt");
    
        Assertions.assertEquals(0, result);
      }
    
    }
            """;

  private static String DAY_CLASS = """
    package com.ovhcloud.java;

    import java.util.Scanner;
    import java.util.concurrent.Callable;
    import com.ovhcloud.java.util.FileOperations;
    import picocli.CommandLine.Command;
    import picocli.CommandLine.Option;
    
    @Command(name = "dayN", mixinStandardHelpOptions = true)
    public class DayN implements Callable<Integer> {
    
      @Option(names = {"-part1"})
      private boolean puzzleOne;
    
      @Option(names = {"-part2"})
      private boolean puzzleTwo;
    
      @Override
      public Integer call() throws Exception {
        if (puzzleOne) {
          puzzle1("/input-dayN-1.txt");
        }
        if (puzzleTwo) {
          puzzle2("/input-dayN-2.txt");
        }
    
        return 0;
      }
    
       int puzzle1(String input) {
        int count = 0;
        Scanner scanner = new Scanner(FileOperations.loadInputs(input));
    
        while (scanner.hasNextLine()) {
          // Put code here
        }
        scanner.close();
    
        System.out.println("Result: " + count);
        return 0;
      }
    
       int puzzle2(String input) {
        int count = 0;
        
        Scanner scanner = new Scanner(FileOperations.loadInputs(input));
    
        while (scanner.hasNextLine()) {
          // Put code here
        }
        scanner.close();
    
        System.out.println("Result: " + count);
        return 0;
      }
    }
    
        """;

    private final static String SOURCE_PATH = "/Users/stef/Dev/java-advent-of-code";

  @Parameters()
  private String day;

  @Override
  public Integer call() throws Exception {
    String unitTestFile =
        UNIT_TEST.replace("dayN", day.toLowerCase()).replace("DayN", day).replace("INPUTN", "INPUT" + day.substring(3));
    Files.write(Paths.get(
        SOURCE_PATH + "/src/test/java/com/ovhcloud/java/" + day + "Test.java"),
        unitTestFile.getBytes());

    String dayFile = DAY_CLASS.replace("dayN", day.toLowerCase()).replace("DayN", day)
        .replace("INPUTN", "INPUT" + day.substring(3));
    Files.write(
        Paths.get(
            SOURCE_PATH + "/src/main/java/com/ovhcloud/java/" + day + ".java"),
        dayFile.getBytes());

    Files.createFile(Paths.get( SOURCE_PATH + "/src/main/resources/input-" + day.toLowerCase() + "-1.txt"));
    Files.createFile(Paths.get( SOURCE_PATH + "/src/main/resources/input-" + day.toLowerCase() + "-2.txt"));
    Files.createFile(Paths.get( SOURCE_PATH + "/src/main/resources/input-" + day.toLowerCase() + "-test.txt"));

    return 0;
  }

}
