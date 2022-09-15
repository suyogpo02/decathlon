package com.kuehne.nagel.sport.config;

import com.kuehne.nagel.sport.model.event.EventName;

import java.util.HashMap;
import java.util.Map;

public class EventConfigRepository {


  private static final Map<EventName, EventConfig> eventConfigMap = new HashMap<EventName, EventConfig>();

  static {
    eventConfigMap.put(EventName.HUNDRED_METERS, new EventConfig(25.4347, 18, 1.81));
    eventConfigMap.put(EventName.LONG_JUMP, new EventConfig(0.14354, 220, 1.4));
    eventConfigMap.put(EventName.SHOT_PUT, new EventConfig(51.39, 1.5, 1.05));
    eventConfigMap.put(EventName.HIGH_JUMP, new EventConfig(0.8465, 75, 1.42));
    eventConfigMap.put(EventName.FOUR_HUNDRED_METERS, new EventConfig(1.53775, 82, 1.81));
    eventConfigMap.put(EventName.HUNDRED_TEN_METER_HURDLE, new EventConfig(5.74352, 28.5, 1.92));
    eventConfigMap.put(EventName.DISCUS_THROW, new EventConfig(12.91, 4, 1.1));
    eventConfigMap.put(EventName.POLE_VAULT, new EventConfig(0.2797, 100, 1.35));
    eventConfigMap.put(EventName.JAVELIN_THROW, new EventConfig(10.14, 7, 1.08));
    eventConfigMap.put(EventName.FIFTEEN_HUNDRED_METERS, new EventConfig(0.03768, 480, 1.85));

  }

  public static EventConfig getConfigByEventName(EventName eventName) {
    return eventConfigMap.get(eventName);
  }

}
