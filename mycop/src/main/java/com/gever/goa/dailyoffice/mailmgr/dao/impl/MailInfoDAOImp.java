package com.gever.goa.dailyoffice.mailmgr.dao.impl;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.dao.MailInfoDAO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailInfoVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;

public class MailInfoDAOImp extends BaseDAO implements MailInfoDAO{
    private static String pageSql="select PAGENUMBER, USER_CODE, LABLE_NAME, POST_FLAG, SIGN_FLAG, RETURN_FLAG, SHOW_FLAG, MAIL_LEVEL FROM  MAILINFO  WHERE 1=1";
    public MailInfoDAOImp(String dbData) {
        super(dbData);
    }

    public String getPageSql(){
        return this.pageSql;
    }
    /**
     * 新增
     * @param vo 当前邮件参数vo对象
     * @return 当前邮件参数vo对象
     */
    public int insert(VOInterface vo) throws DefaultException {
//        SQLHelper sHelper=new DefaultSQLHelper();
  //      sHelper.setAutoID(false);
    //    return sHelper.insert(vo);
        super.setIsIdMng(false);
        return super.insert(vo);
    }



    /**
     * 新增
     * @param vo 当前邮件参数vo对象
     * @return 当前邮件参数vo对象
     */
    public int update(VOInterface vo) throws DefaultException {
        return super.update(vo);
    }

    /**
     * 查寻
     * @param vo 当前邮件参数vo对象
     * @return 当前邮件参数vo对象
     */

    public VOInterface queryByPK(VOInterface vo) throws DefaultException {
        MailInfoVO info = (MailInfoVO)super.queryByPk(vo);
       // System.out.println("--- info.lable_name : " + info.getLable_name());
        return info;
    }
    /**
     * 根据用户ID得到MailInfoVo
     * @param userId
     * @return
     */
    public MailInfoVO getMailInfoByUserId(String userId) throws
        DefaultException {
        SQLHelper sHelper= new DefaultSQLHelper(super.dbData);
        StringBuffer sb =new StringBuffer();
        sb.append(this.pageSql);
        sb.append(" and user_code=");
        sb.append(userId);
        return  (MailInfoVO)sHelper.queryByVo(sb.toString(),new MailInfoVO());
    }
}
