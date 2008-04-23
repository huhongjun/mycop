package com.gever.goa.dailyoffice.worklog.dao.impl;

import com.gever.goa.dailyoffice.worklog.dao.TeamWorkLogSetDao;
import com.gever.goa.dailyoffice.worklog.dao.TeamWorkLogSetFactory;

/**
 * Title: 团队日志抽象类工厂的实现类
 * Description: 团队日志抽象类工厂的实现类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
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
