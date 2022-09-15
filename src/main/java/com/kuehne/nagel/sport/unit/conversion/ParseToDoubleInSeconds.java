package com.kuehne.nagel.sport.unit.conversion;

public class ParseToDoubleInSeconds implements UnitParser {

  public double parse(String input) {

    String[] split = input.split(":");
    return Double.parseDouble(split[0]) * 60 + Double.parseDouble(split[1]);
  }
}
