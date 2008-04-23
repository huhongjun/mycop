package com.gever.goa.dailyoffice.calendararrange.dao.impl;

import com.gever.goa.dailyoffice.calendararrange.dao.CalendarArrangeDao;
import com.gever.goa.dailyoffice.calendararrange.dao.CalendarArrangeFactory;


/**
 * <p>Title: 天行办公自动化平台</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class DefaultCalendarArrangeFactory
    extends CalendarArrangeFactory {
  public DefaultCalendarArrangeFactory() {
  }

  public CalendarArrangeDao createCalendarArrange(String dbData) {
    return new CalendarArrangeDaoImpl(dbData);
  }



}
