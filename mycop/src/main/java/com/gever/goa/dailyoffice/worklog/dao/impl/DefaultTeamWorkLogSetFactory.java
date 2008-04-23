package com.gever.goa.dailyoffice.worklog.dao.impl;

import com.gever.goa.dailyoffice.worklog.dao.TeamWorkLogSetDao;
import com.gever.goa.dailyoffice.worklog.dao.TeamWorkLogSetFactory;

/**
 * Title: �Ŷ���־�����๤����ʵ����
 * Description: �Ŷ���־�����๤����ʵ����
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultTeamWorkLogSetFactory
    extends TeamWorkLogSetFactory {
  public DefaultTeamWorkLogSetFactory() {
  }

  public TeamWorkLogSetDao createTeamWorkLogSet(String dbData) {
    return new TeamWorkLogSetDaoImpl(dbData);
  }
}
