package com.gever.jdbc.sqlhelper;

import java.sql.*;
import java.util.*;

import com.gever.exception.*;
import com.gever.jdbc.database.dialect.DialectFactory;
import com.gever.vo.*;

/**
 * 数据库操作基类(数据库接口)
 * 设计思路：
 * 1、集成了系统所有与数据打交道的所有操作：一般查询和动作查询。
 * 2、支持固定sql语句和预编译sql语句。
 * 3、除了一个日志属性外，不使用其他属性。
 * 4、支持Connection事务功能
 * 只有在需要与数据库打交道时才获取连接；
 * <p>Title:数据库操作基类 </p>
 * <p>Description: 数据库操作基类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class DefaultSQLHelper extends SQLHelper {

    private String className = ""; //类的名字关键是
    private static final String QUERY_ERROR_ID = "sys_query_001";
    private static final String NULL_ERROR_ID =
        "java.lang.NullPointerException";
    private SQLHelperUtil sUtil = new SQLHelperUtil();
    /**
     * 主要考虑多数据库
     * @param dbData 数据库名称
     */
    public DefaultSQLHelper(String dbData) {
        super(dbData);
    }

    public DefaultSQLHelper() {
    }

    /**
     * 查询简单的SQL语句
     * @param strsql sql语句
     * @param type 查询类型
     * @return 返回结果
     * @throws DefaultException
     */
    public Object query(String strsql, int type) throws DefaultException {
        return this.query(strsql, null, type);
    }

    /**
     * 查询一般SQL语句
     * @param strsql sql语句
     * @return 返回结果
     * @throws DefaultException
     */
    public Object query(String strsql) throws DefaultException {
        return this.query(strsql, null, DataTypes.RS_LIST_MAP);
    }

    /**
     * 查询sql 语句,ArrayList记录以VO行式封装
     * @param strsql sql语句
     * @param view vo对象
     * @return 返回结果
     * @throws DefaultException
     */
    public List queryByListVo(String strsql, VOInterface view) throws
        DefaultException {
        return (List)this.query(strsql, view, DataTypes.RS_LIST_VO);
    }

    /**
     * 按主键查询
     * @param vo vo对象(注vo对象,要给vo对象的主键进行赋值,否则是会出错)
     * @return 返回结果
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
     * 查询sql 语句,ArrayList记录以VO行式封装
     * @param strsql sql语句
     * @param vo vo对象
     * @return 返回结果
     * @throws DefaultException
     */
    public Object queryByVo(String strsql, VOInterface vo) throws
        DefaultException {
        return this.query(strsql, vo, DataTypes.RS_SINGLE_VO);
    }

    /**
     * 查询一般SQL语句
     * @param strsql sql语句
     * @param view VO对象
     * @param type 查询类型
     * @return 返回结果
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
            //查义数据
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
     * 查询一般SQL语句
     * @param strsql strsql[0]为sql语句,strsql[1]为类型如"string,date,int":
     * 注:以类型之间以逗号分隔,并且类型与aryValue的个数要相匹配
     * @param aryValues 值列表
     * @param type 查询类型
     * @return 返回结果
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
     * 查询一般SQL语句
     * @param strsql strsql[0]为sql语句,strsql[1]为类型如"string,date,int":
     * 注:以类型之间以逗号分隔,并且类型与aryValue的个数要相匹配
     * @param sValues 值数组：sValues[0],sValues[1]
     * @param type 查询类型
     * @return 返回结果
     * @throws DefaultException
     */

    public Object query(String[] strsql, String[] sValues, int type) throws
        DefaultException {

        return query(strsql, sValues, null, type);
    }

    /**
     * 查询一般SQL语句
     * @param strsql strsql[0]为sql语句,strsql[1]为类型如"string,date,int":
     * 注:以类型之间以逗号分隔,并且类型与aryValue的个数要相匹配
     * @param sValues valueTypes[0]为相对应,valueTypes[1]
     * @param vo 当前的vo对象（vo VOInterface)
     * @param type 查询类型
     * @return 返回结果
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
                log.showLog("查询时,发现类型总数与值的总数不对!");
                throw new DefaultException(QUERY_ERROR_ID);
            }

            //给setPstmtParam方面的
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

            //查询数据
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
     * 查询SQL语句,根据字段名称及vo对象的值,进行查询
     * @param strsql SQL语句
     * @param fldNames 字段如"name,username,memo":
     * @param view VO对象
     * @param type 查询类型
     * @return 返回结果
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

            //设置PreparedStatement对象参数
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
            //查义数据
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
     * 根据rs对象,返回相应的数据类型
     * @param rs ResultSet数据对象
     * @param view VO对象
     * @param type 返回对象类型
     * @return 数据对象
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
     * 设置PreparedStatement对象参数
     * @param pstmt PreparedStatement对象
     * @param value 相对应的值
     * @param type 类型
     * @param col 第几个字段
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
        if (DataTypes.STRING.equals(type)) { //string 类型
            pstmt.setString(col, strValue);

        } else if (DataTypes.INT.equals(type)) { //int类型
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.INTEGER);
            } else {
            	log.console(this.getClass(),"setPstmtParam:INT:value",strValue);
                pstmt.setInt(col, Integer.parseInt(strValue));

            }

        } else if (DataTypes.LONG.equals(type)) { //long型
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.BIGINT);
            } else {
                pstmt.setLong(col, Long.parseLong(strValue));
            }

        } else if (DataTypes.DOUBLE.equals(type)
                   || DataTypes.FLOAT.equals(type)) { //double类型
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.DOUBLE);
            } else {
                pstmt.setDouble(col, Double.parseDouble(strValue));
            }

        } else if (DataTypes.DECIMAL.equals(type)
                   || DataTypes.DECIMAL.equals(type)) { //double类型
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.DECIMAL);
            } else {
                pstmt.setBigDecimal(col, new java.math.BigDecimal(strValue));
            }

        } else if (DataTypes.DATE.equals(type)) { //日期类型
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

        } else if (DataTypes.TIMESTMAP.equals(type)) { //时间类型
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.TIMESTAMP);
            } else {
                pstmt.setTimestamp(col, java.sql.Timestamp.valueOf(strValue));
            }
        } else if (DataTypes.TIME.equals(type)) { //时间类型
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.TIME);
            } else {
                pstmt.setTime(col, java.sql.Time.valueOf(strValue));
            }
        } else if (DataTypes.BOOLEAN.equals(type)) { //日期类型
            if (isNull(strValue)) {
                pstmt.setNull(col, java.sql.Types.BOOLEAN);
            } else {
                pstmt.setBoolean(1, Boolean.valueOf(strValue).booleanValue());
            }

        } else if (DataTypes.CLOB.equals(type)) { //日期类型
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
                    byteFldName); //反射
                //log.showLog("-----get bytes--start--length---- " + bytes.length);
                pstmt.setBytes(col, bytes);
            } catch (Exception e) {
                e.printStackTrace();
                log.console(this.getClass(),"setPstmtParam:BYTES","-----get byte出错-----");
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
     * 获得记录字段名称
     * @param rs 记录集
     * @return rs中的字段名集合
     * @throws DefaultException
     */
    private List rsToMeta(ResultSet rs) throws DefaultException {
        List retAry = new ArrayList();
        String logMethod = "DefaultSQLHelper.rsToMeta(ResultSet  rs): ";

        try {
            //取得结果集的单元信息
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
     * ResultSet 转换成 List
     * @param rs 数据库结果集对象
     * @return List列表
     */

    private List rsToListMap(ResultSet rs) throws DefaultException {
        List aryRet = new ArrayList();
        Map rsMap = new HashMap();
        String logMethod = "DefaultSQLHelper.rsToArray(ResultSet rs): ";

        try {
            //取得结果集的单元信息
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNum = rsmd.getColumnCount();
            //列数为0,返回空的ArrayList
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
     * 获取VO实例化对象
     * @param view VO对象
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
     * 得到VO对象以List格式
     * @param rs ResultSet记录集对象
     * @param view VO对象
     * @return 得到VO对象以List格式
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
            //取得结果集的单元信息
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnNum = rsMeta.getColumnCount();

            //列数为0,返回空的List
            if (columnNum == 0) {
                return aryRet;
            }

            StringTokenizer stk = new StringTokenizer(vo.getAllFields(), ",");
            int iCount = stk.countTokens();
            String[] aryCol = new String[iCount];
            int[] intCol = new int[columnNum]; //实际要填的列
            int row = 0;
            int factCol = 0; //计算器
            String strCol = new String();
            List allList = new ArrayList();

            //根据比对得对要更新的实际字段
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
                    //重新实例化VO对象
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
            } else { //为oracle
                while (rs.next()) {
                    //重新实例化VO对象
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
     * 得到VO对象格式
     * @param rs ResultSet记录集对象
     * @param view VO对象
     * @return 得到VO对象格式
     * @throws DefaultException
     */

    private VOInterface rsToVo(ResultSet rs, VOInterface view) throws
        DefaultException {
        String logMethod = "DefaultSQLHelper.rsToVO(rs, view): ";
        List aryRet = new ArrayList();
        HashMap rsMap = new HashMap();
        this.className = view.getClass().getName();
        VOInterface vo = this.getInstanceVO(view); //实例化这个vo类

        try {

            //取得结果集的单元信息
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNum = rsmd.getColumnCount();
            //列数为0,返回空的ArrayList
            if (columnNum == 0) {
                return vo;
            }

            String strValue = "";
            if (rs.next()) {
                for (int i = 1; i <= columnNum; i++) {
                    //bytes变量
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

                        //clob变量
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
     * 获得单条记录值
     * @param  rs 数据库结果集对象
     * @return 获得单条记录值(Map)
     * @throws DefaultException
     */
    private Map rsSingleToMap(ResultSet rs) throws DefaultException {
        Map hMap = new HashMap();
        String logMethod = "DefaultSQLHelper.rsSingleToMap(ResultSet rs): ";

        //取得结果集的单元信息
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNum = rsmd.getColumnCount();
            //列数为0,返回空的ArrayList
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

                    //如果字段类型为字符型，且得到的字段结果为空，将该字段的值赋值为""。
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
     * 执行动作查寻
     * @param conn 数据库连结
     * @param strsql sql语句
     * @param values 值数组:例如{"200","翁乃彬"}
     * @param types  类型字串:"String,int,long,"
     * @return 已更新的记录数 注:-1为失败
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
     * 执行异动查寻
     * @param conn 数据库连结
     * @param strsql sql语句
     * @param vo 一个vo对象
     * @param columns  字段名称:"name,amt,password,"
     * @return 已更新的记录数 注:-1为失败
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
     * 执行异动查寻,
     * @param conn 数据库连结
     * @param strsql sql语句
     * @return 已更新的记录数 注:-1为失败
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
     * 简单删除数据(主要考虑接入事务)
     * @param conn 数据库连结
     * @param vo vo对象
     * @return 删除记录数,-1为删除失败
     * @throws DefaultException
     */
    public int delete(Connection conn, VOInterface vo) throws DefaultException {
        int intRet = -1;
        String logMethod = " delete(conn, vo)";

        ArrayList aryPk = new ArrayList();
        String strName = new String();
        StringBuffer sbWhere = new StringBuffer();

        //组织删除条件子句
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

            log.showLog("主键值：" + pkValues[col]);

            types = types + vo.getColType(strValue) + ",";
            sbWhere.append(" ").append(strValue).append("=? ");
            log.showLog("---del--" + col + " count= " + stkNames.countTokens());
            if (col < iCount - 1)
                sbWhere.append(" AND ");
            ++col;
        }

        //构造整体删除语句
        StringBuffer sbDelSql = new StringBuffer();
        sbDelSql.append("DELETE FROM ").append(vo.getTableName());
        sbDelSql.append(" WHERE  ").append(sbWhere.toString());

        //执行sql语句
        try {

            intRet = execUpdate(conn, sbDelSql.toString(), pkValues, types);
        } catch (DefaultException e) {
            log.showLog(logMethod + e.getMessage());
            throw new DefaultException(e);
        }
        return intRet;
    }

    /**
     * 修改动作
     * @param conn 数据库连结
     * @param view vo对象
     * @return 删除
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
        if (modCount < 1) //容错处理
            throw new DefaultException(UPDATE_ERROR);
        //构造update SQL语句
        sbUpdateSql.append(" UPDATE ").append(view.getTableName());
        sbUpdateSql.append(" SET ");
        int idx = 0;
        String[] values = new String[iCount];
        StringBuffer sbTypes = new StringBuffer();
        String columName = "";

        //修改字段
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

        //组织条件子句
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
            //执行sql
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
     * 新增
     * @param conn 数据库连结
     * @param vo 当前VO对象
     * @return 已新增的记录数
     * @throws DefaultException
     */
    public int insert(Connection conn, VOInterface vo) throws DefaultException {
        int intRet = -1;
        String logMethod = "insert(conn, vo)";
        StringBuffer sbInsertSql = new StringBuffer();
        String modFields = vo.getModifyFields(); //可修改的字段
        String finishFlds = modFields;
        //如果是自动ID
        if (super.autoID == false)
            finishFlds = vo.getPkFields() + modFields;

        String table = vo.getTableName(); //当前vo对象所对应的表名
        sbInsertSql.append(" INSERT INTO ").append(table).append("(");
        sbInsertSql.append(finishFlds.substring(0, finishFlds.length() - 1));
        sbInsertSql.append(") values (");
        StringTokenizer stkModColums = new StringTokenizer(finishFlds, ",");
        int iCount = stkModColums.countTokens();
        if (iCount < 1) //容错处理
            throw new DefaultException(UPDATE_ERROR);
        byteVo = vo;
        int idx = 0;
        String columName = "";
        String[] values = new String[iCount];
        StringBuffer sbTypes = new StringBuffer();
        StringBuffer sbCols = new StringBuffer();

        //组织",?,?"
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
            //执行sql
            intRet = execUpdate(conn, sbInsertSql.toString(), values, sbTypes
                                .toString());
        } catch (DefaultException e) {
            log.showLog(logMethod + e.getMessage());
            throw new DefaultException(UPDATE_ERROR, e);
        }
        return intRet;
    }

    /**
     * 多笔新增
     * @param conn 数据库连结
     * @param aryData List数据对象
     * @param vo 当前vo对象
     * @return 新增多少笔
     * @throws DefaultException
     */

    public int multiInsert(Connection conn, List aryData, VOInterface vo) throws
        DefaultException {
        log.showLog("modify:" + vo.getModifyFields());
        //如果是自动ID
        String finishFlds = vo.getModifyFields();
        if (super.autoID == false)
            finishFlds = vo.getPkFields() + vo.getModifyFields();

        return multiInsert(conn, aryData, vo, finishFlds);
    }

    /**
     * 多笔新增
     * @param conn 数据库连结
     * @param aryData List数据对象
     * @param vo 当前vo对象
     * @param columNames 需要修改的字段
     * @return 新增多少笔
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
            //组织insert SQL语句

        sbInsertSql.append(" INSERT INTO ").append(vo.getTableName());
        sbInsertSql.append("(");
        sbInsertSql.append(columNames).append(") values (");
        StringTokenizer stkModColums = new StringTokenizer(columNames, ",");
        int iCount = stkModColums.countTokens();
        if (iCount < 1) //容错处理
            throw new DefaultException(UPDATE_ERROR);

        int idx = 0;
        String columName = "";
        String[] values = new String[iCount];
        String[] names = new String[iCount];
        StringBuffer sbTypes = new StringBuffer();
        StringBuffer sbCols = new StringBuffer();

        //组织",?,?"这种格式
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
                voTmp = this.getInstanceVO(vo); //实例化对象
                voTmp = (VOInterface) aryData.get(idx); //得到相对应的vo对象
                for (int col = 0; col < iCount; col++) {

                    values[col] = voTmp.getValue(names[col]);
                }
                //执行sql
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
     * 执行动作sql语句
     * @param strsql sql语句
     * @param values 值对象(values[0]="0"; values[1]="name");
     * @param types 数据类型(String types),以逗号分隔"long,String"
     * @return 修改记录数,-1为失败
     * @throws DefaultException
     */

    public int execUpdate(String strsql, String[] values, String types) throws
        DefaultException {
        Connection conn = getConnection();
        return execUpdate(conn, strsql, values, types);

    }

    /**
     * 执行动作sql语句
     * @param strsql sql语句
     * @param aryValues 值对象(aryValues.add("1");aryValues.add("name");
     * @param types 数据类型(String types),以逗号分隔"long,String"
     * @return 修改记录数,-1为失败
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
     * 执行动作SQL语句
     * @param strsql sql语句(String strsql)
     * @param vo 当前的vo对象（VOInterface vo)
     * @param columns 字段名字，以逗号分隔"id,name,sex"，(columNames String)
     * @return 修改记录数,-1为失败
     * @throws DefaultException
     */

    public int execUpdate(String strsql, VOInterface vo, String columns) throws
        DefaultException {
        Connection conn = super.getConnection();

        return execUpdate(conn, strsql, vo, columns);
    }

    /**
     * 执行动作SQL语句
     * @param strsql sql语句(String strsql)
     * @return 修改记录数,-1为失败
     * @throws DefaultException
     */
    public int execUpdate(String strsql) throws DefaultException {
        Connection conn = getConnection();
        return execUpdate(conn, strsql);
    }

    /**
     * 删除记录数
     * @param vo 当前的vo对象（vo VOInterface)
     * @return 删除记录数,-1为删除失败
     * @throws DefaultException
     */

    public int delete(VOInterface vo) throws DefaultException {
        Connection conn = getConnection();
        return delete(conn, vo);
    }

    /**
     * 修改记录数
     * @param view 当前的vo对象（vo VOInterface)
     * @return 修改记录数,-1为删除失败
     * @throws DefaultException
     */
    public int update(VOInterface view) throws DefaultException {
        Connection conn = getConnection();
        return update(conn, view);
    }

    /**
     * 新增一笔数据资料
     * @param vo 当前的vo对象（vo VOInterface)
     * @return 新增记录数,-1为删除失败
     * @throws DefaultException
     */
    public int insert(VOInterface vo) throws DefaultException {
        Connection conn = getConnection();
        return insert(conn, vo);
    }

    /**
     * 多笔新增
     * @param aryData 集合数据（aryListData List)
     * @param vo 当前的vo对象（vo VOInterface)
     * @return 新增多少笔资料
     * @throws DefaultException
     */

    public int multiInsert(List aryData, VOInterface vo) throws
        DefaultException {
        Connection conn = getConnection();
        return multiInsert(conn, aryData, vo);
    }

    /**
     * 多笔新增
     * @param aryData 集合数据（aryListData List)
     * @param vo 当前的vo对象（vo VOInterface)
     * @param columNames 字段名字，以逗号分隔"id,name,sex"，(columNames String)
     * @return 新增多少笔资料
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
                log.showLog("查询时,发现类型总数与值的总数不对!");
                throw new DefaultException(QUERY_ERROR_ID);
            }

            //给setPstmtParam方面的
            String strType = "";
            Object value;
            int idx = 0;
            
            pstmt = DialectFactory.getDialect().setStatementPageValue(pstmt, 1, (int)start, 2, (int)end);
            rs = pstmt.executeQuery();
            
            //查询数据
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