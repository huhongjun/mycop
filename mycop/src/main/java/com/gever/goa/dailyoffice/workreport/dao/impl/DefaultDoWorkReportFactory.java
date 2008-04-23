package com.gever.goa.dailyoffice.workreport.dao.impl;

/**
 * <p>Title:天行办公自动化平台 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company:天行软件 </p>
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
