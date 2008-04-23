package com.gever.goa.dailyoffice.worklog.dao.impl;

import com.gever.goa.dailyoffice.worklog.dao.PersonWeekTargetDao;
import com.gever.goa.dailyoffice.worklog.dao.PersonWeekTargetFactory;

/**
 * Title: 人周目标抽象类工厂的实现类
 * Description: 人周目标抽象类工厂的实现类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultPersonWeekTargetFactory
    extends PersonWeekTargetFactory {
  public DefaultPersonWeekTargetFactory() {
  }

  public PersonWeekTargetDao createPersonWeekTarget(String dbData) {
    return new PersonWeekTargetDaoImpl(dbData);
  }
}
