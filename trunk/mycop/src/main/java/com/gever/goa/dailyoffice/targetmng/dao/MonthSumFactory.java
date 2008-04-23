package com.gever.goa.dailyoffice.targetmng.dao;

/**
 * Title: �¶��ܽ���󹤳���
 * Description: �¶��ܽ���󹤳���
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */
public abstract class MonthSumFactory {
  private static String className =
      "com.gever.goa.dailyoffice.targetmng.dao.impl.DefaultMonthSumFactory";
  private static MonthSumFactory factory = null;
  public MonthSumFactory() {
  }

  /**
   *@return com.gever.sysman.privilege.dao.PrivilegeFactory
   *@roseuid 40BAB9B00167
   */
  public static synchronized MonthSumFactory getInstance() { //��̬ģʽ����������ʵ��
    if (factory == null) {
      try {
        Class c = Class.forName(className);
        factory = (MonthSumFactory) c.newInstance();
      }
      catch (Exception e) {
        System.err.println("Failed to load PrivilegeFactory class " + className);
        e.printStackTrace();
        return null;
      }
    }

    return factory;
  }

  public abstract MonthSumDao createMonthSum(String dbData);

}
