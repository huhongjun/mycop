package com.gever.sysman.organization.dao;

import java.util.Collection;

import com.gever.exception.db.DAOException;

import com.gever.sysman.organization.model.Department;
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

public interface DepartmentDAO {

  public void insertDepartment(Department department) throws DAOException;

  /**
   * ����Department
   * @param department ����ʵ��
   * @throws DAOException
   */
  public void updateDepartment(Department department) throws DAOException;

  /**
   * ɾ��Department
   * @param department ����ʵ��
   * @throws DAOException
   */
  public void deleteDepartment(String[] departmentId) throws DAOException;
  /**
   * ���ݲ���CODE��ȡDepartmentʵ��
   * @param code ����CODE
   * @return ����ʵ��
   * @throws DAOException
   */

  public Collection findDepartmentByCode(String code) throws DAOException;
  /**
   * ���ݲ���ID��ȡDepartmentʵ��
   * @param id ����ID
   * @return ����ʵ��
   * @throws DAOException
   */
  public Department findDepartmentByID(long id) throws DAOException;

  /**
   * ��ҳ��ȡDepartment
   * @param start ��ʼ��¼
   * @param count ��¼��Ŀ
   * @return Page
   */
  public Collection getDepartments(long start, long count) throws DAOException;


  /**
   * ��ȡ���е�Department
   * @return ��Set��ʽ����Department����
   * @throws DAOException
   */
  public Collection getDepartments() throws DAOException;

  /**
   * ���ݲ��������ز���ʵ��
   * @param name ������
   * @return ����ʵ��
   * @throws DAOException
   */
    public Collection findDepartmentByName(String name) throws DAOException;

    /**
   * �����ϼ�ID�������ز���ʵ��
   * @param  Parent_ID �ϼ�����ID
   * @return ����ʵ��
   * @throws DAOException
   */
    public Department findDepartmentNameByid(String id) throws DAOException;
  /**
   * ����SQL����ѯDepartment
   * @param searchQuery SQL���
   * @param start ��ʼ��¼
   * @param count ��¼��Ŀ
   * @return
   * @throws DAOException
   */
  public Collection findDepartment(String searchQuery, long start, long count) throws
      DAOException;

  public Collection findDepartmentsForPage(String searchQuery, long start,
                                           long count) throws DAOException;

  /**
   * ��ȡ���ϲ�Department,��parentDepartmentΪ��
   * @return
   * @throws DAOException
   */
  public Collection getRootDepartment() throws DAOException;

  /**
   * ��ȡ�¼�����
   * @param department
   * @return
   * @throws DAOExceptions
   */
  public Collection getSubDepartments(Department department) throws
      DAOException;

  public Department getParentDepartment(Department department) throws
      DAOException;

  public Collection getSubDepartments(Department department,long start,long count) throws
      DAOException ;

  /**
   * ��ȡ��������
   * @return ������Ŀ
   * @throws DAOException
   */
  public int getDepartmentCount(long id) throws DAOException;

  public int getDepartmentCount() throws DAOException;

  public int getSubDepartmentCount(Department department) throws DAOException;

  /**
   * ��ȡ����ְλ��Ŀ��Ĭ�ϲ������¼�����
   * @param department
   * @return
   * @throws DAOException
   */
  public Collection getJobs(Department department) throws DAOException;

  public Collection getJobs(Department department, long start, long count) throws
      DAOException;

  public Collection getJobsForPage(Department department, long start, long count) throws
      DAOException;

  public Collection getUnDistributeJobs() throws DAOException;

  /**
   * ��ȡ�����û���Ĭ�ϲ������¼�����
   * @param department Departmentʵ��
   * @return
   * @throws DAOException
   */
  public Collection getUsers(Department department) throws DAOException;

  public Collection getUnDistributeUsers(Department department) throws
      DAOException;

  /**
   * ��ҳ��ȡ�����û���Ĭ�ϲ������¼�����
   * @param department
   * @param start
   * @param count
   * @return
   * @throws DAOException
   */
  public Collection getUsers(Department department, long start, long count) throws
      DAOException;

  public Collection getUsersForPage(Department department, long start, long count) throws
      DAOException;
  /**
   * ��ȡ�����û���Ŀ��Ĭ�ϲ������¼�����
   * @param department Departmentʵ��
   * @return
   * @throws DAOException
   */
  public int getUserCount(Department department) throws DAOException;

  public void removeUser(Department department, User user) throws DAOException;

  public Collection getDepartmentsForPage(long start, long count) throws
      DAOException;

  public Collection getDepartmentsForPage(long id, long start, long count) throws
      DAOException;

    //===============================================================
    //������ӣ�����JSP��ͼ�б�������
    public void setOrderby(String[] s) ;
    //===============================================================
}
