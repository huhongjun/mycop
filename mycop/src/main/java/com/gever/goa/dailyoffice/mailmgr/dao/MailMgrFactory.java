package com.gever.goa.dailyoffice.mailmgr.dao;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ÌìÐÐÈí¼þ</p>
 * @author Hu.Walker
 * @version 1.0
 */

public abstract class MailMgrFactory {
    private static String className =
        "com.gever.goa.dailyoffice.mailmgr.dao.impl.DefaultMailMgrFactory";
    private static MailMgrFactory factory = null;

    public MailMgrFactory() {
    }

    /**
           @return com.gever.sysman.privilege.dao.PrivilegeFactory
           @roseuid 40BAB9B00167
     */
    public static synchronized MailMgrFactory getInstance() {
        if (factory == null) {
            try {
                Class c = Class.forName(className);
                factory = (MailMgrFactory) c.newInstance();
            } catch (Exception e) {
                System.err.println("Failed to load PrivilegeFactory class " +
                                   className);
                e.printStackTrace();

                return null;
            }
        }

        return factory;
    }

    public abstract MailInfoDAO creatMailInfo(String dBData);
    public abstract MailDirectoryDAO creatMailDirectory(String dBData);
    public abstract MailCapacityDAO creatMailCapacity(String dBData);
    public abstract MailInfoDAO createMailInfo(String dbData);
    public abstract MailMgrDAO createMailMgr(String dbData);
    public abstract Pop3ConfigDAO createPop3Config(String dbData);
    public abstract ImportAndExportDAO createImportAndExport(String dbData);
    public abstract MailboxMgrDAO createMailboxMgr(String dbData);
    public abstract Pop3MailMgrDAO creatPop3MailMgr(String dbData);
}
