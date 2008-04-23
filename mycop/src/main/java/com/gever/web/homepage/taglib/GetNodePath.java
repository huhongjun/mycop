package com.gever.web.homepage.taglib;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 获取菜单路径扩展标签类 谌毅 创建于 2004-3-30
 * <p>Title: 获取菜单路径</p>
 * <p>Description: 获取菜单路径</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Gever</p>
 * @author Hu.Walker
 * @version 1.0
 */


public class GetNodePath extends TagSupport{
    public GetNodePath() {
    }
    private String bgColor = "#F4F9F2";
    private String nodePath = "我的桌面";
    public void setBgColor(String newBgColor){
        bgColor = newBgColor;
    }
    public void setNodePath(String newNodePath){
        nodePath = newNodePath;
    }
    public int doStartTag(){
        try{
            JspWriter out = pageContext.getOut();
            HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
            String context = request.getContextPath();
            out.println("<script language='javascript' type='text/JavaScript'>");
            out.println("function getNodePathName()");
            out.println("{");
            out.println("var nodePathName = \"\";");
            out.println("try{");
            out.println("  nodePathName = parent.document.forms[0].nodePathName.value;");
            out.println("} catch (e){");
            out.println("  try{");
            out.println("    nodePathName = parent.parent.document.forms[0].nodePathName.value;");
            out.println("  } catch (e){");
            out.println("    nodePathName = \"\";");
            out.println("  }");
            out.println("}");


            out.println("document.write(\"<table width='98%' height='25' border='0' align='center' cellpadding='0' cellspacing='0'>\")");
            out.println("document.write(\"<tr width='100%'>\")");
            out.println("document.write(\"<td width='98%' align='left' valign='middle' class='f12'>\")");
            //out.println("document.write('当前路径：'+nodePathName)");
            out.println("document.write(\"<img src='" +context+"/images/ico_dqwz.gif' align='absmiddle'>\")");
            out.println("document.write(\"\"+nodePathName)");
            out.println("document.write(\"</td>\")");
            out.println("document.write(\"</tr>\")");
            out.println("document.write(\"</table>\")");
            out.println("}");
            out.println("try{");
            out.println("getNodePathName();");
            out.println("document.body.onmousedown=function(){");
            out.println("try{");
            out.println("if(navigator.appName == 'Netscape'){window.parent.window.parent.hideAllMenu();}");
            out.println("else{parent.hideAllMenu();}");
            out.println("}catch(e){}}");
            out.println("}catch(e){}");

            out.println("</script>");

            //out.println("<table width='90%' height=50 border='0' align='center' cellpadding='0' cellspacing='2'>");
            //out.println("<tr>");
            //out.println("<td width='57%' align='left' valign='middle' class='t16'>");
            //out.println("<img src='../images/ico_dqwz.gif' width='28' height='25'>");
            //out.println("当前路径："+nodePath);
            //out.println("</td>");
            //out.println("</tr>");
            //out.println("</table>");
        }catch(Exception e){
        }
        return(EVAL_BODY_INCLUDE);//将标签中的内容加进来
    }
    public int doEndTag(){
        try{
            JspWriter out = pageContext.getOut();
        }catch(Exception e){
        }
        return(EVAL_PAGE);//将标签中的内容加进来

    }
}










