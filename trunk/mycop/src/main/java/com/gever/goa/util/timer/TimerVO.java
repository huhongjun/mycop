package com.gever.goa.util.timer;

/**
 * <p>Title:��ʱ����VO���� </p>
 * <p>Description: ��ʱ����VO����</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class TimerVO {
    private String className = "";    //���Ӧ����
    private String checkMethod = "";   //���ķ���
    private String doMethod = "";     //ִ�еķ���
    private String seconds = "";      //������ɨ��
    private String isDao = "";        //�Ƿ�Ϊdao
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
