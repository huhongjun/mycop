package com.gever.sysman.privilege.dao;

import com.gever.exception.db.DAOException;

import com.gever.sysman.privilege.model.Operation;
import com.gever.sysman.privilege.model.Resource;

import java.util.Collection;


/**
Operation操作接口
 */
public interface OperationDAO {
    /**
    根据id获取Operation
    @param id
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA558036B
     */
    public Operation findOperationByID(long id) throws DAOException;
    public Operation findOperationByCode(String code) throws DAOException;

    public Collection getOptByRoleid(long roleid) throws DAOException;

	public Collection getOptByUserid(long userid) throws DAOException;

	public Collection getOperationsByResid(String id) throws DAOException;
	/**
	 * 获取用户的角色权限集合（一个用户可能包含多个角色）
	 * @param uid
	 * @return
	 * @throws DAOException
	 */
	public Collection getUserRolePerm(String uid) throws DAOException;

    /**
    根据操作名获取资源的操作
    @param resource
    @param name
    @return
    更新操作
    @param operation
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA55803C9
     */
    public void updateOperation(Operation operation) throws DAOException;

    /**
    删除操作
    @param operation
    @throws Exception
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA559003F
     */
    public void deleteOperation(Operation operation) throws DAOException;

	public void deleteOperation(String[] ids) throws DAOException;


    /**
    创建Operation
    @param operation
    @throws Exception
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA55900AB
     */
    public void createOperation(Operation operation) throws DAOException;

    /**
    获取指定资源的Operation
    @param resource
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA559010A
     */
    public Collection getOperations(Resource resource)
        throws DAOException;

    /**
    获取所有的Operation
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA5590196
     */
    public Collection getOperations() throws DAOException;

    /**
    分页获取Operation
    @param start
    @param count
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA55901F4
     */
    public Collection getOperations(int start, int count)
        throws DAOException;

    /**
    查询Operation
    @param searchQuery
    @param start
    @param count
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA5590263
     */
    public Collection findOperations(String searchQuery, int start, int count)
        throws DAOException;

    /**
    获取操作总数
    @return
    @throws DAOException
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA55902DE
     */
    public int getOperationCount() throws DAOException;
    
    //===============================================================
    //胡勇添加，增加JSP视图列表排序功能
    public void setOrderby(String[] s) ;
    //===============================================================
}
