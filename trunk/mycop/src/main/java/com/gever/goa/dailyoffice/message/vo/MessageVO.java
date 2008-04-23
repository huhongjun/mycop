package com.gever.goa.dailyoffice.message.vo;

import com.gever.util.StringUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: Message中的vo对象</p>
 * <p>Description: 是do_inner_msg的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class MessageVO extends BaseVO implements VOInterface {
    public MessageVO() {
    }

    private String serial_num = ""; // SERIAL_NUM
    private String user_code = ""; // USER_CODE
    private String send_time = ""; // SEND_TIME
    private String content = ""; // CONTENT
    private String receiver = ""; // RECEIVER
    private String read_flag = ""; // READ_FLAG
    private String read_time = ""; // READ_TIME
    private String web_url = ""; // WEB_URL
    private String msg_type = ""; // msg_type
    private String memo = ""; // memo
    private String model_id = ""; // memo
    private String mserial_num = ""; // memo
    private String user_name = "";
    private String receivername="";

    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int user_code_col = 1; // USER_CODE相对应的列数
    private static final int send_time_col = 2; // SEND_TIME相对应的列数
    private static final int content_col = 3; // CONTENT相对应的列数
    private static final int receiver_col = 4; // RECEIVER相对应的列数
    private static final int read_flag_col = 5; // READ_FLAG相对应的列数
    private static final int read_time_col = 6; // READ_TIME相对应的列数
    private static final int web_url_col = 7; // WEB_URL相对应的列数
    private static final int msg_type_col = 8; // READ_TIME相对应的列数
    private static final int memo_col = 9; // WEB_URL相对应的列数
    private static final int user_name_col = 10; // WEB_URL相对应的列数
     private static final int model_id_col = 11; // WEB_URL相对应的列数
      private static final int mserial_num_col = 12; // WEB_URL相对应的列数
    public String getSerial_num() {
        return this.serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getUser_code() {
        return this.user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getSend_time() {
        return this.send_time;
    }

    public void setSend_time(String send_time) {
        int index;
        if ( (index = send_time.indexOf(".")) != -1) {
            send_time = send_time.substring(0, index);
        }
        this.send_time = send_time;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getRead_flag() {
        return this.read_flag;
    }

    public void setRead_flag(String read_flag) {
        this.read_flag = read_flag;
    }

    public String getRead_time() {
        return this.read_time;
    }

    public void setRead_time(String read_time) {
        int index;
        if ( (index = read_time.indexOf(".")) != -1) {
            read_time = read_time.substring(0, index);
        }
        this.read_time = read_time;

    }

    public String getWeb_url() {
        return this.web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getMsg_type() {
        return this.msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            return this.serial_num;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            return this.user_code;
        } else if ("send_time".equalsIgnoreCase(name) == true) {
            return this.send_time;
        } else if ("content".equalsIgnoreCase(name) == true) {
            return this.content;
        } else if ("receiver".equalsIgnoreCase(name) == true) {
            return this.receiver;
        } else if ("read_flag".equalsIgnoreCase(name) == true) {
            return this.read_flag;
        } else if ("read_time".equalsIgnoreCase(name) == true) {
            return this.read_time;
        } else if ("web_url".equalsIgnoreCase(name) == true) {
            return this.web_url;
        } else if ("msg_type".equalsIgnoreCase(name) == true) {
            return this.msg_type;
        } else if ("memo".equalsIgnoreCase(name) == true) {
            return this.memo;
        } else if ("user_name".equalsIgnoreCase(name) == true) {
            return this.user_name;
        } else if ("model_id".equalsIgnoreCase(name) == true) {
            return this.model_id;
        } else if ("mserial_num".equalsIgnoreCase(name) == true) {
            return this.mserial_num;
        } else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            this.serial_num = value;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            this.user_code = value;
        } else if ("send_time".equalsIgnoreCase(name) == true) {
            this.setSend_time(value);
        } else if ("content".equalsIgnoreCase(name) == true) {
            this.content = value;
        } else if ("receiver".equalsIgnoreCase(name) == true) {
            this.receiver = value;
        } else if ("read_flag".equalsIgnoreCase(name) == true) {
            this.read_flag = value;
        } else if ("read_time".equalsIgnoreCase(name) == true) {
            this.setRead_time(value);
        } else if ("web_url".equalsIgnoreCase(name) == true) {
            this.web_url = value;
        } else if ("msg_type".equalsIgnoreCase(name) == true) {
            this.msg_type = value;
        } else if ("memo".equalsIgnoreCase(name) == true) {
            this.memo = value;
        } else if ("user_name".equalsIgnoreCase(name) == true) {
            this.user_name = value;
        } else if ("model_id".equalsIgnoreCase(name) == true) {
            this.model_id = value;
        } else if ("mserial_num".equalsIgnoreCase(name) == true) {
            this.mserial_num = value;
        } else {
            return;
        }
    }

    public String getColType(String name) {
        String colType = new String();
        if ("send_time".equalsIgnoreCase(name) == true) {
            colType = "timestamp";
        } else if ("read_flag".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("model_id".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("read_time".equalsIgnoreCase(name) == true) {
            colType = "timestamp";
        } else {
            colType = "String";
        }
        return colType;
    }

    public String getTableName() {
        return "do_inner_msg";
    }

    public String getPkFields() {
        return "serial_num,";
    }

    public String getModifyFields() {
        return "user_code,send_time,content,receiver,read_flag,read_time,web_url,msg_type,memo,model_id,mserial_num,";
    }

    public String getAllFields() {
        return "serial_num,user_code,send_time,content,receiver,read_flag,read_time,web_url,msg_type,memo,user_name,model_id,mserial_num,";
    }

    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.user_code = values[user_code_col];
        this.send_time = values[send_time_col];
        this.content = values[content_col];
        this.receiver = values[receiver_col];
        this.read_flag = values[read_flag_col];
        this.read_time = values[read_time_col];
        this.web_url = values[web_url_col];
        this.msg_type = values[msg_type_col];
        this.memo = values[memo_col];
    }

    public void setOtherProperty(String[] values) {
        if (StringUtils.isNull(this.user_name)) {
            this.user_name = this.user_code;
        }

    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
  public String getMserial_num() {
    return mserial_num;
  }
  public void setMserial_num(String mserial_num) {
    this.mserial_num = mserial_num;
  }
  public String getModel_id() {
    return model_id;
  }
  public void setModel_id(String model_id) {
    this.model_id = model_id;
  }
  public String getReceivername() {
    return receivername;
  }
  public void setReceivername(String receivername) {
    this.receivername = receivername;
  }
}
