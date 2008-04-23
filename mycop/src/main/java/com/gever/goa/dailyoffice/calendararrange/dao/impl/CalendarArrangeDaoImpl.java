package com.gever.goa.dailyoffice.calendararrange.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.calendararrange.dao.CalendarArrangeDao;
import com.gever.goa.dailyoffice.calendararrange.vo.CalendarArrangeVO;
import com.gever.goa.dailyoffice.calendararrange.vo.CalendarDeptTreeVO;
import com.gever.goa.dailyoffice.calendararrange.vo.CalendarRightVO;
import com.gever.goa.dailyoffice.calendararrange.vo.CalendarUserVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.jdbc.connection.ConnectionUtil;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.sysman.organization.model.Department;
import com.gever.util.StringUtils;

/**
 * <p>Title: 天行办公自动化平台</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class CalendarArrangeDaoImpl
    extends BaseDAO
    implements CalendarArrangeDao {
  private String pageSql;
  private static String SELECT_CALENDAR_ALL =
      "SELECT SERIAL_NUM,CALENDAR,DO_CALENDAR_ARRANGE.USER_CODE AS USER_CODE,BEGIN_TIME,END_TIME,TASK_TYPE,TASK_SUM,TASK_CONTENT,REMIND_FLAG,AWOKE_TIME,DO_CALENDAR_ARRANGE.ARRANGE AS ARRANGE,B.NAME AS USER_NAME,C.NAME AS ARRANGENAME FROM DO_CALENDAR_ARRANGE" +
      " LEFT OUTER JOIN T_USER AS B ON DO_CALENDAR_ARRANGE.USER_CODE=B.ID " +
      " LEFT OUTER JOIN T_USER as C ON DO_CALENDAR_ARRANGE.ARRANGE=cast(c.id as char(20)) " +
      " WHERE 1=1 ";
  private static String TREE_SQL =
      "Select id as nodeid,name as nodename,case " +
      "when (Select COUNT(*) AS CNT from T_DEPARTMENT b  WHERE " +
      "T_DEPARTMENT.id<>b.id AND b.parent_id=T_DEPARTMENT.id  ) >0 " +
      "then '1' else '0' end as isfolder from T_DEPARTMENT where  1=1 ";

  private static String SELECT_ARRANGE_USER =
      "SELECT DO_ARRANGER.USER_CODE AS USER_CODE,B.NAME AS NAME,ARRANGE AS MEMBER, (CASE WHEN ARRANGE<>'' THEN 1 END) AS RIGTHTYPE " +
      " FROM DO_ARRANGER " +
      " LEFT OUTER JOIN T_USER AS B ON DO_ARRANGER.USER_CODE= B.ID " +
      " WHERE 1=1 ";
  private static String SELECT_VIEW_USER =
      "SELECT DO_VIEW_RIGHT.USER_CODE AS USER_CODE,B.NAME AS NAME,MEMBER, (CASE WHEN MEMBER<>'' THEN 0 END) AS  RIGTHTYPE " +
      " FROM DO_VIEW_RIGHT " +
      " LEFT OUTER JOIN T_USER AS B ON DO_VIEW_RIGHT.USER_CODE= B.ID " +
      " WHERE 1=1";

 public CalendarArrangeDaoImpl(String dbData) {
    super(dbData);
  }

  public String getPageSql() {
    return SELECT_CALENDAR_ALL;
  }

  public void setOracleSQL() {
    SELECT_CALENDAR_ALL =
        "SELECT SERIAL_NUM,CALENDAR,DO_CALENDAR_ARRANGE.USER_CODE AS USER_CODE,BEGIN_TIME,END_TIME,TASK_TYPE,TASK_SUM,TASK_CONTENT,REMIND_FLAG,AWOKE_TIME,DO_CALENDAR_ARRANGE.ARRANGE AS ARRANGE,B.NAME AS USER_NAME,C.NAME AS ARRANGENAME FROM DO_CALENDAR_ARRANGE,T_USER B,T_USER C" +
        " WHERE 1=1 " +
        " AND DO_CALENDAR_ARRANGE.USER_CODE=B.ID(+) " +
        " AND CAST(DO_CALENDAR_ARRANGE.ARRANGE AS INTEGER)=c.id(+) ";

    SELECT_ARRANGE_USER =
        "SELECT DO_ARRANGER.USER_CODE AS USER_CODE,B.NAME AS NAME,ARRANGE AS MEMBER, (CASE WHEN ARRANGE IS NOT NULL THEN 1 END) AS RIGTHTYPE " +
        " FROM DO_ARRANGER,T_USER B " +
        " WHERE 1=1 " +
        " AND DO_ARRANGER.USER_CODE= B.ID(+) ";

    SELECT_VIEW_USER =
        "SELECT DO_VIEW_RIGHT.USER_CODE AS USER_CODE,B.NAME AS NAME,MEMBER, (CASE WHEN MEMBER IS NOT NULL THEN 0 END) AS  RIGTHTYPE " +
        " FROM DO_VIEW_RIGHT,T_USER B " +
        " WHERE 1=1" +
        " AND DO_VIEW_RIGHT.USER_CODE= B.ID(+) ";
  }

  /**
   * 返回通过日期来查询日程的SQL语句
   * @param date Date
   * @param userid String
   * @return String
   */
  private String getSqlOfDate(java.util.Date date, String userid) {
    if (("").equals(userid)) {
      userid = super.userID;
    }String pagesql ="";
    System.out.println("************************super.isOracle()****************" + super.isOracle());
    if (super.isOracle()){
         pagesql = SELECT_CALENDAR_ALL +
                " AND DO_CALENDAR_ARRANGE.USER_CODE=" + userid;
        java.sql.Date sqlTodayDate = new java.sql.Date(date.getTime());
        pagesql = pagesql + " AND CALENDAR = to_date('" + sqlTodayDate + "','yyyy-mm-dd')"
          + " order by begin_time asc " ;
    } else {
        pagesql = SELECT_CALENDAR_ALL +
               " AND DO_CALENDAR_ARRANGE.USER_CODE=" + userid;
       java.sql.Date sqlTodayDate = new java.sql.Date(date.getTime());
        pagesql = pagesql + " AND CALENDAR = '" + sqlTodayDate + "'"
            + " order by begin_time asc ";

    }
    return pagesql;
  }

  /**
   * 返回MONTH每天的所有日程
   * @param year int
   * @param month int
   * @param userid String
   * @throws DefaultException
   * @return List
   */
  public List queryAllInMonth(int year, int month, String userid) throws
      DefaultException {
    SQLHelper sqlHelper = new DefaultSQLHelper(super.dbData);
    List list = new ArrayList();
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month, 1);
    int maxdays = calendar.getActualMaximum(calendar.DATE);
    try {
      sqlHelper.begin();
      for (int i = 0; i < maxdays; i++) {
        CalendarArrangeVO calendarArrangeVO = new CalendarArrangeVO();
        List ary = new ArrayList();
        ary = (ArrayList) sqlHelper.query(getSqlOfDate(calendar.getTime(),
            userid),
                                          calendarArrangeVO,
                                          DataTypes.RS_LIST_VO);
        calendar.add(Calendar.DATE, 1);
        list.add(ary);
      }
      sqlHelper.commit();
    }
    catch (DefaultException e) {
      sqlHelper.rollback();
    }
    finally {
      sqlHelper.end();
    }
    return list;
  }

  /**
   * 返回week每天的日程
   * @param year int
   * @param month int
   * @param week int
   * @param userid String
   * @throws DefaultException
   * @return List
   */
  public List queryAllInWeek(int year, int month, int week, String userid) throws
      DefaultException {
    SQLHelper sqlHelper = new DefaultSQLHelper(super.dbData);
    List list = new ArrayList();
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month);
    calendar.set(Calendar.WEEK_OF_MONTH, week);
    calendar.set(Calendar.DAY_OF_WEEK, 2);
    try {
      sqlHelper.begin();
      for (int i = 0; i < 7; i++) {
        CalendarArrangeVO calendarArrangeVO = new CalendarArrangeVO();
        List ary = (ArrayList) sqlHelper.query(getSqlOfDate(calendar.getTime(),
            userid),
                                               calendarArrangeVO,
                                               DataTypes.RS_LIST_VO);
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        list.add(ary);
      }
      sqlHelper.commit();
    }
    catch (DefaultException e) {
      e.printStackTrace();
      sqlHelper.rollback();
    }
    finally {
      sqlHelper.end();
    }
    return list;
  }

  public List getTreeData(String nodeid) throws DefaultException {
    List treeData = new ArrayList();
    String strWhere = "";
    if ("0".equals(nodeid) || StringUtils.isNull(nodeid)) { //判断0--根结点
      strWhere = " and parent_id = 0 "; //获取第一级的目录节点
    }
    else {
      strWhere = " and parent_id =" + nodeid; //否则获取拥有父节点的那些节点
    }
    CalendarDeptTreeVO vo = new CalendarDeptTreeVO();
    SQLHelper helper = helper = new DefaultSQLHelper(dbData); ;
    treeData = (ArrayList) helper.queryByListVo(TREE_SQL + strWhere, vo);
    return treeData;
  }

  /**
   * 取得用户的所有权限人员列表
   * @param userid String
   * @throws DefaultException
   * @return List
   */
  public List getCalendarRightUsers(String userid) throws
      DefaultException {
    List list = new ArrayList();
    List list2 = new ArrayList();
    ConnectionProvider cp = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      String sql =
          SELECT_ARRANGE_USER + " AND (ARRANGE LIKE '" + userid +
          ",%' OR ARRANGE LIKE '%," + userid + ",%' OR ARRANGE LIKE '%," +
          userid + "' OR ARRANGE='" + userid + "' )" +
          " UNION ALL " +
          SELECT_VIEW_USER + " AND  (MEMBER LIKE '" +
          userid + ",%' OR MEMBER LIKE '%," + userid + ",%' OR MEMBER LIKE '%," +
          userid + "' OR MEMBER='" + userid + "')";
      cp = ConnectionProviderFactory.getConnectionProvider(this.dbData);
      conn = cp.getConnection();
      log.showLog("calendar dao sql= " + sql);
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        String usercode = rs.getString("USER_CODE");
        String memeber = rs.getString("MEMBER");
        String user_name = rs.getString("NAME");
        String righttype = rs.getString("RIGTHTYPE");
        //如果已经存在与列表中,删除重复的人员.
       
        if (("1").equals(righttype) && list2.contains(usercode)) {
          list.remove(list2.indexOf(usercode));
          list2.remove(list2.indexOf(usercode));
          CalendarUserVO cuv = new CalendarUserVO();
          cuv.setUserCode(usercode);
          cuv.setUserName(user_name);
          cuv.setRightType(righttype);
          list.add(cuv);
          list2.add(usercode);
        }
        //如果没有出现在列表中的则加入list
        else if (!list2.contains(usercode)) {
          CalendarUserVO cuv = new CalendarUserVO();
          cuv.setUserCode(usercode);
          cuv.setUserName(user_name);
          cuv.setRightType(righttype);
          list.add(cuv);
          list2.add(usercode);
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new DefaultException(e);
    }
    finally {
      try {
        ConnectionUtil.close(conn, pstmt, rs);
      }
      catch (Exception e) {
        throw new DefaultException(e);
      }
    }
    return list;
  }

  /**
   * 通过用户取得相应权限
   * @param userid String
   * @param righttype String
   * @throws DefaultException
   * @return CalendarRightVO
   */
  public CalendarRightVO getCalendarRightByUser(String userid, String righttype) throws
      DefaultException {
    String sqlview =
        "SELECT USER_CODE,MEMBER,'0' AS RIGHTTYPE  FROM DO_VIEW_RIGHT WHERE USER_CODE=" +
        userid;
    String sqlarrange =
         "SELECT USER_CODE,ARRANGE AS MEMBER,'1' AS RIGHTTYPE FROM DO_ARRANGER WHERE USER_CODE=" +
        userid;
    SQLHelper sqlHelper = new DefaultSQLHelper(super.dbData);
    CalendarRightVO calendarRightVO = new CalendarRightVO();
    if ("0".equals(righttype)) {
      calendarRightVO = (CalendarRightVO) sqlHelper.queryByVo(sqlview,
          calendarRightVO);
    }
    else if ("1".equals(righttype)) {
      calendarRightVO = (CalendarRightVO) sqlHelper.queryByVo(sqlarrange,
          calendarRightVO);
    }
    return calendarRightVO;
  }

  public List getUsersInDeptByRightType(Department dept, String righttype,
                                        String member) throws
      DefaultException {
    String sqlusers = "";
    List list = new ArrayList();
    ConnectionProvider cp = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      cp = ConnectionProviderFactory.getConnectionProvider(this.dbData);
      conn = cp.getConnection();
      if (!"".equals(member)) {
        member = "," + member;
      }
      sqlusers = "SELECT ID,NAME,(CASE WHEN ID IN (-1" + member +
          ") THEN '" + righttype + "' ELSE '-1' END) AS RIGHTTYPE FROM T_USER WHERE ID IN (SELECT ID FROM T_DEPARTMENT_PERSON WHERE DEPARTMENT_ID=" +
          dept.getId() + " AND ID<>" + super.userID + ") ORDER BY NAME ASC ";

      log.showLog("calendar dao  sql = " + sqlusers );
      pstmt = conn.prepareStatement(sqlusers);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        CalendarUserVO cuv = new CalendarUserVO();
        cuv.setUserCode(rs.getInt("ID") + "");
        cuv.setUserName(rs.getString("NAME"));
        cuv.setRightType((rs.getString("RIGHTTYPE")).trim());
        list.add(cuv);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new DefaultException(e);
    }
    finally {
      try {
        ConnectionUtil.close(conn, pstmt, rs);
      }
      catch (Exception e) {
        throw new DefaultException(e);
      }
    }
    return list;

  }

  /**
   * 把字符串转换成list
   * @param str String
   * @param regex String
   * @throws DefaultException
   * @return List
   */
  private List StringToList(String str, String regex) throws DefaultException {
    List list = new ArrayList();
    try {
      String[] strarr = str.split(regex);
      for (int i = 0; i < strarr.length; i++) {
        list.add(strarr[i]);
      }
    }
    catch (PatternSyntaxException ex) {
      throw new DefaultException(ex);
    }
    return list;
  }

  public List getTodayCalendarArrange(String userid) throws DefaultException {
    if ("".equals(userid)) {
      userid = super.userID;
    }
    Calendar calendar = Calendar.getInstance();
    SQLHelper sqlHelper = new DefaultSQLHelper(super.dbData);
    String sql = this.getSqlOfDate(calendar.getTime(), userid);
    List list = sqlHelper.queryByListVo(sql, new CalendarArrangeVO());
    return list;
  }
}
