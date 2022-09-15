package com.kuehne.nagel.sport;

import com.kuehne.nagel.sport.output.OutputFormat;
import com.kuehne.nagel.sport.processor.DecathlonResultsProcessor;
import com.kuehne.nagel.sport.reader.InputFormat;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DecathlonApplication {

  private static final InputFormat INPUT_FORMAT = InputFormat.CSV;
  private static final OutputFormat OUTPUT_FORMAT = OutputFormat.XML;
  private static final Logger logApp = Logger.getLogger(DecathlonApplication.class.getName());

  public static void main(String[] args) {
    try {
      String inputFilePath = args[0];
      logApp.log(Level.INFO, "Received request to process results for file {0}", inputFilePath);
      DecathlonResultsProcessor decathlonResultsProcessor = new DecathlonResultsProcessor();
      decathlonResultsProcessor
          .process(inputFilePath, INPUT_FORMAT, OUTPUT_FORMAT);
    } catch (Exception e) {
      logApp.log(Level.SEVERE, "An exception occurred while processing the file. Exception is : {0}", e.getMessage());
    }
  }

}
