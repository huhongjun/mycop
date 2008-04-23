package com.gever.goa.dailyoffice.mailmgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.dao.MailCapacityDAO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailCapacityVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.StringUtils;
import com.gever.util.SumUtils;
import com.gever.vo.VOInterface;

public class MailCapacityDAOImp extends BaseDAO implements MailCapacityDAO {
	public MailCapacityDAOImp(String dbData) {
		super(dbData);
	}

	private  String QUERY_UPDATE_MAILCAPACITY = "UPDATE mailcapacity set mail_capacity=?,capacity_flag=?,limit_flag=?,memo=? where 1=1 ";//更新mailcapacity

	private  String QUERY_UPDATE_TYPE = "String,String,String,String,";//mailcapacity
																			// 表的数据类型
	private String QUERY_ID="select id from t_department_person";

	//查询邮箱容量语句
	private  static String QUERY_SQL = "select t_user.id as user_code ,mailcapacity.mail_capacity,mailcapacity.capacity_flag,mailcapacity.limit_flag,mailcapacity.memo from t_user left join mailcapacity on t_user.id=mailcapacity.user_code where id not  in ( select user_code from mailcapacity )";

	//查询邮箱容量根据用户名语句
	private  String QUERY_BY_NAME = "select MAILCAPACITY.USER_CODE, MAILCAPACITY.MAIL_CAPACITY, MAILCAPACITY.CAPACITY_FLAG, MAILCAPACITY.LIMIT_FLAG, MAILCAPACITY.MEMO FROM  MAILCAPACITY left join t_user on mailcapacity.user_code =t_user.id where 1=1 ";

	public static  StringBuffer QUERY_CAPACITY_WITH_USER = new StringBuffer(
			"select MAILCAPACITY.user_code,MAILCAPACITY.mail_capacity,MAILCAPACITY.capacity_flag "
					+ " ,MAILCAPACITY.limit_flag,MAILCAPACITY.memo,mailinfo.usedmailsize,T_USER.name "
					+ " from T_USER left join MAILCAPACITY on T_USER.id = MAILCAPACITY.user_code "
					+ " left join (select mail.user_code,sum(mail.mail_size) as usedmailsize from mail GROUP BY mail.user_code) "
					+ " mailinfo on mailinfo.user_code = MAILCAPACITY.user_code where 1=1 ");

	private StringBuffer strWhere = new StringBuffer();

	MailCapacityVO vo = new MailCapacityVO();
	/**
	 *
	 */

	public void setOracleSQL(){
		QUERY_BY_NAME = "select MAILCAPACITY.USER_CODE, MAILCAPACITY.MAIL_CAPACITY, "
						+ " MAILCAPACITY.CAPACITY_FLAG, MAILCAPACITY.LIMIT_FLAG, MAILCAPACITY.MEMO "
						+" FROM  MAILCAPACITY , t_user where mailcapacity.user_code =t_user.id(+) ";
		QUERY_CAPACITY_WITH_USER.setLength(0);
		QUERY_CAPACITY_WITH_USER.append("select MAILCAPACITY.user_code,MAILCAPACITY.mail_capacity,MAILCAPACITY.capacity_flag ,"
									+" MAILCAPACITY.limit_flag,MAILCAPACITY.memo,mailinfo.usedmailsize,T_USER.name "
									+" from T_USER, MAILCAPACITY,(select mail.user_code,sum(mail.mail_size) as usedmailsize from mail GROUP BY mail.user_code) "
									+" mailinfo where T_USER.id = MAILCAPACITY.user_code(+) and "
									+" mailinfo.user_code(+) = MAILCAPACITY.user_code ");
                QUERY_SQL="select t_user.id as user_code ,mailcapacity.mail_capacity, "
                        +" mailcapacity.capacity_flag,mailcapacity.limit_flag,mailcapacity.memo "
                        +" from t_user , mailcapacity where t_user.id=mailcapacity.user_code(+) "
                        +" and  id  not  in ( select user_code from mailcapacity )";

	}

	/**
	 * 查询收件夹
	 *
	 * @param curMailDir
	 *            当前邮件夹
	 * @param mailType
	 *            邮件类型(0所有,1是内部,2是外部)
	 * @return 邮件列表
	 * @throws DefaultException
	 */
	public List queryByAll(String curMailDir, int mailType)
			throws DefaultException {
		List list = new ArrayList();
		return list;
	}

