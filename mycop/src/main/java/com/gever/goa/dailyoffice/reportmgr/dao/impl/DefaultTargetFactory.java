package com.gever.goa.dailyoffice.reportmgr.dao.impl;

import com.gever.goa.dailyoffice.reportmgr.dao.TargetDao;
import com.gever.goa.dailyoffice.reportmgr.dao.TargetFactory;


/**
 *
 * <p>Title:Ŀ������๤�� </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultTargetFactory extends TargetFactory{
    public DefaultTargetFactory() {
    }

    public TargetDao createTarget(String dbData){
        return new TargetDaoImpl(dbData);
    }
}
