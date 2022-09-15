package com.kuehne.nagel.sport.scoring;

import com.kuehne.nagel.sport.model.event.EventType;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ScoringStrategyTest {

  @Test
  public void testGetFieldEventsScoringStrategy() {
    ScoringStrategy scoringStrategy = ScoringStrategyFactory.getStrategy(EventType.FIELD);
    assertTrue(scoringStrategy instanceof FieldEventsScoringStrategy);
  }

  @Test
  public void testGetTrackEventsScoringStrategy() {
    ScoringStrategy scoringStrategy = ScoringStrategyFactory.getStrategy(EventType.TRACK);
    assertTrue(scoringStrategy instanceof TrackEventsScoringStrategy);
  }

  @Test
  public void testGetNoScoringStrategyWhenNoTypeMentioned() {
    ScoringStrategy scoringStrategy = ScoringStrategyFactory.getStrategy(null);
    assertNull(scoringStrategy);
  }

}
