package com.gever.web.homepage.taglib;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Hu.Walker
 * @version 1.0
 */
import javax.servlet.jsp.tagext.TagSupport;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import com.gever.util.StringUtils;

import java.io.*;

public class MenuTag extends TagSupport {
    public MenuTag() {
    }

    private final static String TABLEBEGIN =
        "<table cellspacing=\"0\" cellpadding=\"0\"  class=\"menu\" id=\"#id\">";
    private final static String TABLEEND = "</table>";
    private final static String TRBEGIN = "<tr class=\"#class\" havesub=\"#havesub\" index=\"#index\" menu=\"#menu\" menuid=\"#menuid\" icon=\"#icon\" text=\"#text\" attributes=\"#attributes\">";
    private final static String TREND = "</tr>";
    private final static String ICONTD =
        "<td class=\"icon\" style=\"display:#display\"><img style=\"display:#visibility\" src=\"#icon\">#space</td>";
    private final static String TEXTTD =
        "<td class=\"middle\" nowrap>#text</td>";
    private final static String RIGHTTD = "<td class=\"#class\">#text</td>";

    private String menuId;
    private String file;
    private String property;
    private javax.servlet.http.HttpSession session = null;
    private javax.servlet.ServletRequest request = null;
    private javax.servlet.jsp.JspWriter writer = null;

