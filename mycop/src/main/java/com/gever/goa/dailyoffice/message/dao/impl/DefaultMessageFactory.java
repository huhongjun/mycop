package com.gever.goa.dailyoffice.message.dao.impl;

import com.gever.goa.dailyoffice.message.dao.MessageDao;
import com.gever.goa.dailyoffice.message.dao.MessageFactory;

public class DefaultMessageFactory
    extends MessageFactory {
  public DefaultMessageFactory() {
  }

  public MessageDao createMessageDao(String dbData) {
    return new MessageDaoImpl(dbData);
  }

}
