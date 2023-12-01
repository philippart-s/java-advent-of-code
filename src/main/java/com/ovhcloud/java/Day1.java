package com.ovhcloud.java;

import java.util.Scanner;
import java.util.concurrent.Callable;
import com.ovhcloud.java.util.FileOperations;
import com.ovhcloud.java.util.PuzzleInputs;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "day1", mixinStandardHelpOptions = true)
public class Day1 implements Callable<Integer> {

  @Option(names = {"-part1"})
  private boolean puzzleOne;

  @Option(names = {"-part2"})
  private boolean puzzleTwo;

  @Override
  public Integer call() throws Exception {
    if (puzzleOne) {
      puzzle1();
    }
    if (puzzleTwo) {
      puzzle2();
    }

    return 0;
  }

  private void puzzle1() {
    int count = 0;

    Scanner scanner = new Scanner(FileOperations.loadInputs(PuzzleInputs.INPUT1_1));

    String line;
    char first;
    char second;
    while (scanner.hasNextLine()) {
      line = scanner.nextLine();
      int j = 0;
      while (j < line.length() && !Character.isDigit(line.charAt(j))) {
        j++;
      }
      first = line.charAt(j);
      int k = line.length() - 1;
      while (!Character.isDigit(line.charAt(k))) {
        k--;
      }
      second = line.charAt(k);

      count += Integer.parseInt("" + first + second);
    }

    scanner.close();

    System.out.println("Result Day1 part1: " + count);

  }

  private void puzzle2() {
    int count = 0;

    Scanner scanner = new Scanner(FileOperations.loadInputs(PuzzleInputs.INPUT1_2));

    String line;
    char first;
    char second;
    while (scanner.hasNextLine()) {
      line = scanner.nextLine();
      System.out.println("line: " + line);

      line = line.replace("one", "o1e");
      line = line.replace("two", "t2o");
      line = line.replace("three", "t3e");
      line = line.replace("four", "f4r");
      line = line.replace("five", "f5e");
      line = line.replace("six", "s6x");
      line = line.replace("seven", "s7n");
      line = line.replace("eight", "e8t");
      line = line.replace("nine", "n9e");

      System.out.println("line: " + line);

      int j = 0;
      while (j < line.length() && !Character.isDigit(line.charAt(j))) {
        j++;
      }
      first = line.charAt(j);
      int k = line.length() - 1;
      while (!Character.isDigit(line.charAt(k))) {
        k--;
      }
      second = line.charAt(k);

      count += Integer.parseInt("" + first + second);
    }

    scanner.close();

    System.out.println("Result Day1 part2: " + count);
  }
}
