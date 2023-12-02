package com.ovhcloud.java;

import java.util.Scanner;
import java.util.concurrent.Callable;
import com.ovhcloud.java.util.FileOperations;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "day3", mixinStandardHelpOptions = true)
public class Day3 implements Callable<Integer> {

  @Option(names = {"-part1"})
  private boolean puzzleOne;

  @Option(names = {"-part2"})
  private boolean puzzleTwo;

  @Override
  public Integer call() throws Exception {
    if (puzzleOne) {
      puzzle1("/input-day3-1.txt");
    }
    if (puzzleTwo) {
      puzzle2("/input-day3-2.txt");
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
