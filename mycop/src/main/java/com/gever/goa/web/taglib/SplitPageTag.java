package com.gever.goa.web.taglib;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.struts.util.RequestUtils;

import com.gever.struts.pager.PageControl;
import com.gever.struts.pager.taglib.PagerTag;

/**
 * <p>Title: 封装所有分页标签</p>
 * <p>Description: 封装所有分页标签</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class SplitPageTag extends BodyTagSupport {
    private String text;
    private long nextPage;
    private PagerTag parent;
    private PageControl pc;

    private String[] noActivePic = {"/images/First_no.gif",
        "/images/Previous_no.gif", "/images/Next_no.gif",
        "/images/Last_no.gif"};

    private String[] activePic = {"/images/First.gif",
        "/images/Previous.gif", "/images/Next.gif",
        "/images/Last.gif"};
    private String[] urlText = {"第一页","上一页","下一页","最后一页" };

    private static final int FIRST_PAGE = 0;
    private static final int PRE_PAGE = 1;
    private static final int NEXT_PAGE = 2;
    private static final int LAST_PAGE = 3;

    private String name;
    private String scope;
    private String usepic = "true";
    private String context;
    private long prevPage;
    private String property;

    public SplitPageTag() {
    }

    public String getName() {
        return name;
    }

    public String getSubmit() {

        StringBuffer sb = new StringBuffer();
        //sb.append("javascript:this.document.").append(this.name).append(".submit() ");
        sb.append("javascript:doAction('doPage')");
        return sb.toString();
    }

    public void setName(String string) {
        name = string;
    }

    /* （非 Javadoc）
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doStartTag() throws JspException {
        //System.out.println("---------------tag start-----" + this.property);
        Object p =   RequestUtils.lookup(pageContext,this.name,this.property,null);
        //System.out.println(" classname=" + p);
        pc =(PageControl) RequestUtils.lookup(pageContext,this.name,this.property,null);
        //pc = (PageControl) pageContext.findAttribute(this.name+"."+this.property);
        try {
            context = ( (HttpServletRequest) pageContext.getRequest()).
            getContextPath();
            pageContext.getRequest().setCharacterEncoding("GBK");
            pageContext.getResponse().setContentType("text/html; charset=GBK");

        } catch (UnsupportedCharsetException e) {
            throw new JspException(e.getMessage());
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        // return EVAL_BODY_INCLUDE;


        long currentPage = pc.getCurrentPage();
        //下一页
        nextPage = currentPage >= pc.getMaxPage() ? pc.getMaxPage() :
            currentPage + 1;
        //上一页
        prevPage = currentPage <= 1 ? 1 : currentPage - 1;

        return EVAL_PAGE;
    }

    /* （非 Javadoc）
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */
    public int doEndTag() throws JspException {
        try {
           // JspWriter out = bodyContent.getEnclosingWriter();
            JspWriter out = pageContext.getOut();
            StringBuffer sb = new StringBuffer();
            sb.append(" <table width=\"90%\" border=\"0\" ");
            sb.append(" align=\"center\" cellspacing=\"3\" cellpadding=\"2\" ");
            sb.append(" class=\"f12\"><tr><td width=\"65%\" align=\"center\"> ");
            sb.append("<div valign=\"bottom\"><nobr>");
            sb.append(" 共").append(pc.getMaxRowCount()).append("条，分");
            sb.append(pc.getMaxPage()).append("页显示&nbsp;目前是第");
            sb.append(pc.getCurrentPage()).append("页&nbsp;");

            sb.append(" <input type=hidden name='paction' value='doPage'> ");
            sb.append(" <input type=hidden name='page' value='1'> ");
            sb.append(" <input type=text size='4' maxlength='8' class='input2' ");
            sb.append(" onlytype='int' style=\"ime-mode:Disabled;\" onfocus='checkTextBoxInput()' ");
            sb.append(" name='pagenum' id = 'pagenum' value=''/>&nbsp页 ");
            sb.append("<input type=\"button\" name='onGo' id='onGo'");
            sb.append("  class=\"button\" value=\"Go\" onclick=\"");
            sb.append("javascript:if(document.").append(this.name);
            sb.append(".pagenum.value=='') ");
            sb.append("{ alert('页码必须输入');return false;} ");
            sb.append("else { ").append("document.").append(this.name).append(".page.value=");
            sb.append("document.").append(this.name).append(".pagenum.value;");
            sb.append(getSubmit() + ";}\"");
            sb.append("</nobr></div> </td><td width=35%>");
            sb.append(" <div align=\"right\"><div valign=\"bottom\"> \n");
            sb.append(" <table width=\"90%\" border=\"0\" cellspacing=\"0\" ");
            sb.append(" cellpadding=\"0\">");

            out.print(sb.toString());
            //第一页
            out.write(" <tr align=\"center\" \n> <td width=\"25%\">" );
            if (pc.getCurrentPage() <= 1) {
                out.write(this.getPageInfo(this.FIRST_PAGE,false));
            } else {
                out.write( getUrl(1l,getPageInfo(this.FIRST_PAGE,true)));
            }
            out.write(" </td><td width=\"25%\"> \n");

            //上一页
            if (pc.getCurrentPage() <= 1) {
                out.write(this.getPageInfo(this.PRE_PAGE,false));
            } else {
                out.write( getUrl(prevPage,getPageInfo(this.PRE_PAGE,true)));
            }
            out.write(" </td><td width=\"25%\"> \n");

            //下一页
            if (pc.getCurrentPage() == pc.getMaxPage()) {
                out.write(this.getPageInfo(this.NEXT_PAGE,false));
            } else {
                out.write( getUrl(nextPage,getPageInfo(this.NEXT_PAGE,true)));
            }
            out.write(" </td><td width=\"25%\"> \n");

            //最后一页
            if (pc.getCurrentPage() == pc.getMaxPage()) {
                out.write(this.getPageInfo(this.LAST_PAGE,false));
            } else {
                out.write( getUrl( pc.getMaxPage(),getPageInfo(this.LAST_PAGE,true)));
            }

            out.write(" </td></tr></table></td></tr></table>\n");

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return EVAL_PAGE;
    }

    private String getUrl(long number,String text){
        StringBuffer sb = new StringBuffer();

        sb.append(" <a href =\"javascript:doAction('doPage')\" ");
//        sb.append(this.name).append(".submit() ");
//
//        sb.append(" <a href =\"javascript:this.document.");
//        sb.append(this.name).append(".submit() ");

        sb.append("onclick=\"this.document.");
        sb.append(this.name);
        sb.append(".page.value=").append(number).append("\">");
        sb.append(text).append("</a> ");
        return sb.toString();
    }

    private String getPageInfo(int pageType,boolean isTrue) {
        if ("true".equals(this.usepic)){
            return this.getPageImg(pageType,isTrue);
        } else {
           return this.urlText[pageType];
        }
    }

    /**
     * 为url增加一个参数
     * @param url url
     * @param keyValue key,value对,形式为key=value
     * @return
     */
    public static String addParameter(String url, String keyValue) {
        if (url == null) {
            return null;
        }
        int index = url.indexOf("?");
        if (index < 0) {
            return url + "?" + keyValue;
        } else {
            return url + "&" + keyValue;
        }
    }

    private String getPageImg(int pageType, boolean isTrue) {
        String pageData = "";
        StringBuffer sb = new StringBuffer();
        sb.append("<img src=\"" + context);
        if (isTrue == true) {
            sb.append(activePic[pageType]);
        } else {
            sb.append(noActivePic[pageType]);
        }

        sb.append("\" alt=\"").append(urlText[pageType]).append("\"");
        if (pageType == this.FIRST_PAGE || pageType == this.LAST_PAGE)
            sb.append("width=\"18\"");
        else
            sb.append(" width=\"14\"");
        sb.append("height=\"13\" border=\"0\">");

        return sb.toString();
    }

    /* （非 Javadoc）
     * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
     */
    public int doAfterBody() throws JspException {
        text = bodyContent.getString();
        return SKIP_BODY;
    }
    public String getUsepic() {
        if (usepic == null || "".equals(usepic))
            return "true";
        return usepic;
    }
    public void setUsepic(String usepic) {
        this.usepic = usepic;
    }
    public String getProperty() {
        return property;
    }
    public void setProperty(String property) {
        this.property = property;
    }

}
