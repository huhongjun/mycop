package com.gever.goa.dailyoffice.mailmgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.dao.Pop3ConfigDAO;
import com.gever.goa.dailyoffice.mailmgr.vo.Pop3ConfigVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: Pop3邮件实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: 天行软件
 * </p>
 * 
 * @author Hu.Walker
 * @version 1.0
 */

public class Pop3ConfigDAOImp extends BaseDAO implements Pop3ConfigDAO {
	private static String QUERY_SQL = "SELECT POP3MAILSETUP.SERIAL_NUM, POP3MAILSETUP.USER_CODE, POP3MAILSETUP.POP3_NAME, POP3MAILSETUP.POP3_ADDRESS, POP3MAILSETUP.POP3_ACCOUNT, POP3MAILSETUP.POP3_PWD, POP3MAILSETUP.AUTO_FLAG, POP3MAILSETUP.DEL_FLAG, POP3MAILSETUP.INCEPT_MAIL_DIR, POP3MAILSETUP.SHOW_NAME, POP3MAILSETUP.SHOW_ADDRESS, POP3MAILSETUP.SMTP_SERVER,POP3MAILSETUP.SMTP_AUTH, POP3MAILSETUP.SMTP_NAME, POP3MAILSETUP.SMTP_PWD,POP3MAILSETUP.USE_FLAG ,maildirectory.mail_dir_name FROM POP3MAILSETUP left join maildirectory on POP3MAILSETUP.incept_mail_dir=maildirectory.serial_num where 1=1 ";

	private String QUERY_POP3_SQL = "SELECT SERIAL_NUM,USER_CODE,POP3_NAME,POP3_ADDRESS,"
			+ " POP3_ACCOUNT,POP3_PWD,AUTO_FLAG,DEL_FLAG,INCEPT_MAIL_DIR,"
			+ " SHOW_NAME,SHOW_ADDRESS,SMTP_SERVER,SMTP_AUTH,SMTP_NAME,SMTP_PWD,"
			+ " USE_FLAG FROM POP3MAILSETUP  where";

	public Pop3ConfigDAOImp(String dbData) {
		super(dbData);
	}

	public String getPageSql() {
		return this.QUERY_SQL;
	}
	
	public void setOracleSQL(){
		QUERY_SQL="SELECT POP3MAILSETUP.SERIAL_NUM, POP3MAILSETUP.USER_CODE, POP3MAILSETUP.POP3_NAME, "
					+" POP3MAILSETUP.POP3_ADDRESS, POP3MAILSETUP.POP3_ACCOUNT, " 	
					+" POP3MAILSETUP.POP3_PWD, POP3MAILSETUP.AUTO_FLAG, POP3MAILSETUP.DEL_FLAG, " 
					+" POP3MAILSETUP.INCEPT_MAIL_DIR, POP3MAILSETUP.SHOW_NAME, POP3MAILSETUP.SHOW_ADDRESS, " 
					+" POP3MAILSETUP.SMTP_SERVER,POP3MAILSETUP.SMTP_AUTH, POP3MAILSETUP.SMTP_NAME, " 
					+" POP3MAILSETUP.SMTP_PWD,POP3MAILSETUP.USE_FLAG ,maildirectory.mail_dir_name " 
					+ "FROM POP3MAILSETUP , maildirectory where POP3MAILSETUP.incept_mail_dir=maildirectory.serial_num(+) ";
	}
	/**
	 * 
	 * @param vo
	 * @return
	 * @throws DefaultException
	 */
	public VOInterface queryByPK(VOInterface vo) throws DefaultException {
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		Pop3ConfigVO pop3VO;
		pop3VO = (Pop3ConfigVO) helper.queryByPK(vo);
		return pop3VO;
	}

	//added by dongzg
	public long queryByCount(VOInterface vo) throws DefaultException {
		return super.queryByCount(vo);
	}

	/**
	 * 根据所选邮件设置主键得到Pop3设置信息
	 * 
	 * @param serialNums
	 *            String[]
	 * @throws DefaultException
	 * @return List
	 */
	public List queryListByPK(String[] serialNums) throws DefaultException {
		List result = new ArrayList();
		String serailNumbers = StringUtils.arrayToString(serialNums);
		String querySql = this.QUERY_POP3_SQL + " SERIAL_NUM in ("
				+ serailNumbers + ")";
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		Pop3ConfigVO pop3ConfigVO = new Pop3ConfigVO();
		result = helper.queryByListVo(querySql, pop3ConfigVO);
		return result;
	}

	public Pop3ConfigVO queryPop3ConfigInfoByPK(String serialNum)
			throws DefaultException {
		Pop3ConfigVO result = new Pop3ConfigVO();
		result.setSerial_num(serialNum);
		result = (Pop3ConfigVO) this.queryByPK(result);
		return result;
	}

	//added by dongzg
	/**
	 * 根据用户ID得到pop3configVO
	 * 
	 * @param userId
	 * @return
	 * @throws DefaultException
	 */
	public List getPop3ConfigByUserId(String userId) throws DefaultException {
		SQLHelper sHelper = new DefaultSQLHelper(super.dbData);
		StringBuffer sb = new StringBuffer();
		sb.append(this.QUERY_SQL);
		sb.append(" and POP3MAILSETUP.user_code=");
		sb.append(userId);
		sb
				.append(" and POP3MAILSETUP.use_flag <> '1' order by  POP3MAILSETUP.use_flag desc ");
		return (List) sHelper.queryByListVo(sb.toString(), new Pop3ConfigVO());
	}
}