/*
 * 创建日期 2004-6-9
 *
 */
package com.gever.sysman.privilege.model.impl;

import com.gever.sysman.privilege.model.UserPermission;

/**
 * @author Hu.Walker
 *
 */
public class DefaultUserPermission
    implements UserPermission {
  private long user_id;
  private long resource_id;
  private long resop_id;
  private String res_code;
  private String opt_code;
  private boolean isRolePerm;
  /**
   * @return
   */
  public long getResop_id() {
    return resop_id;
  }

  /**
   * @return
   */
  public long getResource_id() {
    return resource_id;
  }

  /**
   * @return
   */
  public long getUser_id() {
    return user_id;
  }

  /**
   * @param l
   */
  public void setResop_id(long l) {
    resop_id = l;
  }

  /**
   * @param l
   */
  public void setResource_id(long l) {
    resource_id = l;
  }

  /**
   * @param l
   */
  public void setUser_id(long l) {
    user_id = l;
  }

  /**
   * @return
   */
  public boolean isRolePerm() {
    return isRolePerm;
  }

  /**
   * @param b
   */
  public void setRolePerm(boolean b) {
    isRolePerm = b;
  }

  public String getOpt_code() {
    return opt_code;
  }

  public String getRes_code() {
    return res_code;
  }

  public void setOpt_code(String opt_code) {
    this.opt_code = opt_code;
  }

  public void setRes_code(String res_code) {
    this.res_code = res_code;
  }

}
