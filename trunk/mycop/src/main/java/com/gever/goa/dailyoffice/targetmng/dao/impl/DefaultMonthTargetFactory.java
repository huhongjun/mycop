package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.goa.dailyoffice.targetmng.dao.MonthTargetDao;
import com.gever.goa.dailyoffice.targetmng.dao.MonthTargetFactory;

/**
 * Title: �¶�Ŀ������๤����ʵ����
 * Description: �¶�Ŀ������๤����ʵ����
 * Copyright: Copyright (c) 2004
 * Company: �������
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
