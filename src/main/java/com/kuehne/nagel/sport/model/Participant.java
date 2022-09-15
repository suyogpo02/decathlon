package com.kuehne.nagel.sport.model;

import com.kuehne.nagel.sport.model.event.EventName;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to hold internal data for decathlon participant needed for business logic
 */
public class Participant {

  String playerName;
  Map<EventName, String> eventPerformanceMap;
  Map<EventName, Integer> eventPointsMap;
  int totalScore;
  String rank;

  public Participant(String playerName, Map<EventName, String> map) {
    this.playerName = playerName;
    this.eventPerformanceMap = map;
    eventPointsMap = new HashMap<>();
  }

  public void addToMap(EventName eventName, Integer points) {
    eventPointsMap.put(eventName, points);
  }

  public void addToEventPerformanceMap(EventName eventName, String performance) {
    eventPerformanceMap.put(eventName, performance);
  }

  public Map<EventName, Integer> getEventPointsMap() {
    return eventPointsMap;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public Map<EventName, String> getEventPerformanceMap() {
    return eventPerformanceMap;
  }

  public void setEventPerformanceMap(
      Map<EventName, String> eventPerformanceMap) {
    this.eventPerformanceMap = eventPerformanceMap;
  }

  public Integer getTotalScore() {
    return totalScore;
  }

  public void setTotalScore(int totalScore) {
    this.totalScore = totalScore;
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }
}
