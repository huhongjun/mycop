package com.gever.sysman.organization.dao;

import com.gever.sysman.organization.model.Job;
import com.gever.exception.db.DAOException;

import java.util.Collection;
import com.gever.sysman.organization.model.User;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE　OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface JobDAO {

  /**
      保存Job
      @param Job 岗位实例
      @param job
      @throws DAOException@throws com.gever.db.DAOException
      @roseuid 40B6A88602FE
   */
  public void createJob(Job job) throws DAOException;

  /**
      更新Job
      @param Job 岗位实例
      @param job
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A886036C
   */
  public void updateJob(Job job) throws DAOException;

  public void setDepartmentJob(String departmentId, String[] jobId) throws
      DAOException;

  /**
      删除Job
      @param job 岗位实例
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A88603C8
   */
  public void deleteJob(String[] jobId) throws DAOException;

  public void deleteJobDepartment(String[] jobId, String departmentId) throws
      DAOException;

  /**
     根据岗位ID获取Job实例
     @para   @param id 岗位ID
     @return 岗位实例
     @throws DAOException
     @throws com.gever.exception.db.DAOException
     @roseuid 40B6A887002F
   */
  public Job findJobByID(long id) throws DAOException;

  /**
      分页获取Job
      @param start 起始记录
      @param count 记录数目
      @return Page
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A887007E
   */
  /*
      public Set getJobs(int start, int count) throws DAOException;
      /**
             获取所有的岗位
             @return 以Set形式返回Job集合
             @throws DAOException
             @throws com.gever.db.DAOException
             @roseuid 40B6A88700DC
    */
   public Collection getJobs() throws DAOException;

  /**
      根据岗位名获取Job实例
      @param name 岗位名
      @return 岗位实例
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A8870128
   */
  public Collection findJobByName(String name) throws DAOException;

  /**
      根据SQL语句查询Job
      @param searchQuery SQL语句
      @param start 起始记录
      @param count 记录数目
      @return
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A8870187
   */
  public Collection findJob(String searchQuery, long start, long count) throws
      DAOException;

  public Collection findJobsForPage(String searchQuery, long start, long count) throws
      DAOException;

  public Collection getJobs(long start, long count) throws DAOException;

  public Job findJobDeparment(int id, int department) throws DAOException;

  public Collection getJobDepartments() throws DAOException;

  public Collection getJobDepartments(long start, long count) throws DAOException;

  /**
      分页获取该岗位的用户
      @param job Job实例
      @param start 起始记录
      @param count 记录数目
      @return
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A88701E4
   */
  public Collection getUsers(Job job, long start, long count) throws DAOException;

  /**
      获取该岗位的用户
      @param job Job实例
      @return
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A8870251
   */
  public Collection getUsers(Job job) throws DAOException;

  public Collection getUsersByDept(Job job) throws DAOException;

  /**
      获取非该岗位的用户
      @param job Job实例
      @return
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A8870251
   */


  public Collection getUnDistributeUsers(Job job) throws DAOException;

  public Collection getUnDistributeUsersByDept(Job job) throws DAOException;

  /**
      获取岗位数目
      @return
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A887032C
   */
  public int getJobCount() throws DAOException;

  /**
      删除用户与工作岗位之间的关系
      @param job
      @param user
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A8880232
   */
  public void removeUser(Job job, User user) throws DAOException;

  //public java.util.List findJobsByDepartmentID(long departmentID) throws DAOException;

  //===============================================================
  //胡勇添加，增加JSP视图列表排序功能
  public void setOrderby(String[] s) ;
  //===============================================================

  //===============================================================
//LIBIAO添加，增加DELETE FROM DEPARTMENT_ID
public void deleteJobDepartment(String departmentId) throws DAOException ;
public int getJobId(String departmentId) throws DAOException;
//===============================================================


}