	/**
	 * 查询邮件统计数
	 *
	 * @param vo
	 *            当前邮件夹
	 * @return 统计数
	 * @throws DefaultException
	 */
	public long queryByCount(VOInterface vo) throws DefaultException {
		return 0;
	}

	/**
	 *
	 * @param vo
	 *            当前邮件vo对象
	 * @param start
	 *            开始
	 * @param howMany
	 *            查询多少笔
	 * @return 当前页的邮件列表
	 * @throws DefaultException
	 */
	public List queryByPage(VOInterface vo, long start, long howMany)
			throws DefaultException {
		List list = new ArrayList();
		return list;
	}

	/**
	 * 得默认的字节
	 *
	 * @return 得默认的字节
	 */
	public double getDefaultSize() {
		return 0;
	}

	/**
	 * 修改选择多少笔资料
	 *
	 * @param dblSize
	 *            容量
	 * @param aryUserId
	 *            选择用户
	 * @return 修改多少笔
	 * @throws DefaultException
	 */
	public int update(double dblSize, String[] aryUserId)
			throws DefaultException {
		int rowCount = 0;
		vo.setMail_capacity(String.valueOf(dblSize));
		for (int i = 0; i < aryUserId.length; i++) {
			vo.setUser_code(aryUserId[i]);
			vo.setLimit_flag("1");
			vo.setCapacity_flag("0");
			vo.setMemo("NULL");
			rowCount = super.update(vo);
		}
		return rowCount;
	}

	/**
	 * 修改所有
	 *
	 * @param dblSize
	 *            容量
	 * @return 修改多少笔
	 * @throws DefaultException
	 */
	public int setAllCapacity(double dblSize, String deptId)
			throws DefaultException {
		SQLHelper sHelper = new DefaultSQLHelper(super.dbData);
		int rowCount = 0;
		StringBuffer sb = new StringBuffer();
		String values[] = new String[4];
		String sbsql = "";
		//根据部门ID来更新记录
		if (!("0".equals(deptId) || StringUtils.isNull(deptId) || "false"
				.equals(deptId))) { //如果选择了一个部门或所有部门
			sb.append(this.QUERY_UPDATE_MAILCAPACITY);
			sb.append("and mailcapacity.user_code  in (");
			sb.append(this.QUERY_ID).append(")");
			sb.append(" where department_id = ").append(deptId).append(")");
		} else {
			sb.append(this.QUERY_UPDATE_MAILCAPACITY);
		}
		values[0] = String.valueOf(dblSize);
		values[1] = "0";
		values[2] = "1";
		values[3] = "NULL";
		try {
			rowCount = sHelper.execUpdate(sb.toString(), values,
					this.QUERY_UPDATE_TYPE);
		} catch (Exception e) {
			//System.out.println("----------执行出错----------");
			e.printStackTrace();
		}

		return rowCount;
	}

	/**
	 * 插入多条记录
	 *
	 * @param vo
	 * @return
	 * @throws DefaultException
	 */
	public int multiInsert(VOInterface vo) throws DefaultException {
		SQLHelper sHelper = new DefaultSQLHelper(super.dbData);
		sHelper.setAutoID(false);
		int rowCount = 0;
		try {
			//取到没有分配邮箱容量的用户
			List list = this.getUsers(vo);
			for (int i = 0; i < list.size(); i++) {
				//得到该用户的ID
				MailCapacityVO mailVo = (MailCapacityVO) list.get(i);
				//System.out.println("-------mailVO user_code
				// is:---------"+mailVo.getUser_code());
				//初始化一条记录
				mailVo.setUser_code(((MailCapacityVO) list.get(i))
						.getUser_code());
				mailVo.setMail_capacity("0");
				mailVo.setCapacity_flag("0");
				mailVo.setLimit_flag("1");
				mailVo.setMemo("NULL");
				rowCount = sHelper.insert(mailVo);
			}
			//rowCount = sHelper.multiInsert(list, vo);
		} catch (Exception e) {
			//System.out.println("----------multiInsert出错-----------");
			e.printStackTrace();
		}
		return rowCount;

	}

	/**
	 * 得到没有分配邮箱容量的用户
	 *
	 * @param vo
	 * @return
	 * @throws DefaultException
	 */
	public List getUsers(VOInterface vo) throws DefaultException {
		SQLHelper sHelper = new DefaultSQLHelper(super.dbData);
		List list = new ArrayList();
		try {
			list = sHelper.queryByListVo(this.QUERY_SQL, vo);
		} catch (Exception e) {
			//System.out.println("--------getUsers出错----------");
			e.printStackTrace();
		}
		return list;
	}

