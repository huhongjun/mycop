package com.gever.goa.dailyoffice.tools.dao;

public abstract class ToolsFactory {
  private static ToolsFactory factory;
  private static String className = "com.gever.goa.dailyoffice.tools.dao.impl.DefaultToolsFactory";

  public static synchronized ToolsFactory getInstance() {
     if (factory == null) {
       try {
         factory = (ToolsFactory) Class.forName(className).newInstance();
       }
       catch (Exception e) {
         System.err.println("Failed to load PrivilegeFactory class " +
                            className);
         e.printStackTrace();
       }
     }
     return factory;
   }
   public abstract CardcaseDao createCardcaseDao(String dbData);
   public abstract TicklerDao createTicklerDao(String dbData);
   public abstract CardcaseTypeDao createCardcaseTypeDao(String dbData);

}
