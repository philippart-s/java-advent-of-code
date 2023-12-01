package com.ovhcloud.java.util;

public enum PuzzleInputs {
  INPUT1_1("/input-day1-1.txt"),
  INPUT1_2("/input-day1-2.txt");

  public final String label;

  private PuzzleInputs(String label) {
    this.label = label;
  }
}
