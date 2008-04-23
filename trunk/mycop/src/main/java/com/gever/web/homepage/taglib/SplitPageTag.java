package com.gever.web.homepage.taglib;

import java.io.*;
import java.util.Map;
import java.nio.charset.*;
import javax.servlet.jsp.*;

import com.gever.struts.pager.PageControl;
import com.gever.struts.pager.taglib.PagerTag;
import com.gever.web.util.URLBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.BodyTagSupport;

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
    private Map param=null;
    private String strParam="";

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
    private String page;
    private String scope;
    private String usepic = "true";
    private String context;
    private long prevPage;

    public SplitPageTag() {
    }

    public String getName() {
        return name;
    }

    public String getPage() {
        return page;
    }

    public void setName(String string) {
        name = string;
    }

    public void setParam(String p) {
        if(pageContext.getAttribute(p)!=null);
          param=(Map)pageContext.getAttribute(p);
        strParam="";
    }


    public void setPage(String string) {
        String contextPath = ( (HttpServletRequest) pageContext.getRequest()).
            getContextPath();
        page = contextPath + string;
    }


    /* （非 Javadoc）
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doStartTag() throws JspException {

      //  parent = (PagerTag)super.getParent();

        pc = (PageControl) pageContext.findAttribute(this.name);
        try {
            context = ( (HttpServletRequest) pageContext.getRequest()).
            getContextPath();
            pageContext.getRequest().setCharacterEncoding("GBK");
            pageContext.getResponse().setContentType("text/html; charset=GBK");
            pageContext.getOut().write(
                "<form name='page_form' action='' method='post'>");
            if(param!=null){
              java.util.Iterator iter = param.keySet().iterator();
              Object key;
              while (iter.hasNext()) {
                key = iter.next();
                strParam += String.valueOf(key) + "=" + String.valueOf(param.get(key)) +
                    "&";
              }
              if (strParam.length() != 0)
                strParam = strParam.substring(0, strParam.length() - 1);
              page = URLBuilder.addParameter(page, strParam);
            }
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
            sb.append("<div valign=\"bottom\">");
            sb.append(" 共").append(pc.getMaxRowCount()).append("条，分");
            sb.append(pc.getMaxPage()).append("页显示&nbsp;目前是第");
            sb.append(pc.getCurrentPage()).append("页&nbsp;");

            sb.append(" <input type=text size='4' maxlength='8' class='input2' ");
            sb.append(" onlytype='int' onfocus='checkTextBoxInput(event)' ");
            sb.append(" name='page' id = 'page' value='"+pc.getCurrentPage()+"'/>&nbsp页 ");
            sb.append("<input type=\"button\" name='onGo' id='onGo'");
            sb.append("  class=\"button\" value=\"Go\" onclick=\"");
            sb.append("if(document.page_form.page.value=='') ");
            sb.append("{ alert('页码必须输入');return false;} ");
            sb.append("else { document.page_form.action='");
            sb.append(getPage() + "';");
            sb.append("document.page_form.target='_self';");
            sb.append("document.page_form.submit();}\">");
            sb.append("</div> </td><td width=35%>");
            sb.append(" <div align=\"right\"><div valign=\"bottom\"> \n");
            sb.append(" <table width=\"90%\" border=\"0\" cellspacing=\"0\" ");
            sb.append(" cellpadding=\"0\">");

            out.print(sb.toString());
            //第一页
            out.write(" <tr align=\"center\" \n> <td width=\"25%\">" );
            if (pc.getCurrentPage() <= 1) {
                out.write(this.getPageInfo(this.FIRST_PAGE,false));
            } else {
                String url = URLBuilder.addParameter(getPage(), "page=1");
                out.write("<a href=" + url + ">");
                out.write(this.getPageInfo(this.FIRST_PAGE,true) + "</a>");
            }
            out.write(" </td><td width=\"25%\"> \n");
            //上一页
            if (pc.getCurrentPage() <= 1) {
                out.write(this.getPageInfo(this.PRE_PAGE,false));
            } else {
                String url = URLBuilder.addParameter(getPage(),
                    "page=" + prevPage);
                out.write("<a href=" + url + ">");
                out.write(this.getPageInfo(this.PRE_PAGE,true) + "</a>");
            }
            out.write(" </td><td width=\"25%\"> \n");
            //下一页
            if (pc.getCurrentPage() == pc.getMaxPage()) {
                out.write(this.getPageInfo(this.NEXT_PAGE,false));
            } else {
                String url = URLBuilder.addParameter(getPage(),
                    "page=" + nextPage);
                out.write("<a href=" + url + ">");
                out.write(getPageInfo(this.NEXT_PAGE,true) + "</a>");
            }
            out.write(" </td><td width=\"25%\"> \n");
            //最后一页
            if (pc.getCurrentPage() == pc.getMaxPage()) {
                out.write(this.getPageInfo(this.LAST_PAGE,false));
            } else {
                String url = URLBuilder.addParameter(getPage(),
                    "page=" + pc.getMaxPage());
                out.write("<a href=" + url + ">");
                out.write(getPageInfo(this.LAST_PAGE,true) + "</a>");
            }

            out.write(" </td></tr></table></td></tr></table>\n");
           pageContext.getOut().write("</form>");

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return EVAL_PAGE;
    }

    private String getPageInfo(int pageType,boolean isTrue) {
        if ("true".equals(this.usepic)){
            return this.getPageImg(pageType,isTrue);
        } else {
           return this.urlText[pageType];
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
        return usepic;
    }
    public void setUsepic(String usepic) {
        this.usepic = usepic;
    }

}