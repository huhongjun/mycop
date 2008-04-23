package com.gever.goa.dailyoffice.calendararrange.dao.impl;

import com.gever.goa.dailyoffice.calendararrange.dao.CalendarArrangeDao;
import com.gever.goa.dailyoffice.calendararrange.dao.CalendarArrangeFactory;


/**
 * <p>Title: ���а칫�Զ���ƽ̨</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
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
