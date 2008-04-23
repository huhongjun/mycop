package com.gever.sysman.privilege.webapp.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import com.gever.sysman.privilege.util.Constants;
import com.gever.sysman.privilege.util.PermissionUtil;

public class PermissionImgButton
    extends org.apache.struts.taglib.html.ImageTag {
  private String href = "";
  private String target = "";
  private String resid = "";
  private String optid = "";
  private boolean display = true;
  private static String ERROR_PAGE = "/jsp/errors.jsp";
  private static String LOGIN_PAGE = "login.jsp";
  private String rescode;
  private String optcode;



  public int doStartTag() throws JspException {
    HttpSession session = pageContext.getSession();
    JspWriter out = pageContext.getOut();
    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
    HttpServletResponse response =
        (HttpServletResponse) pageContext.getResponse();
    ActionErrors errors = new ActionErrors();
    StringBuffer results = new StringBuffer();
    String tmp="";

    try {
      String userStr = (String) session.getAttribute(Constants.USERID);

      if ( (userStr == null) || ("".equals(userStr))) {
        errors.add(ActionErrors.GLOBAL_ERROR,
                   new ActionError("privilege.permission.invalid"));
        saveErrors(request, errors);
        pageContext.forward(ERROR_PAGE);
      }
      else {
        String userId = userStr.toString();

        if (PermissionUtil.checkPermissionById(session, optid, resid) ||
            PermissionUtil.checkPermissionByCode(session, optcode, rescode)) {

          results.append("<a ");
          if (href != null) {
              results.append(" href=\"");
              results.append(request.getContextPath()+href);
              results.append("\"");
          }
          if (target != null) {
              results.append(" target=\"");
              results.append(target);
              results.append("\"");
          }
          results.append(">");

          results.append("<img  name=\"");
          results.append(property);
          results.append("\"");
          tmp = src();
          if (tmp != null) {
              results.append(" src=\"");
              results.append(request.getContextPath()+src);
              results.append("\"");
          }
          if (this.getAlt() != null) {
              results.append(" alt=\"");
              results.append(this.getAlt());
              results.append("\"");
          }
          if (border != null) {
              results.append(" border=\"");
              results.append(border);
              results.append("\"");
          }else{
            results.append(" border=\"");
            results.append("0");
            results.append("\"");
          }
          if (value != null) {
              results.append(" value=\"");
              results.append(value);
              results.append("\"");
          }
          if (accesskey != null) {
              results.append(" accesskey=\"");
              results.append(accesskey);
              results.append("\"");
          }
          if (tabindex != null) {
              results.append(" tabindex=\"");
              results.append(tabindex);
              results.append("\"");
          }
          results.append(prepareEventHandlers());
          results.append(prepareStyles());
          results.append(">");
          results.append("</a>");

        }

      }
      out.print(results.toString());
    }
    catch (Exception e) {

    }

    return (EVAL_BODY_INCLUDE);
  }


  public int doEndTag() {
    return (EVAL_PAGE);
  }

  /**
   * @return
   */
  public String getOptid() {
    return optid;
  }

  /**
   * @return
   */
  public String getResid() {
    return resid;
  }

  /**
   * @param string
   */
  public void setOptid(String string) {
    optid = string;
  }

  /**
   * @param string
   */
  public void setResid(String string) {
    resid = string;
  }



  /**
   * @return
   */
  public String getValue() {
    return value;
  }

  /**
   * @param string
   */
  public void setValue(String string) {
    value = string;
  }



  protected void saveErrors(HttpServletRequest request, ActionErrors errors) {

    // Remove any error messages attribute if none are required
    if ( (errors == null) || errors.isEmpty()) {
      request.removeAttribute(Globals.ERROR_KEY);
      return;
    }

    // Save the error messages we need
    request.setAttribute(Globals.ERROR_KEY, errors);

  }

  public String getRescode() {
    return rescode;
  }

  public void setRescode(String rescode) {
    this.rescode = rescode;
  }

  public String getOptcode() {
    return optcode;
  }

  public void setOptcode(String optcode) {
    this.optcode = optcode;
  }
  public String getSrc() {
    return src;
  }
  public void setSrc(String src) {
    this.src = src;
  }
  public String getHref() {
    return href;
  }
  public void setHref(String href) {
    this.href = href;
  }
  public String getTarget() {
    return target;
  }
  public void setTarget(String target) {
    this.target = target;
  }
  public String getLOGIN_PAGE() {
    return LOGIN_PAGE;
  }
  public void setLOGIN_PAGE(String LOGIN_PAGE) {
    this.LOGIN_PAGE = LOGIN_PAGE;
  }
  public boolean isDisplay() {
    return display;
  }
  public void setDisplay(boolean display) {
    this.display = display;
  }

}