package com.kuehne.nagel.sport.unit.conversion;

import com.kuehne.nagel.sport.model.unit.UnitType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ParseToDoubleInSecondsTest {

  @Test
  public void testParseToDoubleInSeconds(){
    UnitParser unitParser = UnitParserFactory.getUnitParser(UnitType.MINUTES_SECONDS);
    double parsedOutput = unitParser.parse("3:53.79");
    assertEquals(233.79, parsedOutput);
  }

}
