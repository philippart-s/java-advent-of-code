package com.ovhcloud.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import com.ovhcloud.java.Day5.Seed;
import com.ovhcloud.java.util.FileOperations;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "day6", mixinStandardHelpOptions = true)
public class Day6 implements Callable<Integer> {

  @Option(names = {"-part1"})
  private boolean puzzleOne;

  @Option(names = {"-part2"})
  private boolean puzzleTwo;

  @Override
  public Integer call() throws Exception {
    if (puzzleOne) {
      puzzle1("/input-day6-1.txt");
    }
    if (puzzleTwo) {
      puzzle2("/input-day6-2.txt");
    }

    return 0;
  }

  private List<Race> extractRaces(String input) {
    ArrayList<Race> races = new ArrayList<>();

    Scanner scanner = new Scanner(FileOperations.loadInputs(input));
    String[] arrTime = scanner.nextLine().split(":")[1].replaceFirst("^\\s*", "").split("\\s+");
    String[] arrDistance = scanner.nextLine().split(":")[1].replaceFirst("^\\s*", "").split("\\s+");

    scanner.close();

    for (int i = 0; i < arrTime.length; i++) {
      races.add(new Race(Integer.parseInt(arrTime[i]), Integer.parseInt(arrDistance[i])));
    }
    return races;
  }

    private Race extractRaces2(String input) {
    Scanner scanner = new Scanner(FileOperations.loadInputs(input));
    String arrTime = scanner.nextLine().split(":")[1].replaceAll("\\s+", "").trim();
    System.out.println("arrTime: " + arrTime);
    String arrDistance = scanner.nextLine().split(":")[1].replaceAll("\\s+", "");

    scanner.close();

    return new Race(Long.parseLong(arrTime), Long.parseLong(arrDistance));
  }


  long puzzle1(String input) {
    long count = 1;

    List<Race> races = extractRaces(input); 
    System.out.println("Races: " + races);
    long boatDistance = 0;
    long wayToWin = 0;
    for (Race race : races) {
      for (int i = 1; i <= race.time; i++) {
        boatDistance = (race.time - i) * i;
        if (boatDistance > race.distance) {
          wayToWin++;
        }
      }
      if (wayToWin != 0) {
        count = wayToWin * count;
      }
      wayToWin = 0;
      
    }

    System.out.println("Result: " + count);
    return count;
  }

  long puzzle2(String input) {
    long count = 1;

    Race race = extractRaces2(input); 
    System.out.println("Race: " + race);
    long boatDistance = 0;
    long wayToWin = 0;
      for (int i = 1; i <= race.time; i++) {
        boatDistance = (race.time - i) * i;
        if (boatDistance > race.distance) {
          wayToWin++;
        }
      }
      if (wayToWin != 0) {
        count = wayToWin * count;
      }
      wayToWin = 0;
      

    System.out.println("Result: " + count);
    return count;
  }

  record Race(long time, long distance) {
  }

}

