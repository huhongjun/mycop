package com.gever.goa.dailyoffice.tools.dao.impl;

import com.gever.goa.dailyoffice.tools.dao.CardcaseDao;
import com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao;
import com.gever.goa.dailyoffice.tools.dao.TicklerDao;
import com.gever.goa.dailyoffice.tools.dao.ToolsFactory;

public class DefaultToolsFactory extends ToolsFactory {
  public DefaultToolsFactory() {
  }
  public CardcaseDao createCardcaseDao(String dbData){
    return new CardcaseDaoImpl(dbData);
  }
  public TicklerDao createTicklerDao(String dbData) {
    return new TicklerDaoImpl(dbData);
  }
  public CardcaseTypeDao createCardcaseTypeDao(String dbData){
    return new CardcaseTypeDaoImpl(dbData);
  }


}
