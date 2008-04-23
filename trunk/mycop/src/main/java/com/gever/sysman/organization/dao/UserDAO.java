package com.gever.sysman.organization.dao;

import com.gever.sysman.organization.model.User;
import com.gever.sysman.organization.model.Department;
import com.gever.exception.db.DAOException;

import java.util.Collection;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: KOBE OFFICE
 * </p>
 * 
 * @author Hu.Walker
 * @version 1.0
 */

public interface UserDAO {
  /**
   * author libiao add 2004-12-2
   * 
   * @throws DAOException
   * @return Collection
   */

   public Collection getAllUserName() throws DAOException;
   public Collection getAllLevel() throws DAOException;

  /**
   * ����User
   * 
   * @param User
   *            �û�ʵ��
   * @param user
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A00CC
   */
  public void createUser(User user) throws DAOException;

  /**
   * ����User
   * 
   * @param User
   *            �û�ʵ��
   * @param user
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A0128
   */
  public void updateUser(User user) throws DAOException;

  /**
   * ɾ��User
   * 
   * @param user
   *            �û�ʵ��
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A0178
   */
  public void deleteUser(String[] userId) throws DAOException;

  /**
   * �����û�ID��ȡUserʵ��
   * 
   * @param id
   *            �û�ID
   * @return �û�ʵ��
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A01C6
   */
  public User findUserByID(long id) throws DAOException;

  public User findUserByUserName(String userName) throws DAOException;

  /**
   * ��ҳ��ȡU ��ҳ��ȡUser
   * 
   * @param start
   *            ��ʼ��¼
   * @param count
   *            ��ʼ��¼
   * @return Page
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A0223
   */
  public Collection getUsers(long start, long count) throws DAOException;

  public Collection getUsersForPage(long start, long count) throws DAOException;

  /**
   * ��ȡ���е�User
   * 
   * @return ��ȡ���е�User
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A0273
   */
  public Collection getUsers() throws DAOException;

  /**
   * �����û�����ȡUserʵ��
   * 
   * @param name
   *            �û���
   * @return �û�ʵ��
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A02CE
   */
  public Collection findUserByName(String name) throws DAOException;

  /**
   * ����Pin��ȡUserʵ��
   * 
   * @param name
   *            �û���
   * @return �û�ʵ��
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A02CE
   */
 public Collection findUserByPin(String pin) throws DAOException;

  /**
   * ����SQL����ѯUser
   * 
   * @param searchQuery
   *            SQL���
   * @param start
   *            ��ʼ��¼
   * @param count
   *            ��¼��Ŀ
   * @return
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A031D
   */
  public Collection findUser(String searchQuery, long start, long count) throws
      DAOException;

  public Collection findUsersForPage(String searchQuery, long start, long count) throws
      DAOException;

  /**
   * ��ȡ�û�������λ�б�
   * 
   * @param user
   *            Userʵ��
   * @return ��Set��ʽ���ظ�λʵ��
   * @throws DAOException@throws
   *             com.gever.db.DAOException
   * @roseuid 40B6A88A037D
   */
  public Collection getJobs(User user) throws DAOException;

  /**
   * ���ݲ��ţ���ȡ�û�������λ�б�
   * 
   * @param user
   *            Userʵ��
   * @return ��Set��ʽ���ظ�λʵ��
   * @throws DAOException@throws
   *             com.gever.db.DAOException
   * @roseuid 40B6A88A037D
   */


  public Collection getJobs(User user,Department dept) throws DAOException;

  /**
   * ���ݲ��ţ���ȡδ�����û�������λ�б�
   * 
   * @param user
   *            Userʵ��
   * @return ��Set��ʽ���ظ�λʵ��
   * @throws DAOException@throws
   *             com.gever.db.DAOException
   * @roseuid 40B6A88A037D
   */


   public Collection getUnDistributeJobs(User user,Department dept) throws DAOException;


  /**
   * ��ȡ�û����ڵĲ���
   * 
   * @param user
   *            Userʵ��
   * @return �û����ڵĲ���
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88B003E
   */
  public Collection getDepartments(User user) throws DAOException;

  /**
   * @param user
   * @param jobs
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88B0232
   */
  public void addJobs(User user, Collection jobs) throws DAOException;

  /**
   * @param user
   * @param departments
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88B029F
   */
  public void addDepartments(User user, Collection departments) throws
      DAOException;

  /**
   * @param user
   * @param jobs
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88B030D
   */
  public void setJobs(User user, Collection jobs) throws DAOException;

  /**
   * @param user
   * @param departments
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88B037A
   */
  public void setDepartments(User user, Collection departments) throws
      DAOException;

  /**
   * ��ȡ�����û���
   * 
   * @return int
   * @roseuid 40B6D61802AF
   */
  public int getUserCount() throws DAOException;

  public int getUserCountBySQL(String inputSQL) throws DAOException;

  public int getUserCount(String searchQuery) throws DAOException;

  public void updateUserPassword(User user) throws DAOException;
  
  //===============================================================
  //������ӣ�����JSP��ͼ�б�������
  public void setOrderby(String[] s) ;
  //===============================================================
  
  public void updateUserLevel(String[] level_id) throws DAOException;
}
