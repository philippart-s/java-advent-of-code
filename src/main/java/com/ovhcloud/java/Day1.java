package com.ovhcloud.java;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

  Matcher matcherFirst = null;
  Matcher matcherLast = null;


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
    while (scanner.hasNextLine()) {
      count += giveMagicNumber(scanner.nextLine());
    }

    scanner.close();

    System.out.println("Result Day1 part1: " + count);

  }

  private void puzzle2() {
    int count = 0;

    Scanner scanner = new Scanner(FileOperations.loadInputs(PuzzleInputs.INPUT1_2));

    String line;
    while (scanner.hasNextLine()) {
      line = scanner.nextLine();

      line = line.replace("one", "o1e")
        .replace("two", "t2o")
        .replace("three", "t3e")
        .replace("four", "f4r")
        .replace("five", "f5e")
        .replace("six", "s6x")
        .replace("seven", "s7n")
        .replace("eight", "e8t")
        .replace("nine", "n9e");

      count += giveMagicNumber(line);
    }

    scanner.close();

    System.out.println("Result Day1 part2: " + count);
  }

  private int giveMagicNumber(String line) {

    matcherFirst = Pattern.compile("^[^\\d]*(\\d)").matcher(line);
    matcherLast = Pattern.compile("(\\d)(?!.*\\d)").matcher(line);
    matcherFirst.find();
    matcherLast.find();
    return Integer.parseInt(matcherFirst.group(1) + matcherLast.group());
  }
}
