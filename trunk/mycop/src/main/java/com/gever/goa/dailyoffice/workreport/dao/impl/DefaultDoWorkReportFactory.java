package com.gever.goa.dailyoffice.workreport.dao.impl;

/**
 * <p>Title:���а칫�Զ���ƽ̨ </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company:������� </p>
 * @author Hu.Walker
 * @version 1.0
 */
import com.gever.goa.dailyoffice.workreport.dao.DoWorkReportDao;
import com.gever.goa.dailyoffice.workreport.dao.DoWorkReportFactory;

public class DefaultDoWorkReportFactory
    extends DoWorkReportFactory {
  public DefaultDoWorkReportFactory() {
  }

  public DoWorkReportDao createDoWorkReport(String dbData) {
    return new DoWorkReportDaoImpl(dbData);
  }


}
