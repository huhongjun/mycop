/*
 * 创建日期 2004-6-9
 *
 */
package com.gever.sysman.privilege.model;

import java.io.Serializable;

/**
 * @author Hu.Walker
 *
 */
public interface UserPermission
    extends Serializable, Cloneable {
  /**
   * @return
   */
  public abstract long getResop_id();

  /**
   * @return
   */
  public abstract long getResource_id();

  /**
   * @return
   */
  public abstract long getUser_id();

  /**
   * @param l
   */
  public abstract void setResop_id(long l);

  /**
   * @param l
   */
  public abstract void setResource_id(long l);

  /**
   * @param l
   */
  public abstract void setUser_id(long l);

  public boolean isRolePerm();

  /**
   * @param b
   */
  public void setRolePerm(boolean b);

  public String getRes_code();

  public void setRes_code(String res_code);

  public String getOpt_code();

  public void setOpt_code(String opt_code);
}