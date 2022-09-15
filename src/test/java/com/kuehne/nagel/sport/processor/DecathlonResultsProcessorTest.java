package com.kuehne.nagel.sport.processor;

import com.kuehne.nagel.sport.TestUtil;
import com.kuehne.nagel.sport.model.Participant;
import com.kuehne.nagel.sport.output.OutputFormat;
import com.kuehne.nagel.sport.reader.InputFormat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecathlonResultsProcessorTest {

  @Test
  public void testCalculateResultsForDistinctRanks() throws Exception {
    String path = getClass().getClassLoader().getResource("input/test1.csv").getPath();
    DecathlonResultsProcessor decathlonResultsProcessor = new DecathlonResultsProcessor();
    String outPutFilePath = decathlonResultsProcessor.process(path, InputFormat.CSV, OutputFormat.XML);
    List<Participant> participants = TestUtil.readFile(outPutFilePath);

    Map<String, Integer> participantExpectedScore = new HashMap<>();
    participantExpectedScore.put("John Smith", 4200);
    participantExpectedScore.put("Jaan Lepp", 3494);
    participantExpectedScore.put("Jane Doe", 3199);
    participantExpectedScore.put("Foo Bar", 3099);

    Map<String, String> participantExpectedRank = new HashMap<>();
    participantExpectedRank.put("John Smith", "1");
    participantExpectedRank.put("Jaan Lepp", "2");
    participantExpectedRank.put("Jane Doe", "3");
    participantExpectedRank.put("Foo Bar", "4");

    assertEquals(4, participants.size());
    participants.forEach(participant -> {
      assertEquals(participant.getRank(), participantExpectedRank.get(participant.getPlayerName()));
      assertEquals(participant.getTotalScore(), participantExpectedScore.get(participant.getPlayerName()));
    });

  }

  @Test
  public void testCalculateResultsForSplitRanks() throws Exception {
    String path = getClass().getClassLoader().getResource("input/test2.csv").getPath();
    DecathlonResultsProcessor decathlonResultsProcessor = new DecathlonResultsProcessor();
    String outPutFilePath = decathlonResultsProcessor.process(path, InputFormat.CSV, OutputFormat.XML);
    List<Participant> participants = TestUtil.readFile(outPutFilePath);

    Map<String, Integer> participantExpectedScore = new HashMap<>();
    participantExpectedScore.put("John Smith", 4200);
    participantExpectedScore.put("Sam Will", 4200);
    participantExpectedScore.put("Jaan Lepp", 3494);
    participantExpectedScore.put("Jane Doe", 3199);
    participantExpectedScore.put("Foo Bar", 3099);
    participantExpectedScore.put("Foo Jar", 3099);

    Map<String, String> participantExpectedRank = new HashMap<>();
    participantExpectedRank.put("John Smith", "1-2");
    participantExpectedRank.put("Sam Will", "1-2");
    participantExpectedRank.put("Jaan Lepp", "3");
    participantExpectedRank.put("Jane Doe", "4");
    participantExpectedRank.put("Foo Bar", "5-6");
    participantExpectedRank.put("Foo Jar", "5-6");

    assertEquals(6, participants.size());
    participants.forEach(participant -> {
      assertEquals(participant.getRank(), participantExpectedRank.get(participant.getPlayerName()));
      assertEquals(participant.getTotalScore(), participantExpectedScore.get(participant.getPlayerName()));
    });

  }

  @Test
  public void testCalculateResultsForThreeParticipantsSameRank() throws Exception {
    String path = getClass().getClassLoader().getResource("input/test3.csv").getPath();
    DecathlonResultsProcessor decathlonResultsProcessor = new DecathlonResultsProcessor();
    String outPutFilePath = decathlonResultsProcessor.process(path, InputFormat.CSV, OutputFormat.XML);
    List<Participant> participants = TestUtil.readFile(outPutFilePath);

    Map<String, Integer> participantExpectedScore = new HashMap<>();
    participantExpectedScore.put("John Smith", 4200);
    participantExpectedScore.put("Sam Will", 4200);
    participantExpectedScore.put("Jane Doe", 4200);
    participantExpectedScore.put("Jaan Lepp", 3494);

    participantExpectedScore.put("Foo Bar", 3099);
    participantExpectedScore.put("Foo Jar", 3099);

    Map<String, String> participantExpectedRank = new HashMap<>();
    participantExpectedRank.put("John Smith", "1-3");
    participantExpectedRank.put("Sam Will", "1-3");
    participantExpectedRank.put("Jane Doe", "1-3");
    participantExpectedRank.put("Jaan Lepp", "4");

    participantExpectedRank.put("Foo Bar", "5-6");
    participantExpectedRank.put("Foo Jar", "5-6");

    assertEquals(6, participants.size());
    participants.forEach(participant -> {
      assertEquals(participant.getRank(), participantExpectedRank.get(participant.getPlayerName()));
      assertEquals(participant.getTotalScore(), participantExpectedScore.get(participant.getPlayerName()));
    });

  }

  @Test
  public void testCalculateResultsForAllParticipantsSameScore() throws Exception {
    String path = getClass().getClassLoader().getResource("input/test4.csv").getPath();
    DecathlonResultsProcessor decathlonResultsProcessor = new DecathlonResultsProcessor();
    String outPutFilePath = decathlonResultsProcessor.process(path, InputFormat.CSV, OutputFormat.XML);
    List<Participant> participants = TestUtil.readFile(outPutFilePath);

    Map<String, Integer> participantExpectedScore = new HashMap<>();
    participantExpectedScore.put("John Smith", 3494);
    participantExpectedScore.put("Sam Will", 3494);
    participantExpectedScore.put("Jane Doe", 3494);
    participantExpectedScore.put("Jaan Lepp", 3494);
    participantExpectedScore.put("Foo Bar", 3494);
    participantExpectedScore.put("Foo Jar", 3494);

    Map<String, String> participantExpectedRank = new HashMap<>();
    participantExpectedRank.put("John Smith", "1-6");
    participantExpectedRank.put("Sam Will", "1-6");
    participantExpectedRank.put("Jaan Lepp", "1-6");
    participantExpectedRank.put("Jane Doe", "1-6");
    participantExpectedRank.put("Foo Bar", "1-6");
    participantExpectedRank.put("Foo Jar", "1-6");

    assertEquals(6, participants.size());
    participants.forEach(participant -> {
      assertEquals(participant.getRank(), participantExpectedRank.get(participant.getPlayerName()));
      assertEquals(participant.getTotalScore(), participantExpectedScore.get(participant.getPlayerName()));
    });

  }

  @Test
  public void testCalculateResultsForTwoHavingTenThousandPoints() throws Exception {
    String path = getClass().getClassLoader().getResource("input/test5.csv").getPath();
    DecathlonResultsProcessor decathlonResultsProcessor = new DecathlonResultsProcessor();
    String outPutFilePath = decathlonResultsProcessor.process(path, InputFormat.CSV, OutputFormat.XML);
    List<Participant> participants = TestUtil.readFile(outPutFilePath);

    Map<String, Integer> participantExpectedScore = new HashMap<>();
    participantExpectedScore.put("Sam Johnson", 10000);
    participantExpectedScore.put("Rock Will", 10000);
    participantExpectedScore.put("John Smith", 4200);

    Map<String, String> participantExpectedRank = new HashMap<>();
    participantExpectedRank.put("Sam Johnson", "1-2");
    participantExpectedRank.put("Rock Will", "1-2");
    participantExpectedRank.put("John Smith", "3");

    assertEquals(3, participants.size());
    participants.forEach(participant -> {
      assertEquals(participant.getRank(), participantExpectedRank.get(participant.getPlayerName()));
      assertEquals(participant.getTotalScore(), participantExpectedScore.get(participant.getPlayerName()));
    });

  }

}
