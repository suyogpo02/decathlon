package com.kuehne.nagel.sport.output;

import com.kuehne.nagel.sport.model.Participant;
import com.kuehne.nagel.sport.model.event.EventName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Map;

public class XmlOutputBehaviour implements OutputBehaviour {

  @Override
  public String write(List<Participant> participants, String inputFilePath)
      throws ParserConfigurationException, TransformerException {
    Document document = getDocument();
    Element decathlon = document.createElement("decathlon");
    document.appendChild(decathlon);
    for (Participant participant : participants) {
      Element player = document.createElement("participant");
      Element name = getElement(document, "name", participant.getPlayerName());
      player.appendChild(name);
      Element events = document.createElement("events");
      player.appendChild(events);
      for (Map.Entry<EventName, String> entry : participant.getEventPerformanceMap().entrySet()) {
        Element event = document.createElement("event");
        event.appendChild(getElement(document, "event_name", entry.getKey().getNameOfEvent()));
        event.appendChild(getElement(document, "performance", entry.getValue()));
        event.appendChild(
            getElement(document, "event_score", String.valueOf(participant.getEventPointsMap().get(entry.getKey()))));
        events.appendChild(event);
      }
      player.appendChild(getElement(document, "total_points", participant.getTotalScore().toString()));
      player.appendChild(getElement(document, "rank", participant.getRank()));
      decathlon.appendChild(player);
    }
    String outPutFilePath = getOutputPath(inputFilePath);
    writeToFile(outPutFilePath, document);
    return outPutFilePath;
  }

  private String getOutputPath(String inputPath) {
    String[] split = inputPath.split("/");
    int length = split.length;
    String fileName = split[length - 1];
    String[] nameSplits = fileName.split("\\.");
    String outputFileName = nameSplits[0] + ".xml";
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length - 1; i++) {
      sb.append(split[i]).append("/");
    }
    sb.append(outputFileName);
    return sb.toString();
  }

  private void writeToFile(String outputFilePath, Document document) throws TransformerException {
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    DOMSource domSource = new DOMSource(document);
    StreamResult streamResult = new StreamResult(new File(outputFilePath));
    transformer.transform(domSource, streamResult);
  }

  private Document getDocument() throws ParserConfigurationException {
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
    return documentBuilder.newDocument();
  }

  private Element getElement(Document document, String event2, String nameOfEvent) {
    Element event = document.createElement(event2);
    event.appendChild(document.createTextNode(nameOfEvent));
    return event;
  }

}
