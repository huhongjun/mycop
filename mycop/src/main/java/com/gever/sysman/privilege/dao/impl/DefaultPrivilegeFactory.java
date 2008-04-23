package com.gever.sysman.privilege.dao.impl;

import com.gever.sysman.privilege.dao.*;
import com.gever.sysman.privilege.dao.OperationDAO;
import com.gever.sysman.privilege.dao.PermissionDAO;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.ResourceDAO;
import com.gever.sysman.privilege.dao.RoleDAO;
import com.gever.sysman.privilege.dao.UserDAO;
import com.gever.sysman.privilege.model.I_User;
import com.gever.sysman.privilege.model.Operation;
import com.gever.sysman.privilege.model.PermissionMap;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.model.Role;
import com.gever.sysman.privilege.model.RolePermission;
import com.gever.sysman.privilege.model.UserPermission;
import com.gever.sysman.privilege.model.impl.DefaultOperation;
import com.gever.sysman.privilege.model.impl.DefaultPermissionMap;
import com.gever.sysman.privilege.model.impl.DefaultResource;
import com.gever.sysman.privilege.model.impl.DefaultRole;
import com.gever.sysman.privilege.model.impl.DefaultRolePermission;
import com.gever.sysman.privilege.model.impl.DefaultUser;
import com.gever.sysman.privilege.model.impl.DefaultUserPermission;
import com.gever.sysman.privilege.dao.ResOrderDAO;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultPrivilegeFactory extends PrivilegeFactory {
    //    private UserDAO userDAO;
    //    private RoleDAO roleDAO;
    //    private ResourceDAO resourceDAO;
    //    private PrivilegeDAO privilegeDAO;
    //    private PermissionDAO permissionDAO;
    //    static {
    //        userDAO = new DefaultUserDAO();
    //        roleDAO = new DefaultRoleDAO();
    //        resourceDAO = new DefaultResourceDAO();
    //        privilegeDAO = new DefaultPrivilegeDAO();
    //        permissionDAO = new DefaultPermissionDAO();
    //    }
    public Operation createOperation() {
        return new DefaultOperation();
    }

    public OperationDAO getOperationDAO() {
        return new DefaultOperationDAO();
    }

    public RolePermission createRolePermission() {
        return new DefaultRolePermission();
    }

    public UserPermission createUserPermission() {
        return new DefaultUserPermission();
    }

    /**
     * 获取ResourceDAO实例
     * @return
     */
    public ResourceDAO getResourceDAO() {
        return new DefaultResourceDAO();
    }

    /**
     * 获取RoleDAO实例
     * @return
     */
    public RoleDAO getRoleDAO() {
        return new DefaultRoleDAO();
    }

    /**
     * 获取UserDAO实例
     * @return
     */
    public UserDAO getUserDAO() {
        return new DefaultUserDAO();
    }

    /*
     * @see com.gever.privilege.dao.PermissionFactory#createResource()
     */
    public Resource createResource() {
        return new DefaultResource();
    }

    /*
     * @see com.gever.privilege.dao.PermissionFactory#createRole()
     */
    public Role createRole() {
        return new DefaultRole();
    }

    /*
     * @see com.gever.privilege.dao.PermissionFactory#createUser()
     */
    public I_User createUser() {
        return new DefaultUser();
    }
	public PermissionDAO getPermissionDAO() {
		  return new DefaultPermissionDAO();
	  }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.PrivilegeFactory#getSQLFactory()
     */
    public SQLFactory getSQLFactory() {
        return new DefaultSQLFactory();
    }
    /**
     * libiao add 2004-12-6
     * @return ResOrderDAO
     */
    public  ResOrderDAO getResOrderDAO(){
        return new ResOrderDAOIMP();
      }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.PrivilegeFactory#getPerMissionMapDAO()
     */
    public PermissionMapDAO getPerMissionMapDAO() {
        return new DefaultPermissionMapDAO();
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.PrivilegeFactory#createPermissionmap()
     */
    public PermissionMap createPermissionMap() {
        return new DefaultPermissionMap();
    }
}
