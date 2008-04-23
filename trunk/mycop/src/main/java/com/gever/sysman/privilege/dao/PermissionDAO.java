/*
 * 创建日期 2004-6-25
 *
 */
package com.gever.sysman.privilege.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.gever.exception.db.DAOException;
import com.gever.sysman.privilege.model.Resource;

/**
 * @author Hu.Walker
 *  
 */
public interface PermissionDAO {
    /**
     * 根据操作集合和当前角色的操作权限，设置操作集合的一个属性（是否有操作权限）
     * 
     * @param opt_collect
     *            所有操作的集合
     * @param role_res_opt_collect
     *            角色权限集合
     * @return
     */
    public abstract Collection returnOpts(Collection opt_collect,
            Collection role_res_opt_collect);

    /**
     * 将各操作对应到相应的资源下面
     * 
     * @param res_collect
     * @param opt_isOpt_collect
     * @return
     */
    public abstract Collection returnRess(Collection res_collect,
            Collection opt_isOpt_collect);

    public abstract Collection getChildNodes(Collection node_collect,
            long node_id);

    public abstract Resource getParentNodes(Collection root, long node_pid);

    public abstract Resource getRoot(Collection root);

    /**
     * 根据资源列表处理输出权限列表菜单
     * 
     * @param res_collect
     * @return
     */
    public abstract String process(Collection res_collect);

    public String user_process(Collection res_collect);

    /**
     * 递归产生资源树型列表
     */
    public abstract void resTree(Collection res_collect,
            Collection node_child_collect, Resource res_node,
            StringBuffer result);

    public abstract String space(int i);

    public abstract void savaRolePerm(String roleid,
            Collection role_perm_collect) throws DAOException, SQLException;

    public void savaUserPerm(String userid, Collection user_perm_collect)
            throws DAOException, SQLException;

    public abstract Collection getUserPerm(String userid, String[] res_opt);

    public abstract Collection getRolePerm(String roleid, String[] res_opt);

    /**
     * 获取用户权限和角色权限和并后的集合
     * 
     * @param userID
     * @param userOpts
     * @param roleOpts
     * @return
     */
    public abstract Collection getUserAndRolePerm(long userID,
            Collection userOpts, Collection roleOpts);

    /**
     * 根据用户的的权限（包含角色的），设置资源操作的一个属性（是否有操作权限）
     * 
     * @param opt_collect
     *            所有操作的集合
     * @param user_res_opt_collect
     *            用户权限集合
     * @return
     */
    public abstract Collection returnUserOpts(Collection opt_collect,
            Collection user_res_opt_collect);

    public void resetMenuRes(String userid, Collection userPerm)
            throws DAOException;

    public Collection getResCollect(Collection userPerm) throws DAOException;

    public java.util.Set getAllResources(Collection resources)
            throws DAOException;

    public String[] getUserPerm(long userID) throws DAOException, SQLException;

    public Collection getResCollectByRole(Collection rolePerm)
            throws DAOException;
    
    /**
     * 删除用户时删除用户权限关联数据
     * @author Hu.Walker
     */
    public void delUserPerm(String[] userId) throws DAOException, SQLException;
}