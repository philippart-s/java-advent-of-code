package com.ovhcloud.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Callable;
import com.ovhcloud.java.util.FileOperations;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "day4", mixinStandardHelpOptions = true)
public class Day4 implements Callable<Integer> {

  @Option(names = {"-part1"})
  private boolean puzzleOne;

  @Option(names = {"-part2"})
  private boolean puzzleTwo;

  @Override
  public Integer call() throws Exception {
    if (puzzleOne) {
      puzzle1("/input-day4-1.txt");
    }
    if (puzzleTwo) {
      puzzle2("/input-day4-2.txt");
    }

    return 0;
  }

  private String getCurrentLine(String line) {
    return line.split(":")[1];
  }

  private ArrayList<Integer> getWinnerNumbers(String numbers) {

    return null;
  }


  int puzzle1(String input) {
    int count = 0;
    Scanner scanner = new Scanner(FileOperations.loadInputs(input));

    String currentLine;
    while (scanner.hasNextLine()) {
      currentLine = scanner.nextLine();
      currentLine = getCurrentLine(currentLine);

      // Wining number as array
      int[] winningIntArray = Arrays.stream(currentLine.split("\\|")[0].split("\\D+"))
          .filter(number -> !number.isEmpty()).mapToInt(Integer::parseInt).toArray();

      // Elf number as array
      int[] elfIntArray = Arrays.stream(currentLine.split("\\|")[1].split("\\D+"))
          .filter(number -> !number.isEmpty()).mapToInt(Integer::parseInt).toArray();

      ArrayList<Integer> elfIntegerArraylist = new ArrayList<>();
      for (int i = 0; i < elfIntArray.length; i++) {
        elfIntegerArraylist.add(elfIntArray[i]);
      }

      ArrayList<Integer> winningIntegerArraylist = new ArrayList<>();
      for (int i = 0; i < winningIntArray.length; i++) {
        winningIntegerArraylist.add(winningIntArray[i]);
      }

      // Check if the elf number is in the winning numbers
      long numberOfMatch = elfIntegerArraylist.stream()
          // .forEach(element -> System.out.println("element: " + element));
          .filter(element -> winningIntegerArraylist.contains(element)).count();
      if (numberOfMatch > 0) {
        count += Math.pow(2, numberOfMatch - 1);
      }
    }
    scanner.close();

    System.out.println("Result: " + count);
    return count;
  }

  int puzzle2(String input) {
    int count = 0;

    /*
     * Scanner scanner = new Scanner(FileOperations.loadInputs(input));
     * 
     * while (scanner.hasNextLine()) { // Put code here } scanner.close();
     * 
     * System.out.println("Result: " + count);
     */
    return 0;
  }
}

