package com.gever.goa.dailyoffice.mailmgr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.dao.MailDirectoryDAO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailDirectoryVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.StringUtils;
import com.gever.util.SumUtils;
import com.gever.vo.VOInterface;

public class MailDirectoryDAOImp extends BaseDAO implements MailDirectoryDAO {
	public MailDirectoryDAOImp(String dbData) {
		super(dbData);
	}

	private static String COUNT_SQL = " SELECT count(*) AS CNT FROM ";

	private static String QUERY_SQL = "SELECT SERIAL_NUM, USER_CODE, MAIL_DIR_NAME, FLAG, MEMO FROM MAILDIRECTORY ";

	private static String QUERY_MAILDIRECTORY_SQL = "SELECT MAILDIRECTORY.SERIAL_NUM, MAILDIRECTORY.USER_CODE, "
			+ " MAILDIRECTORY.MAIL_DIR_NAME, MAILDIRECTORY.FLAG, MAILDIRECTORY.MEMO "
			+ " FROM MAILDIRECTORY where ";

	private static String QUERY_MAILDIR_SQL = "select a.serial_num,a.user_code,a.mail_dir_name,a.flag,a.memo,b.totalmailsize,b.totalmailnum,c.unreadmailnum "
			+ " from maildirectory a left join (select sum(mail_size) as totalmailsize,count(mail_dir_id) as totalmailnum,mail_dir_id from mail "
			+ " where mail.user_code = ? "
			+ "group by mail_dir_id) b on b.mail_dir_id = a.serial_num "
			+ " left join (select count(mail_dir_id) as unreadmailnum,mail_dir_id from mail "
			+ "where read_flag = '1' and mail.user_code = ? "
			+ " group by mail_dir_id) c on c.mail_dir_id = a.serial_num  where 1=1 ";

	public String condition = "";

	public String getPageSql() {
		return this.QUERY_SQL;
	}

	public void setOracleSQL(){
		QUERY_MAILDIR_SQL="select a.serial_num,a.user_code,a.mail_dir_name,a.flag,a.memo,b.totalmailsize,b.totalmailnum,c.unreadmailnum "
			 				+" from maildirectory a , (select sum(mail_size) as totalmailsize, "
							+" count(mail_dir_id) as totalmailnum,mail_dir_id from mail "
							+" where mail.user_code = ? "
							+" group by mail_dir_id) b ,"
							+" (select count(mail_dir_id) as unreadmailnum,mail_dir_id from mail "
							+" where read_flag = '1' and mail.user_code = ? "
							+" group by mail_dir_id) c "
							+" where  a.serial_num =b.mail_dir_id(+) and a.serial_num=c.mail_dir_id(+)";
	}

	/**
	 * 新增邮件夹 新增过程中发现有重名则抛出异常，新增不继续
	 *
	 * @param vo
	 *            当前邮件参数vo对象
	 * @return 当前邮件参数vo对象
	 */
	public int insert(VOInterface vo) throws DefaultException {
		int result = 0;
		vo.setValue("user_code", super.userID);
		checkIsDirNameRepeated(vo);
		result = super.insert(vo);
		return result;
	}

	private void checkIsDirNameRepeated(VOInterface vo) throws DefaultException {
		MailDirectoryVO dirVo = (MailDirectoryVO) vo;
		String conditionStr = " where mail_dir_name = " + "'"
				+ dirVo.getMail_dir_name() + "' and user_code = '"
				+ super.userID + "' ";
		this.setCondition(conditionStr);
		if (this.isRepeated(vo, condition)) {
			throw new DefaultException("邮件夹重复,请重新更改邮件夹名称！");
		}
	}

