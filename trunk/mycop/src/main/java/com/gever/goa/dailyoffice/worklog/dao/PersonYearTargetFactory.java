package com.gever.goa.dailyoffice.worklog.dao;

/**
 * Title: ����Ŀ����󹤳���
 * Description: ����Ŀ����󹤳���
 * Copyright: Copyright (c) 2004
 * Company: �������
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
  public static synchronized PersonYearTargetFactory getInstance() { //��̬ģʽ����������ʵ��
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
