package com.gever.sysman.privilege.dao;

import com.gever.config.Environment;
import com.gever.sysman.privilege.model.I_User;
import com.gever.sysman.privilege.model.Operation;
import com.gever.sysman.privilege.model.PermissionMap;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.model.Role;
import com.gever.sysman.privilege.model.RolePermission;
import com.gever.sysman.privilege.model.UserPermission;


/**
@author Hu.Walker
 */
public abstract class PrivilegeFactory {

    /**
    @clientCardinality 1
    @supplierCardinality 1
    @supplierRole factory
     */
    private static PrivilegeFactory factory = null;

    /**
    @return com.gever.sysman.privilege.dao.PrivilegeFactory
    @roseuid 40BAB9B00167
     */
    public static synchronized PrivilegeFactory getInstance() {
        if (factory == null) {
            String privilegeFactoryClassName = null;

            try {
                privilegeFactoryClassName = Environment.getProperty(
                        "Sysman.PrivilegeFactoryClassName");

                if ((privilegeFactoryClassName == null) ||
                        "".equals(privilegeFactoryClassName)) {
                    privilegeFactoryClassName = "com.gever.sysman.privilege.dao.impl.DefaultPrivilegeFactory";
                }

                Class c = Class.forName(privilegeFactoryClassName);
                factory = (PrivilegeFactory) c.newInstance();
            } catch (Exception e) {
                System.err.println("Failed to load PrivilegeFactory class " +
                    privilegeFactoryClassName);
                e.printStackTrace();

                return null;
            }
        }

        return factory;
    }

    /**
    ¥¥ΩΩ””ªª
    @return
    @roseuid 40BAB9B00177
     */
    public abstract I_User createUser();

    /**
    ¥¥ΩΩΩΩ……
    @return
    @roseuid 40BAB9B001C5
     */
    public abstract Role createRole();

    public abstract RolePermission createRolePermission();

    public abstract UserPermission createUserPermission();

    /**
    ¥¥ΩΩ◊◊‘‘
    @return
    @roseuid 40BAB9B001E4
     */
    public abstract Resource createResource();

    /**
    ¥¥ΩΩ≤≤◊◊
    @return
    @roseuid 40BAB9B00203
     */
    public abstract Operation createOperation();

    /**
    ªª»»ResourceDAO  ¿¿
    @return
    @roseuid 40BAB9B00271
     */
    public abstract ResourceDAO getResourceDAO();

    /**
    ªª»»RoleDAO  ¿¿
    @return
    @roseuid 40BAB9B00290
     */
    public abstract RoleDAO getRoleDAO();

    /**
    ªª»»UserDAO  ¿¿
    @return
    @roseuid 40BAB9B002AF
     */
    public abstract UserDAO getUserDAO();

    /**
    ªª»»OperationDAO  ¿¿
    @return
    @roseuid 40BAB9B002DE
     */
    public abstract OperationDAO getOperationDAO();

      public abstract PermissionDAO getPermissionDAO();

      public abstract SQLFactory getSQLFactory();

  /**
   * libiao add ResOrderDAO2004/12/6
   * @return ResOrderDAO
   */
  public abstract ResOrderDAO getResOrderDAO();

  // modified by eddy on 20041206 ---------------\\\
    public abstract PermissionMapDAO getPerMissionMapDAO();
    
    public abstract PermissionMap createPermissionMap();
  // modified by eddy on 20041206 ---------------///
}
