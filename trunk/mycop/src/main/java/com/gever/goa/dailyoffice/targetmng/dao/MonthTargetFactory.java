package com.gever.goa.dailyoffice.targetmng.dao;

/**
 * Title: 月度目标抽象工厂类
 * Description: 月度目标抽象工厂类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public abstract class MonthTargetFactory {
  private static String className =
      "com.gever.goa.dailyoffice.targetmng.dao.impl.DefaultMonthTargetFactory";
  private static MonthTargetFactory factory = null;
  public MonthTargetFactory() {
  }

  /**
   *@return com.gever.sysman.privilege.dao.PrivilegeFactory
   *@roseuid 40BAB9B00167
   */
  public static synchronized MonthTargetFactory getInstance() { //单态模式创建抽象类实例
    if (factory == null) {
      try {
        Class c = Class.forName(className);
        factory = (MonthTargetFactory) c.newInstance();
      }
      catch (Exception e) {
        System.err.println("Failed to load PrivilegeFactory class " + className);
        e.printStackTrace();
        return null;
      }
    }

    return factory;
  }

  public abstract MonthTargetDao createMonthTarget(String dbData);

}
