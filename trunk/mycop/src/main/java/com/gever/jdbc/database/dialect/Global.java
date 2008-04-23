/*
 * �������� 2004-4-27
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
	 * ��ȡ�ַ������͵�����
	 * @param name ������
	 * @return
	 */
	public static String getProperty(String name) {
		return Environment.getProperty(name);
	}

	/**
	 * ��ȡ��������
	 * @param name ������
	 * @param defaultValue Ĭ��ֵ
	 * @return
	 */
	public static int getIntProperty(String name, int defaultValue) {
		return Environment.getIntProperty(name,defaultValue);
	}

	/**
	 * ��ȡ����������
	 * @param name ������
	 * @param defaultValue Ĭ��ֵ
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
