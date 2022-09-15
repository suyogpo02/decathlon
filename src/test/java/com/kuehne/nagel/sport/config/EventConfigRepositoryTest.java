package com.kuehne.nagel.sport.config;

import com.kuehne.nagel.sport.model.event.EventName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class EventConfigRepositoryTest {

  @Test
  public void testEventConfigByName() {
    int totalConfigs = 0;
    for (EventName eventName : EventName.values()) {
      EventConfig configByEventName = EventConfigRepository.getConfigByEventName(eventName);
      assertNotNull(configByEventName);
      totalConfigs++;
    }
    assertEquals(10, totalConfigs);
  }

}
