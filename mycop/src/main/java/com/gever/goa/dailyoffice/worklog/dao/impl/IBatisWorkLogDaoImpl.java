package com.gever.goa.dailyoffice.worklog.dao.impl;

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
import com.gever.jdbc.ibatis.SqlMapClientUtil;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.IdMng;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;
import com.ibatis.sqlmap.client.SqlMapClient;


public class IBatisWorkLogDaoImpl extends BaseDAO implements WorkLogDao {
private static String QUERY_SQL =
        "SELECT SERIAL_NUM,CREATE_TIME,DEPT_CODE,USER_CODE,FILL_DATE,LOG_CONTENT from DO_WORK_LOG";
private static String  SELECT_WORKLOG1= "SELECT * FROM DO_WORK_LOG WHERE USER_CODE = ? AND FILL_DATE = ? ";
private static String  SELECT_WORKLOG2=  "SELECT SERIAL_NUM,CREATE_TIME,DEPT_CODE,USER_CODE,FILL_DATE,LOG_CONTENT FROM DO_WORK_LOG WHERE 1=1";
private static String SELECT_WORKLOGNUM = "SELECT work_log_num FROM SYS_PARAMETER  ";
private static String SELECT_WORKLOG_CONTENT = "SELECT LOG_CONTENT FROM DO_WORK_LOG ";
private static String SELECT_WORKLOG_SERIALNUM = "SELECT SERIAL_NUM FROM DO_WORK_LOG ";
private static String SELECT_LOGCONTENT_REMARK =  "SELECT REMARK FROM DO_LOG_CONTENT ";
private static String UPDATE_LOGCONTENT = "update DO_LOG_CONTENT set ";

//QUERY_SQL--���巭ҳʱ�õĲ�ѯ���
    public IBatisWorkLogDaoImpl(String dbData) {
        super(dbData);
    }

    public String getPageSql() {
        return QUERY_SQL;
    }

    /**
     * ����--����BaseDAO�е�insert����
     * Ϊ���ܹ������ȡlogID����ֵ
     * @param vo ��ǰvo����
     * @return �������ٱ�,-1Ϊʧ��
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
     * �����жϵ�ǰ���Ƿ��Ѿ���д�˵������־
     * @param userid ��ǰ�û�id
     * @param curdate ��ǰ����
     * @return true-�Ѿ���д,false-û����д
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
     * ������ȡϵͳ���ж����ÿҳ��ʾ����־��
     * @return ÿҳ��ʾ����־��Ŀ
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
             if (StringUtils.isNull(workLogNum) == false) { //�����ȡ��ֵ��δ��
                numberOfPage = Integer.parseInt(workLogNum);
            } else {
                numberOfPage = 7; //Ĭ��ȡ7
            }

        } else {
            numberOfPage = 7;
        }
        return numberOfPage;
    }

    /**
     * �����жϲ鿴����־�Ƿ��ǵ������־
     * @param curUser ��ǰ��½�û�id
     * @param userid �鿴����־�Ĵ�����
     * @param filldate ����־��д����
     * @return 1-�������־,0-���ǵ������־
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
     * ������ȡ��ǰ���ڵı�������д�Ĺ�����־
     * @param userid ��ǰ��½�û�id
     * @param curdate ���������
     * @return �ö��Ÿ�������־��Ϣ
     * @throws DefaultException
     */

    public VOInterface getCurDayWorkLog(String userid, String curdate) throws Exception {
        WorkLogVO worklogVO = new WorkLogVO();
        worklogVO.setUser_code(userid);
        worklogVO.setFill_date(curdate);
        WorkLogVO retVO = (WorkLogVO)SqlMapClientUtil.getInstance().
        	queryForObject("getCurDayWorkLog",worklogVO);

        return retVO;
    }

    /**
     * ͨ����ǰ�û�ID�͵�ǰ���ڻ�ȡ��־��������
     * @return ÿҳ��ʾ����־��Ŀ
     * @throws DefaultException
     */

