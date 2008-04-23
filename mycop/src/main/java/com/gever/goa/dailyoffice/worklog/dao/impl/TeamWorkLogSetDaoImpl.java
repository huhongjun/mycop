package com.gever.goa.dailyoffice.worklog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.worklog.dao.TeamWorkLogSetDao;
import com.gever.goa.dailyoffice.worklog.vo.TeamWorkLogSetVO;
import com.gever.goa.dailyoffice.worklog.vo.TeamWorkLogTreeVO;
import com.gever.goa.dailyoffice.worklog.vo.WorkLogContentVO;
import com.gever.goa.dailyoffice.worklog.vo.WorkLogVO;
import com.gever.goa.util.DepartmentStructure;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.sysman.api.OrganizationUtil;
import com.gever.sysman.organization.model.Department;
import com.gever.util.DateTimeUtils;
import com.gever.util.IdMng;
import com.gever.util.NumberUtil;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;

/**
 * Title: 团队日志Dao接口实现类
 * Description: 团队日志Dao接口实现类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */

public class TeamWorkLogSetDaoImpl extends BaseDAO implements TeamWorkLogSetDao {

    private static String QUERY_SQL =
        "SELECT a.FILL_DATE as fdate FROM  DO_WORK_LOG a ,DO_LOG_CONTENT  b" +
        " where a.SERIAL_NUM = b.TSERIAL_NUM and b.LOG_CONTENT<>'||||||||||' ";
    private static String QRY_MEMS =
        "SELECT team_member FROM DO_TEAM_LOG WHERE USER_CODE =?  AND TEAM_TYPE =?";
    private static String TREE_SQL =
        "SELECT id as nodeid,name as nodename,'1' as isfolder  from DO_TEAM_LOG_TYPE ";
    private static String QRY_LOG =
        "SELECT  a.serial_num, a.FILL_DATE,C.NAME as user_name,b.log_content as log_memos FROM DO_WORK_LOG as a " +
        "  left join  DO_LOG_CONTENT as b on a.serial_num = b.tserial_num " +
        "  left join T_USER AS C ON A.USER_CODE = C.ID   where 1=1 ";
    private static String SELECT_DEPT =
        "SELECT  ID, NAME, PARENT_ID  FROM T_DEPARTMENT WHERE 1=1 ORDER BY ID ";
    private static String SELECT_WORKLOG =
        "SELECT DEPT_CODE FROM DO_WORK_LOG  WHERE 1=1";
    private static String SELECT_TEAMWORKLOG =
        "SELECT TEAM_MEMBER FROM DO_TEAM_LOG  WHERE 1=1";

    private static String COUNT_USER_DEPT_PER_ALL =
        " select distinct a.ID, a.NAME  from T_USER a, T_DEPARTMENT_PERSON b ";
    private static String COUNT_USER_DEPT_PER0 = "select count(distinct T_USER.ID) as Ret from T_USER,T_DEPARTMENT_PERSON WHERE 1=1";
    private static String COUNT_USER_DEPT_PER1 =
        "SELECT Count(T_DEPARTMENT.ID) as Ret FROM T_DEPARTMENT WHERE 1=1";
    private static String COUNT_USER_DEPT_PER2 = "select count(distinct d.ID) as Ret from (select distinct b.DEPARTMENT_ID as deptID from (select distinct ID as deptid from T_DEPARTMENT WHERE 1=1";
    private static String COUNT_USER_DEPT_PER4 = "select count(distinct c.ID) as Ret from (select a.ID,a.NAME from (select T_USER.ID,T_USER.NAME from T_USER,T_DEPARTMENT_PERSON ";
    private static String COUNT_USER_DEPT_PER5 = "select count(distinct g.id) as Ret from (select * from (select distinct d.ID from (select distinct b.DEPARTMENT_ID as deptID from (select distinct ID as deptid from T_DEPARTMENT ";
    private static String DELETE_TEAMLOG =
        "DELETE FROM DO_TEAM_LOG WHERE USER_CODE=? AND TEAM_TYPE=?";
    private static String INSERT_TEAMLOG = "INSERT INTO DO_TEAM_LOG(SERIAL_NUM,USER_CODE,TEAM_TYPE,TEAM_MEMBER) VALUES(?,?,?,?) ";
    private static String QUERY_DEPT="select id,name from t_department where parent_id=0";

    //查询是否是子部门的语句
  private static final String SON_DEPT_SQL = " Select " +
        " (Select COUNT(*) AS CNT from T_DEPARTMENT b  WHERE " +
        " T_DEPARTMENT.id<>b.id AND b.parent_id=T_DEPARTMENT.id  ) " +
        " as issondept from T_DEPARTMENT where  1=1 ";

    private String cacheMems = "-1";
    private String context;
    public void setOracleSQL() {
        QRY_LOG =
            "SELECT  a.serial_num, a.FILL_DATE,c.NAME as user_name,b.log_content as log_memos FROM DO_WORK_LOG a,DO_LOG_CONTENT b,T_USER c " +
            "  where a.serial_num = b.tserial_num(+) " +
            " and a.USER_CODE = c.ID(+)  ";
             }

//QUERY_SQL--定义翻页时用的查询语句
    public TeamWorkLogSetDaoImpl(String dbData) {
        super(dbData);
    }

    /**
     * 翁乃彬(加入)
     * 分页按条件查询
     * @param vo 查询时用的VO对象
     * @param start 开始标记
     * @param howMany 结束标记
     * @return 记录集对象
     * @throws DefaultException
     */
    public List queryByPage(VOInterface vo, long start, long howMany) throws
        DefaultException {

        List listLogs = new ArrayList();
        listLogs = super.queryByPage(vo, start, howMany);
        SQLHelper helper = new DefaultSQLHelper(dbData);
        try {
            helper.begin();
            for (int idx = 0; idx < listLogs.size(); idx++) {
                TeamWorkLogSetVO tvo = new TeamWorkLogSetVO();
                tvo = (TeamWorkLogSetVO) listLogs.get(idx);
                tvo.setListMens(getCurDateLog(tvo.getFdate(), helper));
                tvo.setSerial_num("" + (idx + 1));
                listLogs.set(idx, tvo);
            }
        } catch (DefaultException e) {
            helper.rollback();
            throw e;
        } finally {
            helper.end();
        }
        return listLogs;
    }



