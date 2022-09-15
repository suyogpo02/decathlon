package com.kuehne.nagel.sport.unit.conversion;

import com.kuehne.nagel.sport.model.unit.UnitType;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class UnitParserFactoryTest {

  @Test
  public void testGetParseToDoubleUnitParser() {
    UnitParser unitParser = UnitParserFactory.getUnitParser(UnitType.SECONDS);
    assertTrue(unitParser instanceof ParseToDouble);

    unitParser = UnitParserFactory.getUnitParser(UnitType.METERS);
    assertTrue(unitParser instanceof ParseToDouble);
  }

  @Test
  public void testGetParseToDoubleInCentimetresUnitParser() {
    UnitParser unitParser = UnitParserFactory.getUnitParser(UnitType.CENTIMETRES);
    assertTrue(unitParser instanceof ParseToDoubleInCentimetres);
  }

  @Test
  public void testGetParseToDoubleInSeconds() {
    UnitParser unitParser = UnitParserFactory.getUnitParser(UnitType.MINUTES_SECONDS);
    assertTrue(unitParser instanceof ParseToDoubleInSeconds);
  }

}
