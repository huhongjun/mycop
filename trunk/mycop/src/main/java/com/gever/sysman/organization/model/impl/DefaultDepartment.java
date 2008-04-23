package com.gever.sysman.organization.model.impl;

import com.gever.sysman.organization.model.Department;
import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE��OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DefaultDepartment
    implements Serializable, Department {
    private int Id; //��֯���ű�ID
    private String code; //��֯����
    private String name; //��֯��������
    private String description; //��֯��������
    private String departmentType; //��֯��������
    private int parentDepartment; //�ϼ���֯����ID
    private String parentDepartmentName; //�ϼ���֯��������
    private int childNum; //�¼���������
    private String functionary;//���Ÿ�����
    private int orderid;//���Ÿ�����

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

public void setFunctioary(String functionary){   //libiao���Ӳ��Ÿ����� 2004-11-26
   this.functionary=functionary;
}
 public String getFunctionary(){     //libiao���Ӳ��Ÿ����� 2004-11-26
  return this.functionary;
 }

 public int getOrderid(){   //libiao���Ӳ������� 2004-12-2
  return orderid;
}

 public void setOrderid(int orderid){
 this.orderid=orderid;    //libiao���Ӳ������� 2004-12-2
 }




}