	/**
	 * 修改邮件夹 修改过程中发现有重名则抛出异常，修改不继续
	 *
	 * @param vo
	 *            当前邮件参数vo对象
	 * @return 当前邮件参数vo对象
	 */
	public int update(VOInterface vo) throws DefaultException {
		int result = 0;
		vo.setValue("user_code", super.userID);

		MailDirectoryVO directoryVo = (MailDirectoryVO) vo;
		//System.out.println("--- flag is " + directoryVo.getFlag());
		if (directoryVo.getOldDirectoryName().trim().equals(
				directoryVo.getMail_dir_name().trim()) == false) {
			checkIsDirNameRepeated(vo);
		}

		result = super.update(vo);
		return result;
	}

	/**
	 * 按照主键查寻邮件夹
	 *
	 * @param vo
	 *            当前邮件参数vo对象
	 * @return 当前邮件参数vo对象
	 */

	public VOInterface queryByPK(VOInterface vo) throws DefaultException {
		return (VOInterface) super.queryByPk(vo);
	}

	public MailDirectoryVO queryBySerialNum(String serialNum)
			throws DefaultException {
		MailDirectoryVO dirVo = new MailDirectoryVO();
		dirVo.setSerial_num(serialNum);
		return (MailDirectoryVO) queryByPK(dirVo);
	}

	/**
	 * 查询所有邮件邮件夹
	 *
	 * @param vo
	 *            当前邮件夹
	 * @return 查询所有邮件
	 * @throws DefaultException
	 */

	public List queryAll(VOInterface vo) throws DefaultException {
		return super.queryAll(vo);
	}

	/**
	 * 查询所有邮件邮件夹
	 *
	 * @param vo
	 *            当前邮件夹
	 * @return 查询所有邮件
	 * @throws DefaultException
	 */

	public List queryAllExceptCurrentDir(String mailDirId, String userID)
			throws DefaultException {
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		String sql = this.QUERY_MAILDIRECTORY_SQL
				+ "(MAILDIRECTORY.USER_CODE = '000000' or MAILDIRECTORY.USER_CODE = '"
				+ userID
				+ "') and MAILDIRECTORY.serial_num <> '"
				+ mailDirId
				+ "' order by MAILDIRECTORY.user_code,MAILDIRECTORY.serial_num asc ";
		MailDirectoryVO mailDirVO = new MailDirectoryVO();

		return helper.queryByListVo(sql, mailDirVO);
	}

