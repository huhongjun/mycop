package com.gever.goa.dailyoffice.smsmgr.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 收件箱接口</p>
 * <p>Description: 包括删除,查询</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface SmsInBoxDAO {
    /**
     * 删除所有的资料
     * @param aryPk 主键数组
     * @param vo 短信vo
     * @return 删除成功多少笔,-1为失败
     * @throws DefaultException
     */
    public int delBySelect(String[] aryPk,VOInterface vo) throws
        DefaultException;

    /**
     * 得到分页的sql语句
     * @return sql语句
     */
    public String getPageSql();

    /**
     * 查询所有
     * @param vo 当前vo对象
     * @return 所有例子数据列表
     * @throws DefaultException
     */

    public List queryAll(VOInterface vo) throws DefaultException;

    /**
     * 分页查询
     * @param vo 当前vo对象
     * @param start 开始
     * @param howMany 多少笔资料
     *
     *
     * @return 所有例子数据列表
     * @throws DefaultException
     */

    public List queryByPage(VOInterface vo, long start, long howMany) throws
        DefaultException;

    /**
     * 统计当前的数据
     * @param vo 当前vo对象
     * @return 统计数量
     * @throws DefaultException
     */
    public long queryByCount(VOInterface vo) throws DefaultException;

    public int updateReadFlag(VOInterface vo) throws DefaultException;
}