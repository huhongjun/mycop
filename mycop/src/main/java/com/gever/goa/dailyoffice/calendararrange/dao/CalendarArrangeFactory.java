package com.gever.goa.dailyoffice.calendararrange.dao;

/**
 * <p>Title: 天行办公自动化平台</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public abstract class CalendarArrangeFactory {
  private static String className = "com.gever.goa.dailyoffice.calendararrange.dao.impl.DefaultCalendarArrangeFactory";
  private static CalendarArrangeFactory factory = null;
  public CalendarArrangeFactory() {
  }
  /**
 @return com.gever.sysman.privilege.dao.PrivilegeFactory
 @roseuid 40BAB9B00167
  */
 public static synchronized CalendarArrangeFactory getInstance() {
     if (factory == null) {
         try {
             Class c = Class.forName(className);
             factory = (CalendarArrangeFactory) c.newInstance();
         } catch (Exception e) {
             System.err.println("Failed to load PrivilegeFactory class " +
                 className);
             e.printStackTrace();

             return null;
         }
     }

     return factory;
 }
 public abstract CalendarArrangeDao createCalendarArrange(String dbData);

}
