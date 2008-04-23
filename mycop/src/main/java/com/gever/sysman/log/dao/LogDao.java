package com.gever.sysman.log.dao;

import java.util.List;
import com.gever.vo.VOInterface;
import com.gever.exception.DefaultException;

/**
 * <p>Title: LogDao接口</p>
 * <p>Description: 主要有查询,删除,导出功能</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public interface LogDao {

    /**
     * 对外的接口
     * @param username 用户名称
     * @param module 模块名
     * @param action 动作
     * @param ipAddress ip地址
     * @param memo 备注
     * @param level 层级
     * @param type 类型
     * @return 返回新增成功记录数,-1为失败
     * @throws DefaultException
     */
    public int addLog(String username, String module, String action,
                      String ipAddress, String memo,int level, int type)  throws DefaultException;


    /**
     * 分页按条件查询
     * @param searchVo 查询时用的VO对象
     * @param start 开始标记
     * @param end 结束标记
     * @return 记录集对象
     * @throws DefaultException
     */
    public List queryByPage(VOInterface searchVo, long start , long end)  throws DefaultException;
    /**
     * 统计出当前的资料数
     * @param searchVo 查询时用的VO对象
     * @return 记录集数
     */
    public long queryByCount(VOInterface searchVo) ;
    /**
     * 查询所有的资料
     * @return 记录集对象
     * @throws DefaultException
     */
    public List queryAll()  throws DefaultException;

    /**
     * 删除所有的资料
     * @return 删除成功多少笔,-1为失败
     * @throws DefaultException
     */
     public int delBySelect(String[] aryPk) throws DefaultException ;

    /**
     * 删除条件的资料
     * @param searchVo 查询时用的VO对象
     * @return 删除成功多少笔,-1为失败
     * @throws DefaultException
     */
    public int delByCond(VOInterface searchVo) throws DefaultException;

    //===============================================================
    //胡勇添加，增加JSP视图列表排序功能
    public void setOrderby(String[] s) ;
    //===============================================================
}