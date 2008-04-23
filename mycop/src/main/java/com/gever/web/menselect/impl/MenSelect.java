package com.gever.web.menselect.impl;

import java.util.Map;
import java.util.HashMap;

import java.util.Iterator;

import com.gever.exception.db.DAOException;

import com.gever.sysman.organization.dao.OrganizationFactory;

import com.gever.sysman.organization.model.UserDepartment;
import com.gever.sysman.organization.dao.UserDepartmentDAO;
import com.gever.sysman.organization.model.Department;
import com.gever.sysman.organization.dao.DepartmentDAO;
import com.gever.web.menselect.MenSelectedCatalog;

public class MenSelect implements MenSelectedCatalog {

  private String id;
  private String name;

  private Map catalogs = new HashMap();
  private Map manToCatalogMap = new HashMap();
  private Object defaultCatalog = new Object();

  //类表Id
  //对于客户端编写代码有用
  //注意，该返回值不能出现空格，如，不能为"administrator Level"
  //在这里可以如"administratorLevel"

  public String getId() {
    id = "department";
    return id;

  }

  //类别名称
  //如:"按行政级别"
  public String getName() {
    name = "按部门";
    return name;
  }

//在该方法的返回值Map中
  //key对应下拉列表的Value值
  //value对应下拉列表的显示名name
  //如:
  //<select>
  //<option value="(***)">(---)</option>
  //</select>
  //在客户端的生成过程中，
  //(***)为value
  //(---)为name
  public Map getCatalogs() throws DAOException {
    Department department = OrganizationFactory.getInstance().createDepartment();

    DepartmentDAO departmentDAO = OrganizationFactory.getInstance().
        getDepartmentDAO();

    Iterator i = departmentDAO.getDepartments().iterator();
    while (i.hasNext()) {

      department = (Department) i.next();
      catalogs.put(new Integer(department.getId()), department.getName());
    }

    return catalogs;

  }

  //得到人员到该类别的映射关系
  //Man应该为Map中的key
  //catalog应该为Map中的value
  //catalog的值必须保证为getCatalogs中返回的Map一致
  public Map getManToCatalogMap() throws DAOException {
    UserDepartmentDAO userDepartmentDAO = OrganizationFactory.getInstance().
        getUserDepartmentDAO();
    UserDepartment userDepartment = OrganizationFactory.getInstance().
        createUserDepartment();


    Iterator i = userDepartmentDAO.findUserDepartments().iterator();
    while (i.hasNext()) {

      userDepartment = (UserDepartment) i.next();

      manToCatalogMap.put(new Integer(userDepartment.getUser()),
      new Integer(userDepartment.getDepartment()));
    }

    return manToCatalogMap;

  }

//得到getManToCatalogMap的返回值Map中未定义的
  //映射关系的man默认所属的类别值
  //必须保证为getCatalogs中返回的Map一致
  public Object getDefaultCatalog() {

    return defaultCatalog;
  }

}
