package com.gever.goa.dailyoffice.targetmng.dao;

/**
 * Title: 年度总结抽象工厂类
 * Description: 年度总结抽象工厂类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public abstract class YearSumFactory {
  private static String className =
      "com.gever.goa.dailyoffice.targetmng.dao.impl.DefaultYearSumFactory";
  private static YearSumFactory factory = null;
  public YearSumFactory() {
  }

  /**
   *@return com.gever.sysman.privilege.dao.PrivilegeFactory
   *@roseuid 40BAB9B00167
   */
  public static synchronized YearSumFactory getInstance() { //单态模式创建抽象类实例
    if (factory == null) {
      try {
        Class c = Class.forName(className);
        factory = (YearSumFactory) c.newInstance();
      }
      catch (Exception e) {
        System.err.println("Failed to load PrivilegeFactory class " + className);
        e.printStackTrace();
        return null;
      }
    }

    return factory;
  }

  public abstract YearSumDao createYearSum(String dbData);

}
