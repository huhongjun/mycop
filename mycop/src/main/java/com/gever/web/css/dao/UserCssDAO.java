package com.gever.web.css.dao;

import java.util.*;

import com.gever.exception.db.DAOException;

public interface UserCssDAO {
  /**
   * 查表获得用户css id
   * @param userId
   * @return
   * @throws DAOException
   */
  public String getUserCssGroupId(int userId) throws DAOException ;

  /**
   * 解析默认css
   * @return
   * @throws Exception
   */
  public Collection getDefaultCss() throws Exception;
  /**
   * 解析xml获得用户所有css
   * @return
   * @throws Exception
   */
  public Map getCss() throws Exception;
}