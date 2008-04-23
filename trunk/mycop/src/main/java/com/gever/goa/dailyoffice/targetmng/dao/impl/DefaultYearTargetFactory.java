package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.goa.dailyoffice.targetmng.dao.YearTargetDao;
import com.gever.goa.dailyoffice.targetmng.dao.YearTargetFactory;

/**
 * Title: 年度目标抽象类工厂的实现类
 * Description: 年度目标抽象类工厂的实现类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultYearTargetFactory
    extends YearTargetFactory {
  public DefaultYearTargetFactory() {
  }

  public YearTargetDao createYearTarget(String dbData) {
    return new YearTargetDaoImpl(dbData);
  }
}
