package com.ovhcloud.java.util;

import java.io.InputStream;

public class FileOperations {

  public static InputStream loadInputs(PuzzleInputs input) {
    return FileOperations.class.getResourceAsStream(input.label);
  }
}
