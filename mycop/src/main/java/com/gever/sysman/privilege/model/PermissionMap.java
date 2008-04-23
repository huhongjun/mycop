/*
 * 功能描述 权限映射接口
 * 创建日期 2004-11-25 10:23:33
 */
package com.gever.sysman.privilege.model;


import java.io.Serializable;

import com.gever.sysman.privilege.util.Extensible;


/**
 * @author Hu.Walker
 */
public interface PermissionMap extends Extensible, Serializable, Cloneable {
    /**
     * 取得数据库唯一标识
     * @return
     */
    public long getId();

    /**
     * 设置ID
     * @return
     */
    public void setId(long id);
    /**
     * 设置Action的映射路径
     * @param actionPath
     */
    public void setActionPath(String actionPath);

    /**
     * 取得Action的映射路径
     * @return
     */
    public String getActionPath();

    /**
     * 设置Action所拥有的方法名称
     * @param methodName
     */
    public void setMethodName(String methodName);

    /**
     * 取得Action所拥有的方法名称
     * @return
     */
    public String getMethodName();

    /**
     * 设置资源代码
     * @param resCode
     */
    public void setResCode(String resCode);

    /**
     * 取得资源代码
     * @return
     */
    public String getResCode();

    /**
     * 设置该资源所拥有的操作代码
     * @param resOpCode
     */
    public void setResOpCode(String resOpCode);

    /**
     * 取得该资源所拥有的操作代码
     * @return
     */
    public String getResOpCode();
}
