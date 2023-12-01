package com.ovhcloud.java;

import java.util.concurrent.Callable;
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
      puzzle1();
    }
    if (puzzleTwo) {
      puzzle2();
    }

    return 0;
  }

  private void puzzle1() {}

  private void puzzle2() {}
}
