package com.gever.jdbc.sqlhelper;

import java.sql.*;
import java.util.*;

import com.gever.exception.*;
import com.gever.jdbc.database.dialect.DialectFactory;
import com.gever.vo.*;

/**
 * ���ݿ��������(���ݿ�ӿ�)
 * ���˼·��
 * 1��������ϵͳ���������ݴ򽻵������в�����һ���ѯ�Ͷ�����ѯ��
 * 2��֧�̶ֹ�sql����Ԥ����sql��䡣
 * 3������һ����־�����⣬��ʹ���������ԡ�
 * 4��֧��Connection������
 * ֻ������Ҫ�����ݿ�򽻵�ʱ�Ż�ȡ���ӣ�
 * <p>Title:���ݿ�������� </p>
 * <p>Description: ���ݿ��������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class DefaultSQLHelper extends SQLHelper {

    private String className = ""; //������ֹؼ���
    private static final String QUERY_ERROR_ID = "sys_query_001";
    private static final String NULL_ERROR_ID =
        "java.lang.NullPointerException";
    private SQLHelperUtil sUtil = new SQLHelperUtil();
    /**
     * ��Ҫ���Ƕ����ݿ�
     * @param dbData ���ݿ�����
     */
    public DefaultSQLHelper(String dbData) {
        super(dbData);
    }

    public DefaultSQLHelper() {
    }

    /**
     * ��ѯ�򵥵�SQL���
     * @param strsql sql���
     * @param type ��ѯ����
     * @return ���ؽ��
     * @throws DefaultException
     */
    public Object query(String strsql, int type) throws DefaultException {
        return this.query(strsql, null, type);
    }

    /**
     * ��ѯһ��SQL���
     * @param strsql sql���
     * @return ���ؽ��
     * @throws DefaultException
     */
    public Object query(String strsql) throws DefaultException {
        return this.query(strsql, null, DataTypes.RS_LIST_MAP);
    }

    /**
     * ��ѯsql ���,ArrayList��¼��VO��ʽ��װ
     * @param strsql sql���
     * @param view vo����
     * @return ���ؽ��
     * @throws DefaultException
     */
    public List queryByListVo(String strsql, VOInterface view) throws
        DefaultException {
        return (List)this.query(strsql, view, DataTypes.RS_LIST_VO);
    }

    /**
     * ��������ѯ
     * @param vo vo����(עvo����,Ҫ��vo������������и�ֵ,�����ǻ����)
     * @return ���ؽ��
     * @throws DefaultException
     */
    public Object queryByPK(VOInterface vo) throws DefaultException {
        StringBuffer sbPkSql = new StringBuffer();
        sbPkSql.append(" SELECT * FROM ").append(vo.getTableName());
        sbPkSql.append(" WHERE 1=1 ");
        StringTokenizer stkPk = new StringTokenizer(vo.getPkFields(), ",");
        String strFld = new String();
        while (stkPk.hasMoreTokens()) {
            strFld = stkPk.nextToken().toString();
            sbPkSql.append(" AND ").append(strFld).append("=?");
        }
        return this.query(sbPkSql.toString(), vo.getPkFields(), vo,
                          DataTypes.RS_SINGLE_VO);
    }

    /**
     * ��ѯsql ���,ArrayList��¼��VO��ʽ��װ
     * @param strsql sql���
     * @param vo vo����
     * @return ���ؽ��
     * @throws DefaultException
     */
    public Object queryByVo(String strsql, VOInterface vo) throws
        DefaultException {
        return this.query(strsql, vo, DataTypes.RS_SINGLE_VO);
    }

    /**
     * ��ѯһ��SQL���
     * @param strsql sql���
     * @param view VO����
     * @param type ��ѯ����
     * @return ���ؽ��
     * @throws DefaultException
     */
    public Object query(String strsql, VOInterface view, int type) throws
        DefaultException {
        ResultSet rs = null;
        Object obj = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = super.getConnection();
            stmt = conn.createStatement();
            log.console(this.getClass(),"query:stSQL",strsql);
            rs = stmt.executeQuery(strsql);
            //��������
            obj = queryRs(rs, view, type);
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            throw new DefaultException(se);
        } finally {
            super.close(rs);
            rs = null;
            super.close(stmt);
            stmt = null;
            if (this.autoClose == true)
                super.close(conn);

        }
        return obj;
    }

    /**
     * ��ѯһ��SQL���
     * @param strsql strsql[0]Ϊsql���,strsql[1]Ϊ������"string,date,int":
     * ע:������֮���Զ��ŷָ�,����������aryValue�ĸ���Ҫ��ƥ��
     * @param aryValues ֵ�б�
     * @param type ��ѯ����
     * @return ���ؽ��
     * @throws DefaultException
     */
    public Object query(String[] strsql, List aryValues, int type) throws
        DefaultException {
        int iCount = aryValues.size();
        String[] sValues = new String[aryValues.size()];
        for (int idx = 0; idx < iCount; idx++) {
            sValues[idx] = String.valueOf(aryValues.get(idx));
        }
        return query(strsql, sValues, type);
    }

    /**
     * ��ѯһ��SQL���
     * @param strsql strsql[0]Ϊsql���,strsql[1]Ϊ������"string,date,int":
     * ע:������֮���Զ��ŷָ�,����������aryValue�ĸ���Ҫ��ƥ��
     * @param sValues ֵ���飺sValues[0],sValues[1]
     * @param type ��ѯ����
     * @return ���ؽ��
     * @throws DefaultException
     */

    public Object query(String[] strsql, String[] sValues, int type) throws
        DefaultException {

        return query(strsql, sValues, null, type);
    }

    /**
     * ��ѯһ��SQL���
     * @param strsql strsql[0]Ϊsql���,strsql[1]Ϊ������"string,date,int":
     * ע:������֮���Զ��ŷָ�,����������aryValue�ĸ���Ҫ��ƥ��
     * @param sValues valueTypes[0]Ϊ���Ӧ,valueTypes[1]
     * @param vo ��ǰ��vo����vo VOInterface)
     * @param type ��ѯ����
     * @return ���ؽ��
     * @throws DefaultException
     */
    public Object query(String[] strsql, String[] sValues, VOInterface vo,
                        int type) throws DefaultException {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        Object obj = null;
        try {
            conn = super.getConnection();
            //            pstmt = conn.prepareStatement(strsql[0],
            //                                          ResultSet.TYPE_SCROLL_INSENSITIVE,
            //                                          ResultSet.CONCUR_READ_ONLY);

            log.console(this.getClass(),"query:strsql",strsql[0]);
            pstmt = conn.prepareStatement(strsql[0]);

            StringTokenizer stTypes = new StringTokenizer(strsql[1], ",");
            if (stTypes.countTokens() != sValues.length) {
                log.showLog("��ѯʱ,��������������ֵ����������!");
                throw new DefaultException(QUERY_ERROR_ID);
            }

            //��setPstmtParam�����
            String strType = "";
            Object value;
            int idx = 0;

            while (stTypes.hasMoreTokens()) {
                strType = (String) stTypes.nextToken();
                value = sValues[idx++];
                this.setPstmtParam(pstmt, value, strType, idx);
                // log.showLog("value:" + value  + " type = "  + strType);

            }
            rs = pstmt.executeQuery();

            //��ѯ����
            obj = queryRs(rs, vo, type);
            rs.close();
            rs = null;
            pstmt.close();
            pstmt = null;
        } catch (SQLException se) {
            se.printStackTrace();
            throw new DefaultException(se);
        } finally {
            super.close(rs);
            rs = null;
            super.close(pstmt);
            pstmt = null;
            if (this.autoClose == true) {
                super.close(conn);
            }
        }
        return obj;
    }

    /**
     * ��ѯSQL���,�����ֶ����Ƽ�vo�����ֵ,���в�ѯ
     * @param strsql SQL���
     * @param fldNames �ֶ���"name,username,memo":
     * @param view VO����
     * @param type ��ѯ����
     * @return ���ؽ��
     * @throws DefaultException
     */
    public Object query(String strsql, String fldNames, VOInterface view,
                        int type) throws DefaultException {
        ResultSet rs = null;

        PreparedStatement pstmt = null;
        Object obj = null;
        Connection conn = null;
        try {
            conn = super.getConnection();
            log.console(this.getClass(),"query():strsql",strsql);
            pstmt = conn.prepareStatement(strsql);
            StringTokenizer stTypes = new StringTokenizer(fldNames, ",");

            //����PreparedStatement�������
            String strName = new String();
            String strType = new String();
            Object value;
            int idx = 0;
            while (stTypes.hasMoreTokens()) {
                strName = (String) stTypes.nextToken();
                this.setPstmtParam(pstmt, view.getValue(strName), view
                                   .getColType(strName), idx + 1);
                idx++;
            }
            rs = pstmt.executeQuery();
            //��������
            obj = queryRs(rs, view, type);
            rs.close();
            rs = null;
            pstmt.close();
            pstmt = null;

        } catch (SQLException se) {
            throw new DefaultException(se);
        } finally {
            super.close(rs);
            super.close(pstmt);
            if (this.autoClose == true)
                super.close(conn);

        }
        return obj;
    }

    /**
     * ����rs����,������Ӧ����������
     * @param rs ResultSet���ݶ���
     * @param view VO����
     * @param type ���ض�������
     * @return ���ݶ���
     * @throws DefaultException
     */
    private Object queryRs(ResultSet rs, VOInterface view, int type) throws
        DefaultException {
        Object obj = null;
        switch (type) {
            case DataTypes.RS_LIST_MAP:
                obj = this.rsToListMap(rs);
                break;
            case DataTypes.RS_META_LIST:
                obj = this.rsToMeta(rs);
                break;
            case DataTypes.RS_SINGLE_MAP:
                obj = this.rsSingleToMap(rs);
                break;
            case DataTypes.RS_SINGLE_VO:
                if (view == null)
                    throw new DefaultException(NULL_ERROR_ID);
                obj = this.rsToVo(rs, view);
                break;
            case DataTypes.RS_LIST_VO:
                if (view == null)
                    throw new DefaultException(NULL_ERROR_ID);
                obj = this.rsToListVo(rs, view);
                break;
            default:
                obj = this.rsToListMap(rs);
                break;
        }
        return obj;
    }

    /**
     * ����PreparedStatement�������
     * @param pstmt PreparedStatement����
     * @param value ���Ӧ��ֵ
     * @param type ����
     * @param col �ڼ����ֶ�
     * @throws SQLException
     */
    private String fldname = "";

    private boolean isNull(String value) {
        if ( (null == value) || ("".equals(value)) || "null".equals(value))
            return true;
        else
            return false;
    }

    private void setPstmtParam(PreparedStatement pstmt, Object value,
                               String type, int col) throws SQLException {
        String strValue = String.valueOf(value);
//        log.showLog("----value=" + value + "-----col=" + col + " type=" + type);
        if (DataTypes.STRING.equals(type)) { //string ����
            pstmt.setString(col, strValue);

        } else if (DataTypes.INT.equals(type)) { //int����
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.INTEGER);
            } else {
            	log.console(this.getClass(),"setPstmtParam:INT:value",strValue);
                pstmt.setInt(col, Integer.parseInt(strValue));

            }

        } else if (DataTypes.LONG.equals(type)) { //long��
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.BIGINT);
            } else {
                pstmt.setLong(col, Long.parseLong(strValue));
            }

        } else if (DataTypes.DOUBLE.equals(type)
                   || DataTypes.FLOAT.equals(type)) { //double����
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.DOUBLE);
            } else {
                pstmt.setDouble(col, Double.parseDouble(strValue));
            }

        } else if (DataTypes.DECIMAL.equals(type)
                   || DataTypes.DECIMAL.equals(type)) { //double����
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.DECIMAL);
            } else {
                pstmt.setBigDecimal(col, new java.math.BigDecimal(strValue));
            }

        } else if (DataTypes.DATE.equals(type)) { //��������
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.DATE);
            } else {
                if (strValue.length() <= 10) {
                    pstmt.setDate(col, java.sql.Date.valueOf(strValue));
                } else {
                    pstmt.setTimestamp(col, java.sql.Timestamp
                                       .valueOf(strValue));
                }
            }

        } else if (DataTypes.TIMESTMAP.equals(type)) { //ʱ������
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.TIMESTAMP);
            } else {
                pstmt.setTimestamp(col, java.sql.Timestamp.valueOf(strValue));
            }
        } else if (DataTypes.TIME.equals(type)) { //ʱ������
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.TIME);
            } else {
                pstmt.setTime(col, java.sql.Time.valueOf(strValue));
            }
        } else if (DataTypes.BOOLEAN.equals(type)) { //��������
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.BOOLEAN);
            } else {
                pstmt.setBoolean(1, Boolean.valueOf(strValue).booleanValue());
            }

        } else if (DataTypes.CLOB.equals(type)) { //��������
            // System.out.println("---strValue--value-*************---" + strValue);
            if (sUtil.isOracle() == true) {
                // System.out.println("---strValue--value-****^^^^*********---" + strValue);
                if (isNull(strValue)) {
                    pstmt.setBytes(col, null);
                } else {

                    pstmt.setBytes(col, strValue.getBytes());
                }
            } else {
                pstmt.setString(col, strValue);

            }

        } else if (DataTypes.BYTES.equals(type)) {
            //log.showLog("-----get bytes--start------ " );
            try {
                byte[] bytes = (byte[]) SQLHelperUtil.getProperty(byteVo,
                    byteFldName); //����
                //log.showLog("-----get bytes--start--length---- " + bytes.length);
                pstmt.setBytes(col, bytes);
            } catch (Exception e) {
                e.printStackTrace();
                log.console(this.getClass(),"setPstmtParam:BYTES","-----get byte����-----");
            }

        } else {
            log.console(this.getClass(),"setPstmtParam:BYTES","-----get bytes--clob------");
            if (isNull(strValue)) {
                log.console(this.getClass(),"setPstmtParam:BYTES","----set null----");
                pstmt.setNull(col, java.sql.Types.VARCHAR);
            } else {
                pstmt.setString(col, strValue);
            }

        }

    }

    /**
     * ��ü�¼�ֶ�����
     * @param rs ��¼��
     * @return rs�е��ֶ�������
     * @throws DefaultException
     */
    private List rsToMeta(ResultSet rs) throws DefaultException {
        List retAry = new ArrayList();
        String logMethod = "DefaultSQLHelper.rsToMeta(ResultSet  rs): ";

        try {
            //ȡ�ý�����ĵ�Ԫ��Ϣ
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNum = rsmd.getColumnCount();
            //log.showLog(logMethod + "  columnNum = " + columnNum);
            for (int idx = 1; idx <= columnNum; idx++) {
                retAry.add(rsmd.getColumnName(idx).toUpperCase());
            }

        } catch (SQLException e) {
            log.showLog(logMethod + e.toString());
            throw new DefaultException(e);
        }
        return retAry;
    }

    /*
     * ResultSet ת���� List
     * @param rs ���ݿ���������
     * @return List�б�
     */

    private List rsToListMap(ResultSet rs) throws DefaultException {
        List aryRet = new ArrayList();
        Map rsMap = new HashMap();
        String logMethod = "DefaultSQLHelper.rsToArray(ResultSet rs): ";

        try {
            //ȡ�ý�����ĵ�Ԫ��Ϣ
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNum = rsmd.getColumnCount();
            //����Ϊ0,���ؿյ�ArrayList
            if (columnNum == 0) {
                return aryRet;
            }

            String strValue = new String();
            while (rs.next()) {
                rsMap = new HashMap();
                for (int idx = 1; idx <= columnNum; idx++) {
                    strValue = rs.getString(idx);
                    if ("null".equals(strValue) || (strValue == null))
                        strValue = "";
                    rsMap.put(rsmd.getColumnName(idx).toLowerCase(), strValue
                              .trim());
                }
                aryRet.add(rsMap);
            }

        } catch (SQLException e) {
            log.showLog(logMethod + e.toString());
            throw new DefaultException(e);
        }

        return aryRet;
    }

    /**
     * ��ȡVOʵ��������
     * @param view VO����
     * @return ObjectView
     */
    private VOInterface getInstanceVO(VOInterface view) {
        VOInterface valueObject = null;
        String logMethod = "getInstanceVO(view)";
        try {
            valueObject = (VOInterface) Class.forName(this.className)
                .newInstance();
        } catch (InstantiationException e) {
            log.showLog(logMethod + e.getMessage());
        } catch (IllegalAccessException e) {
            log.showLog(logMethod + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.showLog(logMethod + e.getMessage());
        }

        return valueObject;
    }

    /**
     * �õ�VO������List��ʽ
     * @param rs ResultSet��¼������
     * @param view VO����
     * @return �õ�VO������List��ʽ
     * @throws DefaultException
     */
    private List rsToListVo(ResultSet rs, VOInterface view) throws
        DefaultException {

        String logMethod = "DefaultSQLHelper.rsToListVo(rs,view): ";
        List aryRet = new ArrayList();
        HashMap rsMap = new HashMap();
        className = view.getClass().getName();
        VOInterface vo = this.getInstanceVO(view);

        try {
            //ȡ�ý�����ĵ�Ԫ��Ϣ
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnNum = rsMeta.getColumnCount();

            //����Ϊ0,���ؿյ�List
            if (columnNum == 0) {
                return aryRet;
            }

            StringTokenizer stk = new StringTokenizer(vo.getAllFields(), ",");
            int iCount = stk.countTokens();
            String[] aryCol = new String[iCount];
            int[] intCol = new int[columnNum]; //ʵ��Ҫ�����
            int row = 0;
            int factCol = 0; //������
            String strCol = new String();
            List allList = new ArrayList();

            //���ݱȶԵö�Ҫ���µ�ʵ���ֶ�
            while (stk.hasMoreTokens()) {

                strCol = stk.nextToken();
                for (int col = 0; col < columnNum; col++) {
                    if (strCol.equals(rsMeta.getColumnName(col + 1)
                                      .toLowerCase())) {
                        intCol[factCol] = row;
                        ++factCol;
                        break;
                    }
                }

                row++;
            }

            String strValue = new String();
            if (!sUtil.isOracle()) {
                while (rs.next()) {
                    //����ʵ����VO����
                    vo = this.getInstanceVO(view);
                    for (int idx = 1; idx <= columnNum; idx++) {
                        strValue = rs.getString(idx);
                        if ("null".equals(strValue) || (strValue == null))
                            strValue = "";

                        vo.setValue(rsMeta.getColumnName(idx), strValue.trim());
                        //   aryCol[intCol[idx - 1]] = strValue;

                    }
                    vo.setOtherProperty(aryCol);
                    //vo.setValues(aryCol);
                    aryRet.add(vo);
                }
            } else { //Ϊoracle
                while (rs.next()) {
                    //����ʵ����VO����
                    vo = this.getInstanceVO(view);
                    for (int idx = 1; idx <= columnNum; idx++) {
                        if (DataTypes.CLOB.equalsIgnoreCase(vo.getColType(
                            rsMeta.getColumnName(idx)))
                            ||
                            DataTypes.BYTES.equalsIgnoreCase(vo.getColType(rsMeta.
                            getColumnName(idx)))) {
                            byte[] bytes = rs.getBytes(idx);
                            if (bytes == null)
                                strValue = "";
                            else
                                strValue = new String(bytes);

                        } else {
                            strValue = rs.getString(idx);
                        }

                        if ("null".equals(strValue) || (strValue == null))
                            strValue = "";

                        vo.setValue(rsMeta.getColumnName(idx), strValue.trim());
                        //   aryCol[intCol[idx - 1]] = strValue;

                    }
                    vo.setOtherProperty(null);
                    //vo.setValues(aryCol);
                    aryRet.add(vo);
                }
            }
        } catch (SQLException e) {
            log.showLog(logMethod + e.getMessage());
            throw new DefaultException(e);
        }

        return aryRet;
    }

    /**
     * �õ�VO�����ʽ
     * @param rs ResultSet��¼������
     * @param view VO����
     * @return �õ�VO�����ʽ
     * @throws DefaultException
     */

    private VOInterface rsToVo(ResultSet rs, VOInterface view) throws
        DefaultException {
        String logMethod = "DefaultSQLHelper.rsToVO(rs, view): ";
        List aryRet = new ArrayList();
        HashMap rsMap = new HashMap();
        this.className = view.getClass().getName();
        VOInterface vo = this.getInstanceVO(view); //ʵ�������vo��

        try {

            //ȡ�ý�����ĵ�Ԫ��Ϣ
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNum = rsmd.getColumnCount();
            //����Ϊ0,���ؿյ�ArrayList
            if (columnNum == 0) {
                return vo;
            }

            String strValue = "";
            if (rs.next()) {
                for (int i = 1; i <= columnNum; i++) {
                    //bytes����
                    if (DataTypes.BYTES.equals(view.getColType(rsmd
                        .getColumnName(i).toLowerCase()))) {
                        try {

                            byte[] bytes = rs.getBytes(i);
//                            log.showLog("--bytes---length---" + bytes.length +
//                                        "--getColumnName--" +
//                                        rsmd.getColumnName(i));
                            SQLHelperUtil.setProperty(vo, rsmd.getColumnName(i)
                                .toLowerCase(), bytes);
                        } catch (Exception e) {
                            e.printStackTrace(System.out);
                        }

                        //clob����
                    } else if (DataTypes.CLOB.equals(view.getColType(
                        rsmd.getColumnName(i)).toLowerCase())
                               && sUtil.isOracle()) {
                        byte[] bytes = rs.getBytes(i);
                        if (bytes == null)
                            strValue = "";
                        else
                            strValue = new String(bytes);

                        vo.setValue(rsmd.getColumnName(i).toLowerCase(),
                                    strValue);

                    } else {

                        strValue = rs.getString(i);
                        if ("null".equals(strValue) || (strValue == null))
                            strValue = "";
//                        log.showLog(rsmd.getColumnName(i).toLowerCase()
//                                    + "----value --" + strValue);
                        vo.setValue(rsmd.getColumnName(i).toLowerCase(),
                                    strValue.trim());
                    }
                }
                vo.setOtherProperty(null);
            }
        } catch (SQLException e) {
            log.showLog(logMethod + e.toString());
            throw new DefaultException(e);

        }
        return vo;

    }

    /*
     * ��õ�����¼ֵ
     * @param  rs ���ݿ���������
     * @return ��õ�����¼ֵ(Map)
     * @throws DefaultException
     */
    private Map rsSingleToMap(ResultSet rs) throws DefaultException {
        Map hMap = new HashMap();
        String logMethod = "DefaultSQLHelper.rsSingleToMap(ResultSet rs): ";

        //ȡ�ý�����ĵ�Ԫ��Ϣ
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNum = rsmd.getColumnCount();
            //����Ϊ0,���ؿյ�ArrayList
            log.showLog(logMethod + "=" + columnNum);
            if (columnNum == 0) {
                return hMap;
            }

            String colValue = "";
            String colName = "";

            if (rs.next()) {
                for (int i = 1; i <= columnNum; i++) {
                    colValue = rs.getString(i);
                    colName = rsmd.getColumnName(i).toLowerCase();
                    if (colName.indexOf(".") > 0) {
                        colName = colName.substring(colName.indexOf(".") + 1,
                            colName.length());
                    }

                    //����ֶ�����Ϊ�ַ��ͣ��ҵõ����ֶν��Ϊ�գ������ֶε�ֵ��ֵΪ""��
                    if ("null".equals(colValue) || (colValue == null))
                        colValue = "";
                        //log.showLog("colName:" + colName + "  colValue:" + colValue);
                    hMap.put(colName, colValue.trim());
                }
            }

        } catch (SQLException e) {
            log.showLog(logMethod + e.toString());
            throw new DefaultException(e);
        }
        return hMap;

    }

    /**
     * ִ�ж�����Ѱ
     * @param conn ���ݿ�����
     * @param strsql sql���
     * @param values ֵ����:����{"200","���˱�"}
     * @param types  �����ִ�:"String,int,long,"
     * @return �Ѹ��µļ�¼�� ע:-1Ϊʧ��
     * @throws DefaultException
     */
    public int execUpdate(Connection conn, String strsql, String[] values,
                          String types) throws DefaultException {
        String logMethod = " execUpdate(conn, strsql, values[],  types)";

        PreparedStatement pstmt = null;
        int intRet = -1;
        try {
            pstmt = conn.prepareStatement(strsql);
            StringTokenizer stkTypes = new StringTokenizer(types, ",");
            String datatype = new String();
            int idx = 0;
            //log.showLog("----" + types);
            while (stkTypes.hasMoreTokens()) {
                datatype = stkTypes.nextToken().toString();
                log.console(this.getClass(),"execUpdate:value-type array","<"+idx+",Type:"+datatype+",Value:"+values[idx]+">");
                this.setPstmtParam(pstmt, values[idx], datatype, idx + 1);
                ++idx;
            }
            log.console(this.getClass(),"execUpdate:strSQL",strsql);
            intRet = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            log.showLog(logMethod + e.getMessage());

            throw new DefaultException(e);

        } finally {
            super.close(pstmt);
            if (this.autoClose == true)
                super.close(conn);
        }

        return intRet;
    }

    /**
     * ִ���춯��Ѱ
     * @param conn ���ݿ�����
     * @param strsql sql���
     * @param vo һ��vo����
     * @param columns  �ֶ�����:"name,amt,password,"
     * @return �Ѹ��µļ�¼�� ע:-1Ϊʧ��
     * @throws DefaultException
     */

    public int execUpdate(Connection conn, String strsql, VOInterface vo,
                          String columns) throws DefaultException {
        PreparedStatement pstmt = null;
        String logMethod = " execUpdate(conn, strsql, vo,  columns)";
        int intRet = -1;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(strsql.toString());
            StringTokenizer stkTypes = new StringTokenizer(columns, ",");

            int idx = 0;
            String columnName = "";

            while (stkTypes.hasMoreTokens()) {
                columnName = stkTypes.nextToken().toString().trim();
                this.setPstmtParam(pstmt, vo.getValue(columnName), vo
                                   .getColType(columnName), idx + 1);
                idx++;
            }
            log.console(this.getClass(),"execUpdate:vo:strSQL",strsql);            
            intRet = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            log.showLog(logMethod + e.getMessage());
            throw new DefaultException(e);
        } finally {
            super.close(pstmt);
            if (this.autoClose == true)
                super.close(conn);
        }
        return intRet;
    }

    /**
     * ִ���춯��Ѱ,
     * @param conn ���ݿ�����
     * @param strsql sql���
     * @return �Ѹ��µļ�¼�� ע:-1Ϊʧ��
     * @throws DefaultException
     */

    public int execUpdate(Connection conn, String strsql) throws
        DefaultException {
        Statement stmt = null;
        String logMethod = " execUpdate(conn, strsql)";
        int intRet = -1;
        try {
            stmt = conn.createStatement();
            log.showLog(strsql);
            intRet = stmt.executeUpdate(strsql);
        } catch (SQLException e) {
            log.showLog(logMethod + e.getMessage());
            throw new DefaultException(e);
        } finally {

            super.close(stmt);
            if (super.autoClose == true)
                super.close(conn);
        }
        return intRet;
    }

    /**
     * ��ɾ������(��Ҫ���ǽ�������)
     * @param conn ���ݿ�����
     * @param vo vo����
     * @return ɾ����¼��,-1Ϊɾ��ʧ��
     * @throws DefaultException
     */
    public int delete(Connection conn, VOInterface vo) throws DefaultException {
        int intRet = -1;
        String logMethod = " delete(conn, vo)";

        ArrayList aryPk = new ArrayList();
        String strName = new String();
        StringBuffer sbWhere = new StringBuffer();

        //��֯ɾ�������Ӿ�
        sbWhere.append("  ");
        StringTokenizer stkNames = new StringTokenizer(vo.getPkFields(), ",");
        int iCount = stkNames.countTokens();
        int col = 0;
        String[] pkNames = new String[iCount];
        String[] pkValues = new String[iCount];
        String[] pkTypes = new String[iCount];
        String strValue = new String();
        String types = "";
        while (stkNames.hasMoreTokens()) {
            strValue = stkNames.nextToken().toString();
            pkNames[col] = strValue;
            pkValues[col] = vo.getValue(strValue);

            log.showLog("����ֵ��" + pkValues[col]);

            types = types + vo.getColType(strValue) + ",";
            sbWhere.append(" ").append(strValue).append("=? ");
            log.showLog("---del--" + col + " count= " + stkNames.countTokens());
            if (col < iCount - 1)
                sbWhere.append(" AND ");
            ++col;
        }

        //��������ɾ�����
        StringBuffer sbDelSql = new StringBuffer();
        sbDelSql.append("DELETE FROM ").append(vo.getTableName());
        sbDelSql.append(" WHERE  ").append(sbWhere.toString());

        //ִ��sql���
        try {

            intRet = execUpdate(conn, sbDelSql.toString(), pkValues, types);
        } catch (DefaultException e) {
            log.showLog(logMethod + e.getMessage());
            throw new DefaultException(e);
        }
        return intRet;
    }

    /**
     * �޸Ķ���
     * @param conn ���ݿ�����
     * @param view vo����
     * @return ɾ��
     * @throws DefaultException
     */
    public int update(Connection conn, VOInterface view) throws
        DefaultException {
        String logMethod = "update (conn, view)";
        int intRet = -1;
        StringBuffer sbUpdateSql = new StringBuffer();
        String modFlds = view.getModifyFields();
        String pkFlds = view.getPkFields();

        StringTokenizer stkModColums = new StringTokenizer(modFlds, ",");
        StringTokenizer stkPkColums = new StringTokenizer(pkFlds, ",");
        int modCount = stkModColums.countTokens();
        int pkCount = stkPkColums.countTokens();
        int iCount = modCount + pkCount;
        byteVo = view;
        if (modCount < 1) //�ݴ���
            throw new DefaultException(UPDATE_ERROR);
        //����update SQL���
        sbUpdateSql.append(" UPDATE ").append(view.getTableName());
        sbUpdateSql.append(" SET ");
        int idx = 0;
        String[] values = new String[iCount];
        StringBuffer sbTypes = new StringBuffer();
        String columName = "";

        //�޸��ֶ�
        while (stkModColums.hasMoreTokens()) {
            columName = stkModColums.nextToken().toString();
            sbUpdateSql.append(columName).append("=?");
            values[idx] = view.getValue(columName);
            sbTypes.append(view.getColType(columName));
            if (idx < modCount - 1)
                sbUpdateSql.append(",");
            sbTypes.append(",");
            if (view.getColType(columName).equals(DataTypes.BYTES)) {
                log.showLog("000--------bytes-------");
                byteFldName = columName;

            }

            idx++;
        }

        //��֯�����Ӿ�
        int preNum = idx;
        sbUpdateSql.append(" WHERE ");
        idx = 0;

        while (stkPkColums.hasMoreTokens()) {
            columName = stkPkColums.nextToken().toString();
            sbUpdateSql.append(columName).append("=?");
            sbTypes.append(view.getColType(columName));
            values[preNum + idx] = view.getValue(columName);
            if (idx < pkCount - 1)
                sbUpdateSql.append(" AND ");
            sbTypes.append(",");
            ++idx;
        }

        try {
            //ִ��sql
            for (int i = 0; i < values.length; i++) {
                log.showLog("values[" + i + "]= " + values[i]);
            }
            log.console(this.getClass(),"Update:sbUpdateSql",sbUpdateSql.toString());            
            log.showLog("sbTypes" + sbTypes.toString());
            intRet = execUpdate(conn, sbUpdateSql.toString(), values, sbTypes
                                .toString());
        } catch (DefaultException e) {
            log.showLog(logMethod + e.getMessage());
            throw new DefaultException(e);
        }
        return intRet;

    }

    private Object byteVo;
    private String byteFldName = "";

    /**
     * ����
     * @param conn ���ݿ�����
     * @param vo ��ǰVO����
     * @return �������ļ�¼��
     * @throws DefaultException
     */
    public int insert(Connection conn, VOInterface vo) throws DefaultException {
        int intRet = -1;
        String logMethod = "insert(conn, vo)";
        StringBuffer sbInsertSql = new StringBuffer();
        String modFields = vo.getModifyFields(); //���޸ĵ��ֶ�
        String finishFlds = modFields;
        //������Զ�ID
        if (super.autoID == false)
            finishFlds = vo.getPkFields() + modFields;

        String table = vo.getTableName(); //��ǰvo��������Ӧ�ı���
        sbInsertSql.append(" INSERT INTO ").append(table).append("(");
        sbInsertSql.append(finishFlds.substring(0, finishFlds.length() - 1));
        sbInsertSql.append(") values (");
        StringTokenizer stkModColums = new StringTokenizer(finishFlds, ",");
        int iCount = stkModColums.countTokens();
        if (iCount < 1) //�ݴ���
            throw new DefaultException(UPDATE_ERROR);
        byteVo = vo;
        int idx = 0;
        String columName = "";
        String[] values = new String[iCount];
        StringBuffer sbTypes = new StringBuffer();
        StringBuffer sbCols = new StringBuffer();

        //��֯",?,?"
        while (stkModColums.hasMoreTokens()) {
            columName = stkModColums.nextToken();
            values[idx] = vo.getValue(columName);
            sbTypes.append(vo.getColType(columName));
            if (vo.getColType(columName).equals(DataTypes.BYTES)) {
                log.showLog("000--------bytes-------");
                byteFldName = columName;

            }
            sbCols.append("?,");
            sbTypes.append(",");
            idx++;
        }

        String strTmp = sbCols.toString();
        strTmp = strTmp.substring(0, strTmp.toString().length() - 1);
        sbInsertSql.append(strTmp);
        sbInsertSql.append(" )");

        try {
            //ִ��sql
            intRet = execUpdate(conn, sbInsertSql.toString(), values, sbTypes
                                .toString());
        } catch (DefaultException e) {
            log.showLog(logMethod + e.getMessage());
            throw new DefaultException(UPDATE_ERROR, e);
        }
        return intRet;
    }

    /**
     * �������
     * @param conn ���ݿ�����
     * @param aryData List���ݶ���
     * @param vo ��ǰvo����
     * @return �������ٱ�
     * @throws DefaultException
     */

    public int multiInsert(Connection conn, List aryData, VOInterface vo) throws
        DefaultException {
        log.showLog("modify:" + vo.getModifyFields());
        //������Զ�ID
        String finishFlds = vo.getModifyFields();
        if (super.autoID == false)
            finishFlds = vo.getPkFields() + vo.getModifyFields();

        return multiInsert(conn, aryData, vo, finishFlds);
    }

    /**
     * �������
     * @param conn ���ݿ�����
     * @param aryData List���ݶ���
     * @param vo ��ǰvo����
     * @param columNames ��Ҫ�޸ĵ��ֶ�
     * @return �������ٱ�
     * @throws DefaultException
     */
    public int multiInsert(Connection conn, List aryData, VOInterface vo,
                           String columNames) throws DefaultException {
        int intRet = -1;
        String logMethod = "multiInsert(conn, aryData,vo,columNames)";

        StringBuffer sbInsertSql = new StringBuffer();
        log.showLog("-------columname " + columNames);
        columNames = columNames.trim();
        int pos = columNames.lastIndexOf(",");
        if (pos == (columNames.length() - 1))
            columNames = columNames.substring(0, pos);
            //��֯insert SQL���

        sbInsertSql.append(" INSERT INTO ").append(vo.getTableName());
        sbInsertSql.append("(");
        sbInsertSql.append(columNames).append(") values (");
        StringTokenizer stkModColums = new StringTokenizer(columNames, ",");
        int iCount = stkModColums.countTokens();
        if (iCount < 1) //�ݴ���
            throw new DefaultException(UPDATE_ERROR);

        int idx = 0;
        String columName = "";
        String[] values = new String[iCount];
        String[] names = new String[iCount];
        StringBuffer sbTypes = new StringBuffer();
        StringBuffer sbCols = new StringBuffer();

        //��֯",?,?"���ָ�ʽ
        int iCol = 0;
        while (stkModColums.hasMoreTokens()) {
            columName = stkModColums.nextToken();
            names[iCol++] = columName;
            sbTypes.append(vo.getColType(columName)).append(",");
            sbCols.append("?,");
        }

        this.className = vo.getClass().getName();
        sbInsertSql.append(sbCols.substring(0, sbCols.toString().length() - 1));
        sbInsertSql.append(" )");
        VOInterface voTmp = null;
        int curRow = 0;
        try {
            for (idx = 0; idx < aryData.size(); idx++) {
                voTmp = this.getInstanceVO(vo); //ʵ��������
                voTmp = (VOInterface) aryData.get(idx); //�õ����Ӧ��vo����
                for (int col = 0; col < iCount; col++) {

                    values[col] = voTmp.getValue(names[col]);
                }
                //ִ��sql
                curRow += execUpdate(conn, sbInsertSql.toString(), values,
                                     sbTypes.toString());
            }
            return curRow;
        } catch (DefaultException e) {
            log.showLog(logMethod + e.getMessage());
            throw new DefaultException(e);
        }
    }

    /**
     * ִ�ж���sql���
     * @param strsql sql���
     * @param values ֵ����(values[0]="0"; values[1]="name");
     * @param types ��������(String types),�Զ��ŷָ�"long,String"
     * @return �޸ļ�¼��,-1Ϊʧ��
     * @throws DefaultException
     */

    public int execUpdate(String strsql, String[] values, String types) throws
        DefaultException {
        Connection conn = getConnection();
        return execUpdate(conn, strsql, values, types);

    }

    /**
     * ִ�ж���sql���
     * @param strsql sql���
     * @param aryValues ֵ����(aryValues.add("1");aryValues.add("name");
     * @param types ��������(String types),�Զ��ŷָ�"long,String"
     * @return �޸ļ�¼��,-1Ϊʧ��
     * @throws DefaultException
     */
    public int execUpdate(String strsql, List aryValues, String types) throws
        DefaultException {
        Connection conn = getConnection();
        String[] values = new String[aryValues.size()];

        for (int idx = 0; idx < aryValues.size(); idx++)
            values[idx] = String.valueOf(aryValues.get(idx));
        return execUpdate(conn, strsql, values, types);

    }

    /**
     * ִ�ж���SQL���
     * @param strsql sql���(String strsql)
     * @param vo ��ǰ��vo����VOInterface vo)
     * @param columns �ֶ����֣��Զ��ŷָ�"id,name,sex"��(columNames String)
     * @return �޸ļ�¼��,-1Ϊʧ��
     * @throws DefaultException
     */

    public int execUpdate(String strsql, VOInterface vo, String columns) throws
        DefaultException {
        Connection conn = super.getConnection();

        return execUpdate(conn, strsql, vo, columns);
    }

    /**
     * ִ�ж���SQL���
     * @param strsql sql���(String strsql)
     * @return �޸ļ�¼��,-1Ϊʧ��
     * @throws DefaultException
     */
    public int execUpdate(String strsql) throws DefaultException {
        Connection conn = getConnection();
        return execUpdate(conn, strsql);
    }

    /**
     * ɾ����¼��
     * @param vo ��ǰ��vo����vo VOInterface)
     * @return ɾ����¼��,-1Ϊɾ��ʧ��
     * @throws DefaultException
     */

    public int delete(VOInterface vo) throws DefaultException {
        Connection conn = getConnection();
        return delete(conn, vo);
    }

    /**
     * �޸ļ�¼��
     * @param view ��ǰ��vo����vo VOInterface)
     * @return �޸ļ�¼��,-1Ϊɾ��ʧ��
     * @throws DefaultException
     */
    public int update(VOInterface view) throws DefaultException {
        Connection conn = getConnection();
        return update(conn, view);
    }

    /**
     * ����һ����������
     * @param vo ��ǰ��vo����vo VOInterface)
     * @return ������¼��,-1Ϊɾ��ʧ��
     * @throws DefaultException
     */
    public int insert(VOInterface vo) throws DefaultException {
        Connection conn = getConnection();
        return insert(conn, vo);
    }

    /**
     * �������
     * @param aryData �������ݣ�aryListData List)
     * @param vo ��ǰ��vo����vo VOInterface)
     * @return �������ٱ�����
     * @throws DefaultException
     */

    public int multiInsert(List aryData, VOInterface vo) throws
        DefaultException {
        Connection conn = getConnection();
        return multiInsert(conn, aryData, vo);
    }

    /**
     * �������
     * @param aryData �������ݣ�aryListData List)
     * @param vo ��ǰ��vo����vo VOInterface)
     * @param columNames �ֶ����֣��Զ��ŷָ�"id,name,sex"��(columNames String)
     * @return �������ٱ�����
     * @throws DefaultException
     */
    public int multiInsert(List aryData, VOInterface vo, String columNames) throws
        DefaultException {
        Connection conn = getConnection();
        return multiInsert(conn, aryData, vo, columNames);
    }
    public Object queryPage(String[] strsql, long start, long end, VOInterface vo, int type) throws DefaultException {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        Object obj = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(strsql[0]);

            StringTokenizer stTypes = new StringTokenizer(strsql[1], ",");
            if (stTypes.countTokens() != 2) {
                log.showLog("��ѯʱ,��������������ֵ����������!");
                throw new DefaultException(QUERY_ERROR_ID);
            }

            //��setPstmtParam�����
            String strType = "";
            Object value;
            int idx = 0;
            
            pstmt = DialectFactory.getDialect().setStatementPageValue(pstmt, 1, (int)start, 2, (int)end);
            rs = pstmt.executeQuery();
            
            //��ѯ����
            obj = queryRs(rs, vo, type);
            rs.close();
            rs = null;
            pstmt.close();
            pstmt = null;
        } catch (SQLException se) {
            se.printStackTrace();
            throw new DefaultException(se);
        } catch (Exception e) {
            e.printStackTrace(); 
            throw new DefaultException(e);
        } finally {
            super.close(rs);
            rs = null;
            super.close(pstmt);
            pstmt = null;
            if (this.autoClose == true){
                log.showLog("close connection wengnb--");
                super.close(conn);
            }
        }
        return obj;

    }
}