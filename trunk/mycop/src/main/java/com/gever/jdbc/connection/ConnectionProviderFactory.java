/*
 * 创建日期 2004-5-18
 * 修改  2004-5-18 李立波 修改Init为public 增加了初始检验
 * 代码走查 2004-05-28 15：13 zhouens
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
 * ConnectionProvider工厂
 * @author Hu.Walker
 */
public final class ConnectionProviderFactory {
	private static Map connectionProviders = new HashMap();
	private static Log log = Log.getInstance(ConnectionProviderFactory.class);

        private static boolean isInitialed = false;//是否初始化
	/**
	 * 根据配置文件初始化
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
	 * 获取ConnectionProvider
	 * @param name
	 * @return
	 * @throws DefaultException
	 */
	public static ConnectionProvider getConnectionProvider(String name) throws DefaultException {
		ConnectionProvider result = (ConnectionProvider)connectionProviders.get(name);
		if (result == null) {
			log.showLog("连接池" + name + "未找到!");
			throw new DefaultException("连接池" + name + "未找到!");
		}
		return result;
	}

	/**
	 * 根据Properties创建ConnectionProvider，默认不覆盖原有的连接池
	 * @param properties
	 * @return
	 * @throws ConnectionException
	 */
	public static ConnectionProvider newConnectionProvider(String name,Properties properties) throws DefaultException {
		return newConnectionProvider(name,properties,false);
	}

	/**
	 * 根据Properties创建ConnectionProvider
	 * @param name
	 * @param properties
	 * @param createNew 是否覆盖原有的连接池
	 * @return
	 * @throws DefaultException
	 */
	public static ConnectionProvider newConnectionProvider(String name,Properties properties,boolean createNew) throws DefaultException {
		ConnectionProvider provider = (ConnectionProvider)connectionProviders.get(name);
		if (provider != null && !createNew) {
			return provider;
		}

		// 判断连接池类型
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
	 * 初始化
	 * @throws DefaultException
	 */
	public static void init() throws DefaultException {
                if(!isInitialed)
		  init(PropertiesUtil.split(Environment.getProperties(ConnectionParam.CONFIG_FILE)));
	}

	/**
	 * 初始化
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
