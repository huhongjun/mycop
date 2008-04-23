package com.gever.goa.admin.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.JobDao;
import com.gever.goa.admin.vo.JobVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;
/**
 * <p>Title: 职称程序</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class JobDaoImpl
    extends BaseDAO
    implements JobDao {
  private static String QUERY_SQL =
      "SELECT job_code,job_name,remarks from sys_job";
   private String QUER_SQL="select count(*) as cnt from sys_job where job_code='";
   private String UPDATE_SQL="update sys_job set ";
  public JobDaoImpl(String dbData) {
    super(dbData);
  }

  public String getPageSql() {
    return QUERY_SQL;
  }

  public int insert(VOInterface vo) throws DefaultException {

    SQLHelper helper = new DefaultSQLHelper(dbData);
    JobVO jobVO = (JobVO) vo;

    helper.setAutoID(false);
    if (isRepeat( (JobVO) vo)) {
      throw new DefaultException("PK repeat!");
    }
    else {
      int iRet = helper.insert(vo);
      return iRet;
    }
  }

  public int update(VOInterface vo) throws DefaultException {
    SQLHelper helper = new DefaultSQLHelper(dbData);
    JobVO jobVO = (JobVO) vo;
    UPDATE_SQL += "job_code='" + jobVO.getJob_code() + "',"
        + "job_name='" + jobVO.getJob_name() + "',"
        + "remarks='" + jobVO.getRemarks() + "'"
        + "where job_code='" + jobVO.getJob_code_bak() + "'";
       if (isRepeat( (JobVO) vo)) {
         throw new DefaultException("PK");
       }

    if (checkUpdate(vo) != 1) {
      throw new DefaultException("sys_mod_002");
    }
    try {
      return helper.execUpdate(UPDATE_SQL);
       } catch(DefaultException e){
           throw new DefaultException("sys_mod_001", e);
       }

}

     private boolean isRepeat(JobVO vo) throws DefaultException{
        String sql = QUER_SQL +vo.getJob_code() + "'";
        SQLHelper helper = new DefaultSQLHelper(dbData); ;
        boolean bRet = false;
        Map map = (HashMap) helper.query(sql,
                                         com.gever.jdbc.sqlhelper.DataTypes.RS_SINGLE_MAP);
        if (com.gever.util.NumberUtil.stringToLong( (String) map.get("cnt")) > 0) {
          bRet = true;


        }
        return bRet;



     }
}