	public List getMailCapacityByUser(String userIds) throws DefaultException {
		List list = new ArrayList();
		if (userIds.equals("")) {
			userIds = "-1";
		}
		this.setStrWhere(" and T_USER.id in (" + userIds + ")");
		list = getMailCapacity();
		//System.out.println("--- getMailCapacityByUser list size is " +
		// list.size());
		return list;
	}

	public List getMailCapacity() throws DefaultException {
		//List list = new ArrayList();
		MailCapacityVO capacityVO = new MailCapacityVO();
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		StringBuffer sql = setSqlWithStrWhere();
		//list = helper.queryByListVo(sql.toString(),capacityVO);
		return helper.queryByListVo(sql.toString(), capacityVO);
	}
	
	public StringBuffer setSqlWithStrWhere() {
		String tempSql = QUERY_CAPACITY_WITH_USER.toString();
		//System.out.println("-- QUERY_CAPACITY_WITH_USER is " +
		// QUERY_CAPACITY_WITH_USER);
		if (this.strWhere.length() > 0) {
			tempSql += this.strWhere;
		}
		//System.out.println("----- temp sql is " + tempSql);
		return new StringBuffer(tempSql);
	}

	public boolean isOutOfCapacity(MailCapacityVO vo, long mailSize)
			throws DefaultException {
		//System.out.println("--- in isOutOfCapacity ---");
		boolean isOut = false;
		double dblValue = 0.0;

		String strSize = new String("");
		String strSubSize = new String("");
		String strUsedSize = new String("");
		//mailSize = mailSize < 100 * 1024 ? 100 * 1024 : mailSize;
		strSubSize = (Long.toString(mailSize));
		//strSubSize = SumUtils.format(SumUtils.sum(strSubSize +
		// "/(1024*1024)"),"0.00");
		System.out.println("------------- current mailsize is " + strSubSize
				+ " B=================");
		strUsedSize = SumUtils.sum(vo.getUsedmailsize() + "*(1024*1024)");
		System.out.println("------------- used mailsize is " + strUsedSize
				+ " B=================");
		System.out.println("------------- Mail_capacity is "
				+ SumUtils.sum(vo.getMail_capacity() + "*(1024*1024)")
				+ " B=================");
		String mail_capacity = SumUtils.sum(vo.getMail_capacity()
				+ "*(1024*1024)");
		strSize = SumUtils.sum(mail_capacity + "-(" + strUsedSize + "+"
				+ strSubSize + ")");
		System.out.println("---------- The result is " + strSize
				+ " B=================");
		dblValue = SumUtils.strToDouble(strSize);
		System.out.println("------------- dblValue is " + dblValue
				+ " =============");
		//        double size = 0l;
		//        double subSize = 0l;
		//        double usedSize = 0l;
		//        subSize = mailSize;
		//        usedSize = Double.parseDouble(vo.getUsedmailsize());
		//        size = (Double.parseDouble(vo.getMail_capacity()) - (usedSize +
		// subSize));
		//System.out.println("--- capacity is " + vo.getMail_capacity());
		//        //System.out.println("--- virtual size is " + usedSize + subSize);
		//        System.out.println("--- get size is " + size);
		//System.out.println("--- used mail size is " + strUsedSize);
		//System.out.println("--- get dblValue is " + dblValue);
		if (dblValue < 0.0)
			isOut = true;
		//System.out.println("--- isOutOfCapacity is " + isOut);
		return isOut;
	}

	public StringBuffer getStrWhere() {
		return strWhere;
	}

	public void setStrWhere(String strWhere) {
		this.strWhere = new StringBuffer();
		//if(this.strWhere.length() == 0) {
		//this.strWhere.append(" where 1=1 ");
		//}
		this.strWhere = this.strWhere.append(strWhere);
	}

	/**
	 * 根据用户名得到邮箱信息
	 *
	 * @param username
	 * @return
	 */
	public List getCapactiryByUser(String userName) throws DefaultException {
		SQLHelper sHelper = new DefaultSQLHelper(super.dbData);
		List list = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append(this.QUERY_BY_NAME);
		sb.append(" name=");
		sb.append("'" + userName + "'");
		MailCapacityVO mailVo = new MailCapacityVO();
		list = sHelper.queryByListVo(sb.toString(), mailVo);
		return list;
	}
}
