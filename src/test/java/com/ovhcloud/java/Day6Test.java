package com.ovhcloud.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class Day6Test {

  @Test
  void testPuzzleOne() {
    long result = new Day6().puzzle1("/input-day6-test.txt");

    Assertions.assertEquals(288, result);
  }

  @Test
  void testPuzzleTwo() {
    long result = new Day6().puzzle2("/input-day6-test.txt");

    Assertions.assertEquals(71503, result);
  }

}
