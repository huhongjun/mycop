package com.gever.goa.dailyoffice.worklog.dao.impl;

import com.gever.goa.dailyoffice.worklog.dao.WorkLogAdviceDAO;
import com.gever.goa.dailyoffice.worklog.dao.WorkLogDao;
import com.gever.goa.dailyoffice.worklog.dao.WorkLogFactory;

/**
 * <p>Title: 工作日志抽象类工厂的实现类</p>
 * <p>Description: 工作日志抽象类工厂的实现类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
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

