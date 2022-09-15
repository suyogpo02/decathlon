package com.kuehne.nagel.sport;

import com.kuehne.nagel.sport.model.Participant;
import com.kuehne.nagel.sport.model.event.EventName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestUtil {

  private static Map<String, EventName> EVENT_MAP;

  static {
    EVENT_MAP = Arrays.stream(EventName.values())
        .collect(Collectors.toMap(EventName::getNameOfEvent, eventName -> eventName));
  }

  public static List<Participant> readFile(String filePath) throws Exception {
    File file = new File(filePath);
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(file);
    NodeList nodeList = document.getElementsByTagName("participant");
    List<Participant> outParticipantList = new ArrayList<>();
    for (int index = 0; index < nodeList.getLength(); index++) {
      outParticipantList.add(buildParticipant(nodeList.item(index)));
    }
    return outParticipantList;
  }

  private static Participant buildParticipant(Node node) {
    Element element = (Element)node;
    String name = element.getElementsByTagName("name").item(0).getTextContent();
    NodeList events = ((Element)element.getElementsByTagName("events").item(0)).getElementsByTagName("event");
    Participant participant = new Participant(name, new HashMap<>());
    for (int ie = 0; ie < events.getLength(); ie++) {
      Element eventElement = (Element)events.item(ie);
      EventName eventName = EVENT_MAP.get(eventElement.getElementsByTagName("event_name").item(0).getTextContent());
      participant.addToMap(eventName,
          Integer.parseInt(eventElement.getElementsByTagName("event_score").item(0).getTextContent()));
      participant.addToEventPerformanceMap(eventName,
          eventElement.getElementsByTagName("performance").item(0).getTextContent());
    }

    participant.setTotalScore(Integer.parseInt(element.getElementsByTagName("total_points").item(0).getTextContent()));
    participant.setRank(element.getElementsByTagName("rank").item(0).getTextContent());
    return participant;
  }

}
