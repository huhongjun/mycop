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
   * 创建User
   * 
   * @param User
   *            用户实例
   * @param user
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A00CC
   */
  public void createUser(User user) throws DAOException;

  /**
   * 更新User
   * 
   * @param User
   *            用户实例
   * @param user
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A0128
   */
  public void updateUser(User user) throws DAOException;

  /**
   * 删除User
   * 
   * @param user
   *            用户实例
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A0178
   */
  public void deleteUser(String[] userId) throws DAOException;

  /**
   * 根据用户ID获取User实例
   * 
   * @param id
   *            用户ID
   * @return 用户实例
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A01C6
   */
  public User findUserByID(long id) throws DAOException;

  public User findUserByUserName(String userName) throws DAOException;

  /**
   * 分页获取U 分页获取User
   * 
   * @param start
   *            起始记录
   * @param count
   *            起始记录
   * @return Page
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A0223
   */
  public Collection getUsers(long start, long count) throws DAOException;

  public Collection getUsersForPage(long start, long count) throws DAOException;

  /**
   * 获取所有的User
   * 
   * @return 获取所有的User
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A0273
   */
  public Collection getUsers() throws DAOException;

  /**
   * 根据用户名获取User实例
   * 
   * @param name
   *            用户名
   * @return 用户实例
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A02CE
   */
  public Collection findUserByName(String name) throws DAOException;

  /**
   * 根据Pin获取User实例
   * 
   * @param name
   *            用户名
   * @return 用户实例
   * @throws DAOException
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40B6A88A02CE
   */
 public Collection findUserByPin(String pin) throws DAOException;

  /**
   * 根据SQL语句查询User
   * 
   * @param searchQuery
   *            SQL语句
   * @param start
   *            起始记录
   * @param count
   *            记录数目
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
   * 获取用户工作岗位列表
   * 
   * @param user
   *            User实例
   * @return 以Set形式返回岗位实例
   * @throws DAOException@throws
   *             com.gever.db.DAOException
   * @roseuid 40B6A88A037D
   */
  public Collection getJobs(User user) throws DAOException;

  /**
   * 依据部门，获取用户工作岗位列表
   * 
   * @param user
   *            User实例
   * @return 以Set形式返回岗位实例
   * @throws DAOException@throws
   *             com.gever.db.DAOException
   * @roseuid 40B6A88A037D
   */


  public Collection getJobs(User user,Department dept) throws DAOException;

  /**
   * 依据部门，获取未分配用户工作岗位列表
   * 
   * @param user
   *            User实例
   * @return 以Set形式返回岗位实例
   * @throws DAOException@throws
   *             com.gever.db.DAOException
   * @roseuid 40B6A88A037D
   */


   public Collection getUnDistributeJobs(User user,Department dept) throws DAOException;


  /**
   * 获取用户所在的部门
   * 
   * @param user
   *            User实例
   * @return 用户所在的部门
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
   * 获取所有用户数
   * 
   * @return int
   * @roseuid 40B6D61802AF
   */
  public int getUserCount() throws DAOException;

  public int getUserCountBySQL(String inputSQL) throws DAOException;

  public int getUserCount(String searchQuery) throws DAOException;

  public void updateUserPassword(User user) throws DAOException;
  
  //===============================================================
  //胡勇添加，增加JSP视图列表排序功能
  public void setOrderby(String[] s) ;
  //===============================================================
  
  public void updateUserLevel(String[] level_id) throws DAOException;
}
