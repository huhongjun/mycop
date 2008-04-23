package com.gever.goa.dailyoffice.worklog.dao.impl;

import com.gever.goa.dailyoffice.worklog.dao.WorkLogAdviceDAO;
import com.gever.goa.dailyoffice.worklog.dao.WorkLogDao;
import com.gever.goa.dailyoffice.worklog.dao.WorkLogFactory;

/**
 * <p>Title: ������־�����๤����ʵ����</p>
 * <p>Description: ������־�����๤����ʵ����</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DefaultWorkLogFactory extends WorkLogFactory{
    public DefaultWorkLogFactory() {
    }
    public WorkLogDao createWorkLog(String dbData){
        return new WorkLogDaoImpl(dbData);
    }
    
    public WorkLogDao createIBatisWorkLog(String dbData){
        return new IBatisWorkLogDaoImpl(dbData);
    }
    
    public WorkLogAdviceDAO createWorkLogAdvice(String dbData){
         return new WorkLogAdviceDAOImpl(dbData);
     }
}

