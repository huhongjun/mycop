package com.gever.goa.dailyoffice.worklog.dao.impl;

import com.gever.goa.dailyoffice.worklog.dao.PersonYearTargetDao;
import com.gever.goa.dailyoffice.worklog.dao.PersonYearTargetFactory;

/**
 * Title: ����Ŀ������๤����ʵ����
 * Description: ����Ŀ������๤����ʵ����
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultPersonYearTargetFactory
    extends PersonYearTargetFactory {
  public DefaultPersonYearTargetFactory() {
  }

  public PersonYearTargetDao createPersonYearTarget(String dbData) {
    return new PersonYearTargetDaoImpl(dbData);
  }
}
