/*
 * 创建日期 2004-4-27
 *
 */
package com.gever.jdbc.database.dialect;

import com.gever.config.Environment;

/**
 * @author Hu.Walker
 */
public final class Global {

	private Global() {
	}

	/**
	 * 获取字符串类型的属性
	 * @param name 属性名
	 * @return
	 */
	public static String getProperty(String name) {
		return Environment.getProperty(name);
	}

	/**
	 * 获取整型属性
	 * @param name 属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public static int getIntProperty(String name, int defaultValue) {
		return Environment.getIntProperty(name,defaultValue);
	}

	/**
	 * 获取长整型属性
	 * @param name 属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public static long getLongProperty(String name, long defaultValue) {
		return Environment.getLongProperty(name,defaultValue);
	}
	
	public static Dialect getDialect() {
		String dialect = Environment.getProperty("database.dialect");
		try {
			return (Dialect)Class.forName(dialect).newInstance();
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
