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
 * <p>Company: KOBE��OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface JobDAO {

  /**
      ����Job
      @param Job ��λʵ��
      @param job
      @throws DAOException@throws com.gever.db.DAOException
      @roseuid 40B6A88602FE
   */
  public void createJob(Job job) throws DAOException;

  /**
      ����Job
      @param Job ��λʵ��
      @param job
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A886036C
   */
  public void updateJob(Job job) throws DAOException;

  public void setDepartmentJob(String departmentId, String[] jobId) throws
      DAOException;

  /**
      ɾ��Job
      @param job ��λʵ��
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A88603C8
   */
  public void deleteJob(String[] jobId) throws DAOException;

  public void deleteJobDepartment(String[] jobId, String departmentId) throws
      DAOException;

  /**
     ���ݸ�λID��ȡJobʵ��
     @para   @param id ��λID
     @return ��λʵ��
     @throws DAOException
     @throws com.gever.exception.db.DAOException
     @roseuid 40B6A887002F
   */
  public Job findJobByID(long id) throws DAOException;

  /**
      ��ҳ��ȡJob
      @param start ��ʼ��¼
      @param count ��¼��Ŀ
      @return Page
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A887007E
   */
  /*
      public Set getJobs(int start, int count) throws DAOException;
      /**
             ��ȡ���еĸ�λ
             @return ��Set��ʽ����Job����
             @throws DAOException
             @throws com.gever.db.DAOException
             @roseuid 40B6A88700DC
    */
   public Collection getJobs() throws DAOException;

  /**
      ���ݸ�λ����ȡJobʵ��
      @param name ��λ��
      @return ��λʵ��
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A8870128
   */
  public Collection findJobByName(String name) throws DAOException;

  /**
      ����SQL����ѯJob
      @param searchQuery SQL���
      @param start ��ʼ��¼
      @param count ��¼��Ŀ
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
      ��ҳ��ȡ�ø�λ���û�
      @param job Jobʵ��
      @param start ��ʼ��¼
      @param count ��¼��Ŀ
      @return
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A88701E4
   */
  public Collection getUsers(Job job, long start, long count) throws DAOException;

  /**
      ��ȡ�ø�λ���û�
      @param job Jobʵ��
      @return
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A8870251
   */
  public Collection getUsers(Job job) throws DAOException;

  public Collection getUsersByDept(Job job) throws DAOException;

  /**
      ��ȡ�Ǹø�λ���û�
      @param job Jobʵ��
      @return
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A8870251
   */


  public Collection getUnDistributeUsers(Job job) throws DAOException;

  public Collection getUnDistributeUsersByDept(Job job) throws DAOException;

  /**
      ��ȡ��λ��Ŀ
      @return
      @throws DAOException
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A887032C
   */
  public int getJobCount() throws DAOException;

  /**
      ɾ���û��빤����λ֮��Ĺ�ϵ
      @param job
      @param user
      @throws com.gever.exception.db.DAOException
      @roseuid 40B6A8880232
   */
  public void removeUser(Job job, User user) throws DAOException;

  //public java.util.List findJobsByDepartmentID(long departmentID) throws DAOException;

  //===============================================================
  //������ӣ�����JSP��ͼ�б�������
  public void setOrderby(String[] s) ;
  //===============================================================

  //===============================================================
//LIBIAO��ӣ�����DELETE FROM DEPARTMENT_ID
public void deleteJobDepartment(String departmentId) throws DAOException ;
public int getJobId(String departmentId) throws DAOException;
//===============================================================


}
