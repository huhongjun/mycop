package com.gever.goa.infoservice.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: 组织信息Form</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class IsInfoServeForm extends BaseForm {
    private String paraFlag; //导航能数
    private String paraSimpleQuery; //简单查询参数
    private String cellcontent; //报表内容
    //文本编辑器的内容(GW_ADD)
    private String word_content;
    private String html_content;
    //保存editor_type(GW_ADD)
    private String editortype;
    private String cellname; //临时报表路径名
    private String templatePath; //模板存放路径
    private String nodeID; //树结点ID
    //保存用户ID
    private String userId; 
    
    private String deptid;
    
    /**
     * @return Returns the deptid.
     */
    public String getDeptid() {
        return deptid;
    }
    /**
     * @param deptid The deptid to set.
     */
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }
    /**
     * @return Returns the userId.
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId The userId to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return Returns the html_content.
     */
    public String getHtml_content() {
        return html_content;
    }
    /**
     * @param html_content The html_content to set.
     */
    public void setHtml_content(String html_content) {
        this.html_content = html_content;
    }
    /**
     * @return Returns the wordcontent.
     */
    public String getWord_content() {
        return word_content;
    }

    /**
     * @param wordcontent The wordcontent to set.
     */
    public void setWord_content(String word_content) {
        this.word_content = word_content;
    }

    /**
     * @return Returns the editortype.
     */
    public String getEditortype() {
        return editortype;
    }

    /**
     * @param editortype The editortype to set.
     */
    public void setEditortype(String editortype) {
        this.editortype = editortype;
    }

    public IsInfoServeForm() {
    }

    public String getParaFlag() {
        return paraFlag;
    }

    public void setParaFlag(String paraFlag) {
        this.paraFlag = paraFlag;
    }

    public String getParaSimpleQuery() {
        return paraSimpleQuery;
    }

    public void setParaSimpleQuery(String paraSimpleQuery) {
        this.paraSimpleQuery = paraSimpleQuery;
    }

    public String getCellcontent() {
        return cellcontent;
    }

    public void setCellcontent(String cellcontent) {
        this.cellcontent = cellcontent;
    }

    public String getCellname() {
        return cellname;
    }

    public void setCellname(String cellname) {
        this.cellname = cellname;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

}