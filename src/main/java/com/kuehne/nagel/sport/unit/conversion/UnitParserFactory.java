package com.kuehne.nagel.sport.unit.conversion;

import com.kuehne.nagel.sport.model.unit.UnitType;

public class UnitParserFactory {

  private static final ParseToDouble parseToDouble = new ParseToDouble();
  private static final ParseToDoubleInCentimetres parseToDoubleInCentimetres = new ParseToDoubleInCentimetres();
  private static final ParseToDoubleInSeconds parseToDoubleInSeconds = new ParseToDoubleInSeconds();
  private UnitParserFactory() {
  }

  public static UnitParser getUnitParser(UnitType unitType) {
    UnitParser unitParser = null;
    switch (unitType) {
      case METERS:
      case SECONDS:
        unitParser = parseToDouble;
        break;
      case MINUTES_SECONDS:
        unitParser = parseToDoubleInSeconds;
        break;
      case CENTIMETRES:
        unitParser = parseToDoubleInCentimetres;
        break;
    }
    return unitParser;
  }

}
