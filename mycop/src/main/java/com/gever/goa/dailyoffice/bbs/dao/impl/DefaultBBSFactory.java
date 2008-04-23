package com.gever.goa.dailyoffice.bbs.dao.impl;

import com.gever.goa.dailyoffice.bbs.dao.BBSDAO;
import com.gever.goa.dailyoffice.bbs.dao.BBSFactory;
import com.gever.goa.dailyoffice.bbs.dao.BBSMngDAO;
import com.gever.goa.dailyoffice.bbs.dao.BBSTopicDAO;
import com.gever.goa.dailyoffice.bbs.dao.BBSTopicListDAO;
import com.gever.goa.dailyoffice.bbs.dao.ViewTopicDAO;
import com.gever.goa.dailyoffice.bbs.dao.ViewTopiclistDAO;

public class DefaultBBSFactory extends BBSFactory {
  public DefaultBBSFactory() {
  }

  public BBSMngDAO createMngDAO(String dbData) {
    return new BBSMngDAOImpl(dbData);
  }
  public BBSDAO createDAO(String dbData) {
    return new BBSDAOImpl(dbData);
  }
  public  BBSTopicDAO createTopicDAO(String dbData){
      return new BBSTopicDAOImpl(dbData);
  }

  public  BBSTopicListDAO createTopicLisDAO(String dbData){
      return new BBSTopicListDAOImpl(dbData);
  }
  public ViewTopicDAO createViewTopicDAO(String dbData){
      return new ViewTopicDAOImpl(dbData);
  }

  public ViewTopiclistDAO createViewTopiclistDAO(String dbData){
      return new ViewTopiclistDAOImpl(dbData);
  }


}
