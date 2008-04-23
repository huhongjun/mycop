package com.gever.goa.dailyoffice.worklog.dao.impl;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.worklog.dao.WorkLogAdviceDAO;
import com.gever.goa.dailyoffice.worklog.vo.WorkLogAdviceVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.DateTimeUtils;
/**
 * <p>Title: </p>
 * <p>Description:意见数据操做对象，主要是对 DO_WORk_LOG_ADVICE 进行操作</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: GEVER</p>
 * @author Hu.Walker
 * @version 0.9
 */

public class WorkLogAdviceDAOImpl extends BaseDAO implements WorkLogAdviceDAO {

    public final static String QUERY_ADVICE_SQL = "select t.SERIAL_NUM, t.OP_DATE,t.ADVISER,t.ADVICE,t.REMARK, tu.username "
                                                + "\n from DO_WORk_LOG_ADVICE t,t_user tu "
                                                + "\n where t.adviser = tu.id ";
    public WorkLogAdviceDAOImpl() {
    }
    public WorkLogAdviceDAOImpl(String dbData) {
            super(dbData);
    }
    /**
     * 保存意见方法
     * @param string
     * @param adviser
     * @param advisername
     * @param words
     */
    public void saveAdvice(String SerialNum, String adviser, String words, String actionType) throws
        DefaultException {
        WorkLogAdviceVO adviceVo = new WorkLogAdviceVO();
        adviceVo.setSerial_num(SerialNum);
        adviceVo.setAdviser(adviser);
        adviceVo.setAdvice(words);
        adviceVo.setOp_date(DateTimeUtils.getCurrentDate());
        SQLHelper helper = new DefaultSQLHelper(this.dbData);
        if("new".equals(actionType)) {
            helper.insert(adviceVo);
        } else if("modify".equals(actionType)) {
            helper.update(adviceVo);
        }
    }
    /**
     * 取得所有意见
     * @param usercode
     * @return
     * @throws DefaultException
     */
    public List getAdviceListByLogId(String serialNum) throws DefaultException {
        String querySql = this.QUERY_ADVICE_SQL + " and t.SERIAL_NUM =" + serialNum;
        log.showLog("========>WorkLogAdviceDAOImpl.getAdviceListByLogId.querySql: \n" + querySql);
        SQLHelper helper = new DefaultSQLHelper(this.dbData);
        List adviceList = helper.queryByListVo(querySql,new WorkLogAdviceVO());
        return adviceList;
    }
    /**
     * 根据日志的编号和当前登录人id，取得当前登录人意见.
     * @param serialNumber
     * @param curUser
     * @return
     */
    public String getAdviceWordsByLogIdAndAdviser(String serialNumber,
                                                  String curUser) throws
        DefaultException {
        String querySql = this.QUERY_ADVICE_SQL
                        + " and t.SERIAL_NUM =" + serialNumber
                        + " and t.ADVISER =" + curUser;
        SQLHelper helper = new DefaultSQLHelper(this.dbData);
        WorkLogAdviceVO adviceVo = (WorkLogAdviceVO) helper.queryByVo(querySql,new WorkLogAdviceVO());
        return adviceVo.getAdvice();
    }
}