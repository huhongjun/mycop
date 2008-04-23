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

public interface User
    extends Serializable, Cloneable {
    public String getCode();

    public void setCode(String code);

    public String getGender();

    public void setGender(String gender);

    public int getId();

    public void setId(int Id);

    public String getLevel();

    public void setLevel(String level);

    public String getName();

    public void setName(String name);

    public String getPassword();

    public void setPassword(String password);

    public String getStatus();

    public void setStatus(String status);

    public String getUserName();

    public void setUserName(String userName);

    public String getUserType();

    public void setUserType(String userType);

    public String getValidDate();

    public void setValidDate(String validDate);

    public boolean equals(User user);
}
