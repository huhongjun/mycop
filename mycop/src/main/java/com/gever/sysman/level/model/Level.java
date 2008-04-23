package com.gever.sysman.level.model;

import java.io.Serializable;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE¡¡OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface Level
    extends Serializable, Cloneable {

  public long getId();

  public void setId(long Id);

  public String getCode();

  public void setCode(String code);

  public String getName();

  public void setName(String name);

  public String getDescription();

  public void setDescription(String Description);

  public boolean equals(Level level);
}
