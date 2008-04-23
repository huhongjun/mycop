package com.gever.sysman.organization.dao;

import java.util.Collection;

import com.gever.exception.db.DAOException;
import com.gever.sysman.organization.model.*;

/**
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

public interface UserDepartmentDAO {
  public void createUserDepartment(UserDepartment userDepartment) throws
      DAOException;

  public void createUserDepartment(int[] userId, int[] departmentId) throws
      DAOException;

  public void updateUserDepartment(UserDepartment userDepartment,
                                   int userUpdate,
                                   int departmentUpdate) throws DAOException;

  public void deleteUserDepartment(int[] userId, int[] departmentId) throws
      DAOException;

  public void deleteUserDepartmentByDepartment(int departmentId) throws
      DAOException;

  public void deleteUserDepartmentByUser(int userId) throws
      DAOException;

  public void deleteUserDepartmentByUser(String[] userId) throws DAOException;
  
  public Collection findUserDepartment(String searchQuery, long start, long count) throws
      DAOException;

  public Collection findUserDepartmentsForPage(String searchQuery, long start,
                                               long count) throws DAOException;

  public Collection findUserDepartments() throws DAOException;

  public Collection findUserDepartments(long start, long count) throws
      DAOException;

  public Collection findUserDepartmentsForPage(long start, long count) throws
      DAOException;

  public Collection findUserDepartmentByUser(int userId) throws DAOException;

  public Collection findUserDepartmentByDepartment(int departmentId) throws
      DAOException;

  public UserDepartment findUserDepartmentByUserByDepartment(int userId,
      int departmentId) throws DAOException;

  public int getUserDepartmentCount(String searchQuery) throws
      DAOException;

  public long getUserDepartmentCount() throws DAOException;

}