package com.ovhcloud.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.ovhcloud.java.util.PuzzleInputs;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class Day2Test {

  @Test
  void testPuzzleOne() {
    int result = new Day2().puzzle1(PuzzleInputs.INPUT2_TEST);

    Assertions.assertEquals(8, result);
  }

    @Test
  void testPuzzleTwo() {
    int result = new Day2().puzzle2(PuzzleInputs.INPUT2_TEST);

    Assertions.assertEquals(2286, result);
  }

}
