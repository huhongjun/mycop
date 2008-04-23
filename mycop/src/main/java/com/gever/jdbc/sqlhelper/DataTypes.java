package com.gever.jdbc.sqlhelper;

/**
 * <p>Title: �������ʹ洢 </p>
 * <p>Description: �������ʹ洢 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Hu.Walker
 * @version 0.5
 */

public class DataTypes {


    public final static String STRING = "String";       // �ִ���
    public final static String INT = "int";             // int��
    public final static String LONG = "long";           // long��
    public final static String DOUBLE = "double";       // double��
    public final static String FLOAT = "float";         // float��
    public final static String DATE = "date";           // date��
    public final static String TIMESTMAP = "timestamp";  // ʱ����
    public final static String BYTES = "bytes";  // ʱ����
    public final static String TIME = "time";  // ʱ����
    public final static String BOOLEAN = "boolean";  // ʱ����
    public final static String DECIMAL = "decimal";  // ʱ����
    public final static String CLOB = "clob";

    public final static int RS_LIST_VO = 0;          // ��ArrayList->vo��ʽ��ѯ
    public final static int RS_LIST_MAP = 1;         // ��ArrayList->HashMap��ʽ��ѯ
    public final static int RS_SINGLE_VO = 2;        // ��vo��ʽ��ѯ
    //public final static int RS_SINGLE_LIST = 3;      // ��ArrayList��ѯ
    public final static int RS_SINGLE_MAP = 4;       // ��HashMap��ѯ
    public final static int RS_META_LIST = 5;        // rsMeta��List��ѯ

    public final static int INSERT_ACTION =1;
    public final static int UPDATE_ACTION =2;
    public final static int DELETE_ACTION =4;
    public final static int QUERY_ACTION =8;
    public final static int COPY_ACTION =16;
}
