package com.gever.sysman.privilege.dao;

import com.gever.exception.db.DAOException;

import com.gever.sysman.organization.model.User;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.model.Role;

import java.sql.SQLException;

import java.util.Collection;


/**
 关于权限方面操作的User操作接口
 */
public interface UserDAO extends com.gever.sysman.organization.dao.UserDAO {
    //caDisa:CA卡号
    public String checkLoginForCA(String caDisa) throws DAOException;

    /**
    检验name,password
    @param name
    @param password
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E240128
     */
    public boolean checkLogin(String name, String password)
        throws DAOException;

    /**
     将用户从角色删除
     @param user 用户实例
     @param role 角色
     @throws DAOException

     @throws DAOException
     @throws com.gever.exception.db.DAOException
     @roseuid 40BA8E2602DE
      */
    public void removeRole(User user, Role role) throws DAOException;

    /**
    获取用户拥有的资源操作权限集合
    注：需将用户资源权限和用户所拥有的角色资源权限合并
    @param user 用户实例@return java.util.Set
    @roseuid 40BAA6440271
     */
    public Collection getResources(User user) throws DAOException;

    /**
    为用户添加资源操作权限
    @param user 用户实例
    @param resources 资源权限集合
    @throws DAOException
    @roseuid 40BAA65D0290
     */
    public void addResources(User user, Collection resources)
        throws DAOException;

    /**
    删除用户对资源操作的权限
    @param user 用户实例
    @param resource 权限
    @throws DAOException
    @roseuid 40BAA67B0177
     */
    public void removeResource(User user, Resource resource)
        throws DAOException;

    /**
     * 分页获取所有角色用户
     * @param rid 角色id
     * @param start
     * @param count
     * @return
     * @throws DAOException
     */
    public Collection getRoleUsers(String rid, int start, int count)
        throws DAOException;

    /**
     * 根据角色ID获取所有角色用户
     * @param rid
     * @return
     * @throws DAOException
     */
    public Collection getRoleUsers(String rid) throws DAOException;

    /**
     * 获取所有用户
     * @param start
     * @param count
     * @return
     * @throws DAOException
     */
    public Collection getUsers(int start, int count) throws DAOException;

    public Collection getUsers(int start, int count, String username)
        throws DAOException;

    /**
     * 根据传入SQL查询用户
     * @param inputSQL String
     * @param start int
     * @param count int
     * @throws DAOException
     * @return Collection
     */
    public Collection getUsers(String inputSQL, int start, int count)
        throws DAOException;

    public int getRoleUserCount(String id) throws DAOException;

    /**
     * 根据用户名判断是否在用户数据表中已经有用户存在
     * @param username
     * @return
     * @throws DAOException
     */
    public boolean haveUserName(String username) throws DAOException;

    public Collection getUsersForSearch(String strSQL, int start, int count)
        throws DAOException;



    //===============================================================
    //胡勇添加，增加JSP视图列表排序功能
    public void setOrderby(String[] s);
    //===============================================================

  /**
 * 扩展方法：通过将用户列表中的id添加一条记录或多个记录到角色分配用户表中
 * @完成时间：2004年11月17日
 * @作者：方晓
  */
public void addUserToRole(String[] userId,String roleId) throws DAOException,SQLException;

/**
 * 扩展方法：通过将用户列表中的id添加一条记录或多个记录到角色分配用户表中。
 * @完成时间：2004年11月17日
 * @作者：方晓
 * @扩展功能：新加了一个参数roleId
 * @修改时间：2004年11月24日
 * @修改人：方晓（杨帆协助）
 */

public void deleteUserFromRole(String[] userId,String roleId) throws DAOException,SQLException;
/**
 * 扩展方法：通过将用户列表中的id添加一条记录或多个记录到角色分配用户表中后的分页的表示
 * @完成时间：2004年11月30日
 * @作者：方晓
 */

public int getRoleToUserCount(String rid) throws DAOException;

/*
 *扩展方法：提交通过行政级别来过滤角色分配用户列表而得到新的列表。
 *修改时间：2004年12月1日
 *修改作者：方晓
*/
public Collection findUserInRolesByLevel(int start,int count,String rid,String level)
   throws
 DAOException ;
public int getRoleToUserCountByLevel(String rid,String level ) throws DAOException;


public Collection getUsersInRoles(String rid) throws
 DAOException;

public Collection findUserInRolesByLevel(String rid, String level) throws
 DAOException;

public Collection getSelectUsersInRoles(String rid) throws
 DAOException;

public Collection findSelectUserInRolesByLevel(String rid, String level) throws
 DAOException;

public void addUsers(Role role, Collection users) throws DAOException;

public Collection getUsersByID(String[] userids) throws DAOException;


 public void deleteRoleUserByIds(String rid) throws DAOException;

}
