package com.ovhcloud.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class Day3Test {

  @Test
  void testPuzzleOne() {
    int result = new Day3().puzzle1("/input-day3-test.txt");

    Assertions.assertEquals(4361, result);
  }

  @Test
  void testPuzzleTwo() {
    int result = new Day3().puzzle2("/input-day3-test.txt");

    Assertions.assertEquals(467835, result);
  }

}
