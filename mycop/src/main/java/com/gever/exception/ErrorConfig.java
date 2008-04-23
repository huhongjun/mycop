package com.gever.exception;

import java.io.*;
import java.util.*;

/**
 * <p>Title: 错误配置文件</p>
 * <p>Description: 以单态模式得到错误配置文件信息</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author 
 * @version 0.5
 */

public class ErrorConfig {

    private ErrorConfig() {
    }

    private static String configFile = "error.properties";  //配置文件
    private static ErrorConfig objectConfig = null;
    private Properties properties = null;
    private static String path = "";

    /**
     * 得到错误配置文件类的实例
     * @return 实例
     * @throws IOException
     */
    public static ErrorConfig getInstance() throws IOException {
        if (objectConfig == null) {
            synchronized (ErrorConfig.class) {
                ErrorConfig inst = objectConfig;
                if (inst == null) {
                    synchronized (ErrorConfig.class) {
                        objectConfig = new ErrorConfig();
                        objectConfig.loadProperties();
                    }
                }
            }
        }
        return objectConfig;
    }

    /**
     * 得到错误配置文件类的实例
     * @return 实例
     * @throws IOException
     */
    public static ErrorConfig getInstance(String fileName) throws IOException {
        if (objectConfig == null) {
            synchronized (ErrorConfig.class) {
                ErrorConfig inst = objectConfig;
                if (inst == null) {
                    synchronized (ErrorConfig.class) {
                        objectConfig = new ErrorConfig();
                        configFile = fileName;
                        objectConfig.loadProperties();
                    }
                }
            }
        }
        return objectConfig;
    }

    /**
     * 得到错误文件属性
     * @return 文件属性
     */
    public Properties getProperties() {
        return this.properties;
    }
    /**
     * 装载文件属性
     * @throws IOException
     */
    private void loadProperties() throws IOException {
        InputStream in = null;
        File propsFile = new File( path+configFile);
         properties = new Properties();
        try {
            properties.load(new FileInputStream(propsFile));
        } catch (Exception e) {
            System.err.println("不能读取属性文件. " +
                               "请确保" +  path+configFile + "在CLASSPATH指定的路径中");
        }

    }

    /**
     * 获取字符串类型的属性
     * @param name 属性名
     * @return
     */
    public  String getProperty(String name) {
            return properties.getProperty(name);
    }


    public static void setPath(String apath) {
        path = apath;
    }

}