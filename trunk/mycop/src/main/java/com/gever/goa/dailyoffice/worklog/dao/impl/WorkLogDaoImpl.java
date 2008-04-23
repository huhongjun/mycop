package com.gever.goa.dailyoffice.worklog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.worklog.dao.WorkLogDao;
import com.gever.goa.dailyoffice.worklog.vo.WorkLogContentVO;
import com.gever.goa.dailyoffice.worklog.vo.WorkLogVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.DateTimeUtils;
import com.gever.util.IdMng;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 工作日志Dao接口实现类</p>
 * <p>Description: 工作日志Dao接口实现类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class WorkLogDaoImpl extends BaseDAO implements WorkLogDao {
private static String QUERY_SQL =
        "SELECT SERIAL_NUM,CREATE_TIME,DEPT_CODE,USER_CODE,FILL_DATE,LOG_CONTENT from DO_WORK_LOG";
private static String  SELECT_WORKLOG1= "SELECT * FROM DO_WORK_LOG WHERE USER_CODE = ? AND FILL_DATE = ? ";
private static String  SELECT_WORKLOG2=  "SELECT SERIAL_NUM,CREATE_TIME,DEPT_CODE,USER_CODE,FILL_DATE,LOG_CONTENT FROM DO_WORK_LOG WHERE 1=1";
private static String SELECT_WORKLOGNUM = "SELECT work_log_num FROM SYS_PARAMETER  ";
private static String SELECT_WORKLOG_CONTENT = "SELECT LOG_CONTENT FROM DO_WORK_LOG ";
private static String SELECT_LOG_CONTENT = "SELECT LOG_CONTENT FROM DO_LOG_CONTENT ";
private static String SELECT_WORKLOG_SERIALNUM = "SELECT SERIAL_NUM FROM DO_WORK_LOG ";
private static String SELECT_LOGCONTENT_REMARK =  "SELECT REMARK FROM DO_LOG_CONTENT ";
private static String UPDATE_LOGCONTENT = "update DO_LOG_CONTENT set ";

//QUERY_SQL--定义翻页时用的查询语句
    public WorkLogDaoImpl(String dbData) {
        super(dbData);
    }

    public String getPageSql() {
        return QUERY_SQL;
    }

    /**
     * 新增--重载BaseDAO中的insert方法
     * 为了能够处理获取logID主键值
     * @param vo 当前vo对象
     * @return 新增多少笔,-1为失败
     * @throws DefaultException
     */
    public int insert(VOInterface vo) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData); ;
        if (isIdMng == true) {
            String pkName = vo.getPkFields();
            pkName = toPkFld(pkName);
            vo.setValue(pkName, IdMng.getModuleID(this.userID));
            log.console(this.getClass(),"Insert(VOInterface):PK","Name:" + pkName+",Value:" + IdMng.getModuleID(this.userID));
        }

        if (checkInsert(vo) != 1) {
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
     * 用来判断当前人是否已经填写了当天的日志
     * @param userid 当前用户id
     * @param curdate 当前日期
     * @return true-已经填写,false-没有填写
     * @throws DefaultException
     */

    public boolean findCurPsnCurDayWorkLogIsExist(String userid, String curdate) throws DefaultException {
        boolean isExistFlag = false;
        SQLHelper helper = new DefaultSQLHelper(dbData);
        helper.begin();
        String[] arySql = new String[2];
        arySql[0] = SELECT_WORKLOG1;
        arySql[1] = "String,date";
        List values = new ArrayList();
        values.add(userid);
        values.add(curdate);
        List aryData = (ArrayList) helper.query(arySql, values, DataTypes.RS_LIST_MAP);
        helper.end();
        if (aryData.size() > 0) {
            isExistFlag = true;
        } else {
            isExistFlag = false;
        }
        return isExistFlag;
    }

    /**
     * 用来获取系统表中定义的每页显示的日志数
     * @return 每页显示的日志数目
     * @throws DefaultException
     */

    public int findLogNumOfEveryPage() throws DefaultException {
        int numberOfPage = 0;
        SQLHelper sqlHelper = new DefaultSQLHelper(this.dbData);
        StringBuffer sbSql = new StringBuffer();
        sbSql.append(SELECT_WORKLOGNUM);
        HashMap map = (HashMap) sqlHelper.query(sbSql.toString(), DataTypes.RS_SINGLE_MAP);
         if (map.size() > 0) {
            String workLogNum = (String) map.get("work_log_num");
             if (StringUtils.isNull(workLogNum) == false) { //如果获取的值不未空
                numberOfPage = Integer.parseInt(workLogNum);
            } else {
                numberOfPage = 7; //默认取7
            }

        } else {
            numberOfPage = 7;
        }
        return numberOfPage;
    }

    /**
     * 用来判断查看的日志是否是当天的日志
     * @param curUser 当前登陆用户id
     * @param userid 查看的日志的创建人
     * @param filldate 该日志填写日期
     * @return 1-当天的日志,0-不是当天的日志
     * @throws DefaultException
     */

    public String isCurUserTodayWorkLog(String curUser, String userid, String filldate, String curDate) throws
        DefaultException {
        String isCurUserTodayWorkLogFlag = "0";
        if (curUser.equals(userid) && filldate.equals(curDate)) {
            isCurUserTodayWorkLogFlag = "1";
        } else {
            isCurUserTodayWorkLogFlag = "0";
        }
        return isCurUserTodayWorkLogFlag;
    }

    /**
     * 用来获取当前日期的报表中填写的工作日志
     * @param userid 当前登陆用户id
     * @param curdate 当天的日期
     * @return 用逗号隔开的日志信息
     * @throws DefaultException
     */

    public VOInterface getCurDayWorkLog(String userid, String curdate) throws Exception {
        WorkLogVO worklogVO = new WorkLogVO();
        //SQLHelper helper = new DefaultSQLHelper(dbData);
        //首先要判断如果当前人没有填写了当天的日志的话---无
        //其次获取当前人当天填写的工作日志内容---content---报表类型
        //再次获取该content中的第一列的前五行信息
        //最后，返回用逗号隔开的字符串
        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
            dbData);
        Connection conn = cp.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = SELECT_WORKLOG2+ " AND USER_CODE = " + userid + " AND FILL_DATE = " +DateTimeUtils.toDate(curdate)  + " ";
        try {
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                worklogVO.setSerial_num(rs.getString("serial_num"));
                worklogVO.setLog_content(rs.getBytes("log_content"));
            }
        } catch (Exception e) {
            System.out.println("getCurDayWorkLog error::" + e.getMessage());
        }
        finally {
            rs.close();
            pstmt.close();
            conn.close();
        }
        return worklogVO;
    }

    /**
     * 通过当前用户ID和当前日期获取日志报表内容
     * @return 每页显示的日志数目
     * @throws DefaultException
     */

    public byte[] getWorkLogContentByUserIdAndCurDate(String userid, String curdate) throws DefaultException {
        byte[] worklogcontent;
        SQLHelper helper = new DefaultSQLHelper(dbData);
        String[] arySql = new String[2];
        arySql[0] = SELECT_WORKLOG_CONTENT + " WHERE USER_CODE = ? AND FILL_DATE = ? ";
        arySql[1] = "String,date";
        List values = new ArrayList();
        values.add(userid);
        values.add(curdate);
        List aryData = (ArrayList) helper.query(arySql, values, DataTypes.RS_LIST_MAP);
        worklogcontent = (byte[]) aryData.get(0);
        return worklogcontent;
    }

    /**
     * 用来获取当前人在当前日期填写的日志对应的serial_number
     * @param userid 当前登陆用户id
     * @param curdate 当天的日期
     * @return serial_number
     * @throws Exception
     */

    public String getSerialNumByUseridAndCurdate(String userid, String curdate) throws Exception {
        String serial_number;
        SQLHelper helper = new DefaultSQLHelper(dbData);
        String[] arySql = new String[2];
        arySql[0] = SELECT_WORKLOG_SERIALNUM+ "  WHERE USER_CODE = ? AND FILL_DATE = ? ";
        arySql[1] = "String,date";
        List values = new ArrayList();
        values.add(userid);
        values.add(curdate);
        Map aryData = (HashMap) helper.query(arySql, values, DataTypes.RS_SINGLE_MAP);
        serial_number = (String) aryData.get("serial_num");
        return serial_number;
    }

    /**
     * 用来在新增一条日志时向备份表中加入相应的记录
     * @param logTotalStr 该日志对应的字符串
     * @param logGrjb 个人进步
     * @param logID 填写的日志serial_num
     * @param userID 日志填写人-为了产生唯一的content serial number
     * @return 成功与否
     * @throws SQLException
     */
    public boolean insertLogsIntoBakTable(String logTotalStr, String logGrjb, String logID, String userID) throws
        DefaultException {
        StringBuffer strBuf = new StringBuffer();
        SQLHelper helper = new DefaultSQLHelper(super.dbData);
        if (StringUtils.isNull(logTotalStr)) { //如果为空，直接返回
            return true;
        }
        try {
            WorkLogContentVO vo = new WorkLogContentVO();
            vo.setSerial_num(IdMng.getModuleID(userID));
            vo.setTserial_num(logID);
            vo.setLog_content(logTotalStr);
            vo.setRemark(logGrjb);
            helper.setAutoID(false);
            //这里需要调用方法自动创建一个Serial_num
            helper.insert(vo);
            vo = null; //回收拉圾
        } catch (DefaultException de) {

        }
        return true;
    }

    /**
     * 通过工作日志logID更改当前工作日志内容表中的内容
     * @param logID 工作日志id
     * @param log_content 工作日志对应的内容
     * @param logGrjb 个人进步
     * @throws DefaultException
     */

    public int updateLogContent(String logID, String log_content, String logGrjb) throws
        DefaultException {
        String sql =UPDATE_LOGCONTENT+" LOG_CONTENT='" + log_content +
            "' , REMARK='" + logGrjb + "' where TSERIAL_NUM='" + logID + "'";
        SQLHelper helper = new DefaultSQLHelper(dbData);
        return helper.execUpdate(sql);
    }
    /**
     * 删除LogContent表里,TSERIAL_NUM为logid的记录
     * @param logID String TSERIAL_NUM值
     * @throws DefaultException
     * @return int
     */
    public int deleteLogContent(String logID) throws DefaultException{
      String delsql = "DELETE FROM LOG_CONTENT WHERE TSERIAL_NUM='"+ logID +"'";
      SQLHelper helper = new DefaultSQLHelper(dbData);
      return helper.execUpdate(delsql);
    }
    /**
     * 用来获取当前人在当前日期填写的日志对应的日志内容的字符串
     * @param userid 当前登陆用户id
     * @param curdate 当天的日期
     * @return logTotalStr
     * @throws Exception
     */

    public String getLogTotalStrByUseridAndCurdate(String userid, String curdate) throws Exception {
        String logTotalStr;
        String tserial_num = this.getSerialNumByUseridAndCurdate(userid, curdate);
        //通过当前人和日期获取日志主键，作为查找DO_LOG_CONTENT的外键
        //如果为空的话说明还没有填写日志，则直接返回
        if (StringUtils.isNull(tserial_num)) {
            return "";
        } else {
            SQLHelper helper = new DefaultSQLHelper(dbData);
            String[] arySql = new String[2];
            arySql[0] = SELECT_LOG_CONTENT + " WHERE TSERIAL_NUM = ? ";
            arySql[1] = "String";
            List values = new ArrayList();
            values.add(tserial_num.trim());
            Map aryData = (HashMap) helper.query(arySql, values, DataTypes.RS_SINGLE_MAP);
            logTotalStr = (String) aryData.get("log_content");
        }
        return logTotalStr;
    }

    /**
     * 用来获取当前人在当前日期填写的日志对应的个人进步的字符串
     * @param userid 当前登陆用户id
     * @param curdate 当天的日期
     * @return logTotalStr
     * @throws Exception
     */

    public String getLogGrjbByUseridAndCurdate(String userid, String curdate) throws Exception {
        String logGrjb;
        String tserial_num = this.getSerialNumByUseridAndCurdate(userid, curdate);
        //通过当前人和日期获取日志主键，作为查找DO_LOG_CONTENT的外键
        //如果为空的话说明还没有填写日志，则直接返回
        if (StringUtils.isNull(tserial_num)) {
            return "";
        } else {
            SQLHelper helper = new DefaultSQLHelper(dbData);
            String[] arySql = new String[2];
            arySql[0] =SELECT_LOGCONTENT_REMARK+" WHERE TSERIAL_NUM = ? ";
            arySql[1] = "String";
            List values = new ArrayList();
            values.add(tserial_num.trim());
            Map aryData = (HashMap) helper.query(arySql, values, DataTypes.RS_SINGLE_MAP);
            logGrjb = (String) aryData.get("remark");
        }
        return logGrjb;
    }

    /**
     * 用来获取当前人在当前日期填写的日志对应的日志内容的字符串和个人进步的字符串
     * @param userid 当前登陆用户id
     * @param curdate 当天的日期
     * @return Map --包含日志字符串列表和个人进步
     * @throws Exception
     */

    public Map getLogStrAndGrjbByUseridAndCurdate(String userid, String curdate) throws Exception {
        HashMap logMap = new HashMap();
        List logList = new ArrayList();
        String logGrjb;
        logList = this.getLogListByUseridAndCurdate(userid,curdate);
        logGrjb = this.getLogGrjbByUseridAndCurdate(userid,curdate);
        logMap.put("logList",logList);
        logMap.put("logGrjb",logGrjb);
        return logMap;
    }

    /**
     * 用来获取当前人在当前日期填写的日志对应的日志内容的字符串列表
     * @param userid 当前登陆用户id
     * @param curdate 当天的日期
     * @return logTotalStr
     * @throws Exception
     */

    public List getLogListByUseridAndCurdate(String userid, String curdate) throws Exception {
        String logTotalStr;
        logTotalStr = this.getLogTotalStrByUseridAndCurdate(userid, curdate);
        ArrayList logList = new ArrayList();
        java.util.StringTokenizer stk = new StringTokenizer(logTotalStr, "||");
        while (stk.hasMoreTokens()) {
            logList.add(stk.nextToken());
        }
        return logList;
    }
    /**
      * 翁乃彬新增
      * @todo 因为oracle的不同,所以先删除,后新增
      * @param vo 当前vo对象
      * @return 修改的记录数
      * @throws DefaultException
      */
      public int update(VOInterface vo) throws DefaultException {
          //如果不是oracle
          if (!super.isOracle()){
              return super.update(vo);
          }

          //如果是oracle的时候处理
          String delsql = "DELETE FROM DO_LOG_CONTENT WHERE TSERIAL_NUM='"+ vo.getValue("serial_num") +"'";
          SQLHelper helper = new DefaultSQLHelper(dbData); ;
          int iRet = -1;
          try {
              helper.begin();
              helper.execUpdate(delsql);
              helper.delete(vo);
              helper.setAutoID(false);
              iRet =  helper.insert(vo);
              helper.commit();
          } catch (DefaultException e) {
              helper.rollback();
              throw new DefaultException(e);
          } finally {
              helper.end();
          }
          return iRet;

      }

}
