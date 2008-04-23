/**
 * 
 */
package com.gever.util;

import com.gever.jdbc.sqlhelper.SQLHelperUtil;
import com.gever.util.log.Log;

/**
 * @author Hu.Walker
 * ���Կ��ǼӸ���ĳ�ʼ�����������ü������Ȼ��Ϊ����дһ��������
 */
public class InitTestUtil {

	/**
	 * 
	 */
	public static void init(){
		boolean debug = true;
		boolean	uselog4j = true;
		String 	debugFile = "./testDir/resource/debug.properties";
		
		String 	dbtype = "mysql"; 
        //��ʼ��Log��
        Log.init(debug, uselog4j, debugFile);
        Log.getInstance();
        com.gever.jdbc.sqlhelper.SQLHelperUtil sUtil = new SQLHelperUtil();
        sUtil.setDbtype(dbtype);
   	}
}
