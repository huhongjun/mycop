package com.gever.goa.dailyoffice.message.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MessageForm
    extends BaseForm {
  private String flagQuery; //��ѯ�Ķ���־
  private String contentQuery; //��ѯ����
  private String curNodeid; //������ȡ��ǰ�����������ڵ�ID
   private String nodeid; //�����ж��Ƿ��Ǳ�־
 private String web_flag;
  //private String receivername; //������ȡ������������
//  private String receiverid; //������ȡ������ID
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
