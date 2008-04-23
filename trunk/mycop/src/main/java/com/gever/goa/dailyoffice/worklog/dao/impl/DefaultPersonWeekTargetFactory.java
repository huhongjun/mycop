package com.gever.goa.dailyoffice.worklog.dao.impl;

import com.gever.goa.dailyoffice.worklog.dao.PersonWeekTargetDao;
import com.gever.goa.dailyoffice.worklog.dao.PersonWeekTargetFactory;

/**
 * Title: ����Ŀ������๤����ʵ����
 * Description: ����Ŀ������๤����ʵ����
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultPersonWeekTargetFactory
    extends PersonWeekTargetFactory {
  public DefaultPersonWeekTargetFactory() {
  }

  public PersonWeekTargetDao createPersonWeekTarget(String dbData) {
    return new PersonWeekTargetDaoImpl(dbData);
  }
}
