/*
 * �������� 2004-4-26
 *
 */
package com.gever.web.util;

/**
 * URL��ش���
 * @author Hu.Walker
 */
public class URLBuilder {
	
	/**
	 * Ϊurl����һ������
	 * @param url url
	 * @param keyValue key,value��,��ʽΪkey=value
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
