package com.gever.sysman.privilege.model.impl;

import com.gever.sysman.privilege.model.Operation;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.model.Role;

import java.util.Map;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */
public class DefaultOperation implements Operation {
    private long id;
    private String name;
    private String description;
    private Role role;
    private Resource resource;
    private long resourceID;
    private String resourceName;
    private boolean isOpt;
    private boolean isRolePerm;
  private String code;

    //	private Map properties = new HashMap();
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    /**
    获取扩展属性
    @return java.util.Map
    @roseuid 40B69DA500CB
             */
    public Map getProperties() {
        return null;
    }

    /**
    设置扩展属性
    @param properties - 扩展属性
    @roseuid 40B69DB7005D
             */
    public void setProperties(Map properties) {
    }

    /**
    根据key获取扩展属性
    @param key
    @return java.lang.String
    @roseuid 40B69DBF0119
             */
    public String getProperty(String key) {
        return null;
    }

    /**
    设置扩展属性
    @param key - 指定的key值
    @param value - 指定的属性值
    @roseuid 40B69DC30251
             */
    public void setProperty(String key, String value) {
    }

    public void setResourceID(long resourceid) {
        this.resourceID = resourceid;
    }

    public long getResourceID() {
        return resourceID;
    }

    /**
     * @return
     */
    public boolean isOpt() {
        return isOpt;
    }

    /**
     * @param b
     */
    public void setOpt(boolean b) {
        isOpt = b;
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
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

}
