/*
 * 创建日期 2004-6-9
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.gever.sysman.privilege.model.impl;

import com.gever.sysman.privilege.model.RolePermission;;

/**
 * @author Hu.Walker
 *
 */
public class DefaultRolePermission implements RolePermission {
 private long roleid;
 private long resourceid;
 private long resopid;
  private String res_code;
  private String opt_code;
/**
 * @return
 */
public long getResopid() {
	return resopid;
}

/**
 * @return
 */
public long getResourceid() {
	return resourceid;
}

/**
 * @return
 */
public long getRoleid() {
	return roleid;
}

/**
 * @param l
 */
public void setResopid(long l) {
	resopid = l;
}

/**
 * @param l
 */
public void setResourceid(long l) {
	resourceid = l;
}

/**
 * @param l
 */
public void setRoleid(long l) {
	roleid = l;
}
  public String getRes_code() {
    return res_code;
  }
  public void setRes_code(String res_code) {
    this.res_code = res_code;
  }
  public String getOpt_code() {
    return opt_code;
  }
  public void setOpt_code(String opt_code) {
    this.opt_code = opt_code;
  }

}
