package com.gever.jdbc.sqlhelper;

import java.sql.*;
//import com.wnb.code.db.ConnectionPool;
import java.util.*;

import com.gever.exception.*;
import com.gever.jdbc.connection.*;
import com.gever.util.log.*;
import com.gever.vo.*;

/**
 * <p>Title: SQLHelper父类</p>
 * <p>Description: SQLHelper父类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public abstract class SQLHelper {
    public SQLHelper() {
    }
    public SQLHelper(String dbData){
        this.dbData = dbData;

    }
   
    private String dbData ="";
    protected Connection connection = null;
    protected boolean autoCommit = true; // 是否自动提交
    protected boolean autoClose = true; // 是否自动关闭
    protected boolean bakAutoCommit = true; //备份用的
    public static final String CONNECTION_ERROR = "sys_id_001";
    public static final String QUERY_ERROR = "sys_id_003";
    public static final String UPDATE_ERROR = "sys_id_004";
    public static final String PARAM_ERROR = "sys_id_005";

    public static final String CLOSE_CONN_ERROR = "sys_id_010";
    public static final String CLOSE_RS_ERROR = "sys_id_011";
    public static final String CLOSE_PS_ERROR = "sys_id_012";
    public static final String CLOSE_ST_ERROR = "sys_id_013";
    protected boolean autoID = true;

    protected Log log = Log.getInstance(SQLHelper.class);
    //private ConnectionPool pool = ConnectionPool.getInstance();

    /**
     * 关闭数据库连结对象
     * @param conn Connection对象
     * @throws DefaultException
     */

    public void close(java.sql.Connection conn) throws DefaultException {
        try {
            if (conn != null && conn.isClosed() == false)
                conn.close();
        } catch (java.sql.SQLException sqlEx) {
            sqlEx.printStackTrace();
            //throw new DefaultException(CLOSE_CONN_ERROR);
        }
    }

    /**
     * 关闭ResultSet对象
     * @param rs ResultSet对象
     * @throws DefaultException
     */

    public void close(ResultSet rs) throws DefaultException {
        try {
            if (rs != null)
                rs.close();
        } catch (java.sql.SQLException sqlEx) {
            if (log.getDebug()) {
                sqlEx.printStackTrace(System.err);
            }

            throw new DefaultException(CLOSE_RS_ERROR);
        }
    }

    /**
     * 关闭PreparedStatement对象
     * @param pstmt PreparedStatement对象
     * @throws DefaultException
     */

    public void close(PreparedStatement pstmt)   {
        try {
            if (pstmt != null )
                pstmt.close();
        } catch (java.sql.SQLException sqlEx) {
            if ( log.getDebug()){
                sqlEx.printStackTrace(System.err);
            }
        }
    }

    /**
     * 关闭Statement对象
     * @param stmt Statement对象
     * @throws DefaultException
     */
    public void close(Statement stmt) throws DefaultException {
        try {
            if (stmt != null)
                stmt.close();
        } catch (java.sql.SQLException sqlEx) {
            if (log.getDebug()) {
                sqlEx.printStackTrace(System.err);
            }

            throw new DefaultException(CLOSE_ST_ERROR);
        }
    }

    //得到数据库连接
    public void begin() throws DefaultException{

        this.setAutoClose(false); //不关闭数据库连结
        connection = getConnection();
        try{
            connection.setAutoCommit(false);
        }catch (SQLException e ){
            log.showLog(" 事务设置出错 ");
            e.printStackTrace();
        }
    }

    public void setConnection (Connection conn)throws DefaultException {
			this.connection = conn;
    }

    //关闭数据库连接
    public void end() throws DefaultException {
        this.setAutoClose(true); //不关闭数据库连结
        close(connection);
    }

    //得到数据库连结
    public Connection getConnection() throws DefaultException{
        try {
            if (connection == null || connection.isClosed() == true) {
              //  System.out.println("-------dbData-" + dbData);
                ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.dbData);
                connection = cp.getConnection();
//                ConnectionPool pool = ConnectionPool.getInstance();
//                connection = pool.getConnection();
                this.bakAutoCommit = connection.getAutoCommit();
            }
        } catch (SQLException e) {
            if (log.getDebug()) {
                e.printStackTrace(System.err);
            }
        }
        return connection;
    }

    //提交
    public void commit() {
        try {
            if (connection != null && connection.isClosed() == false)
                connection.commit();
            connection.setAutoCommit(this.bakAutoCommit);
        } catch (java.sql.SQLException sqlEx) {
            if (log.getDebug()) {
                sqlEx.printStackTrace(System.err);
            }
        }
    }

    // 回滚
    public void rollback() {
        try {
            if (connection != null && connection.isClosed() == false)
                connection.rollback();
        } catch (java.sql.SQLException sqlEx) {
            if (log.getDebug()) {
                sqlEx.printStackTrace(System.err);
            }
        }
    }

    /**
     * @param  autoCommit   设置自动提交
     */

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    /**
     * @param  autoClose   是否自动关闭数据库链接
     */
    public void setAutoClose(boolean autoClose) {
        this.autoClose = autoClose;
    }
    /**
     * 设置ID
     * @param autoID 是否自增长的ID
     */
    public void setAutoID(boolean autoID) {
        this.autoID = autoID;
    }

    /**
     * 查询简单的SQL语句
     * @param strsql sql语句(String)
     * @param type 查询类型(int)
     * @return 返回结果
     * @throws DefaultException
     */
    public abstract Object query(String strsql, int type) throws DefaultException ;

    /**
     * 查询一般SQL语句
     * @param strsql sql语句
     * @return 返回结果
     * @throws DefaultException
     */
    public abstract Object query(String strsql) throws DefaultException ;

    /**
     * 查询sql 语句,ArrayList记录以VO行式封装
     * @param strsql sql语句
     * @param view vo对象
     * @return 返回结果
     * @throws DefaultException
     */
    public abstract List queryByListVo(String strsql, VOInterface view) throws DefaultException;

    /**
     * 按主键查询
     * @param vo vo对象(注vo对象,要给vo对象的主键进行赋值,否则是会出错)
     * @return 返回结果
     * @throws DefaultException
     */
    public abstract Object queryByPK(VOInterface vo) throws DefaultException ;

    /**
     * 查询sql 语句,ArrayList记录以VO行式封装
     * @param strsql sql语句
     * @param vo vo对象
     * @return 返回结果
     * @throws DefaultException
     */
    public abstract Object queryByVo(String strsql, VOInterface vo) throws DefaultException ;

    /**
     * 查询一般SQL语句
     * @param strsql sql语句(String)
     * @param view VO对象(VOInteface)
     * @param type 查询类型(DataTypes)
     * @return 返回结果
     * @throws DefaultException
     */

    public abstract Object query(String strsql, VOInterface view, int type) throws DefaultException ;

    /**
     * 查询一般SQL语句
     * @param strsql strsql[0]为sql语句,strsql[1]为类型如"string,date,int":
     * 注:以类型之间以逗号分隔,并且类型与aryValue的个数要相匹配
     * @param aryValues 值列表
     * @param type 查询类型
     * @return 返回结果
     * @throws DefaultException
     */
    public abstract Object query(String[] strsql, List aryValues, int type) throws DefaultException ;

    /**
     * 查询一般SQL语句
     * @param strsql strsql[0]为sql语句,strsql[1]为类型如"string,date,int":
     * 注:以类型之间以逗号分隔,并且类型与aryValue的个数要相匹配
     * @param aryValues 值列表
     * @param type 查询类型
     * @return 返回结果
     * @throws DefaultException
     */
    public abstract Object query(String[] strsql, String[] aryValues, int type) throws DefaultException ;

    /**
     * 查询一般SQL语句
     * @param strsql strsql[0]为sql语句,strsql[1]为类型如"string,date,int":
     * 注:以类型之间以逗号分隔,并且类型与aryValue的个数要相匹配
     * @param sValues 值数组：sValues[0],sValues[1]
     * @param vo VO对象(VOInteface)
     * @param type 查询类型
     * @return 返回结果
     * @throws DefaultException
     */
    public abstract Object query(String[] strsql, String[] sValues, VOInterface vo, int type) throws DefaultException ;

    /**
     * 查询SQL语句,根据字段名称及vo对象的值,进行查询
     * @param strsql SQL语句
     * @param fldNames 字段如"name,username,memo":
     * @param view VO对象
     * @param type 查询类型
     * @return 返回结果
     * @throws DefaultException
     */
    public abstract Object query(String strsql, String fldNames, VOInterface view, int type) throws DefaultException ;

    /**
     * 执行动作查寻
     * @param conn 数据库连结
     * @param strsql sql语句
     * @param values 值数组:例如{"200","翁乃彬"}
     * @param types  类型字串:"String,int,long,"
     * @return 已更新的记录数 注:-1为失败
     * @throws DefaultException
     */
    public abstract int execUpdate(Connection conn, String strsql, String[] values, String types) throws DefaultException;

    /**
     * 执行异动查寻
     * @param conn 数据库连结
     * @param strsql sql语句
     * @param vo 一个vo对象
     * @param columns  字段名称:"name,amt,password,"
     * @return 已更新的记录数 注:-1为失败
     * @throws DefaultException
     */
    public abstract int execUpdate(Connection conn, String strsql, VOInterface vo, String columns) throws DefaultException;

    /**
     * 执行异动查寻,
     * @param conn 数据库连结
     * @param strsql sql语句
     * @return 已更新的记录数 注:-1为失败
     * @throws DefaultException
     */
    public abstract int execUpdate(Connection conn, String strsql) throws DefaultException;

    /**
     * 简单删除数据(主要考虑接入事务)
     * @param conn 数据库连结
     * @param vo vo对象
     * @return 删除记录数,-1为删除失败
     * @throws DefaultException
     */
    public abstract int delete(Connection conn, VOInterface vo) throws DefaultException;

    /**
     * 修改动作
     * @param conn 数据库连结
     * @param view vo对象
     * @return 删除
     * @throws DefaultException
     */
    public abstract int update(Connection conn, VOInterface view) throws DefaultException;

    /**
     * 新增
     * @param conn 数据库连结
     * @param vo 当前VO对象
     * @return 已新增的记录数
     * @throws DefaultException
     */
    public abstract int insert(Connection conn, VOInterface vo) throws DefaultException;

    /**
     * 多笔新增
     * @param conn 数据库连结
     * @param aryData List数据对象
     * @param vo 当前vo对象
     * @return 新增多少笔
     * @throws DefaultException
     */
    public abstract int multiInsert(Connection conn, List aryData, VOInterface vo) throws DefaultException;

    /**
     * 多笔新增
     * @param conn 数据库连结
     * @param aryData List数据对象
     * @param vo 当前vo对象
     * @param columNames 需要修改的字段
     * @return 新增多少笔
     * @throws DefaultException
     */
    public abstract int multiInsert(Connection conn, List aryData, VOInterface vo, String columNames) throws DefaultException;

    /**
     * 执行动作sql语句
     * @param strsql sql语句
     * @param values 值对象(values[0]="0"; values[1]="name");
     * @param types 数据类型(String types),以逗号分隔"long,String"
     * @return 修改记录数,-1为失败
     * @throws DefaultException
     */
    public abstract int execUpdate(String strsql, String[] values, String types) throws DefaultException;

    /**
     * 执行动作sql语句
     * @param strsql sql语句
     * @param vo 当前的vo对象（VOInterface vo)
    * @param columns 字段名字，以逗号分隔"id,name,sex"，(columNames String)
     * @return 修改记录数,-1为失败
     * @throws DefaultException
     */
    public abstract int execUpdate(String strsql, VOInterface vo, String columns) throws DefaultException;

    /**
     * 执行动作SQL语句
     * @param strsql sql语句(String strsql)
     * @return 修改记录数,-1为失败
     * @throws DefaultException
     */
    public abstract int execUpdate(String strsql) throws DefaultException;

    /**
     * 执行动作sql语句
     * @param strsql sql语句
     * @param aryValues 值对象(aryValues.add("1");aryValues.add("name");
     * @param types 数据类型(String types),以逗号分隔"long,String"
     * @return 修改记录数,-1为失败
     * @throws DefaultException
     */
    public abstract int execUpdate(String strsql, List aryValues, String types) throws DefaultException ;

    /**
     * 删除记录数
     * @param vo 当前的vo对象（vo VOInterface)
     * @return 删除记录数,-1为删除失败
     * @throws DefaultException
     */
    public abstract int delete(VOInterface vo) throws DefaultException;

    /**
     * 修改记录数
     * @param view 当前的vo对象（vo VOInterface)
     * @return 修改记录数,-1为删除失败
     * @throws DefaultException
     */
    public abstract int update(VOInterface view) throws DefaultException;

    /**
     * 新增一笔数据资料
     * @param vo 当前的vo对象（vo VOInterface)
     * @return 新增记录数,-1为删除失败
     * @throws DefaultException
     */
    public abstract int insert(VOInterface vo) throws DefaultException;

    /**
     * 多笔新增
     * @param aryData 集合数据（aryListData List)
     * @param vo 当前的vo对象（vo VOInterface)
     * @return 新增多少笔资料
     * @throws DefaultException
     */
    public abstract int multiInsert(List aryData, VOInterface vo) throws DefaultException;

    /**
     * 多笔新增
     * @param aryData 集合数据（aryListData List)
     * @param vo 当前的vo对象（vo VOInterface)
     * @param columNames 字段名字，以逗号分隔"id,name,sex"，(columNames String)
     * @return 新增多少笔资料
     * @throws DefaultException
     */

    public abstract int multiInsert(List aryData, VOInterface vo, String columNames) throws DefaultException;
    public boolean isAutoClose() {
        return autoClose;
    }
    public abstract Object queryPage(String[] strsql, long start,long end, VOInterface vo, int type) throws DefaultException;
}
