package com.gever.sysman.privilege.model.impl;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

import com.gever.sysman.organization.model.Department;
import com.gever.sysman.organization.model.Job;

import com.gever.sysman.privilege.model.I_User;
import com.gever.sysman.privilege.model.Role;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 * @hibernate.subclass discriminator-value="DEFAULT_USER"
 */

public class DefaultUser implements I_User {
	private int id;
	private String name;
	private String userName;
	private String password;
	private String status;
	private String code;
    private String userType;
    private Set resources;
    private String gender;
    private String level;
    private String validDate;
    private String userClass;
	private Set roles = new HashSet();
	private Set jobs = new HashSet();
	private Set departments = new HashSet();
	private Map properties = new HashMap();

	public DefaultUser() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set getRoles() {
		return roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		roles.add(role);
	}

	public void removeRole(Role role) {
		roles.remove(role);
	}

	public String toString() {
		return "[DefaultUser id=" + id + "]";
	}

	public boolean equals(Object obj) {
		if (obj instanceof I_User) {
			if (this.getId() == ((I_User)obj).getId()) {
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		return new Long(id).hashCode();
	}

	/*
	 * @see com.gever.organization.model.I_User#getProperties()
	 */
	public Map getProperties() {
		return properties;
	}

	/*
	 * @see com.gever.organization.model.I_User#getProperty(java.lang.String)
	 */
	public String getProperty(String key) {
		return (String)properties.get(key);
	}

	/*
	 * @see com.gever.organization.model.I_User#getUserName()
	 */
	public String getUserName() {
		return userName;
	}

	/*
	 * @see com.gever.organization.model.I_User#setProperties(java.util.Map)
	 */
	public void setProperties(Map properties) {
		this.properties = properties;
	}

	/*
	 * @see com.gever.organization.model.I_User#setProperty(java.lang.String, java.lang.String)
	 */
	public void setProperty(String key, String value) {
		properties.put(key,value);
	}

	/*
	 * @see com.gever.organization.model.I_User#setUserName(java.lang.String)
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * @see com.gever.organization.model.I_User#addDepartment(com.gever.organization.model.Department)
	 */
	public void addDepartment(Department department) {
		departments.add(department);
	}

	/*
	 * @see com.gever.organization.model.I_User#addJob(com.gever.organization.model.Job)
	 */
	public void addJob(Job job) {
		jobs.add(job);
	}

	/*
	 * @see com.gever.organization.model.I_User#getDepartments()
	 */
	public Set getDepartments() {
		return departments;
	}

	/*
	 * @see com.gever.organization.model.I_User#getJobs()
	 */
	public Set getJobs() {
		return jobs;
	}

	/*
	 * @see com.gever.organization.model.I_User#removeDepartment(com.gever.organization.model.Department)
	 */
	public void removeDepartment(Department department) {
		departments.remove(department);
	}

	/*
	 * @see com.gever.organization.model.I_User#removeJob(com.gever.organization.model.Job)
	 */
	public void removeJob(Job job) {
		jobs.remove(job);
	}

	/*
	 * @see com.gever.organization.model.I_User#setDepartments(java.util.Set)
	 */
	public void setDepartments(Set departments) {
		this.departments = departments;
	}

	/*
	 * @see com.gever.organization.model.I_User#setJobs(java.util.Set)
	 */
	public void setJobs(Set jobs) {
		this.jobs = jobs;
	}

	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string) {
		status = string;
	}

	/**
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param string
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return
	 */
	public Set getResources() {
		return resources;
	}

	/**
	 * @return
	 */
	public String getUserClass() {
		return userClass;
	}

	/**
	 * @return
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @return
	 */
	public String getValidDate() {
		return validDate;
	}

	/**
	 * @param set
	 */
	public void setResources(Set set) {
		resources = set;
	}

	/**
	 * @param string
	 */
	public void setUserClass(String string) {
		userClass = string;
	}

	/**
	 * @param string
	 */
	public void setUserType(String string) {
		userType = string;
	}

	/**
	 * @param date
	 */
	public void setValidDate(String validdate) {
		validDate = validdate;
	}

	/**
	 * @return
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param string
	 */
	public void setGender(String string) {
		gender = string;
	}

	/**
	 * @return
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param string
	 */
	public void setLevel(String string) {
		level = string;
	}
        public boolean equals(com.gever.sysman.organization.model.User user){
            boolean result = false;
            if (this.id == user.getId()){
                result = true;
            }
            return result;
        }

}
