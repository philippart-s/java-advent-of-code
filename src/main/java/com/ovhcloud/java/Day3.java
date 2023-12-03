package com.ovhcloud.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.ovhcloud.java.Day3.PartNumber;
import com.ovhcloud.java.util.FileOperations;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "day3", mixinStandardHelpOptions = true)
public class Day3 implements Callable<Integer> {

  @Option(names = {"-part1"})
  private boolean puzzleOne;

  @Option(names = {"-part2"})
  private boolean puzzleTwo;

  HashMap<Integer, ArrayList<PartNumber>> bagPartNumbers =
      new HashMap<Integer, ArrayList<PartNumber>>();
  HashMap<Integer, ArrayList<Symbol>> bagSymbols = new HashMap<Integer, ArrayList<Symbol>>();

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

  private void constructBags(String input) {
    ArrayList<PartNumber> partNumbers = new ArrayList<PartNumber>();
    ArrayList<Symbol> symbols = new ArrayList<Symbol>();

    Scanner scanner = new Scanner(FileOperations.loadInputs(input));
    int lineNumber = 1;
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      partNumbers = new ArrayList<>();
      Pattern pattern = Pattern.compile("\\d+");
      Matcher matcher = pattern.matcher(line);

      while (matcher.find()) {
        partNumbers.add(new PartNumber(Integer.parseInt(matcher.group()), matcher.start(),
            matcher.end(), lineNumber));
      }
      bagPartNumbers.put(lineNumber, partNumbers);

      pattern = Pattern.compile("[^0-9\\.]");
      matcher = pattern.matcher(line);
      symbols = new ArrayList<>();

      while (matcher.find()) {
        symbols.add(new Symbol(matcher.group(), matcher.start(), matcher.end(), lineNumber));
      }
      bagSymbols.put(lineNumber, symbols);

      lineNumber++;
    }
    scanner.close();

  }

  int puzzle1(String input) {
    int count = 0;

    constructBags(input);

    for (Integer keyPart : bagPartNumbers.keySet()) {
      for (PartNumber partNumber : bagPartNumbers.get(keyPart)) {
        for (Integer keySymbol : bagSymbols.keySet()) {
          if (isASymbolIsAdjacent(partNumber, bagSymbols.get(keyPart))) {
            // System.out.println("partNumber cuurent: " + bagPartNumbers.get(keyPart));
            count += partNumber.number;
            break;
          } else {
            if (isASymbolIsAdjacent(partNumber, bagSymbols.get(keyPart - 1))) {
              // System.out.println("partNumber previous: " + bagPartNumbers.get(keyPart));
              count += partNumber.number;
              break;
            } else {
              if (isASymbolIsAdjacent(partNumber, bagSymbols.get(keyPart + 1))) {
                // System.out.println("partNumber next: " + bagPartNumbers.get(keyPart));
                count += partNumber.number;
                break;
              }
            }
          }
        }
      }
    }
    System.out.println("Result part 1: " + count);
    return count;
  }

  private boolean isASymbolIsAdjacent(PartNumber partNumber, ArrayList<Symbol> symbols) {
    if (symbols != null) {
      for (Symbol symbol : symbols) {
        if (symbol.start >= partNumber.start - 1 && symbol.end <= partNumber.end + 1) {
          return true;
        }
      }
    }
    return false;
  }
  record PartNumber(int number, int start, int end, int lineNumber) {
  }
  record Symbol(String symbol, int start, int end, int lineNumber) {
  }

  int puzzle2(String input) {
    int count = 0;
    constructBags(input);

    for (Integer keySymbol : bagSymbols.keySet()) {
      for (Symbol symbol : bagSymbols.get(keySymbol)) {
        Integer ratio = null;
          int find = 0;

        if (symbol.symbol.equals("*")) {
          for (PartNumber partNumber : bagPartNumbers.get(keySymbol)) {
            if (partNumber.start - 1 <= symbol.start && partNumber.end + 1 >= symbol.end) {
              find++;
              if (ratio == null)
                ratio = 1;
              ratio *= partNumber.number;
            }
          }
          for (PartNumber partNumber : bagPartNumbers.get(keySymbol - 1)) {
            if (partNumber.start - 1 <= symbol.start && partNumber.end + 1 >= symbol.end) {
              find++;
              if (ratio == null)
                ratio = 1;
              ratio *= partNumber.number;
            }
          }
          for (PartNumber partNumber : bagPartNumbers.get(keySymbol + 1)) {
            if (partNumber.start - 1 <= symbol.start && partNumber.end + 1 >= symbol.end) {
              find++;
              if (ratio == null)
                ratio = 1;
              ratio *= partNumber.number;
            }
          }
        }

        if (ratio != null && find == 2)
          count += ratio;
          else  find = 0;
      }
    }

    System.out.println("Result part 2: " + count);
    return count;
  }
}
