package com.gever.web.css.dao;

import com.gever.config.Environment;

public class UserCssDAOFactory {
  private static UserCssDAO userCssDAO = null;
  private UserCssDAOFactory() {
  }

  /**
   * 工厂产生DAO
   * @return
   */
  public static UserCssDAO getUserCssDAO() {
    String userCssDAOClassName = null;
    try {
      userCssDAOClassName = Environment.getProperty("css.UserCssDAO");
      if((userCssDAOClassName == null) ||
         "".equals(userCssDAOClassName)) {
        userCssDAOClassName = "com.gever.web.css.dao.impl.DefaultUserCssDAO";
      }
      Class c = Class.forName(userCssDAOClassName);
      userCssDAO = (UserCssDAO) c.newInstance();
    }catch (Exception e) {
        System.err.println("Failed to load UserCssDAO class " +
                           userCssDAOClassName);
        e.printStackTrace();

        return null;
      }

    return userCssDAO;
  }

}