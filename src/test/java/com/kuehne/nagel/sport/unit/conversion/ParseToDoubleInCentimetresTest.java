package com.kuehne.nagel.sport.unit.conversion;

import com.kuehne.nagel.sport.model.unit.UnitType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ParseToDoubleInCentimetresTest {

  @Test
  public void testParseToDoubleInCentimeters() {
    UnitParser unitParser = UnitParserFactory.getUnitParser(UnitType.CENTIMETRES);
    double parsedOutput = unitParser.parse("2.208");
    assertEquals(220.8, parsedOutput);
  }

}
