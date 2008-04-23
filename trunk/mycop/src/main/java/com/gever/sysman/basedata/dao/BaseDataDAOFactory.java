package com.gever.sysman.basedata.dao;

import com.gever.config.Environment;

public class BaseDataDAOFactory {
  private static BaseDataDAO baseDataDAO = null;
  private BaseDataDAOFactory() {
  }

  /**
   * 工厂产生DAO
   * @return BaseDataDAO
   */
  public static BaseDataDAO getBaseDataDAO() {
    String baseDataDAOClassName = null;
    try {
      baseDataDAOClassName = Environment.getProperty("basedata.BaseDataDAO");
      if (baseDataDAOClassName == null ||
          "".equals(baseDataDAOClassName)) {
        baseDataDAOClassName = "com.gever.sysman.basedata.dao.impl.DefaultBaseDataDAO";
      }
      Class c = Class.forName(baseDataDAOClassName);
      baseDataDAO = (BaseDataDAO) c.newInstance();
    }
    catch (Exception e) {
      System.err.println("Failed to load BaseDataDAO class " +
                         baseDataDAO);
      e.printStackTrace();
      return null;
    }
    return baseDataDAO;
  }
}