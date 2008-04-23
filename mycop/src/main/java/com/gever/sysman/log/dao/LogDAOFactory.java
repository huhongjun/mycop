/*
 * 创建日期 2004-10-25
 */
package com.gever.sysman.log.dao;

import com.gever.config.Environment;

/**
 * @author Hu.Walker
 */
public class LogDAOFactory {
    private static LogDAOFactory factory;
    
    protected LogDAOFactory() {
    }
    
    public static synchronized LogDAOFactory getInstance() {
        String organizationFactoryClassName = null;

        if (factory == null) {
            try {
                organizationFactoryClassName = Environment.getProperty(
                        "Sysman.LogDAOFactoryClassName");

                if ((organizationFactoryClassName == null) ||
                        "".equals(organizationFactoryClassName)) {
                    organizationFactoryClassName = "com.gever.sysman.log.dao.LogDAOFactory";
                }

                Class c = Class.forName(organizationFactoryClassName);
                factory = (LogDAOFactory) c.newInstance();
            } catch (Exception e) {
                System.err.println("Failed to load LogDAOFactory class " +
                    organizationFactoryClassName);
                e.printStackTrace();

                return null;
            }
        }

        return factory;
    }
    
    public LogDao getLogDao() {
        return new LogDaoImpl();
    }
}
