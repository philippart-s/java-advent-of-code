package com.ovhcloud.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class Day8Test {

  @Test
  void testPuzzleOne() {
    int result = new Day8().puzzle1("/input-day8-test.txt");

    Assertions.assertEquals(6, result);
    // 13719 --
    // 14257
  }

  @Test
  void testPuzzleTwo() {
    long result = new Day8().puzzle2("/input-day8-test-2.txt");

    Assertions.assertEquals(6, result);

    // 300452481 --
    // 16187743689077
  }

}
