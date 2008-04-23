package com.gever.goa.dailyoffice.mailmgr.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.vo.MailCapacityVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 邮箱管理</p>
 * <p>Description: 邮箱管理</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface MailCapacityDAO {
    /**
     * 查询收件夹
     * @param curMailDir 当前邮件夹
     * @param mailType 邮件类型(0所有,1是内部,2是外部)
     * @return 邮件列表
     * @throws DefaultException
     */
    public List queryByAll(String curMailDir, int mailType) throws
        DefaultException;

    /**
     * 查询邮件统计数
     * @param vo 当前邮件夹
     * @return 统计数
     * @throws DefaultException
     */
    public long queryByCount(VOInterface vo) throws DefaultException;

    /**
     *
     * @param vo 当前邮件vo对象
     * @param start 开始
     * @param howMany 查询多少笔
     * @return 当前页的邮件列表
     * @throws DefaultException
     */
    public List queryByPage(VOInterface vo, long start, long howMany) throws
        DefaultException;


    /**
     * 得默认的字节
     * @return 得默认的字节
     */
    public double getDefaultSize();

    /**
     * 修改选择多少笔资料
     * @param dblSize 容量
     * @param aryUserId 选择用户
     * @return 修改多少笔
     * @throws DefaultException
     */
    public int update(double dblSize ,String[] aryUserId)throws DefaultException;

    /**
     * 修改所有
     * @param dblSize 容量
     * @return 修改多少笔
     * @throws DefaultException
     */
    public int setAllCapacity(double dblSize,String deptId) throws DefaultException;

    public boolean isOutOfCapacity(MailCapacityVO vo, long mailSize) throws DefaultException;
    public List getMailCapacityByUser(String userIds) throws DefaultException;
    public void setStrWhere(String strWhere);
    public List getMailCapacity() throws DefaultException;
    /**add by lihaidong
     * 插入多条邮箱信息
     * @param vo
     * @return
     * @throws DefaultException
     */
    public int multiInsert(VOInterface vo) throws DefaultException;
    /**
     * 根据用户名得到邮箱信息
     * @param vo
     * @return
     * @throws DefaultException
     */
    public List getCapactiryByUser(String userName) throws DefaultException;
}
