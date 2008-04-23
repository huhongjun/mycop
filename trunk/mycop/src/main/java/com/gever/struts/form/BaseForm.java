package com.gever.struts.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.gever.struts.pager.PageControl;
import com.gever.vo.VOInterface;


/**
 * <p>Title: 所有form bean的父类</p>
 * <p>Description:里面分页,动作类型,当前页,当前主键,orderby,当前用户名,当前vo对象 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class BaseForm extends ActionForm{
    private String userId;             // 当前用户ID
    private String actionType;         // 动作类型
    private VOInterface vo ;           // 当前vo对象
    public PageControl pageControl = new PageControl();   //分页控制类
    private long curPage =1l;          // 当前页
    private String fid="";             // 当前fid
    private String orderFld = "";      // 排序字段
    private String orderType = "asc";  // 排序类型
    private String sqlWhere = "";      // sql条件语句
    private String userName = "";      // 当前用户名称
    private String userFilter = "";    // 当前用户筛选层级
    private String logonName = "";     // 登入帐号
    private String curDeptCodes = "";  // 登入用户所对应部门ID(以逗号分隔,例:1001,1002)
    private String curDeptNames = ""; // 登入用户所对应部门名称(以空格分隔,例:行财部 开发部)
    public BaseForm() {
        if (pageControl != null && pageControl.getData() == null) {
            pageControl.setData(new ArrayList());
        }
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getActionType() {
        return actionType;
    }
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    public VOInterface getVo() {
        return vo;
    }
    public void setVo(VOInterface vo) {
        this.vo = vo;
    }
    public long getCurPage() {
        return curPage;
    }
    public void setCurPage(long curPage) {
        this.curPage = curPage;
    }
    public PageControl getPageControl() {
        return pageControl;
    }
    public void setPageControl(PageControl pageControl) {
        this.pageControl = pageControl;
    }
    public String getFid() {
        return fid;
    }
    public void setFid(String fid) {
        this.fid = fid;
    }
    public String getOrderFld() {
        return orderFld;
    }
    public void setOrderFld(String orderFld) {
        this.orderFld = orderFld;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getSqlWhere() {
        return sqlWhere;
    }
    public void setSqlWhere(String sqlWhere) {
        this.sqlWhere = sqlWhere;
    }
    public String getUserFilter() {
        return userFilter;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserFilter(String userFilter) {
        this.userFilter = userFilter;
    }
    public String getLogonName() {
        return logonName;
    }
    public void setLogonName(String logonName) {
        this.logonName = logonName;
    }
    public String getCurDeptCodes() {
        return curDeptCodes;
    }
    public void setCurDeptCodes(String curDeptCodes) {
        this.curDeptCodes = curDeptCodes;
    }
    public String getCurDeptNames() {
        return curDeptNames;
    }
    public void setCurDeptNames(String curDeptNames) {
        this.curDeptNames = curDeptNames;
    }

}