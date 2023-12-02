package com.ovhcloud.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class Day4Test {

  @Test
  void testPuzzleOne() {
    int result = new Day4().puzzle1("/input-day4-test.txt");

    Assertions.assertEquals(0, result);
  }

  @Test
  void testPuzzleTwo() {
    int result = new Day4().puzzle2("/input-day4-test.txt");

    Assertions.assertEquals(0, result);
  }

}
