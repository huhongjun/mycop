package com.gever.goa.infoservice.dao;

/**
 * <p>Title: 信息服务工厂接口类</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public abstract class IsCustomerFactory {

    private static String className =
        "com.gever.goa.infoservice.dao.impl.DefaultIsCustomerFactory";
    private static IsCustomerFactory factory = null;

    public IsCustomerFactory() {
    }

    public static synchronized IsCustomerFactory getInstance() {
        if (factory == null) {
            try {
                Class c = Class.forName(className);
                factory = (IsCustomerFactory) c.newInstance();
            } catch (Exception e) {
                System.err.println("Failed to load IsCustomerFactory class " +
                                   className);
                e.printStackTrace();
                return null;
            }
        }

        return factory;
    }

    public abstract IsCustomerDao createIsCustomer(String dbData);

    public abstract IsCustomerTouchDao createIsCustomerTouch(String dbData);

    public abstract SysInfoTypeDao createSysInfoType(String dbData);

    public abstract IsInfoServeDao createIsInfoServe(String dbData);

    public abstract IsDoTypeDao createIsDoType(String dbData);

    public abstract IsInfoManageDao createIsInfoManage(String dbData);

    public abstract IsStandardModelDao createIsStandardModel(String dbData);

    public abstract IsAddressListDao createIsAddressList(String dbData);
}