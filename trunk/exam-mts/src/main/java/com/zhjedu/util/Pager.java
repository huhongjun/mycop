package com.zhjedu.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class Pager
    implements Tag {

  private String url;
  private int pageCount;
  private int page;
  private int totalrecord;
  private int intPageSize;
  private String jsName;
  private String formName;
  private String pageVar;
  private PageContext pageContext;
  private Tag parent;
  public Pager() {}

  public void setUrl(String s) {
    this.url = s;
  }

  public String getUrl() {
    return this.url;
  }

  public void setPageCount(int s) {
    this.pageCount = s;
  }

  public int getPageCount() {
    return this.pageCount;
  }

  public void setPage(int s) {
    this.page = s;
  }

  public int getPage() {
    return this.page;
  }

  public void setTotalrecord(int s) {
    this.totalrecord = s;
  }

  public int getTotalrecord() {
    return this.totalrecord;
  }

  public void setJsName(String s) {
    this.jsName = s;
  }

  public String getJsName() {
    return this.jsName;
  }

  public void setFormName(String s) {
    this.formName = s;
  }

  public String getFormName() {
    return this.formName;
  }

  public void setPageVar(String s) {
    this.pageVar = s;
  }

  public String getPageVar() {
    return this.pageVar;
  }

  public int doStartTag() throws JspException {
    return Tag.EVAL_PAGE;
  }

  public int doEndTag() throws JspException {
    boolean bNeedJs = false;
    StringBuffer stringbuffer = new StringBuffer("");
    jsName = "Js_TagLib_Pager";
    bNeedJs = true;
    stringbuffer.append("每页" + intPageSize + "条/共"+totalrecord+"条 第" + page + "/" + pageCount + "页 ");
    if (page > 1) { //当前页码大于第一页；
      stringbuffer.append("<a href=\"javascript:" + jsName +
                          "(1)\" class=\"c1\">首页</a> <a href=\"javascript:" + jsName + "(" +
                          (page - 1) + ")\" class=\"c1\">上一页</a>");
    }
    else {
      stringbuffer.append("首页 上一页");
    }
    if (page < pageCount) { //当前页码小于所有页；
      stringbuffer.append("&nbsp;<a href=\"javascript:" + jsName + "(" + (page + 1) +
                          ")\" class=\"c1\">下一页</a> <a href=\"javascript:" + jsName + "(" +
                          pageCount + ")\" class=\"c1\">末页</a>");
    }
    else {
      stringbuffer.append("&nbsp;下一页 末页");
    }
    if (bNeedJs) { //需要输出JavaScript函数

      if (url == null || "".equals(url) || "null".equals(url)) {
        throw new JspException("JSP Error: No Action");
      }
      stringbuffer.append("\n");
      stringbuffer.append("<Script language=\"javascript\">\n");
      stringbuffer.append("function " + jsName + "(strPage){\n");

      if (url.indexOf("?") == -1) {
        url += "?" + pageVar + "=";
      }
      else {
        url += "&" + pageVar + "=";
      }
      url += "\"+strPage"; 
      stringbuffer.append("document." + formName + ".action=\"" + url + ";\n");
      //stringbuffer.append("alert("+formName+".action);\n");
      stringbuffer.append("document." + formName + ".submit();\n");
      stringbuffer.append("}\n");
      stringbuffer.append("</script>\n");
    }

    try {
      pageContext.getOut().write(stringbuffer.toString());
    }
    catch (IOException ioexception) {
      throw new JspException("IO Error: " + ioexception.getMessage());
    }
    return 6;
  }

  public void release() {

  }

  public void setPageContext(PageContext pagecontext) {
    pageContext = pagecontext;
  }

  public void setParent(Tag tag) {
    parent = tag;
  }

  public Tag getParent() {
    return parent;
  }

public int getIntPageSize() {
	return intPageSize;
}

public void setIntPageSize(int intPageSize) {
	this.intPageSize = intPageSize;
}

//  private String prepareString(String s) {
//    StringBuffer stringbuffer = new StringBuffer("");
//    for (int i = 0; i < s.length(); i++) {
//      char c;
//      if ( (c = s.charAt(i)) == '\'') {
//        stringbuffer.append("\\'");
//      }
//      else
//      if (c == '"') {
//        stringbuffer.append("\\\"");
//      }
//      else {
//        stringbuffer.append(c);
//      }
//    }
//
//    return stringbuffer.toString();
//  }

}