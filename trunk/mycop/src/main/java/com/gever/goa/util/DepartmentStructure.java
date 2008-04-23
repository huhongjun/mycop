package com.gever.goa.util;

import com.gever.sysman.organization.model.Department;

/**
 * <p>Title: 部门结构对象类</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DepartmentStructure {

    private Department department;//部门
    private int layerNumber;//部门层级

    public DepartmentStructure() {
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getLayerNumber() {
        return layerNumber;
    }

    public void setLayerNumber(int layerNumber) {
        this.layerNumber = layerNumber;
    }
}