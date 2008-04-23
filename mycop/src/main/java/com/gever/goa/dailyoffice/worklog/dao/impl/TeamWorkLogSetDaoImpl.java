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
 * Title: �Ŷ���־Dao�ӿ�ʵ����
 * Description: �Ŷ���־Dao�ӿ�ʵ����
 * Copyright: Copyright (c) 2004
 * Company: �������
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

    //��ѯ�Ƿ����Ӳ��ŵ����
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

//QUERY_SQL--���巭ҳʱ�õĲ�ѯ���
    public TeamWorkLogSetDaoImpl(String dbData) {
        super(dbData);
    }

    /**
     * ���˱�(����)
     * ��ҳ��������ѯ
     * @param vo ��ѯʱ�õ�VO����
     * @param start ��ʼ���
     * @param howMany �������
     * @return ��¼������
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
     * �õ���ǰ����(���˱� ����)
     * @param curDate ��ǰ����
     * @param helper SqlHelper
     * @return �б�.
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
     * �õ���ǰ�Ŷӵ���Ա"1,2,3,4"�ķ���    ���˱�(����)
     * @param userID �û�ID
     * @param type �Ŷ�����
     * @return ��Ա�ִ�
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
     * ��ѯ��ǰ�û����Ŷ���־����ĳ���Ŷ��еĲ����б�
     * @param user_code ��ǰ�û�ID
     * @param vo ��ǰvo
     * @param team_type �Ŷ�����
     * @return List ��¼������
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
     * ��ѯ��ǰ�û����Ŷ���־����ĳ���Ŷ��е����г�Ա��Ӧ�Ĳ����б�
     * @param user_code ��ǰ�û�ID
     * @param team_type �Ŷ�����
     * @param vo ��ǰvo
     * @return List �����б��¼������
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
     * ����ȥ���ظ��ַ�����ͨ�÷���
     * @param stringList ����Ĵ��л򲻴����ظ��ַ�����List�б�
     * @return String �����ظ��ַ�����List�б�
     * @throws DefaultException
     */

    private String getChecked(List stringList) {
        ArrayList retList = new ArrayList();
        String strValue = "";
        String strTmp1 = "";
        String strTmp2 = "";
        for (int idx = 0; idx < stringList.size(); idx++) {
            strTmp1 = (String) stringList.get(idx); //ȡ�õ�һ��ѭ���е�ֵ
            for (int idy = idx + 1; idy < stringList.size(); idy++) { //�ӵڶ���ֵ�Ƚ��ж�
                strTmp2 = (String) stringList.get(idy);
                //����ط���û�д���
                //if (!strTmp2.equals(strTmp1)) {
                //retList.add(strTmp1);
                //break;
                //}
            }
        }
        return strValue;
    }

    /**
     * ��ѯ��ǰ�û��ڹ�����־���еĲ��ţ�ʵ����Ҳ����ϵͳ�����еĲ��ţ�������һ�µģ�
     * @param member �Ŷ���־��team_member�в�ֳ����ĵ�����Ա�ַ���
     * @return String ���Ӧ�Ĳ����ַ���
     * @throws DefaultException
     */
    public String queryDeptByUserCode(String member) throws
        DefaultException {
        String deptCode = "";
        SQLHelper helper = new DefaultSQLHelper(dbData);
        StringBuffer sbSql = new StringBuffer();
        String curDate = DateTimeUtils.getCurrentDate(); //ȡ�õ�ǰ����-��fill_date�Ƚ�ʱ��
        sbSql.append(SELECT_WORKLOG);
        sbSql.append(" and  user_code=" + Integer.parseInt(member) +
                     " and fill_date=" + DateTimeUtils.toDate(curDate) + " ");
        //�ӹ�����־���в��ҳ���Щ������д����־���û����ڵĲ���ID
        HashMap map = (HashMap) helper.query(sbSql.toString(),
                                             DataTypes.RS_SINGLE_MAP);
        deptCode = (String) map.get("dept_code");
        return deptCode;
    }

    /**
     * ��ѯ��ǰ�û����Ŷ���־����ĳ���Ŷ��е�ĳ�����ŵĳ�Ա�б�--team_member����queryTeamMembers()���
     * @param team_member �ö��ŷָ�ĸò����Ŷӳ�Ա
     * @return List ��¼������
     * @throws DefaultException
     */
    public List queryUsersByCurDept(String team_member) throws
        DefaultException {
        ArrayList deptUserList = new ArrayList();
        StringUtils.separate(deptUserList, team_member, ",");
        return deptUserList;
    }

    /**
     * ��ѯ��ǰ�û����Ŷ���־����ĳ���Ŷ��е�ĳ�����ŵĳ�Ա�б�
     * @param user_code ��ǰ�û�ID
     * @param team_type �Ŷ�����
     * @param dept_code ���Ŵ���
     * @param vo ��ǰvo
     * @return List ��¼������
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
        //Ϊ���ܹ����б�Ĳ�ѯsql�����ʹ��IN�������ȡ��teamMembers���ܻ���Ҫ��ȡ�����һ��","��
        return teamMembers;
    }

    /**
     * ���첿����־��ȫ����־��html�ִ�(ע���ڲ��ż�������Ƕ༶,�ò�����չ��ǩ��ѭ��)
     * ��д�����Command����,�����е��ѿ�
     * ע:1.����д��벿�Ŵ���,˵��Ϊ������־��ѯ,����ȫ����־--ϵͳ�����û�Ȩ�޻�û���趨
     * ������ʱĬ��Ϊ��ʾ���в�����־����ʵ��Ӧ�����û�Ȩ�޹ҹ���
     *    2.˼·:���Ƚ����Ŵ����������,
     *           �ٸ������Ӧ�Ĳ��Ŵ��ŵĳ���,���������,
     *           ѭ��ʱ�������Ĳ���ID,�ҳ����Ӧ�¼��������Լ���������������
     * @param user_code �û�ID
     * @param team_type �Ŷ�����
     * @param vo vo
     * @return ����ļ�¼�б�--ת���ɵ�htmlҳ����ʽ
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
        int intCols = 6; //һ�б����Ҫ�ż���
        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
            dbData);
        Connection conn = cp.getConnection();
        PreparedStatement pstmt = null;
        PreparedStatement psDet = null;
        ResultSet rs = null; //��ȡȫ�����Ž����
        ResultSet rsDet = null;

        //���г����в����б�
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

                    //  --> ������������
                    int iDeptUserNumber = this.getDeptUserNumber(conn, strDept,
                        strWhere);
                    //  --> �¼���������
                    int iNextDeptUserNumber = this.getNextDeptUserNumber(conn,
                        strDept,
                        strWhere);

                    if (iDeptUserNumber > 0 || iNextDeptUserNumber > 0 ) {
                        //�����ǰ����ӵ���û������¼�����ӵ���û�--������
                        strRet += "<tr class=listrow1 id=\"trBM" + strTmpDept +
                            "\" pid=\"trBM" + dept.getParentDepartment() + "\" name=\"trBM" + strTmpDept + "\"";
                        strRet += isShowStyle + ">" +
                            "<td class=listcellrow align=left width=\"100%\"><nobr>" +
                            this.getSpaces(lever) +
                            " <a onclick='openItem(\"" + strDept +
                            "\")' style='cursor:hand'>" +
                            "<img src=\""+context+"/images/menu_open.gif\" border=0 id=img"
                            + strDept + "></a>" + strDeptName + "</nobr></td>\n";
                    } else { //��ӵ���û�ʱ
                        strRet += "<tr class=listrow1 id=\"trBM" + strTmpDept +
                            "\" pid=\"trBM" + dept.getParentDepartment() + "\" name=\"trBM" + strTmpDept + "\"" + isShowStyle +
                            ">" +
                            "<td class=listcellrow align=left width=\"100%\"><nobr>" +
                            StringUtils.replaceLen2(strDept) + strDeptName + "" +
                            "</nobr></td>\n";
                    }

                    if (iDeptUserNumber > 0) { //����������ӵ���û�ʱ,��Ҫ����ص���Ա��¼-�൱��body������
                        strRet += "<tr class=listrow2 name=\"trRY" + strTmpDept +
                            "\" id=\"trRY" + strTmpDept +
                            "\" pid=\"trRY" + dept.getParentDepartment() + "\" style='display:block'><td colspan=5 class=listcellrow>\n";
                        strBuf.setLength(0); //���ÿ�stringBuffer
                        //��sql�������ǰ�����µ������û�ID�����û�������
                        strBuf.append(COUNT_USER_DEPT_PER_ALL);
                        strBuf.append(" where a.ID = b.ID and b.DEPARTMENT_ID = " +
                                      Integer.valueOf(strDept) + " ");
                        if (!super.isOracle()) {
                            strBuf.append(" and a.LEVEL >= '" + userLevel + "' ");
                        } else {
                            strBuf.append(" and a.LEV >= '" + userLevel + "' ");
                        }

                        //psDet = conn.prepareStatement(strBuf.toString());

                        //��sql�������ǰ�����µ������û�ID�����û�������
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
                            vo); //��ѯ����ǰѡ�е��Ŷӳ�Ա
                        List curDeptUserList = this.queryUsersByCurDept(
                            curTeamMembers); //����û��ڵ�ǰ����ѡ�еĳ�Ա�б�
                        //   ��Ա���ʱ��Ҫ,ÿ��6�еķ�ʽ���,�����Ͳ��ձ��
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
     * �õ����еĸ��ڵ�
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
     * �õ����е��Ӳ���
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
     * �����Ŷ����ú��Ŷ���־�б�ҳ���html�ִ�(ע���ڲ��ż�������Ƕ༶,�ò�����չ��ǩ��ѭ��)
     * ��д�����Command����,�����е��ѿ�
     * ע:1.����д��벿�Ŵ���,˵��Ϊ������־��ѯ,����ȫ����־--ϵͳ�����û�Ȩ�޻�û���趨
     * ������ʱĬ��Ϊ��ʾ���в�����־����ʵ��Ӧ�����û�Ȩ�޹ҹ���
     *    2.˼·:���Ƚ����Ŵ����������,
     *           �ٸ������Ӧ�Ĳ��Ŵ��ŵĳ���,���������,
     *           ѭ��ʱ�������Ĳ���ID,�ҳ����Ӧ�¼��������Լ���������������
     * @param user_code �û�ID
     * @param team_type �Ŷ�����
     * @param vo vo
     * @return ����ļ�¼�б�--ת���ɵ�htmlҳ����ʽ
     * @throws DefaultException
     */
    public String queryTeamWorkLogListHtml(String user_code, int team_type,
                                           VOInterface vo) throws
        DefaultException {
        String tempCurTeamMembers = this.queryTeamMembers(user_code, team_type,
            vo); //��ѯ����ǰѡ�е��Ŷӳ�Ա
        int teamMembersSize = tempCurTeamMembers.length();
        String curTeamMembers = "";
        if (!StringUtils.isNull(tempCurTeamMembers)) { //��Ϊ��ʱ�Ž�ȡ,��������
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
        String curDate = DateTimeUtils.getCurrentDate(); //ȡ�õ�ǰ����--��Ӧ��������
        StringBuffer strBuf = new StringBuffer();
        int i = 0;
        int intCols = 6; //һ�б����Ҫ�ż���-����ÿ�����ֻ��ʾ�����û�
        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.dbData);
        Connection conn = cp.getConnection();
        PreparedStatement pstmt = null;
        PreparedStatement psDet = null;
        ResultSet rs = null; //��ȡȫ�����Ž����
        ResultSet rsDet = null;
        //���г����в����б�
        strBuf.append(SELECT_DEPT);

        strSql = strBuf.toString();
        String strTmpDept = new String();
        try {
            pstmt = conn.prepareStatement(strBuf.toString());
            rs = pstmt.executeQuery(); //�����б�
            String preDept = "";
            while (rs.next()) {
                strTmpDept = StringUtils.replace(rs.getString("ID"), "'", "\'"); ;
                strDept = StringUtils.replaceText(rs.getString("ID"));
                strDeptName = rs.getString("NAME");
                //  --> ������������
                int iDeptUserNumber = this.getDeptHasWriteWorkLogUserNumber(
                    curTeamMembers, conn, strDept, strWhere);
                //  --> �¼���������
                int iNextDeptUserNumber = this.
                    getNextDeptHasWriteWorkLogUserNumber(
                    conn, strDept,
                    strWhere);
                if (iDeptUserNumber > 0 || iNextDeptUserNumber > 0) {
                    //ֻҪ�ò��������û�-���Ҹ��û���д�˸������־�Ż���ʾ�����û������б���
                    //�����ǰ����ӵ���û������¼�����ӵ���û�--������
                    strRet += "<tr class=listrow1 id=\"trBM" + strTmpDept +
                        "\" name=\"trBM" + strTmpDept + "\"";
                    strRet += isShowStyle + ">" +
                        "<td class=listcellrow align=left width=\"100%\"><nobr>" +
                        StringUtils.replaceLen2(strDept) +
                        " <a onclick='openItem(\"" + strDept +
                        "\")' style='cursor:hand'>" +
                        "<img src='/\""+this.context+"/images/menu_open.gif' border=0 id=img"
                        + strDept + "></a>" + strDeptName + "</nobr></td>\n";
                } else { //�ò����²�ӵ���û�ʱ
                    strRet += "<tr class=listrow1 id=\"trBM" + strTmpDept +
                        "\" name=\"trBM" + strTmpDept + "\"" + isShowStyle +
                        ">" +
                        "<td class=listcellrow align=left width=\"100%\"><nobr>" +
                        StringUtils.replaceLen2(strDept) + strDeptName + "" +
                        "</nobr></td>\n";
                }
                if (iDeptUserNumber > 0) {
                    //����������ӵ���û�������Щ�û���д�˵������־ʱ,��Ҫ����ص���Ա��¼-�൱��body������
                    //List curDeptUserList = this.queryUsersByCurDept(curTeamMembers); //����û��ڵ�ǰ����ѡ�еĳ�Ա�б�
                    strRet += "<tr class=listrow2 name=\"trRY" + strTmpDept +
                        "\" id=\"trRY" + strTmpDept +
                        "\" style='display:block'><td colspan=5 class=listcellrow>\n";
                    strBuf.setLength(0); //���ÿ�stringBuffer
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
                        if (!StringUtils.isNull(curTeamMembers)) { //������Ŷ����ñ����������Ŷӳ�Ա
                            strBuf.append(" and e.ID in (" + curTeamMembers +
                                          ")");
                        } else {
                            strBuf.append(" and e.ID in (-1) "); //û������������Ϊ��
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
                        if (!StringUtils.isNull(curTeamMembers)) { //������Ŷ����ñ����������Ŷӳ�Ա
                            strBuf.append(" and e.ID in (" + curTeamMembers +
                                          ")");
                        } else {
                            strBuf.append(" and e.ID in (-1) "); //û������������Ϊ��
                        }

                    }
                    //�����fill_date-���û�в�ѯ�Ļ���ʾ�ľ��ǵ��������
                    //����в�ѯ�Ļ���ʾ�ľ��ǲ�ѯ���������

                    //˼·һ����sql�������ǰ�����µ������û�ID�����û�������
                    //�ڸ������Ŷ��������˵���ǰ��������ʾ��������û���д������
                    //����־��������˫������鿴����־---�������˼·��
                    //˼·��������Ӧ�ò����ǰ�������ڸ������Ŷ��������˵�
                    //���ҽ�����д����־����Щ�û���ID����������
                    //û�����õĽ�������ʾ�������˵���û����д��־��Ҳ����ʾ

                    //��Ϊ����ϵͳ����ûʵ�֣���ʱû�����û�����ҹ�����
                    psDet = conn.prepareStatement(strBuf.toString());
                    rsDet = psDet.executeQuery();
                    strRet +=
                        "<table border=0 cellspacing=0 cellpadding=0 width='100%'>\n";
                    strRet += "<tr class=listrow2><td width=" +
                        ( (strDept.length() - 2) * 20) + "><div class=f12b>";
                    strRet += StringUtils.replaceLen(strDept) + "</div></td>\n";
                    i = 0;

                    //��Ա���ʱ��Ҫ,ÿ��6�еķ�ʽ���,�����Ͳ��ձ��
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
     * �����Ŷ����ú��Ŷ���־�б�ҳ���html�ִ�(ע���ڲ��ż�������Ƕ༶,�ò�����չ��ǩ��ѭ��)-ֻ�Ǵ����ѯ
     * ��д�����Command����,�����е��ѿ�
     * ע:1.����д��벿�Ŵ���,˵��Ϊ������־��ѯ,����ȫ����־--ϵͳ�����û�Ȩ�޻�û���趨
     * ������ʱĬ��Ϊ��ʾ���в�����־����ʵ��Ӧ�����û�Ȩ�޹ҹ���
     *    2.˼·:���Ƚ����Ŵ����������,
     *           �ٸ������Ӧ�Ĳ��Ŵ��ŵĳ���,���������,
     *           ѭ��ʱ�������Ĳ���ID,�ҳ����Ӧ�¼��������Լ���������������
     * @param query ��ѯ����-����ֻ������һ��
     * @param user_code �û�ID
     * @param team_type �Ŷ�����
     * @param vo vo
     * @return ����ļ�¼�б�--ת���ɵ�htmlҳ����ʽ
     * @throws DefaultException
     */
    public String queryTeamWorkLogListHtmlForQuery(String query,
        String user_code,
        int team_type,
        VOInterface vo) throws
        DefaultException {
        String tempCurTeamMembers = this.queryTeamMembers(user_code, team_type,
            vo); //��ѯ����ǰѡ�е��Ŷӳ�Ա
        int teamMembersSize = tempCurTeamMembers.length();
        String curTeamMembers = "";
        if (!StringUtils.isNull(tempCurTeamMembers)) { //��Ϊ��ʱ�Ž�ȡ,��������
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
        String curDate = DateTimeUtils.getCurrentDate(); //ȡ�õ�ǰ����--��Ӧ��������
        StringBuffer strBuf = new StringBuffer();
        int i = 0;
        int intCols = 6; //һ�б����Ҫ�ż���-����ÿ�����ֻ��ʾ�����û�
        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
            dbData);
        Connection conn = cp.getConnection();
        PreparedStatement pstmt = null;
        PreparedStatement psDet = null;
        ResultSet rs = null; //��ȡȫ�����Ž����
        ResultSet rsDet = null;
        //���г����в����б�
        strBuf.append(SELECT_DEPT);

        strSql = strBuf.toString();
        String strTmpDept = new String();
        try {
            pstmt = conn.prepareStatement(strBuf.toString());
            rs = pstmt.executeQuery(); //�����б�
            String preDept = "";
            while (rs.next()) {
                strTmpDept = StringUtils.replace(rs.getString("ID"), "'", "\'"); ;
                strDept = StringUtils.replaceText(rs.getString("ID"));
                strDeptName = rs.getString("NAME");
                //  --> ������������
                int iDeptUserNumber = this.getDeptHasWriteWorkLogUserNumber(
                    curTeamMembers, conn, strDept, strWhere);
                //  --> �¼���������
                int iNextDeptUserNumber = this.
                    getNextDeptHasWriteWorkLogUserNumber(
                    conn, strDept,
                    strWhere);
                if (iDeptUserNumber > 0 || iNextDeptUserNumber > 0) {
                    //ֻҪ�ò��������û�-���Ҹ��û���д�˸������־�Ż���ʾ�����û������б���
                    //�����ǰ����ӵ���û������¼�����ӵ���û�--������
                    strRet += "<tr class=listrow1 id=\"trBM" + strTmpDept +
                        "\" name=\"trBM" + strTmpDept + "\"";
                    strRet += isShowStyle + ">" +
                        "<td class=listcellrow align=left width=\"100%\"><nobr>" +
                        StringUtils.replaceLen2(strDept) +
                        " <a onclick='openItem(\"" + strDept +
                        "\")' style='cursor:hand'>" +
                        "<img src="+context+"/images/menu_open.gif' border=0 id=img"
                        + strDept + "></a>" + strDeptName + "</nobr></td>\n";
                } else { //�ò����²�ӵ���û�ʱ
                    strRet += "<tr class=listrow1 id=\"trBM" + strTmpDept +
                        "\" name=\"trBM" + strTmpDept + "\"" + isShowStyle +
                        ">" +
                        "<td class=listcellrow align=left width=\"100%\"><nobr>" +
                        StringUtils.replaceLen2(strDept) + strDeptName + "" +
                        "</nobr></td>\n";
                }
                if (iDeptUserNumber > 0) {
                    //����������ӵ���û�������Щ�û���д�˵������־ʱ,��Ҫ����ص���Ա��¼-�൱��body������
                    //List curDeptUserList = this.queryUsersByCurDept(curTeamMembers); //����û��ڵ�ǰ����ѡ�еĳ�Ա�б�
                    strRet += "<tr class=listrow2 name=\"trRY" + strTmpDept +
                        "\" id=\"trRY" + strTmpDept +
                        "\" style='display:block'><td colspan=5 class=listcellrow>\n";
                    strBuf.setLength(0); //���ÿ�stringBuffer
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
                        if (!StringUtils.isNull(curTeamMembers)) { //������Ŷ����ñ����������Ŷӳ�Ա
                            strBuf.append(" and e.ID in (" + curTeamMembers +
                                          ")");
                        } else {
                            strBuf.append(" and e.ID in (-1) "); //û������������Ϊ��
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
                        if (!StringUtils.isNull(curTeamMembers)) { //������Ŷ����ñ����������Ŷӳ�Ա
                            strBuf.append(" and e.ID in (" + curTeamMembers +
                                          ")");
                        } else {
                            strBuf.append(" and e.ID in (-1) "); //û������������Ϊ��
                        }

                    }
                    //�����fill_date-���û�в�ѯ�Ļ���ʾ�ľ��ǵ��������
                    //����в�ѯ�Ļ���ʾ�ľ��ǲ�ѯ���������

                    //˼·һ����sql�������ǰ�����µ������û�ID�����û�������
                    //�ڸ������Ŷ��������˵���ǰ��������ʾ��������û���д������
                    //����־��������˫������鿴����־---�������˼·��
                    //˼·��������Ӧ�ò����ǰ�������ڸ������Ŷ��������˵�
                    //���ҽ�����д����־����Щ�û���ID����������
                    //û�����õĽ�������ʾ�������˵���û����д��־��Ҳ����ʾ

                    //��Ϊ����ϵͳ����ûʵ�֣���ʱû�����û�����ҹ�����
                    psDet = conn.prepareStatement(strBuf.toString());
                    rsDet = psDet.executeQuery();
                    strRet +=
                        "<table border=0 cellspacing=0 cellpadding=0 width='100%'>\n";
                    strRet += "<tr class=listrow2><td width=" +
                        ( (strDept.length() - 2) * 20) + "><div class=f12b>";
                    strRet += StringUtils.replaceLen(strDept) + "</div></td>\n";
                    i = 0;

                    //��Ա���ʱ��Ҫ,ÿ��6�еķ�ʽ���,�����Ͳ��ձ��
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
     * ȡ�ñ�����������,��Ҫȥ��geValues������÷���,���õ���ֵ
     * @param conn ����
     * @param strDept ���Ŵ���
     * @param strWhere �����Ӿ�
     * @return int ���ٲ�����������
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
     * ȡ�ñ���������д����־������,��Ҫȥ����geValues������÷���,���õ���ֵ
     * --�˷���ʱ����
     * --��Ϊ��ʱ����checkbox���Ƿ������ʾ���û��Ƿ�������
     * @param curTeamMembers �Ƿ����Ŷ����ñ���������
     * @param conn ����
     * @param strDept ���Ŵ���
     * @param strWhere �����Ӿ�
     * @return int ���ٲ�����������
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
     * ȡ���¼�����ͳ�Ƹ���,��Ҫȥ��geValues������÷���,���õ���ֵ
     * @param conn ����
     * @param strDept ���Ŵ���
     * @param strWhere �����Ӿ�
     * @return int ����ͳ�Ƹ���
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
     * ȡ���¼�������д����־������,��Ҫȥ����geValues������÷���,���õ���ֵ
     * @param conn ����
     * @param strDept ���Ŵ���
     * @param strWhere �����Ӿ�
     * @return int ����ͳ�Ƹ���
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
     * ȡ���¼���������,��Ҫȥ��geValues������÷���,���õ���ֵ
     * @param conn Connection :����
     * @param strDept String  :���Ŵ���
     * @param strWhere String :�����Ӿ�
     * @return int(�¼���������)
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
     * ������Ӧ�ĺ������������,�õ���Ӧ��ֵ.
     * @param curTeamMembers ��ǰ�û����õ��Ŷ������е��Ŷӳ�Ա
     * @param conn ����
     * @param strDept ���Ŵ���
     * @param strWhere �����Ӿ�
     * @param iFindType ��ȡ������
     * @return String ���Ӧ��ֵ
     * @throws DefaultException
     */
    private String getValues(String curTeamMembers, String user_code,
                             String team_type, Connection conn, String strDept,
                             String strWhere, int iFindType) throws
        DefaultException {
        String strRet = new String("0");
        String curDate = DateTimeUtils.getCurrentDate(); //ȡ�õ�ǰ����--��Ӧ��������
        StringBuffer strBuf = new StringBuffer();
        SQLHelper helper = new DefaultSQLHelper(dbData);
        switch (iFindType) {
            case 0: //������������

                //��Ϊϵͳ�������û�Ȩ�����û�û��ʵ�֣�������ʱû�м����û�Ȩ�޵��ж�sql
                strBuf.append(COUNT_USER_DEPT_PER0);
                strBuf.append(" and T_DEPARTMENT_PERSON.ID = T_USER.ID ");
                strBuf.append("and T_DEPARTMENT_PERSON.DEPARTMENT_ID = " +
                              Integer.valueOf(strDept) + " ");
                break;
            case 1: //�¼���������
                strBuf.append(COUNT_USER_DEPT_PER1);
                strBuf.append(" AND PARENT_ID = " + Integer.valueOf(strDept) +
                              " ");
                break;
            case 2: //�¼���������
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
            case 3: //��������

                //strBuf.append(" select (usermanage.fname) as Ret from dept ");
                //strBuf.append(" inner join usermanage on dept.deptleadercode = cast (usermanage.userid as char(20)) ");
                //strBuf.append(" AND dept.DEPTCODE ='").append(strDept).append(
                //    "'");
                //strRet = "";
                break;
            case 4: //����������д�˵�����־������,������Ҫ�ж����Ŷ�������������
                strBuf.append(COUNT_USER_DEPT_PER4);
                strBuf.append("where T_DEPARTMENT_PERSON.ID = T_USER.ID ");
                strBuf.append("and T_DEPARTMENT_PERSON.DEPARTMENT_ID = " +
                              Integer.valueOf(strDept) + ") a,DO_WORK_LOG b ");

                if (!StringUtils.isNull(strWhere)) { //��������˲�ѯ����
                    strBuf.append("where a.ID = b.user_code and b.fill_date=" +
                                  DateTimeUtils.toDate(strWhere) + " ");
                } else {
                    strBuf.append("where a.ID = b.user_code and b.fill_date=" +
                                  DateTimeUtils.toDate(curDate) + " ");
                }
                if (!StringUtils.isNull(curTeamMembers)) { //������Ŷ����ñ����������Ŷӳ�Ա
                    strBuf.append(" and a.ID in (" + curTeamMembers + ")");
                } else {
                    strBuf.append(" and a.ID in (-1) "); //û������������Ϊ��
                }
                strBuf.append(" )c ");
                break;
            case 5: //�¼�������д�˵�����־������
                strBuf.append(COUNT_USER_DEPT_PER5);
                strBuf.append("where T_DEPARTMENT.PARENT_ID is not null ");
                strBuf.append("and T_DEPARTMENT.PARENT_ID = " +
                              Integer.valueOf(strDept) +
                              ") a,T_DEPARTMENT_PERSON b ");
                strBuf.append(
                    "where  a.DEPTID = b.DEPARTMENT_ID) c,T_DEPARTMENT_PERSON d ");
                strBuf.append(
                    "where d.DEPARTMENT_ID = c.deptID) e,DO_WORK_LOG f ");
                if (!StringUtils.isNull(strWhere)) { //��������˲�ѯ����
                    strBuf.append(
                        "where e.ID = f.user_code and f.fill_date = " +
                        DateTimeUtils.toDate(strWhere) + ") g ");
                } else {
                    strBuf.append(
                        "where e.ID = f.user_code and f.fill_date = " +
                        DateTimeUtils.toDate(curDate) + ") g ");
                }
                break;
            default: //����ѡ����˳���
                return "";
        }
        //Map map = (HashMap) helper.query(strBuf.toString(),DataTypes.RS_SINGLE_MAP);
        HashMap map = (HashMap) helper.query(strBuf.toString(),
                                             DataTypes.RS_SINGLE_MAP);
        strRet = (String) map.get("ret");
        return strRet;
    }

    /**
     * �жϵ�ǰ�����Ƿ���ʾ
     * @param strDept ��ǰ���Ŵ���
     * @param preDept ��һ�����Ŵ���
     * @return �Ƿ���ʾ
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
     * �����Ŷ�����
     * @param vo ��ǰ���Ŵ���
     * @return int intRet �Ƿ����óɹ�
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
