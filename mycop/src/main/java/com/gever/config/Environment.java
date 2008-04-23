/*
 * 创建日期 2004-5-18
 */
package com.gever.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author 
 * 
 * 用于读取属性文件
 * 
 */
public class Environment {
	private static String FILE_NAME = "/gever_config.properties";
	private static Properties properties;

	static {
		properties = getProperties(FILE_NAME);
	}

	private Environment() {
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties getProperties(String fileName) {
		Properties result = new Properties();
		try {
			result.load(Environment.class.getResourceAsStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取Properties
	 * @return
	 */
	public static Properties getProperties() {
		return properties;
	}
	
	/**
	 * 获取字符串类型的属性
	 * @param name 属性名
	 * @return
	 */
	public static String getProperty(String name) {
		return properties.getProperty(name);
	}

	/**
	 * 获取整型属性
	 * @param name 属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public static int getIntProperty(String name, int defaultValue) {
		try {
			return Integer.parseInt(getProperty(name));
		} catch (NumberFormatException e) {
			System.err.println(name + "格式错误");
			return defaultValue;
		} catch (NullPointerException e) {
			System.err.println("未找到属性" + name);
			return defaultValue;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return defaultValue;
		}
	}

	/**
	 * 获取长整型属性
	 * @param name 属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public static long getLongProperty(String name, long defaultValue) {
		try {
			return Long.parseLong(getProperty(name));
		} catch (NumberFormatException e) {
			System.err.println(name + "格式错误");
			return defaultValue;
		} catch (NullPointerException e) {
			System.err.println("未找到属性" + name);
			return defaultValue;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return defaultValue;
		}
	}
}
