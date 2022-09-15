package com.kuehne.nagel.sport.unit.conversion;

import com.kuehne.nagel.sport.model.unit.UnitType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ParseToDoubleUnitParserTest {

  @Test
  public void testParseToDouble() {
    UnitParser unitParser = UnitParserFactory.getUnitParser(UnitType.METERS);
    double parsedOutput = unitParser.parse("7.76");
    assertEquals(7.76, parsedOutput);
  }

  @Test
  public void testParseToDoubleForRunning() {
    UnitParser unitParser = UnitParserFactory.getUnitParser(UnitType.SECONDS);
    double parsedOutput = unitParser.parse("48.29");
    assertEquals(48.29, parsedOutput);
  }

}
