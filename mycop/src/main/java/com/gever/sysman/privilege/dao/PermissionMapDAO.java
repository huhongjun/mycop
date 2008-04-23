/*
 * 功能描述 权限关联中，对应数据库的接口
 * 创建日期 2004-11-25 10:53:42
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
     * 清理数据库中因为ActionPath删除而遗留的垃圾数据
     */
    public void clearDataBase(int node, String condition, Collection list)
        throws DAOException;

    /**
     * 通过ID查找某个映射关系
     * @param id
     * @return
     */
    public PermissionMap findPermissionMapByID(long id)
        throws DAOException;

    /**
     * 通过ActionPath查找该path的所有映射关系
     * @param actionPath
     * @return
     */
    public List findPermissionMapByPath(String actionPath)
        throws DAOException;

    /**
     * 创建新的映射关系
     * @param map
     */
    public void createPermissionMap(PermissionMap map)
        throws DAOException;

    /**
     * 更新指定的映射关系
     * @param map
     */
    public void updatePermissionMap(PermissionMap map)
        throws DAOException;

    /**
     * 获得所有映射关系列表
     * @return
     * @throws DAOException
     */
    public Map getAllMap() throws DAOException;

    /**
     * 是否存在
     * @param map
     * @return
     * @throws DAOException
     */
    public boolean haveActionMethod(PermissionMap map)
        throws DAOException;

    /**
     * 导出DDL
     * @param action
     */
    public String exportDDL(String action) throws DAOException;
}
