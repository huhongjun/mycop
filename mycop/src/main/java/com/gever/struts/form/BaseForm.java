package com.gever.struts.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.gever.struts.pager.PageControl;
import com.gever.vo.VOInterface;


/**
 * <p>Title: ����form bean�ĸ���</p>
 * <p>Description:�����ҳ,��������,��ǰҳ,��ǰ����,orderby,��ǰ�û���,��ǰvo���� </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class BaseForm extends ActionForm{
    private String userId;             // ��ǰ�û�ID
    private String actionType;         // ��������
    private VOInterface vo ;           // ��ǰvo����
    public PageControl pageControl = new PageControl();   //��ҳ������
    private long curPage =1l;          // ��ǰҳ
    private String fid="";             // ��ǰfid
    private String orderFld = "";      // �����ֶ�
    private String orderType = "asc";  // ��������
    private String sqlWhere = "";      // sql�������
    private String userName = "";      // ��ǰ�û�����
    private String userFilter = "";    // ��ǰ�û�ɸѡ�㼶
    private String logonName = "";     // �����ʺ�
    private String curDeptCodes = "";  // �����û�����Ӧ����ID(�Զ��ŷָ�,��:1001,1002)
    private String curDeptNames = ""; // �����û�����Ӧ��������(�Կո�ָ�,��:�вƲ� ������)
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