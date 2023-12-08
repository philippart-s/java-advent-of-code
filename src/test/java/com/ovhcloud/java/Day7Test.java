package com.ovhcloud.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class Day7Test {

  @Test
  void testPuzzleOne() {
    Day7.isJocker = false;
    int result = new Day7().puzzle1("/input-day7-test.txt");

    Assertions.assertEquals(6440, result);
  }

  @Test
  void testPuzzleTwo() {
    Day7.isJocker = true;
    int result = new Day7().puzzle2("/input-day7-test.txt");

    Assertions.assertEquals(5905, result);
  }
}