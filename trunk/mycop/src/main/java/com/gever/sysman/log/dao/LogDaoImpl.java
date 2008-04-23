package com.gever.sysman.log.dao;

import java.util.List;

import com.gever.vo.VOInterface;
import java.util.ArrayList;
import com.gever.sysman.log.vo.LogVO;
import com.gever.jdbc.database.dialect.Dialect;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.exception.DefaultException;
import java.util.HashMap;
import java.util.Map;
import com.gever.util.NumberUtil;
import com.gever.util.DateTimeUtils;
import com.gever.util.log.Log;
import com.gever.config.Constants;
import com.gever.config.Environment;
import com.gever.sysman.log.util.IdManager;
import com.gever.sysman.util.OrderList;

/**
 * <p>Title: LogDaoʵ����</p>
 * <p>Description: ��Ҫ�в�ѯ,ɾ��,��������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class LogDaoImpl implements LogDao {
    private String dbData = ""; //���ݿ���������
    protected SQLHelper helper = null;
    protected Log log = Log.getInstance(LogDaoImpl.class);
    protected static final String QUERY_SQL = "SELECT id,otime,username,module,ipaddress,action,filterlevel,type,memo FROM T_SYSTEM_LOG WHERE 1=1 ";
    private static final String DEL_SQL = "DELETE FROM T_SYSTEM_LOG WHERE 1=1 ";
    
    public LogDaoImpl() {
        this.dbData = Constants.DATA_SOURCE;
        helper = new DefaultSQLHelper(dbData);
    }
    
    public LogDaoImpl(String dbData) {
        this.dbData = dbData;
        helper = new DefaultSQLHelper(dbData);
    }

    /**
     * ����Ľӿ�
     * @param username �û�����
     * @param module ģ����
     * @param action ����
     * @param ipAddress ip��ַ
     * @param memo ��ע
     * @param type ����
     * @return ���������ɹ���¼��,-1Ϊʧ��
     */
    public int addLog(String username, String module, String action,
                      String ipAddress, String memo, int level, int type) throws
        DefaultException {

        int intRet = -1;
        LogVO vo = new LogVO();
        vo.setId(String.valueOf(IdManager.nextID()));
        vo.setAction(action);
        vo.setUsername(username);
        vo.setMemo(memo);
        vo.setModule(module);
        vo.setFilterlevel(String.valueOf(level));
        vo.setIpaddress(ipAddress);
        vo.setType(String.valueOf(type));
        vo.setOtime(DateTimeUtils.getCurrentDateTime());
        helper.setAutoID(false);
        intRet = helper.insert(vo);
        return intRet;
    }

    /**
     * ��ҳ��������ѯ
     * @param voByCond ��ѯʱ�õ�VO����
     * @param start ��ʼ���
     * @param end �������
     * @return ��¼������
     */
    //==========================================================
    //������ӣ�����JSP��ͼ�б�������
    private String[] orderby;
    public void setOrderby(String[] s){ this.orderby = s; }
    //==========================================================
    public List queryByPage(VOInterface searchVo, long start, long end) throws
        DefaultException {
        List aryData = new ArrayList();
        String[] arySql = new String[2];
        String[] values = new String[2];
        VOInterface vo = new LogVO();
        values[0] = String.valueOf(start);
        values[1] = String.valueOf(start+end);
        
        String temp = QUERY_SQL + this.getSqlWhere(searchVo) + " order by otime desc";
        
        //==========================================================
        //������ӣ�����JSP��ͼ�б�������
        temp = OrderList.getInstance().formatSQL(orderby,temp);
        //==========================================================
        
        arySql[0] = getDialect().getLimitString(temp);
        arySql[1] = "long,long";
        log.showLog("start:" + start + " end =" + (start+end));
        try {
            aryData = (ArrayList) helper.queryPage(arySql, start, (start + end), vo, DataTypes.RS_LIST_VO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DefaultException(e);
        }
//        aryData = (ArrayList) helper.query(arySql, values, vo,
//                                           DataTypes.RS_LIST_VO);
        
        return aryData;
    }

    /**
     * ͳ�Ƴ���ǰ�ļ�¼��
     * @param searchVo VO����
     * @return ��¼��
     */
    public long queryByCount(VOInterface searchVo) {

        StringBuffer sbSql = new StringBuffer();
        long lngCnt = 0;
        try {
            sbSql.append("select count(*) as cnt from (");
            sbSql.append(QUERY_SQL).append(getSqlWhere(searchVo));
            sbSql.append(") as countTable ");
            
            Map map = (HashMap) helper.query(sbSql.toString(),
                                             DataTypes.RS_SINGLE_MAP);
            lngCnt = NumberUtil.stringToLong( (String) map.get("cnt"), 0);

        } catch (DefaultException e) {
           System.err.println(e.getMessage());
        }
        return lngCnt;

    }

    public static Dialect getDialect() {

        String dialect = Environment.getProperty("database.dialect");
        try {
            Dialect d = (Dialect) Class.forName(dialect).newInstance();
            return d;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * ��ѯ���е�����
     * @return ��¼������
     */
    public List queryAll() throws DefaultException {
        List aryData = new ArrayList();
        aryData = helper.queryByListVo(this.QUERY_SQL, new LogVO());
        return aryData;

    }

    /**
     * ɾ�����е�����
     * @return ɾ���ɹ����ٱ�,-1Ϊʧ��
     */
    public int delBySelect(String[] aryPk) throws DefaultException {
        int intRet = 0;
        try{
            helper.begin();
            for (int idx = 0; idx < aryPk.length; idx++) {
                LogVO vo = new LogVO();
                vo.setId(aryPk[idx]);
                intRet+= helper.delete(vo);
            }
            helper.commit();
        } catch (DefaultException e) {
            helper.rollback();
            intRet = -1;
        } finally {
            helper.end();
        }
        return intRet;

    }

    /**
     * ɾ������������
     * @param searchVo ��ѯʱ�õ�VO����
     * @return ɾ���ɹ����ٱ�,-1Ϊʧ��
     */
    public int delByCond(VOInterface searchVo) throws DefaultException {
        int intRet = -1;

        String strsql = "select id  from T_SYSTEM_LOG where 1=1 " +
            this.getSqlWhere(searchVo) + " order by id ";
        LogVO vo = new LogVO();
        //String delSql = "delete from T_SYSTEM_LOG WHERE ID<=";
        String delSql = "delete from T_SYSTEM_LOG WHERE ID<=";
        String delSql2 = "";
        int iMaxRecord = 500;
        long count = 0;

        try {
            helper.begin();
            int idx = 0;
            String strID = "0";
            int iSqlNumber = 0;

            List aryData = (ArrayList) helper.query(strsql, vo,
                DataTypes.RS_LIST_VO);
            for (int j = 0; j < aryData.size(); j++) {
                ++idx;
                vo = new LogVO();
                vo = (LogVO) aryData.get(j);
                strID =  vo.getId();
                //�������������500��,��ִ��sql���
                if (idx == iMaxRecord) {
                    delSql2 = delSql + strID + this.getSqlWhere(searchVo);
                    idx = 0;
                    ++iSqlNumber;
                    intRet += helper.execUpdate(delSql2);
                }
            }
            delSql2 = delSql + strID + this.getSqlWhere(searchVo);

            intRet += helper.execUpdate(delSql2);
            helper.commit();
        } catch (DefaultException e) {
            count = -1;
            e.printStackTrace();
            helper.rollback();
            throw new DefaultException (e);

        } finally {
            helper.end();

        }
        return intRet;
    }


    /**
     * ��֯sql���
     * @param searchVo ��ѯ��vo����
     * @return �������
     */
    protected String getSqlWhere(VOInterface searchVo) {
        StringBuffer sbWhere = new StringBuffer();

        LogVO vo = (LogVO) searchVo;
        //��ʼ����,�����
        if (isNull(vo.getBeginDate()) && (isNull(vo.getEndDate()) == false)) {
            sbWhere.append(" AND otime<=").append("'");
            sbWhere.append(vo.getEndDate()).append("'");

        } else if (isNull(vo.getEndDate()) && (isNull(vo.getBeginDate()) == false)) {
            sbWhere.append(" AND otime>=").append("'");
            sbWhere.append(vo.getBeginDate()).append("'");

        } else if (isNull(vo.getEndDate()) == false && (isNull(vo.getBeginDate()) == false)) {
            sbWhere.append(" AND otime<=").append("'");
            sbWhere.append(vo.getEndDate()).append("'");
            sbWhere.append(" AND otime>=").append("'");
            sbWhere.append(vo.getBeginDate()).append("'");

        } else {
            sbWhere.append("");
        }

        //������
        if (!isNull(vo.getUsername())) {
            sbWhere.append(" AND username like '%");
            sbWhere.append(this.replaceText(vo.getUsername()));
            sbWhere.append("%'");
        }

        //ģ��
        if (!isNull(vo.getModule())) {
            sbWhere.append(" AND module like '%");
            sbWhere.append(replaceText(vo.getModule()));
            sbWhere.append("%'");
        }

        //�鿴����
        if (!isNull(vo.getType(), "0")) {
            sbWhere.append(" AND type = ");
            sbWhere.append(vo.getType());
        }
        //����
        if (!isNull(vo.getMemo())) {
            sbWhere.append(" AND memo like '%");
            sbWhere.append(replaceText(vo.getMemo()));
            sbWhere.append("%'");
        }

        //����
        if (!isNull(vo.getAction())) {
            sbWhere.append(" AND action like '%");
            sbWhere.append(replaceText(vo.getAction()));
            sbWhere.append("%'");
        }
        log.console(this.getClass(),"getSqlWhere",sbWhere.toString());
        return sbWhere.toString();

    }
    /**
        * �ַ����滻����
        * Wengnb Add 2003-09-09
        * @param strSource String:�ַ���
        * @param strFrom   String:Դ�ִ�
        * @param strTo     String:�滻���ִ�
        * @return          String:�����滻����ִ����
        */
       public static String replace(String strSource, String strFrom, String strTo) {
           if (strSource == null)
               return "";

           String strDest = "";
           int intFromLen = strFrom.length();
           int intPos = 0;

           while ( (intPos = strSource.indexOf(strFrom)) != -1) {
               strDest = strDest + strSource.substring(0, intPos);
               strDest = strDest + strTo;
               strSource = strSource.substring(intPos + intFromLen);
           }
           strDest = strDest + strSource;
           return strDest;
       }

       /**
        * Wengnb Add 2003-09-09
        * ��"'"�滻��Ϊ"''"
        * @param strSource String:�ַ���
        * @return          String:�����滻����ִ����
        */
       public static String replaceText(String strSource) {
           return replace(strSource, "'", "''");
       }

    private boolean isNull(String value) {
        boolean bRet = false;
        if (null == value || "".equals(value))
            bRet = true;
        return bRet;
    }

    private boolean isNull(String value, String defaultValue) {
        boolean bRet = false;
        if (null == value || "".equals(value) || defaultValue.equals(value))
            bRet = true;
        return bRet;
    }

}