    public byte[] getWorkLogContentByUserIdAndCurDate(String userid, String curdate) throws DefaultException {
        byte[] worklogcontent = null;
        WorkLogVO worklogVO = new WorkLogVO();
        worklogVO.setUser_code(userid);
        worklogVO.setFill_date(curdate);
        
        try {
			worklogcontent = (byte[])SqlMapClientUtil.getInstance().
				queryForObject("getWorkLogContentByUserIdAndCurDate",worklogVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return worklogcontent;
    }

    public String getSerialNumByUseridAndCurdate(String userid, String curdate) throws Exception {
        String serial_number="";
        WorkLogVO work = new WorkLogVO();
        work.setUser_code(userid);
        work.setFill_date(curdate);
        
		SqlMapClient sqlMap = SqlMapClientUtil.getInstance();
		serial_number = (String)sqlMap.queryForObject("getSerialNumByUseridAndCurdate", work);
		       log.console(this.getClass(),"getSerialNumByUseridAndCurdate",serial_number);
        return serial_number;
    }

    /**
     * ����������һ����־ʱ�򱸷ݱ��м�����Ӧ�ļ�¼
     * @param logTotalStr ����־��Ӧ���ַ���
     * @param logGrjb ���˽���
     * @param logID ��д����־serial_num
     * @param userID ��־��д��-Ϊ�˲���Ψһ��content serial number
     * @return �ɹ����
     * @throws SQLException
     */
    public boolean insertLogsIntoBakTable(String logTotalStr, String logGrjb, String logID, String userID) throws
        DefaultException {
        StringBuffer strBuf = new StringBuffer();
        SQLHelper helper = new DefaultSQLHelper(super.dbData);
        if (StringUtils.isNull(logTotalStr)) { //���Ϊ�գ�ֱ�ӷ���
            return true;
        }
        try {
            WorkLogContentVO vo = new WorkLogContentVO();
            vo.setSerial_num(IdMng.getModuleID(userID));
            vo.setTserial_num(logID);
            vo.setLog_content(logTotalStr);
            vo.setRemark(logGrjb);
            helper.setAutoID(false);
            //������Ҫ���÷����Զ�����һ��Serial_num
            helper.insert(vo);
            vo = null; //��������
        } catch (DefaultException de) {

        }
        return true;
    }

    public int updateLogContent(String logID, String log_content, String logGrjb) throws
        DefaultException {
    	WorkLogContentVO work = new WorkLogContentVO();
    	work.setLog_content(log_content);
    	work.setRemark(logGrjb);
    	work.setTserial_num(logID);
    	int ret= -1;
		try {
			ret = SqlMapClientUtil.getInstance().update("updateLogContent",work);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return ret;
    }

    public int deleteLogContent(String logID) throws DefaultException{    	
		SqlMapClient sqlMap = SqlMapClientUtil.getInstance();
		int ret=-1;
		try {
			ret = sqlMap.delete("deleteLogContent", logID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ret; 
    }

    public String getLogTotalStrByUseridAndCurdate(String userid, String curdate) throws Exception {
        String logTotalStr="";
        String tserial_num = this.getSerialNumByUseridAndCurdate(userid, curdate);
		SqlMapClient sqlMap = SqlMapClientUtil.getInstance();
		logTotalStr = (String)sqlMap.queryForObject("getLogTotalStrByUseridAndCurdate", tserial_num);
		
        return logTotalStr; 
    }

    public String getLogGrjbByUseridAndCurdate(String userid, String curdate) throws Exception {
        String logGrjb;
        String tserial_num = this.getSerialNumByUseridAndCurdate(userid, curdate);
        //ͨ����ǰ�˺����ڻ�ȡ��־��������Ϊ����DO_LOG_CONTENT�����
        //���Ϊ�յĻ�˵����û����д��־����ֱ�ӷ���
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
     * ������ȡ��ǰ���ڵ�ǰ������д����־��Ӧ����־���ݵ��ַ����͸��˽������ַ���
     * @param userid ��ǰ��½�û�id
     * @param curdate ���������
     * @return Map --������־�ַ����б�͸��˽���
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
     * ������ȡ��ǰ���ڵ�ǰ������д����־��Ӧ����־���ݵ��ַ����б�
     * @param userid ��ǰ��½�û�id
     * @param curdate ���������
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
      * ���˱�����
      * @todo ��Ϊoracle�Ĳ�ͬ,������ɾ��,������
      * @param vo ��ǰvo����
      * @return �޸ĵļ�¼��
      * @throws DefaultException
      */
      public int update(VOInterface vo) throws DefaultException {
          //�������oracle
          if (!super.isOracle()){
              return super.update(vo);
          }

          //�����oracle��ʱ����
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

