package com.gever.goa.dailyoffice.worklog.action;

import java.util.ArrayList;
import java.util.List;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title:工作日志Form类</p>
 * <p>Description: 工作日志Form类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class WorkLogForm extends BaseForm {
    private String username; //用户中文名
    private String deptnames; //当前用户所在部门中文名
    private String cellcontent; //报表内容
    private String cellname; //临时报表路径名
    private String searchBeginTime; //查询开始时间
    private String searchEndTime; //查询结束时间
    private String isInsertFlag = "";

    //判断是否可以新增的标志-当天的日志没有写则可新增
    //判断是否已经写过当天的日志--1--已写过--0--还没有写
    private String isTodayWorkLogFlag = "";

    //用来判断是否是今天的日志-查看时屏蔽修改按钮用
    //因为日志列表中显示的总是当前人一个人的日志，所以不用再判断是否时当前人写的日志
    //但是由于日志查询中可能直接查看用户日志，所以还是需要加入当前人的判断条件
    private String reportTemplateName; //用来获取从报表模板表获取过来模板路径
    private String logTotalStr; //用来获取日志的字符串
    private String logGrjb; //用来获取日志中的个人进步
    private List listLog = new ArrayList();
    private List adviceList = new ArrayList();//日志建议列表
    private String adviceWords;//日志建议信息
    private String editAdviceFlag = "0";//是否编辑日志信息,0为不编辑，1为编辑

    public WorkLogForm() {
    }

    public String getCellcontent() {
        return cellcontent;
    }

    public String getCellname() {
        return cellname;
    }

    public void setCellcontent(String cellcontent) {
        this.cellcontent = cellcontent;
    }

    public void setCellname(String cellname) {
        this.cellname = cellname;
    }

    public String getSearchBeginTime() {
        return searchBeginTime;
    }

    public String getSearchEndTime() {
        return searchEndTime;
    }

    public void setSearchBeginTime(String searchBeginTime) {
        this.searchBeginTime = searchBeginTime;
    }

    public void setSearchEndTime(String searchEndTime) {
        this.searchEndTime = searchEndTime;
    }

    public String getIsInsertFlag() {
        return isInsertFlag;
    }

    public void setIsInsertFlag(String isInsertFlag) {
        this.isInsertFlag = isInsertFlag;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeptnames() {
        return deptnames;
    }

    public void setDeptnames(String deptnames) {
        this.deptnames = deptnames;
    }

    public List getListLog() {
        return listLog;
    }

    public void setListLog(List listLog) {
        this.listLog = listLog;
    }

    public String getIsTodayWorkLogFlag() {
        return isTodayWorkLogFlag;
    }

    public void setIsTodayWorkLogFlag(String isTodayWorkLogFlag) {
        this.isTodayWorkLogFlag = isTodayWorkLogFlag;
    }

    public String getReportTemplateName() {
        return reportTemplateName;
    }

    public void setReportTemplateName(String reportTemplateName) {
        this.reportTemplateName = reportTemplateName;
    }

    public String getLogTotalStr() {
        return logTotalStr;
    }

    public void setLogTotalStr(String logTotalStr) {
        this.logTotalStr = logTotalStr;
    }

    public String getLogGrjb() {
        return logGrjb;
    }

    public void setLogGrjb(String logGrjb) {
        this.logGrjb = logGrjb;
    }
    public List getAdviceList() {
        return adviceList;
    }
    public void setAdviceList(List adviceList) {
        this.adviceList = adviceList;
    }
    public String getAdviceWords() {
        return adviceWords;
    }
    public void setAdviceWords(String adviceWords) {
        this.adviceWords = adviceWords;
    }
    public String getEditAdviceFlag() {
        return editAdviceFlag;
    }
    public void setEditAdviceFlag(String editAdviceFlag) {
        this.editAdviceFlag = editAdviceFlag;
    }
}
