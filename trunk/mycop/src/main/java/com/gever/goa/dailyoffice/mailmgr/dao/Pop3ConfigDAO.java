package com.gever.goa.dailyoffice.mailmgr.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.vo.Pop3ConfigVO;
import com.gever.vo.VOInterface;
/**
 *
 * <p>Title: </p>
 * <p>Description:Pop3 DAO接口 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface Pop3ConfigDAO {
    /**
     * 新增
     * @param vo 当前POP3邮件vo对象
     * @return 插入的行数
     */
    public int insert(VOInterface vo) throws DefaultException;

    /**
     * 新增
     * @param vo 当前POP3邮件vo对象
     * @return 更新的行数
     */
    public int update(VOInterface vo) throws DefaultException;
    /**
     * 删除
     * @param vo 当前POP3邮件vo对象
     * @return  删除的行数
     * @throws DefaultException
     */

    public int delete(VOInterface vo) throws DefaultException;

    /**
     * 查寻
     * @param vo 当前POP3邮件vo对象
     * @return 当前POP3邮件vo对象
     */

    public VOInterface queryByPK(VOInterface vo) throws DefaultException;

    public long queryByCount(VOInterface vo) throws DefaultException;

    public List getPop3ConfigByUserId(String userId) throws DefaultException;

    public List queryListByPK(String[] serialNums) throws DefaultException;

    public Pop3ConfigVO queryPop3ConfigInfoByPK(String serialNum) throws DefaultException;
}
