package com.gever.goa.dailyoffice.worklog.dao;

import java.util.List;

import com.gever.exception.DefaultException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: GEVER</p>
 * @author Hu.Walker
 * @version 0.9
 */

public interface WorkLogAdviceDAO {
    public void saveAdvice(String string, String adviser, String words, String actionType)throws
        DefaultException ;

    public List getAdviceListByLogId(String usercode)throws
        DefaultException ;

    public String getAdviceWordsByLogIdAndAdviser(String serialNumber,
                                                  String curUser)throws
        DefaultException ;
}