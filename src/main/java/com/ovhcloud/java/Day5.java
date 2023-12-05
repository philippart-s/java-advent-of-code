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

  private List<AlmanacMap> extractListOfMap(List<String> inputList) {
    List<AlmanacMap> res = new ArrayList<>();
    i = i + 2;

    String[] elements;
    while (i < inputList.size() && !inputList.get(i).trim().isEmpty()) {
      elements = inputList.get(i).split("\\D+");
      res.add(new AlmanacMap(Long.parseLong(elements[0]), Long.parseLong(elements[1]),
          Long.parseLong(elements[2])));
      i++;
    }
    return res;
  }

  private long nextDestination(List<AlmanacMap> almanacMaps, Long seed) {
    long currentDestination = seed;
    for (AlmanacMap almanacMap : almanacMaps) {
      if (seed >= almanacMap.source && seed <= almanacMap.source + almanacMap.length) {
        currentDestination = seed - almanacMap.source + almanacMap.destination;
        break;
      }
    }
    return currentDestination;
  }

  long puzzle1(String input) {
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
    List<Long> arrayOfSeeds = Arrays.stream(seeds).filter(number -> !number.isEmpty())
        .mapToLong(Long::parseLong).boxed().toList();
    //System.out.println("arrayOfSeeds: " + arrayOfSeeds);

    // List of seed-to-soil
    List<AlmanacMap> seedsToSoilList = extractListOfMap(inputAsArrayList);
    //System.out.println("seedsToSoilList: " + seedsToSoilList);

    // List of soil-to-fertilizer
    List<AlmanacMap> soilsToFertilizerList = extractListOfMap(inputAsArrayList);
    //System.out.println("soilsToFertilizerList: " + soilsToFertilizerList);

    // List of fertilizer-to-water
    List<AlmanacMap> fertilizerToWaterList = extractListOfMap(inputAsArrayList);
    //System.out.println("fertilizerToWaterList: " + fertilizerToWaterList);

    // List of water-to-light
    List<AlmanacMap> waterToLightList = extractListOfMap(inputAsArrayList);
    //System.out.println("waterToLightList: " + waterToLightList);

    // List of light-to-temperature
    List<AlmanacMap> lightToTemperatureList = extractListOfMap(inputAsArrayList);
    //System.out.println("lightToTemperatureList: " + lightToTemperatureList);

    // List of temperature-to-humidity
    List<AlmanacMap> temperatureToHumidityList = extractListOfMap(inputAsArrayList);
    //System.out.println("temperatureToHumidityList: " + temperatureToHumidityList);

    // List of temperature-to-humidity
    List<AlmanacMap> humidityToLocationList = extractListOfMap(inputAsArrayList);
    //System.out.println("humidityToLocationList: " + humidityToLocationList);



    long currentDestination = Long.MAX_VALUE;
    long nextDestination = -1;
    for (Long seed : arrayOfSeeds) {
      nextDestination = nextDestination(seedsToSoilList, seed);
      nextDestination = nextDestination(soilsToFertilizerList, nextDestination);
      nextDestination = nextDestination(fertilizerToWaterList, nextDestination);
      nextDestination = nextDestination(waterToLightList, nextDestination);
      nextDestination = nextDestination(lightToTemperatureList, nextDestination);
      nextDestination = nextDestination(temperatureToHumidityList, nextDestination);
      nextDestination = nextDestination(humidityToLocationList, nextDestination);

      if (nextDestination < currentDestination) {
        currentDestination = nextDestination;
      }
    }

    System.out.println("Result: " + currentDestination);
    return currentDestination;
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


  class AlmanacMap {
    long destination, source, length;

    AlmanacMap(long destination, long source, long length) {
      this.destination = destination;
      this.source = source;
      this.length = length;
    }

    @Override
    public String toString() {
      return "destination: " + destination + ", source: " + source + ", length: " + length;
    }
  }
}

