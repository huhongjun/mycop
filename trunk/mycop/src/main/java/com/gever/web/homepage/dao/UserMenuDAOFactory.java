/*
 * 创建日期 2004-10-25
 */
package com.gever.web.homepage.dao;

import com.gever.config.Environment;

/**
 * @author Hu.Walker
 */
public abstract class UserMenuDAOFactory {
    private static UserMenuDAOFactory instance;

    protected UserMenuDAOFactory() {
    }

    public static UserMenuDAOFactory getInstance() {
        if (instance == null) {
            String privilegeFactoryClassName = null;

            try {
                privilegeFactoryClassName = Environment.getProperty(
                        "Sysman.UserMenuDAOFactory");

                if ((privilegeFactoryClassName == null) ||
                        "".equals(privilegeFactoryClassName)) {
                    privilegeFactoryClassName = "com.gever.web.homepage.dao.impl.DefaultUserMenuDAOFactory";
                }

                Class c = Class.forName(privilegeFactoryClassName);
                instance = (UserMenuDAOFactory) c.newInstance();
            } catch (Exception e) {
                System.err.println("Failed to load UserMenuDAOFactory class " +
                    privilegeFactoryClassName);
                e.printStackTrace();

                return null;
            }
        }
        return instance;
    }

    public abstract UserMenuDao getUserMenuDao();
    public abstract CreateDefaultMenuDAO getCreateDefaultMenuDao();
}
