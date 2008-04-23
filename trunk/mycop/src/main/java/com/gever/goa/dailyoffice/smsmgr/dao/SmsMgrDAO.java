package com.gever.goa.dailyoffice.smsmgr.dao;

import java.util.List;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.smsmgr.vo.OutBoxVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 短信管理类</p>
 * <p>Description:包括新增,修改,删除,查询等动作 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface SmsMgrDAO {
    /**
     * 删除已选的资料
     * @param idValues 主键数组
     * @param types 表类型
     * @return 删除成功多少笔,-1为失败
     * @throws DefaultException
     */

    public int delBySelect(String[] idValues, String[] types) throws DefaultException;
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
     * @return 所有例子数据列表
     * @throws DefaultException
     */

    public List queryByPage(  VOInterface vo, long start, long howMany) throws
        DefaultException;

    /**
     * 统计当前的数据
     * @param vo 当前vo对象
     * @return 统计数量
     * @throws DefaultException
     */
     public long queryByCount(VOInterface vo) throws DefaultException;

    /**
     * 新增短信表
     * @param msgList 消息列表
     * @return  新增成功多少笔
     * @throws DefaultException
     */
    public int insert(List msgList) throws DefaultException;

    /**
     * 新增定时短信表
     * @param msgList 消息列表
     * @return  新增成功多少笔
     * @throws DefaultException
     */
    public int insertTiming(List msgList) throws DefaultException;
    /**
     * 得到当前用户的发送的相关资料
     * @param strUserId 当前用户
     * @param strWhere 条件语句
     * @return 相关统计信息
     * @throws DefaultException
     */
    public Map getCurUserSendNumber(String strUserId, String strWhere) throws
        DefaultException ;
    /**
     * 按userid,查出当前所有的名片信息
     * @param userId 用户号
     * @return 名片列表
     * @throws DefaultException
     */
    public List queryCardByUserID(String userId) throws DefaultException;

    /**
     * @param userId 用户号
     * 按userid,查出当前所有的名片信息
     * @return 名片夹列表
     * @throws DefaultException
     */
    public List queryCardTypeByUserID(String userId) throws DefaultException;

    /**
     * 查询人员
     * @return 人员列表
     * @throws DefaultException
     */
    public List querySmsUsers() throws DefaultException ;

    /**
     * 按主键查询
     * @param id 主键id
     * @param type 表类型
     * @return vo对象
     * @throws DefaultException
     */
    public OutBoxVO queryByPk(String id, String type) throws DefaultException;

}