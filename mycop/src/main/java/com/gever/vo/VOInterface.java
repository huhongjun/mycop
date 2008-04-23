package com.gever.vo;
/**
 * <p>Title: VO�����ӿ���</p>
 * <p>Description:  ΪVO��չ��һЩ�����ݿ���صĲ���</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author 
 * @version 0.5
 */

public interface VOInterface {
    /**
     * ȡ��ָ����������ֵ
     * @param name ��������
     */
    public String getValue(String name);

    /**
     * ����ָ����������ֵ
     * @param name ��������
     * @param value ֵ
     */
    public void setValue(String name, String value);
 
    /**ͨ�����鷽ʽһ��ָ�������ֶε�ֵ
     * @param values
     */
    public void setValues(String[] values);

    /**
     * ��ȡָ���ֶε���������
     * @param name �ֶ�����
     * @return �ֶ�����
     */
    public String getColType(String name);

    /**
     * ��ȡ��vo������б�����
     * @return ��vo��������ֶ����顣
     */
    public String getAllFields();

    /**
     * ��ȡ��vo������п��޸ĵ��ֶ�����,ע��(���ŷָ�)��
     * @return ��vo��������ֶΡ�
     */
    public String getModifyFields();

    /**
     * ��ȡ��vo���������������,ע��(���ŷָ�)��
     * @return ��vo��������ֶ����顣
     */
    public String getPkFields();

    /**
     * ��ȡ��vo�����ӳ�������
     * @return ��vo�����ӳ�������
     */
    public String getTableName();

    /**
     * ������������(����ܻ��õ�����������)
     * @param map (���е����ݼ�¼)
     */
    public void setOtherProperty(String[] values) ;
}