    /**
     * 得到当前日期(翁乃彬 加入)
     * @param curDate 当前日期
     * @param helper SqlHelper
     * @return 列表.
     * @throws DefaultException
     */
    private List getCurDateLog(String curDate, SQLHelper helper) throws
        DefaultException {

        StringBuffer sbWhere = new StringBuffer(10);
        sbWhere.append(
            "  and b.log_content<>'||||||||||'  and a.USER_CODE  IN (");
        sbWhere.append(cacheMems);
        sbWhere.append(")  AND ( FILL_DATE=");
        sbWhere.append(DateTimeUtils.toDate(curDate));
        sbWhere.append(")");

        WorkLogVO vo = new WorkLogVO();
        List logList = new ArrayList();
        logList = (ArrayList) helper.query(QRY_LOG + sbWhere.toString(), vo,
                                           DataTypes.RS_LIST_VO);

        for (int idx = 0; idx < logList.size(); idx++) {
            vo = new WorkLogVO();
            vo = (WorkLogVO) logList.get(idx);
            String content = vo.getLog_memos();
            java.util.StringTokenizer stk = new StringTokenizer(content, "||");
            List subList = new ArrayList();
            int next = 0;
            while (stk.hasMoreTokens()) {
                WorkLogContentVO wvo = new WorkLogContentVO();
                wvo.setSerial_num("" + (++next));
                wvo.setLog_content(stk.nextToken());
                subList.add(wvo);
            }
            vo.setSublist(subList);
            logList.set(idx, vo);
        }
        return logList;

    }

    /**
     * 得到当前团队的人员"1,2,3,4"的方法    翁乃彬(加入)
     * @param userID 用户ID
     * @param type 团队类型
     * @return 人员字串
     * @throws DefaultException
     */
    public String getTermMens(String userID, String type) throws
        DefaultException {
        String mems = queryTeamMembers(userID, NumberUtil.stringToInt(type, 0),
                                       new TeamWorkLogSetVO());
        if (StringUtils.isNull(mems)) {
            mems = "-1";
            return mems;
        }
        String lastChar = mems.substring(mems.length() - 1, mems.length());
        if (",".equals(lastChar)) {
            mems = mems.substring(0, mems.length() - 1);
        }
        cacheMems = mems;
        return mems;
    }

    public String getPageSql() {
        return QUERY_SQL;
    }

    public List getTreeData() throws DefaultException {
        List treeData = new ArrayList();
        TeamWorkLogTreeVO vo = new TeamWorkLogTreeVO();
        SQLHelper helper = helper = new DefaultSQLHelper(dbData); ;
        treeData = (ArrayList) helper.queryByListVo(TREE_SQL, vo);
        return treeData;
    }

    /**
     * 查询当前用户在团队日志表中某种团队中的部门列表
     * @param user_code 当前用户ID
     * @param vo 当前vo
     * @param team_type 团队类型
     * @return List 记录集对象
     * @throws DefaultException
     */
    public List queryDeptsByCurUser(VOInterface vo,
                                    int team_type) throws
        DefaultException {
        List deptList = new ArrayList();
        SQLHelper helper = new DefaultSQLHelper(dbData);
        deptList = (ArrayList) helper.query(SELECT_DEPT, vo,
                                            DataTypes.RS_LIST_VO);
        return deptList;
    }

    /**
     * 查询当前用户在团队日志表中某种团队中的所有成员对应的部门列表
     * @param user_code 当前用户ID
     * @param team_type 团队类型
     * @param vo 当前vo
     * @return List 部门列表记录集对象
     * @throws DefaultException
     */
    public List queryDeptsByCurTeamMember(String team_member) throws
        DefaultException {
        String deptsOfTeamsMember = "";
        String member = "";
        ArrayList teamMemberList = new ArrayList();
        ArrayList deptList = new ArrayList();
        StringUtils.separate(teamMemberList, team_member, ",");
        for (int i = 0; i < teamMemberList.size(); i++) {
            member = ( (String) teamMemberList.get(i)).toString();
            deptsOfTeamsMember = this.queryDeptByUserCode(member);
            if (deptsOfTeamsMember == null) {
                deptsOfTeamsMember = StringUtils.nullToString(
                    deptsOfTeamsMember);
            }
            if (!"".equals(deptsOfTeamsMember) && deptsOfTeamsMember != null) {
                deptList.add(deptsOfTeamsMember);
            }
        }
        return deptList;
    }

    /**
     * 用来去除重复字符串的通用方法
     * @param stringList 输入的带有或不带有重复字符串的List列表
     * @return String 不带重复字符串的List列表
     * @throws DefaultException
     */

    private String getChecked(List stringList) {
        ArrayList retList = new ArrayList();
        String strValue = "";
        String strTmp1 = "";
        String strTmp2 = "";
        for (int idx = 0; idx < stringList.size(); idx++) {
            strTmp1 = (String) stringList.get(idx); //取得第一个循环中的值
            for (int idy = idx + 1; idy < stringList.size(); idy++) { //从第二个值比较判断
                strTmp2 = (String) stringList.get(idy);
                //这个地方还没有处理
                //if (!strTmp2.equals(strTmp1)) {
                //retList.add(strTmp1);
                //break;
                //}
            }
        }
        return strValue;
    }

    /**
     * 查询当前用户在工作日志列中的部门（实际上也就是系统管理中的部门，这里是一致的）
     * @param member 团队日志中team_member中拆分出来的单个成员字符串
     * @return String 相对应的部门字符串
     * @throws DefaultException
     */
    public String queryDeptByUserCode(String member) throws
        DefaultException {
        String deptCode = "";
        SQLHelper helper = new DefaultSQLHelper(dbData);
        StringBuffer sbSql = new StringBuffer();
        String curDate = DateTimeUtils.getCurrentDate(); //取得当前日期-与fill_date比较时用
        sbSql.append(SELECT_WORKLOG);
        sbSql.append(" and  user_code=" + Integer.parseInt(member) +
                     " and fill_date=" + DateTimeUtils.toDate(curDate) + " ");
        //从工作日志表中查找出那些当天填写了日志的用户所在的部门ID
        HashMap map = (HashMap) helper.query(sbSql.toString(),
                                             DataTypes.RS_SINGLE_MAP);
        deptCode = (String) map.get("dept_code");
        return deptCode;
    }

    /**
     * 查询当前用户在团队日志表中某种团队中的某个部门的成员列表--team_member已由queryTeamMembers()查出
     * @param team_member 用逗号分割的该部门团队成员
     * @return List 记录集对象
     * @throws DefaultException
     */
    public List queryUsersByCurDept(String team_member) throws
        DefaultException {
        ArrayList deptUserList = new ArrayList();
        StringUtils.separate(deptUserList, team_member, ",");
        return deptUserList;
    }

