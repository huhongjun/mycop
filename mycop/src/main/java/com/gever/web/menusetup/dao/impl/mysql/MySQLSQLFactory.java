/*
 * 创建日期 2004-11-1
 */
package com.gever.web.menusetup.dao.impl.mysql;

import java.util.Properties;

import com.gever.web.menusetup.dao.impl.DefaultSQLFactory;

/**
 * @author Hu.Walker
 */
public class MySQLSQLFactory extends DefaultSQLFactory {
	private static Properties m_queries = null;
	private static String SQLFile = "com/gever/web/menusetup/dao/impl/mysql/sql.properties";

	/**
	 * 构造函数
	 */
	public MySQLSQLFactory() {
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
		String value = m_queries.getProperty(queryName);

		if (value == null) {
			value = super.get(queryName);
		}

		if (value == null) {
			System.err.println("[SQLFactory] query '" + queryName +
				"' not found in sql.properties!");
		}

		return value;
	}
}
