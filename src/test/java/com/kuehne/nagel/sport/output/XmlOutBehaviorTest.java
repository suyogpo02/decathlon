package com.kuehne.nagel.sport.output;

import com.kuehne.nagel.sport.TestUtil;
import com.kuehne.nagel.sport.model.Participant;
import com.kuehne.nagel.sport.model.event.EventName;
import com.kuehne.nagel.sport.reader.InputFormat;
import com.kuehne.nagel.sport.reader.InputIteratorFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XmlOutBehaviorTest {

  @Test
  public void testReadCsvInput() throws Exception {
    String path = getClass().getClassLoader().getResource("input/test6.csv").getPath();
    Iterator<Participant> iterator = InputIteratorFactory.getIterator(InputFormat.CSV, path);
    List<Participant> participants = new ArrayList<>();
    while (iterator.hasNext()) {
      Participant participant = iterator.next();
      participant.setRank("1");
      participant.setTotalScore(10000);
      for (EventName eventName : participant.getEventPerformanceMap().keySet()) {
        participant.addToMap(eventName, 1000);
      }
      participants.add(participant);
    }
    assertEquals(1, participants.size());
    OutputBehaviour behavior = OutPutBehaviorFactory.getBehavior(OutputFormat.XML);
    String writePath = behavior.write(participants, path);
    List<Participant> expected = TestUtil.readFile(writePath);
    assertEquals(1, expected.size());
    assertEquals(participants.get(0).getRank(), expected.get(0).getRank());
    assertEquals(participants.get(0).getTotalScore(), expected.get(0).getTotalScore());
    assertEquals(participants.get(0).getPlayerName(), expected.get(0).getPlayerName());
  }

}
