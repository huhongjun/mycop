package com.gever.goa.dailyoffice.impowermgr.dao.impl;

import com.gever.goa.dailyoffice.impowermgr.dao.DoImpowerDao;
import com.gever.goa.dailyoffice.impowermgr.dao.DoImpowerFactory;
/**
* Title: 授权管理抽象类工厂的实现类
* Description: 授权管理抽象类工厂的实现类
* Copyright: Copyright (c) 2004
* Company: 天行软件
* @author Hu.Walker
* @version 1.0
*/
public class DefaultDoImpowerFactory extends DoImpowerFactory{
public DefaultDoImpowerFactory() {
}
public DoImpowerDao createDoImpower(String dbData){
return new DoImpowerDaoImpl(dbData);
}
}