    /**
     * 查询当前用户在团队日志表中某种团队中的某个部门的成员列表
     * @param user_code 当前用户ID
     * @param team_type 团队类型
     * @param dept_code 部门代码
     * @param vo 当前vo
     * @return List 记录集对象
     * @throws DefaultException
     */
    public String queryTeamMembers(String user_code, int team_type,
                                   VOInterface vo) throws
        DefaultException {
        String teamMembers = "";
        SQLHelper helper = new DefaultSQLHelper(dbData);
        StringBuffer sbSql = new StringBuffer();
        sbSql.append(SELECT_TEAMWORKLOG);
        sbSql.append(" and user_code=" + Integer.parseInt(user_code) +
                     " and team_type=" +
                     team_type + " ");
        TeamWorkLogSetVO teamWorkLogSetVO = new TeamWorkLogSetVO();
        teamWorkLogSetVO = (TeamWorkLogSetVO) helper.query(sbSql.toString(), vo,
            DataTypes.RS_SINGLE_VO);
        teamMembers = teamWorkLogSetVO.getTeam_member();
        //为了能够在列表的查询sql语句中使用IN，这里获取的teamMembers可能还需要截取掉最后一个","号
        return teamMembers;
    }

    /**
     * 构造部门日志或全体日志的html字串(注由于部门级别可能是多级,用不了扩展标签来循环)
     * 故写在这个Command里面,代码有点难看
     * 注:1.如果有传入部门代号,说明为部门日志查询,否则全体日志--系统管理用户权限还没有设定
     * 这里暂时默认为显示所有部门日志，其实是应该与用户权限挂钩的
     *    2.思路:首先将部门代号排序出来,
     *           再根据相对应的部门代号的长度,来缩进表格,
     *           循环时根据它的部门ID,找出相对应下级部门数以及本级部门人数等
     * @param user_code 用户ID
     * @param team_type 团队类型
     * @param vo vo
     * @return 查出的记录列表--转化成的html页面形式
     * @throws DefaultException
     */
    public String queryList(String user_code, int team_type,
                            VOInterface vo) throws
        DefaultException {
        String strRet = new String("");
        String strWhere = new String("");
        String strSql = new String("");
        String strDept = new String("");
        String strDeptName = new String("");
        int lever=0;
        String isShowStyle = " style='display:block'";
        StringBuffer strBuf = new StringBuffer();
        int i = 0;
        int intCols = 6; //一行表格需要排几列
        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
            dbData);
        Connection conn = cp.getConnection();
        PreparedStatement pstmt = null;
        PreparedStatement psDet = null;
        ResultSet rs = null; //存取全部部门结果集
        ResultSet rsDet = null;

        //先列出所有部门列表
        strBuf.append(SELECT_DEPT);
        OrganizationUtil smu = new OrganizationUtil();
        Collection allDeptCol= smu.getDepartmentStructure();
        String userLevel = "";
        try {
            userLevel = smu.getUserLevelByUserID(user_code);
        } catch (Exception e) {
            e.printStackTrace();
        }

