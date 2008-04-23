package com.gever.goa.dailyoffice.reportmgr.dao;

/**
 *
 * <p>Title: 目标管理类工厂构造器</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public abstract class TargetFactory {
    //类名
    private static String CLASS_NAME="com.gever.goa.dailyoffice.reportmgr.dao.impl.DefaultTargetFactory";
    private static TargetFactory factory=null;
    public TargetFactory() {
    }
    /**
     * 得到类工厂实例
     * @return DefaultTargetFactory
     */
    public static synchronized TargetFactory getInstance(){
        try {
            if(factory==null){
                factory = (TargetFactory) Class.forName(
                    CLASS_NAME).newInstance();
            }
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Fail load the class...");
        }
        catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        catch (InstantiationException ex) {
            ex.printStackTrace();
        }
        return factory;
    }
    public abstract TargetDao createTarget(String dbData);


}
