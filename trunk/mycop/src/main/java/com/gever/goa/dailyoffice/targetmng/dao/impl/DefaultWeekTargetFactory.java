package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.goa.dailyoffice.targetmng.dao.WeekTargetDao;
import com.gever.goa.dailyoffice.targetmng.dao.WeekTargetFactory;

/**
 * Title: ��Ŀ������๤����ʵ����
 * Description: ��Ŀ������๤����ʵ����
 * Copyright: Copyright (c) 2004
 * Company: �������
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