        strSql = strBuf.toString();
        String strTmpDept = new String();
        try {
            pstmt = conn.prepareStatement(strBuf.toString());
            rs = pstmt.executeQuery();
            String preDept = "";
            for (Iterator iter = allDeptCol.iterator(); iter.hasNext(); ) {
                DepartmentStructure item = (DepartmentStructure) iter.next();
                Department dept = item.getDepartment();
                strTmpDept = StringUtils.replace(String.valueOf(dept.getId()), "'", "\'");
                strDept = StringUtils.replaceText(String.valueOf(dept.getId()));
                lever=item.getLayerNumber();
                strDeptName = dept.getName();

                    //  --> 本级部门人数
                    int iDeptUserNumber = this.getDeptUserNumber(conn, strDept,
                        strWhere);
                    //  --> 下级部门人数
                    int iNextDeptUserNumber = this.getNextDeptUserNumber(conn,
                        strDept,
                        strWhere);

                    if (iDeptUserNumber > 0 || iNextDeptUserNumber > 0 ) {
                        //如果当前部门拥有用户或者下级部门拥有用户--行内容
                        strRet += "<tr class=listrow1 id=\"trBM" + strTmpDept +
                            "\" pid=\"trBM" + dept.getParentDepartment() + "\" name=\"trBM" + strTmpDept + "\"";
                        strRet += isShowStyle + ">" +
                            "<td class=listcellrow align=left width=\"100%\"><nobr>" +
                            this.getSpaces(lever) +
                            " <a onclick='openItem(\"" + strDept +
                            "\")' style='cursor:hand'>" +
                            "<img src=\""+context+"/images/menu_open.gif\" border=0 id=img"
                            + strDept + "></a>" + strDeptName + "</nobr></td>\n";
                    } else { //不拥有用户时
                        strRet += "<tr class=listrow1 id=\"trBM" + strTmpDept +
                            "\" pid=\"trBM" + dept.getParentDepartment() + "\" name=\"trBM" + strTmpDept + "\"" + isShowStyle +
                            ">" +
                            "<td class=listcellrow align=left width=\"100%\"><nobr>" +
                            StringUtils.replaceLen2(strDept) + strDeptName + "" +
                            "</nobr></td>\n";
                    }

                    if (iDeptUserNumber > 0) { //当本级部门拥有用户时,需要其相关的人员记录-相当于body中内容
                        strRet += "<tr class=listrow2 name=\"trRY" + strTmpDept +
                            "\" id=\"trRY" + strTmpDept +
                            "\" pid=\"trRY" + dept.getParentDepartment() + "\" style='display:block'><td colspan=5 class=listcellrow>\n";
                        strBuf.setLength(0); //先置空stringBuffer
                        //该sql语句查出当前部门下的所有用户ID及其用户中文名
                        strBuf.append(COUNT_USER_DEPT_PER_ALL);
                        strBuf.append(" where a.ID = b.ID and b.DEPARTMENT_ID = " +
                                      Integer.valueOf(strDept) + " ");
                        if (!super.isOracle()) {
                            strBuf.append(" and a.LEVEL >= '" + userLevel + "' ");
                        } else {
                            strBuf.append(" and a.LEV >= '" + userLevel + "' ");
                        }

                        //psDet = conn.prepareStatement(strBuf.toString());

                        //该sql语句查出当前部门下的所有用户ID及其用户中文名
                        psDet = conn.prepareStatement(strBuf.toString());
                        rsDet = psDet.executeQuery();
                        strRet +=
                            "<table border=0 cellspacing=0 cellpadding=0 width='100%'>\n";
                        strRet += "<tr class=listrow2><td width=" +
                            ( (strDept.length() - 2) * 20) + "><div class=f12b>";
                        strRet += StringUtils.replaceLen(strDept) + "</div></td>\n";
                        i = 0;

                        String curTeamMembers = this.queryTeamMembers(user_code,
                            team_type,
                            vo); //查询出当前选中的团队成员
                        List curDeptUserList = this.queryUsersByCurDept(
                            curTeamMembers); //获得用户在当前部门选中的成员列表
                        //   人员输出时需要,每行6列的方式输出,不够就补空表格
                        while (rsDet.next()) {
                            if ( ( (i % intCols) == 0) && i != 0) {
                                strRet += "</tr>\n";
                                strRet += "<tr class=listrow2><td width=" +
                                    ( (strDept.length() - 2) * 20) +
                                    "><div class=f12b>";
                                strRet += StringUtils.replaceLen(strDept) +
                                    "</div></td>\n";
                            }
                            ++i;

                            strRet += "<td nowrap width='" +
                                (100 / intCols) +
                                "%'><nobr><input type=checkbox id=\"UserID" +
                                rsDet.getString("ID") +
                                "\" name=\"UserID\" value='" +
                                rsDet.getString("ID") + "' " +
                                StringUtils.getChecked(curDeptUserList,
                                rsDet.getString("ID")) +
                                ">" + "<font size='2'>" +
                                rsDet.getString("NAME") + "</font>" +
                                "</nobr></td>\n";
                        } while ( (i % intCols) != 0) {
                            strRet += "<td width='" + (100 / intCols) +
                                "%'>&nbsp;</td>\n";
                            ++i;
                        }
                        rsDet.close();
                        strRet += "</TR>\n</table>\n";
                    }
                    strRet += "</TD>\n</TR>\n";
                    preDept = strDept;
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
                psDet.close();
                rs.close();
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception a) {
                a.printStackTrace();
            }

        }
        return strRet;
    }


    /**
     * 得到所有的根节点
     * @return List
     * add by lihaidong
     */
    public List getRootDept() throws DefaultException, SQLException {
        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
            dbData);
        List rootDeptList=new ArrayList();
        Connection conn=cp.getConnection();
        StringBuffer sb=new StringBuffer();
        sb.append(this.QUERY_DEPT);
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(sb.toString());
        for (int idx=0;rs.next() ;idx++ ) {
            rootDeptList.add(idx,rs.getString("id"));
        }
        return rootDeptList;
    }

    /**
     * 得到所有的子部门
     * @param deptId String
     * @return List
     */

    public static void main(String[] args) throws SQLException, DefaultException {
        /*TeamWorkLogSetDao teamWorkLogSetDao =TeamWorkLogSetFactory.getInstance().createTeamWorkLogSet("goa");
        List list=teamWorkLogSetDao.getRootDept();
        for (int i=0;i<list.size() ;i++ ) {
            System.out.println("The dept is:--->"+list.get(i));
        }*/
        OrganizationUtil utils=new OrganizationUtil();
        Collection col=utils.getDepartmentStructure();
        for (Iterator iter = col.iterator(); iter.hasNext(); ) {
            DepartmentStructure item = (DepartmentStructure)iter.next();
            Department dept=item.getDepartment();
            System.out.println("The dept id is:-->"+dept.getId());
            System.out.println("The dept name is:-->"+dept.getName());
            System.out.println("The dept is-->"+item.getLayerNumber());
        }
    }



    /**
     * 构造团队设置后团队日志列表页面的html字串(注由于部门级别可能是多级,用不了扩展标签来循环)
     * 故写在这个Command里面,代码有点难看
     * 注:1.如果有传入部门代号,说明为部门日志查询,否则全体日志--系统管理用户权限还没有设定
     * 这里暂时默认为显示所有部门日志，其实是应该与用户权限挂钩的
     *    2.思路:首先将部门代号排序出来,
     *           再根据相对应的部门代号的长度,来缩进表格,
     *           循环时根据它的部门ID,找出相对应下级部门数以及本级部门人数等
     * @param user_code 用户ID
     * @param team_type 团队类型
     * @param vo vo
     * @return 查出的记录列表--转化成的html页面形式
     * @throws DefaultException
     */
    public String queryTeamWorkLogListHtml(String user_code, int team_type,
                                           VOInterface vo) throws
        DefaultException {
        String tempCurTeamMembers = this.queryTeamMembers(user_code, team_type,
            vo); //查询出当前选中的团队成员
        int teamMembersSize = tempCurTeamMembers.length();
        String curTeamMembers = "";
        if (!StringUtils.isNull(tempCurTeamMembers)) { //不为空时才截取,否则会出错
            curTeamMembers = tempCurTeamMembers.substring(0,
                teamMembersSize - 1);
        } else {
            curTeamMembers = "";
        }

        String strRet = new String("");
        String strWhere = new String("");
        String strSql = new String("");
        String strDept = new String("");
        String strDeptName = new String("");
        String isShowStyle = " style='display:block'";
        String curDate = DateTimeUtils.getCurrentDate(); //取得当前日期--对应创建日期
        StringBuffer strBuf = new StringBuffer();
        int i = 0;
        int intCols = 6; //一行表格需要排几列-这里每行最多只显示六个用户
        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.dbData);
        Connection conn = cp.getConnection();
        PreparedStatement pstmt = null;
        PreparedStatement psDet = null;
        ResultSet rs = null; //存取全部部门结果集
        ResultSet rsDet = null;
        //先列出所有部门列表
        strBuf.append(SELECT_DEPT);

        strSql = strBuf.toString();
        String strTmpDept = new String();
        try {
            pstmt = conn.prepareStatement(strBuf.toString());
            rs = pstmt.executeQuery(); //部门列表集
            String preDept = "";
            while (rs.next()) {
                strTmpDept = StringUtils.replace(rs.getString("ID"), "'", "\'"); ;
                strDept = StringUtils.replaceText(rs.getString("ID"));
                strDeptName = rs.getString("NAME");
                //  --> 本级部门人数
                int iDeptUserNumber = this.getDeptHasWriteWorkLogUserNumber(
                    curTeamMembers, conn, strDept, strWhere);
                //  --> 下级部门人数
                int iNextDeptUserNumber = this.
                    getNextDeptHasWriteWorkLogUserNumber(
                    conn, strDept,
                    strWhere);
                if (iDeptUserNumber > 0 || iNextDeptUserNumber > 0) {
                    //只要该部门下有用户-并且该用户填写了该天的日志才会显示出该用户名在列表中
                    //如果当前部门拥有用户或者下级部门拥有用户--行内容
                    strRet += "<tr class=listrow1 id=\"trBM" + strTmpDept +
                        "\" name=\"trBM" + strTmpDept + "\"";
                    strRet += isShowStyle + ">" +
                        "<td class=listcellrow align=left width=\"100%\"><nobr>" +
                        StringUtils.replaceLen2(strDept) +
                        " <a onclick='openItem(\"" + strDept +
                        "\")' style='cursor:hand'>" +
                        "<img src='/\""+this.context+"/images/menu_open.gif' border=0 id=img"
                        + strDept + "></a>" + strDeptName + "</nobr></td>\n";
                } else { //该部门下不拥有用户时
                    strRet += "<tr class=listrow1 id=\"trBM" + strTmpDept +
                        "\" name=\"trBM" + strTmpDept + "\"" + isShowStyle +
                        ">" +
                        "<td class=listcellrow align=left width=\"100%\"><nobr>" +
                        StringUtils.replaceLen2(strDept) + strDeptName + "" +
                        "</nobr></td>\n";
                }
                if (iDeptUserNumber > 0) {
                    //当本级部门拥有用户并且这些用户填写了当天的日志时,需要其相关的人员记录-相当于body中内容
                    //List curDeptUserList = this.queryUsersByCurDept(curTeamMembers); //获得用户在当前部门选中的成员列表
                    strRet += "<tr class=listrow2 name=\"trRY" + strTmpDept +
                        "\" id=\"trRY" + strTmpDept +
                        "\" style='display:block'><td colspan=5 class=listcellrow>\n";
                    strBuf.setLength(0); //先置空stringBuffer
                    if (!isOracle()) {
                        strBuf.append("select e.ID,e.NAME,e.serial_num from "
                                      +
                                      " (select distinct c.ID,c.NAME,d.serial_num from "
                                      + " (select distinct a.ID, a.NAME "
                                      + " from T_USER a,T_DEPARTMENT_PERSON b "
                                      );
                        strBuf.append(
                            " where a.ID = b.ID and b.DEPARTMENT_ID = " +
                            Integer.valueOf(strDept) + ") c ");
                        strBuf.append(" LEFT OUTER JOIN DO_WORK_LOG d ");
                        strBuf.append(
                            " ON c.ID = d.USER_CODE and d.FILL_DATE='" +
                            curDate +
                            "') e ");
                        strBuf.append(" where e.serial_num is not null ");
                        if (!StringUtils.isNull(curTeamMembers)) { //如果在团队设置表中设置了团队成员
                            strBuf.append(" and e.ID in (" + curTeamMembers +
                                          ")");
                        } else {
                            strBuf.append(" and e.ID in (-1) "); //没有设置则让他为空
                        }
                    } else { //oracle
                        strBuf.append("select e.ID,e.NAME,e.serial_num from "
                                      +
                                      " (select distinct c.ID,c.NAME,d.serial_num from "
                                      + " (select distinct a.ID, a.NAME "
                                      + " from T_USER a,T_DEPARTMENT_PERSON b "
                                      );
                        strBuf.append(
                            " where a.ID = b.ID and b.DEPARTMENT_ID = " +
                            Integer.valueOf(strDept) +
                            ") c,DO_WORK_LOG d where");
                        strBuf.append(
                            "c.ID = d.USER_CODE(+) and d.FILL_DATE=to_date('" +
                           curDate  +
                            "','yyyy-mm-dd')) e ");
                        strBuf.append(" where e.serial_num is not null ");
                        if (!StringUtils.isNull(curTeamMembers)) { //如果在团队设置表中设置了团队成员
                            strBuf.append(" and e.ID in (" + curTeamMembers +
                                          ")");
                        } else {
                            strBuf.append(" and e.ID in (-1) "); //没有设置则让他为空
                        }

                    }
                    //这里的fill_date-如果没有查询的话显示的就是当天的日期
                    //如果有查询的话显示的就是查询那天的日期

                    //思路一：该sql语句查出当前部门下的所有用户ID及其用户中文名
                    //在该类型团队中设置了的在前面打个勾显示，如果该用户填写了这天
                    //的日志，则允许双击进入查看其日志---这里采用思路二
                    //思路二：这里应该查出当前部门下在该类型团队中设置了的
                    //并且今天填写了日志的那些用户的ID及其中文名
                    //没有设置的将不会显示，设置了但是没有填写日志的也不显示

                    //因为这里系统管理还没实现，暂时没有与用户级别挂钩起来
                    psDet = conn.prepareStatement(strBuf.toString());
                    rsDet = psDet.executeQuery();
                    strRet +=
                        "<table border=0 cellspacing=0 cellpadding=0 width='100%'>\n";
                    strRet += "<tr class=listrow2><td width=" +
                        ( (strDept.length() - 2) * 20) + "><div class=f12b>";
                    strRet += StringUtils.replaceLen(strDept) + "</div></td>\n";
                    i = 0;

                    //人员输出时需要,每行6列的方式输出,不够就补空表格
                    while (rsDet.next()) {
                        if ( ( (i % intCols) == 0) && i != 0) {
                            strRet += "</tr>\n";
                            strRet += "<tr class=listrow2><td width=" +
                                ( (strDept.length() - 2) * 20) +
                                "><div class=f12b>";
                            strRet += StringUtils.replaceLen(strDept) +
                                "</div></td>\n";
                        }
                        ++i;

                        strRet += "<td nowrap width='" +
                            (100 / intCols) + "%' " +
                            " ondblclick=\"toUrl('/"+context+"/dailyoffice/worklog/viewworklog.do?fid=" +
                            rsDet.getString("serial_num") + "',false)\"" +
                            "><nobr style='cursor:hand'>" + "<font size='2'>" +
                            rsDet.getString("NAME") +
                            "</nobr></td>\n";
                    } while ( (i % intCols) != 0) {
                        strRet += "<td width='" + (100 / intCols) +
                            "%'>&nbsp;</td>\n";
                        ++i;
                    }
                    rsDet.close();
                    psDet.close();
                    strRet += "</TR>\n</table>\n";
                } //if iDeptUserNumber > 0
                strRet += "</TD>\n</TR>\n";
                preDept = strDept;
            } //while dept rs.next
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception a) {
                a.printStackTrace();
            }
        }
        return strRet;
    }

    /**
     * 构造团队设置后团队日志列表页面的html字串(注由于部门级别可能是多级,用不了扩展标签来循环)-只是处理查询
     * 故写在这个Command里面,代码有点难看
     * 注:1.如果有传入部门代号,说明为部门日志查询,否则全体日志--系统管理用户权限还没有设定
     * 这里暂时默认为显示所有部门日志，其实是应该与用户权限挂钩的
     *    2.思路:首先将部门代号排序出来,
     *           再根据相对应的部门代号的长度,来缩进表格,
     *           循环时根据它的部门ID,找出相对应下级部门数以及本级部门人数等
     * @param query 查询条件-这里只有日期一项
     * @param user_code 用户ID
     * @param team_type 团队类型
     * @param vo vo
     * @return 查出的记录列表--转化成的html页面形式
     * @throws DefaultException
     */
    public String queryTeamWorkLogListHtmlForQuery(String query,
        String user_code,
        int team_type,
        VOInterface vo) throws
        DefaultException {
        String tempCurTeamMembers = this.queryTeamMembers(user_code, team_type,
            vo); //查询出当前选中的团队成员
        int teamMembersSize = tempCurTeamMembers.length();
        String curTeamMembers = "";
        if (!StringUtils.isNull(tempCurTeamMembers)) { //不为空时才截取,否则会出错
            curTeamMembers = tempCurTeamMembers.substring(0,
                teamMembersSize - 1);
        } else {
            curTeamMembers = "";
        }

        String strRet = new String("");
        String strWhere = new String("");
        strWhere = query;
        String strSql = new String("");
        String strDept = new String("");
        String strDeptName = new String("");
        String isShowStyle = " style='display:block'";
        String curDate = DateTimeUtils.getCurrentDate(); //取得当前日期--对应创建日期
        StringBuffer strBuf = new StringBuffer();
        int i = 0;
        int intCols = 6; //一行表格需要排几列-这里每行最多只显示六个用户
        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
            dbData);
        Connection conn = cp.getConnection();
        PreparedStatement pstmt = null;
        PreparedStatement psDet = null;
        ResultSet rs = null; //存取全部部门结果集
        ResultSet rsDet = null;
        //先列出所有部门列表
        strBuf.append(SELECT_DEPT);

        strSql = strBuf.toString();
        String strTmpDept = new String();
        try {
            pstmt = conn.prepareStatement(strBuf.toString());
            rs = pstmt.executeQuery(); //部门列表集
            String preDept = "";
            while (rs.next()) {
                strTmpDept = StringUtils.replace(rs.getString("ID"), "'", "\'"); ;
                strDept = StringUtils.replaceText(rs.getString("ID"));
                strDeptName = rs.getString("NAME");
                //  --> 本级部门人数
                int iDeptUserNumber = this.getDeptHasWriteWorkLogUserNumber(
                    curTeamMembers, conn, strDept, strWhere);
                //  --> 下级部门人数
                int iNextDeptUserNumber = this.
                    getNextDeptHasWriteWorkLogUserNumber(
                    conn, strDept,
                    strWhere);
                if (iDeptUserNumber > 0 || iNextDeptUserNumber > 0) {
                    //只要该部门下有用户-并且该用户填写了该天的日志才会显示出该用户名在列表中
                    //如果当前部门拥有用户或者下级部门拥有用户--行内容
                    strRet += "<tr class=listrow1 id=\"trBM" + strTmpDept +
                        "\" name=\"trBM" + strTmpDept + "\"";
                    strRet += isShowStyle + ">" +
                        "<td class=listcellrow align=left width=\"100%\"><nobr>" +
                        StringUtils.replaceLen2(strDept) +
                        " <a onclick='openItem(\"" + strDept +
                        "\")' style='cursor:hand'>" +
                        "<img src="+context+"/images/menu_open.gif' border=0 id=img"
                        + strDept + "></a>" + strDeptName + "</nobr></td>\n";
                } else { //该部门下不拥有用户时
                    strRet += "<tr class=listrow1 id=\"trBM" + strTmpDept +
                        "\" name=\"trBM" + strTmpDept + "\"" + isShowStyle +
                        ">" +
                        "<td class=listcellrow align=left width=\"100%\"><nobr>" +
                        StringUtils.replaceLen2(strDept) + strDeptName + "" +
                        "</nobr></td>\n";
                }
                if (iDeptUserNumber > 0) {
                    //当本级部门拥有用户并且这些用户填写了当天的日志时,需要其相关的人员记录-相当于body中内容
                    //List curDeptUserList = this.queryUsersByCurDept(curTeamMembers); //获得用户在当前部门选中的成员列表
                    strRet += "<tr class=listrow2 name=\"trRY" + strTmpDept +
                        "\" id=\"trRY" + strTmpDept +
                        "\" style='display:block'><td colspan=5 class=listcellrow>\n";
                    strBuf.setLength(0); //先置空stringBuffer
                    if (!this.isOracle()) {
                        strBuf.append("select e.ID,e.NAME,e.serial_num from "
                                      +
                                      " (select distinct c.ID,c.NAME,d.serial_num from "
                                      + " (select distinct a.ID, a.NAME "
                                      + " from T_USER a,T_DEPARTMENT_PERSON b "
                                      );
                        strBuf.append(
                            " where a.ID = b.ID and b.DEPARTMENT_ID = " +
                            Integer.valueOf(strDept) + ") c ");
                        strBuf.append(" LEFT OUTER JOIN DO_WORK_LOG d ");
                        strBuf.append(
                            " ON c.ID = d.USER_CODE and d.FILL_DATE='" +
                            query +
                            "') e ");
                        strBuf.append(" where e.serial_num is not null ");
                        if (!StringUtils.isNull(curTeamMembers)) { //如果在团队设置表中设置了团队成员
                            strBuf.append(" and e.ID in (" + curTeamMembers +
                                          ")");
                        } else {
                            strBuf.append(" and e.ID in (-1) "); //没有设置则让他为空
                        }
                    } else {
                        strBuf.append("select e.ID,e.NAME,e.serial_num from "
                                      +
                                      " (select distinct c.ID,c.NAME,d.serial_num from "
                                      + " (select distinct a.ID, a.NAME "
                                      + " from T_USER a,T_DEPARTMENT_PERSON b "
                                      );
                        strBuf.append(
                            " where a.ID = b.ID and b.DEPARTMENT_ID = " +
                            Integer.valueOf(strDept) +
                            ") c,DO_WORK_LOG d where ");
                        strBuf.append(
                            "c.ID = d.USER_CODE(+) and d.FILL_DATE=to_date('" +
                            query +
                            "','yyyy-mm-dd')) e ");
                        strBuf.append(" where e.serial_num is not null ");
                        if (!StringUtils.isNull(curTeamMembers)) { //如果在团队设置表中设置了团队成员
                            strBuf.append(" and e.ID in (" + curTeamMembers +
                                          ")");
                        } else {
                            strBuf.append(" and e.ID in (-1) "); //没有设置则让他为空
                        }

                    }
                    //这里的fill_date-如果没有查询的话显示的就是当天的日期
                    //如果有查询的话显示的就是查询那天的日期

                    //思路一：该sql语句查出当前部门下的所有用户ID及其用户中文名
                    //在该类型团队中设置了的在前面打个勾显示，如果该用户填写了这天
                    //的日志，则允许双击进入查看其日志---这里采用思路二
                    //思路二：这里应该查出当前部门下在该类型团队中设置了的
                    //并且今天填写了日志的那些用户的ID及其中文名
                    //没有设置的将不会显示，设置了但是没有填写日志的也不显示

                    //因为这里系统管理还没实现，暂时没有与用户级别挂钩起来
                    psDet = conn.prepareStatement(strBuf.toString());
                    rsDet = psDet.executeQuery();
                    strRet +=
                        "<table border=0 cellspacing=0 cellpadding=0 width='100%'>\n";
                    strRet += "<tr class=listrow2><td width=" +
                        ( (strDept.length() - 2) * 20) + "><div class=f12b>";
                    strRet += StringUtils.replaceLen(strDept) + "</div></td>\n";
                    i = 0;

                    //人员输出时需要,每行6列的方式输出,不够就补空表格
                    while (rsDet.next()) {
                        if ( ( (i % intCols) == 0) && i != 0) {
                            strRet += "</tr>\n";
                            strRet += "<tr class=listrow2><td width=" +
                                ( (strDept.length() - 2) * 20) +
                                "><div class=f12b>";
                            strRet += StringUtils.replaceLen(strDept) +
                                "</div></td>\n";
                        }
                        ++i;

                        strRet += "<td nowrap width='" +
                            (100 / intCols) + "%' " +
                            " ondblclick=\"toUrl('/"+context+"\"/dailyoffice/worklog/viewworklog.do?fid=" +
                            rsDet.getString("serial_num") + "',false)\"" +
                            "><nobr style='cursor:hand'>" + "<font size='2'>" +
                            rsDet.getString("NAME") +
                            "</nobr></td>\n";
                    } while ( (i % intCols) != 0) {
                        strRet += "<td width='" + (100 / intCols) +
                            "%'>&nbsp;</td>\n";
                        ++i;
                    }
                    rsDet.close();
                    psDet.close();
                    strRet += "</TR>\n</table>\n";
                } //if iDeptUserNumber > 0
                strRet += "</TD>\n</TR>\n";
                preDept = strDept;
            } //while dept rs.next
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception a) {
                a.printStackTrace();
            }
        }
        return strRet;
    }

    /**
     * 取得本级部门人数,主要去掉geValues这个公用方法,来得到其值
     * @param conn 连接
     * @param strDept 部门代号
     * @param strWhere 条件子句
     * @return int 多少部门人数数量
     * @throws DefaultException
     */
    private int getDeptUserNumber(Connection conn, String strDept,
                                  String strWhere) throws DefaultException {
        String curTeamMembers = "";
        String user_code = "";
        String team_type = "";
        return Integer.parseInt(getValues(curTeamMembers, user_code, team_type,
                                          conn, strDept, strWhere, 0).trim());
    }

    /**
     * 取得本级部门填写了日志的人数,主要去调用geValues这个公用方法,来得到其值
     * --此法暂时不用
     * --因为暂时先用checkbox框是否打勾来表示该用户是否设置了
     * @param curTeamMembers 是否在团队设置表中设置了
     * @param conn 连接
     * @param strDept 部门代号
     * @param strWhere 条件子句
     * @return int 多少部门人数数量
     * @throws DefaultException
     */
    private int getDeptHasWriteWorkLogUserNumber(String curTeamMembers,
                                                 Connection conn,
                                                 String strDept,
                                                 String strWhere) throws
        DefaultException {
        String user_code = "";
        String team_type = "";
        return Integer.parseInt(getValues(curTeamMembers, user_code, team_type,
                                          conn, strDept, strWhere, 4).trim());
    }

    /**
     * 取得下级部门统计个数,主要去掉geValues这个公用方法,来得到其值
     * @param conn 连接
     * @param strDept 部门代号
     * @param strWhere 条件子句
     * @return int 部门统计个数
     * @throws DefaultException
     */

    private int getNextDeptNumber(Connection conn, String strDept,
                                  String strWhere) throws DefaultException {
        String curTeamMembers = "";
        String user_code = "";
        String team_type = "";
        return Integer.parseInt(getValues(curTeamMembers, user_code, team_type,
                                          conn, strDept, strWhere, 1).trim());
    }

    /**
     * 取得下级部门填写了日志的人数,主要去调用geValues这个公用方法,来得到其值
     * @param conn 连接
     * @param strDept 部门代号
     * @param strWhere 条件子句
     * @return int 部门统计个数
     * @throws DefaultException
     */

    private int getNextDeptHasWriteWorkLogUserNumber(Connection conn,
        String strDept,
        String strWhere) throws DefaultException {
        String curTeamMembers = "";
        String user_code = "";
        String team_type = "";
        return Integer.parseInt(getValues(curTeamMembers, user_code, team_type,
                                          conn, strDept, strWhere, 5).trim());
    }

    /**
     * 取得下级部门人数,主要去掉geValues这个公用方法,来得到其值
     * @param conn Connection :连接
     * @param strDept String  :部门代号
     * @param strWhere String :条件子句
     * @return int(下级部门人数)
     * @throws DefaultException
     */

    private int getNextDeptUserNumber(Connection conn, String strDept,
                                      String strWhere) throws DefaultException {
        String curTeamMembers = "";
        String user_code = "";
        String team_type = "";
        return Integer.parseInt(getValues(curTeamMembers, user_code, team_type,
                                          conn, strDept, strWhere, 2).trim());
    }

    /**
     * 根据相应的函数传入的类型,得到相应的值.
     * @param curTeamMembers 当前用户设置的团队类型中的团队成员
     * @param conn 连接
     * @param strDept 部门代号
     * @param strWhere 条件子句
     * @param iFindType 获取的类型
     * @return String 相对应的值
     * @throws DefaultException
     */
    private String getValues(String curTeamMembers, String user_code,
                             String team_type, Connection conn, String strDept,
                             String strWhere, int iFindType) throws
        DefaultException {
        String strRet = new String("0");
        String curDate = DateTimeUtils.getCurrentDate(); //取得当前日期--对应创建日期
        StringBuffer strBuf = new StringBuffer();
        SQLHelper helper = new DefaultSQLHelper(dbData);
        switch (iFindType) {
            case 0: //本级部门人数

                //因为系统管理中用户权限设置还没有实现，这里暂时没有加入用户权限的判断sql
                strBuf.append(COUNT_USER_DEPT_PER0);
                strBuf.append(" and T_DEPARTMENT_PERSON.ID = T_USER.ID ");
                strBuf.append("and T_DEPARTMENT_PERSON.DEPARTMENT_ID = " +
                              Integer.valueOf(strDept) + " ");
                break;
            case 1: //下级部门数量
                strBuf.append(COUNT_USER_DEPT_PER1);
                strBuf.append(" AND PARENT_ID = " + Integer.valueOf(strDept) +
                              " ");
                break;
            case 2: //下级部门人数
                strBuf = new StringBuffer(100);
                strBuf.append(COUNT_USER_DEPT_PER2);
                strBuf.append(" and T_DEPARTMENT.PARENT_ID is not null ");
                strBuf.append("and T_DEPARTMENT.PARENT_ID = " +
                              Integer.valueOf(strDept) +
                              ") a,T_DEPARTMENT_PERSON b ");
                strBuf.append(
                    "where  a.DEPTID = b.DEPARTMENT_ID) c,T_DEPARTMENT_PERSON d ");
                strBuf.append("where d.DEPARTMENT_ID = c.deptID ");
                break;
            case 3: //部门主管

                //strBuf.append(" select (usermanage.fname) as Ret from dept ");
                //strBuf.append(" inner join usermanage on dept.deptleadercode = cast (usermanage.userid as char(20)) ");
                //strBuf.append(" AND dept.DEPTCODE ='").append(strDept).append(
                //    "'");
                //strRet = "";
                break;
            case 4: //本级部门填写了当天日志的人数,并且需要判断在团队设置中设置了
                strBuf.append(COUNT_USER_DEPT_PER4);
                strBuf.append("where T_DEPARTMENT_PERSON.ID = T_USER.ID ");
                strBuf.append("and T_DEPARTMENT_PERSON.DEPARTMENT_ID = " +
                              Integer.valueOf(strDept) + ") a,DO_WORK_LOG b ");

                if (!StringUtils.isNull(strWhere)) { //如果输入了查询日期
                    strBuf.append("where a.ID = b.user_code and b.fill_date=" +
                                  DateTimeUtils.toDate(strWhere) + " ");
                } else {
                    strBuf.append("where a.ID = b.user_code and b.fill_date=" +
                                  DateTimeUtils.toDate(curDate) + " ");
                }
                if (!StringUtils.isNull(curTeamMembers)) { //如果在团队设置表中设置了团队成员
                    strBuf.append(" and a.ID in (" + curTeamMembers + ")");
                } else {
                    strBuf.append(" and a.ID in (-1) "); //没有设置则让他为空
                }
                strBuf.append(" )c ");
                break;
            case 5: //下级部门填写了当天日志的人数
                strBuf.append(COUNT_USER_DEPT_PER5);
                strBuf.append("where T_DEPARTMENT.PARENT_ID is not null ");
                strBuf.append("and T_DEPARTMENT.PARENT_ID = " +
                              Integer.valueOf(strDept) +
                              ") a,T_DEPARTMENT_PERSON b ");
                strBuf.append(
                    "where  a.DEPTID = b.DEPARTMENT_ID) c,T_DEPARTMENT_PERSON d ");
                strBuf.append(
                    "where d.DEPARTMENT_ID = c.deptID) e,DO_WORK_LOG f ");
                if (!StringUtils.isNull(strWhere)) { //如果输入了查询日期
                    strBuf.append(
                        "where e.ID = f.user_code and f.fill_date = " +
                        DateTimeUtils.toDate(strWhere) + ") g ");
                } else {
                    strBuf.append(
                        "where e.ID = f.user_code and f.fill_date = " +
                        DateTimeUtils.toDate(curDate) + ") g ");
                }
                break;
            default: //其它选项就退出来
                return "";
        }
        //Map map = (HashMap) helper.query(strBuf.toString(),DataTypes.RS_SINGLE_MAP);
        HashMap map = (HashMap) helper.query(strBuf.toString(),
                                             DataTypes.RS_SINGLE_MAP);
        strRet = (String) map.get("ret");
        return strRet;
    }

    /**
     * 判断当前部门是否显示
     * @param strDept 当前部门代号
     * @param preDept 上一级部门代号
     * @return 是否显示
     */
    private boolean isShowHidden(String strDept, String preDept) {
        boolean bRet = false;
        if (preDept.length() < 2 || strDept.length() <= 2 ||
            strDept.length() <= preDept.length()) {
            return true;
        }
//        if (strDept.substring(0, strDept.length() - 2).equals(preDept)) {
//            bRet = false;}
        else {
            bRet = true;

        }
        return bRet;
    }

    /**
     * 进行团队设置
     * @param vo 当前部门代号
     * @return int intRet 是否设置成功
     */
    public int save(TeamWorkLogSetVO vo) throws Exception {
        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(
            dbData);
        Connection conn = cp.getConnection();
        //SQLHelper helper = new DefaultSQLHelper(dbData);
        StringBuffer delSql = new StringBuffer();
        StringBuffer insertSql = new StringBuffer();
        delSql.append(DELETE_TEAMLOG);
        insertSql.append(INSERT_TEAMLOG);
        PreparedStatement delPstmt = null;
        PreparedStatement insertPstmt = null;
        int intRet = -1;
        SQLHelper helper = new DefaultSQLHelper(dbData);
        try {
            helper.begin();
            helper.execUpdate(delSql.toString(), vo, "USER_CODE,TEAM_TYPE");
            vo.setSerial_num(IdMng.getModuleID(super.userID));
            helper.execUpdate(insertSql.toString(), vo, "SERIAL_NUM,USER_CODE,TEAM_TYPE,TEAM_MEMBER");
            helper.commit();

        } catch (DefaultException e) {
            helper.rollback();
        } finally {
            helper.end();
        }
        return intRet;
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
          SQLHelper helper = new DefaultSQLHelper(dbData); ;
          int iRet = -1;
          try {
              helper.begin();
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

    private String getSpaces(int num){
        StringBuffer sb = new StringBuffer(10);
        for (int idx = 0 ; idx <num ; idx++){
            sb.append("&nbsp;&nbsp;")    ;
        }
        return sb.toString();
      }
  public void setCacheMems(String cacheMems) {
    this.cacheMems = cacheMems;
  }
  public String getCacheMems() {
    return cacheMems;
  }
    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }

}
