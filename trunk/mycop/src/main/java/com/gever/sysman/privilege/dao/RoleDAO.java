package com.gever.sysman.privilege.dao;

import com.gever.exception.db.DAOException;

import com.gever.sysman.organization.model.User;
import com.gever.sysman.privilege.model.I_User;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.model.Role;

import java.util.Collection;


/**
��ɫRole�����ӿ�
 */
public interface RoleDAO {
    /**
    ����ID����Role
    @param ID
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E1F0242
     */
    public Role findRoleByID(long ID) throws DAOException;

    /**
    ����name����Role
    @param name
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E1F02A0
     */
    public Role findRoleByName(String name) throws DAOException;

    /**
    ����Role
    @param role
    @throws DAOException
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E1F030D
     */
    public void createRole(Role role) throws DAOException;

    public void createUserRole(String roleid, String userid)
        throws DAOException;

    /**
    ɾ��Role
    @param role
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E1F036C
     */
    public void deleteRole(Role role) throws DAOException;

    /**
    ����idɾ��Role
    @param role
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E1F036C
         */
    public void deleteRolesByIds(String[] ids) throws DAOException;

    public void deleteUserRolesByIds(String uid, String[] ids)
        throws DAOException;

    public void deleteUserRolesByIds(String uid) throws DAOException;

    /**
    ����Role
    @param role
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E1F03D8
     */
    public void updateRole(Role role) throws DAOException;

    /**
    ��������Role
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E20004F
     */
    public Collection getRoles() throws DAOException;

    public Collection getRolesByID(String[] roleids) throws DAOException;

    /**
    ��ҳ��ȡRole
    @param start
    @param count
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E2000BB
     */
    public Collection getRoles(long start, long count)
        throws DAOException;

    /**
    ��ѯRole
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
    ��ȡ���н�ɫ��Ϣ������
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E200196
     */
    public int getRoleCount() throws DAOException;

    /**
    ��ȡĳ��ɫ���������û�
    @param role ��ɫʵ��
    @return �û�����
    @throws DAOException
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E2001E4
     */
    public Collection getUsers(Role role) throws DAOException;

    /**
    ��ҳ��ȡĳ��ɫ���������û�
    @param role ��ɫʵ��
    @param start ��ʼ��¼
    @param count ��¼��Ŀ
    @return �û�����
    @throws DAOException
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E200261
     */
    public Collection getUsers(Role role, int start, int count)
        throws DAOException;

    /**
    �ж�ĳ��ɫ���Ƿ����ĳ�û�
    @param role
    @param user
    @return
    @throws DAOException
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E21000F
     */
    public boolean contain(Role role, I_User user) throws DAOException;

    /**
    ��Ӷ���û�����ɫ��
    @param role
    @param users
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E21008C
     */
    public void addUsers(Role role, Collection users) throws DAOException;

    /**
    ����û�����ɫ
    @param user �û�ʵ��
    @param roles ��ɫ����
    @throws DAOException
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E2601C5
         */
    public void addRoles(User user, Collection roles) throws DAOException;

    /**
    ���û��ӽ�ɫ��ɾ��
    @param role
    @param user
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E210196
     */
    public void removeUser(Role role, I_User user) throws DAOException;

    /**
    ��ȡ��ɫӵ�е���Դ����Ȩ�޼���
    @param role ��ɫʵ��@return java.util.Collection
    @roseuid 40BC76F00261
     */
    public Collection getResources(Role role) throws DAOException;

    /**
    Ϊ��ɫ�����Դ����Ȩ��
    @param role ��ɫʵ��
    @param resources ��ԴȨ�޼���
    @throws DAOException
    @roseuid 40BC76F00271
     */
    public void addResources(Role role, Collection resources)
        throws DAOException;

    /**
    ɾ����ɫ����Դ������Ȩ��
    @param role ��ɫʵ��
    @param resource Ȩ��
    @throws DAOException
    @roseuid 40BC76F00280
     */
    public void removeResource(Role role, Resource resource)
        throws DAOException;

    /**
    ��ȡ�û�������ɫ
    @param user �û�ʵ��
    @return ��ɫ����
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
     * ���ݽ�ɫ�����жϽ�ɫ�Ƿ��Ѿ�����
     * @param rolename
     * @return
     * @throws DAOException
     */
    public boolean haveRoleName(String rolename) throws DAOException;

    //===============================================================
    //������ӣ�����JSP��ͼ�б�������
    public void setOrderby(String[] s);
    //===============================================================
}
