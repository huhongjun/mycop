package com.gever.sysman.privilege.dao;

import com.gever.exception.db.DAOException;

import com.gever.sysman.organization.model.User;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.model.Role;

import java.sql.SQLException;

import java.util.Collection;


/**
 ����Ȩ�޷��������User�����ӿ�
 */
public interface UserDAO extends com.gever.sysman.organization.dao.UserDAO {
    //caDisa:CA����
    public String checkLoginForCA(String caDisa) throws DAOException;

    /**
    ����name,password
    @param name
    @param password
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BA8E240128
     */
    public boolean checkLogin(String name, String password)
        throws DAOException;

    /**
     ���û��ӽ�ɫɾ��
     @param user �û�ʵ��
     @param role ��ɫ
     @throws DAOException

     @throws DAOException
     @throws com.gever.exception.db.DAOException
     @roseuid 40BA8E2602DE
      */
    public void removeRole(User user, Role role) throws DAOException;

    /**
    ��ȡ�û�ӵ�е���Դ����Ȩ�޼���
    ע���轫�û���ԴȨ�޺��û���ӵ�еĽ�ɫ��ԴȨ�޺ϲ�
    @param user �û�ʵ��@return java.util.Set
    @roseuid 40BAA6440271
     */
    public Collection getResources(User user) throws DAOException;

    /**
    Ϊ�û������Դ����Ȩ��
    @param user �û�ʵ��
    @param resources ��ԴȨ�޼���
    @throws DAOException
    @roseuid 40BAA65D0290
     */
    public void addResources(User user, Collection resources)
        throws DAOException;

    /**
    ɾ���û�����Դ������Ȩ��
    @param user �û�ʵ��
    @param resource Ȩ��
    @throws DAOException
    @roseuid 40BAA67B0177
     */
    public void removeResource(User user, Resource resource)
        throws DAOException;

    /**
     * ��ҳ��ȡ���н�ɫ�û�
     * @param rid ��ɫid
     * @param start
     * @param count
     * @return
     * @throws DAOException
     */
    public Collection getRoleUsers(String rid, int start, int count)
        throws DAOException;

    /**
     * ���ݽ�ɫID��ȡ���н�ɫ�û�
     * @param rid
     * @return
     * @throws DAOException
     */
    public Collection getRoleUsers(String rid) throws DAOException;

    /**
     * ��ȡ�����û�
     * @param start
     * @param count
     * @return
     * @throws DAOException
     */
    public Collection getUsers(int start, int count) throws DAOException;

    public Collection getUsers(int start, int count, String username)
        throws DAOException;

    /**
     * ���ݴ���SQL��ѯ�û�
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
     * �����û����ж��Ƿ����û����ݱ����Ѿ����û�����
     * @param username
     * @return
     * @throws DAOException
     */
    public boolean haveUserName(String username) throws DAOException;

    public Collection getUsersForSearch(String strSQL, int start, int count)
        throws DAOException;



    //===============================================================
    //������ӣ�����JSP��ͼ�б�������
    public void setOrderby(String[] s);
    //===============================================================

  /**
 * ��չ������ͨ�����û��б��е�id���һ����¼������¼����ɫ�����û�����
 * @���ʱ�䣺2004��11��17��
 * @���ߣ�����
  */
public void addUserToRole(String[] userId,String roleId) throws DAOException,SQLException;

/**
 * ��չ������ͨ�����û��б��е�id���һ����¼������¼����ɫ�����û����С�
 * @���ʱ�䣺2004��11��17��
 * @���ߣ�����
 * @��չ���ܣ��¼���һ������roleId
 * @�޸�ʱ�䣺2004��11��24��
 * @�޸��ˣ��������Э����
 */

public void deleteUserFromRole(String[] userId,String roleId) throws DAOException,SQLException;
/**
 * ��չ������ͨ�����û��б��е�id���һ����¼������¼����ɫ�����û����к�ķ�ҳ�ı�ʾ
 * @���ʱ�䣺2004��11��30��
 * @���ߣ�����
 */

public int getRoleToUserCount(String rid) throws DAOException;

/*
 *��չ�������ύͨ���������������˽�ɫ�����û��б���õ��µ��б�
 *�޸�ʱ�䣺2004��12��1��
 *�޸����ߣ�����
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
