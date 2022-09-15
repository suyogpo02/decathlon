package com.kuehne.nagel.sport.model.event;

import com.kuehne.nagel.sport.model.unit.UnitType;

public enum EventName {

  HUNDRED_METERS("100 m", 1, UnitType.SECONDS, EventType.TRACK),
  LONG_JUMP("Long jump", 2, UnitType.CENTIMETRES, EventType.FIELD),
  SHOT_PUT("Shot put", 3, UnitType.METERS, EventType.FIELD),
  HIGH_JUMP("High jump", 4, UnitType.CENTIMETRES, EventType.FIELD),
  FOUR_HUNDRED_METERS("400 m", 5, UnitType.SECONDS, EventType.TRACK),
  HUNDRED_TEN_METER_HURDLE("110 m hurdles", 6, UnitType.SECONDS, EventType.TRACK),
  DISCUS_THROW("Discus throw", 7, UnitType.METERS, EventType.FIELD),
  POLE_VAULT("Pole vault", 8, UnitType.CENTIMETRES, EventType.FIELD),
  JAVELIN_THROW("Javelin throw", 9, UnitType.METERS, EventType.FIELD),
  FIFTEEN_HUNDRED_METERS("1500 m", 10, UnitType.MINUTES_SECONDS, EventType.TRACK);

  final String nameOfEvent;
  final int eventNumber;
  final UnitType unitType;
  final EventType eventType;

  EventName(String s, int i, UnitType unitType, EventType eventType) {
    nameOfEvent = s;
    eventNumber = i;
    this.unitType = unitType;
    this.eventType = eventType;
  }

  public EventType getEventType() {
    return eventType;
  }

  public String getNameOfEvent() {
    return nameOfEvent;
  }

  public int getEventNumber() {
    return eventNumber;
  }

  public UnitType getUnitType() {
    return unitType;
  }

}
