package com.gever.goa.dailyoffice.mailmgr.dao;

import java.util.List;

import com.gever.exception.DefaultException;
/**
 *
 * <p>Title:�������DAO�ӿ� </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface MailboxMgrDAO {
    public List getTreeData(String nodedid)throws DefaultException;//��ȡ���ͽṹ�б�

}