package com.gever.goa.menselect.vo;

public class ConstCodeVO {
  // ����
  private String code;
  // ����
  private String content;
  //���ڵ����
  private String parentCode;
  //������
  private int codeLevel;

  public ConstCodeVO() {
  }
  public ConstCodeVO(String code, String content) {
      this.code = code;
      this.content = content;
  }
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
    public String getParentCode() {
        return parentCode;
    }
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
  public int getCodeLevel() {
    return codeLevel;
  }
  public void setCodeLevel(int codeLevel) {
    this.codeLevel = codeLevel;
  }

}
