package com.kuehne.nagel.sport.scoring;

import com.kuehne.nagel.sport.model.event.EventType;

public class ScoringStrategyFactory {

  private static final FieldEventsScoringStrategy fieldEventsScoringStrategy = new FieldEventsScoringStrategy();
  private static final TrackEventsScoringStrategy trackEventsScoringStrategy = new TrackEventsScoringStrategy();

  private ScoringStrategyFactory() {
  }

  public static ScoringStrategy getStrategy(EventType eventType) {
    ScoringStrategy scoringStrategy = null;
    if (eventType != null) {
      switch (eventType) {

        case FIELD:
          scoringStrategy = fieldEventsScoringStrategy;
          break;

        case TRACK:
          scoringStrategy = trackEventsScoringStrategy;
          break;

        default:
          scoringStrategy = null;
      }
    }
    return scoringStrategy;
  }

}
