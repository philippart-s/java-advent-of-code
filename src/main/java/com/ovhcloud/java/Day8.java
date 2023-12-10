package com.ovhcloud.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import org.apache.commons.math3.util.ArithmeticUtils;
import com.ovhcloud.java.util.FileOperations;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "day8", mixinStandardHelpOptions = true)
public class Day8 implements Callable<Integer> {

  @Option(names = {"-part1"})
  private boolean puzzleOne;

  @Option(names = {"-part2"})
  private boolean puzzleTwo;

  String finalDestination;
  Map<String, Coordinate> part2Start;
  Map<String, Coordinate> part2End;


  @Override
  public Integer call() throws Exception {
    if (puzzleOne) {
      puzzle1("/input-day8-1.txt");
    }
    if (puzzleTwo) {
      puzzle2("/input-day8-2.txt");
    }

    return 0;
  }

  int puzzle1(String input) {
    int count = 0;

    Map<String, Coordinate> coordinates = extractData(input);


    Scanner scanner = new Scanner(FileOperations.loadInputs(input));
    String[] navigations = scanner.nextLine().split("");
    scanner.nextLine().trim();
    String dest = "AAA";
    scanner.close();


    while (!dest.trim().equals("ZZZ")) {
      for (String direction : navigations) {
        // System.out.println("from: " + from);
        // System.out.println("direction: " + direction);
        if (coordinates.get(dest).equals("ZZZ")) {
          break;
        }


        if (direction.trim().equals("L")) {
          dest = coordinates.get(dest).left;
        } else if (direction.trim().equals("R")) {
          dest = coordinates.get(dest).right;
        } else {
          System.out.println("unknow direction: " + direction);
        }
        count++;
        if (dest.trim().equals("ZZZ")) {
          System.out.println("Find!!!!!");
          break;
        }
      }
      // System.out.println("repeat: " + from);
    }

    System.out.println("Result: " + count);
    return count;
  }

  long puzzle2(String input) {
    long count = 0;

    Map<String, Coordinate> coordinates = extractData(input);

    Scanner scanner = new Scanner(FileOperations.loadInputs(input));
    String[] navigations = scanner.nextLine().split("");
    scanner.nextLine().trim();
    String dest = "AAA";
    scanner.close();
    long[] tries = new long[part2Start.size()];
    long[] res = new long[part2Start.size()];
    part2End = new HashMap<>();

    System.out.println("Nmber of ghosts: " + tries.length);

    int j = 0;
    for (Coordinate coor : part2Start.values()) {
      dest = coor.source;
      while (!dest.substring(2).equals("Z")) {
        for (String direction : navigations) {
          // System.out.println("from: " + from);
          // System.out.println("direction: " + direction);
          if (direction.trim().equals("L")) {
            dest = coordinates.get(dest).left;
          } else if (direction.trim().equals("R")) {
            dest = coordinates.get(dest).right;
          } else {
            System.out.println("unknow direction: " + direction);
          }
          count++;
          if (dest.trim().substring(2).equals("Z")) {
            System.out.println("Find!!!!!");
            break;
          }
        }
        // System.out.println("repeat: " + from);
      }
      res[j] = count;
      System.out.println("count: " + count);
      j++;
      count = 0;
    }

    count = res[0];
    for (int i = 1; i < res.length; i++) {
      count = ArithmeticUtils.lcm(count, res[i]);
    }
    System.out.println("Result: " + count);
    return count;
  }

  Map<String, Coordinate> extractData(String input) {
    Map<String, Coordinate> coordinates = new HashMap();
    part2Start = new HashMap<>();
    String currentLine;

    Scanner scanner = new Scanner(FileOperations.loadInputs(input));


    scanner.nextLine();
    scanner.nextLine();

    while (scanner.hasNextLine()) {
      currentLine = scanner.nextLine();

      String source = currentLine.split("=")[0].trim();
      String left = currentLine.split("=")[1].split(",")[0].trim().substring(1);
      String right = currentLine.split("=")[1].split(",")[1].trim().substring(0, 3);
      coordinates.put(source, new Coordinate(source, left, right));
      if (source.substring(2).equals("A")) {
        part2Start.put(source, new Coordinate(source, left, right));
      }
    }
    scanner.close();

    return coordinates;
  }

  record Coordinate(String source, String left, String right) {

  }

}

