package com.ovhcloud.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class Day9Test {

  @Test
  void testPuzzleOne() {
    int result = new Day9().puzzle1("/input-day9-test.txt");

    Assertions.assertEquals(114, result);

    // 2043183816
  }

  @Test
  void testPuzzleTwo() {
    int result = new Day9().puzzle2("/input-day9-test.txt");

    Assertions.assertEquals(2, result);
    // 1118
  }

}
