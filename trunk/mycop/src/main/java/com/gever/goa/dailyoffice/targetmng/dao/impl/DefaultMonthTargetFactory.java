package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.goa.dailyoffice.targetmng.dao.MonthTargetDao;
import com.gever.goa.dailyoffice.targetmng.dao.MonthTargetFactory;

/**
 * Title: 月度目标抽象类工厂的实现类
 * Description: 月度目标抽象类工厂的实现类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultMonthTargetFactory
    extends MonthTargetFactory {
  public DefaultMonthTargetFactory() {
  }

  public MonthTargetDao createMonthTarget(String dbData) {
    return new MonthTargetDaoImpl(dbData);
  }
}
