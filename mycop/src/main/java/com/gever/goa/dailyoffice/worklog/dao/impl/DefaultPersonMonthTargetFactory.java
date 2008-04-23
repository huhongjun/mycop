package com.gever.goa.dailyoffice.worklog.dao.impl;

import com.gever.goa.dailyoffice.worklog.dao.PersonMonthTargetDao;
import com.gever.goa.dailyoffice.worklog.dao.PersonMonthTargetFactory;

/**
 * Title: 人月目标抽象类工厂的实现类
 * Description: 人月目标抽象类工厂的实现类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultPersonMonthTargetFactory
    extends PersonMonthTargetFactory {
  public DefaultPersonMonthTargetFactory() {
  }

  public PersonMonthTargetDao createPersonMonthTarget(String dbData) {
    return new PersonMonthTargetDaoImpl(dbData);
  }
}
