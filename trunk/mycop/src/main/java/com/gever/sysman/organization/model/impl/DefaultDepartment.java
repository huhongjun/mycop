package com.gever.sysman.organization.model.impl;

import com.gever.sysman.organization.model.Department;
import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE　OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DefaultDepartment
    implements Serializable, Department {
    private int Id; //组织部门表ID
    private String code; //组织编码
    private String name; //组织部门名称
    private String description; //组织部门描述
    private String departmentType; //组织部门类型
    private int parentDepartment; //上级组织部门ID
    private String parentDepartmentName; //上级组织部门名称
    private int childNum; //下级部门数量
    private String functionary;//部门负责人
    private int orderid;//部门负责人

    public DefaultDepartment() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(int parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public String getParentDepartmentName() {
        return parentDepartmentName;
    }

    public void setParentDepartmentName(String parentDepartmentName) {
        this.parentDepartmentName = parentDepartmentName;
    }

    public int getChildNum(){
      return this.childNum;
    }

    public void setChildNum(int num){
      this.childNum =num;
    }

public void setFunctioary(String functionary){   //libiao增加部门负责人 2004-11-26
   this.functionary=functionary;
}
 public String getFunctionary(){     //libiao增加部门负责人 2004-11-26
  return this.functionary;
 }

 public int getOrderid(){   //libiao增加部门排序 2004-12-2
  return orderid;
}

 public void setOrderid(int orderid){
 this.orderid=orderid;    //libiao增加部门排序 2004-12-2
 }




}
