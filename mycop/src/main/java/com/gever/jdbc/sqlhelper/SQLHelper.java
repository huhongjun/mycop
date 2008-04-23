package com.gever.jdbc.sqlhelper;

import java.sql.*;
//import com.wnb.code.db.ConnectionPool;
import java.util.*;

import com.gever.exception.*;
import com.gever.jdbc.connection.*;
import com.gever.util.log.*;
import com.gever.vo.*;

/**
 * <p>Title: SQLHelper����</p>
 * <p>Description: SQLHelper����</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
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
    protected boolean autoCommit = true; // �Ƿ��Զ��ύ
    protected boolean autoClose = true; // �Ƿ��Զ��ر�
    protected boolean bakAutoCommit = true; //�����õ�
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
     * �ر����ݿ��������
     * @param conn Connection����
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
     * �ر�ResultSet����
     * @param rs ResultSet����
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
     * �ر�PreparedStatement����
     * @param pstmt PreparedStatement����
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
     * �ر�Statement����
     * @param stmt Statement����
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

    //�õ����ݿ�����
    public void begin() throws DefaultException{

        this.setAutoClose(false); //���ر����ݿ�����
        connection = getConnection();
        try{
            connection.setAutoCommit(false);
        }catch (SQLException e ){
            log.showLog(" �������ó��� ");
            e.printStackTrace();
        }
    }

    public void setConnection (Connection conn)throws DefaultException {
			this.connection = conn;
    }

    //�ر����ݿ�����
    public void end() throws DefaultException {
        this.setAutoClose(true); //���ر����ݿ�����
        close(connection);
    }

    //�õ����ݿ�����
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

    //�ύ
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

    // �ع�
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
     * @param  autoCommit   �����Զ��ύ
     */

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    /**
     * @param  autoClose   �Ƿ��Զ��ر����ݿ�����
     */
    public void setAutoClose(boolean autoClose) {
        this.autoClose = autoClose;
    }
    /**
     * ����ID
     * @param autoID �Ƿ���������ID
     */
    public void setAutoID(boolean autoID) {
        this.autoID = autoID;
    }

    /**
     * ��ѯ�򵥵�SQL���
     * @param strsql sql���(String)
     * @param type ��ѯ����(int)
     * @return ���ؽ��
     * @throws DefaultException
     */
    public abstract Object query(String strsql, int type) throws DefaultException ;

    /**
     * ��ѯһ��SQL���
     * @param strsql sql���
     * @return ���ؽ��
     * @throws DefaultException
     */
    public abstract Object query(String strsql) throws DefaultException ;

    /**
     * ��ѯsql ���,ArrayList��¼��VO��ʽ��װ
     * @param strsql sql���
     * @param view vo����
     * @return ���ؽ��
     * @throws DefaultException
     */
    public abstract List queryByListVo(String strsql, VOInterface view) throws DefaultException;

    /**
     * ��������ѯ
     * @param vo vo����(עvo����,Ҫ��vo������������и�ֵ,�����ǻ����)
     * @return ���ؽ��
     * @throws DefaultException
     */
    public abstract Object queryByPK(VOInterface vo) throws DefaultException ;

    /**
     * ��ѯsql ���,ArrayList��¼��VO��ʽ��װ
     * @param strsql sql���
     * @param vo vo����
     * @return ���ؽ��
     * @throws DefaultException
     */
    public abstract Object queryByVo(String strsql, VOInterface vo) throws DefaultException ;

    /**
     * ��ѯһ��SQL���
     * @param strsql sql���(String)
     * @param view VO����(VOInteface)
     * @param type ��ѯ����(DataTypes)
     * @return ���ؽ��
     * @throws DefaultException
     */

    public abstract Object query(String strsql, VOInterface view, int type) throws DefaultException ;

    /**
     * ��ѯһ��SQL���
     * @param strsql strsql[0]Ϊsql���,strsql[1]Ϊ������"string,date,int":
     * ע:������֮���Զ��ŷָ�,����������aryValue�ĸ���Ҫ��ƥ��
     * @param aryValues ֵ�б�
     * @param type ��ѯ����
     * @return ���ؽ��
     * @throws DefaultException
     */
    public abstract Object query(String[] strsql, List aryValues, int type) throws DefaultException ;

    /**
     * ��ѯһ��SQL���
     * @param strsql strsql[0]Ϊsql���,strsql[1]Ϊ������"string,date,int":
     * ע:������֮���Զ��ŷָ�,����������aryValue�ĸ���Ҫ��ƥ��
     * @param aryValues ֵ�б�
     * @param type ��ѯ����
     * @return ���ؽ��
     * @throws DefaultException
     */
    public abstract Object query(String[] strsql, String[] aryValues, int type) throws DefaultException ;

    /**
     * ��ѯһ��SQL���
     * @param strsql strsql[0]Ϊsql���,strsql[1]Ϊ������"string,date,int":
     * ע:������֮���Զ��ŷָ�,����������aryValue�ĸ���Ҫ��ƥ��
     * @param sValues ֵ���飺sValues[0],sValues[1]
     * @param vo VO����(VOInteface)
     * @param type ��ѯ����
     * @return ���ؽ��
     * @throws DefaultException
     */
    public abstract Object query(String[] strsql, String[] sValues, VOInterface vo, int type) throws DefaultException ;

    /**
     * ��ѯSQL���,�����ֶ����Ƽ�vo�����ֵ,���в�ѯ
     * @param strsql SQL���
     * @param fldNames �ֶ���"name,username,memo":
     * @param view VO����
     * @param type ��ѯ����
     * @return ���ؽ��
     * @throws DefaultException
     */
    public abstract Object query(String strsql, String fldNames, VOInterface view, int type) throws DefaultException ;

    /**
     * ִ�ж�����Ѱ
     * @param conn ���ݿ�����
     * @param strsql sql���
     * @param values ֵ����:����{"200","���˱�"}
     * @param types  �����ִ�:"String,int,long,"
     * @return �Ѹ��µļ�¼�� ע:-1Ϊʧ��
     * @throws DefaultException
     */
    public abstract int execUpdate(Connection conn, String strsql, String[] values, String types) throws DefaultException;

    /**
     * ִ���춯��Ѱ
     * @param conn ���ݿ�����
     * @param strsql sql���
     * @param vo һ��vo����
     * @param columns  �ֶ�����:"name,amt,password,"
     * @return �Ѹ��µļ�¼�� ע:-1Ϊʧ��
     * @throws DefaultException
     */
    public abstract int execUpdate(Connection conn, String strsql, VOInterface vo, String columns) throws DefaultException;

    /**
     * ִ���춯��Ѱ,
     * @param conn ���ݿ�����
     * @param strsql sql���
     * @return �Ѹ��µļ�¼�� ע:-1Ϊʧ��
     * @throws DefaultException
     */
    public abstract int execUpdate(Connection conn, String strsql) throws DefaultException;

    /**
     * ��ɾ������(��Ҫ���ǽ�������)
     * @param conn ���ݿ�����
     * @param vo vo����
     * @return ɾ����¼��,-1Ϊɾ��ʧ��
     * @throws DefaultException
     */
    public abstract int delete(Connection conn, VOInterface vo) throws DefaultException;

    /**
     * �޸Ķ���
     * @param conn ���ݿ�����
     * @param view vo����
     * @return ɾ��
     * @throws DefaultException
     */
    public abstract int update(Connection conn, VOInterface view) throws DefaultException;

    /**
     * ����
     * @param conn ���ݿ�����
     * @param vo ��ǰVO����
     * @return �������ļ�¼��
     * @throws DefaultException
     */
    public abstract int insert(Connection conn, VOInterface vo) throws DefaultException;

    /**
     * �������
     * @param conn ���ݿ�����
     * @param aryData List���ݶ���
     * @param vo ��ǰvo����
     * @return �������ٱ�
     * @throws DefaultException
     */
    public abstract int multiInsert(Connection conn, List aryData, VOInterface vo) throws DefaultException;

    /**
     * �������
     * @param conn ���ݿ�����
     * @param aryData List���ݶ���
     * @param vo ��ǰvo����
     * @param columNames ��Ҫ�޸ĵ��ֶ�
     * @return �������ٱ�
     * @throws DefaultException
     */
    public abstract int multiInsert(Connection conn, List aryData, VOInterface vo, String columNames) throws DefaultException;

    /**
     * ִ�ж���sql���
     * @param strsql sql���
     * @param values ֵ����(values[0]="0"; values[1]="name");
     * @param types ��������(String types),�Զ��ŷָ�"long,String"
     * @return �޸ļ�¼��,-1Ϊʧ��
     * @throws DefaultException
     */
    public abstract int execUpdate(String strsql, String[] values, String types) throws DefaultException;

    /**
     * ִ�ж���sql���
     * @param strsql sql���
     * @param vo ��ǰ��vo����VOInterface vo)
    * @param columns �ֶ����֣��Զ��ŷָ�"id,name,sex"��(columNames String)
     * @return �޸ļ�¼��,-1Ϊʧ��
     * @throws DefaultException
     */
    public abstract int execUpdate(String strsql, VOInterface vo, String columns) throws DefaultException;

    /**
     * ִ�ж���SQL���
     * @param strsql sql���(String strsql)
     * @return �޸ļ�¼��,-1Ϊʧ��
     * @throws DefaultException
     */
    public abstract int execUpdate(String strsql) throws DefaultException;

    /**
     * ִ�ж���sql���
     * @param strsql sql���
     * @param aryValues ֵ����(aryValues.add("1");aryValues.add("name");
     * @param types ��������(String types),�Զ��ŷָ�"long,String"
     * @return �޸ļ�¼��,-1Ϊʧ��
     * @throws DefaultException
     */
    public abstract int execUpdate(String strsql, List aryValues, String types) throws DefaultException ;

    /**
     * ɾ����¼��
     * @param vo ��ǰ��vo����vo VOInterface)
     * @return ɾ����¼��,-1Ϊɾ��ʧ��
     * @throws DefaultException
     */
    public abstract int delete(VOInterface vo) throws DefaultException;

    /**
     * �޸ļ�¼��
     * @param view ��ǰ��vo����vo VOInterface)
     * @return �޸ļ�¼��,-1Ϊɾ��ʧ��
     * @throws DefaultException
     */
    public abstract int update(VOInterface view) throws DefaultException;

    /**
     * ����һ����������
     * @param vo ��ǰ��vo����vo VOInterface)
     * @return ������¼��,-1Ϊɾ��ʧ��
     * @throws DefaultException
     */
    public abstract int insert(VOInterface vo) throws DefaultException;

    /**
     * �������
     * @param aryData �������ݣ�aryListData List)
     * @param vo ��ǰ��vo����vo VOInterface)
     * @return �������ٱ�����
     * @throws DefaultException
     */
    public abstract int multiInsert(List aryData, VOInterface vo) throws DefaultException;

    /**
     * �������
     * @param aryData �������ݣ�aryListData List)
     * @param vo ��ǰ��vo����vo VOInterface)
     * @param columNames �ֶ����֣��Զ��ŷָ�"id,name,sex"��(columNames String)
     * @return �������ٱ�����
     * @throws DefaultException
     */

    public abstract int multiInsert(List aryData, VOInterface vo, String columNames) throws DefaultException;
    public boolean isAutoClose() {
        return autoClose;
    }
    public abstract Object queryPage(String[] strsql, long start,long end, VOInterface vo, int type) throws DefaultException;
}
