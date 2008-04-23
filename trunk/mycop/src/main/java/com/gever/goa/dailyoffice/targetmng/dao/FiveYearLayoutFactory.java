package com.gever.goa.dailyoffice.targetmng.dao;

/**
 * Title: 五年规划抽象工厂类
 * Description: 五年规划抽象工厂类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public abstract class FiveYearLayoutFactory {
    private static String className = "com.gever.goa.dailyoffice.targetmng.dao.impl.DefaultFiveYearLayoutFactory";
    private static FiveYearLayoutFactory factory = null;
    public FiveYearLayoutFactory() {
    }

    /**
     *@return com.gever.sysman.privilege.dao.PrivilegeFactory
     *@roseuid 40BAB9B00167
     */
    public static synchronized FiveYearLayoutFactory getInstance() { //单态模式创建抽象类实例
        if (factory == null) {
            try {
                Class c = Class.forName(className);
                factory = (FiveYearLayoutFactory) c.newInstance();
            } catch (Exception e) {
                System.err.println("Failed to load PrivilegeFactory class " + className);
                e.printStackTrace();
                return null;
            }
        }

        return factory;
    }

    public abstract FiveYearLayoutDao createFiveYearLayout(String dbData);

}
