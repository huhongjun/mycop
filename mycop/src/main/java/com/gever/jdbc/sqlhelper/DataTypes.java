package com.gever.jdbc.sqlhelper;

/**
 * <p>Title: 常量类型存储 </p>
 * <p>Description: 常量类型存储 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Hu.Walker
 * @version 0.5
 */

public class DataTypes {


    public final static String STRING = "String";       // 字串型
    public final static String INT = "int";             // int型
    public final static String LONG = "long";           // long型
    public final static String DOUBLE = "double";       // double型
    public final static String FLOAT = "float";         // float型
    public final static String DATE = "date";           // date型
    public final static String TIMESTMAP = "timestamp";  // 时间型
    public final static String BYTES = "bytes";  // 时间型
    public final static String TIME = "time";  // 时间型
    public final static String BOOLEAN = "boolean";  // 时间型
    public final static String DECIMAL = "decimal";  // 时间型
    public final static String CLOB = "clob";

    public final static int RS_LIST_VO = 0;          // 以ArrayList->vo方式查询
    public final static int RS_LIST_MAP = 1;         // 以ArrayList->HashMap方式查询
    public final static int RS_SINGLE_VO = 2;        // 以vo方式查询
    //public final static int RS_SINGLE_LIST = 3;      // 以ArrayList查询
    public final static int RS_SINGLE_MAP = 4;       // 以HashMap查询
    public final static int RS_META_LIST = 5;        // rsMeta以List查询

    public final static int INSERT_ACTION =1;
    public final static int UPDATE_ACTION =2;
    public final static int DELETE_ACTION =4;
    public final static int QUERY_ACTION =8;
    public final static int COPY_ACTION =16;
}
