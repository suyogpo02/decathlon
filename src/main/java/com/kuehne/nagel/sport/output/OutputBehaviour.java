package com.kuehne.nagel.sport.output;

import com.kuehne.nagel.sport.model.Participant;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

public interface OutputBehaviour {

  String write(List<Participant> participants, String inputFilePath)
      throws ParserConfigurationException, TransformerException;

}
