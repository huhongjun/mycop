package com.gever.sysman.privilege.model.impl;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

import com.gever.sysman.privilege.model.Role;
import com.gever.sysman.privilege.model.I_User;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Hu.Walker
 * @version 1.0
 * @hibernate.subclass discriminator-value="DEFAULT_ROLE"
 */

public class DefaultRole implements Role {
	private long id;
	private String name;
	private String description;
	private String roleType;
	private Set resources;
	private Set users = new HashSet();
	private Map properties = new HashMap();

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

	public Set getUsers() {
		return users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

	public void addUser(I_User user) {
		users.add(user);
	}

	public void removeUser(I_User user) {
		users.remove(user);
	}

	public int getUserCount() {
		return users.size();
	}

	public String toString() {
		return "[DefaultRole id=" + id + " name=" + name + "]";
	}

	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Role) {
			if (this.id == ((Role)obj).getId()) {
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		return new Long(id).hashCode();
	}
	/* 
	 * @see com.gever.privilege.model.Role#getProperties()
	 */
	public Map getProperties() {
		return properties;
	}

	/* 
	 * @see com.gever.privilege.model.Role#getProperty(java.lang.String)
	 */
	public String getProperty(String key) {
		return (String)properties.get(key);
	}

	/* 
	 * @see com.gever.privilege.model.Role#setProperties(java.util.Map)
	 */
	public void setProperties(Map properties) {
		this.properties = properties;
	}

	/* 
	 * @see com.gever.privilege.model.Role#setProperty(java.lang.String, java.lang.String)
	 */
	public void setProperty(String key, String value) {
		properties.put(key, value);
	}


	/**
	 * @return
	 */
	public Set getResources() {
		return resources;
	}

	/**
	 * @param resource
	 */
	public void setResources(Set resource) {
		resources = resource;
	}

	/**
	 * @return
	 */
	public String getRoleType() {
		return roleType;
	}

	/**
	 * @param string
	 */
	public void setRoleType(String roletype) {
		roleType = roletype;
	}

}
