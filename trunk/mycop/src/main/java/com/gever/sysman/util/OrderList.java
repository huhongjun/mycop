/*
 * 创建日期 2004-11-30
 */
package com.gever.sysman.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hu.Walker
 * 增加JSP视图列表排序功能
 * 存储需要排序数据的结构
 */
public class OrderList {
    public static final String replace = "order by";
    public static final String key_name = "from";
    public static final String suf_name = "limit";
    public static final String desc = "desc";

    public static final String log_key = "log_key";					//session key
    public static final String user_key = "user_key";				//session key
    public static final String role_key = "role_key";				//session key
    public static final String department_key = "department_key";	//session key
    public static final String job_key = "job_key";					//session key
    public static final String employee_key = "employee_key";		//session key
    public static final String opt_key = "opt_key";					//session key
    public static final String res_key = "res_key";					//session key
    public static final String level_key = "level_key";				//session key

    public static String[] log_value = { "username", "ipaddress", "otime", "module", "action", "memo" };
    public static String[] user_value = { "USERNAME", "NAME", "USERTYPE","LEVEL", "STATUS", "VALIDDATE" };
    public static String[] role_value = { "NAME", "DESCRIPTION" };
    public static String[] department_value = { "CODE", "NAME" };
    public static String[] job_value = { "NAME" };
    public static String[] employee_value = { "USERNAME", "NAME","USERTYPE", "STATUS" ,"VALIDDATE"};
    public static String[] opt_value = { "T_RESOPERATION.ID", "T_RESOPERATION.CODE", "T_RESOPERATION.Name", "T_RESOPERATION.Description", "T_RESOURCE.Name" };
    public static String[] res_value = { "CODE", "NAME", "Description", "ResourceType" };
    public static String[] level_value = { "CODE", "NAME", "Description" };

    public Map map = new HashMap();
    public static OrderList _order = null;

    public Map getMap(){
        return this.map;
    }

    public static synchronized OrderList getInstance(){
        if( _order == null){
            _order = new OrderList();
            _order.map.put(log_key, log_value);
            _order.map.put(user_key, user_value);
            _order.map.put(role_key, role_value);
            _order.map.put(department_key, department_value);
            _order.map.put(job_key, job_value);
            _order.map.put(employee_key, employee_value);
            _order.map.put(opt_key, opt_value);
            _order.map.put(res_key, res_value);
            _order.map.put(level_key, level_value);
        }
        return _order;
    }

 
    /** 格式化SQL语句，把相应的 order by 替换掉
     * @param orderby
     * @param sql
     * @return
     */
    public String formatSQL(String[] orderby, String sql){
        if((orderby == null) || (orderby.length < 2)) return sql;
        boolean ordesc = orderby[1].equals("true");
        StringBuffer out = new StringBuffer();

        int order_pos = getLastPosition(sql,replace);
        int limit_pos = getLastPosition(sql,suf_name);
        int key_pos   = getLastPosition(sql,key_name);

        //=========================================================================
        //根据关键字key_name判断replace是不是要替换的那个replace
        //此处只需判断最后一个key_name的位置在replace之前就要替换，否者需要添加
        //=========================================================================
        if(order_pos > 0){
            if(order_pos > key_pos){
                out.append(sql.substring(0,order_pos));
            } else {
                if(limit_pos > key_pos){
                    out.append(sql.substring(0,key_pos));
                } else {
                    out.append(sql);
                }
            }
        } else {
            out.append(sql);
        }
        out.append(" " + replace);
        out.append(" " + orderby[0]);
        if(!ordesc) out.append(" " + desc);
        if(limit_pos > key_pos) out.append(" " + sql.substring(limit_pos));

        return out.toString();
    }
    // 获取要找的 key_name 位置
    // 没有排除FROM 和WHERE 中的复合SELECT语句中的 key_name
    // 不支持复合语句中子查询有ORDER BY的情况，在本项目中没有问题，需完善
    public int getLastPosition(String sql, String key_name){
        int lower = sql.indexOf(key_name);
        int upper = sql.indexOf(key_name.toUpperCase());
        int position = lower >= upper ? lower : upper;
        //i条件防止死循环
        for(int i=0; position>0 && i<5; i++){
            lower = sql.substring(position + key_name.length()).indexOf(key_name);
            upper = sql.substring(position + key_name.length()).indexOf(key_name.toUpperCase());
            if((lower < 0) && (upper < 0)) break;
            position += lower >= upper ? lower : upper;
        }
        return position;
    }
    public boolean isNull(String s){
        return (s == null) || s.equals("") || s.equals("null");
    }
}
