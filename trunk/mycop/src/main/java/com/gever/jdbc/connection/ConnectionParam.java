package com.gever.jdbc.connection ;

import java.io.Serializable;

public class ConnectionParam implements Serializable {
      //配置文件
      public static final String CONFIG_FILE="/SYSTEM.PROPERTIES";
       //
      public static final int CONN_TYPE_DEFAULT=0;//应用服务器连接池
      public static final int CONN_TYPE_DBCP=1;//APACHE的DPCP
      public static final int CONN_TYPE_JNDI=2;//JNDI形式
      public static final int CONN_TYPE_JDBC=3;//JDBC形式
      //public static final int CONN_TYPE_SELF_POOL=4;//自己写的
      //配置文件的KEY名称
      public static final String CONNECTION_TYPE="conntype";//连接池连接类型
      public static final String JNDI_NAME="jndiname";//APP server's jndi name
      public static final String DRIVER="driver";//数据库驱动程序
      public static final String URL="url";//数据连接的URL
      public static final String USER="user";//数据库用户名
      public static final String PASSWORD="password";//数据库密码
      public static final String MAXCONN="maxconn";//最大连接数
      public static final String MINCONN="minconn";//初始化连接数
      public static final String MAXACTIVE="maxactive";//最大连接活动数
      public static final String TIMEOUT="timeout";//连接的最大空闲时间 60秒
      public static final String WAITTIME="waittime";//取连接的时候如果没有可用连接最大的等待时间 3秒
      public static final String MAMAGETYPE="managetype";//管理策略
      public static final String PSTYPE="pstype";//pool prepared statements catch 应用开关
      public static final String PSCOUNT="pscount";//最大pool prepared statements catch 个数




/**
* ConnectionParam 构造子注解。
*/
private ConnectionParam() {
//      super();
}

}