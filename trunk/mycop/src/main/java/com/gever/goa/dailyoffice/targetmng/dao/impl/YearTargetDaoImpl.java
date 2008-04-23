package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.targetmng.dao.YearTargetDao;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;

/**
 * Title: 年度目标Dao接口实现类 Description: 年度目标Dao接口实现类 Copyright: Copyright (c) 2004
 * Company: 天行软件
 *
 * @author Hu.Walker
 * @version 1.0
 */

public class YearTargetDaoImpl
    extends BaseDAO
    implements YearTargetDao {
    private static String QUERY_SQL = "SELECT serial_num,current_year,current_month,current_work,"
        + "target_type,dept_code,user_code,create_date,auditor,audit_date,"
        + "audit_flag,audit_opinion,check_flag,"
        + "checker,check_date,check_opinion,target_content FROM DO_TARGET_MANAGE "
        + "a left join t_user b on a.user_code=b.id where 1=1 ";

    //QUERY_SQL--定义翻页时用的查询语句
    public YearTargetDaoImpl(String dbData) {
        super(dbData);
    }

    public String getPageSql() {
        return QUERY_SQL;
    }

    public void setOracleSQL() {
        QUERY_SQL = "SELECT serial_num,current_year,current_month,current_work,"
            + "target_type,dept_code,user_code,create_date,auditor,audit_date,"
            + "audit_flag,audit_opinion,check_flag,"
            + "checker,check_date,check_opinion,target_content FROM DO_TARGET_MANAGE "
            + "a, t_user b  where a.user_code=b.id(+) ";
    }

    /**
     *重载BaseDAO中更新的方法
     * @param vo VOInterface
     * @throws DefaultException
     * @return int
     *  add by lihaidong
     */
    public int update(VOInterface vo) throws DefaultException {
        if (!super.isOracle()) {
            System.out.println("start db2 sql....");
            return super.update(vo);
        }
        System.out.println("start oracle sql ....");
        int row = 0;
        SQLHelper helper = new DefaultSQLHelper(this.dbData);
        try {
            helper.begin();
            //System.out.println("The serial_num is :"+ vo.getValue("serial_Num"));
            helper.setAutoID(false);
            helper.delete(vo);
            row = helper.insert(vo);
            helper.commit();
        } catch (DefaultException e) {
            helper.rollback();
            throw new DefaultException("更新出错");
        }
        finally {
            helper.end();
        }
        return row;

    }

    /**
     * 审核操作
     *
     * @param isPass
     *            是否通过审核-0-未通过审核-1-已通过审核
     * @param audit_date
     *            审核时间
     * @throws DefaultException
     */
    /*
     * public void doCheck(String isPass,String audit_date) throws
     * DefaultException {
     * System.out.println("-----------------------doCheck--------------");
     * SQLHelper helper = new DefaultSQLHelper(dbData); //StringBuffer sbSql =
     * new StringBuffer(); String sql = ""; String shzt =
     * "0";//审核状态，0表示未审核,1表示已通过审核,2表示未通过审核 if("1".equals(isPass)){//如果点击了通过 shzt =
     * "1";//将审核状态设为1 }else if("0".equals(isPass)){//如果点击了不通过 shzt =
     * "2";//将审核状态设为2 } sql = "UPDATE TARGETMAIN SET AUDIT_FLAG=?,AUDIT_DATE=?
     * WHERE SERIAL_NUM=? "; //sbSql.append("UPDATE TARGETMAIN SET
     * SPZT=?,SHSJ=?,SHR=? WHERE BH=? "); }
     */
    /**
     * 判断当前人是否拥有删除权限--只有创建人才有删除权限
     *
     * @param curUser
     *            当前用户
     * @param user_code
     *            创建人
     * @return int 1----拥有删除权限--0--不拥有删除权限
     * @throws DefaultException
     */
    public int judgeDelTag(String curUser, String user_code) throws DefaultException {
        int tag = 0;
        if (curUser.equals(user_code)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * 判断当前人是否拥有修改权限--只有创建人才有修改权限,并且要保证没有通过审核
     *
     * @param curUser
     *            当前用户
     * @param user_code
     *            创建人
     * @param audit_flag
     *            审核状态
     * @return int 1----拥有修改权限--0--不拥有修改权限
     * @throws DefaultException
     */
    public int judgeEditTag(String curUser, String user_code, String audit_flag) throws DefaultException {
        int tag = 0;
        if (curUser.equals(user_code) && "0".equals(audit_flag)
            || "2".equals(audit_flag)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * 判断当前人是否拥有审核权限--只有当前人为审核人时才具有审核权限，并且要保证审核和审批都没有通过
     *
     * @param curUser
     *            当前用户ID
     * @param auditor
     *            审核人ID
     * @param audit_flag
     *            审核状态
     * @param check_flag
     *            审批状态
     * @return int 1----拥有审核权限--0--不拥有审核权限
     * @throws DefaultException
     */
    public int judgeAuditTag(String curUser, String auditor, String audit_flag,
                             String check_flag) throws DefaultException {
        int tag = 0;
        if (curUser.equals(auditor) && "0".equals(audit_flag)
            && "0".equals(check_flag)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * 判断当前人是否拥有审批权限--只有当前人为审批人时才具有审批权限，并且要保证审核已通过而审批没有通过
     *
     * @param curUser
     *            当前用户ID
     * @param checker
     *            审批人ID
     * @param audit_flag
     *            审核状态
     * @param check_flag
     *            审批状态
     * @return int 1----拥有审核权限--0--不拥有审核权限
     * @throws DefaultException
     */
    public int judgeCheckTag(String curUser, String checker, String audit_flag,
                             String check_flag) throws DefaultException {
        int tag = 0;
        if (curUser.equals(checker) && "1".equals(audit_flag)
            && "0".equals(check_flag)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * 通过当前记录的审核和审批两个状态来显示审核审批状态的中文 注意：该方法后来移到了ConstantSet类中去实现了
     *
     * @param audit_flag
     *            审核状态
     * @param check_flag
     *            审批状态ConstantSet
     * @return String 可能的审核审批状况
     * @throws DefaultException
     */
    public String getAuditCheckName(String audit_flag, String check_flag) throws DefaultException {
        String retAuditCheckName = "";
        //排列组合
        //audit_flag-----0表示未审核,1表示已通过审核,2表示未通过审核
        //check_flag-----0表示未审批,1表示已通过审批,2表示未通过审批
        //如果审批通过则不能修改,审批不通过则审核人可以修改
        int audit_check_flag = 0;
        if ("0".equals(audit_flag) && "0".equals(check_flag)) {
            audit_check_flag = 0; //未审核
        }
        if ("1".equals(audit_flag) && "0".equals(check_flag)) {
            audit_check_flag = 1; //审核通过未审批
        }
        if ("2".equals(audit_flag) && "0".equals(check_flag)) {
            audit_check_flag = 2; //审核未通过
        }
        if ("1".equals(audit_flag) && "1".equals(check_flag)) {
            audit_check_flag = 3; //审批通过
        }
        if ("1".equals(audit_flag) && "2".equals(check_flag)) {
            audit_check_flag = 4; //审批未通过
        }
        switch (audit_check_flag) {
            case 0:
                retAuditCheckName = "未审核";
                break;
            case 1:
                retAuditCheckName = "审核通过未审批";
                break;
            case 2:
                retAuditCheckName = "审核未通过";
                break;
            case 3:
                retAuditCheckName = "审批通过";
                break;
            case 4:
                retAuditCheckName = "审批未通过";
                break;
            default:
                retAuditCheckName = "";
                break;
        }
        return retAuditCheckName;
    }

}
