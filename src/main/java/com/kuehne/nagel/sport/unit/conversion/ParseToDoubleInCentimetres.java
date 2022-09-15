package com.kuehne.nagel.sport.unit.conversion;

public class ParseToDoubleInCentimetres implements UnitParser {

  public double parse(String input) {
    return Double.parseDouble(input) * 100;
  }
}
