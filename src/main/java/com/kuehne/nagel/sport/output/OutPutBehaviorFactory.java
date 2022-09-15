package com.kuehne.nagel.sport.output;

public class OutPutBehaviorFactory {

  private static final XmlOutputBehaviour xmlOutputBehaviour = new XmlOutputBehaviour();

  private OutPutBehaviorFactory() {
  }

  public static OutputBehaviour getBehavior(OutputFormat format) {
    OutputBehaviour behaviour = null;
    if (format == OutputFormat.XML) {
      behaviour = xmlOutputBehaviour;
    }
    return behaviour;
  }

}
