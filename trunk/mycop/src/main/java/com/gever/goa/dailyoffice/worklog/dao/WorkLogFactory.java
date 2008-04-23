package com.gever.goa.dailyoffice.worklog.dao;

/**
 * <p>Title: 工作日志抽象工厂类</p>
 * <p>Description: 工作日志抽象工厂类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public abstract class WorkLogFactory {
    private static String className = "com.gever.goa.dailyoffice.worklog.dao.impl.DefaultWorkLogFactory";
    private static WorkLogFactory factory = null;
    public WorkLogFactory() {
    }
    /**
   @return com.gever.sysman.privilege.dao.PrivilegeFactory
   @roseuid 40BAB9B00167
    */
   public static synchronized WorkLogFactory getInstance() {//单态模式创建抽象类实例
       if (factory == null) {
           try {
               Class c = Class.forName(className);
               factory = (WorkLogFactory) c.newInstance();
           } catch (Exception e) {
               System.err.println("Failed to load PrivilegeFactory class " + className);
               e.printStackTrace();
               return null;
           }
       }

       return factory;
   }
   public abstract WorkLogDao createWorkLog(String dbData);
   public abstract WorkLogAdviceDAO createWorkLogAdvice(String dbData);
   public abstract WorkLogDao createIBatisWorkLog(String dbData);   

}

