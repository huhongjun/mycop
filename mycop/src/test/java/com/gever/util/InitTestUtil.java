/**
 * 
 */
package com.gever.util;

import com.gever.jdbc.sqlhelper.SQLHelperUtil;
import com.gever.util.log.Log;

/**
 * @author Hu.Walker
 * 可以考虑加更多的初始化、参数配置检测任务，然后为该类写一个测试类
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
        //初始化Log类
        Log.init(debug, uselog4j, debugFile);
        Log.getInstance();
        com.gever.jdbc.sqlhelper.SQLHelperUtil sUtil = new SQLHelperUtil();
        sUtil.setDbtype(dbtype);
   	}
}
