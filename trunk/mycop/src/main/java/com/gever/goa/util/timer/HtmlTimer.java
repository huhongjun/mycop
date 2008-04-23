package com.gever.goa.util.timer;

import java.util.ArrayList;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.struts.Constant;
import com.gever.util.log.Log;
import com.gever.web.util.ActiveUsers;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: ÌìÐÐÈí¼þ
 * </p>
 *
 * @author Hu.Walker
 * @version 1.0
 */

public class HtmlTimer {
	Log log = Log.getInstance(HtmlTimer.class);
	ActiveUsers au = ActiveUsers.getInstance();
	private String QUERY_SQL = "SELECT a.ID,b.cnt as mailcnt,c.cnt as msgcnt \n"
					+ " FROM T_USER  a \n"
					+ " LEFT JOIN  \n"
					+ " (select user_code,count(*) as cnt  \n"
					+ "  from mail   \n"
					+ "  where mail.read_flag <> '1' \n"
					+ "  and mail.mail_dir_id = '0' \n"
					+ "  group by user_code)  b \n"
					+ " on b.user_code = a.id\n"
					+ "  left join \n"
					+ " ( select receiver,count(*) as cnt "
					+ "   from do_inner_msg \n"
					+ "   WHERE READ_FLAG=0 group  by receiver )  c \n"
					+ " on cast(a.id as char(30)) = c.receiver ";

	public HtmlTimer() {
		if (!au.isOracle()) {
			return;
		}
		QUERY_SQL = " SELECT a.ID,b.cnt as mailcnt,c.cnt as msgcnt  "
						+ " FROM T_USER  a 	,(select user_code,count(*) as cnt "
						+ " from mail    "
						+ " where mail.read_flag <> '1'  "
						+ " and mail.mail_dir_id = '0'  "
						+ " group by user_code)  b 	 , "
						+ "( select receiver,count(*) as cnt  "
						+ "  from do_inner_msg \n "
						+ " WHERE READ_FLAG=0 group  by receiver )  c  "
						+ "	where 	a.id = b.user_code(+) "
						+ " and trim(cast(a.id as char(30))) = c.receiver (+)  ";


	}

	public boolean checkTimer() {
		return true;
	}

	public void doSearch() throws DefaultException {

		SQLHelper helper = new DefaultSQLHelper(Constant.DATA_SOURCE);
		;

		List dataList = (ArrayList) helper.query(this.QUERY_SQL);
		ActiveUsers au = ActiveUsers.getInstance();
		au.setMsgMailData(dataList);
	}
}
