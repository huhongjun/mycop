package com.gever.goa.web.taglib;

import java.net.MalformedURLException;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.BaseHandlerTag;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

import com.gever.sysman.privilege.util.PermissionUtil;
import com.gever.util.StringUtils;
import com.gever.util.log.Log;

/**
 * <p>Title: 链接扩展标签</p>
 * <p>Description:包括判断权限 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class LinkTag extends BaseHandlerTag {
    private Log log = Log.getInstance(LinkTag.class);
    public LinkTag() {
        text = null;
        anchor = null;
        forward = null;
        href = null;
        linkName = null;
        name = null;
        page = null;
        paramId = null;
        paramName = null;
        paramProperty = null;
        paramScope = null;
        property = null;
        scope = null;
        target = null;
        transaction = false;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamProperty() {
        return paramProperty;
    }

    public void setParamProperty(String paramProperty) {
        this.paramProperty = paramProperty;
    }

    public String getParamScope() {
        return paramScope;
    }

    public void setParamScope(String paramScope) {
        this.paramScope = paramScope;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean getTransaction() {
        return transaction;
    }

    public void setTransaction(boolean transaction) {
        this.transaction = transaction;
    }

    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        if (linkName != null) {
            StringBuffer results = new StringBuffer("<a name=\"");
            results.append(linkName);
            results.append("\">");
            ResponseUtils.write(super.pageContext, results.toString());
            return 2;
        }

        java.util.Map params = RequestUtils.computeParameters(super.pageContext,
            paramId, paramName, paramProperty, paramScope, name, property,
            scope, transaction);
        String url = null;
        try {
            url = RequestUtils.computeURL(super.pageContext, forward, href,
                                          page, params, anchor, false);
        } catch (MalformedURLException e) {
            RequestUtils.saveException(super.pageContext, e);
            throw new JspException(messages.getMessage("rewrite.url",
                e.toString()));
        }

        StringBuffer results = new StringBuffer(10);
        StringTokenizer stk = new StringTokenizer(this.operation, ".");
        boolean bIsNull = false;
        String optcode = "";
        String rescode = "";
        int next = 0;
        while (stk.hasMoreTokens()) {
            if (next == 0)
                rescode = stk.nextToken();
            else
                optcode = stk.nextToken();
            ++next;
        }

        //log.showLog("----tag--optcode----" + optcode + "-rescode---" + rescode);
        if (StringUtils.isNull(optcode) || StringUtils.isNull(rescode)) {
            bIsNull = true;
            log.showLog("----tag--optcode----" + optcode + "-rescode---" +
                               rescode);
            // return (SKIP_BODY);
        }
        if (PermissionUtil.checkPermissionByCode(session, optcode,
                                                 rescode) || bIsNull) {

            haveAcl = true;
        }

        if (haveAcl == true){
            results.append("<a href=\"");
            results.append(url);
            results.append("\"");
            if (target != null) {
                results.append(" target=\"");
                results.append(target);
                results.append("\"");
            }
            if (!StringUtils.isNull(this.border)) {
                results.append(" border=\"");
                results.append(border);
                results.append("\"");
            }

            results.append(prepareStyles());
            results.append(prepareEventHandlers());
            results.append(">");
        } else {
            results.append("<span class=\"f12\">");
        }

        ResponseUtils.write(super.pageContext, results.toString());
        text = null;
        return 2;
    }

    public int doAfterBody() throws JspException {
        if (super.bodyContent != null) {
            String value = super.bodyContent.getString().trim();
            if (value.length() > 0)
                text = value;
        }
        return 0;
    }

    public int doEndTag() throws JspException {
        StringBuffer results = new StringBuffer();
        if (text != null)
            results.append(text);
        if (haveAcl == true){
            results.append("</a>");
        } else {
            results.append("</span>");
        }
        ResponseUtils.write(super.pageContext, results.toString());
        return 6;
    }

    public void release() {
        super.release();
        anchor = null;
        forward = null;
        href = null;
        linkName = null;
        name = null;
        page = null;
        paramId = null;
        paramName = null;
        paramProperty = null;
        paramScope = null;
        property = null;
        scope = null;
        target = null;
        text = null;
        transaction = false;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    public String getBorder() {
        return border;
    }
    public void setBorder(String border) {
        this.border = border;
    }

    protected String text;
    protected String anchor;
    protected String forward;
    protected String href;
    protected String linkName;
    protected static MessageResources messages = MessageResources.
        getMessageResources("org.apache.struts.taglib.html.LocalStrings");
    protected String name;
    protected String page;
    protected String paramId;
    protected String paramName;
    protected String paramProperty;
    protected String paramScope;
    protected String property;
    protected String scope;
    protected String target;
    protected boolean transaction;
    protected String operation = "";
    protected String display = "true";
    private boolean haveAcl = false;
    private String border;


}