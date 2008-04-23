package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.goa.dailyoffice.targetmng.dao.YearSumDao;
import com.gever.goa.dailyoffice.targetmng.dao.YearSumFactory;

/**
 * Title: ����ܽ�����๤����ʵ����
 * Description: ����ܽ�����๤����ʵ����
 * Copyright: Copyright (c) 2004
 * Company: �������
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
