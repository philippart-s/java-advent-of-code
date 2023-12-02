package com.ovhcloud.java.util;

import java.io.InputStream;

public class FileOperations {

  public static InputStream loadInputs(String inputName) {
    return FileOperations.class.getResourceAsStream(inputName);
  }
}
