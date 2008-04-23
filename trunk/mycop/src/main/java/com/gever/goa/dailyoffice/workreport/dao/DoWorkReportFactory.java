package com.gever.goa.dailyoffice.workreport.dao;

/**
 * <p>Title: ���а칫�Զ���ƽ̨</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public abstract class DoWorkReportFactory {
  private static String className = "com.gever.goa.dailyoffice.workreport.dao.impl.DefaultDoWorkReportFactory";
 private static DoWorkReportFactory factory = null;
 public DoWorkReportFactory() {
 }
 /**
@return com.gever.sysman.privilege.dao.PrivilegeFactory
@roseuid 40BAB9B00167
 */
public static synchronized DoWorkReportFactory getInstance() {
    if (factory == null) {
        try {
            Class c = Class.forName(className);
            factory = (DoWorkReportFactory) c.newInstance();
        } catch (Exception e) {
            System.err.println("Failed to load PrivilegeFactory class " +
                className);
            e.printStackTrace();

            return null;
        }
    }

    return factory;
}
public abstract DoWorkReportDao createDoWorkReport(String dbData);

}


