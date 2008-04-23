package com.gever.goa.dailyoffice.worklog.dao;

import java.util.List;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 工作日志Dao接口</p>
 * <p>Description: 工作日志Dao接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface WorkLogDao {

    public int delBySelect(String[] aryPk, VOInterface vo) throws DefaultException;//删除
    public String getPageSql();//翻页
    public int insert(VOInterface vo) throws DefaultException;//新增
    public int update(VOInterface vo) throws DefaultException;//修改
    public List queryAll(VOInterface vo) throws DefaultException;//查询所有
    public List queryByPage(VOInterface vo, long start, long howMany) throws DefaultException;//按页查询
    public long queryByCount(VOInterface vo) throws DefaultException;//按数查询
    public int copy(String[] keyValue,VOInterface vo) throws DefaultException;//复制

    public boolean findCurPsnCurDayWorkLogIsExist(String userid,String curdate) throws DefaultException;
    //用来判断当前人是否已经填写了当天的日志
    //public int insertTemplate(VOInterface vo)throws DefaultException;//添加模板
    public int findLogNumOfEveryPage() throws DefaultException;
    //获取SYS_PARAMETER表中的每页的日志记录显示数
    public String isCurUserTodayWorkLog(String curUser,String userid,String filldate,String curDate) throws DefaultException;
    //用来判断查看的日志是否是当天的日志
    public VOInterface getCurDayWorkLog(String userid,String curdate) throws Exception;
    //用来获取当前日期的报表中填写的工作日志
    public String getSerialNumByUseridAndCurdate(String userid, String curdate) throws Exception;
    //用来获取当前人在当前日期填写的日志对应的serial_number
    public boolean insertLogsIntoBakTable(String logTotalStr,String logGrjb,String logID,String userID) throws DefaultException;
    //用来在新增一条日志时向备份表中加入相应的记录
    public int updateLogContent(String logID, String log_content, String logGrjb) throws DefaultException;
    //通过工作日志logID更改当前工作日志内容表中的内容
    public String getLogTotalStrByUseridAndCurdate(String userid, String curdate) throws Exception;
    //用来获取当前人在当前日期填写的日志对应的日志内容的字符串
    public List getLogListByUseridAndCurdate(String userid, String curdate) throws Exception;
    //用来获取当前人在当前日期填写的日志对应的日志内容的字符串列表
    public Map getLogStrAndGrjbByUseridAndCurdate(String userid, String curdate) throws Exception;
    //用来获取当前人在当前日期填写的日志对应的日志内容的字符串和个人进步的字符串
     public int deleteLogContent(String logID) throws DefaultException;
     //删除LogContent表里面的内容
}