    public int doStartTag() throws javax.servlet.jsp.JspException {
        session = this.pageContext.getSession();
        request = this.pageContext.getRequest();
        writer = this.pageContext.getOut();
        try {
            if (this.property == null && this.file == null) {
                throw new javax.servlet.jsp.JspException(
                    "[MenuTag Err]property属性和file属性至少设置一个");
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = null;

            if (this.property != null) {

                String strXml = null;
                if (session.getAttribute(this.property) != null) {
                    if (session.getAttribute(this.property)instanceof Document) {
                        dom = (Document) session.getAttribute(this.property);
                    } else if (session.getAttribute(this.property)instanceof
                               String) {
                        strXml = (String) session.getAttribute(this.property);
                        if (strXml == null)
                            throw new javax.servlet.jsp.JspException(
                                "[MenuTag Err]找不到property相对应的值");

                        StringBufferInputStream is = new
                            StringBufferInputStream(StringUtils.toUTF8(strXml));

                        dom = builder.parse(is);
                    } else {
                        throw new javax.servlet.jsp.JspException(
                            "[MenuTag Err]property属性值不正确");
                    }
                }
                //request优先

                if (request.getAttribute(this.property) != null) {
                    if (request.getAttribute(this.property) != null) {
                        if (request.getAttribute(this.property)instanceof
                            Document) {
                            dom = (Document) request.getAttribute(this.property);
                        } else if (request.getAttribute(this.property)instanceof
                                   String) {
                            strXml = (String) request.getAttribute(this.
                                property);
                            if (strXml == null)
                                throw new javax.servlet.jsp.JspException(
                                    "[MenuTag Err]找不到property相对应的值");

                            StringBufferInputStream is = new
                                StringBufferInputStream(StringUtils.toUTF8(
                                strXml));
                            dom = builder.parse(is);
                        } else {
                            throw new javax.servlet.jsp.JspException(
                                "[MenuTag Err]property属性值不正确");
                        }
                    }
                }
            } else {
                dom = builder.parse(request.getRealPath("\\") + this.file);
            }
            parseMenu(dom);

        } catch (Exception e) {
            throw new javax.servlet.jsp.JspException(e.toString());
        }
        return EVAL_BODY_INCLUDE;
    }

    private void parseMenu(Document dom) throws javax.servlet.jsp.JspException {
        StringBuffer menuContent = new StringBuffer("");
        NodeList menuList = dom.getElementsByTagName("menu");
        int menuCount = menuList.getLength();
        Element menu = null;
        try {
            for (int i = 0; i < menuCount; i++) {
                menu = (Element) menuList.item(i);
                String id = menu.getAttribute("id");
                String showIcon = menu.getAttribute("showicon");
                if (id == null || "".equals(id)) {
                    throw new javax.servlet.jsp.JspException(
                        "[MenuTag Err]menu节点必须具有id属性");
                }
                menuContent.append(StringUtils.replace(TABLEBEGIN, "#id", id) +
                                   "\n");
                NodeList itemList = menu.getElementsByTagName("menuitem");
                int listCount = itemList.getLength();
                for (int j = 0; j < listCount; j++) {
                    Element menuItem = (Element) itemList.item(j);
                    String itemId = menuItem.getAttribute("id");
                    String icon = menuItem.getAttribute("icon");

                    String text = StringUtils.toGBK(menuItem.getAttribute("text"));
                    String sid = menuItem.getAttribute("sid");
                    boolean haveSub = (sid != null && !"".equals(sid));
                    String tmp = "";
                    tmp = StringUtils.replace(MenuTag.TRBEGIN, "#menuid", itemId);
                    if (haveSub) {
                        tmp = StringUtils.replace(tmp, "#havesub", "1");
                        tmp = StringUtils.replace(tmp, "#class", "sub");
                        tmp = StringUtils.replace(tmp, "#menu", sid);
                    } else {
                        tmp = StringUtils.replace(tmp, "#class", "");
                        tmp = StringUtils.replace(tmp, "#havesub", "0");
                        tmp = StringUtils.replace(tmp, "#menu", "");

                    }
                    tmp = StringUtils.replace(tmp, "#index", String.valueOf(j));
                    tmp = StringUtils.replace(tmp, "#menuid", id);
                    tmp = StringUtils.replace(tmp, "#icon", icon);
                    tmp = StringUtils.replace(tmp, "#text", text);
                    NodeList attrList = menuItem.getElementsByTagName(
                        "customattribute");
                    int attrCount = attrList.getLength();
                    String attrData = "";
                    for (int k = 0; k < attrCount; k++) {
                        Element attrItem = (Element) attrList.item(k);
                        String attrName = attrItem.getAttribute("name");
                        String attrValue = attrItem.getAttribute("value"); //需要过滤某些字符
                        if (! (attrName == null || "".equals(attrName) ||
                               attrValue == null)) {
                            attrData += attrName + "|*|" + attrValue + "|*|";
                        }
                    }
                    if (attrData != null && !"".equals(attrData)) {
                        attrData = attrData.substring(0, attrData.length() - 3);
                    }
                    tmp = StringUtils.replace(tmp, "#attributes", attrData);
                    menuContent.append(tmp + "\n");
                    //td
                    if (showIcon != null && !"".equals(showIcon)) {
                        tmp = StringUtils.replace(MenuTag.ICONTD, "#display",
                                                 "block");
                        tmp = StringUtils.replace(tmp, "#icon", icon);
                        if (icon != null && !"".equals(icon)) {
                            tmp = StringUtils.replace(tmp, "#visibility",
                                "block");
                            tmp = StringUtils.replace(tmp, "#space", "");
                        } else {
                            tmp = StringUtils.replace(tmp, "#visibility", "none");
                            tmp = StringUtils.replace(tmp, "#space", "&nbsp");
                        }
                    } else {
                        tmp = StringUtils.replace(MenuTag.ICONTD, "#display",
                                                 "none");
                        tmp = StringUtils.replace(tmp, "#icon", icon);
                        tmp = StringUtils.replace(tmp, "#space", "&nbsp");
                    }
                    menuContent.append(tmp + "\n");
                    menuContent.append(StringUtils.replace(MenuTag.TEXTTD,
                        "#text", text) + "\n");
                    if (haveSub) {
                        tmp = StringUtils.replace(MenuTag.RIGHTTD, "#class",
                                                 "more");
                        tmp = StringUtils.replace(tmp, "#text", "4");
                    } else {
                        tmp = StringUtils.replace(MenuTag.RIGHTTD, "#class",
                                                 "right");
                        tmp = StringUtils.replace(tmp, "#text", "&nbsp");
                    }
                    menuContent.append(tmp + "\n");
                    menuContent.append(MenuTag.TREND + "\n");
                }
                menuContent.append(MenuTag.TABLEEND + "\n");
                //完成一个菜单
            }
            writer.print(menuContent.toString());
        } catch (java.io.IOException e) {
            throw new javax.servlet.jsp.JspException(
                e.toString());
        }
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

}