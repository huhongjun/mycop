package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.goa.dailyoffice.targetmng.dao.WeekTargetDao;
import com.gever.goa.dailyoffice.targetmng.dao.WeekTargetFactory;

/**
 * Title: 周目标抽象类工厂的实现类
 * Description: 周目标抽象类工厂的实现类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultWeekTargetFactory
    extends WeekTargetFactory {
  public DefaultWeekTargetFactory() {
  }

  public WeekTargetDao createWeekTarget(String dbData) {
    return new WeekTargetDaoImpl(dbData);
  }
}
