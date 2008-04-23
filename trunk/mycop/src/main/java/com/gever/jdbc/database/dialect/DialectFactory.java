/*
 * 创建日期 2004-11-19
 */
package com.gever.jdbc.database.dialect;

import com.gever.config.Environment;


/**
 * @author Hu.Walker
 */
public class DialectFactory {
    private static Dialect instance = null;

    private DialectFactory() {
    }

    public static Dialect getDialect() {
        if (instance == null) {
            String dialect = Environment.getProperty("database.dialect");

            try {
                instance = (Dialect) Class.forName(dialect).newInstance();
            } catch (Exception e) {
                System.err.println(e.getMessage());

                return null;
            }
        }

        return instance;
    }
}
