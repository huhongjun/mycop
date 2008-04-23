package com.gever.goa.infoservice.dao.impl;

import com.gever.goa.infoservice.dao.IsAddressListDao;
import com.gever.goa.infoservice.dao.IsCustomerDao;
import com.gever.goa.infoservice.dao.IsCustomerFactory;
import com.gever.goa.infoservice.dao.IsCustomerTouchDao;
import com.gever.goa.infoservice.dao.IsDoTypeDao;
import com.gever.goa.infoservice.dao.IsInfoManageDao;
import com.gever.goa.infoservice.dao.IsInfoServeDao;
import com.gever.goa.infoservice.dao.IsStandardModelDao;
import com.gever.goa.infoservice.dao.SysInfoTypeDao;

/**
 * <p>Title: 信息服务工厂实现类</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DefaultIsCustomerFactory
    extends IsCustomerFactory {
    public DefaultIsCustomerFactory() {
    }

    public IsCustomerDao createIsCustomer(String dbData) {
        return new IsCustomerDaoImpl(dbData);
    }

    public IsCustomerTouchDao createIsCustomerTouch(String dbData) {
        return new IsCustomerTouchDaoImpl(dbData);
    }

    public SysInfoTypeDao createSysInfoType(String dbData) {
        return new SysInfoTypeDaoImpl(dbData);
    }

    public IsInfoServeDao createIsInfoServe(String dbData) {
        return new IsInfoServeDaoImpl(dbData);
    }

    public IsDoTypeDao createIsDoType(String dbData) {
        return new IsDoTypeDaoImpl(dbData);
    }

    public IsInfoManageDao createIsInfoManage(String dbData) {
        return new IsInfoManageDaoImpl(dbData);
    }

    public IsStandardModelDao createIsStandardModel(String dbData) {
        return new IsStandardModelDaoImpl(dbData);
    }

    public IsAddressListDao createIsAddressList(String dbData) {
        return new IsAddressListDaoImpl(dbData);
    }
}