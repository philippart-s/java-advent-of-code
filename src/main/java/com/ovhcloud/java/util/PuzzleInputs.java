package com.ovhcloud.java.util;

public enum PuzzleInputs {
  INPUT1_1("/input-day1-1.txt"),
  INPUT1_2("/input-day1-2.txt"),
  INPUT2_1("/input-day2-1.txt"),
  INPUT2_2("/input-day2-2.txt"),
  INPUT2_TEST("/input-day2-Test.txt");

  public final String label;

  private PuzzleInputs(String label) {
    this.label = label;
  }
}
