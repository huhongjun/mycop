package com.gever.vo;

/**
 * <p>Title: �������е�vo����</p>
 * <p>Description: ��test��ӳ��,�������е���������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 0.5  ��������:
 */
public class TestVO extends BaseVO implements VOInterface {
    private String id = ""; // �û�ID
    private String userid = ""; // �û�ID
    private String username = ""; // �û�����
    private String BDATE = ""; // ��ʼ����
    private String ETIME = ""; // ȷ������
    private String QTY = ""; // ����
    private String AMT = ""; // ���
    private String price = ""; // ����
    private String memo = ""; // ����
    private String num = ""; // ����
    private String sdatetime = ""; // ����
    private String ctype = ""; // ���
    private String col1 = ""; // ��λ1
    private String col2 = ""; // ��λ2
    private String col3 = ""; // ��λ3
    private String col4 = ""; // ��λ4
    private String col5 = ""; // ��λ5
    private String col6 = ""; // ��λ6
    private String col7 = ""; // ��λ7
    private String col8 = ""; // ��λ8
    private String col9 = ""; // ��λ9
    private String col10 = ""; // ��λ10
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getBdate() {
        return this.BDATE;
    }
    public void setBdate(String BDATE) {
        this.BDATE = BDATE;
    }
    public String getEtime() {
        return this.ETIME;
    }
    public void setEtime(String ETIME) {
        this.ETIME = ETIME;
    }
    public String getQty() {
        return this.QTY;
    }
    public void setQty(String QTY) {
        this.QTY = QTY;
    }
    public String getAmt() {
        return this.AMT;
    }
    public void setAmt(String AMT) {
        this.AMT = AMT;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getSdatetime() {
        return this.sdatetime;
    }
    public void setSdatetime(String sdatetime) {
        this.sdatetime = sdatetime;
    }
    public String getCtype() {
        return this.ctype;
    }
    public void setCtype(String ctype) {
        this.ctype = ctype;
    }
    public String getCol1() {
        return this.col1;
    }
    public void setCol1(String col1) {
        this.col1 = col1;
    }
    public String getCol2() {
        return this.col2;
    }
    public void setCol2(String col2) {
        this.col2 = col2;
    }
    public String getCol3() {
        return this.col3;
    }
    public void setCol3(String col3) {
        this.col3 = col3;
    }
    public String getCol4() {
        return this.col4;
    }
    public void setCol4(String col4) {
        this.col4 = col4;
    }
    public String getCol5() {
        return this.col5;
    }
    public void setCol5(String col5) {
        this.col5 = col5;
    }
    public String getCol6() {
        return this.col6;
    }
    public void setCol6(String col6) {
        this.col6 = col6;
    }
    public String getCol7() {
        return this.col7;
    }
    public void setCol7(String col7) {
        this.col7 = col7;
    }
    public String getCol8() {
        return this.col8;
    }
    public void setCol8(String col8) {
        this.col8 = col8;
    }
    public String getCol9() {
        return this.col9;
    }
    public void setCol9(String col9) {
        this.col9 = col9;
    }
    public String getCol10() {
        return this.col10;
    }
    public void setCol10(String col10) {
        this.col10 = col10;
    }
    public String getValue(String name) {
        if ("id".equals(name) == true) {
             return  this.id;
        } else if ("userid".equals(name) == true) {
             return  this.userid;
        } else if ("username".equals(name) == true) {
             return  this.username;
        } else if ("bdate".equals(name) == true) {
             return  this.BDATE;
        } else if ("etime".equals(name) == true) {
             return  this.ETIME;
        } else if ("qty".equals(name) == true) {
             return  this.QTY;
        } else if ("amt".equals(name) == true) {
             return  this.AMT;
        } else if ("price".equals(name) == true) {
             return  this.price;
        } else if ("memo".equals(name) == true) {
             return  this.memo;
        } else if ("num".equals(name) == true) {
             return  this.num;
        } else if ("sdatetime".equals(name) == true) {
             return  this.sdatetime;
        } else if ("ctype".equals(name) == true) {
             return  this.ctype;
        } else if ("col1".equals(name) == true) {
             return  this.col1;
        } else if ("col2".equals(name) == true) {
             return  this.col2;
        } else if ("col3".equals(name) == true) {
             return  this.col3;
        } else if ("col4".equals(name) == true) {
             return  this.col4;
        } else if ("col5".equals(name) == true) {
             return  this.col5;
        } else if ("col6".equals(name) == true) {
             return  this.col6;
        } else if ("col7".equals(name) == true) {
             return  this.col7;
        } else if ("col8".equals(name) == true) {
             return  this.col8;
        } else if ("col9".equals(name) == true) {
             return  this.col9;
        } else if ("col10".equals(name) == true) {
             return  this.col10;
        } else {
             return "";
        }
    }
    public void setValues(String[] values) {
        int i = 0;
        this.id = values[i++]; // �û�ID
        this.userid = values[i++]; // �û�ID
        this.username = values[i++]; // �û�����
        this.BDATE = values[i++]; // ��ʼ����
        this.ETIME = values[i++]; // ȷ������
        this.QTY = values[i++]; // ����
        this.AMT = values[i++]; // ���
        this.price = values[i++]; // ����
        this.memo = values[i++]; // ����
        this.num = values[i++]; // ����
        this.sdatetime = values[i++]; // ����
        this.ctype = values[i++]; // ���
        this.col1 = values[i++]; // ��λ1
        this.col2 = values[i++]; // ��λ2
        this.col3 = values[i++]; // ��λ3
        this.col4 = values[i++]; // ��λ4
        this.col5 = values[i++]; // ��λ5
        this.col6 = values[i++]; // ��λ6
        this.col7 = values[i++]; // ��λ7
        this.col8 = values[i++]; // ��λ8
        this.col9 = values[i++]; // ��λ9
        this.col10 = values[i++]; // ��λ10


    }
    public void setValue(String name, String value) {
        if ("id".equals(name) == true) {
             this.id = value ;
        } else if ("userid".equals(name) == true) {
             this.userid = value ;
        } else if ("username".equals(name) == true) {
             this.username = value ;
        } else if ("bdate".equals(name) == true) {
             this.BDATE = value ;
        } else if ("etime".equals(name) == true) {
             this.ETIME = value ;
        } else if ("qty".equals(name) == true) {
             this.QTY = value ;
        } else if ("amt".equals(name) == true) {
             this.AMT = value ;
        } else if ("price".equals(name) == true) {
             this.price = value ;
        } else if ("memo".equals(name) == true) {
             this.memo = value ;
        } else if ("num".equals(name) == true) {
             this.num = value ;
        } else if ("sdatetime".equals(name) == true) {
             this.sdatetime = value ;
        } else if ("ctype".equals(name) == true) {
             this.ctype = value ;
        } else if ("col1".equals(name) == true) {
             this.col1 = value ;
        } else if ("col2".equals(name) == true) {
             this.col2 = value ;
        } else if ("col3".equals(name) == true) {
             this.col3 = value ;
        } else if ("col4".equals(name) == true) {
             this.col4 = value ;
        } else if ("col5".equals(name) == true) {
             this.col5 = value ;
        } else if ("col6".equals(name) == true) {
             this.col6 = value ;
        } else if ("col7".equals(name) == true) {
             this.col7 = value ;
        } else if ("col8".equals(name) == true) {
             this.col8 = value ;
        } else if ("col9".equals(name) == true) {
             this.col9 = value ;
        } else if ("col10".equals(name) == true) {
             this.col10 = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("id".equals(name) == true) {
             colType = "long";
        } else if ("bdate".equals(name) == true) {
             colType = "date";
        } else if ("etime".equals(name) == true) {
             colType = "time";
        } else if ("qty".equals(name) == true) {
             colType = "int";
        } else if ("amt".equals(name) == true) {
             colType = "double";
        } else if ("price".equals(name) == true) {
             colType = "double";
        } else if ("num".equals(name) == true) {
             colType = "int";
        } else if ("sdatetime".equals(name) == true) {
             colType = "timestamp";
        } else if ("ctype".equals(name) == true) {
             colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "test";
    }
    public String getPkFields() {
        return "id";
    }
    public String getAllFields(){
        return "id,userid,username,bdate,etime,qty,amt,price,memo,num,sdatetime,ctype,col1,col2,col3,col4,col5,col6,col7,col8,col9,col10,";
    }
    public String getModifyFields() {
        return "userid,username,bdate,etime,qty,amt,price,memo,num,sdatetime,ctype,col1,col2,col3,col4,col5,col6,col7,col8,col9,col10 ";
    }
    public void setOtherProperty(String Values[]) {
    }
}
