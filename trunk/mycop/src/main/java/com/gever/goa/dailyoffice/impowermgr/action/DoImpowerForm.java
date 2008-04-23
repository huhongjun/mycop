package com.gever.goa.dailyoffice.impowermgr.action;

import com.gever.struts.form.BaseForm;

/**
* Title:授权管理Form类
* Description: 授权管理Form类
* Copyright: Copyright (c) 2004
* Company: 天行软件
* @author Hu.Walker
* @version 1.0
*/
public class DoImpowerForm extends BaseForm{
  private String cellcontent="";
  private String cellName="";
  private String templatepath = "";//标准模板的路径
  private String cellHtml="";
  private String sendflag = "0";
public DoImpowerForm() {
}
  public String getCellcontent() {
    return cellcontent;
  }
  public String getCellName() {
    return cellName;
  }
  public void setCellcontent(String cellcontent) {
    this.cellcontent = cellcontent;
  }
  public void setCellName(String cellName) {
    this.cellName = cellName;
  }
  public String getTemplatepath() {
    return templatepath;
  }
  public void setTemplatepath(String templatepath) {
    this.templatepath = templatepath;
  }
  public String getCellHtml() {
    return cellHtml;
  }
  public void setCellHtml(String cellHtml) {
    this.cellHtml = cellHtml;
  }
  public String getSendflag() {
    return sendflag;
  }
  public void setSendflag(String sendflag) {
    this.sendflag = sendflag;
  }
}

