package com.gever.goa.dailyoffice.targetmng.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

/**
 * Title: 年度目标Dao接口
 * Description: 年度目标Dao接口
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */

public interface YearTargetDao {
    public int delBySelect(String[] aryPk, VOInterface vo) throws
        DefaultException; //删除

    public String getPageSql(); //翻页

    public int insert(VOInterface vo) throws DefaultException; //新增

    public int update(VOInterface vo) throws DefaultException; //修改

    public List queryAll(VOInterface vo) throws DefaultException; //查询所有

    public List queryByPage(VOInterface vo, long start, long howMany) throws
        DefaultException; //按页查询

    public long queryByCount(VOInterface vo) throws DefaultException; //按数查询

    public int copy(String[] keyValue, VOInterface vo) throws DefaultException; //复制

    public int judgeDelTag(String curUser, String user_code) throws DefaultException;
    //判断当前人是否拥有删除权限--只有创建人才有删除权限
    public int judgeEditTag(String curUser, String user_code, String audit_flag) throws DefaultException;
    //判断当前人是否拥有修改权限--只有创建人才有修改权限,并且要保证没有通过审核
    public int judgeAuditTag(String curUser, String auditor, String audit_flag, String check_flag) throws DefaultException;
    //判断当前人是否拥有审核权限--只有当前人为审核人时才具有审核权限，并且要保证审核和审批都没有通过
    public int judgeCheckTag(String curUser, String checker, String audit_flag, String check_flag) throws DefaultException;
    //判断当前人是否拥有审批权限--只有当前人为审批人时才具有审批权限，并且要保证审核已通过而审批没有通过
    public String getAuditCheckName(String audit_flag, String check_flag) throws DefaultException;
    //通过当前记录的审核和审批两个状态来显示审核审批状态的中文
}
