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
 * <p>Company: KOBE　OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface DepartmentDAO {

  public void insertDepartment(Department department) throws DAOException;

  /**
   * 更新Department
   * @param department 部门实例
   * @throws DAOException
   */
  public void updateDepartment(Department department) throws DAOException;

  /**
   * 删除Department
   * @param department 部门实例
   * @throws DAOException
   */
  public void deleteDepartment(String[] departmentId) throws DAOException;
  /**
   * 根据部门CODE获取Department实例
   * @param code 部门CODE
   * @return 部门实例
   * @throws DAOException
   */

  public Collection findDepartmentByCode(String code) throws DAOException;
  /**
   * 根据部门ID获取Department实例
   * @param id 部门ID
   * @return 部门实例
   * @throws DAOException
   */
  public Department findDepartmentByID(long id) throws DAOException;

  /**
   * 分页获取Department
   * @param start 起始记录
   * @param count 记录数目
   * @return Page
   */
  public Collection getDepartments(long start, long count) throws DAOException;


  /**
   * 获取所有的Department
   * @return 以Set形式返回Department集合
   * @throws DAOException
   */
  public Collection getDepartments() throws DAOException;

  /**
   * 根据部门名返回部门实例
   * @param name 部门名
   * @return 部门实例
   * @throws DAOException
   */
    public Collection findDepartmentByName(String name) throws DAOException;

    /**
   * 根据上级ID门名返回部门实例
   * @param  Parent_ID 上级部门ID
   * @return 部门实例
   * @throws DAOException
   */
    public Department findDepartmentNameByid(String id) throws DAOException;
  /**
   * 根据SQL语句查询Department
   * @param searchQuery SQL语句
   * @param start 起始记录
   * @param count 记录数目
   * @return
   * @throws DAOException
   */
  public Collection findDepartment(String searchQuery, long start, long count) throws
      DAOException;

  public Collection findDepartmentsForPage(String searchQuery, long start,
                                           long count) throws DAOException;

  /**
   * 获取最上层Department,其parentDepartment为空
   * @return
   * @throws DAOException
   */
  public Collection getRootDepartment() throws DAOException;

  /**
   * 获取下级部门
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
   * 获取部门总数
   * @return 部门数目
   * @throws DAOException
   */
  public int getDepartmentCount(long id) throws DAOException;

  public int getDepartmentCount() throws DAOException;

  public int getSubDepartmentCount(Department department) throws DAOException;

  /**
   * 获取部门职位数目，默认不包括下级部门
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
   * 获取部门用户，默认不包括下级部门
   * @param department Department实例
   * @return
   * @throws DAOException
   */
  public Collection getUsers(Department department) throws DAOException;

  public Collection getUnDistributeUsers(Department department) throws
      DAOException;

  /**
   * 分页获取部门用户，默认不包括下级部门
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
   * 获取部门用户数目，默认不包括下级部门
   * @param department Department实例
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
    //胡勇添加，增加JSP视图列表排序功能
    public void setOrderby(String[] s) ;
    //===============================================================
}
