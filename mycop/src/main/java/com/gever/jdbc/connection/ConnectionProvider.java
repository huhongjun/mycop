package com.gever.jdbc.connection ;

import java.util.Properties;
import com.gever.exception.DefaultException;
/**

 */
public interface ConnectionProvider {
        /**
         * 初始化参数
         * @param props <tt>SessionFactory</tt> properties
         */
        public void configure(Properties props) throws DefaultException ;
        /**
         * 拿取一个连接
         * @return a JDBC connection
         * @throws SQLException
         */
        public java.sql.Connection getConnection() throws DefaultException ;
        /**
         * 关闭整个连接池
         * @param conn a JDBC connection
         * @throws SQLException
         */
        public void close() throws DefaultException;
}