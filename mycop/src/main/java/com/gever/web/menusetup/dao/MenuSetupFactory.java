/*
 * 创建日期 2004-10-25
 */
package com.gever.web.menusetup.dao;

import com.gever.config.Environment;

/**
 * @author Hu.Walker
 */
public abstract class MenuSetupFactory {
    private static MenuSetupFactory instance;
    
    protected MenuSetupFactory() {
    }
    
    public static synchronized MenuSetupFactory getInstance() {
        if (instance == null) {
            String privilegeFactoryClassName = null;

            try {
                privilegeFactoryClassName = Environment.getProperty(
                        "Sysman.MenuSetupFactoryClassName");

                if ((privilegeFactoryClassName == null) ||
                        "".equals(privilegeFactoryClassName)) {
                    privilegeFactoryClassName = "com.gever.web.menusetup.dao.impl.mysql.DefaultMenuSetupFactory";
                }

                Class c = Class.forName(privilegeFactoryClassName);
                instance = (MenuSetupFactory) c.newInstance();
            } catch (Exception e) {
                System.err.println("Failed to load PrivilegeFactory class " +
                    privilegeFactoryClassName);
                e.printStackTrace();

                return null;
            }
        }
        return instance;
    }
    
    public abstract MenuSetupDao getMenuSetupDao();
    
    public abstract SQLFactory getSQLFactory();
}
