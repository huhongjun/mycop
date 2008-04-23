package com.gever.goa.dailyoffice.message.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MessageForm
    extends BaseForm {
  private String flagQuery; //查询阅读标志
  private String contentQuery; //查询内容
  private String curNodeid; //用来获取当前传过来的树节点ID
   private String nodeid; //用来判断是否是标志
 private String web_flag;
  //private String receivername; //用来存取接收人中文名
//  private String receiverid; //用来存取接收人ID
  private String username;
  public MessageForm() {
  }

  public String getFlagQuery() {
    return flagQuery;
  }

  public void setFlagQuery(String flagQuery) {
    this.flagQuery = flagQuery;
  }

  public String getContentQuery() {
    return contentQuery;
  }

  public void setContentQuery(String contentQuery) {
    this.contentQuery = contentQuery;
  }
/*
  public String getReceiverid() {
    return receiverid;
  }

  public String getReceivername() {
    return receivername;
  }

  public void setReceiverid(String receiverid) {
    this.receiverid = receiverid;
  }

  public void setReceivername(String receivername) {
    this.receivername = receivername;
  }
*/
  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }
  public String getCurNodeid() {
    return curNodeid;
  }
  public void setCurNodeid(String curNodeid) {
    this.curNodeid = curNodeid;
  }
  public String getNodeid() {
    return nodeid;
  }
  public void setNodeid(String nodeid) {
    this.nodeid = nodeid;
  }
  public String getWeb_flag() {
    return web_flag;
  }
  public void setWeb_flag(String web_flag) {
    this.web_flag = web_flag;
  }
  /*
  public String getIsEndFlag() {
    return isEndFlag;
  }
  public void setIsEndFlag(String isEndFlag) {
    this.isEndFlag = isEndFlag;
  }
*/
}
