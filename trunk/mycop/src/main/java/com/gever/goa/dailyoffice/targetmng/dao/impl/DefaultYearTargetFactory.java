package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.goa.dailyoffice.targetmng.dao.YearTargetDao;
import com.gever.goa.dailyoffice.targetmng.dao.YearTargetFactory;

/**
 * Title: ���Ŀ������๤����ʵ����
 * Description: ���Ŀ������๤����ʵ����
 * Copyright: Copyright (c) 2004
 * Company: �������
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
