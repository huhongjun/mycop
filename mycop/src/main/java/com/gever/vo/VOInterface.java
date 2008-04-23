package com.gever.vo;
/**
 * <p>Title: VO公共接口类</p>
 * <p>Description:  为VO扩展了一些与数据库相关的操作</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author 
 * @version 0.5
 */

public interface VOInterface {
    /**
     * 取得指定变量名的值
     * @param name 属性名称
     */
    public String getValue(String name);

    /**
     * 设置指定变量名的值
     * @param name 属性名称
     * @param value 值
     */
    public void setValue(String name, String value);
 
    /**通过数组方式一次指定数个字段的值
     * @param values
     */
    public void setValues(String[] values);

    /**
     * 获取指定字段的数据类型
     * @param name 字段名称
     * @return 字段类型
     */
    public String getColType(String name);

    /**
     * 获取本vo类的所有变量。
     * @return 本vo类的所有字段数组。
     */
    public String getAllFields();

    /**
     * 获取本vo类的所有可修改的字段名字,注以(逗号分隔)。
     * @return 本vo类的所有字段。
     */
    public String getModifyFields();

    /**
     * 获取本vo类的所有主键名称,注以(逗号分隔)。
     * @return 本vo类的所有字段数组。
     */
    public String getPkFields();

    /**
     * 获取本vo类的所映射表名。
     * @return 本vo类的所映射表名。
     */
    public String getTableName();

    /**
     * 设置其它属性(因可能会用到其它的属性)
     * @param map (单行的数据记录)
     */
    public void setOtherProperty(String[] values) ;
}