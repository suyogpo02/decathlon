package com.kuehne.nagel.sport.scoring;

import com.kuehne.nagel.sport.config.EventConfig;

public class TrackEventsScoringStrategy implements ScoringStrategy {

  public int calculatePointsScored(EventConfig config, double p) {
    return (int)Math.floor(config.getA() * Math.pow((config.getB() - p), config.getC()));
  }
}
