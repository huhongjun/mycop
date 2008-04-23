package com.gever.goa.admin.dao;

/**
 * <p>Title: AdminFactory</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ÌìÐÐÈí¼þ</p>
 * @author Hu.Walker
 * @version 1.0
 */

public abstract class AdminFactory {
  private static String className =
      "com.gever.goa.admin.dao.impl.DefaultAdminFactory";
  private static AdminFactory factory = null;
  public AdminFactory() {
  }

  /**
      @return com.gever.sysman.privilege.dao.PrivilegeFactory
      @roseuid 40BAB9B00167
   */
  public static synchronized AdminFactory getInstance() {
    if (factory == null) {
      try {
        Class c = Class.forName(className);
        factory = (AdminFactory) c.newInstance();
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

  public abstract DutyDao createDuty(String dbData);

  public abstract UnitDao createUnit(String dbData);

  public abstract JobDao createJob(String dbData);

  public abstract MarriageDao createMarriage(String dbData);

  public abstract PolityDao createPolity(String dbData);

  public abstract NationDao createNation(String dbData);

  public abstract KnowDao createKnow(String dbData);

  public abstract SexDao createSex(String dbData);


}
