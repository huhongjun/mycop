package com.gever.jdbc.connection ;

import java.util.Properties;
import com.gever.exception.DefaultException;
/**

 */
public interface ConnectionProvider {
        /**
         * ��ʼ������
         * @param props <tt>SessionFactory</tt> properties
         */
        public void configure(Properties props) throws DefaultException ;
        /**
         * ��ȡһ������
         * @return a JDBC connection
         * @throws SQLException
         */
        public java.sql.Connection getConnection() throws DefaultException ;
        /**
         * �ر��������ӳ�
         * @param conn a JDBC connection
         * @throws SQLException
         */
        public void close() throws DefaultException;
}