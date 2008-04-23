package com.gever.goa.admin.dao.impl;

import com.gever.goa.admin.dao.AdminFactory;
import com.gever.goa.admin.dao.DutyDao;
import com.gever.goa.admin.dao.JobDao;
import com.gever.goa.admin.dao.KnowDao;
import com.gever.goa.admin.dao.MarriageDao;
import com.gever.goa.admin.dao.NationDao;
import com.gever.goa.admin.dao.PolityDao;
import com.gever.goa.admin.dao.SexDao;
import com.gever.goa.admin.dao.UnitDao;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ÌìÐÐÈí¼þ</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DefaultAdminFactory
    extends AdminFactory {
  public DefaultAdminFactory() {
  }

  public PolityDao createPolity(String dbData) {
    return new PoliDaoImpl(dbData);
  }

  public DutyDao createDuty(String dbData) {
    return new DutyDaoImpl(dbData);
  }

  public JobDao createJob(String dbData) {
    return new JobDaoImpl(dbData);
  }

  public MarriageDao createMarriage(String dbData) {
    return new MarrDaoImpl(dbData);
  }

  public NationDao createNation(String dbData) {
    return new NatiDaoImpl(dbData);
  }

  public UnitDao createUnit(String dbData) {
    return new UnitDaoImpl(dbData);
  }
  public KnowDao createKnow(String dbData) {
   return new KnowDaoImpl(dbData);
 }
 public SexDao createSex(String dbData) {
   return new SexDaoImpl(dbData);
 }



}
