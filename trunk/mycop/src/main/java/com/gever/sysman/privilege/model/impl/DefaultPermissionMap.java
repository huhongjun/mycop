/*
 * 功能描述 权限映射的实现类
 * 创建日期 2004-11-25 10:30:27
 */
package com.gever.sysman.privilege.model.impl;

import com.gever.sysman.privilege.model.PermissionMap;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Hu.Walker
 */
public class DefaultPermissionMap implements PermissionMap {
    private long id;
    private String actionPath;
    private String methodName;
    private String resCode;
    private String resOpCode;
    private Map properties = new HashMap();

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.model.PermissionMap#getActonPath()
     */
    public String getActionPath() {
        return actionPath;
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.model.PermissionMap#getMethodName()
     */
    public String getMethodName() {
        return methodName;
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.model.PermissionMap#getResCode()
     */
    public String getResCode() {
        return resCode;
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.model.PermissionMap#getResOpCode()
     */
    public String getResOpCode() {
        return resOpCode;
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.model.PermissionMap#setActionPath(java.lang.String)
     */
    public void setActionPath(String actionPath) {
        this.actionPath = actionPath;
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.model.PermissionMap#setMethodName(java.lang.String)
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.model.PermissionMap#setResCode(java.lang.String)
     */
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.model.PermissionMap#setResOpCode(java.lang.String)
     */
    public void setResOpCode(String resOpCode) {
        this.resOpCode = resOpCode;
    }

    /* （非 Javadoc）
     * @see com.gever.Extensible#getProperties()
     */
    public Map getProperties() {
        return properties;
    }

    /* （非 Javadoc）
     * @see com.gever.Extensible#getProperty(java.lang.String)
     */
    public String getProperty(String key) {
        return (String) properties.get(key);
    }

    /* （非 Javadoc）
     * @see com.gever.Extensible#setProperties(java.util.Map)
     */
    public void setProperties(Map properties) {
        this.properties = properties;
    }

    /* （非 Javadoc）
     * @see com.gever.Extensible#setProperty(java.lang.String, java.lang.String)
     */
    public void setProperty(String key, String value) {
        this.properties.put(key, value);
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.model.PermissionMap#getId()
     */
    public long getId() {
        return id;
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.model.PermissionMap#setId()
     */
    public void setId(long id) {
        this.id = id;
    }

}
