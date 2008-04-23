package com.gever.sysman.privilege.webapp.taglib;

import com.gever.sysman.privilege.util.Constants;
import com.gever.sysman.privilege.util.PermissionUtil;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;


/**
 * <p>权限标签,通过给定的action和resource,在HttpSession中查找该用户是否有该操作权限</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @version 1.0
 */
public class PermissionTag extends TagSupport {
    private static String ERROR_PAGE = "errors.jsp";
    private static String LOGIN_PAGE = "login.jsp";
    private String action = "";
    private String resource = "";
  private String resourcecode;

    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        JspWriter out = pageContext.getOut();
        ServletRequest request = pageContext.getRequest();

        try {
            String userStr = (String) session.getAttribute(Constants.USERID);

            if (userStr == null) {
                //setError(request,"超时或未登陆,请重新登陆!");
                ((HttpServletResponse) pageContext.getResponse()).sendRedirect(LOGIN_PAGE);
            } else {
                String userId = userStr.toString();

                if (!PermissionUtil.checkPermissionById(session,  resource)&&!PermissionUtil.checkPermissionByCode(session, resourcecode)) {
                    //setError(request,"sorry,你不能执行该操作!");
                    pageContext.forward(ERROR_PAGE);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();

            try {
                pageContext.forward(ERROR_PAGE);
            } catch (Exception e) {
            }
        } catch (ServletException e) {
            e.printStackTrace();

            try {
                pageContext.forward(ERROR_PAGE);
            } catch (Exception ee) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (EVAL_BODY_INCLUDE);
    }

    public int doEndTag() {
        return (EVAL_PAGE);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setError(ServletRequest request, String msg) {
        //error.setMessage(msg);
        //request.setAttribute("Error",error);
    }
  public String getResourcecode() {
    return resourcecode;
  }
  public void setResourcecode(String resourcecode) {
    this.resourcecode = resourcecode;
  }
}
