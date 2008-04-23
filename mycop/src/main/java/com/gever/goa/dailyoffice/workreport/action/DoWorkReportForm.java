package com.gever.goa.dailyoffice.workreport.action;

/**
 * <p>Title:天行办公自动化平台 </p>
 * <p>Description:工作汇报DoWorkReport模块的DAO IMPL  </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
import com.gever.struts.form.BaseForm;

public class DoWorkReportForm extends BaseForm{
  private String cellcontent="";//华表的数据
  private String cellName="";//华表文件名
  private String listType="0";//列表浏览的类型
  private String templatepath = "";//标准模板的路径
  private String searchBeginTime="";
  private String searchEndTime="";
  public DoWorkReportForm() {
  }
  public String getCellcontent() {
    return cellcontent;
  }
  public String getListType() {
    return listType;
  }
  public void setListType(String listType) {
    this.listType = listType;
  }
  public void setCellcontent(String cellcontent) {
    this.cellcontent = cellcontent;
  }
  public String getCellName() {
    return cellName;
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

}
