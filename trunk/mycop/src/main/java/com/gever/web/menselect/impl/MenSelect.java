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

  //���Id
  //���ڿͻ��˱�д��������
  //ע�⣬�÷���ֵ���ܳ��ֿո��磬����Ϊ"administrator Level"
  //�����������"administratorLevel"

  public String getId() {
    id = "department";
    return id;

  }

  //�������
  //��:"����������"
  public String getName() {
    name = "������";
    return name;
  }

//�ڸ÷����ķ���ֵMap��
  //key��Ӧ�����б��Valueֵ
  //value��Ӧ�����б����ʾ��name
  //��:
  //<select>
  //<option value="(***)">(---)</option>
  //</select>
  //�ڿͻ��˵����ɹ����У�
  //(***)Ϊvalue
  //(---)Ϊname
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

  //�õ���Ա��������ӳ���ϵ
  //ManӦ��ΪMap�е�key
  //catalogӦ��ΪMap�е�value
  //catalog��ֵ���뱣֤ΪgetCatalogs�з��ص�Mapһ��
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

//�õ�getManToCatalogMap�ķ���ֵMap��δ�����
  //ӳ���ϵ��manĬ�����������ֵ
  //���뱣֤ΪgetCatalogs�з��ص�Mapһ��
  public Object getDefaultCatalog() {

    return defaultCatalog;
  }

}
