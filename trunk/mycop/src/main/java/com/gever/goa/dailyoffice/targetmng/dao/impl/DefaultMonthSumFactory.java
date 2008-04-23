package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.goa.dailyoffice.targetmng.dao.MonthSumDao;
import com.gever.goa.dailyoffice.targetmng.dao.MonthSumFactory;

/**
 * Title: 月度总结抽象类工厂的实现类
 * Description: 月度总结抽象类工厂的实现类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultMonthSumFactory
    extends MonthSumFactory {
  public DefaultMonthSumFactory() {
  }

  public MonthSumDao createMonthSum(String dbData) {
    return new MonthSumDaoImpl(dbData);
  }
}
