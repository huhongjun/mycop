/*
 * 创建日期 2004-7-4
 *
 */
package com.gever.sysman.privilege.webapp.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import com.gever.sysman.privilege.util.Constants;
import com.gever.sysman.privilege.util.PermissionUtil;
import org.apache.struts.taglib.html.BaseHandlerTag;

/**
 * @author Hu.Walker
 *
 */
public class PermissionButton extends BaseHandlerTag {
  private static final long serialVersionUID = 1L;
  protected String property = "submit";
  protected String value = "submit";
  protected String resid = "";
  protected String optid = "";
  protected String rescode;
  protected String optcode;
  protected String dis_type = "0";
  protected String type = "submit";
  private static String ERROR_PAGE = "/jsp/errors.jsp";
  private static String LOGIN_PAGE = "login.jsp";


  public int doStartTag() throws JspException {
    HttpSession session = pageContext.getSession();
    JspWriter out = pageContext.getOut();
    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
    ActionErrors errors = new ActionErrors();
    StringBuffer results = new StringBuffer();

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
            this.setDisabled(false);

        }
        else {
          errors.add(ActionErrors.GLOBAL_ERROR,
                     new ActionError("privilege.permission.invalid"));
            this.setDisabled(true);
        }
      }

      results.append("<input type=\"" + type + "\" name=\"");
      results.append(property);
      results.append("\"");
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
      if (this.getStyleClass() == null) {
        results.append(" class=\"");
        results.append("button");
        results.append("\"");
      }
      results.append(" value=\"");
      results.append(value);
      results.append("\"");

      results.append(prepareEventHandlers());
      results.append(prepareStyles());
      results.append(">");
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
  public String getDis_type() {
    return dis_type;
  }

  /**
   * @param string
   */
  public void setDis_type(String string) {
    dis_type = string;
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

  /**
   * @return
   */
  public String getType() {
    return type;
  }

  /**
   * @param string
   */
  public void setType(String string) {
    type = string;
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
  public String getProperty() {
    return property;
  }
  public void setProperty(String property) {
    this.property = property;
  }
}