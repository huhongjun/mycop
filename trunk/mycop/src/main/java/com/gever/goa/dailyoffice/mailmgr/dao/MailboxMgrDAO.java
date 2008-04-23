package com.gever.goa.dailyoffice.mailmgr.dao;

import java.util.List;

import com.gever.exception.DefaultException;
/**
 *
 * <p>Title:邮箱管理DAO接口 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface MailboxMgrDAO {
    public List getTreeData(String nodedid)throws DefaultException;//获取树型结构列表

}