package com.gever.goa.dailyoffice.smsmgr.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.message.vo.MessageVO;
import com.gever.goa.dailyoffice.smsmgr.dao.SmsProduct;
import com.gever.goa.dailyoffice.smsmgr.vo.InBoxVO;
import com.gever.goa.dailyoffice.smsmgr.vo.OutBoxVO;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.struts.Constant;
import com.gever.util.DateTimeUtils;
import com.gever.util.IdMng;
import com.gever.util.NumberUtil;
import com.gever.util.StringUtils;
import com.gever.web.util.ActiveUsers;

/**
 * <p>Title:佛山大学手机短信设备 </p>
 * <p>Description:佛山大学手机短信设备 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SmsProductFs implements SmsProduct {
    private static StringBuffer ADD_OUTBOX_SQL = new StringBuffer(10);
    private static StringBuffer QRY_BADOUT_SQL = new StringBuffer(10);
    private static StringBuffer ADD_TIMING_SQL = new StringBuffer(10);

    private static StringBuffer QRY_INBOX_SQL = new StringBuffer(10);
    private static String QRY_INBOX_START_SQL = " SELECT MSG AS cnt FROM INBOX WHERE MBNO='GEVER_InBox_EndID'";
    private static String QRY_INBOX_COUNT_SQL = "SELECT MAX(ID) as cnt from INBOX where 1=1";
    private static String MOD_INBOX_SQL = "update INBOX set msg =cast ((SELECT MAX(ID) as cnt from INBOX ) as char(20)) where  MBNO='GEVER_InBox_EndID'";
    private static String DEL_TIMING_SQL = "DELETE FROM TIMINGOUTBOX WHERE 1=1 ";


    static {
        ADD_OUTBOX_SQL.append(" INSERT INTO OutBox ");
        ADD_OUTBOX_SQL.append("(SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE)");
        ADD_OUTBOX_SQL.append(" VALUES(?,?,?,?,?,?,?)");

        QRY_BADOUT_SQL.append("SELECT ID,EXPRESSLEVEL,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE,'3' as TYPE ");
        QRY_BADOUT_SQL.append("FROM BadOutBox ");

        ADD_TIMING_SQL.append(" INSERT INTO OUTBOX ");
        ADD_TIMING_SQL.append("(SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE) ");
        ADD_TIMING_SQL.append(" SELECT SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE ");
        ADD_TIMING_SQL.append(" FROM TimingOutBox where 1=1 ");

        QRY_INBOX_SQL.append("SELECT a.ID,a.MBNO,a.MSG,a.ARRIVEDATE,a.ARRIVETIME,a.COMMPORT ");
        QRY_INBOX_SQL.append("FROM InBox a ");
        QRY_INBOX_SQL.append("WHERE  MBNO<>'GEVER_InBox_EndID' ");

    }


    public SmsProductFs() {
    }
    public void setOracleSQL(){
        ADD_OUTBOX_SQL.setLength(0);
        ADD_OUTBOX_SQL.append(" INSERT INTO OutBox ");
        ADD_OUTBOX_SQL.append("(ID,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE)");
        ADD_OUTBOX_SQL.append(" VALUES(SMSSEQ.NEXTVAL,?,?,?,?,?,?,?)");

        ADD_TIMING_SQL.setLength(0);
        ADD_TIMING_SQL.append(" INSERT INTO OUTBOX ");
        ADD_TIMING_SQL.append("(ID,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE) ");
        ADD_TIMING_SQL.append(" SELECT SMSSEQ.NEXTVAL,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE ");
        ADD_TIMING_SQL.append(" FROM TimingOutBox where 1=1 ");

    }
    ActiveUsers au = ActiveUsers.getInstance();
    private String dbData = Constant.DATA_SOURCE;
    public SmsProductFs(String dbData) {
        this.dbData = dbData;
    }

    public void setDbData(String dbData) {
        if (au.isOracle())
            setOracleSQL();
        this.dbData = dbData;
    }

    private boolean isClose = false;
    /**
     * 初始化手机通讯连接
     * @return 成功与否
     */

    public boolean initConnection() { //初始化连接
        this.isClose = false;
        return true;
    }

    /**
     *  关闭手机通讯连结
     * @return 成功与否
     */

    public boolean close() {
        this.isClose = true;
        return true;
    }

    /**
     * 得到发送错误的列表
     * @return 错误讯息列表
     * @throws DefaultException
     */
    public List getErrorResult() throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        List aryRetList = new ArrayList();
        OutBoxVO vo = new OutBoxVO();
        aryRetList = (ArrayList) helper.query(QRY_BADOUT_SQL.toString(), vo,
                                              DataTypes.RS_LIST_VO);
        return aryRetList;

    }

    /**
     * 自动检查
     * @return 是否真假
     * @throws DefaultException
     */
    public boolean checkTimingSend() throws DefaultException {
        return true;

    }

    /**
     * 定时发送短信
     * @throws DefaultException
     */
    public void sendingSms() throws DefaultException {
        String strTime =DateTimeUtils.getCurrentDateTime();
        SQLHelper helper = new DefaultSQLHelper(dbData);

        StringBuffer sbSql = new StringBuffer();

        try {
            helper.begin();
            sbSql.append(ADD_TIMING_SQL.toString());
            sbSql.append(" and SENDINGDATE <='");
            sbSql.append(strTime).append("'");
            helper.execUpdate(sbSql.toString());

            sbSql.setLength(0);
            sbSql.append(this.DEL_TIMING_SQL.toString());
            sbSql.append(" and SENDINGDATE<='").append(strTime).append("'");
            helper.execUpdate(sbSql.toString());

            sbSql.setLength(0);
            sbSql.append(" DELETE FROM  TimingOutBox ");
            sbSql.append(" WHERE SENDINGDATE<='").append(strTime).append("'");
            helper.execUpdate(sbSql.toString());

            helper.commit();
        } catch (DefaultException e) {
            helper.rollback();
        } finally{
            helper.end();

        }

    }
    /**
     * 发送短信
     * @param msgList 短信列表
     * @return 信息
     * @throws DefaultException
     */
    public String sendSms(List msgList) throws DefaultException {
        return String.valueOf(this.insert(msgList));
    }

    /**
     * 新增短信表
     * @param msgList 消息列表
     * @return  新增成功多少笔
     * @throws DefaultException
     */
    private int insert(List msgList) throws DefaultException {

        int intRet = 0;
        SQLHelper helper = new DefaultSQLHelper(dbData);
        String columns = "expresslevel,sn,username,mbno,msg,senddate,sendtime,smstype,";
        OutBoxVO vo = new OutBoxVO();

        try {
            helper.begin();
            for (int idx = 0; idx < msgList.size(); idx++) {
                vo = new OutBoxVO();
                vo = (OutBoxVO) msgList.get(idx);
                intRet +=
                    helper.execUpdate(ADD_OUTBOX_SQL.toString(), vo, columns);
            }

            helper.commit();
        } catch (DefaultException e) {
            intRet =-1;
            helper.rollback();
            throw new DefaultException("新增失败");
        } finally {
            helper.end();
        }
        return intRet;
    }

    public boolean checkInceptSms() {
        return true;
    }

    /**
     * 接收短消息
     * @return 接收短信的列表
     * @throws DefaultException
     */
    public boolean inceptSms() throws DefaultException {
       // System.out.println("- START -dbData---"+ dbData);
        SQLHelper helper = new DefaultSQLHelper(dbData);
        boolean bRet = false;

        try{
            helper.begin();
            //先得到
            Map map = (HashMap) helper.query(QRY_INBOX_START_SQL,
                                             DataTypes.RS_SINGLE_MAP);
            long lngStart = NumberUtil.stringToLong( (String) map.get("cnt"), -1);

            //容错处理
            if (lngStart == -1) {
                helper.rollback();
                helper.end();
                return true;
            }

            //查询新短信资料
            String strWhere = " and ID > " + lngStart;
            InBoxVO vo = new InBoxVO();
            List aryData = new ArrayList();
            String sql = this.QRY_INBOX_SQL + strWhere;
            StringBuffer sbSql = new StringBuffer();
            aryData =(ArrayList) helper.queryByListVo(sql,vo);

            //没有新短信
            if (aryData.size() <=0){
                helper.rollback();
                helper.end();
                return true;
            }

            //更新最大的id号
            helper.execUpdate(MOD_INBOX_SQL);

            //将数据插入到内部短消息当中
            String strMsg = "";
            int pos = -1;
            boolean isFind = false;
            String userId = "";
            int curId = 0;

            for (int idx = 0 ; idx < aryData.size(); idx++){
                isFind = false;
                vo = new InBoxVO();
                vo = (InBoxVO) aryData.get(idx);
                strMsg = vo.getMsg();
                isFind = false;
                pos = strMsg.indexOf(':');
                if (pos > 0){
                    sbSql.setLength(0);
                    userId = strMsg.substring(0, pos);
                    strMsg = strMsg.substring(pos + 1);

                    if (!StringUtils.isNull(userId)){
                        sbSql.append("SELECT id FROM T_USER ");
                        sbSql.append("WHERE  USERNAME='");
                        sbSql.append(userId).append("'");
                        Map cMap = (HashMap) helper.query(sbSql.toString(),
                            DataTypes.RS_SINGLE_MAP);
                        curId = NumberUtil.stringToInt( (String) cMap.get("id"), 0);
                        isFind = true;
                    }
                }

                //插入到内部短消息当中
                MessageVO msgVO = new MessageVO();
                msgVO.setRead_flag("0");
                msgVO.setRead_time(DateTimeUtils.getCurrentDateTime());

                msgVO.setReceiver((isFind == true) ? String.valueOf(curId) : "");
                msgVO.setUser_code(vo.getMbno());
                msgVO.setContent(strMsg);
                msgVO.setMsg_type("2");

                msgVO.setSend_time(DateTimeUtils.getCurrentDateTime());
                msgVO.setSerial_num(IdMng.getModuleID(String.valueOf(idx)));
                helper.setAutoID(false);
                helper.insert(msgVO);

            }

            helper.commit();
        } catch (DefaultException e){
            helper.rollback();
            e.printStackTrace(System.out);
            throw new DefaultException("接收短信息失败!");
        } finally {
            helper.end();
        }
        return bRet;
    }



}
