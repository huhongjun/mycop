package com.gever.goa.dailyoffice.tools.dao.impl;

import com.gever.goa.dailyoffice.tools.dao.WorldwindowDao;
import com.gever.goa.dailyoffice.tools.dao.WorldwindowFactory;
import com.gever.goa.dailyoffice.tools.dao.WorldwindowTypeDao;
/**����֮��ģ��
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DefaultWorldwindowFactory extends WorldwindowFactory {
  public DefaultWorldwindowFactory() {

  }
  public WorldwindowDao createWorldwindowDao(String dbData) {
    return new WorldwindowDaoImpl(dbData);
  }
  public WorldwindowTypeDao createWorldwindowTypeDao(String dbData){
    return new WorldwindowTypeDaoImpl(dbData);
  }

}
