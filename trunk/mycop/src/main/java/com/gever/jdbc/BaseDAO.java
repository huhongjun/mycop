package com.gever.jdbc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.gever.config.Environment;
import com.gever.exception.DefaultException;
import com.gever.jdbc.database.dialect.Dialect;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.OrderUtils;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.struts.Constant;
import com.gever.util.IdMng;
import com.gever.util.NumberUtil;
import com.gever.util.StringUtils;
import com.gever.util.log.Log;
import com.gever.vo.VOInterface;
import com.gever.web.util.ActiveUsers;

/**
 * <p>Title: DAO�ĸ���</p>
 * <p>Description: ����������ɾ�����޸ġ����ơ���ҳ��ҵ���߼�</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class BaseDAO {
    protected String dbData = Constant.DATA_SOURCE;   // ����Դ����
    protected String userID;           // ��ǰ�û�ID��
    private long count = 0l;           // ��ǰͳ��ֵ
    protected Log log = Log.getInstance(BaseDAO.class);    //log��
    protected boolean isIdMng = true;  //�Ƿ�Ϊ������ID
    private String sqlWhere = "";      // sql�������
    private String pageSql = "";       // ��ǰҪ��ҳ��sql���
    protected String orderColumn = ""; // ������ֶ�
    protected String orderType = "";   // ��������

    ActiveUsers au = ActiveUsers.getInstance();
    public BaseDAO(String dbData, String userID) {
        this.dbData = dbData;

        this.setUserID(userID);
        if (au.isOracle() == true){
                    setOracleSQL();
            }
    }

    public BaseDAO() {

    }

    public BaseDAO(String dbData) {
            if (isOracle() == true){
                    setOracleSQL();
            }
        this.dbData = dbData;
    }

    /**
     * ����oracle��sql���
     */
    public void setOracleSQL(){

    }

    /**
     * �Ƿ�Ϊoracle���ݿ�
     * @return �Ƿ�
     */
    public boolean isOracle(){
            return au.isOracle();
    }
    /**
     * ����
     * @param vo ��ǰvo����
     * @return �������ٱ�,-1Ϊʧ��
     * @throws DefaultException
     */
    public int insert(VOInterface vo) throws DefaultException {

        SQLHelper helper =  new DefaultSQLHelper(dbData); ;
        if (isIdMng == true){
            String pkName = vo.getPkFields();
            pkName = toPkFld(pkName);
            vo.setValue(pkName, IdMng.getModuleID(this.userID));
            log.showLog("pkname--->" + pkName);
            log.showLog("pkValue--->" + IdMng.getModuleID(this.userID));
        }

        if (checkInsert(vo) != 1){
            throw new DefaultException("sys_ins_001");
        }

        helper.setAutoID(false);
        int iRet = 0;
        try {
            iRet = helper.insert(vo);
        } catch (DefaultException e) {
            throw new DefaultException("sys_ins_002", e);
        }
        return iRet;
    }

    /**
     * ������֧������
     * linhs 2005-1-7
     * @param vo ��ǰvo����
     * @return �������ٱ�,-1Ϊʧ��
     * @throws DefaultException
     */
    public int insert(VOInterface vo, Connection conn) throws DefaultException {
        SQLHelper helper =  new DefaultSQLHelper(dbData); ;
        if (isIdMng == true){
            String pkName = vo.getPkFields();
            pkName = toPkFld(pkName);
            vo.setValue(pkName, IdMng.getModuleID(this.userID));
            log.showLog("pkname--->" + pkName);
            log.showLog("pkValue--->" + IdMng.getModuleID(this.userID));
        }

        if (checkInsert(vo) != 1){
            throw new DefaultException("sys_ins_001");
        }

        helper.setAutoID(false);
        int iRet = 0;
        try {
            boolean autoClose = helper.isAutoClose();
            helper.setAutoClose(false);
            iRet = helper.insert(conn, vo);
            helper.setAutoClose(autoClose);
            return iRet;
        } catch (DefaultException e) {
            throw new DefaultException("sys_ins_002", e);
        }
    }


    /**
     * ����ǰ�ü��
     * @param vo ��ǰvo����
     * @return 1Ϊ�ɹ�,0Ϊʧ��
     */
    protected int checkInsert(VOInterface vo){
        return 1;
    }
    /**
     * �޸�ǰ�ü��
     * @param vo ��ǰvo����
     * @return 1Ϊ�ɹ�,0Ϊʧ��
     */
    protected int checkUpdate(VOInterface vo) {
        return 1;
    }
    /**
     * ��������
     * @param vo ��ǰ��vo����
     * @return 1Ϊ�ɹ�,0Ϊʧ��
     */
    protected int postInsert(VOInterface vo) {
        return 1;
    }

    /**
     * �����޸�
     * @param vo ��ǰ��vo����
     * @return 1Ϊ�ɹ�,0Ϊʧ��
     */

    protected int postModify(VOInterface vo) {
        return 1;
    }

    /**
     * �޸�
     * @param vo ��ǰvo����
     * @return �޸Ķ��ٱ�,-1Ϊʧ��
     * @throws DefaultException
     */

    public int update(VOInterface vo) throws DefaultException {

        SQLHelper helper = new DefaultSQLHelper(dbData); ;
        if (checkUpdate(vo) != 1) {
            throw new DefaultException("sys_mod_002");
        }

        try{
            return helper.update(vo);
        } catch(DefaultException e){
            throw new DefaultException("sys_mod_001", e);
        }
    }

    /**
     * �޸�
     * linhs 2005-1-15
     * @param vo ��ǰvo����
     * @return �޸Ķ��ٱ�,-1Ϊʧ��
     * @throws DefaultException
     */
    public int update(VOInterface vo, Connection conn) throws DefaultException {

        SQLHelper helper = new DefaultSQLHelper(dbData); ;
        if (checkUpdate(vo) != 1) {
            throw new DefaultException("sys_mod_002");
        }

        try{
            boolean autoClose = helper.isAutoClose();
            helper.setAutoClose(false);
            int n = helper.update(conn, vo);
            helper.setAutoClose(autoClose);
            return n;
        } catch(DefaultException e){
            throw new DefaultException("sys_mod_001", e);
        }
    }


    /**
     * �õ�����������
     * @param pkName ��������
     * @return ��������
     */
    public String toPkFld(String pkName) {

        int pos = pkName.indexOf(",");
        String strRet = pkName;
        if (pos > 1) {
            strRet = pkName.substring(0, pos);
        }
        return strRet;
    }

    /**
     * ���Ƽ�¼
     * @param keyValue ��������
     * @param vo ��ǰvo����
     * @return ���ٱ�����
     * @throws DefaultException
     */
    public int copy(String[] keyValue,VOInterface vo) throws DefaultException {

        SQLHelper helper = new DefaultSQLHelper(dbData);
        String pkName = toPkFld(vo.getPkFields());
        Object obj = null;
        int iCount = keyValue.length;
        StringBuffer sb = new StringBuffer();
        int iRet = -1;
        helper.setAutoID(false);
        try {
            for (int idx = 0; idx < iCount; idx++) {
                vo = this.getInstanceVO(vo);
                vo.setValue(pkName, keyValue[idx]);
                obj = helper.queryByPK(vo);
                vo = (VOInterface) obj;
                vo.setValue(pkName, IdMng.getModuleID(this.userID, idx));

                iRet += helper.insert(vo);
            }
        } catch (DefaultException e) {
            throw new DefaultException("sys_copy_001",e);
        }
        return iRet;

    }

    /**
     * ɾ��
     * @param vo ��ǰvo����
     * @return ɾ�����ٱ�,-1Ϊʧ��
     * @throws DefaultException
     */

    public int delete(VOInterface vo) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        try {
            return helper.delete(vo);
        } catch (DefaultException e) {
            throw new DefaultException("sys_del_001",e);
        }

    }

    public String getPageSql() {
        return "";
    }

    protected void setWhere(String strWhere) {
        this.sqlWhere = strWhere;
    }

    /**
     * ��ҳ��������ѯ
     * @param vo ��ѯʱ�õ�VO����
     * @param start ��ʼ���
     * @param howMany �������
     * @return ��¼������
     * @throws DefaultException
     */
    public List queryByPage(VOInterface vo, long start, long howMany) throws
        DefaultException {
        List aryData = new ArrayList();
        String[] arySql = new String[2];
        String[] values = new String[2];

        if (start <0)   start =0;
        
        if (isOracle()){
            values[0] = String.valueOf(start+howMany);
            values[1] = String.valueOf(start );
        } else {
            values[0] = String.valueOf(start);
            values[1] = String.valueOf(start + howMany);
        }

        SQLHelper helper = new DefaultSQLHelper(dbData);
        if (!StringUtils.isNull(queryTblName) &&
            !StringUtils.isNull(orderColumn))
            orderColumn = this.queryTblName + "." + orderColumn;
        log.showLog("----queryTblName---->" + queryTblName +
                    " -orderColumn----" + orderColumn);

        String strsql = getQuerySQLByOrderBy();

        arySql[0] = getDialect().getLimitString(strsql);
        arySql[1] = "long,long";
        log.showLog("start:" + values[0]  + " end =" +values[1] );
        try{
            aryData = (ArrayList) helper.query(arySql, values, vo,
                                               DataTypes.RS_LIST_VO);
        }catch(DefaultException e){
            throw new DefaultException ("sys_qry_002",e);
        }

        log.showLog(" my page size="+aryData.size());
        return aryData;
    }

    /**
     * �õ������orderby��sql���
     * @return ���յ�sql���
     */
    protected String getQuerySQLByOrderBy(){
            return  OrderUtils.procOrderBy(getPageSql() +  this.sqlWhere,this.orderColumn,this.orderType);
    }

    /**
     * ��ҳ��������ѯ
     * @param vo ��ѯʱ�õ�VO����
     * @return ��¼������
     * @throws DefaultException
     */
    public List queryAll(VOInterface vo) throws
        DefaultException {
        List aryData = new ArrayList();
        SQLHelper helper =  new DefaultSQLHelper(dbData); ;
        try {
            aryData = (ArrayList) helper.queryByListVo(this.getPageSql() +
                this.sqlWhere, vo);
        } catch (DefaultException e) {
            throw new DefaultException("sys_qry_003", e);
        }

        return aryData;
    }

    /**
     * ͳ�Ƴ���ǰ�ļ�¼��
     * @param searchVo VO����
     * @return ��¼��
     * @throws DefaultException
     */
    public long queryByCount(VOInterface searchVo) throws DefaultException {

        StringBuffer sbSql = new StringBuffer();
        SQLHelper helper =  new DefaultSQLHelper(dbData); ;
        Log.setUselog4j(false);

        long lngCnt = 0;
        try {
            sbSql.append("select count(*) as cnt from (");
            sbSql.append(getPageSql()).append(this.sqlWhere);
            sbSql.append(") countTable ");
            log.console(this.getClass(),"queryByCount:SQL",sbSql.toString());
            Map map = (HashMap) helper.query(sbSql.toString(),
                                             DataTypes.RS_SINGLE_MAP);
            lngCnt = NumberUtil.stringToLong( (String) map.get("cnt"), 0);
            this.count =  lngCnt;

        } catch (DefaultException e) {
            throw new DefaultException("sys_qry_003", e);
        }
        return lngCnt;

    }

    /**
     * �õ����Ӧ��ʵ����ҳʵ��
     * @return ��ҳʵ��
     */
    public static Dialect getDialect() {

        String dialect = Environment.getProperty("database.dialect");
        try {
            return (Dialect) Class.forName(dialect).newInstance();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * ��ȡVOʵ��������
     * @param vo VO����
     * @return ObjectView
     */
    public VOInterface getInstanceVO(VOInterface vo) {
        VOInterface valueObject = null;
        String logMethod = "getInstanceVO(vo)";
        try {
            valueObject = (VOInterface) Class.forName(vo.getClass().getName()).
                newInstance();
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
     * ɾ�����е�����
     * @param aryPk ��������
     * @param vo ��ǰVO����
     * @return ɾ���ɹ����ٱ�,-1Ϊʧ��
     * @throws DefaultException
     */
    public int delBySelect(String[] aryPk, VOInterface vo) throws
        DefaultException {
        int intRet = 0;
        SQLHelper helper = helper = new DefaultSQLHelper(dbData); ;

        try {
            helper.begin();
            String name = "";
            String value = "";
            for (int idx = 0; idx < aryPk.length; idx++) {
                vo = getInstanceVO(vo);
                StringTokenizer stk = new StringTokenizer(aryPk[idx], "::");
                StringTokenizer stkPk = new StringTokenizer(vo.getPkFields(),",");
                while (stk.hasMoreTokens()) {
                    name = stkPk.nextToken();
                    value = stk.nextToken();
                    vo.setValue(name,value);
                }
                intRet += helper.delete(vo);
            }
            helper.commit();
        } catch (DefaultException e) {
            intRet = -1;
            helper.rollback();
            throw new DefaultException("sys_del_002");

        } finally {
            helper.end();
        }
        return intRet;

    }

    /**
     * ��������ѯ
     * @param vo ��ǰvo����
     * @return ��ѯ��vo����
     * @throws DefaultException
     */
    public Object queryByPk2(VOInterface vo) throws
        DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);

        StringTokenizer stkName = new StringTokenizer(vo.getPkFields(), ",");
        String name = "";
        String value = "";
        String values[] = new String[stkName.countTokens()];
        int idx = 0;
        StringBuffer sbPkSql = new StringBuffer(10);

        while (stkName.hasMoreTokens()) {
            name = stkName.nextToken();
            values[idx++] = vo.getValue(name);
            sbPkSql.append(" AND ");
            if (!StringUtils.isNull(queryTblName))
                                sbPkSql.append(queryTblName).append(".");
                        sbPkSql.append( name).append("=?");
        }

        String pkSql = this.getPageSql().toLowerCase();
        log.showLog(" page sql = >" + this.getPageSql());
        try {
            if (pkSql.indexOf(" where ") > 8) {
                return (VOInterface) helper.query(this.getPageSql() +
                                                  sbPkSql.toString(),
                                                  vo.getPkFields(), vo,
                                                  DataTypes.RS_SINGLE_VO);
            } else {
                return helper.queryByPK(vo);
            }
        } catch (DefaultException e) {
            throw new DefaultException("sys_qry_004", e);
        }

    }

    protected String queryTblName = "";
    public void setQueryTblName(String tblName) {
        this.queryTblName = tblName;
    }

    /**
     * ��������ѯ
     * @param vo ��ǰvo����
     * @return ��ѯ��vo����
     * @throws DefaultException
     */
    public Object queryByPk(VOInterface vo) throws
        DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        //helper.setAutoCommit(false);
        Object obj= helper.queryByPK(vo);
        //helper.commit();
        //helper.setAutoCommit(true);
        return obj;

    }

    public long getCount() {
        return count;
    }

    public void setSqlWhere(String sqlWhere) {
        this.sqlWhere = sqlWhere;
    }
    public void setPageSql(String pageSql) {
        this.pageSql = pageSql;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }
    public void setIsIdMng(boolean isIdMng) {
        this.isIdMng = isIdMng;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public void setDbData(String dbData) {
        this.dbData = dbData;
    }

        /**
         * @return
         */
        public String getOrderType() {
                return orderType;
        }

        /**
         * @return
         */
        public String getSqlWhere() {
                return sqlWhere;
        }
    public String getOrderColumn() {
        return orderColumn;
    }

}
