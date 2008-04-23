package com.gever.goa.dailyoffice.smsmgr.dao;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public abstract class SmsFactory {
    public SmsFactory() {
    }

    private final static String className =
        "com.gever.goa.dailyoffice.smsmgr.dao.impl.DefaultSmsFactory";
    private static SmsFactory factory = null;

    /**
     * �õ�����Ϣ��ʵ�幤��
     * @return DAOʵ�幤��
     */
    public static synchronized SmsFactory getInstance() {
        if (factory == null) {
            try {
                Class c = Class.forName(className);

                factory = (SmsFactory) c.newInstance();
                System.out.println("---------"+factory.getClass().getName());
            } catch (Exception e) {
                System.err.println("Failed to load PrivilegeFactory class " +
                                   className);
                e.printStackTrace();

                return null;
            }
        }

        return factory;
    }


    /**
     * �����ֻ����Ź���DAO
     * @param dbData ����Դ
     * @return �ֻ����Ź���DAO��ʵ��
     */
    public abstract SmsMgrDAO createSmsMgrDAO(String dbData);

    /**
     * �����ֻ����ŵĲ�Ʒ
     * @param dbData ����Դ
     * @return �ֻ����Ų�Ʒ��ʵ��
     */

    public abstract SmsProduct createSmsProduct(String dbData);
    public abstract SMSCapacityDAO createSmsCapacityDAO(String dbData);
    public abstract SmsInBoxDAO createSmsInBoxDAO(String dbData) ;

}