package com.gever.web.css.dao;

import java.util.*;

import com.gever.exception.db.DAOException;

public interface UserCssDAO {
  /**
   * ������û�css id
   * @param userId
   * @return
   * @throws DAOException
   */
  public String getUserCssGroupId(int userId) throws DAOException ;

  /**
   * ����Ĭ��css
   * @return
   * @throws Exception
   */
  public Collection getDefaultCss() throws Exception;
  /**
   * ����xml����û�����css
   * @return
   * @throws Exception
   */
  public Map getCss() throws Exception;
}