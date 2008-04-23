package com.gever.goa.dailyoffice.mailmgr.dao;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.vo.MailInfoVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 邮件参数</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface MailInfoDAO {

    /**
     * 新增
     * @param vo 当前邮件参数vo对象
     * @return 当前邮件参数vo对象
     */
    public int insert(VOInterface vo) throws DefaultException;

    /**
     * 新增
     * @param vo 当前邮件参数vo对象
     * @return 当前邮件参数vo对象
     */
    public int update(VOInterface vo) throws DefaultException;

    /**
     * 查寻
     * @param vo 当前邮件参数vo对象
     * @return 当前邮件参数vo对象
     */

    public VOInterface queryByPK(VOInterface vo) throws DefaultException;

    public MailInfoVO getMailInfoByUserId(String userId) throws DefaultException;

    public long queryByCount(VOInterface searchVO) throws DefaultException;


}
