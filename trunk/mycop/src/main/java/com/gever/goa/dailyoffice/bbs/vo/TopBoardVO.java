package com.gever.goa.dailyoffice.bbs.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: TopBoard中的vo对象</p>
 * <p>Description: 是DO_BBS_TOPBOARD的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class TopBoardVO extends BaseVO implements VOInterface {
    public TopBoardVO(){
    }
    private String serial_num = ""; // SERIAL_NUM
    private String tboard_name = ""; // TBOARD_NAME
    private String icon_file = ""; // ICON_FILE
    private String tboard_note = ""; // TBOARD_NOTE
    private String tboard_state = ""; // TBOARD_STATE
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int tboard_name_col = 1; // TBOARD_NAME相对应的列数
    private static final int icon_file_col = 2; // ICON_FILE相对应的列数
    private static final int tboard_note_col = 3; // TBOARD_NOTE相对应的列数
    private static final int tboard_state_col = 4; // TBOARD_STATE相对应的列数
    public String getSerial_num() {
        return this.serial_num;
    }
    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }
    public String getTboard_name() {
        return this.tboard_name;
    }
    public void setTboard_name(String tboard_name) {
        this.tboard_name = tboard_name;
    }
    public String getIcon_file() {
        return this.icon_file;
    }
    public void setIcon_file(String icon_file) {
        this.icon_file = icon_file;
    }
    public String getTboard_note() {
        return this.tboard_note;
    }
    public void setTboard_note(String tboard_note) {
        this.tboard_note = tboard_note;
    }
    public String getTboard_state() {
        return this.tboard_state;
    }
    public void setTboard_state(String tboard_state) {
        this.tboard_state = tboard_state;
    }
    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             return  this.serial_num;
        } else if ("tboard_name".equalsIgnoreCase(name) == true) {
             return  this.tboard_name;
        } else if ("icon_file".equalsIgnoreCase(name) == true) {
             return  this.icon_file;
        } else if ("tboard_note".equalsIgnoreCase(name) == true) {
             return  this.tboard_note;
        } else if ("tboard_state".equalsIgnoreCase(name) == true) {
             return  this.tboard_state;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             this.serial_num = value ;
        } else if ("tboard_name".equalsIgnoreCase(name) == true) {
             this.tboard_name = value ;
        } else if ("icon_file".equalsIgnoreCase(name) == true) {
             this.icon_file = value ;
        } else if ("tboard_note".equalsIgnoreCase(name) == true) {
             this.tboard_note = value ;
        } else if ("tboard_state".equalsIgnoreCase(name) == true) {
             this.tboard_state = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("tboard_state".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "DO_BBS_TOPBOARD";
    }
    public String getPkFields() {
        return "serial_num,";
    }
    public String getModifyFields() {
        return "tboard_name,icon_file,tboard_note,tboard_state,";
    }
    public String getAllFields() {
        return "serial_num,tboard_name,icon_file,tboard_note,tboard_state,";
    }
    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.tboard_name = values[tboard_name_col];
        this.icon_file = values[icon_file_col];
        this.tboard_note = values[tboard_note_col];
        this.tboard_state = values[tboard_state_col];
    }
    public void setOtherProperty(String[] values) {
    }
}
