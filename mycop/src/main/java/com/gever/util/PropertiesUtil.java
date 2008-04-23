/*
 * 创建日期 2004-5-26
 */
package com.gever.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * @author Hu.Walker
 */
public class PropertiesUtil {

	/**
	 * 依据属性的第一个Token，将属性文件分解为多个Properties
	 * @param properties
	 * @return
	 */
	public static Map split(Properties properties) {
		Map result = new Hashtable();
		if (properties == null) {
			return result;
		}
		for (Enumeration keys = properties.keys();keys.hasMoreElements();) {
			String key = (String)keys.nextElement();
			StringTokenizer tokens = new StringTokenizer(key,".");

			if (tokens.countTokens() < 2) {
				continue;
			}
			//连接池名
			String name = (String)tokens.nextToken();
			int dotIndex = key.indexOf(".");

			//属性名
			String realKey = key.substring(dotIndex + 1);
			//属性值
			String realValue = properties.getProperty(key);

			Properties p = (Properties)result.get(name);
			p = p == null ? new Properties() : p;
			p.put(realKey,realValue);
			result.put(name,p);
		}

		return result;
	}
}
