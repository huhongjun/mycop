package com.gever.web.menusetup.form;

import org.apache.struts.action.ActionForm;

import com.gever.web.homepage.vo.UserMenuVO;

import java.util.*;

/**
 * <p>Title: 菜单定制form类</p>
 * <p>Description: 菜单定制form类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class MenuSetupForm extends ActionForm {

    private String userID = "";    //用户ID号
    private String userName = "";  //用户名称
    private UserMenuVO vo = new UserMenuVO();   //
    private UserMenuVO pvo = new UserMenuVO();   //    上级菜单
    private List curNodeList = new ArrayList(); //当前节点数据
    private String nodeid = "";     //当前节点ID
    private String nodename = "";   //当前节点名称
    private String isFolder = "1";  //是否为目录
    private String parentid ="";    //父ID
    private String isRoot = "false";  //是否为根目录
    private int sum = 0;    //求和
    private List showMenu = new ArrayList();  //显示菜单数据
    private List hideMenu = new ArrayList();  //隐藏菜单数据
    private List selMenu  = new ArrayList();  //已选择菜单数据
    private List noSelMenu  = new ArrayList(); //可选择菜单数据
    private List homeData = new ArrayList();  //
    private String showData = "";       //隐藏菜单页面的显示数据
    private String hideData = "";       //隐藏菜单页面的隐藏数据
    private String actionFlag = "add";  //动作类型
    private String outOption = "";
    private int hides  = 0;
    private int levelnums = 0;
    private String hasInsert = "false";  //是否为根目录
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