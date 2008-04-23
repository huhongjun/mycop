package com.gever.goa.dailyoffice.message.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.message.dao.MessageDao;
import com.gever.goa.dailyoffice.message.vo.MessageVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.DateTimeUtils;
import com.gever.util.IdMng;
import com.gever.vo.VOInterface;

/**
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MessageDaoImpl
    extends BaseDAO
    implements MessageDao {
  private static String QUERY_SQL =
      "SELECT SERIAL_NUM,b.name as user_name ,USER_CODE,receiver,SEND_TIME,CONTENT,READ_FLAG,READ_TIME,WEB_URL,MSG_TYPE,MEMO,mserial_num, model_id from DO_INNER_MSG a left join t_user b on a.user_code = cast(b.id as char(20)) where read_flag!=2  ";

  //QUERY_SQL--定义翻页时用的查询语句
  private static String TREE_SQL =
      " SELECT id as nodeid,name as nodename,'1' as isfolder from DO_TEAM_LOG_TYPE ";

  private String DELETE_SQL = "DELETE FROM do_inner_msg WHERE ";

  private String UPDATE_SQL = "update DO_INNER_MSG  set read_time=" +
      DateTimeUtils.toDateTime(DateTimeUtils.getCurrentDateTime()) +
      ",read_flag=1 where SERIAL_NUM='";
  private String UPDA_SQL = "update do_inner_msg set";
  private String SELECT_SQL = "SELECT * FROM DO_INNER_MSG WHERE";
  public void setOracleSQL() {
//   QUERY_SQL =
//      "SELECT SERIAL_NUM,b.name as user_name ,USER_CODE,receiver,SEND_TIME,CONTENT," +
//      " READ_FLAG,READ_TIME,WEB_URL,MSG_TYPE,MEMO,mserial_num, model_id from " +
//      " DO_INNER_MSG a ,t_user b where cast(a.user_code as integer)=b.id(+)  and read_flag!=2 ";
    QUERY_SQL =
        "SELECT SERIAL_NUM,b.name as user_name ,USER_CODE,receiver,SEND_TIME,CONTENT," +
        " READ_FLAG,READ_TIME,WEB_URL,MSG_TYPE,MEMO,mserial_num, model_id from " +
        " DO_INNER_MSG a ,(select cast(id as varchar2(20)) as id, name from t_user)b where a.user_code=b.id(+)  and read_flag!=2 ";

  }

  public MessageDaoImpl(String dbData) {
    super(dbData);
  }

  public MessageDaoImpl() {
    super("goa");
  }

  /**
   * 新增
   * @param vo 当前vo对象
   * @return 新增多少笔,-1为失败
   * @throws DefaultException
   */

  public int insert(VOInterface vo) throws DefaultException {
    SQLHelper helper = new DefaultSQLHelper(dbData);
    helper.setAutoID(false);
    MessageVO messageVO = (MessageVO) vo;
    messageVO.setSend_time(new Date().toLocaleString());
    messageVO.setRead_flag("0");
    messageVO.setMsg_type("1");
    messageVO.setUser_code(super.userID);
    String receivername = vo.getValue("receiver"); //得到receiver的值
    String[] receivernames = splitReceiver(receivername); //调用splitReceiver方法
    int len = receivernames.length;
    int i = 0;

    for (i = 0; i < len; i++) {
      if (!receivernames[i].trim().equals("")) {
        messageVO.setReceiver(receivernames[i]);
        messageVO.setSerial_num(IdMng.getModuleID(super.userID, i));
        helper.insert(vo);
      }
    }
    return i;
  }

  /**
   * 新增
   * @param vo 当前vo对象
   * @return 新增多少笔,-1为失败
   * @throws DefaultException
   */



  //解析receivername
  public String[] splitReceiver(String receivername) throws DefaultException {
    if (receivername == null || "".equals(receivername))
      return null;
    String[] allReceiver = receivername.trim().split(",");
    return allReceiver;

  }

  //公共接口
  public int sendMessage(String user_code, String send_time, String content,
                         String receiver, String web_url, String memo) throws
      Exception {
    //解析receiver
    if (receiver == null || "".equals(receiver))
      return -1;
    MessageVO vo = new MessageVO();
    vo.setUser_code(user_code);
    vo.setSend_time(send_time);
    vo.setContent(content);
    vo.setReceiver(receiver);
    vo.setWeb_url(web_url);
    vo.setRead_flag("0");
    vo.setMsg_type("1");
    vo.setMemo(memo);

    return this.insertVO(vo);

  }

  //公共接口
  public int sendMessage(String user_code, String send_time, String content,
                         String receiver, String web_url) throws Exception {
    //解析receiver
    log.showLog("====测试=="+receiver);
    if (receiver == null || "".equals(receiver))
      return -1;
    MessageVO vo = new MessageVO();
    vo.setUser_code(user_code);
    vo.setSend_time(send_time);
    vo.setContent(content);
    vo.setReceiver(receiver);
    vo.setWeb_url(web_url);
    vo.setRead_flag("0");
    vo.setMsg_type("1");

    return this.insertVO(vo);

  }

  //公共接口
  public int sendPhoneMessage(String user_code, String send_time,
                              String content,
                              String receiver, String web_url) throws Exception {
    //解析receiver
    if (receiver == null || "".equals(receiver))
      return -1;
    MessageVO vo = new MessageVO();
    vo.setUser_code(user_code);
    vo.setSend_time(send_time);
    vo.setContent(content);
    vo.setReceiver(receiver);
    vo.setWeb_url(web_url);
    vo.setRead_flag("0");
    vo.setMsg_type("2");
    return this.insertVO(vo);

  }

  //为备忘录和日程安排的公共接口
  public int sendMessageInfo(String user_code, String send_time, String content,
                             String receiver, String web_url, String model_id,
                             String mserial_num) throws Exception {
    //解析receiver
    if (receiver == null || "".equals(receiver))
      return -1;
    MessageVO vo = new MessageVO();
    vo.setUser_code(user_code);
    vo.setSend_time(send_time);
    vo.setContent(content);
    vo.setReceiver(receiver);
    vo.setWeb_url(web_url);
    vo.setModel_id(model_id);
    vo.setMserial_num(mserial_num);
    vo.setRead_flag("2");
    vo.setMsg_type("1");
    return this.insertVO(vo);

  }

