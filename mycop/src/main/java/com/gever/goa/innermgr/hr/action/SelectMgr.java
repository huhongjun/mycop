package com.gever.goa.innermgr.hr.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import com.gever.goa.innermgr.hr.vo.MgrinfoVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;

/**
 * <p>Title: 权限控制</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class SelectMgr  extends BaseDAO{
  //private final static Collection coll = new ArrayList();
 private Collection coll=null;
  public SelectMgr(String dbData) {
    super(dbData);
  }
  public SelectMgr() {
     coll= new ArrayList();
  }

  String sql = "select id,name,tag from extmgr_permition";

  public void selectDate() {

    ResultSet rs = null;

      PreparedStatement pstmt = null;
      Object obj = null;
      Connection conn = null;
     try {

        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
            dbData);
        conn = cp.getConnection();


       pstmt = conn.prepareStatement(sql);
      rs=pstmt.executeQuery();

      while(rs.next())
      {MgrinfoVO vo=new MgrinfoVO();
        String id=String.valueOf(rs.getInt("id"));
        vo.setId(id);

      String tag=  String.valueOf(rs.getInt("tag"));

        vo.setTag(tag);
        vo.setName(rs.getString("NAME"));
        this.coll.add(vo);
      }
    }
  catch(Exception e){}
  finally {
   try {
     if (conn != null) {
       conn.close();
     }

     if (pstmt != null) {
       pstmt.close();
     }
   }
   catch (Exception e) {
   }
 }

}
public Collection getMgr()
{
  return this.coll;


}


}