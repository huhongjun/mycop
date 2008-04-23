package com.gever.goa.dailyoffice.mailmgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.dao.MailboxMgrDAO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailboxTreeMgrVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.StringUtils;

public class MailboxMgrImp extends BaseDAO implements MailboxMgrDAO {

	private static String TREE_SQL = "Select id as nodeid,name as nodename,case "
			+ "when (Select COUNT(*) AS CNT from T_DEPARTMENT b  WHERE "
			+ "T_DEPARTMENT.id<>b.id AND b.parent_id=T_DEPARTMENT.id  ) >0 "
			+ "then '1' else '0' end as isfolder from T_DEPARTMENT where  1=1 ";

	private  static String QUERY_SQL = "select t_user.id as user_code,t_user.name as name,MAILCAPACITY.mail_capacity as mail_capacity, "
			+ " MAILCAPACITY.capacity_flag as capacity_flag ,MAILCAPACITY.limit_flag as limit_flag,MAILCAPACITY.memo as memo,mailinfo.usedmailsize as usedmailsize "
			+ " from t_user left join MAILCAPACITY on T_USER.id = MAILCAPACITY.user_code "
			+ " left join (select mail.user_code,sum(mail.mail_size) as usedmailsize "
			+ " from mail GROUP BY mail.user_code) mailinfo "
			+ " on mailinfo.user_code = MAILCAPACITY.user_code where 1=1 ";

	public String getPageSql() {
		return this.QUERY_SQL;
	}
	/**
	 * 设置ORACLE 语句
	 *
	 */
	public void setOracleSQL(){
		QUERY_SQL="select t_user.id as user_code,t_user.name as name,MAILCAPACITY.mail_capacity as mail_capacity, "
			+" MAILCAPACITY.capacity_flag as capacity_flag ,MAILCAPACITY.limit_flag as limit_flag,MAILCAPACITY.memo as memo,mailinfo.usedmailsize as usedmailsize "
			+" from t_user, MAILCAPACITY,(select mail.user_code,sum(mail.mail_size) as usedmailsize "
			+" from mail GROUP BY mail.user_code) mailinfo "
			+" where T_USER.id = MAILCAPACITY.user_code (+) "
			+" and  mailinfo.user_code(+) = MAILCAPACITY.user_code ";

	}

	public MailboxMgrImp(String dbData) {
		super(dbData);
	}

	public List getTreeData(String nodeid) throws DefaultException {
		List treeData = new ArrayList();
		String strWhere = "";
		if ("0".equals(nodeid) || StringUtils.isNull(nodeid)) { //判断0--根结点
			strWhere = " and parent_id = 0 "; //获取第一级的目录节点
			//  System.out.println("----------parent_ID is 0----------");
		} else {
			strWhere = " and parent_id =" + nodeid; //否则获取拥有父节点的那些节点
			//  System.out.println("parent_id is :"+nodeid);
		}
		MailboxTreeMgrVO vo = new MailboxTreeMgrVO();
		SQLHelper helper = helper = new DefaultSQLHelper(dbData);
		;
		treeData = (ArrayList) helper.queryByListVo(TREE_SQL + strWhere, vo);
		return treeData;

	}

}
