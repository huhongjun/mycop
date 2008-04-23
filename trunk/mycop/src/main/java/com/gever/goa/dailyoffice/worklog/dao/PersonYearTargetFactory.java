package com.gever.goa.dailyoffice.worklog.dao;

/**
 * Title: 人年目标抽象工厂类
 * Description: 人年目标抽象工厂类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public abstract class PersonYearTargetFactory {
  private static String className =
      "com.gever.goa.dailyoffice.worklog.dao.impl.DefaultPersonYearTargetFactory";
  private static PersonYearTargetFactory factory = null;
  public PersonYearTargetFactory() {
  }

  /**
   *@return com.gever.sysman.privilege.dao.PrivilegeFactory
   *@roseuid 40BAB9B00167
   */
  public static synchronized PersonYearTargetFactory getInstance() { //单态模式创建抽象类实例
    if (factory == null) {
      try {
        Class c = Class.forName(className);
        factory = (PersonYearTargetFactory) c.newInstance();
      }
      catch (Exception e) {
        System.err.println("Failed to load PrivilegeFactory class " + className);
        e.printStackTrace();
        return null;
      }
    }

    return factory;
  }

  public abstract PersonYearTargetDao createPersonYearTarget(String dbData);

}
