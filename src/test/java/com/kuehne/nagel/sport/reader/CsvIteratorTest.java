package com.kuehne.nagel.sport.reader;

import com.kuehne.nagel.sport.model.Participant;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvIteratorTest {

  @Test
  public void testReadCsvInput() throws IOException {
    String path = getClass().getClassLoader().getResource("input/test1.csv").getPath();
    Iterator<Participant> iterator = InputIteratorFactory.getIterator(InputFormat.CSV, path);
    List<Participant> participants = new ArrayList<>();
    while (iterator.hasNext()) {
      Participant participant = iterator.next();
      participants.add(participant);
    }
    assertEquals(4, participants.size());
  }

}
