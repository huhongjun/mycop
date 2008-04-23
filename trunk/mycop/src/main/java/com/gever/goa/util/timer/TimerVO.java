package com.gever.goa.util.timer;

/**
 * <p>Title:定时器的VO对象 </p>
 * <p>Description: 定时器的VO对象</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class TimerVO {
    private String className = "";    //相对应类名
    private String checkMethod = "";   //检查的方法
    private String doMethod = "";     //执行的方法
    private String seconds = "";      //多少秒扫描
    private String isDao = "";        //是否为dao
    public TimerVO() {
    }
    public String getCheckMethod() {
        return checkMethod;
    }
    public String getClassName() {
        return className;
    }
    public String getDoMethod() {
        return doMethod;
    }
    public void setDoMethod(String doMethod) {
        this.doMethod = doMethod;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public void setCheckMethod(String checkMethod) {
        this.checkMethod = checkMethod;
    }
    public String getSeconds() {
        return seconds;
    }
    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }
    public String getIsDao() {
        return isDao;
    }
    public void setIsDao(String isDao) {
        this.isDao = isDao;
    }

}
