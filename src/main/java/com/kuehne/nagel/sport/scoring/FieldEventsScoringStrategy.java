package com.kuehne.nagel.sport.scoring;

import com.kuehne.nagel.sport.config.EventConfig;

public class FieldEventsScoringStrategy implements ScoringStrategy {

  public int calculatePointsScored(EventConfig config, double performance) {
    return (int)Math.floor(config.getA() * Math.pow((performance - config.getB()), config.getC()));
  }
}
