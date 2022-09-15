package com.kuehne.nagel.sport.scoring;

import com.kuehne.nagel.sport.config.EventConfig;
import com.kuehne.nagel.sport.config.EventConfigRepository;
import com.kuehne.nagel.sport.model.event.EventName;
import com.kuehne.nagel.sport.model.event.EventType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TrackEventsScoringStrategyTest {

  @Test
  public void testScoringForHundredMeters() {
    ScoringStrategy strategy = ScoringStrategyFactory.getStrategy(EventType.TRACK);
    EventConfig configByEventName = EventConfigRepository.getConfigByEventName(EventName.HUNDRED_METERS);
    int pointsScored = strategy.calculatePointsScored(configByEventName, 10.827);
    assertEquals(900, pointsScored);
  }

  @Test
  public void testScoringForFourHundredMeters() {
    ScoringStrategy strategy = ScoringStrategyFactory.getStrategy(EventType.TRACK);
    EventConfig configByEventName = EventConfigRepository.getConfigByEventName(EventName.FOUR_HUNDRED_METERS);
    int pointsScored = strategy.calculatePointsScored(configByEventName, 50.32);
    assertEquals(800, pointsScored);
  }

  @Test
  public void testScoringForHundredTenMeterHurdles() {
    ScoringStrategy strategy = ScoringStrategyFactory.getStrategy(EventType.TRACK);
    EventConfig configByEventName = EventConfigRepository.getConfigByEventName(EventName.HUNDRED_TEN_METER_HURDLE);
    int pointsScored = strategy.calculatePointsScored(configByEventName, 16.29);
    assertEquals(700, pointsScored);
  }

}
