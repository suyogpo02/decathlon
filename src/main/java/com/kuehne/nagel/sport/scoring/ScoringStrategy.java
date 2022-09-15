package com.kuehne.nagel.sport.scoring;

import com.kuehne.nagel.sport.config.EventConfig;

public interface ScoringStrategy {

  public int calculatePointsScored(EventConfig config, double p);

}
