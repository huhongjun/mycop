package com.gever.goa.menselect.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ÌìÐÐÈí¼þ</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MenSelectForm extends BaseForm {
    private String initJavaScriptArray;

    private static String beginScript = "	<script language=javascript>\n"
        + "	<!--\n";
    private static String patternRelate = "	var pattern = null;\n"
        + "     function setPattern(patternStr) {\n"
        + "         pattern = new RegExp(patternStr);\n"
        + "     }\n";
    private static String userObject = "	function User(name,id,dept,job) {\n"
        + "        this.name = name;\n"
        + "        this.id = id;\n"
        + "        this.dept = dept;\n"
        + "        this.job = job;\n"
        + "        this.seperator = ',';\n"
        + "        this.isRight = false;\n"
        + "        function compare_by_type(type) {\n"
        + "            switch (type) {\n"
        + "            //case 'name':\n"
        + "            //    return pattern.test(this.name);\n"
        + "              case 'dept':\n"
        +
        "                  return pattern.test(this.dept + this.seperator);\n"
        + "              case 'job':\n"
        + "                  return pattern.test(this.job + this.seperator);\n"
        + "              default:\n"
        + "                  return pattern.test(this.name);\n"
        + "            }\n"
        + "       }\n"
        + "       this.isMatch = compare_by_type;\n"
        + "    }\n";

    private static String endScript = "	//-->\n"
        + "	</script>\n";
    private String initUserInfoArray;
    private String bmSelectList;
    private String zwSelectList;
    private String ID;
    private String name;
    private String userIds;
    private String addressSelectList;
    private String cardSelect;

    public MenSelectForm() {
    }

    public String getInitJavaScriptArray() {
        return initJavaScriptArray;
    }

    public void setInitJavaScriptArray(String initJavaScriptArray) {
        this.initJavaScriptArray = initJavaScriptArray;
    }
    public String getBeginScript() {
        return beginScript;
    }
    public String getEndScript() {
        return endScript;
    }
    public String getPatternRelate() {
        return patternRelate;
    }
    public String getUserObject() {
        return userObject;
    }
    public String getInitUserInfoArray() {
        return initUserInfoArray;
    }
    public void setInitUserInfoArray(String initUserInfoArray) {
        this.initUserInfoArray = initUserInfoArray;
    }
    public String getBmSelectList() {
        return bmSelectList;
    }
    public void setBmSelectList(String bmSelectList) {
        this.bmSelectList = bmSelectList;
    }
    public String getZwSelectList() {
        return zwSelectList;
    }
    public void setZwSelectList(String zwSelectList) {
        this.zwSelectList = zwSelectList;
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserIds() {
        return userIds;
    }
    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
  public String getAddressSelectList() {
    return addressSelectList;
  }
  public void setAddressSelectList(String addressSelectList) {
    this.addressSelectList = addressSelectList;
  }
    public String getCardSelect() {
        return cardSelect;
    }
    public void setCardSelect(String cardSelect) {
        this.cardSelect = cardSelect;
    }

}
