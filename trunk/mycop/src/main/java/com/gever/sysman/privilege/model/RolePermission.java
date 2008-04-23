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
public interface RolePermission
    extends Serializable, Cloneable {
  /**
   * @return
   */
  public abstract long getResopid();

  /**
   * @return
   */
  public abstract long getResourceid();

  /**
   * @return
   */
  public abstract long getRoleid();

  /**
   * @param l
   */
  public abstract void setResopid(long l);

  /**
   * @param l
   */
  public abstract void setResourceid(long l);

  /**
   * @param l
   */
  public abstract void setRoleid(long l);

  public String getRes_code();

  public void setRes_code(String res_code);

  public String getOpt_code();

  public void setOpt_code(String opt_code);
}