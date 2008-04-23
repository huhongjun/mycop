package com.gever.goa.web.taglib;

import javax.servlet.jsp.JspException;

import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

import com.gever.util.StringUtils;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class BeanWriteTag extends org.apache.struts.taglib.bean.WriteTag {
    public BeanWriteTag() {
    } // 是否截断字符串

    protected boolean cut = false;

    public boolean getCut() {
        return (this.cut);
    }

    public void setCut(boolean cut) {
        this.cut = cut;
    }

    //最大长度
    protected int maxlen = 0;

    public int getMaxlen() {
        return (this.maxlen);
    }

    public void setMaxlen(int maxlen) {
        this.maxlen = maxlen;
    }

    /**
     * Process the start tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        // Look up the requested bean (if necessary)
        if (ignore) {
            if (RequestUtils.lookup(pageContext, name, scope) == null)
                return (SKIP_BODY); // Nothing to output
        }

        // Look up the requested property value
        Object value =
            RequestUtils.lookup(pageContext, name, property, scope);
        if (value == null)
            return (SKIP_BODY); // Nothing to output

        // Convert value to the String with some formatting
        String output = formatValue(value);
        String strValue= "";
        // Print this property value to our output writer, suitably filtered
        if (filter) {
            if (maxlen >0) {
                ResponseUtils.write(pageContext,
                                   StringUtils.subStr(StringUtils.filter(output), maxlen));
            } else {
                ResponseUtils.write(pageContext, StringUtils.filter(output));
            }
        } else {
            if (maxlen >0) {
                strValue =StringUtils.unFilter(subStr(output, maxlen));
                strValue = StringUtils.replace(strValue,"<script","< script");
               // StringUtils.replace(strValue," ",")
                ResponseUtils.write(pageContext, strValue);
            } else {
                 strValue =StringUtils.unFilter(output);
                strValue = StringUtils.replace(strValue,"<script","< script");
                ResponseUtils.write(pageContext, strValue);
            }
        }

        // Continue processing this page
        return (SKIP_BODY);

    }

    protected String subStr(String s, int len) {
        if (s == null || s.length() == 0 || len <= 0)
            return "";
        if (s.length() <= len)
            return s;
        return "<span title='" + s + "'>" + s.substring(0, len) + "..." +
            "</span>";
        // return s.substring(0, len) + "...";
    }

}