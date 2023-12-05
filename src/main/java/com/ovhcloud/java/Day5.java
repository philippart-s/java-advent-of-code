package com.ovhcloud.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import com.ovhcloud.java.util.FileOperations;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "day5", mixinStandardHelpOptions = true)
public class Day5 implements Callable<Integer> {

  @Option(names = {"-part1"})
  private boolean puzzleOne;

  @Option(names = {"-part2"})
  private boolean puzzleTwo;

  int i = 1;


  @Override
  public Integer call() throws Exception {
    if (puzzleOne) {
      puzzle1("/input-day5-1.txt");
    }
    if (puzzleTwo) {
      puzzle2("/input-day5-2.txt");
    }

    return 0;
  }

  private List<List<Integer>> extractListOfList(List<String> inputList) {
    List<List<Integer>> res = new ArrayList<>();
    i = i + 2;

    while (i < inputList.size() && !inputList.get(i).trim().isEmpty()) {
      res.add(Arrays.stream(inputList.get(i).split("\\D+"))
          .filter(number -> !number.isEmpty()).mapToInt(Integer::parseInt).boxed().toList());
      i++;
    }    
    return res;
  }


  int puzzle1(String input) {
    int count = 0;
    Scanner scanner = new Scanner(FileOperations.loadInputs(input));
    String currentLine;
    ArrayList<String> inputAsArrayList = new ArrayList<String>();
    while (scanner.hasNextLine()) {
      currentLine = scanner.nextLine();
      inputAsArrayList.add(currentLine);
    }
    scanner.close();

    // List of seeds
    String[] seeds = inputAsArrayList.get(0).split(":")[1].split("\\D+");
    List<Integer> arrayOfSeeds = Arrays.stream(seeds).filter(number -> !number.isEmpty())
        .mapToInt(Integer::parseInt).boxed().toList();
    System.out.println("arrayOfSeeds: " + arrayOfSeeds);

    // List of seed-to-soil
    List<List<Integer>> seedsToSoilList = extractListOfList(inputAsArrayList);
    System.out.println("seedsToSoilList: " + seedsToSoilList);

    // List of soil-to-fertilizer 
    List<List<Integer>> soilsToFertilizerList = extractListOfList(inputAsArrayList);
    System.out.println("soilsToFertilizerList: " + soilsToFertilizerList);

    // List of fertilizer-to-water 
    List<List<Integer>> fertilizerToWaterList = extractListOfList(inputAsArrayList);
    System.out.println("fertilizerToWaterList: " + fertilizerToWaterList);

    // List of water-to-light 
    List<List<Integer>> waterToLightList = extractListOfList(inputAsArrayList);
    System.out.println("waterToLightList: " + waterToLightList);

    // List of light-to-temperature 
    List<List<Integer>> lightToTemperatureList = extractListOfList(inputAsArrayList);
    System.out.println("lightToTemperatureList: " + lightToTemperatureList);

    // List of temperature-to-humidity 
    List<List<Integer>> temperatureToHumidityList = extractListOfList(inputAsArrayList);
    System.out.println("temperatureToHumidityList: " + temperatureToHumidityList);

    // List of temperature-to-humidity 
    List<List<Integer>> humidityToLocationList = extractListOfList(inputAsArrayList);
    System.out.println("humidityToLocationList: " + humidityToLocationList);

    System.out.println("Result: " + count);
    return 0;
  }

  int puzzle2(String input) {
    int count = 0;

    /*
     * Scanner scanner = new Scanner(FileOperations.loadInputs(input));
     * 
     * while (scanner.hasNextLine()) { // Put code here } scanner.close();
     */

    System.out.println("Result: " + count);
    return 0;
  }
}