//为备忘录和日程安排的公共接口
  public int sendMessageInfo(String user_code, String send_time, String content,
                             String receiver, String web_url, String model_id,
                             String mserial_num, String memo) throws Exception {
    //解析receiver
    if (receiver == null || "".equals(receiver))
      return -1;
    MessageVO vo = new MessageVO();
    vo.setUser_code(user_code);
    vo.setSend_time(send_time);
    vo.setContent(content);
    vo.setReceiver(receiver);
    vo.setWeb_url(web_url);
    vo.setModel_id(model_id);
    vo.setMserial_num(mserial_num);
    vo.setRead_flag("2");
    vo.setMsg_type("1");
    vo.setMemo(memo);
    return this.insertVO(vo);

  }

//删除短信息公共接口
  public int deleteMessageInfo(String model_id,
                               String mserial_num) throws
      DefaultException {
    SQLHelper sqlHelper = new DefaultSQLHelper(super.dbData);
    int deleterow = 0;

    deleterow = sqlHelper.execUpdate(
        DELETE_SQL + " MSERIAL_NUM='" + mserial_num + "'and model_id=" +
        model_id);
    return deleterow;
  }

  //手机短信调用接口
  public long queryByCount(String where) throws DefaultException {
    super.setSqlWhere(where);
    MessageVO vo = new MessageVO();
    return super.queryByCount(vo);
  }

  //手机短信调用接口
  public List queryByPage(String where, long start, long howMany) throws
      DefaultException {
    super.setSqlWhere(where);
    MessageVO vo = new MessageVO();
    return super.queryByPage(vo, start, howMany);

  }

  public String getPageSql() {
    return QUERY_SQL;
  }

  public int updateReadFlag(VOInterface vo) throws DefaultException {
    SQLHelper helper = new DefaultSQLHelper(dbData);
    MessageVO ismVO = (MessageVO) vo;
    String sql = UPDATE_SQL +
        ismVO.getSerial_num() + "'";
    return helper.execUpdate(sql);
  }

  public int updateMessageInfo(String send_time, String content,
                               String model_id,
                               String mserial_num) throws DefaultException {
    SQLHelper helper = new DefaultSQLHelper(dbData);

    if (send_time != null && !send_time.equals("")) {
      UPDA_SQL += " send_time = " + DateTimeUtils.toDateTime(send_time) + ",";
    }
    if (content != null && !content.equals("")) {
      UPDA_SQL += " content ='" + content + "' ,";
    }
    if (UPDA_SQL.endsWith("set")) {
      return 0; //无更新内容；
    }
    if (UPDA_SQL.endsWith(",")) {
      UPDA_SQL = UPDA_SQL.substring(0, UPDA_SQL.length() - 1);
    }
    UPDA_SQL += " where mserial_num ='" + mserial_num + "' and model_id =" +
        model_id;
    return helper.execUpdate(UPDA_SQL);

  }

  private int insertVO(MessageVO vo) throws DefaultException {
    SQLHelper helper = new DefaultSQLHelper(dbData);
    helper.setAutoID(false);
    MessageVO messageVO = (MessageVO) vo;
    String receivername = vo.getValue("receiver"); //得到receiver的值
    String[] receivernames = splitReceiver(receivername); //调用splitReceiver方法
    int len = receivernames.length;
    int i = 0;
    for (i = 0; i < len; i++) {
      if (!receivernames[i].trim().equals("")) {
        messageVO.setReceiver(receivernames[i]);
        if (super.userID == null || super.userID.equals(""))
          messageVO.setSerial_num(IdMng.getModuleID(messageVO.getUser_code(), i));
        else
          messageVO.setSerial_num(IdMng.getModuleID(super.userID, i));
        helper.insert(vo);
      }
    }
    return i;

  }

  public List toListByUserId(String user_id) throws DefaultException {
    MessageVO messageVO = new MessageVO();
    SQLHelper sqlHelper = new DefaultSQLHelper(super.dbData);
    List list = new ArrayList();
    String sql =
        SELECT_SQL + " receiver= '" + user_id +
        "' AND READ_FLAG=0";
    //select user_code,count(*) from do_inner_msg where receiver='"+user_id+"'
    //and read_flag=0
    list = sqlHelper.queryByListVo(sql, messageVO);
    return list;
  }

  public List toListByUserIds(String user_ids) throws DefaultException {
    MessageVO messageVO = new MessageVO();
    SQLHelper sqlHelper = new DefaultSQLHelper(super.dbData);
    //  user_id = super.userID; //取得当前用户ID
    List list = new ArrayList();
    String sql =
        SELECT_SQL + " receiver in(" + user_ids +
        ") AND READ_FLAG=0";
    list = sqlHelper.queryByListVo(sql, messageVO);
    return list;
  }

  public boolean check() {
    return true;
  }

  public boolean doUpdateMessage() throws
      DefaultException {
    boolean retvalue = true;
    String cur_time = DateTimeUtils.toDateTime(DateTimeUtils.getCurrentDateTime());
    String UPDAT_SQL =
        " UPDATE  DO_INNER_MSG SET READ_FLAG =0  where SEND_TIME<=" + cur_time +
        " and read_flag=2 ";

    SQLHelper helper = new DefaultSQLHelper(dbData);

    try {
      helper.execUpdate(UPDAT_SQL.toString());
    }
    catch (DefaultException e) {
      retvalue = false;
      throw new DefaultException("短消息发送失败！");

    }
    finally {
      return retvalue;
    }

  }



  public static void main(String[] arg) {
    try {
      new MessageDaoImpl("goa").getClass().newInstance();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
