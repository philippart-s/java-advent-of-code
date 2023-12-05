package com.ovhcloud.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class Day5Test {

  @Test
  void testPuzzleOne() {
    int result = new Day5().puzzle1("/input-day5-test.txt");

    Assertions.assertEquals(35, result);
  }

  @Test
  void testPuzzleTwo() {
    int result = new Day5().puzzle2("/input-day5-test.txt");

    Assertions.assertEquals(0, result);
  }

}
