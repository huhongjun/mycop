package com.gever.goa.dailyoffice.workreport.action;

/**
 * <p>Title:���а칫�Զ���ƽ̨ </p>
 * <p>Description:�����㱨DoWorkReportģ���DAO IMPL  </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */
import com.gever.struts.form.BaseForm;

public class DoWorkReportForm extends BaseForm{
  private String cellcontent="";//���������
  private String cellName="";//�����ļ���
  private String listType="0";//�б����������
  private String templatepath = "";//��׼ģ���·��
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
