package com.gever.goa.dailyoffice.tools.dao;
/**世界之窗模块
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public abstract class WorldwindowFactory {
  private static String className =
      "com.gever.goa.dailyoffice.tools.dao.impl.DefaultWorldwindowFactory";
  private static WorldwindowFactory factory;
  public WorldwindowFactory() {
  }

  public static synchronized WorldwindowFactory getInstance() {
    if (factory == null) {
      try {
        factory = (WorldwindowFactory) Class.forName(className).newInstance();
      }
      catch (Exception e) {
        System.err.println("Failed to load PrivilegeFactory class " +
                           className);
        e.printStackTrace();
      }
    }
    return factory;
  }

  public abstract WorldwindowDao createWorldwindowDao(String dbData);
  public abstract WorldwindowTypeDao createWorldwindowTypeDao(String dbData);
}
