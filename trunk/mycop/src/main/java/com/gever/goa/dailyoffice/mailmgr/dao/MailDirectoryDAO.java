package com.gever.goa.dailyoffice.mailmgr.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.vo.MailDirectoryVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface MailDirectoryDAO {
    /**
      * 新增邮件夹
      * 新增过程中发现有重名则抛出异常，新增不继续
      * @param vo 当前邮件参数vo对象
      * @return 当前邮件参数vo对象
      */
     public int insert(VOInterface vo) throws DefaultException;

     /**
      * 修改邮件夹
      * 修改过程中发现有重名则抛出异常，修改不继续
      * @param vo 当前邮件参数vo对象
      * @return 当前邮件参数vo对象
      */
     public int update(VOInterface vo) throws DefaultException;

     /**
      * 按照主键查寻邮件夹
      * @param vo 当前邮件参数vo对象
      * @return 当前邮件参数vo对象
      */

     public VOInterface queryByPK(VOInterface vo) throws DefaultException;

     /**
      * 查询所有邮件邮件夹
      * @param vo 当前邮件夹
      * @return 查询所有邮件
      * @throws DefaultException
      */

     public List queryAll(VOInterface vo) throws DefaultException;

     /**
      * 查询邮件统计数
      * @param vo 当前邮件夹
      * @return 统计数
      * @throws DefaultException
      */
     public long queryByCount(VOInterface vo) throws DefaultException;

     public List queryAllMailDir(String userId) throws DefaultException;

     public MailDirectoryVO queryMailDirByUserAndDirId(String userId,
         String mailDirId) throws DefaultException;

     public List queryAllExceptCurrentDir(String mailDirId, String userID)
         throws DefaultException;
     public List queryDirectoryByUserId(String userId) throws DefaultException;

     public MailDirectoryVO queryBySerialNum(String serialNum) throws DefaultException;

}
