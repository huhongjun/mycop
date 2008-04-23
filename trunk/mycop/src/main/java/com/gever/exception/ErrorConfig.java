package com.gever.exception;

import java.io.*;
import java.util.*;

/**
 * <p>Title: ���������ļ�</p>
 * <p>Description: �Ե�̬ģʽ�õ����������ļ���Ϣ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author 
 * @version 0.5
 */

public class ErrorConfig {

    private ErrorConfig() {
    }

    private static String configFile = "error.properties";  //�����ļ�
    private static ErrorConfig objectConfig = null;
    private Properties properties = null;
    private static String path = "";

    /**
     * �õ����������ļ����ʵ��
     * @return ʵ��
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
     * �õ����������ļ����ʵ��
     * @return ʵ��
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
     * �õ������ļ�����
     * @return �ļ�����
     */
    public Properties getProperties() {
        return this.properties;
    }
    /**
     * װ���ļ�����
     * @throws IOException
     */
    private void loadProperties() throws IOException {
        InputStream in = null;
        File propsFile = new File( path+configFile);
         properties = new Properties();
        try {
            properties.load(new FileInputStream(propsFile));
        } catch (Exception e) {
            System.err.println("���ܶ�ȡ�����ļ�. " +
                               "��ȷ��" +  path+configFile + "��CLASSPATHָ����·����");
        }

    }

    /**
     * ��ȡ�ַ������͵�����
     * @param name ������
     * @return
     */
    public  String getProperty(String name) {
            return properties.getProperty(name);
    }


    public static void setPath(String apath) {
        path = apath;
    }

}