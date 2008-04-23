package com.gever.jdbc.connection ;

import java.io.Serializable;

public class ConnectionParam implements Serializable {
      //�����ļ�
      public static final String CONFIG_FILE="/SYSTEM.PROPERTIES";
       //
      public static final int CONN_TYPE_DEFAULT=0;//Ӧ�÷��������ӳ�
      public static final int CONN_TYPE_DBCP=1;//APACHE��DPCP
      public static final int CONN_TYPE_JNDI=2;//JNDI��ʽ
      public static final int CONN_TYPE_JDBC=3;//JDBC��ʽ
      //public static final int CONN_TYPE_SELF_POOL=4;//�Լ�д��
      //�����ļ���KEY����
      public static final String CONNECTION_TYPE="conntype";//���ӳ���������
      public static final String JNDI_NAME="jndiname";//APP server's jndi name
      public static final String DRIVER="driver";//���ݿ���������
      public static final String URL="url";//�������ӵ�URL
      public static final String USER="user";//���ݿ��û���
      public static final String PASSWORD="password";//���ݿ�����
      public static final String MAXCONN="maxconn";//���������
      public static final String MINCONN="minconn";//��ʼ��������
      public static final String MAXACTIVE="maxactive";//������ӻ��
      public static final String TIMEOUT="timeout";//���ӵ�������ʱ�� 60��
      public static final String WAITTIME="waittime";//ȡ���ӵ�ʱ�����û�п����������ĵȴ�ʱ�� 3��
      public static final String MAMAGETYPE="managetype";//�������
      public static final String PSTYPE="pstype";//pool prepared statements catch Ӧ�ÿ���
      public static final String PSCOUNT="pscount";//���pool prepared statements catch ����




/**
* ConnectionParam ������ע�⡣
*/
private ConnectionParam() {
//      super();
}

}