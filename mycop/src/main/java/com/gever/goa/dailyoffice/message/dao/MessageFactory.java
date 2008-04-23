package com.gever.goa.dailyoffice.message.dao;

public abstract class MessageFactory {
  private static MessageFactory factory;
  private static String className =
      "com.gever.goa.dailyoffice.message.dao.impl.DefaultMessageFactory";

  public static synchronized MessageFactory getInstance() {
    if (factory == null) {
      try {
        factory = (MessageFactory) Class.forName(className).newInstance();
      }
      catch (Exception e) {
        System.err.println("Failed to load PrivilegeFactory class " +
                           className);
        e.printStackTrace();
      }
    }
    return factory;
  }

  public abstract MessageDao createMessageDao(String dbData);

}
