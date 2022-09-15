package com.kuehne.nagel.sport.reader;

import com.kuehne.nagel.sport.model.Participant;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class InputIteratorFactoryTest {

  @Test
  public void testGetInputIteratorTest() throws Exception {
    String path = getClass().getClassLoader().getResource("input/test1.csv").getPath();
    Iterator<Participant> iterator = InputIteratorFactory.getIterator(InputFormat.CSV, path);
    assertTrue(iterator instanceof CsvIterator);
    ((CsvIterator)iterator).closeReaderFromOutside();
  }

}
