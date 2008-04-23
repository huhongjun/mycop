package com.gever.web.css.dao.impl;

import java.util.*;
import java.sql.*;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.jdbc.connection.ConnectionUtil;
import com.gever.exception.db.DAOException;

import com.gever.config.Constants;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.web.css.dao.UserCssDAO;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.xpath.*;

public class DefaultUserCssDAO
    implements UserCssDAO {
  public String getUserCssGroupId(int userId) throws DAOException {
    ConnectionProvider cp = null;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    PrivilegeFactory factory = PrivilegeFactory.getInstance();
    String css_group_id = null;
    String sql = "select css_id from T_USER_CSS where user_id=?";
    try {
      cp = ConnectionProviderFactory.getConnectionProvider(Constants.
          DATA_SOURCE);
      conn = cp.getConnection();
      ps = conn.prepareStatement(sql);
      ps.setInt(1, userId);
      rs = ps.executeQuery();
      if (rs.next()) {
        css_group_id = String.valueOf(rs.getInt("css_id"));
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
        if(rs != null) {
          rs.close();
        }
        ConnectionUtil.close(conn, ps);
      }
      catch (Exception e) {
        throw new DAOException("PRV_Css_001", e);
      }
    }

    return css_group_id;
  }

  public Collection getDefaultCss() throws Exception {
    String location = null;
    SAXBuilder builder = new SAXBuilder();
    Document doc = buildDoc(builder);

    Element root = doc.getRootElement();
    Collection defaultCss = root.getChildren("default");
    Collection defaultCssList = new ArrayList();
    Iterator it = defaultCss.iterator();
    if (it.hasNext()) {
      Element defaultCssGroup = (Element) it.next();
      Collection defaultCsss = defaultCssGroup.getChildren("css");
      it = defaultCsss.iterator();
      while (it.hasNext()) {
        Element csss = (Element) it.next();
        location = csss.getAttributeValue("location");
        defaultCssList.add(location);
      }
    }
    return defaultCssList;
  }

  public Map getCss() throws
      Exception {
    String id = null;
    String location = null;

    SAXBuilder builder = new SAXBuilder();
    Document doc = buildDoc(builder);
    Element root = doc.getRootElement();
    Collection cssGroups = root.getChildren("css-group");
    Map map = new HashMap();
    Iterator it = cssGroups.iterator();
    while (it.hasNext()) {
      Element cssGroup = (Element) it.next();
      id = cssGroup.getAttributeValue("id");
      Collection cssList = new ArrayList();
      Collection csss = cssGroup.getChildren("css");
      Iterator i = csss.iterator();
      while (i.hasNext()) {
        Element css = (Element) i.next();
        location = css.getAttributeValue("location");
        cssList.add(location);
      }
      map.put(id, cssList);
    }
    /*
        if (cssList.size() == 0) {
          cssGroups = root.getChildren("default");
          it = cssGroups.iterator();
          if (it.hasNext()) {
            Element defaultGroup = (Element) it.next();
            csss = defaultGroup.getChildren("css");
            it = csss.iterator();
            while (it.hasNext()) {
              Element css = (Element) it.next();
              location = css.getAttributeValue("location");
              cssList.add(location);
            }
          }
        }
     */
    return map;
  }

  public synchronized Document buildDoc(SAXBuilder builder) throws
      Exception {
    Document doc;
    //doc = builder.build(new File(filename));
    doc = builder.build(DefaultUserCssDAO.class.getResourceAsStream(
        "/css-config.xml"));
    return doc;
  }

  public synchronized List getCss(XPath xpath, Document doc) throws
      Exception {
    List list = xpath.selectNodes(doc);
    return list;
  }

}