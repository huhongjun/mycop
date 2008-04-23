package com.gever.goa.dailyoffice.worklog.dao.impl;

import com.gever.goa.dailyoffice.worklog.dao.PersonYearTargetDao;
import com.gever.goa.dailyoffice.worklog.dao.PersonYearTargetFactory;

/**
 * Title: 人年目标抽象类工厂的实现类
 * Description: 人年目标抽象类工厂的实现类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultPersonYearTargetFactory
    extends PersonYearTargetFactory {
  public DefaultPersonYearTargetFactory() {
  }

  public PersonYearTargetDao createPersonYearTarget(String dbData) {
    return new PersonYearTargetDaoImpl(dbData);
  }
}
