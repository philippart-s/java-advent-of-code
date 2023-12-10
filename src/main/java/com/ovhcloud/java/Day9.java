package com.ovhcloud.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import com.ovhcloud.java.util.FileOperations;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "day9", mixinStandardHelpOptions = true)
public class Day9 implements Callable<Integer> {

  @Option(names = {"-part1"})
  private boolean puzzleOne;

  @Option(names = {"-part2"})
  private boolean puzzleTwo;

  List<String[]> reports;

  List<String[]> extractData(String input) {
    reports = new ArrayList<>();

    Scanner scanner = new Scanner(FileOperations.loadInputs(input));

    String currentLine;
    while (scanner.hasNextLine()) {
      currentLine = scanner.nextLine();
      reports.add(currentLine.split(" "));
    }
    scanner.close();

    return reports;
  }

  @Override
  public Integer call() throws Exception {
    if (puzzleOne) {
      puzzle1("/input-day9-1.txt");
    }
    if (puzzleTwo) {
      puzzle2("/input-day9-2.txt");
    }

    return 0;
  }

  int puzzle1(String input) {
    int count = 0;

    extractData(input);

    boolean allZero = false;
    List<Integer> interlines = new ArrayList<>();
    List<Integer> lines = new ArrayList<>();
    List<List<Integer>> histories = new ArrayList<>();
    Integer sub;


    for (String[] strings : reports) {
      for (int i = 0; i < strings.length; i++) {
        lines.add(Integer.parseInt(strings[i]));
      }
      histories.add(lines);

      while (!allZero) {
        // System.out.println("lines: " + lines);

        for (int i = 1; i < lines.size(); i++) {
          sub = lines.get(i) - lines.get(i - 1);
          interlines.add(sub);
        }
        // System.out.println("interlines: " + interlines);
        histories.add(interlines);

        for (Integer integer : interlines) {
          if (integer == 0) {
            allZero = true;
          } else {
            allZero = false;
            break;
          }
        }
        lines = interlines;
        interlines = new ArrayList<>();
      }

      long adder = 0;
      for (int j = histories.size() - 1; j > 0; j--) {
        adder += histories.get(j - 1).get(histories.get(j).size());
      }
      // System.out.println("adder: " + adder);
      allZero = false;
      lines = new ArrayList<>();
      histories = new ArrayList<>();
      count += adder;
    }
    System.out.println("Result: " + count);
    return count;
  }

  int puzzle2(String input) {
    int count = 0;

    extractData(input);

    boolean allZero = false;
    List<Integer> interlines = new ArrayList<>();
    List<Integer> lines = new ArrayList<>();
    List<List<Integer>> histories = new ArrayList<>();
    Integer sub;


    for (String[] strings : reports) {
      for (int i = 0; i < strings.length; i++) {
        lines.add(Integer.parseInt(strings[i]));
      }
      histories.add(lines);

      while (!allZero) {
        // System.out.println("lines: " + lines);

        for (int i = 1; i < lines.size(); i++) {
          sub = lines.get(i) - lines.get(i - 1);
          interlines.add(sub);
        }
        // System.out.println("interlines: " + interlines);

        for (Integer integer : interlines) {
          if (integer == 0) {
            allZero = true;
          } else {
            allZero = false;
            break;
          }
        }
        if (allZero) {
          interlines.add(0, 0);
        }
        histories.add(interlines);

        lines = interlines;
        interlines = new ArrayList<>();
      }

      long adder = 0;
      // System.out.println("histories: " + histories);
      for (int j = histories.size() - 1; j > 0; j--) {
        // System.out.println("histories.get(j - 1).get(0): " +histories.get(j - 1).get(0));
        adder = histories.get(j - 1).get(0) - adder;
        // System.out.println("res: " +adder);
      }
      // System.out.println("adder: " + adder);
      allZero = false;
      lines = new ArrayList<>();
      histories = new ArrayList<>();
      count += adder;
    }
    System.out.println("Result: " + count);
    return count;
  }
}

