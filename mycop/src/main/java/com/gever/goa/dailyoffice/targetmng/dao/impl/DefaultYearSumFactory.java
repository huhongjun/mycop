package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.goa.dailyoffice.targetmng.dao.YearSumDao;
import com.gever.goa.dailyoffice.targetmng.dao.YearSumFactory;

/**
 * Title: 年度总结抽象类工厂的实现类
 * Description: 年度总结抽象类工厂的实现类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultYearSumFactory
    extends YearSumFactory {
  public DefaultYearSumFactory() {
  }

  public YearSumDao createYearSum(String dbData) {
    return new YearSumDaoImpl(dbData);
  }
}
