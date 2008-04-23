package com.gever.goa.dailyoffice.impowermgr.dao;
/**
* Title: 授权管理抽象工厂类
* Description: 授权管理抽象工厂类
* Copyright: Copyright (c) 2004
* Company: 天行软件
* @author Hu.Walker
* @version 1.0
*/
public abstract class DoImpowerFactory {
private static String className = "com.gever.goa.dailyoffice.impowermgr.dao.impl.DefaultDoImpowerFactory";
private static DoImpowerFactory factory = null;
public DoImpowerFactory() {
}
/**
*@return com.gever.sysman.privilege.dao.PrivilegeFactory
*@roseuid 40BAB9B00167
*/
public static synchronized DoImpowerFactory getInstance() {//单态模式创建抽象类实例
if (factory == null) {
try {
Class c = Class.forName(className);
factory = (DoImpowerFactory) c.newInstance();
} catch (Exception e) {
System.err.println("Failed to load PrivilegeFactory class " + className);
e.printStackTrace();
return null;
}
}

return factory;
}
public abstract DoImpowerDao createDoImpower(String dbData);

}
