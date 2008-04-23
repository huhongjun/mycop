package com.gever.goa.dailyoffice.worklog.dao.impl;

import com.gever.goa.dailyoffice.worklog.dao.PersonMonthTargetDao;
import com.gever.goa.dailyoffice.worklog.dao.PersonMonthTargetFactory;

/**
 * Title: ����Ŀ������๤����ʵ����
 * Description: ����Ŀ������๤����ʵ����
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultPersonMonthTargetFactory
    extends PersonMonthTargetFactory {
  public DefaultPersonMonthTargetFactory() {
  }

  public PersonMonthTargetDao createPersonMonthTarget(String dbData) {
    return new PersonMonthTargetDaoImpl(dbData);
  }
}
