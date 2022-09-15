package com.kuehne.nagel.sport.reader;

import com.kuehne.nagel.sport.model.Participant;

import java.io.IOException;
import java.util.Iterator;

public class InputIteratorFactory {

  private InputIteratorFactory() {}

  public static Iterator<Participant> getIterator(InputFormat format, String filePath) throws IOException {
    if (format.equals(InputFormat.CSV)) {
      return new CsvIterator(filePath);
    }
    return null;
  }

}
