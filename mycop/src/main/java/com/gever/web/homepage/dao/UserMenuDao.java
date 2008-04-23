package com.gever.web.homepage.dao;

import java.util.*;
import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;

import org.w3c.dom.*;
/**
 * <p>Title: �û�����˵�����</p>
 * <p>Description: ����ȡ���ϼ��������ý�������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author 
 * @version 0.5
 */

public interface UserMenuDao {

    /**
     * ������û����еĲ˵�����
     * @param userID �û�ID��
     * @return �˵��б�
     * @throws DefaultException
     */
    public List queryAll(String userID) throws DAOException;

    /**
     * ������û������в˵�(�����˵�(������)����Ҫ��һ�������˵�,
     * �ͻ�����ʱ��Ҫxml�ĵ��е�Document����)
     * @param userID �û�ID��
     * @return Document����(�˵�)
     * @throws DefaultException
     */
    public Document queryMenus(String userID) throws DAOException;

    /**
     * ����ӿ�,���û����¸��Ĺ�Ȩ��ʱ
     * @param userID �û�ID��
     * @param moduleList ��ǰ�û���Ӧ��Դ����
     * @return ���ò˵��Ƿ�ɹ�
     * @throws DefaultException
     */
    public boolean resetUserMenus(String userID, Collection moduleList) throws  DAOException;

    /**
     * ����Ƿ���Ȩ��
     * @param userID ��ǰ�û�ID��
     * @return �Ƿ���Ȩ��
     * @throws DAOException
     */
    public boolean isHaveAcl(String userID) throws DAOException;
}