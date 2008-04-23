package com.gever.goa.dailyoffice.worklog.dao;

/**
 * Title: �Ŷ���־���󹤳���
 * Description: �Ŷ���־���󹤳���
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */
public abstract class TeamWorkLogSetFactory {
  private static String className =
      "com.gever.goa.dailyoffice.worklog.dao.impl.DefaultTeamWorkLogSetFactory";
  private static TeamWorkLogSetFactory factory = null;
  public TeamWorkLogSetFactory() {
  }

  /**
   *@return com.gever.sysman.privilege.dao.PrivilegeFactory
   *@roseuid 40BAB9B00167
   */
  public static synchronized TeamWorkLogSetFactory getInstance() { //��̬ģʽ����������ʵ��
    if (factory == null) {
      try {
        Class c = Class.forName(className);
        factory = (TeamWorkLogSetFactory) c.newInstance();
      }
      catch (Exception e) {
        System.err.println("Failed to load PrivilegeFactory class " + className);
        e.printStackTrace();
        return null;
      }
    }

    return factory;
  }

  public abstract TeamWorkLogSetDao createTeamWorkLogSet(String dbData);

}
