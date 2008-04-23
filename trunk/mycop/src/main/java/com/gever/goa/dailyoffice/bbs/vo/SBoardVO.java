package com.gever.goa.dailyoffice.bbs.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: SBoard中的vo对象</p>
 * <p>Description: 是DO_BBS_SBOARD的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class SBoardVO extends BaseVO implements VOInterface {
    public SBoardVO(){
    }
    private String serial_num = ""; // SERIAL_NUM
    private String tboard_serial = ""; // TBOARD_SERIAL
    private String sboard_name = ""; // SBOARD_NAME
    private String sboard_master = ""; // SBOARD_MASTER
    private String icon_file = ""; // ICON_FILE
    private String sboard_note = ""; // SBOARD_NOTE
    private String sboard_state = ""; // SBOARD_STATE
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int tboard_serial_col = 1; // TBOARD_SERIAL相对应的列数
    private static final int sboard_name_col = 2; // SBOARD_NAME相对应的列数
    private static final int sboard_master_col = 3; // SBOARD_MASTER相对应的列数
    private static final int icon_file_col = 4; // ICON_FILE相对应的列数
    private static final int sboard_note_col = 5; // SBOARD_NOTE相对应的列数
    private static final int sboard_state_col = 6; // SBOARD_STATE相对应的列数
    public String getSerial_num() {
        return this.serial_num;
    }
    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }
    public String getTboard_serial() {
        return this.tboard_serial;
    }
    public void setTboard_serial(String tboard_serial) {
        this.tboard_serial = tboard_serial;
    }
    public String getSboard_name() {
        return this.sboard_name;
    }
    public void setSboard_name(String sboard_name) {
        this.sboard_name = sboard_name;
    }
    public String getSboard_master() {
        return this.sboard_master;
    }
    public void setSboard_master(String sboard_master) {
        this.sboard_master = sboard_master;
    }
    public String getIcon_file() {
        return this.icon_file;
    }
    public void setIcon_file(String icon_file) {
        this.icon_file = icon_file;
    }
    public String getSboard_note() {
        return this.sboard_note;
    }
    public void setSboard_note(String sboard_note) {
        this.sboard_note = sboard_note;
    }
    public String getSboard_state() {
        return this.sboard_state;
    }
    public void setSboard_state(String sboard_state) {
        this.sboard_state = sboard_state;
    }
    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             return  this.serial_num;
        } else if ("tboard_serial".equalsIgnoreCase(name) == true) {
             return  this.tboard_serial;
        } else if ("sboard_name".equalsIgnoreCase(name) == true) {
             return  this.sboard_name;
        } else if ("sboard_master".equalsIgnoreCase(name) == true) {
             return  this.sboard_master;
        } else if ("icon_file".equalsIgnoreCase(name) == true) {
             return  this.icon_file;
        } else if ("sboard_note".equalsIgnoreCase(name) == true) {
             return  this.sboard_note;
        } else if ("sboard_state".equalsIgnoreCase(name) == true) {
             return  this.sboard_state;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             this.serial_num = value ;
        } else if ("tboard_serial".equalsIgnoreCase(name) == true) {
             this.tboard_serial = value ;
        } else if ("sboard_name".equalsIgnoreCase(name) == true) {
             this.sboard_name = value ;
        } else if ("sboard_master".equalsIgnoreCase(name) == true) {
             this.sboard_master = value ;
        } else if ("icon_file".equalsIgnoreCase(name) == true) {
             this.icon_file = value ;
        } else if ("sboard_note".equalsIgnoreCase(name) == true) {
             this.sboard_note = value ;
        } else if ("sboard_state".equalsIgnoreCase(name) == true) {
             this.sboard_state = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("sboard_state".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "DO_BBS_SBoard";
    }
    public String getPkFields() {
        return "serial_num,";
    }
    public String getModifyFields() {
        return "tboard_serial,sboard_name,sboard_master,icon_file,sboard_note,sboard_state,";
    }
    public String getAllFields() {
        return "serial_num,tboard_serial,sboard_name,sboard_master,icon_file,sboard_note,sboard_state,";
    }
    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.tboard_serial = values[tboard_serial_col];
        this.sboard_name = values[sboard_name_col];
        this.sboard_master = values[sboard_master_col];
        this.icon_file = values[icon_file_col];
        this.sboard_note = values[sboard_note_col];
        this.sboard_state = values[sboard_state_col];
    }
    public void setOtherProperty(String[] values) {
    }
}
