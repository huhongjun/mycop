package com.gever.goa.dailyoffice.impowermgr.dao.impl;

import com.gever.goa.dailyoffice.impowermgr.dao.DoImpowerDao;
import com.gever.goa.dailyoffice.impowermgr.dao.DoImpowerFactory;
/**
* Title: ��Ȩ��������๤����ʵ����
* Description: ��Ȩ��������๤����ʵ����
* Copyright: Copyright (c) 2004
* Company: �������
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


