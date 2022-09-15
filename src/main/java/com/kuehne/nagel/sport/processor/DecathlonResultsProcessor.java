package com.kuehne.nagel.sport.processor;

import com.kuehne.nagel.sport.config.EventConfig;
import com.kuehne.nagel.sport.config.EventConfigRepository;
import com.kuehne.nagel.sport.exception.DecathlonResultsProcessingException;
import com.kuehne.nagel.sport.model.Participant;
import com.kuehne.nagel.sport.model.event.EventName;
import com.kuehne.nagel.sport.output.OutPutBehaviorFactory;
import com.kuehne.nagel.sport.output.OutputBehaviour;
import com.kuehne.nagel.sport.output.OutputFormat;
import com.kuehne.nagel.sport.reader.InputFormat;
import com.kuehne.nagel.sport.reader.InputIteratorFactory;
import com.kuehne.nagel.sport.scoring.ScoringStrategyFactory;
import com.kuehne.nagel.sport.unit.conversion.UnitParserFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class responsible for orchestration of all the steps required to rank the participants in decathlon
 */
public class DecathlonResultsProcessor {

  static Logger decathlonLogger = Logger.getLogger(DecathlonResultsProcessor.class.getName());

  public String process(String inputFilePath, InputFormat inputFormat, OutputFormat outputFormat)
      throws DecathlonResultsProcessingException {
    //Data structure to hold participants
    final List<Participant> participants = new ArrayList<>();
    //read participants from file and calculate points for each of them
    final int maxScore = readAndPopulate(inputFormat, inputFilePath, participants);
    //rank participants
    List<Participant> orderedParticipants = rankParticipants(participants, maxScore);
    //out put the result
    String outputFilePath = outputResults(inputFilePath, orderedParticipants, outputFormat);
    decathlonLogger.log(Level.INFO, "Successfully written output to file {0} ", outputFilePath);
    return outputFilePath;
  }

  private String outputResults(String inputFilePath, List<Participant> orderedParticipants, OutputFormat outputFormat)
      throws DecathlonResultsProcessingException {
    OutputBehaviour behavior = OutPutBehaviorFactory.getBehavior(outputFormat);
    try {
      return behavior.write(orderedParticipants, inputFilePath);
    } catch (ParserConfigurationException | TransformerException e) {
      throw new DecathlonResultsProcessingException(e.getMessage(), e);
    }
  }

  /**
   * Using bucket sort to rank participants
   */
  private List<Participant> rankParticipants(List<Participant> inputRecords, int maxScore) {
    decathlonLogger.log(Level.INFO, "Ranking participants");
    ArrayList<Participant>[] bucketSortArray = populateArrayForBucketSorting(inputRecords, maxScore);
    int rank = 1;
    List<Participant> orderedParticipants = new ArrayList<>();
    for (int index = bucketSortArray.length - 1; index >= 0; index--) {
      String rankString = String.valueOf(rank);
      if (bucketSortArray[index] != null) {
        ArrayList<Participant> list = bucketSortArray[index];
        if (list.size() > 1) {
          rankString = "" + rank + "-" + (rank + list.size() - 1);
        }
        for (Participant participant : list) {
          participant.setRank(rankString);
          orderedParticipants.add(participant);
        }
        rank += list.size();
      }
    }
    decathlonLogger.log(Level.INFO, "Completed ranking participants");
    return orderedParticipants;
  }

  private ArrayList<Participant>[] populateArrayForBucketSorting(List<Participant> inputRecords, int maxScore) {
    ArrayList<Participant>[] bucketSort = new ArrayList[maxScore + 1];
    for (Participant participant : inputRecords) {
      Integer totalScore = participant.getTotalScore();
      if (bucketSort[totalScore] == null) {
        bucketSort[totalScore] = new ArrayList<>();
      }
      bucketSort[totalScore].add(participant);
    }
    return bucketSort;
  }

  private Iterator<Participant> getIterator(InputFormat inputFormat, String filePath)
      throws DecathlonResultsProcessingException {
    try {
      return InputIteratorFactory.getIterator(inputFormat, filePath);
    } catch (IOException e) {
      throw new DecathlonResultsProcessingException(e.getMessage(), e);
    }
  }

  private int readAndPopulate(InputFormat format, String inputFilePath,
      List<Participant> participants) throws DecathlonResultsProcessingException {
    decathlonLogger.log(Level.INFO, "Reading records from file {0}", inputFilePath);
    Iterator<Participant> iterator = getIterator(format, inputFilePath);
    int maxScore = 0;
    while (iterator.hasNext()) {
      Participant participant = iterator.next();
      int score = calculateScore(participant);
      participant.setTotalScore(score);
      participants.add(participant);
      maxScore = Math.max(maxScore, score);
    }
    decathlonLogger
        .log(Level.INFO,
            "Reading and score calculation completed from file " + inputFilePath + " , Max score is " + maxScore);
    return maxScore;
  }

  private int calculateScore(Participant participant) {
    Map<EventName, String> eventPerformanceMap = participant.getEventPerformanceMap();
    int totalScore = 0;
    for (Map.Entry<EventName, String> entry : eventPerformanceMap.entrySet()) {
      final EventName eventName = entry.getKey();
      final EventConfig eventConfig = EventConfigRepository.getConfigByEventName(eventName);
      final double performance = UnitParserFactory.getUnitParser(eventName.getUnitType()).parse(entry.getValue());
      final int score = ScoringStrategyFactory.getStrategy(entry.getKey().getEventType())
          .calculatePointsScored(eventConfig, performance);
      participant.addToMap(eventName, score);
      totalScore += score;
    }
    return totalScore;
  }

}
