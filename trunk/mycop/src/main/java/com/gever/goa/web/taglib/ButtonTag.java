package com.gever.goa.web.taglib;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.taglib.html.BaseHandlerTag;

import com.gever.struts.Constant;
import com.gever.sysman.privilege.util.PermissionUtil;
import com.gever.util.StringUtils;
import com.gever.util.log.Log;

/**
 * <p>Title: 普通按钮标签</p>
 * <p>Description: 包括判断权限</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class ButtonTag extends BaseHandlerTag {
    private static Log log = Log.getInstance(ButtonTag.class);

    protected String property = "submit";
    protected String value = "submit";
    protected String operation = "";
    protected String display = "true";
    protected String type = "button";
    private static String ERROR_PAGE = "/jsp/errors.jsp";
    private static String LOGIN_PAGE = "login.jsp";
    public ButtonTag() {
        super.setDisabled(false);
    }

    public int doStartTag() throws JspException {

        HttpSession session = pageContext.getSession();
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.
            getRequest();
        ActionErrors errors = new ActionErrors();
        StringBuffer results = new StringBuffer();

        try {

            String strUserID = (String) session.getAttribute(Constant.USER_ID);

            if (StringUtils.isNull(strUserID)) {
                errors.add(ActionErrors.GLOBAL_ERROR,
                           new ActionError("privilege.permission.invalid"));
                saveErrors(request, errors);
                pageContext.forward(ERROR_PAGE);
                return (SKIP_BODY);
            }

            String userId = strUserID;
            StringTokenizer stk = new StringTokenizer(this.operation, ".");
            int next = 0;
            String optcode = "";
            String rescode = "";
            while (stk.hasMoreTokens()) {
                if (next == 0)
                   rescode  = stk.nextToken();
                else
                    optcode = stk.nextToken();
                ++next;
            }
            boolean bIsNull = false;
            if (StringUtils.isNull(optcode) || StringUtils.isNull(rescode)){
                bIsNull = true;
               // return (SKIP_BODY);
            }
            //PermissionUtil.checkPermissionByCode
            if (PermissionUtil.checkPermissionByCode(session, optcode,
                rescode) || bIsNull == true) {

               // if (!this.getDisabled()) {
                    this.setDisabled(false);
               // }

            } else {
                errors.add(ActionErrors.GLOBAL_ERROR,
                           new ActionError("privilege.permission.invalid"));
                this.setDisabled(true);
            }

            if ("false".equals(this.display)){
                return (SKIP_BODY);
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
        } catch (Exception e) {

        }

        return (EVAL_BODY_INCLUDE);
    }

    public int doEndTag() {
        return (EVAL_PAGE);
    }

    /**
     * @return
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param string
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * @return
     */
    public String getDisplay() {
        return display;
    }

    /**
     * @param string
     */
    public void setDisplay(String display) {
        this.display = display;
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

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }


}