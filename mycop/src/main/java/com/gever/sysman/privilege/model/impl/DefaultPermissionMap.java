/*
 * �������� Ȩ��ӳ���ʵ����
 * �������� 2004-11-25 10:30:27
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

    /* ���� Javadoc��
     * @see com.gever.sysman.privilege.model.PermissionMap#getActonPath()
     */
    public String getActionPath() {
        return actionPath;
    }

    /* ���� Javadoc��
     * @see com.gever.sysman.privilege.model.PermissionMap#getMethodName()
     */
    public String getMethodName() {
        return methodName;
    }

    /* ���� Javadoc��
     * @see com.gever.sysman.privilege.model.PermissionMap#getResCode()
     */
    public String getResCode() {
        return resCode;
    }

    /* ���� Javadoc��
     * @see com.gever.sysman.privilege.model.PermissionMap#getResOpCode()
     */
    public String getResOpCode() {
        return resOpCode;
    }

    /* ���� Javadoc��
     * @see com.gever.sysman.privilege.model.PermissionMap#setActionPath(java.lang.String)
     */
    public void setActionPath(String actionPath) {
        this.actionPath = actionPath;
    }

    /* ���� Javadoc��
     * @see com.gever.sysman.privilege.model.PermissionMap#setMethodName(java.lang.String)
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /* ���� Javadoc��
     * @see com.gever.sysman.privilege.model.PermissionMap#setResCode(java.lang.String)
     */
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    /* ���� Javadoc��
     * @see com.gever.sysman.privilege.model.PermissionMap#setResOpCode(java.lang.String)
     */
    public void setResOpCode(String resOpCode) {
        this.resOpCode = resOpCode;
    }

    /* ���� Javadoc��
     * @see com.gever.Extensible#getProperties()
     */
    public Map getProperties() {
        return properties;
    }

    /* ���� Javadoc��
     * @see com.gever.Extensible#getProperty(java.lang.String)
     */
    public String getProperty(String key) {
        return (String) properties.get(key);
    }

    /* ���� Javadoc��
     * @see com.gever.Extensible#setProperties(java.util.Map)
     */
    public void setProperties(Map properties) {
        this.properties = properties;
    }

    /* ���� Javadoc��
     * @see com.gever.Extensible#setProperty(java.lang.String, java.lang.String)
     */
    public void setProperty(String key, String value) {
        this.properties.put(key, value);
    }

    /* ���� Javadoc��
     * @see com.gever.sysman.privilege.model.PermissionMap#getId()
     */
    public long getId() {
        return id;
    }

    /* ���� Javadoc��
     * @see com.gever.sysman.privilege.model.PermissionMap#setId()
     */
    public void setId(long id) {
        this.id = id;
    }

}
