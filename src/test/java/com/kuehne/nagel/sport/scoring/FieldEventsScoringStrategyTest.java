package com.kuehne.nagel.sport.scoring;

import com.kuehne.nagel.sport.config.EventConfig;
import com.kuehne.nagel.sport.config.EventConfigRepository;
import com.kuehne.nagel.sport.model.event.EventName;
import com.kuehne.nagel.sport.model.event.EventType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FieldEventsScoringStrategyTest {

  @Test
  public void testScoringForLongJump() {
    ScoringStrategy strategy = ScoringStrategyFactory.getStrategy(EventType.FIELD);
    EventConfig configByEventName = EventConfigRepository.getConfigByEventName(EventName.LONG_JUMP);
    int pointsScored = strategy.calculatePointsScored(configByEventName, 776);
    assertEquals(1000, pointsScored);
  }

  @Test
  public void testScoringForHighJump() {
    ScoringStrategy strategy = ScoringStrategyFactory.getStrategy(EventType.FIELD);
    EventConfig configByEventName = EventConfigRepository.getConfigByEventName(EventName.HIGH_JUMP);
    int pointsScored = strategy.calculatePointsScored(configByEventName, 220.8);
    assertEquals(1000, pointsScored);
  }

  @Test
  public void testScoringForLavelinThrow() {
    ScoringStrategy strategy = ScoringStrategyFactory.getStrategy(EventType.FIELD);
    EventConfig configByEventName = EventConfigRepository.getConfigByEventName(EventName.JAVELIN_THROW);
    int pointsScored = strategy.calculatePointsScored(configByEventName, 77.19);
    assertEquals(1000, pointsScored);
  }

}
