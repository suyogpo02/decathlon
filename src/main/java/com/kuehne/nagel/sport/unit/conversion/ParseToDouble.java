package com.kuehne.nagel.sport.unit.conversion;

public class ParseToDouble implements UnitParser {

  public double parse(String input) {
    return Double.parseDouble(input);
  }
}
