package com.gever.sysman.organization.model;

import java.io.Serializable;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE　OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface Department
    extends Serializable, Cloneable {
    public String getCode();

    public void setCode(String Code);

    public void setFunctioary(String functionary);//libiao增加部门负责人 2004-11-26

    public String getFunctionary();//libiao增加部门负责人 2004-11-26

    public void setOrderid(int orderid);//libiao增加部门负责人 2004-12-2

    public int getOrderid();//libiao增加部门负责人 2004-12-2


    public String getDepartmentType();

    public void setDepartmentType(String DepartmentType);

    public String getDescription();

    public void setDescription(String Description);

    public int getId();

    public void setId(int Id);

    public String getName();

    public void setName(String Name);

    public int getParentDepartment();

    public void setParentDepartment(int ParentDepartment);

    public String getParentDepartmentName();

    public void setParentDepartmentName(String parentDepartmentName);

    public int getChildNum();

    public void setChildNum(int num);

}
