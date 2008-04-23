package com.gever.util.log;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.gever.util.DateTimeUtils;

/**
 * <p>Title:Log档管理类 </p>
 * <p>Description: 主要采用单态模式</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: 天行</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class Log {

    //是否输出信息
    private static boolean debug = false;      // 是否debug状态
    private static boolean uselog4j = false;  // 是否使用log4j技术
    private static Log logSelf = null;        // 本身对象
    private static Logger logger = null;      // log4j对象

    //常量
    public static String TYPE_DEBUG = "debug";
    public static String TYPE_INFO = "info";
    public static String TYPE_WARN = "warn";
    public static String TYPE_ERROR = "error";
    private static Class classz = null;
    public static String propertiesFile = "";

    public Log(boolean debug) {
        this(debug, Log.class);
    }

    public Log(boolean debug, Class clazz) {
        this.debug = debug;
        initLogger(clazz);
    }

    public static Log getInstance() {
        return getInstance(Log.class);
    }

    /**
     * 得到Log类的实例
     * @param clazz class类
     * @return Log类
     */
    public static Log getInstance(Class clazz) {
        if (logSelf == null) {
            synchronized (Log.class) {
                Log inst = logSelf;
                if (inst == null) {
                    synchronized (Log.class) {
                        logSelf = new Log(debug, clazz);
                    }
                }
            }
        } else {
            initLogger(clazz);
        }

        return logSelf;
    }

    /**
     * 初始化Logger
     * @param clazz 传入相关的类名
     */
    public static void initLogger(Class clazz) {
        classz = clazz;
        if ( uselog4j == false)
            return ;

        //初始化log4j
        logger = Logger.getLogger(clazz);

        File propsFile = new File(propertiesFile);
        Properties Props = new Properties();
        try {
            Props.load(new FileInputStream(propsFile));
        } catch (Exception e) {
            System.err.println("不能读取属性文件. " +
                               "请确保" + propertiesFile + "在CLASSPATH指定的路径中");
        }

        PropertyConfigurator.configure(Props);
    }

    /**
     * 初始化Log
     * @param debug 是否debug
     * @param apropertiesFile 属性文件
     */
    public static void init(boolean debug, boolean useLog4 ,String apropertiesFile) {
        init(debug, useLog4,Log.class, apropertiesFile);
    }

    /**
     * 初始化Log
     * @param debug 是否调试
     * @param clazz 相关类名
     * @param apropertiesFile 属性文件
     */
    public static void init(boolean debug, boolean useLog4, Class clazz, String apropertiesFile) {
        propertiesFile = apropertiesFile;
         uselog4j = useLog4;
        logSelf = new Log(debug, clazz);
    }


    public void setDebug(boolean isDebug) {
        debug =  isDebug;
    }

    public static boolean getDebug() {
        return debug;
    }

    /**
     * @todo
     * 	打印错误信息
     * 	@param  msg 错误消息
     *  @param  type 错误类型
     */
    public void showLog(Object msg, String type) {
    	
    	String strMsg;
    	strMsg = msg==null?"***NULL msg***":msg.toString();

        if (uselog4j == false){
        	if (debug == true) strMsg = "["+DateTimeUtils.getCurrentTime() + "]<"+type+">"+strMsg;
        	this.formatPrint(strMsg);
        	return ;
        }

        if (debug == true) {
            if (type.equals(TYPE_DEBUG)) {
                logger.debug(strMsg);
            } else if (type.equals(TYPE_WARN)) {
                logger.warn(strMsg);
            } else if (type.equals(TYPE_ERROR)) {
                logger.error(strMsg);
            } else if (type.equals(TYPE_INFO)) {
                logger.info(strMsg);
            } else {
                logger.info(strMsg);
            }
        }
    }

    public void formatPrint(String msg)
    {
    	int j=0;
    	int i;
    	int lineMax=100;
    	String str=msg;
    	String curStr;
    	do {
    		i = (lineMax>str.length())?str.length():lineMax;
			curStr = str.substring(0, i); 
			str = str.substring(i);
			if (j>0) {
				curStr = "      "+curStr;
			} 
    		j = j+1;			
    		System.out.println(curStr);
		} while (str.length()>0);
    }
    /**
     * 	打印错误信息
     * 	@param  msg 错误信息
     */
    public void showLog(Object msg) {
        showLog(msg, TYPE_INFO);
    }
    
    public void console(Class claz,String info, Object msg) {
    	String strMsg;
    	if (msg == null)
    	{
    		strMsg="***NULL msg***";
    	}
    	else{
    		// 强制转换字串型
    		strMsg =msg.toString(); 		
    	}
    	String objectName = claz.getName();
    	objectName = objectName.substring((objectName.lastIndexOf(".")==-1)?0:objectName.lastIndexOf('.')+1);
   		strMsg = "["+DateTimeUtils.getCurrentTime()+"] <"+objectName+"><"+info+">"+strMsg;
       	this.formatPrint(strMsg);
        return ;
    }

    public static void setUselog4j(boolean istrue) {
        uselog4j = istrue;
    }
}