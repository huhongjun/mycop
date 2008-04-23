package com.gever.goa.dailyoffice.worklog.dao;

/**
 * Title: 人周目标抽象工厂类
 * Description: 人周目标抽象工厂类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public abstract class PersonWeekTargetFactory {
  private static String className =
      "com.gever.goa.dailyoffice.worklog.dao.impl.DefaultPersonWeekTargetFactory";
  private static PersonWeekTargetFactory factory = null;
  public PersonWeekTargetFactory() {
  }

  /**
   *@return com.gever.sysman.privilege.dao.PrivilegeFactory
   *@roseuid 40BAB9B00167
   */
  public static synchronized PersonWeekTargetFactory getInstance() { //单态模式创建抽象类实例
    if (factory == null) {
      try {
        Class c = Class.forName(className);
        factory = (PersonWeekTargetFactory) c.newInstance();
      }
      catch (Exception e) {
        System.err.println("Failed to load PrivilegeFactory class " + className);
        e.printStackTrace();
        return null;
      }
    }

    return factory;
  }

  public abstract PersonWeekTargetDao createPersonWeekTarget(String dbData);

}
