/*
 * �������� 2004-5-18
 * �޸�  2004-5-18 ������ �޸�InitΪpublic �����˳�ʼ����
 * �����߲� 2004-05-28 15��13 zhouens
 */
package com.gever.jdbc.connection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.gever.exception.DefaultException;
import com.gever.jdbc.connection.impl.*;
import com.gever.util.NumberUtil;
import com.gever.util.PropertiesUtil;
import com.gever.util.log.Log;

import com.gever.config.Environment;

/**
 * ConnectionProvider����
 * @author Hu.Walker
 */
public final class ConnectionProviderFactory {
	private static Map connectionProviders = new HashMap();
	private static Log log = Log.getInstance(ConnectionProviderFactory.class);

        private static boolean isInitialed = false;//�Ƿ��ʼ��
	/**
	 * ���������ļ���ʼ��
	 */
	static {
		try {
			init();
//                        System.out.println("DB Init");
		}catch(DefaultException e) {
                  log.showLog(e, Log.TYPE_ERROR);
//			System.err.println(e);
		}
	}

	private ConnectionProviderFactory() {
	}

	/**
	 * ��ȡConnectionProvider
	 * @param name
	 * @return
	 * @throws DefaultException
	 */
	public static ConnectionProvider getConnectionProvider(String name) throws DefaultException {
		ConnectionProvider result = (ConnectionProvider)connectionProviders.get(name);
		if (result == null) {
			log.showLog("���ӳ�" + name + "δ�ҵ�!");
			throw new DefaultException("���ӳ�" + name + "δ�ҵ�!");
		}
		return result;
	}

	/**
	 * ����Properties����ConnectionProvider��Ĭ�ϲ�����ԭ�е����ӳ�
	 * @param properties
	 * @return
	 * @throws ConnectionException
	 */
	public static ConnectionProvider newConnectionProvider(String name,Properties properties) throws DefaultException {
		return newConnectionProvider(name,properties,false);
	}

	/**
	 * ����Properties����ConnectionProvider
	 * @param name
	 * @param properties
	 * @param createNew �Ƿ񸲸�ԭ�е����ӳ�
	 * @return
	 * @throws DefaultException
	 */
	public static ConnectionProvider newConnectionProvider(String name,Properties properties,boolean createNew) throws DefaultException {
		ConnectionProvider provider = (ConnectionProvider)connectionProviders.get(name);
		if (provider != null && !createNew) {
			return provider;
		}

		// �ж����ӳ�����
		if (NumberUtil.stringToInt(properties.getProperty(ConnectionParam.CONNECTION_TYPE ))==ConnectionParam.CONN_TYPE_JDBC ) {
			provider = new JDBCConnectionProvider();
                }if (NumberUtil.stringToInt(properties.getProperty(ConnectionParam.CONNECTION_TYPE ))==ConnectionParam.CONN_TYPE_JNDI ) {
                        provider = new JNDIConnectionProvider();
                }if (NumberUtil.stringToInt(properties.getProperty(ConnectionParam.CONNECTION_TYPE ))==ConnectionParam.CONN_TYPE_DBCP  ) {
                        provider = new DBCPConnectionProvider();
                }
        provider.configure(properties);
		connectionProviders.put(name,provider);
		return provider;
	}

	/**
	 * ��ʼ��
	 * @throws DefaultException
	 */
	public static void init() throws DefaultException {
                if(!isInitialed)
		  init(PropertiesUtil.split(Environment.getProperties(ConnectionParam.CONFIG_FILE)));
	}

	/**
	 * ��ʼ��
	 * @param properties
	 * @throws DefaultException
	 */
	public static void init(Map properties) throws DefaultException {
		if (properties == null || properties.size() == 0) {
			return;
		}

		for (Iterator it = properties.keySet().iterator();it.hasNext();) {
                        String key = (String)it.next();
                        Properties temp = (Properties)properties.get(key);
			ConnectionProviderFactory.newConnectionProvider(key,temp);
		}
                isInitialed = true;
	}
}
