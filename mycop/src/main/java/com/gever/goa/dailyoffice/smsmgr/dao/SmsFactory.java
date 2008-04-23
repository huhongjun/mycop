package com.gever.goa.dailyoffice.smsmgr.dao;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public abstract class SmsFactory {
    public SmsFactory() {
    }

    private final static String className =
        "com.gever.goa.dailyoffice.smsmgr.dao.impl.DefaultSmsFactory";
    private static SmsFactory factory = null;

    /**
     * 得到短消息的实体工厂
     * @return DAO实体工厂
     */
    public static synchronized SmsFactory getInstance() {
        if (factory == null) {
            try {
                Class c = Class.forName(className);

                factory = (SmsFactory) c.newInstance();
                System.out.println("---------"+factory.getClass().getName());
            } catch (Exception e) {
                System.err.println("Failed to load PrivilegeFactory class " +
                                   className);
                e.printStackTrace();

                return null;
            }
        }

        return factory;
    }


    /**
     * 创建手机短信管理DAO
     * @param dbData 数据源
     * @return 手机短信管理DAO的实例
     */
    public abstract SmsMgrDAO createSmsMgrDAO(String dbData);

    /**
     * 创建手机短信的产品
     * @param dbData 数据源
     * @return 手机短信产品的实例
     */

    public abstract SmsProduct createSmsProduct(String dbData);
    public abstract SMSCapacityDAO createSmsCapacityDAO(String dbData);
    public abstract SmsInBoxDAO createSmsInBoxDAO(String dbData) ;

}