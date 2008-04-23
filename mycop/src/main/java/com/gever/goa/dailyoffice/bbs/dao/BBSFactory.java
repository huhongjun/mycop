package com.gever.goa.dailyoffice.bbs.dao;

public abstract class BBSFactory {

    private static String className =
            "com.gever.goa.dailyoffice.bbs.dao.impl.DefaultBBSFactory";
    private static BBSFactory factory = null;
    public BBSFactory() {
    }

    /**
        @return com.gever.sysman.privilege.dao.PrivilegeFactory
        @roseuid 40BAB9B00167
     */
    public static synchronized BBSFactory getInstance() {
        if (factory == null) {
            try {
                Class c = Class.forName(className);
                factory = (BBSFactory) c.newInstance();
            }
            catch (Exception e) {
                System.err.println("Failed to load PrivilegeFactory class " +
                                   className);
                e.printStackTrace();

                return null;
            }
        }

        return factory;
    }

    public abstract BBSMngDAO createMngDAO(String dbData);

    public abstract BBSDAO createDAO(String dbData);

    public abstract BBSTopicDAO createTopicDAO(String dbData);

    public abstract BBSTopicListDAO createTopicLisDAO(String dbData);

    public abstract ViewTopicDAO createViewTopicDAO(String dbData);

    public abstract ViewTopiclistDAO createViewTopiclistDAO(String dbData);

}
