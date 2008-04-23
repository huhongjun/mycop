package com.gever.sysman.privilege.dao;

import com.gever.exception.db.DAOException;

import com.gever.sysman.organization.model.User;
import com.gever.sysman.privilege.model.I_User;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.model.Role;

import java.util.Collection;


/**
角色Role操作接口
 */
public interface RoleDAO {
    /**
    根据ID查找Role
    @param ID
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E1F0242
     */
    public Role findRoleByID(long ID) throws DAOException;

    /**
    根据name查找Role
    @param name
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E1F02A0
     */
    public Role findRoleByName(String name) throws DAOException;

    /**
    创建Role
    @param role
    @throws DAOException
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E1F030D
     */
    public void createRole(Role role) throws DAOException;

    public void createUserRole(String roleid, String userid)
        throws DAOException;

    /**
    删除Role
    @param role
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E1F036C
     */
    public void deleteRole(Role role) throws DAOException;

    /**
    根据id删除Role
    @param role
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E1F036C
         */
    public void deleteRolesByIds(String[] ids) throws DAOException;

    public void deleteUserRolesByIds(String uid, String[] ids)
        throws DAOException;

    public void deleteUserRolesByIds(String uid) throws DAOException;

    /**
    更新Role
    @param role
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E1F03D8
     */
    public void updateRole(Role role) throws DAOException;

    /**
    返回所有Role
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E20004F
     */
    public Collection getRoles() throws DAOException;

    public Collection getRolesByID(String[] roleids) throws DAOException;

    /**
    分页获取Role
    @param start
    @param count
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E2000BB
     */
    public Collection getRoles(long start, long count)
        throws DAOException;

    /**
    查询Role
    @param searchQuery
    @param start
    @param count
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E20011B
     */
    public Collection findRole(String searchQuery, int start, int count)
        throws DAOException;

    /**
    获取所有角色信息的数量
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E200196
     */
    public int getRoleCount() throws DAOException;

    /**
    获取某角色所包含的用户
    @param role 角色实例
    @return 用户集合
    @throws DAOException
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E2001E4
     */
    public Collection getUsers(Role role) throws DAOException;

    /**
    分页获取某角色所包含的用户
    @param role 角色实例
    @param start 起始记录
    @param count 记录数目
    @return 用户集合
    @throws DAOException
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E200261
     */
    public Collection getUsers(Role role, int start, int count)
        throws DAOException;

    /**
    判断某角色中是否包含某用户
    @param role
    @param user
    @return
    @throws DAOException
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E21000F
     */
    public boolean contain(Role role, I_User user) throws DAOException;

    /**
    添加多个用户到角色中
    @param role
    @param users
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E21008C
     */
    public void addUsers(Role role, Collection users) throws DAOException;

    /**
    添加用户到角色
    @param user 用户实例
    @param roles 角色集合
    @throws DAOException
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E2601C5
         */
    public void addRoles(User user, Collection roles) throws DAOException;

    /**
    将用户从角色中删除
    @param role
    @param user
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E210196
     */
    public void removeUser(Role role, I_User user) throws DAOException;

    /**
    获取角色拥有的资源操作权限集合
    @param role 角色实例@return java.util.Collection
    @roseuid 40BC76F00261
     */
    public Collection getResources(Role role) throws DAOException;

    /**
    为角色添加资源操作权限
    @param role 角色实例
    @param resources 资源权限集合
    @throws DAOException
    @roseuid 40BC76F00271
     */
    public void addResources(Role role, Collection resources)
        throws DAOException;

    /**
    删除角色对资源操作的权限
    @param role 角色实例
    @param resource 权限
    @throws DAOException
    @roseuid 40BC76F00280
     */
    public void removeResource(Role role, Resource resource)
        throws DAOException;

    /**
    获取用户所属角色
    @param user 用户实例
    @return 角色集合
    @throws DAOException
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E2600AB
         */
    public Collection getRoles(User user) throws DAOException;

    public Collection getRolesByID(String id, long start, long count)
        throws DAOException;

    public Collection getRolesByID(String id) throws DAOException;

    public Collection getOtherRolesByID(String id, long start, long count)
        throws DAOException;

    public Collection getOtherRolesByID(String id) throws DAOException;

    public int getOtherRoleCount(String id) throws DAOException;

    public int getUserRoleCount(String id) throws DAOException;

    /**
     * 根据角色名称判断角色是否已经存在
     * @param rolename
     * @return
     * @throws DAOException
     */
    public boolean haveRoleName(String rolename) throws DAOException;

    //===============================================================
    //胡勇添加，增加JSP视图列表排序功能
    public void setOrderby(String[] s);
    //===============================================================
}
