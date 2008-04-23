package com.gever.goa.dailyoffice.smsmgr.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.smsmgr.vo.SMSCapacityVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 手机短信管理</p>
 * <p>Description:手机短信管理 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface SMSCapacityDAO {

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

    public List queryByPage(VOInterface vo, long start, long howMany) throws
        DefaultException;

    /**
     * 统计当前的数据
     * @param vo 当前vo对象
     * @return 统计数量
     * @throws DefaultException
     */
    public long queryByCount(VOInterface vo) throws DefaultException;

    /**
     * 得到默认
     * @return 默认的VO
     * @throws DefaultException
     */
    public SMSCapacityVO queryDefaultCapacity() throws DefaultException;


    /**
     * 设置所有人的短信条数
     * @param smsNumber 短信条数
     * @return 修改成功多少笔
     * @throws DefaultException
     */
    public int updateAll(int smsNumber,int type) throws DefaultException;

    /*
     * 设置选择人员的短信条数:注意有定时短发送的功能
     * @param aryData 选择的数据
     * @param smsNumber 短信条数
     * @return 修改成功多少笔
     * @throws DefaultException
     */
    public int update(String[] aryUser, String[] timing, int smsNumber,
                      int type) throws     DefaultException;

    /**
     * 按人员查出短信容量数据
     * @param userid 用户id
     * @return 按人员查出短信容量数据
     * @throws DefaultException
     */
    public SMSCapacityVO queryCapacityByUserId(String userid) throws
        DefaultException;

    public List getTreeData(String nodeid) throws DefaultException ;

    /**
    * 修改公司名称
    * @param vo 当前vo对象
    * @return 成功更新多少笔,-1为失败
    * @throws DefaultException
    */
   public int updateCorpName(VOInterface vo) throws DefaultException;
   /**
     * 得到当前公司名称
     * @return 公司名称
     * @throws DefaultException
     */
    public String queryCorpName() throws DefaultException ;


}