package com.ovhcloud.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class Day2Test {

  @Test
  void testPuzzleOne() {
    int result = new Day2().puzzle1("/input-day2-test.txt");

    Assertions.assertEquals(8, result);
  }

    @Test
  void testPuzzleTwo() {
    int result = new Day2().puzzle2("/input-day2-test.txt");

    Assertions.assertEquals(2286, result);
  }

}
