package com.kuehne.nagel.sport.reader;

import com.kuehne.nagel.sport.model.Participant;
import com.kuehne.nagel.sport.model.event.EventName;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * An iterator to read records from csv file
 */
public class CsvIterator implements Iterator<Participant> {

  final BufferedReader reader;
  final FileReader fileReader;
  String line; //record line

  public CsvIterator(String filePath) throws IOException {
    fileReader = new FileReader(filePath);
    reader = new BufferedReader(fileReader);
  }

  @Override
  public boolean hasNext() {
    boolean hasNext;
    try {
      this.line = reader.readLine();
      hasNext = this.line != null;
    } catch (IOException e) {
      hasNext = false;
    }
    if (!hasNext) {
      closeReader();
    }
    return hasNext;
  }

  @Override
  public Participant next() {
    if (this.line == null) {
      throw new NoSuchElementException("No record found");
    }
    return parseRow(this.line);
  }

  private Participant parseRow(String row) {
    String[] fields = row.split(";");
    Map<EventName, String> eventNamePerformanceMap = Arrays.stream(EventName.values())
        .collect(Collectors.toMap(eventName -> eventName, eventName -> fields[eventName.getEventNumber()]));
    return new Participant(fields[0], eventNamePerformanceMap);
  }

  private void closeReader() {
    try {
      fileReader.close();
      reader.close();
    } catch (Exception e) {
      System.out.println("Unable to close file reader");
    }
  }

  public void closeReaderFromOutside() {
    closeReader();
  }
}