	public List queryAllMailDir(String userId) throws DefaultException {
		//         System.out.println("--- in queryAllMailDir --- user is " + userId);
		List mailDirList = new ArrayList();
		StringBuffer queryMailDirSql = new StringBuffer();
		initQueryMailDirSql(queryMailDirSql);
		if (!StringUtils.isNull(this.condition)) {
			queryMailDirSql.append(this.getCondition());
		} else {
			queryMailDirSql
					.append(" and (a.user_code = '000000' or a.user_code = ?) order by cast(a.user_code as int),a.serial_num");
		}
		//        System.out.println("--- queryMailDirSql : " + queryMailDirSql);
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = helper.getConnection();
		try {
			pstmt = conn.prepareStatement(queryMailDirSql.toString());
				log.console(this.getClass(),"queryAllMailDir:SQL-",queryMailDirSql.toString());
			pstmt.setInt(1, Integer.parseInt(userId));
			pstmt.setInt(2, Integer.parseInt(userId));
				log.console(this.getClass(),"queryAllMailDir:SQL",condition);

			if (StringUtils.nullToString(this.condition).equals("")) {
				pstmt.setString(3, userId);
			}
			rs = pstmt.executeQuery();

			int sizeCount = 0; // 邮件字节总统计数
			int yjCount = 0; // 邮件总统计数
			int notYjCount = 0; // 已读邮件总统计数
			int iTmpYjCNT = 0; // 临时邮件统计数
			int iTmpNotYjCNT = 0; // 临时未读邮件

			while (rs.next()) {
				int i = 1;
				MailDirectoryVO mailDirectoryVO = new MailDirectoryVO();
				mailDirectoryVO.setSerial_num(rs.getString(i++));
				mailDirectoryVO.setUser_code(rs.getString(i++));
				mailDirectoryVO.setMail_dir_name(rs.getString(i++));
				mailDirectoryVO.setFlag(rs.getString(i++));
				mailDirectoryVO.setMemo(rs.getString(i++));
				sizeCount += Integer.parseInt(StringUtils.emptyStringToZero(rs
						.getString("totalmailsize")));
				iTmpYjCNT = Integer.parseInt(StringUtils.emptyStringToZero(rs
						.getString("totalmailnum")));
				yjCount += Integer.parseInt(StringUtils.emptyStringToZero(rs
						.getString("totalmailnum")));
				notYjCount += Integer.parseInt(StringUtils.emptyStringToZero(rs
						.getString("unreadmailnum")));
				iTmpNotYjCNT = iTmpYjCNT
						- Integer.parseInt(StringUtils.emptyStringToZero(rs
								.getString("unreadmailnum")));
				mailDirectoryVO.setTotalmailsize(this.sizeToKB(rs
						.getString("totalmailsize")));
				mailDirectoryVO.setTotalmailnum(String.valueOf(iTmpYjCNT));
				mailDirectoryVO.setUnreadmailnum(String.valueOf(iTmpNotYjCNT));
				mailDirList.add(mailDirectoryVO);
			}
			MailDirectoryVO mailDirectoryVO = new MailDirectoryVO();

			mailDirectoryVO.setUser_code("COUNT");
			mailDirectoryVO.setMail_dir_name("邮件总数：");
			mailDirectoryVO.setFlag("1");
			mailDirectoryVO
					.setTotalmailsize(sizeToKB(String.valueOf(sizeCount)));
			mailDirectoryVO.setTotalmailnum(String.valueOf(yjCount));
			mailDirectoryVO.setUnreadmailnum("已占用空间：");

			mailDirList.add(mailDirectoryVO);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			helper.close(conn);
		}
		return mailDirList;
	}

	private void initQueryMailDirSql(StringBuffer queryMailDirSql) {
		queryMailDirSql
				.append(this.QUERY_MAILDIR_SQL);
	}

	public MailDirectoryVO queryMailDirByUserAndDirId(String userId,
			String mailDirId) throws DefaultException {
		String condition = " and a.serial_num = '" + mailDirId + "'";
		this.setCondition(condition);
		MailDirectoryVO mailDirVO = (MailDirectoryVO) this.queryAllMailDir(
				userId).get(0);
		return mailDirVO;
	}

	/**
	 * 字节数字转换 自动转换加kb或Byte
	 *
	 * @param strSize
	 *            数字字串
	 * @return 字节转换
	 */
	private String sizeToKB(String strSize) {
		long lngSize = 0l;
		lngSize = SumUtils.strToLong(strSize);
		StringBuffer sBufSize = new StringBuffer();
		if (lngSize < 1024)
			return sBufSize.append(lngSize).append("Byte").toString();
		strSize = SumUtils.sum(strSize + "/1024");
		if (SumUtils.strToDouble(strSize) > 1000)
			strSize = SumUtils.format(strSize, "0,000.0");
		else
			strSize = SumUtils.format(strSize, "0.0");
		sBufSize.append(strSize).append("KB");
		return (sBufSize.toString());
	}

	public boolean isRepeated(VOInterface vo, String condition)
			throws DefaultException {
		super.setWhere(condition);
		return super.queryByCount(vo) > 0;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List queryDirectoryByUserId(String userId) throws DefaultException {
		SQLHelper sHelper = new DefaultSQLHelper(super.dbData);
		List list = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append(this.QUERY_SQL);
		sb
				.append(" where (maildirectory.user_code = '000000' or maildirectory.user_code = ");
		sb.append("'").append(userId).append("'");
		sb
				.append(") order by cast(maildirectory.user_code as int),maildirectory.serial_num");
		//        System.out.println(sb.toString());
		list = sHelper.queryByListVo(sb.toString(), new MailDirectoryVO());
		return list;
	}
}
