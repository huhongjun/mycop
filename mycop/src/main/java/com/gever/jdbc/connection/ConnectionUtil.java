package com.gever.jdbc.connection;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.gever.exception.DefaultException;

public class ConnectionUtil {
  private ConnectionUtil() {
  }

  public static void close(Connection conn) throws DefaultException{
    try{
      if(conn!=null)
        conn.close();
      conn=null;
    }catch(java.sql.SQLException ex){
      throw new DefaultException(ex);
    }
  }

  public static void close(Connection conn,Statement stat) throws DefaultException{
    try{
      if(stat!=null)
        stat.close();
      if(conn!=null)
        conn.close();

      conn=null;
      stat=null;
    }catch(java.sql.SQLException ex){
      throw new DefaultException(ex);
    }
  }

  public static void close(Connection conn,PreparedStatement pstat) throws DefaultException{
    try{
      if(pstat!=null)
        pstat.close();
      if(conn!=null)
        conn.close();
      conn=null;
      pstat=null;
    }catch(java.sql.SQLException ex){
      throw new DefaultException(ex);
    }
  }

  public static void close(Connection conn,Statement stat,ResultSet rs) throws DefaultException{
    try{

      if(rs!=null)
        rs.close();
      if(stat!=null)
        stat.close();
      if(conn!=null)
        conn.close();
      conn=null;
      stat=null;
      rs=null;

    }catch(java.sql.SQLException ex){
      throw new DefaultException(ex);
    }
  }

  public static void close(Connection conn,PreparedStatement pstat,ResultSet rs) throws DefaultException{
    try{
      if(rs!=null)
        rs.close();
      if(pstat!=null)
        pstat.close();
      if(conn!=null)
        conn.close();
      conn=null;
      pstat=null;
      rs=null;

    }catch(java.sql.SQLException ex){
      throw new DefaultException(ex);
    }
  }


}