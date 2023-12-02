package com.ovhcloud.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import com.ovhcloud.java.util.FileOperations;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "day2", mixinStandardHelpOptions = true)
public class Day2 implements Callable<Integer> {

  @Option(names = {"-part1"})
  private boolean puzzleOne;

  @Option(names = {"-part2"})
  private boolean puzzleTwo;

  @Override
  public Integer call() throws Exception {
    if (puzzleOne) {
      puzzle1("/input-day2-1.txt");
    }
    if (puzzleTwo) {
      puzzle2("/input-day2-2.txt");
    }

    return 0;
  }

  int puzzle1(String input) {
    int count = 0;
    int idx = 1;

    Scanner scanner = new Scanner(FileOperations.loadInputs(input));
    while (scanner.hasNextLine()) {
      if (giveNumberOfPlayableGames(scanner.nextLine())) {
        count += idx;
      }
      idx++;
    }
    scanner.close();
    
    System.out.println("Result: " + count);
    return count;
  }

  int puzzle2(String input) {
    int count = 0;
    HashMap<String, Integer> cubesMap = null;

    Scanner scanner = new Scanner(FileOperations.loadInputs(input));

    while (scanner.hasNextLine()) {
      cubesMap = getMinimalSet(scanner.nextLine());
      count += cubesMap.get("red") * cubesMap.get("blue") * cubesMap.get("green");
    }
    System.out.println("Result: " + count);

    scanner.close();
    return count;
  }

  private boolean giveNumberOfPlayableGames(String lineInput) {
    HashMap<String, Integer> cubesMap = new HashMap<>(Map.of("blue", 0, "red", 0, "green", 0));


    // Remove Game xx: -> 9 "red, 5 blue, 6 green; 6 red, 13 blue; 2 blue, 7 green, 5 red"
    String withoutGame = lineInput.substring(lineInput.indexOf(':') + 2);

    // Get string between ; -> ["9 red, 5 blue, 6 green", "6 red, 13 blue", "2 blue, 7 green, 5
    // red"]
    String[] splitedSet = withoutGame.split(";");
    for (String set : splitedSet) {
      // Get string between , -> ["9 red", "5 blue", "6 green"]
      String[] splitedCube = set.split(",");
      for (String cubes : splitedCube) {
        String[] colorAndDigit = cubes.strip().split(" ");
        cubesMap.put(colorAndDigit[1].trim(), Integer.parseInt(colorAndDigit[0].trim()));
      }
      if (!gameOk(cubesMap)) {
        return false;
      }
    }

    return true;
  }

  private boolean gameOk(HashMap<String, Integer> cubesMap) {
    if (cubesMap.get("red") <= 12) {
      if (cubesMap.get("blue") <= 14) {
        if (cubesMap.get("green") <= 13) {
          return true;
        }
      }
    }
    return false;
  }

  private HashMap<String, Integer> getMinimalSet(String lineInput) {
    HashMap<String, Integer> cubesMap = new HashMap<>(Map.of("blue", 0, "red", 0, "green", 0));


    // Remove Game xx: -> 9 "red, 5 blue, 6 green; 6 red, 13 blue; 2 blue, 7 green, 5 red"
    String withoutGame = lineInput.substring(lineInput.indexOf(':') + 2);

    // Get string between ; -> ["9 red, 5 blue, 6 green", "6 red, 13 blue", "2 blue, 7 green, 5
    // red"]
    String[] splitedSet = withoutGame.split(";");
    for (String set : splitedSet) {
      // Get string between , -> ["9 red", "5 blue", "6 green"]
      String[] splitedCube = set.split(",");
      for (String cubes : splitedCube) {
        String[] colorAndDigit = cubes.strip().split(" ");
        Integer colorValue = Integer.parseInt(colorAndDigit[0].trim());

        if (colorValue > cubesMap.get(colorAndDigit[1].trim())) {
          cubesMap.put(colorAndDigit[1].trim(), colorValue);
        }
      }
    }

    return cubesMap;
  }
}