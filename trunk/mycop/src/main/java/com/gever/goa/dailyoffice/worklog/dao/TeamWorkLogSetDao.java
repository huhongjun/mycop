package com.gever.goa.dailyoffice.worklog.dao;

import java.sql.SQLException;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.worklog.vo.TeamWorkLogSetVO;
import com.gever.vo.VOInterface;

/**
 * Title: 团队日志Dao接口
 * Description: 团队日志Dao接口
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */

public interface TeamWorkLogSetDao {
    public int delBySelect(String[] aryPk, VOInterface vo) throws
        DefaultException; //删除

    public String getPageSql(); //翻页

    public int insert(VOInterface vo) throws DefaultException; //新增

    public int update(VOInterface vo) throws DefaultException; //修改

    public List queryAll(VOInterface vo) throws DefaultException; //查询所有

    public List queryByPage(VOInterface vo, long start, long howMany) throws
        DefaultException; //按页查询

    public long queryByCount(VOInterface vo) throws DefaultException; //按数查询

    public int copy(String[] keyValue, VOInterface vo) throws DefaultException; //复制

    public List getTreeData() throws DefaultException; //获取树型结构列表

    public List queryUsersByCurDept(String team_member) throws DefaultException;

    //查询当前用户在团队日志表中某种团队中的某个部门的成员列表--user_code--当前用户id--team_type--团队类型--dept_code--部门代码

    public List queryDeptsByCurUser(VOInterface vo, int type) throws DefaultException;

    //查询当前用户在团队日志表中某种团队中的部门列表--userid--当前用户id--type--团队类型

    public String queryTeamMembers(String user_code, int team_type, VOInterface vo) throws DefaultException;

    //查询当前用户在团队日志表中某种团队中的某个部门的成员列表字符串--user_code--当前用户id--team_type--团队类型--dept_code--部门代码

    public String queryList(String user_code, int team_type, VOInterface vo) throws
        DefaultException;

    //查询出团队日志设置情况列表
    public String queryTeamWorkLogListHtml(String user_code, int team_type,
                                           VOInterface vo) throws
        DefaultException;

    //查询出团队日志列表

    public int save(TeamWorkLogSetVO vo) throws Exception; //保存团队日志设置

    public List queryDeptsByCurTeamMember(String teamMembers) throws DefaultException;

    //查询当前用户在团队日志表中某种团队中的所有成员对应的部门列表--teamMembers--团队成员字符串，逗号隔开

    //查出的是当前用户所设定在团队日志表中的那些团队成员，并且是写了当天的日志的那些才要查出来
    //显示该部门下的那些用户，并且这些用户应该是设在了团队成员中的那些
    public String queryTeamWorkLogListHtmlForQuery(String query, String user_code, int team_type,VOInterface vo) throws DefaultException;
    //查询出团队日志设置情况列表-输入了查询日期的情况

    /**
     * 得到当前团队的人员"1,2,3,4"的方法    翁乃彬(加入)
     * @param userID 用户ID
     * @param type 团队类型
     * @return 人员字串
     * @throws DefaultException
     */
    public String getTermMens(String userID, String type) throws
        DefaultException;

    public void setCacheMems(String cacheMems);

    public String getCacheMems();

    public void setContext(String context);
    public List getRootDept()throws DefaultException, SQLException;

}
