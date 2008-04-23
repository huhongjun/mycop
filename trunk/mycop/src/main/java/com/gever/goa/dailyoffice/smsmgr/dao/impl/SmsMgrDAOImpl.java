package com.gever.goa.dailyoffice.smsmgr.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.smsmgr.dao.SmsMgrDAO;
import com.gever.goa.dailyoffice.smsmgr.vo.OutBoxVO;
import com.gever.goa.dailyoffice.smsmgr.vo.SmsUserVO;
import com.gever.goa.dailyoffice.tools.vo.CardcaseTypeVO;
import com.gever.goa.dailyoffice.tools.vo.CardcaseVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.dao.UserDepartmentDAO;
import com.gever.sysman.organization.model.impl.DefaultUserDepartment;
import com.gever.util.NumberUtil;
import com.gever.util.StringUtils;

/**
 * <p>Title:短信管理 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SmsMgrDAOImpl extends BaseDAO implements SmsMgrDAO{

    //删除发件箱的sql
    private  static String DEL_OUTBOX_SQL = "DELETE FROM OutBox WHERE 1=1 ";
    //删除已发送的短信的sql
    private  static String DEL_SENDEDOUTBOX_SQL = "DELETE FROM SENDEDOUTBOX WHERE 1=1 ";
    //删除错误的短信sql
    private  static String DEL_BADOUTBOX_SQL = "DELETE FROM BadOutBox WHERE 1=1 ";
    //删除定时发送的sql
    private  static String DEL_TIMINGOUTBOX_SQL = "DELETE FROM TimingOutBox WHERE 1=1 ";

    private  static String QUERY_SQL="SELECT ID,EXPRESSLEVEL,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE,TYPE FROM (";

    private static String QUERY_OUTBOX_A_SQL="SELECT COUNT(id) as cnt  FROM( Select ID,SN from OUTBOX WHERE 1=1 ";

    private static String QUERY_TIMINGOUTBOX_A_SQL="UNION ALL SELECT  ID,SN from TIMINGOUTBOX WHERE 1=1 ";

    private static String QUERY_SENDEDOUTBOX_A_SQL="UNION ALL SELECT  ID,SN from SENDEDOUTBOX WHERE 1=1 ";

    private static String QUERY_OUTBOX_B_SQL="select COUNT(B.SN) as CNT from ( Select SN from OUTBOX WHERE 1=1 ";
    private static String QUERY_TIMINGOUTBOX_B_SQL="UNION ALL SELECT  SN from TIMINGOUTBOX WHERE 1=1 ";
    private static String QUERY_SENDEDOUTBOX_B_SQL="UNION ALL SELECT  SN from SENDEDOUTBOX WHERE 1=1 ";
    private static String QUERY_USER_SQL="Select a.id,a.name,b.mobile from T_USER a "
    				+", HR_ARCHIVE b where a.code = b.employee_code and length(b.mobile)>5";

    private  static String SMS_ALL = "0"; // 所有的类型
    private  static String SMS_SENDOUTBOX = "1"; // 已发送
    private  static String SMS_OUTBOX = "2"; // 正在发送
    private  static String SMS_BADDOUTBOX = "3"; // 发送失败
    private  static String SMS_TIMINGOUTBOX = "4"; // 待发送的

    private  static StringBuffer ADD_OUTBOX_SQL = new StringBuffer(10);
    private  static StringBuffer ADD_TIMING_SQL = new StringBuffer(10);

    private  static StringBuffer  QRY_SEND_SQL = new StringBuffer(10);
    private  static StringBuffer QRY_OUT_SQL = new StringBuffer(10);
    private  static StringBuffer QRY_BADOUT_SQL = new StringBuffer(10);
    private  static StringBuffer QRY_TIMING_SQL = new StringBuffer(10);
    private  static StringBuffer QRY_CARD_SQL = new StringBuffer(10);
    private  static StringBuffer QRY_CARDTYPE_SQL = new StringBuffer(10);


    static {
        ADD_OUTBOX_SQL.append(" INSERT INTO OutBox ");
        ADD_OUTBOX_SQL.append("(SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE)");
        ADD_OUTBOX_SQL.append(" VALUES(?,?,?,?,?,?,?)");

        ADD_TIMING_SQL.append("INSERT INTO TimingOutBox");
        ADD_TIMING_SQL.append("(SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE,SENDINGDATE)");
        ADD_TIMING_SQL.append(" VALUES(?,?,?,?,?,?,?,?)");

        QRY_SEND_SQL.append(" SELECT ID,EXPRESSLEVEL,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE,'1' as TYPE ");
        QRY_SEND_SQL.append("FROM SENDEDOUTBOX ");

        QRY_OUT_SQL.append("SELECT ID,EXPRESSLEVEL,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE,'2' as TYPE ");
        QRY_OUT_SQL.append("FROM OUTBOX ");

        QRY_BADOUT_SQL.append("SELECT ID,EXPRESSLEVEL,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE,'3' as TYPE ");
        QRY_BADOUT_SQL.append("FROM BadOutBox ");

        QRY_TIMING_SQL.append("SELECT ID,EXPRESSLEVEL,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE,'4' as TYPE ");
        QRY_TIMING_SQL.append("FROM TimingOutBox ");

		QRY_CARD_SQL.append(" Select a.serial_num,a.mobile,a.customer,c.type_name ,c.user_code,c.type_code,c.name as user_name");
		QRY_CARD_SQL.append(" from DO_CARDCASE a left join ");
		QRY_CARD_SQL.append("      (select b.user_code,b.type_name,b.type_code,t_user.name from DO_CARDCASE_type b ");
		QRY_CARD_SQL.append("       left join t_user on t_user.id =b.user_code )  c ");
		QRY_CARD_SQL.append(" on a.type_code = c.type_code where 1=1 and length(a.mobile)>0 and a.type_code='-1' ");
		QRY_CARD_SQL.append(" union ");	   
       QRY_CARD_SQL.append(" Select a.serial_num,a.mobile,a.customer,c.type_name ,c.user_code,c.type_code,c.name as user_name");
       QRY_CARD_SQL.append(" from DO_CARDCASE a left join ");
       QRY_CARD_SQL.append("      (select b.user_code,b.type_name,b.type_code,t_user.name from DO_CARDCASE_type b ");
       QRY_CARD_SQL.append("       left join t_user on t_user.id =b.user_code )  c ");
       QRY_CARD_SQL.append(" on a.type_code = c.type_code where 1=1 and length(a.mobile)>0 ");
	   
	   QRY_CARDTYPE_SQL.append("select type_code,type_name,remark from do_cardcase_type where type_code='-1' ");
	   QRY_CARDTYPE_SQL.append(" union ");
       QRY_CARDTYPE_SQL.append( " select type_code,type_name,remark from do_cardcase_type where 1=1 ");
    }
    public void setOracleSQL(){
        QRY_CARD_SQL.setLength(0);
		QRY_CARD_SQL.append("       Select a.serial_num,a.mobile,a.customer,c.type_name ,c.user_code,c.type_code,c.name as user_name ");
		QRY_CARD_SQL.append("    from DO_CARDCASE a ,       (select b.user_code,b.type_name,b.type_code,t_user.name ");
		QRY_CARD_SQL.append("    from DO_CARDCASE_type b        , t_user where b.user_code =t_user.id(+) )  c  ");
		QRY_CARD_SQL.append("    where  a.type_code = c.type_code(+)  and length(a.mobile)>0 and a.type_code='-1' ");
		QRY_CARD_SQL.append(" union ");
        QRY_CARD_SQL.append("       Select a.serial_num,a.mobile,a.customer,c.type_name ,c.user_code,c.type_code,c.name as user_name ");
        QRY_CARD_SQL.append("    from DO_CARDCASE a ,       (select b.user_code,b.type_name,b.type_code,t_user.name ");
        QRY_CARD_SQL.append("    from DO_CARDCASE_type b        , t_user where b.user_code =t_user.id(+) )  c  ");
        QRY_CARD_SQL.append("    where  a.type_code = c.type_code(+)  and length(a.mobile)>0  ");

        ADD_OUTBOX_SQL.setLength(0);
        ADD_OUTBOX_SQL.append(" INSERT INTO OutBox ");
        ADD_OUTBOX_SQL.append(
            "(ID,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE)");
        ADD_OUTBOX_SQL.append(" VALUES(SMSSEQ.NEXTVAL,?,?,?,?,?,?,?)");

        ADD_TIMING_SQL.setLength(0);
        ADD_TIMING_SQL.append("INSERT INTO TimingOutBox");
        ADD_TIMING_SQL.append(
            "(ID,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE,SENDINGDATE)");
        ADD_TIMING_SQL.append(" VALUES(SMSSEQ.NEXTVAL,?,?,?,?,?,?,?,?)");

    }
   // private  static QRY_SQL
    public SmsMgrDAOImpl() {
    }
    public String getPageSql(){
        StringBuffer sbSql = new StringBuffer(20);
        sbSql.append(this.QUERY_SQL);
        sbSql.append(this.QRY_SEND_SQL);
        sbSql.append("UNION ALL ");
        sbSql.append(this.QRY_OUT_SQL);
        sbSql.append("UNION ALL ");
        sbSql.append(this.QRY_BADOUT_SQL);
        sbSql.append("UNION ALL ");
        sbSql.append(this.QRY_TIMING_SQL);
        sbSql.append(" )  ALLTABLE  WHERE 1=1  ");
        return sbSql.toString();

    }

    /**
     * 按主键查询
     * @param id 主键id
     * @param type 表类型
     * @return vo对象
     * @throws DefaultException
     */
    public OutBoxVO queryByPk(String id, String type)throws DefaultException{
        OutBoxVO vo = new OutBoxVO();
        SQLHelper helper =  new DefaultSQLHelper(dbData);
        StringBuffer sbSql= new StringBuffer(10);
        sbSql.append(this.getPageSql()).append(" and id=").append(id);
        sbSql.append(" and type ='").append(type).append("'");
        vo =(OutBoxVO) helper.query(sbSql.toString(),vo,DataTypes.RS_SINGLE_VO);
        return vo;
    }
    public SmsMgrDAOImpl(String dbData) {
        super(dbData);
    }


    /**
     * 删除已选的资料
     * @param idValues 主键数组
     * @param types 类型数组
     * @return 删除成功多少笔,-1为失败
     * @throws DefaultException
     */
    public int delBySelect(String[] idValues, String[] types) throws DefaultException{

        int intRet = -1;
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;

        StringBuffer sbSql = new StringBuffer(20);
        StringBuffer sbWhere1 = new StringBuffer();
        StringBuffer sbWhere2 = new StringBuffer();
        StringBuffer sbWhere3 = new StringBuffer();
        StringBuffer sbWhere4 = new StringBuffer();
        SQLHelper helper =  new DefaultSQLHelper(dbData);

        try{
            helper.begin();
            for (int idx = 0; idx < idValues.length; idx++) {
                if (this.SMS_SENDOUTBOX.equals(types[idx])) {
                    if (num1 == 0) {
                        sbWhere1.append(" and (0=1 ");
                    }
                    ++num1;
                    sbWhere1.append(" or ");
                    sbWhere1.append("id=").append(idValues[idx]);
                } else if (SMS_OUTBOX.equals(types[idx])) {
                    if (num2 == 0) {
                        sbWhere2.append(" and (0=1 ");
                    }
                    ++num2;
                    sbWhere2.append(" or ");
                    sbWhere2.append(" id=").append(idValues[idx]);
                } else if (this.SMS_BADDOUTBOX.equals(types[idx])) {
                    if (num3 == 0) {
                        sbWhere3.append(" and (0=1 ");
                    }
                    ++num3;
                    sbWhere3.append(" or ");
                    sbWhere3.append("id=").append(idValues[idx]);
                } else if (this.SMS_TIMINGOUTBOX.equals(types[idx])) {
                    if (num4 == 0) {
                        sbWhere4.append(" and (0=1 ");
                    }
                    ++num4;
                    sbWhere4.append(" or ");
                    sbWhere4.append("id=").append(idValues[idx]);
                }
            }

            if (num1 > 0) {
                sbWhere1.append(")");
                sbSql.append(DEL_SENDEDOUTBOX_SQL).append(sbWhere1.toString());
                intRet += helper.execUpdate(sbSql.toString());
            }

            if (num2 > 0) {
                sbSql.setLength(0);
                sbWhere2.append(")");
                sbSql.append(DEL_OUTBOX_SQL).append(sbWhere2.toString());
                intRet += helper.execUpdate(sbSql.toString());
            }

            if (num3 > 0) {
                sbSql.setLength(0);
                sbWhere3.append(")");
                sbSql.append(DEL_BADOUTBOX_SQL).append(sbWhere3);
                intRet += helper.execUpdate(sbSql.toString());

            }

            if (num4 > 0) {
                sbSql.setLength(0);
                sbWhere4.append(")");
                sbSql.append(DEL_TIMINGOUTBOX_SQL).append(sbWhere4.toString());
                intRet += helper.execUpdate(sbSql.toString());
            }

            helper.commit();
        } catch (DefaultException e ){
            helper.rollback();
            throw new DefaultException("删除失败");
        } finally{
            helper.end();
        }
        return intRet;

    }

    /**
    * 删除已选的资料
    * @param strWhere sql语句
    * @return 删除成功多少笔,-1为失败
    * @throws DefaultException
    */
    public int delAll(String strWhere)throws DefaultException{

        int intRet = -1;
        StringBuffer sbSql = new StringBuffer(20);
        strWhere = StringUtils.nullToString(strWhere);
        SQLHelper helper =  new DefaultSQLHelper(dbData);

        try{
            helper.begin();
            sbSql.append(DEL_OUTBOX_SQL).append(strWhere);
            helper.execUpdate(sbSql.toString());

            sbSql.setLength(0);
            sbSql.append(DEL_SENDEDOUTBOX_SQL).append(strWhere);
            helper.execUpdate(sbSql.toString());

            sbSql.setLength(0);
            sbSql.append(DEL_BADOUTBOX_SQL).append(strWhere);
            helper.execUpdate(sbSql.toString());

            sbSql.setLength(0);
            sbSql.append(DEL_TIMINGOUTBOX_SQL).append(strWhere);
            helper.execUpdate(sbSql.toString());

            helper.commit();
        } catch (DefaultException e ){
            helper.rollback();
            throw new DefaultException("删除失败");
        } finally{
            helper.end();
        }
        return intRet;
    }

    /**
     * 新增短信表
     * @param msgList 消息列表
     * @return  新增成功多少笔
     * @throws DefaultException
     */
    public int insert(List msgList) throws DefaultException {

       int intRet = -1;

       SQLHelper helper =  new DefaultSQLHelper(dbData);

       String columns = "sn,username,mbno,msg,senddate,sendtime,smstype";
       OutBoxVO vo = new OutBoxVO();

       try{
           helper.begin();

           for (int idx = 0 ; idx < msgList.size(); idx++){
               vo = new OutBoxVO();
               vo = (OutBoxVO) msgList.get(idx);
               intRet += helper.execUpdate(ADD_OUTBOX_SQL.toString(),vo,columns);
           }

           helper.commit();
       } catch (DefaultException e ){
           helper.rollback();
           throw new DefaultException("新增失败");
       } finally{
           helper.end();
       }

       return intRet;
   }

   /**
    * 新增短信表
    * @param msgList 消息列表
    * @return  新增成功多少笔
    * @throws DefaultException
    */
   public int insertTiming(List msgList) throws DefaultException {

       int intRet = -1;
       SQLHelper helper = new DefaultSQLHelper(dbData);
       String columns ="sn,username,mbno,msg,senddate,sendtime,smstype,sendingdate";
       OutBoxVO vo = new OutBoxVO();

       try {
           helper.begin();
           for (int idx = 0; idx < msgList.size(); idx++) {
               vo = new OutBoxVO();
               vo = (OutBoxVO) msgList.get(idx);
               intRet +=
                   helper.execUpdate(ADD_TIMING_SQL.toString(), vo, columns);
           }
           helper.commit();
       } catch (DefaultException e) {
           helper.rollback();
           throw new DefaultException("新增失败");
       } finally {
           helper.end();
       }
       return intRet;
   }



   /**
    * 按userid,查出当前所有的名片信息
    * @param userId 用户号
    * @return 名片列表
    * @throws DefaultException
    */
   public List queryCardByUserID(String userId) throws DefaultException{

       SQLHelper helper = new DefaultSQLHelper(super.dbData);
       CardcaseVO vo = new CardcaseVO();
       String sql = QRY_CARD_SQL.toString() + " AND c.user_code=" + userId;
       List aryData = new ArrayList();

       aryData = (ArrayList) helper.query(sql, vo,
                                          DataTypes.RS_LIST_VO);
       return aryData;

   }

   /**
    * @param userId 用户号
    * 按userid,查出当前所有的名片信息
    * @return 名片夹列表
    * @throws DefaultException
    */
   public List queryCardTypeByUserID(String userId) throws DefaultException {
       SQLHelper helper = new DefaultSQLHelper(dbData);
       CardcaseTypeVO typevo = new CardcaseTypeVO();
       String sql;
       sql = this.QRY_CARDTYPE_SQL.toString() + " AND  user_code=" +userId;
       return (List) helper.query(sql, typevo, DataTypes.RS_LIST_VO);

   }

   /**
    * 得到当前用户的发送的相关资料
    * @param strUserId 当前用户
    * @param strWhere 条件语句
    * @return 相关统计信息
    * @throws DefaultException
    */
   public Map getCurUserSendNumber(String strUserId, String strWhere) throws
       DefaultException {

       StringBuffer strBuf = new StringBuffer();
       Map retMap = new HashMap();
       SQLHelper helper = new DefaultSQLHelper(dbData);
       try {
           Map map = new HashMap();
           helper.begin();

           //得到总共条数
           strBuf.append(this.QUERY_OUTBOX_A_SQL).append(" and SN='");
           strBuf.append(strUserId).append("' ");
           strBuf.append(this.QUERY_TIMINGOUTBOX_A_SQL).append(" and SN='");
            strBuf.append(strUserId).append("' ");
           strBuf.append(this.QUERY_SENDEDOUTBOX_A_SQL).append(" and SN='");
           strBuf.append(strUserId).append("' ");
           strBuf.append(") A ");

           map = (HashMap) helper.query(strBuf.toString(),DataTypes.RS_SINGLE_MAP);
           long sumCnt =NumberUtil.stringToLong((String)map.get("cnt"),0);
           retMap.put("SUMCNT", String.valueOf(sumCnt));

           //得到当前天数总共条数
           strBuf.setLength(0);
           strBuf.append(this.QUERY_OUTBOX_B_SQL).append(" and SN='");
           strBuf.append(strUserId).append("' ").append(strWhere);
           strBuf.append(this.QUERY_TIMINGOUTBOX_B_SQL).append(" and SN='");
           strBuf.append(strUserId).append("' ").append(strWhere);
           strBuf.append(this.QUERY_SENDEDOUTBOX_B_SQL).append(" and SN='");
           strBuf.append(strUserId).append("' ").append(strWhere);
           strBuf.append(") B  ");

           map = new HashMap();
           map = (HashMap) helper.query(strBuf.toString(),DataTypes.RS_SINGLE_MAP);
           long whereCnt =NumberUtil.stringToLong((String)map.get("cnt"),0);
           retMap.put("WHERECNT", String.valueOf(whereCnt));

           helper.commit();
       } catch (DefaultException e) {
           helper.rollback();
          // e.printStackTrace();
           throw new DefaultException(e);
       } finally {
           helper.end();
       }
       return retMap;
   }

   /**
    * 查询人员
    * @return 人员列表
    * @throws DefaultException
    */
   public List querySmsUsers() throws DefaultException {
       SQLHelper helper = new DefaultSQLHelper(dbData);
       SmsUserVO vo = new SmsUserVO();
       UserDepartmentDAO deptDao = OrganizationFactory.getInstance().
           getUserDepartmentDAO();

       List aryDepts = (ArrayList) deptDao.findUserDepartments();
       List aryData = helper.queryByListVo(this.QUERY_USER_SQL, vo);

       for (int idx = 0; idx < aryData.size(); idx++) {
           int curID = -1;
           vo = new SmsUserVO();
           vo = (SmsUserVO) aryData.get(idx);
           curID = NumberUtil.stringToInt(vo.getId(), -1);
           StringBuffer sb = new StringBuffer();
           log.showLog("-------------1------qry----------");
           for (int row = 0; row < aryDepts.size(); row++) {

               DefaultUserDepartment udVO = new DefaultUserDepartment();
               udVO = (DefaultUserDepartment) aryDepts.get(row);
                log.showLog("-----------qry-----1-----" + udVO.getUser());
                log.showLog("-----------qry------2----" + udVO.getDepartmentName());
               if (curID == udVO.getUser()) {
                   sb.append(udVO.getDepartment()).append(",");
               }
              // System.out.println("-----sb----" +sb.toString());
           }

           vo.setDepts(sb.toString());
           aryData.set(idx, vo);
       }
       return aryData;

   }

}
