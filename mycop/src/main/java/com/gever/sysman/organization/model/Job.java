package com.gever.sysman.organization.model;

import java.io.Serializable;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE¡¡OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface Job
    extends Serializable, Cloneable {
    public int getDepartment();

    public void setDepartment(int department);

    public String getDescription();

    public void setDescription(String description);

    public int getId();

    public void setId(int Id);

    public String getName();

    public void setName(String name);

    public String getDepartmentName();

    public void setDepartmentName(String departmentName);
}