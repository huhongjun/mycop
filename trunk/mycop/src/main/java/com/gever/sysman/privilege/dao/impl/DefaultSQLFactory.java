/*
 * 创建日期 2004-10-12
 */
package com.gever.sysman.privilege.dao.impl;

import com.gever.sysman.privilege.dao.SQLFactory;

import java.util.Properties;


/**
 *
 * @author Hu.Walker
 */
public class DefaultSQLFactory implements SQLFactory {
    private static Properties m_queries = null;
    private static String SQLFile = "com/gever/sysman/privilege/dao/impl/sql.properties";

    /**
     * 构造函数
     */
    public DefaultSQLFactory() {
        if (m_queries == null) {
            m_queries = new Properties();

            try {
                m_queries.load(getClass().getClassLoader().getResourceAsStream(SQLFile));
            } catch (NullPointerException exc) {
                System.err.println("[SQLFactory] cannot get " + SQLFile);
            } catch (java.io.IOException exc) {
                System.err.println("[SQLFactory] cannot get " + SQLFile);
            }
        }
    }

    /**
     * Get the value for the query name
     *
     * @param queryName the name of the property
     * @return The value of the property
     */
    public String get(String queryName) {
        String value = null;

        if ((value = m_queries.getProperty(queryName)) == null) {
            System.err.println("[SQLFactory] query '" + queryName +
                "' not found in sql.properties!");
        }

        return value;
    }
}
