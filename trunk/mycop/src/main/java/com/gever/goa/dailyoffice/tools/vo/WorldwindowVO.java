package com.gever.goa.dailyoffice.tools.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: 中的vo对象</p>
 * <p>Description: 是do_world_window的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class WorldwindowVO extends BaseVO implements VOInterface {
    public WorldwindowVO(){
    }
    private String old_title="";
    private String old_info_type="";
    private String title = ""; // TITLE
    private String info_type = ""; // INFO_TYPE
    private String child_type = ""; // CHILD_TYPE
    private String url = ""; // URL
    private static final int title_col = 0; // TITLE相对应的列数
    private static final int info_type_col = 1; // INFO_TYPE相对应的列数
    private static final int child_type_col = 2; // CHILD_TYPE相对应的列数
    private static final int url_col = 3; // URL相对应的列数
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getInfo_type() {
        return this.info_type;
    }
    public void setInfo_type(String info_type) {
        this.info_type = info_type;
    }
    public String getChild_type() {
        return this.child_type;
    }
    public void setChild_type(String child_type) {
        this.child_type = child_type;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getValue(String name) {
        if ("title".equalsIgnoreCase(name) == true) {
             return  this.title;
        } else if ("info_type".equalsIgnoreCase(name) == true) {
             return  this.info_type;
        } else if ("child_type".equalsIgnoreCase(name) == true) {
             return  this.child_type;
        } else if ("url".equalsIgnoreCase(name) == true) {
             return  this.url;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("title".equalsIgnoreCase(name) == true) {
             this.title = value ;
        } else if ("info_type".equalsIgnoreCase(name) == true) {
             this.info_type = value ;
        } else if ("child_type".equalsIgnoreCase(name) == true) {
             this.child_type = value ;
        } else if ("url".equalsIgnoreCase(name) == true) {
             this.url = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();

        return  "String";
    }
    public String getTableName() {
        return "do_world_window";
    }
    public String getPkFields() {
        return "title,info_type,";
    }
    public String getModifyFields() {
        return "child_type,url,";
    }
    public String getAllFields() {
        return "title,info_type,child_type,url,";
    }
    public void setValues(String[] values) {
        this.title = values[title_col];
        this.info_type = values[info_type_col];
        this.child_type = values[child_type_col];
        this.url = values[url_col];
    }
    public void setOtherProperty(String[] values) {
      this.old_info_type=this.info_type;
      this.old_title=this.title;
    }
    public String getOld_info_type(){
      return this.old_info_type;
    }
    public String getOld_title(){
      return this.old_title;
    }
}
