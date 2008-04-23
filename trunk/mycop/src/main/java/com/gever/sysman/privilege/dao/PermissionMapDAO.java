/*
 * �������� Ȩ�޹����У���Ӧ���ݿ�Ľӿ�
 * �������� 2004-11-25 10:53:42
 */
package com.gever.sysman.privilege.dao;

import com.gever.exception.db.DAOException;

import com.gever.sysman.privilege.model.PermissionMap;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * @author Hu.Walker
 */
public interface PermissionMapDAO {
    /**
     * �������ݿ�����ΪActionPathɾ������������������
     */
    public void clearDataBase(int node, String condition, Collection list)
        throws DAOException;

    /**
     * ͨ��ID����ĳ��ӳ���ϵ
     * @param id
     * @return
     */
    public PermissionMap findPermissionMapByID(long id)
        throws DAOException;

    /**
     * ͨ��ActionPath���Ҹ�path������ӳ���ϵ
     * @param actionPath
     * @return
     */
    public List findPermissionMapByPath(String actionPath)
        throws DAOException;

    /**
     * �����µ�ӳ���ϵ
     * @param map
     */
    public void createPermissionMap(PermissionMap map)
        throws DAOException;

    /**
     * ����ָ����ӳ���ϵ
     * @param map
     */
    public void updatePermissionMap(PermissionMap map)
        throws DAOException;

    /**
     * �������ӳ���ϵ�б�
     * @return
     * @throws DAOException
     */
    public Map getAllMap() throws DAOException;

    /**
     * �Ƿ����
     * @param map
     * @return
     * @throws DAOException
     */
    public boolean haveActionMethod(PermissionMap map)
        throws DAOException;

    /**
     * ����DDL
     * @param action
     */
    public String exportDDL(String action) throws DAOException;
}
