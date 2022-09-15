package com.kuehne.nagel.sport.output;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class OutputBehaviorFactoryTest {

  @Test
  public void testGetBehavior() {
    OutputBehaviour behavior = OutPutBehaviorFactory.getBehavior(OutputFormat.XML);
    assertTrue(behavior instanceof XmlOutputBehaviour);
  }

}
