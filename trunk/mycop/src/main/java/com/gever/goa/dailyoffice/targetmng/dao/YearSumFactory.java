package com.gever.goa.dailyoffice.targetmng.dao;

/**
 * Title: ����ܽ���󹤳���
 * Description: ����ܽ���󹤳���
 * Copyright: Copyright (c) 2004
 * Company: �������
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
  public static synchronized YearSumFactory getInstance() { //��̬ģʽ����������ʵ��
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
