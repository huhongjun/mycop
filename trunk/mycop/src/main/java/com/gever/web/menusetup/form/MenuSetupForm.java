package com.gever.web.menusetup.form;

import org.apache.struts.action.ActionForm;

import com.gever.web.homepage.vo.UserMenuVO;

import java.util.*;

/**
 * <p>Title: �˵�����form��</p>
 * <p>Description: �˵�����form��</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class MenuSetupForm extends ActionForm {

    private String userID = "";    //�û�ID��
    private String userName = "";  //�û�����
    private UserMenuVO vo = new UserMenuVO();   //
    private UserMenuVO pvo = new UserMenuVO();   //    �ϼ��˵�
    private List curNodeList = new ArrayList(); //��ǰ�ڵ�����
    private String nodeid = "";     //��ǰ�ڵ�ID
    private String nodename = "";   //��ǰ�ڵ�����
    private String isFolder = "1";  //�Ƿ�ΪĿ¼
    private String parentid ="";    //��ID
    private String isRoot = "false";  //�Ƿ�Ϊ��Ŀ¼
    private int sum = 0;    //���
    private List showMenu = new ArrayList();  //��ʾ�˵�����
    private List hideMenu = new ArrayList();  //���ز˵�����
    private List selMenu  = new ArrayList();  //��ѡ��˵�����
    private List noSelMenu  = new ArrayList(); //��ѡ��˵�����
    private List homeData = new ArrayList();  //
    private String showData = "";       //���ز˵�ҳ�����ʾ����
    private String hideData = "";       //���ز˵�ҳ�����������
    private String actionFlag = "add";  //��������
    private String outOption = "";
    private int hides  = 0;
    private int levelnums = 0;
    private String hasInsert = "false";  //�Ƿ�Ϊ��Ŀ¼
    private String newNodes = "";
    private String maxMenus = "10";

    public MenuSetupForm() {
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public UserMenuVO getVo() {
        return vo;
    }

    public void setVo(UserMenuVO vo) {
        this.vo = vo;
    }
    public List getCurNodeList() {
        return curNodeList;
    }
    public String getIsFolder() {
        return isFolder;
    }
    public String getNodeid() {
        return nodeid;
    }
    public String getNodename() {
        return nodename;
    }
    public void setNodename(String nodename) {
        this.nodename = nodename;
    }
    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }
    public void setIsFolder(String isFolder) {
        this.isFolder = isFolder;
    }
    public void setCurNodeList(List curNodeList) {
        this.curNodeList = curNodeList;
    }
    public List getNoSelMenu() {
        return noSelMenu;
    }
    public List getSelMenu() {
        return selMenu;
    }
    public List getShowMenu() {
        return showMenu;
    }
    public List getHideMenu() {
        return hideMenu;
    }
    public void setHideMenu(List hideMenu) {
        this.hideMenu = hideMenu;
    }
    public void setNoSelMenu(List noSelMenu) {
        this.noSelMenu = noSelMenu;
    }
    public void setSelMenu(List selMenu) {
        this.selMenu = selMenu;
    }
    public void setShowMenu(List showMenu) {
        this.showMenu = showMenu;
    }
    public String getShowData() {
        return showData;
    }
    public void setShowData(String showData) {
        this.showData = showData;
    }
    public String getHideData() {
        return hideData;
    }
    public void setHideData(String hideData) {
        this.hideData = hideData;
    }
    public String getParentid() {
        return parentid;
    }
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
    public String getIsRoot() {
        return isRoot;
    }
    public void setIsRoot(String isRoot) {
        this.isRoot = isRoot;
    }
    public List getHomeData() {
        return homeData;
    }
    public void setHomeData(List homeData) {
        this.homeData = homeData;
    }
    public int getSum() {
        return sum;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }
    public String getActionFlag() {
        return actionFlag;
    }
    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }
    public String getOutOption() {
        return outOption;
    }
    public void setOutOption(String outOption) {
        this.outOption = outOption;
    }
    public UserMenuVO getPvo() {
        return pvo;
    }
    public void setPvo(UserMenuVO pvo) {
        this.pvo = pvo;
    }
    public int getHides() {
        return hides;
    }
    public void setHides(int hides) {
        this.hides = hides;
    }
    public int getLevelnums() {
        return levelnums;
    }
    public void setLevelnums(int levelnums) {
        this.levelnums = levelnums;
    }
    public String getHasInsert() {
        return hasInsert;
    }
    public void setHasInsert(String hasInsert) {
        this.hasInsert = hasInsert;
    }
    public String getNewNodes() {
        return newNodes;
    }
    public void setNewNodes(String newNodes) {
        this.newNodes = newNodes;
    }
    public String getMaxMenus() {
        return maxMenus;
    }
    public void setMaxMenus(String maxMenus) {
        this.maxMenus = maxMenus;
    }

}