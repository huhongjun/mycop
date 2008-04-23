/*
 * 创建日期 2004-4-26
 *
 */
package com.gever.web.util;

/**
 * URL相关处理
 * @author Hu.Walker
 */
public class URLBuilder {
	
	/**
	 * 为url增加一个参数
	 * @param url url
	 * @param keyValue key,value对,形式为key=value
	 * @return
	 */
	public static String addParameter(String url,String keyValue) {
		if (url == null) {
			return null;
		}
		int index = url.indexOf("?");
		if (index < 0) {
			return url + "?" + keyValue;
		}else {
			return url + "&" + keyValue;
		}
	}
}
