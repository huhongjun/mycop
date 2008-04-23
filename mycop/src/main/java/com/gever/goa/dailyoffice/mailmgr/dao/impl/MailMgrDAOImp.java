package com.gever.goa.dailyoffice.mailmgr.dao.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.dao.MailCapacityDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailConstant;
import com.gever.goa.dailyoffice.mailmgr.dao.MailInfoDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrFactory;
import com.gever.goa.dailyoffice.mailmgr.dao.Pop3MailMgrDAO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailAttchVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailCapacityVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailErrorInfoVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailInfoVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailVO;
import com.gever.goa.dailyoffice.mailmgr.vo.Pop3ConfigVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.sysman.api.OrganizationUtil;
import com.gever.util.DateTimeUtils;
import com.gever.util.IdMng;
import com.gever.vo.VOInterface;

public class MailMgrDAOImp extends BaseDAO implements MailMgrDAO {
	public MailMgrDAOImp(String dbData) {
		super(dbData);
	}

	MailCapacityDAO capacityDao = null;

	MailInfoDAO mailInfoDao = null;

	Pop3MailMgrDAO pop3MailMgrDao = null;

	List attachList = null;

	List errorList = new ArrayList();

	String mailTo = "";

	private static  String webReturn = "<br>";

	private static  String webSpace = "&nbsp;";

	private static  String SEPERATE_LINE = "----------------------------------------------------------------------";

	private static  String FILE_PATH_COLUM_NAME = "file_path";

	private static  String CHANGE_MAIL_READ_STATE_SQL = "UPDATE mail set read_flag = '1' where serial_num = ?";//修改邮件阅读标志

	private static  String GET_MAIL_ATTACH_LIST_SQL = "SELECT MAILATTCH.SERIAL_NUM,MAILATTCH.MAIL_ID, "
			+ " MAILATTCH.ATTCH_NAME,MAILATTCH.MEMO,MAILATTCH.FILE_PATH "
			+ " FROM MAILATTCH WHERE MAILATTCH.MAIL_ID =?";//查询邮件附件列表

	private static  String DELETE_FROM_MAIL_SQL = "delete from mail where serial_num = ?";//删除邮件

	private static  String DELETE_FROM_MAILAttch_SQL = "delete from mailAttch where mail_id = ?";//删除邮件附件

	private static  String CHECK_CAN_DELETE_FILE_SQL = "select count(file_path) as cnt,file_path,attch_name from mailattch";//查看能否删除物理附件

	private static  String CHECK_CAN_DELETE_FILE_SQL_GROUP_BY = " group by file_path,attch_name";//查看是否能删除物理附件sql后缀

	private static  String MOVE_MAIL_DIR_SQL = "UPDATE MAIL SET MAIL_DIR_ID = ?, "
			+ "OLD_MAILDIR_ID = (select mail_dir_id from mail where serial_num = ?) "
			+ "WHERE  SERIAL_NUM =?";//修改邮件所在邮件夹

	private static  String REVERT_MAIL_DIR_SQL_1 = "UPDATE MAIL SET MAIL_DIR_ID = "
			+ "(select OLD_MAILDIR_ID from mail where serial_num = ";

	private static  String REVERT_MAIL_DIR_SQL_2 = "), OLD_MAILDIR_ID = "
			+ "'" + MailConstant.DIR_GARBAGE_FOLDER + "'"
			+ " WHERE SERIAL_NUM =";//从垃圾箱还原邮件

	private static  String GET_MAIL_IN_GARBAGE_DIR = "select serial_num from mail where MAIL_DIR_ID = '"
			+ MailConstant.DIR_GARBAGE_FOLDER + "' and mail.user_code = ";//查找垃圾箱中邮件

	private static  String GET_MAIL_INFO_IN_MAIL_DIR = "SELECT MAIL.USER_CODE, MAIL.SERIAL_NUM, MAIL.POST_USERNAME, "
			+ " MAIL.POST_ADDRESS, MAIL.RECEIVE_ADDRESS, MAIL.COPY_SEND, "
			+ " MAIL.DENSE_SEND, MAIL.SEND_DATE, MAIL.TITLE, "
			+ " MAIL.OLD_MAILDIR_ID, MAIL.MAIL_DIR_ID, MAIL.RE_FLAG, "
			+ " MAIL.READ_FLAG, MAIL.CONTENT, MAIL.MAIL_TYPE, "
			+ " MAIL.PRIORITY, MAIL.MAIL_SIZE ,attaches.attachcount "
			+ " FROM MAIL left join (select mail_id,count(mail_id) as attachcount from mailattch group by mail_id) attaches "
			+ " on mail.serial_num = attaches.mail_id where 1=1 ";//查找相应用户邮箱中的邮件信息

	//+ " where mail.mail_dir_id = ? and mail.user_code = ? ";

	public String getPageSql() {
		return GET_MAIL_INFO_IN_MAIL_DIR;
	}
	/**
	 * ORACLE语句设置
	 */
	public void setOracleSQL(){
		GET_MAIL_INFO_IN_MAIL_DIR="SELECT MAIL.USER_CODE, MAIL.SERIAL_NUM, MAIL.POST_USERNAME, "
			 						+" MAIL.POST_ADDRESS, MAIL.RECEIVE_ADDRESS, MAIL.COPY_SEND, "
									+" MAIL.DENSE_SEND, MAIL.SEND_DATE, MAIL.TITLE, "
									+" MAIL.OLD_MAILDIR_ID, MAIL.MAIL_DIR_ID, MAIL.RE_FLAG, "
									+" MAIL.READ_FLAG, MAIL.CONTENT, MAIL.MAIL_TYPE, "
									+" MAIL.PRIORITY, MAIL.MAIL_SIZE ,attaches.attachcount "
									+" FROM MAIL,(select mail_id,count(mail_id) as attachcount from mailattch group by mail_id) attaches "
									+" where mail.serial_num = attaches.mail_id(+) ";
	}

	/**
	 * 发送邮件
	 *
	 * @param vo
	 *            当前填写邮件的信息
	 * @param attchList
	 *            附件列表
	 * @param actionType
	 *            动作类型
	 * @return 发送后的信息
	 * @throws DefaultException
	 */
	public Map sendMail(MailVO mailVO, List attchList, int actionType)
			throws DefaultException {
		Map map = new HashMap();
		String addressToSend = "";
		String currentDate = "";
		String users[] = new String[2];
		String unableSender = "";
		long attachFileSize = 0l;
		currentDate = DateTimeUtils.getCurrentDateTime();
		MailErrorInfoVO errorVO = new MailErrorInfoVO();
		this.setMailTo(mailVO.getReceive_address()
				+ ("".equals(mailVO.getCopy_send()) ? "" : ",")
				+ mailVO.getCopy_send()
				+ ("".equals(mailVO.getDense_send()) ? "" : ",")
				+ mailVO.getDense_send()); //取得要发送的人的名单
		this.setAttachList(attchList); //设置附件组
		attachFileSize = getAttachFileSize(attchList);
		initParams(actionType); //根据actionType初始化各个标志位
		//System.out.println("--- mail type is pop3 mail is " + this.bIncWeb);
		setMailType(mailVO);//设置邮件类型
		mailVO.setSend_date(currentDate);//设置邮件日期
		mailVO.setRead_flag("0");
		//mailVO.setPost_address();
		/** @todo 设置发件人地址和姓名 */
		mailVO.setMail_size(String.valueOf(attachFileSize
				+ mailVO.getTitle().length() + mailVO.getContent().length())); //设置邮件大小
		//保存草稿
		if (this.bSave) {
			//System.out.println("--- 保存草稿 ---");
			isSaveOwnFileSuccsseed(mailVO, errorVO);
		} else { //发送邮件

			setMailContentWithSign(mailVO); //设置是否加上签名
			if (this.bSaveBak) { //保存到寄件夹
				//System.out.println("--- 保存到寄件夹 ---");
				if (isSaveOwnFileSuccsseed(mailVO, errorVO) == false) {
					map.put("errorInfo", errorVO);
					return map;
				}
				//mailVO.setMail_dir_id(MailConstant.DIR_RECIEVE_FOLDER);
			}
			//System.out.println("--- 当前邮件为内部邮件 ：" + bIncInside);
			//System.out.println("--- 当前邮件为外部邮件 ：" + bIncWeb);
			if (bIncInside) {
				users = getSplitedRecievers(this.getMailTo(), mailVO);
				unableSender = users[1];
				//取得可发送人员姓名
				addressToSend = users[0];
				this.setMailTo(addressToSend);
				//System.out.println("--- 发送信件给 ： " + addressToSend);
				sendMailByType(MailConstant.INNER_MAIL, mailVO, (HashMap) map);
			}
			if (bIncWeb) {
				//System.out.println("--- 发送外部邮件 处理 ---");
				this.setMailNote(mailVO.getContent());
				sendMailByType(MailConstant.POP3_MAIL, mailVO, (HashMap) map);
			}
		}
		//保存出错人员信息，以备重发时使用，并把actionType中保存到寄件夹的标志位清空
		if (errorVO.getErrorMsg().length() > 0) {
			mailVO.setReceive_address(unableSender);//处理邮箱检查时邮箱不够的人员
			/** @todo 如果发送期间出错并回滚，导致未发送邮件的人员如何处理 */
			if (this.bSaveBak) {
				actionType -= MailConstant.SAVE_POST_MAIL;
			}
		}
		map.put("actionType", Integer.toString(actionType));
		map.put("errorInfo", errorVO);
		map.put("errorInfoList", errorList);
		return map;
	}

	private void setMailType(MailVO mailVO) {
		String mailType = bIncWeb ? String.valueOf(MailConstant.POP3_TYPE)
				: String.valueOf(MailConstant.INNER_TYPE);
		mailVO.setMail_type(mailType);
	}

	private void setPop3MailMgrDao() {
		pop3MailMgrDao = MailMgrFactory.getInstance().creatPop3MailMgr(
				super.dbData);
	}

	/**
	 * 取得附件大小
	 *
	 * @param attchList
	 * @return
	 */
	private long getAttachFileSize(List attchList) {
		long attachFileSize = 0l;
		if (attchList.size() > 0) {
			for (int i = 0; i < attchList.size(); i++) {
				attachFileSize += ((MailAttchVO) attchList.get(i))
						.getFileSize();
			}
		}
		return attachFileSize;
	}

	/**
	 * 是否保存本地邮件成功
	 *
	 * @param mailVO
	 * @param errorVO
	 * @return
	 * @throws DefaultException
	 */
	private boolean isSaveOwnFileSuccsseed(MailVO mailVO,
			MailErrorInfoVO errorVO) throws DefaultException {
		boolean result = true;
		MailVO bakMailVo = initBakMailVO(mailVO);
		boolean isOutCapacity = isSavaOwnFileOutOfCapacity(bakMailVo, errorVO);
		if (isOutCapacity == false) {
			result = this.saveOwnMailByActionType(bakMailVo);
		}
		return result;
	}

	private MailVO initBakMailVO(MailVO mailVO) {
		MailVO bakMailVo = new MailVO();
		//System.out.println("\n--- mail priority is " + mailVO.getPriority());
		bakMailVo.setPriority(mailVO.getPriority());
		bakMailVo.setSend_date(mailVO.getSend_date());
		bakMailVo.setMail_type(mailVO.getMail_type());
		bakMailVo.setUser_code(mailVO.getUser_code());
		bakMailVo.setMail_size(mailVO.getMail_size());
		bakMailVo.setTitle(mailVO.getTitle());
		bakMailVo.setContent(mailVO.getContent());
		bakMailVo.setMail_type(mailVO.getMail_type());
		bakMailVo.setPost_address(mailVO.getPost_address());
		bakMailVo.setPost_username(mailVO.getPost_username());
		bakMailVo.setReceive_address(mailVO.getReceive_address());
		bakMailVo.setCopy_send(mailVO.getCopy_send());
		bakMailVo.setDense_send(mailVO.getDense_send());
		//System.out.println("--- mail type is " + bakMailVo.getMail_type());
		return bakMailVo;
	}

	/**
	 * 根据动作类型保存邮件
	 *
	 * @param mailVO
	 * @return
	 * @throws DefaultException
	 */
	private boolean saveOwnMailByActionType(MailVO bakMailVO)
			throws DefaultException {
		boolean result = true;
		if (this.bSave) {
			bakMailVO.setMail_dir_id(MailConstant.DIR_DRAFT_FOLDER);
		} else if (this.bSaveBak) {
			bakMailVO.setMail_dir_id(MailConstant.DIR_SENDING_FOLDER);
			//bakMailVO.setPost_address("");
			//bakMailVO.setPost_username("");
		}// else {
		bakMailVO.setRead_flag("1");//设置为已读邮件
		bakMailVO.setRe_flag("0");
		//}
		if (this.bSave || this.bSaveBak) {
			int count = 0;
			result = this
					.insertMail(bakMailVO, bakMailVO.getUser_code(), count);
		}
		return result;
	}

	/**
	 * 根据类型发送邮件
	 *
	 * @param type
	 * @param mailVO
	 * @throws DefaultException
	 */
	private void sendMailByType(int type, MailVO mailVO, HashMap mailMap)
			throws DefaultException {
		switch (type) {
		case MailConstant.INNER_MAIL:
			this.sendInnerMail(mailVO);
			break;
		case MailConstant.POP3_MAIL:
			this.sendPop3Mail(mailVO, mailMap);
			break;
		}
	}

	/**
	 * 初始化动作
	 *
	 * @param actionType
	 */
	private void initParams(int actionType) {
		setBIncFW(actionType);//设置转发标志
		setBIncWeb(actionType);//设置是否为外部
		setBIncInside();//设置是否为内部
		setBlnReMail(actionType);//设置是否为回复邮件
		setBlnRet(actionType);//设置是否自动回复
		setBSave(actionType);//设置是否保存草稿
		setBSaveBak(actionType);//设置是否保存到寄件夹
		setBlnAddSign(actionType);//设置是否附带签名
		setIsReplyToAll(actionType);//设置是否给所有人回信
		setIsReplyWithOriginal(actionType);//设置是否回信附带原件
	}

	/**
	 * sendPop3Mail
	 */
	private void sendPop3Mail(MailVO mailVO, HashMap mailMap) {
		setPop3MailMgrDao();
		mailMap.put("view", this.getPop3ConfigVo());
		mailMap.put("mainNote", mailVO.getContent());
		//System.out.println("--- mail reciever address is " +
		// mailVO.getReceive_address());
		//System.out.println("--- serverPath is " + serverPath);
		String pop3SendResult = "";
		pop3MailMgrDao.setServerPath(this.serverPath);
		pop3SendResult = pop3MailMgrDao.sendMails(mailMap, mailVO,
				(ArrayList) this.attachList);
		mailMap.put("pop3SendResult", pop3SendResult);
	}

	/**
	 * sendInnerMail
	 */
	private void sendInnerMail(MailVO mailVO) throws DefaultException {
		// if(saveOwnMailByType(mailVO)) {
		sendOutMails(mailVO);
		//}
	}

	/**
	 * 发送邮件，内部邮件
	 *
	 * @param mailVO
	 * @throws DefaultException
	 */
	private void sendOutMails(MailVO mailVO) throws DefaultException {
		StringTokenizer tknUsers = new StringTokenizer(this.getMailTo(), ",");
		//System.out.println("------- mail to is " + this.getMailTo());
		setReFlag(mailVO);//设置回复
		mailVO.setMail_type(String.valueOf(MailConstant.INNER_TYPE));//设置邮件类型
		mailVO.setMail_dir_id(MailConstant.DIR_RECIEVE_FOLDER); //设置邮件所在邮件夹
		int count = 1;
		while (tknUsers.hasMoreElements()) {
			String user = tknUsers.nextToken();
			//mailVO.setPost_username();
			if (mailVO.getDense_send().length() > 0) {
				//System.out.println("--- has dense send ---");
				//System.out.println("--- current send is : " + user);
				//System.out.println("--- reciever is : " +
				// mailVO.getReceive_address());
				//System.out.println("--- dense send is " +
				// mailVO.getDense_send());
				String userIds = "," + mailVO.getDense_send() + ",";
				if (userIds.indexOf(user) > -1) {
					MailVO denseVo = cloneMailInfo(mailVO);
					denseVo.setReceive_address(mailVO.getDense_send());
					denseVo.setCopy_send("");
					denseVo.setDense_send("");
					insertMail(denseVo, user, count);
					//System.out.println("------ after change receiver in mail
					// vo is : " + mailVO.getReceive_address());
					//System.out.println("------ receiver in dense vo is : " +
					// denseVo.getReceive_address());

				} else {
					MailVO receiverVo = cloneMailInfo(mailVO);
					receiverVo.setDense_send("");
					insertMail(receiverVo, user, count);
				}
			} else {
				insertMail(mailVO, user, count);
			}
			count++;
		}
	}

	private MailVO cloneMailInfo(MailVO mailVO) {
		MailVO denseVo = new MailVO();
		denseVo.setAttachcount(mailVO.getAttachcount());
		denseVo.setAttachList(mailVO.getAttachList());
		denseVo.setContent(mailVO.getContent());
		denseVo.setCopy_send(mailVO.getCopy_send());
		denseVo.setDense_send(mailVO.getDense_send());
		denseVo.setMail_dir_id(mailVO.getMail_dir_id());
		denseVo.setMail_size(mailVO.getMail_size());
		denseVo.setMail_type(mailVO.getMail_type());
		denseVo.setOld_maildir_id(mailVO.getOld_maildir_id());
		denseVo.setOriginalContent(mailVO.getOriginalContent());
		denseVo.setPost_address(mailVO.getPost_address());
		denseVo.setPost_username(mailVO.getPost_username());
		denseVo.setPriority(mailVO.getPriority());
		denseVo.setRe_flag(mailVO.getRe_flag());
		denseVo.setRead_flag(mailVO.getRead_flag());
		denseVo.setReceive_address(mailVO.getReceive_address());
		denseVo.setSend_date(mailVO.getSend_date());
		denseVo.setTitle(mailVO.getTitle());
		denseVo.setUser_code(mailVO.getUser_code());
		return denseVo;
	}

	/**
	 * 根据是否加上签名，增加邮件内容
	 *
	 * @param mailVO
	 * @throws DefaultException
	 */
	private void setMailContentWithSign(MailVO mailVO) throws DefaultException {
		//System.out.println("--------- in setMailContentWithSign ----------");
		if (this.blnAddSign) {
			//System.out.println("-------------- add sign
			// --------------------------");
			String userId = mailVO.getPost_address();
			MailInfoVO mailInfo = getMailSetupInfo(userId);
			String sign = mailInfo.getLable_name();
			String content = this.SEPERATE_LINE + this.webReturn
					+ mailVO.getContent() + "<br><br><br>" + sign;
			mailVO.setContent(content);
		}
	}

	private MailInfoVO getMailSetupInfo(String userId) throws DefaultException {
		MailInfoVO mailInfo = new MailInfoVO();
		//mailInfo.setUser_code(userId);
		this.getMailInfoDao();
		//mailInfo =
		// (MailInfoVO)this.mailInfoDao.queryByPK((VOInterface)mailInfo);
		mailInfo = (MailInfoVO) this.mailInfoDao.getMailInfoByUserId(userId);
		return mailInfo;
	}

	/**
	 * 设置是否回复邮件
	 *
	 * @param mailVO
	 */
	private void setReFlag(MailVO mailVO) {
		if (this.blnRet) {
			mailVO.setRe_flag("1");
		} else {
			mailVO.setRe_flag("0");
		}
	}

	/**
	 * 新增邮件到邮件表
	 *
	 * @param mailVO
	 * @param userId
	 * @return
	 * @throws DefaultException
	 */
	private boolean insertMail(MailVO mailVO, String userId, int count)
			throws DefaultException {
		boolean result = true;
		//System.out.println("------- mail send to id : " + userId);
		mailVO.setSerial_num(IdMng.getModuleID(userId, count));
		//System.out.println("--- serial num is " + mailVO.getSerial_num());
		mailVO.setUser_code(userId);//收件人Id
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		helper.setAutoID(false);
		try {
			helper.begin();
			helper.setAutoClose(false);
			helper.insert(mailVO);
			//System.out.println("--- 新增成功 ------------");
			if (this.attachList.size() > 0) {
				//System.out.println("--- 添加附件信息 ---");
				this.saveMailRelateAttachs(mailVO, helper);
			}
			helper.commit();
		} catch (DefaultException ex) {
			ex.printStackTrace();
			helper.rollback();
			result = false;
		} finally {
			helper.end();
		}
		return result;
	}

	/**
	 * 新增邮件到邮件表
	 *
	 * @param mailVO
	 * @param userId
	 * @param conn
	 * @param helper
	 * @return 邮件是否新增成功
	 * @throws DefaultException
	 */
	public boolean insertMail(MailVO mailVO, String userId, SQLHelper helper)
			throws DefaultException {
		boolean result = true;
		mailVO.setSerial_num(IdMng.getModuleID(userId));
		mailVO.setUser_code(userId); //收件人Id
		//System.out.println("--- serial num is " + mailVO.getSerial_num());
		//System.out.println("--- in insert mail current mail type is " +
		// mailVO.getMail_type());
		helper.setAutoID(false);
		try {
			//helper.begin();
			//helper.setAutoClose(false);
			helper.insert(mailVO);
			//System.out.println("--- 新增成功 ------------");
			if (this.attachList.size() > 0) {
				//System.out.println("--- 添加附件信息 ---");
				this.saveMailRelateAttachs(mailVO, helper);
			}
			//helper.commit();
		} catch (DefaultException ex) {
			ex.printStackTrace();
			//helper.rollback();
			result = false;
		} finally {
			//helper.end();
		}

		return result;
	}

	/**
	 * 保存邮件相关附件
	 *
	 * @param mailId
	 * @throws DefaultException
	 */
	private void saveMailRelateAttachs(MailVO mailVO, SQLHelper sqlhelper)
			throws DefaultException {
		//System.out.println("--- attachList size is " +
		// this.attachList.size());
		int iRandom = new Double(Math.random()).intValue();
		for (int i = 0; i < this.attachList.size(); i++) {
			MailAttchVO attach = (MailAttchVO) this.attachList.get(i);
			attach.setSerial_num(IdMng.getModuleID(mailVO.getUser_code(), i
					+ iRandom));
			//System.out.println("--- attach serial num is " +
			// attach.getSerial_num());
			attach.setMail_id(mailVO.getSerial_num());
			sqlhelper.insert(attach);
		}
	}

	/**
	 * 根据邮件主键取得邮件附件列表
	 *
	 * @param mailId
	 * @return
	 * @throws DefaultException
	 */
	private List getAttachListByMailId(String mailId) throws DefaultException {
		List list = new ArrayList();
		String sql = GET_MAIL_ATTACH_LIST_SQL;
		SQLHelper helper = new DefaultSQLHelper(super.dbData);

		String[] arySql = new String[2];
		arySql[0] = sql;
		arySql[1] = "String";
		String[] values = { mailId };
		MailAttchVO attachVo = new MailAttchVO();
		list = (ArrayList) helper.query(arySql, values, attachVo,
				DataTypes.RS_LIST_VO);

		return list;
	}

	/**
	 * 取得能够被发送的人员名单
	 *
	 * @param userIds
	 * @param mailVO
	 * @param errorVO
	 * @return
	 */
	private String[] getSplitedRecievers(String userIds, MailVO mailVO) {
		System.out.println("--- in getSplitedRecievers ---");
		String errorCode = "saveFaild";
		this.getCapacityDao();
		String[] returnStr = new String[2];
		StringBuffer unableSender = new StringBuffer();
		long mailSize = Long.parseLong(mailVO.getMail_size());
		StringBuffer menToSend = new StringBuffer();

		userIds = removeLastSeperator(userIds,",");

		try {
			System.out.println("--- getSplitedRecievers mailto is " + userIds);
			List list = capacityDao.getMailCapacityByUser(userIds);
			for (int i = 0; i < list.size(); i++) {
				MailCapacityVO capacityVO = (MailCapacityVO) list.get(i);
				System.out.println("--- current user_code is "
						+ capacityVO.getUser_code());
				System.out.println("--- mail size is " + mailSize);
				boolean canSend = !capacityDao.isOutOfCapacity(capacityVO,
						mailSize);
				System.out.println(" ================== can send is " + canSend
						+ " ==============");
				if (canSend == false) {
					unableSender.append(capacityVO.getName()).append(",");
					addErrorInfo(mailVO, errorCode, capacityVO);
				} else if (canSend) {
					System.out.println("--- avoid usercode is "
							+ capacityVO.getUser_code());
					menToSend.append(capacityVO.getUser_code()).append(",");
				}

			}
			//errorVO.setUserName(capacityVO.getUser_name());
		} catch (DefaultException ex) {
			MailErrorInfoVO errorVO = new MailErrorInfoVO();

			errorVO.setErrorCode(errorCode);
			errorVO.setErrorMsg("保存邮件处理资料时出错!");
			errorVO.setTitle(mailVO.getTitle());
			this.errorList.add(errorVO);
			//vo.setUserName(capacityVO.getUser_name());
		}
		returnStr[0] = menToSend.toString();
		returnStr[1] = unableSender.toString();
		return returnStr;
	}
	private String removeLastSeperator(String strUserId, String serperator) {
	    int pos = strUserId.lastIndexOf(serperator);
	    while (pos == strUserId.length() - 1) {
	        if (pos == 0) {
	          return "";
	        }
	        strUserId = strUserId.substring(0, pos);
	        pos = strUserId.lastIndexOf(serperator);
	      }
	      return strUserId;
	 }

	private void addErrorInfo(MailVO mailVO, String errorCode,
			MailCapacityVO capacityVO) {
		MailErrorInfoVO errorVO = new MailErrorInfoVO();
		String errorMsg = "";
		errorMsg = "&nbsp;&nbsp;&nbsp;&nbsp;邮件空间不足,邮件发送失败!";
		errorVO.setUserName(capacityVO.getName());
		errorVO.setErrorCode(errorCode);
		errorVO.setErrorMsg(errorMsg.toString());
		errorVO.setTitle(mailVO.getTitle());
		System.out.println("---------- addErrorInfo -------------");
		this.errorList.add(errorVO);
	}

	/**
	 * 检查是否本人邮件箱容量足够
	 *
	 * @param mailVO
	 * @param errorVO
	 * @return
	 * @throws DefaultException
	 */
	public boolean isSavaOwnFileOutOfCapacity(MailVO mailVO,
			MailErrorInfoVO errorVO) throws DefaultException {
		boolean result = false;
		String errorCode = "";
		String errorMsg = "";
		this.getCapacityDao();
		long mailSize = Long.parseLong(mailVO.getMail_size());
		//System.out.println("--- isSavaOwnFileOutOfCapacity ---");
		//System.out.println("--- mail size is " + mailSize);
		//System.out.println("--- mail user code is " + mailVO.getUser_code());
		try {
			List list = capacityDao
					.getMailCapacityByUser(mailVO.getUser_code());
			/** @todo 如果查不到用户邮箱容量如何处理 */
			MailCapacityVO capacityVO = new MailCapacityVO();
			if (list.size() != 0) {
				capacityVO = (MailCapacityVO) list.get(0);
			}

			if (capacityDao.isOutOfCapacity(capacityVO, mailSize)) {
				//errorMsg =
				// "&nbsp;&nbsp;&nbsp;&nbsp;"+mailVO.getPost_username()+"邮件空间不足,邮件保存失败!<BR>";
				errorMsg = "&nbsp;&nbsp;&nbsp;&nbsp;您邮件空间不足,邮件保存失败!<BR>";
				errorVO.setErrorCode(errorCode);
				errorVO.setErrorMsg(errorMsg);
				errorVO.setTitle(mailVO.getTitle());
				errorVO.setUserName(capacityVO.getName());
				result = true;
			}
		} catch (DefaultException ex) {

			errorVO.setErrorCode(errorCode);
			errorVO.setErrorMsg("保存邮件处理资料时出错!");
			errorVO.setTitle(mailVO.getTitle());
			//vo.setUserName(capacityVO.getUser_name());
			result = true;
		}
		return result;
	}

	/**
	 * 按主键查询邮件
	 *
	 * @param vo
	 *            当前邮件对象
	 * @return 当前邮件对象
	 * @throws DefaultException
	 */
	public MailVO queryByPk(MailVO vo) throws DefaultException {
		return (MailVO) super.queryByPk(vo);
	}

	/**
	 * 初始化回复邮件信息
	 *
	 * @param recievedMail
	 * @return
	 */
	private MailVO initReplyMailInfo(MailVO recievedMail)
			throws DefaultException {
		MailVO mailVo = new MailVO();
		OrganizationUtil mngUtil = new OrganizationUtil();
		String strCurDate = new String(""); // 当前日期及时间
		String strUserName = new String(""); // 当前用户名
		String strSjUserName = new String(""); // 当前用户名
		StringBuffer strContent = new StringBuffer();//当前邮件内容

		strCurDate = DateTimeUtils.getCurrentDateTime();
		strSjUserName = mngUtil.getUserNamesByUserdIDs(recievedMail
				.getUser_code(), ",");
		strUserName = recievedMail.getPost_username();
		strContent.append(strUserName).append(",您好!").append(webReturn);
		strContent.append(webSpace).append(webSpace).append(webSpace).append(
				webSpace).append("您在");
		strContent.append(recievedMail.getSend_date());
		strContent.append("发送给");
		strContent.append(strSjUserName);

		strContent.append("主题为\"");
		strContent.append(recievedMail.getTitle()).append("\"的邮件,他(她)已经于");
		strContent.append(strCurDate).append("阅读了！系统根据您的需要送来这条消息！");
		strContent.append(webReturn).append(webSpace).append(webSpace).append(
				webSpace).append(webSpace).append(";致").append(webReturn)
				.append("礼!").append(webReturn).append(webSpace).append(
						webSpace).append(webSpace).append(webSpace).append(
						"GDCA");
		strContent.append(webSpace).append(webSpace).append(webSpace).append(
				webSpace).append(strCurDate.substring(0, 10));

		mailVo.setContent(strContent.toString()); //设置回复邮件内容
		mailVo.setTitle(" 邮件阅读通知 ");//设置邮件主体
		mailVo.setSend_date(strCurDate);//设置发信日期
		mailVo.setPost_username("系统邮递员");//设置发信人姓名
		mailVo.setPost_address(recievedMail.getUser_code());//设置发信人地址
		mailVo.setRead_flag("0"); //设置邮件标志为未读
		mailVo.setMail_dir_id(MailConstant.DIR_RECIEVE_FOLDER); //设置邮件夹
		mailVo.setMail_size(String.valueOf(mailVo.getTitle().length()
				+ mailVo.getContent().length())); //设置邮件大小
		mailVo.setPriority("2");//设置邮件级别为特别普通
		mailVo.setMail_type(String.valueOf(MailConstant.INNER_MAIL));//设置邮件类型为内部邮件
		mailVo.setRe_flag("0");//该邮件无需回复
		mailVo.setUser_code(recievedMail.getPost_address());
		mailVo.setReceive_address(recievedMail.getPost_address());

		return mailVo;
	}

	/**
	 * 打开邮件
	 *
	 * @param vo
	 *            当前邮件对象
	 * @return 附件列表
	 * @throws DefaultException
	 */
	public List openMail(MailVO mailVo) throws DefaultException {
		//System.out.println("--- in openMail ---");
		List list = new ArrayList();
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		//        Connection conn = null;

		try {
			//            conn = helper.getConnection();
			//            conn.setAutoCommit(false);
			helper.setAutoClose(false);
			helper.begin();
			//如果邮件阅读标志为未阅读则将其标志改为已阅读
			if ("1".equals(mailVo.getRead_flag()) == false) {
				if (changeMailToRead(mailVo.getSerial_num(), helper) != -1) {
					mailVo.setRead_flag("1");
				}
				//如果未阅读并且回复标志为需要回复的话则自动回复
				if (mailVo.getRe_flag().equals("1")) {
					if (autoReplayMail(mailVo, helper) == false) {
						helper.rollback();
					}
				}
			}
			helper.commit();
			list = this.getAttachListByMailId(mailVo.getSerial_num());
		} finally {
			helper.end();
		}

		return list;
	}

	/**
	 * 根据邮件Id打开邮件
	 *
	 * @param serialNum
	 *            String
	 * @throws DefaultException
	 * @return List
	 */
	public List openMailBySerialNum(String serialNum) throws DefaultException {
		MailVO mailVo = getMailinfoByPk(serialNum);
		return this.openMail(mailVo);
	}

	/**
	 * 根据主键查询邮件信息
	 *
	 * @param serialNum
	 * @return
	 * @throws DefaultException
	 */
	public MailVO getMailinfoByPk(String serialNum) throws DefaultException {
		MailVO mailVo = new MailVO();
		mailVo.setSerial_num(serialNum);
		StringBuffer querySql = new StringBuffer(GET_MAIL_INFO_IN_MAIL_DIR);
		String sqlWhere = " and mail.serial_num = " + "'" + serialNum + "'";
		SQLHelper helper = new DefaultSQLHelper(super.dbData);

		querySql.append(sqlWhere);
		//System.out.println("-------- getMailinfoByPk sql : " + querySql);
		mailVo = (MailVO) helper.queryByVo(querySql.toString(), mailVo);
		return mailVo;
	}

	/**
	 * 按照动作参数返回页面显示VO的方法
	 *
	 * @param serialNum
	 * @param ActionType
	 * @return
	 * @throws DefaultException
	 */
	public MailVO getReplyMailInfoByActionType(String serialNum, int actionType)
			throws DefaultException {
		//System.out.println("--- in getReplyMailInfoByActionType serialNum is
		// " + serialNum);
		MailVO mailVo = getMailinfoByPk(serialNum);

		return getReplyMailInfoByActionType(actionType, mailVo);
	}

	public MailVO getReplyMailInfoByActionType(int ActionType, MailVO mailVo)
			throws DefaultException {
		MailVO replyMailVo = new MailVO();
		initParams(ActionType);
		//System.out.println("--- mail type in mailvo is " +
		// mailVo.getMail_type());
		replyMailVo.setSerial_num(mailVo.getSerial_num());
		//回信接收者为当前邮件发送者
		replyMailVo.setReceive_address(mailVo.getPost_address());
		replyMailVo.setTitle(mailVo.getTitle());
		replyMailVo.setMail_type(mailVo.getMail_type());
		if (this.isReplyToAll) {
			String recieverAddress = null;
			String copySend = null;

			//recieverAddress = getRecieversFromSender(mailVo);
			recieverAddress = mailVo.getPost_address();
			copySend = setReciever(mailVo.getReceive_address(), mailVo
					.getUser_code(),mailVo.getCopy_send());
			System.out.println("--- in getReplyMailInfoByActionType copySend id is " + copySend);
			//System.out.println("--- in getReplyMailInfoByActionType
			// recieverAddress id is " + recieverAddress);
			/** @todo 如果施密送的情况如何处理 */
			replyMailVo.setReceive_address(recieverAddress);
			replyMailVo.setCopy_send(copySend);
		}
		if (this.isReplyWithOriginal) {
			String replyContent = null;
			replyContent = getReplyContentFromSender(mailVo);
			//System.out.println("--- original Content : " + replyContent +
			// "\n");
			/** @todo 如果是系统所发邮件如何处理 */
			replyMailVo.setOriginalContent(replyContent);
			List attachList = null;
			if (mailVo.getAttachList() != null
					&& mailVo.getAttachList().size() > 0) {
				attachList = mailVo.getAttachList();
			} else {
				attachList = setAttachListFromSender(mailVo);
			}
			if (attachList.size() > 0) {
				replyMailVo.setAttachList(attachList);
				replyMailVo.setAttachcount(String.valueOf(attachList.size()));
			}
		}
		return replyMailVo;
	}

	private static String setReciever(String originalStr, String testString,String copy_send) {
            /*
		String tempStr = "," + originalStr + ",";
		String tempTestStr = "," + testString + ",";
		int pos = tempStr.indexOf(tempTestStr);
                String tmpStr=originalStr.replaceAll(testString,"");
                System.out.println("The tmpStr is:*****"+tmpStr);
                System.out.println("The tmpStr length is:***"+tmpStr.length());
                System.out.println("The tmpStr to trim() is"+tmpStr.trim());
                System.out.println("The reciever is------"+originalStr);
                System.out.println("The current user_coe  is------ "+testString);
                System.out.println("The pos is"+pos);
		if (pos > 0) {
			tempStr = tempStr.substring(1, pos)
					+ tempStr.substring(pos + tempTestStr.length() - 1, tempStr
							.length() - 1);
			originalStr = tempStr;
                        System.out.println("The copy_send is*******"+originalStr);
		} else if (pos == 0) {
			originalStr = "";
		}
                //收件人加抄送人
                originalStr=originalStr+copy_send;
             }
            */
           //黎海冬加
           String curUser=testString+",";
           originalStr=originalStr+",";
           copy_send=copy_send+",";
           originalStr=originalStr.replaceAll(curUser,"").trim();
           copy_send=copy_send.replaceAll(curUser,"").trim();
           //抄送人
           copy_send=copy_send+originalStr;
           return copy_send;
	}

	/**
	 * 将原邮件的附件记录放入新邮件中
	 *
	 * @param mailVo
	 *            MailVO
	 * @throws DefaultException
	 * @return List
	 */
	public List setAttachListFromSender(MailVO mailVo) throws DefaultException {
		List attachList;
		attachList = this.getAttachListByMailId(mailVo.getSerial_num());
		return attachList;
	}

	/**
	 * 将所收邮件作为内容放入新邮件中
	 *
	 * @param mailVo
	 *            MailVO
	 * @return String
	 */
	private String getReplyContentFromSender(MailVO mailVo) {
		StringBuffer replyContent = new StringBuffer();
		replyContent.append(SEPERATE_LINE).append("<br>");
		replyContent.append("发件人: ").append(mailVo.getPost_username()).append(
				"<br>");
		replyContent.append("邮件主题：").append(mailVo.getTitle()).append("<br>");
		replyContent.append("邮件内容: ").append("<br>");
		replyContent.append(mailVo.getContent()).append("<br>");
		replyContent.append(SEPERATE_LINE);
		return replyContent.toString();
	}

	/**
	 * 从所收邮件中取得收件人
	 *
	 * @param mailVo
	 *            MailVO
	 * @return String
	 */
	private String getRecieversFromSender(MailVO mailVo) {
		String RecieverAddress;
		RecieverAddress = mailVo.getReceive_address()
				+ ("".equals(mailVo.getCopy_send()) ? "" : ",")
				+ mailVo.getCopy_send();
		return RecieverAddress;
	}

	/**
	 * changeMailToRead
	 */
	private int changeMailToRead(String mailId, SQLHelper helper)
			throws DefaultException {
		//        PreparedStatement psmt = null;

		//        String sql = CHANGE_MAIL_READ_STATE_SQL;
		//            psmt = conn.prepareStatement(sql);
		//            psmt.setString(1, mailId);
		//            return psmt.executeUpdate();
		MailVO vo = new MailVO();
		vo.setSerial_num(mailId);
		String columns = "serial_num";
		return helper.execUpdate(CHANGE_MAIL_READ_STATE_SQL, vo, columns);
	}

	/**
	 * autoReplayMail
	 */
	private boolean autoReplayMail(MailVO mailVo, SQLHelper helper)
			throws DefaultException {
		this.setAttachList(new ArrayList());
		MailVO replyMail = initReplyMailInfo(mailVo);
		return this.insertMail(replyMail, replyMail.getUser_code(), helper);
	}

	/**
	 * 真正删除邮件
	 *
	 * @param mailId
	 *            当前选择的邮件
	 * @return 删除成功邮件个数
	 * @throws DefaultException
	 */
	public int removeMails(String[] mailIds, String realPath)
			throws DefaultException {
		int count = 0;
		for (int i = 0; i < mailIds.length; i++) {
			if (deletMailsAndAttachByMailId(mailIds[i], realPath)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 清空垃圾箱所有邮件
	 *
	 * @param userId
	 *            String 用户Id
	 * @param realPath
	 *            String 附件路径
	 * @throws DefaultException
	 * @return int
	 */
	public int removeAllGarbageMails(String userId, String realPath)
			throws DefaultException {
		String[] mailIds = getMailIdsInGarbage(userId);
		return this.removeMails(mailIds, realPath);
	}

	/**
	 * 根据邮件Id删除邮件及其附件
	 *
	 * @param mailId
	 *            String 邮件Id
	 * @param realPath
	 *            String 附件绝对路径
	 * @throws DefaultException
	 * @return boolean 删除是否成功
	 */
	private boolean deletMailsAndAttachByMailId(String mailId, String realPath)
			throws DefaultException {
		boolean isDeleted = true;
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		List deletFilesList = null;
		Connection conn = null;
		conn = helper.getConnection();
		try {
			deletFilesList = getDeleteFilesList(conn, mailId);//取得可删除附件列表
			conn.setAutoCommit(false);
			deleteFromMailByPK(mailId, conn); //删除邮件表中的邮件数据
			deleteFromMailAttachByMailId(mailId, conn);//删除附件表中相关附件数据
			conn.commit();
			deleteAttachFiles(deletFilesList, realPath);//删除物理附件
		} catch (SQLException ex) {
			isDeleted = false;
			try {
				conn.rollback();
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		} finally {
			helper.close(conn);
		}
		return isDeleted;
	}

	/**
	 * getDeleteFilesList
	 */
	private List getDeleteFilesList(Connection conn, String mailId)
			throws DefaultException, SQLException {
		List deletFilesList = new ArrayList();
		StringBuffer strWhere = getCanDeleteFileCondition(mailId);
		if (strWhere.length() > 0) {
			String querySql = CHECK_CAN_DELETE_FILE_SQL + strWhere
					+ CHECK_CAN_DELETE_FILE_SQL_GROUP_BY;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(querySql);
			while (rs.next()) {
				int cnt = 0;
				MailAttchVO attachVO = new MailAttchVO();
				cnt = rs.getInt("cnt");
				if (cnt == 1) {
					attachVO.setFile_path(rs.getString(FILE_PATH_COLUM_NAME));
					attachVO.setAttch_name(rs.getString("attch_name"));
					deletFilesList.add(attachVO);
				}
			}
		}
		return deletFilesList;
	}

	/**
	 * 取得可删除附件相关条件
	 *
	 * @param mailId
	 *            String
	 * @throws DefaultException
	 * @return StringBuffer
	 */
	private StringBuffer getCanDeleteFileCondition(String mailId)
			throws DefaultException {
		StringBuffer strWhere = new StringBuffer("");
		List attachList = null;
		attachList = this.getAttachListByMailId(mailId);
		if (attachList.size() > 0) {
			for (int i = 0; i < attachList.size(); i++) {
				MailAttchVO attachVo = (MailAttchVO) attachList.get(i);
				if (i == 0) {
					strWhere.append(" where ").append(FILE_PATH_COLUM_NAME);
				} else {
					strWhere.append(" or ").append(FILE_PATH_COLUM_NAME);
				}
				strWhere.append(" = ").append("'").append(
						attachVo.getFile_path()).append("'");
			}
		}
		return strWhere;
	}

	/**
	 * 删除附件列表
	 *
	 * @param attachList
	 *            List
	 */
	private void deleteAttachFiles(List attachList, String realPath) {
		for (int i = 0; i < attachList.size(); i++) {
			MailAttchVO attachVo = (MailAttchVO) attachList.get(i);
			File dirName = new File(realPath + attachVo.getFile_path()
					+ attachVo.getAttch_name());
			if (dirName.exists()) {
				dirName.delete();
			}
		}
	}

	/**
	 * 从邮件附件表中删除附件信息
	 *
	 * @param mailId
	 *            String
	 * @param conn
	 *            Connection
	 * @throws SQLException
	 * @return int
	 */
	private int deleteFromMailAttachByMailId(String mailId, Connection conn)
			throws SQLException {
		String updatSql = DELETE_FROM_MAILAttch_SQL;
		PreparedStatement psmt = null;

		psmt = conn.prepareStatement(updatSql);
		psmt.setString(1, mailId);
		return psmt.executeUpdate();
	}

	/**
	 * 根据邮件Id删除邮件
	 *
	 * @param serialId
	 *            String
	 * @param conn
	 *            Connection
	 * @throws SQLException
	 */
	private void deleteFromMailByPK(String serialId, Connection conn)
			throws SQLException {
		String updatSql = DELETE_FROM_MAIL_SQL;
		PreparedStatement psmt = null;

		psmt = conn.prepareStatement(updatSql);
		psmt.setString(1, serialId);
		psmt.executeUpdate();

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
		//System.out.println("----------- at queryByPage in MailMgrDAO
		// ----------pageSql : \n");
		//System.out.println("--- current user is " + super.userID);
		//MailInfoVO mailInfo = this.getMailSetupInfo(super.userID);
		////System.out.println("--- mailInfo page count is " +
		// mailInfo.getPagenumber());
		/*
		 * if(this.getPageNumber() != null &&
		 * this.getPageNumber().trim().length() > 0) { howMany =
		 * Long.parseLong(this.getPageNumber()); System.out.println("--- page
		 * number is " + this.getPageNumber()); }
		 */
		//System.out.println("--- how many is " + howMany);
		//String pageSql = "";
		//super.setPageSql(pageSql);
		List list = null;
		list = super.queryByPage(vo, start, howMany);
		return list;
	}

	/**
	 * 取得当前用户所有未读邮件纪录
	 *
	 * @param userId
	 *            String 用户ID
	 * @throws DefaultException
	 * @return List
	 */
	public List queryUnReadMailByUser(String userId) throws DefaultException {
		List result = new ArrayList();
		String querySql = GET_MAIL_INFO_IN_MAIL_DIR;
		String strWhere = "  where mail.read_flag <> '1' and mail.mail_dir_id = '"
				+ MailConstant.DIR_RECIEVE_FOLDER
				+ "' and mail.user_code = "
				+ userId;
		String sql = querySql + strWhere;
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		result = helper.queryByListVo(sql, new MailVO());
		return result;
	}

	/**
	 * 取得当前用户所有未读邮件纪录
	 *
	 * @param userIds
	 *            String 用户ID
	 * @throws DefaultException
	 * @return List
	 */
	public List queryUnReadMailByUsers(String userIds) throws DefaultException {
		if (userIds.length() > 0) {
			int pos = -1;
			pos = userIds.lastIndexOf(",");
			while (pos == userIds.length()) {
				userIds = userIds.substring(0, pos);
				pos = userIds.lastIndexOf(",");
			}
		} else {
			userIds = "-1";
		}
		List result = new ArrayList();
		String querySql = GET_MAIL_INFO_IN_MAIL_DIR;
		String strWhere = "  where mail.read_flag <> '1' and mail.mail_dir_id = '"
				+ MailConstant.DIR_RECIEVE_FOLDER
				+ "' and mail.user_code in ( " + userIds + ")";
		String sql = querySql + strWhere;
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		result = helper.queryByListVo(sql, new MailVO());
		return result;
	}

	/**
	 *
	 * @param isInnerMail
	 *            是否为内部邮件
	 * @return 当前邮件对象
	 * @throws DefaultException
	 */
	public MailVO queryMailCount(boolean isInnerMail) throws DefaultException {
		MailVO vo = new MailVO();
		return vo;
	}

	/**
	 * 删除邮件到垃圾桶当中
	 *
	 * @param mailId
	 *            当前选择的邮件
	 * @return 是否搬移成功
	 * @throws DefaultException
	 */
	public int deleteMails(String[] mailId) throws DefaultException {

		return this.moveMails(mailId, MailConstant.DIR_GARBAGE_FOLDER);
	}

	/**
	 * 还原邮件
	 *
	 * @param mailId
	 *            当前选择的邮件
	 * @return 还原多少封邮件
	 * @throws DefaultException
	 */
	public int revertMails(String[] mailId) throws DefaultException {
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		int count = 0;
		Connection conn = helper.getConnection();
		for (int i = 0; i < mailId.length; i++) {
			if (revertMailFromGarbageDir(mailId[i], conn)) {
				count++;
			}
		}
		helper.close(conn);
		return count;

	}

	/**
	 * revertMailFromGarbageDir
	 *
	 * @param string
	 *            String
	 * @param conn
	 *            Connection
	 * @return boolean
	 */
	private boolean revertMailFromGarbageDir(String mailId, Connection conn) {
		boolean isSuccess = true;
		String updateSql = REVERT_MAIL_DIR_SQL_1 + "'" + mailId + "'"
				+ REVERT_MAIL_DIR_SQL_2 + "'" + mailId + "'";
		//System.out.println("--- update Sql is " + updateSql);
		try {
			Statement stmt = conn.createStatement();
			if (stmt.executeUpdate(updateSql) == -1) {
				isSuccess = false;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * 还原所有邮件
	 *
	 * @param mailId
	 *            当前选择的邮件
	 * @return 还原多少封邮件
	 * @throws DefaultException
	 */
	public int revertAllMails(String userId) throws DefaultException {
		String[] userIds = null;
		userIds = getMailIdsInGarbage(userId);
		return this.revertMails(userIds);
	}

	/**
	 * getMailIdsInGarbage
	 *
	 * @return String[]
	 */
	private String[] getMailIdsInGarbage(String userId) throws DefaultException {
		String[] result = null;
		StringBuffer stbResult = new StringBuffer();
		String querySql = GET_MAIL_IN_GARBAGE_DIR + userId;
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int i = 0;
		//System.out.println("--- querySql is " + querySql);
		try {
			conn = helper.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(querySql);
			while (rs.next()) {
				stbResult.append(rs.getString(1)).append(",");
			}
			result = stbResult.toString().split(",");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			helper.close(conn);
		}
		return result;
	}

	/**
	 * 搬移邮件
	 *
	 * @param mailId
	 *            当前选择的邮件
	 * @param curMailDir
	 *            当前邮件夹
	 * @return 搬移成功多少笔
	 * @throws DefaultException
	 */
	public int moveMails(String[] mailId, String curMailDir)
			throws DefaultException {
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		int count = 0;
		Connection conn = helper.getConnection();
		for (int i = 0; i < mailId.length; i++) {
			if (moveMailToDirByMailId(mailId[i], curMailDir, conn)) {
				count++;
			}
		}
		helper.close(conn);
		return count;
	}

	/**
	 * 将邮件放入指定邮箱
	 *
	 * @param mailId
	 *            String 邮件Id
	 * @param mailDir
	 *            String 邮箱编号
	 * @param conn
	 *            Connection
	 * @return boolean
	 */
	private boolean moveMailToDirByMailId(String mailId, String mailDir,
			Connection conn) {
		boolean isSuccess = true;
		String updateSql = MOVE_MAIL_DIR_SQL;
		try {
			PreparedStatement psmt = conn.prepareStatement(updateSql);
			psmt.setString(1, mailDir);
			psmt.setString(2, mailId);
			psmt.setString(3, mailId);
			if (psmt.executeUpdate() == -1) {
				isSuccess = false;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			isSuccess = false;
		}

		return isSuccess;
	}

	/** ---------------- 属性设置和取得方法 ------------------ */

	boolean blnSend = false; // 保存标志(是否要保存到寄件夹

	boolean bIncInside = false;// 有包括内部邮件

	boolean bIncWeb = false; // 有包括web邮件

	boolean bSave = false;//是否保存草稿

	boolean bIncFW = false;// 是否为转发

	boolean bSaveOnly = false;//(actionType == ONLY_SAVEMAIL) ? true : false;

	boolean bSaveBak = false;//是否保存到寄件夹

	boolean blnRet = false; //是否收到后回复

	boolean blnReMail = false;//是否为回复邮件

	boolean blnAddSign = false;//是否添加签名

	private boolean isReplyWithOriginal;//是否回复附加原文

	private boolean isReplyToAll;//是否给所有人回复

	private String pageNumber = "";

	private Pop3ConfigVO pop3ConfigVo;

	private String mailNote;

	private String serverPath;

	private boolean isBIncFW() {
		return bIncFW;
	}

	private void setBIncFW(int actionType) {
		this.bIncFW = ((actionType & MailConstant.FW_MAIL) > 0) ? true : false; // 是否为转发
	}

	private boolean isBIncInside() {
		return bIncInside;
	}

	private void setBIncInside() {
		this.bIncInside = !this.bIncWeb;// 有包括内部邮件
	}

	private boolean isBIncWeb() {
		return bIncWeb;
	}

	private void setBIncWeb(int actionType) {
		this.bIncWeb = ((actionType & MailConstant.POP3_TYPE) > 0) ? true
				: false; // 有包括web邮件
		//System.out.println("--- action type after check : " + actionType);
	}

	private boolean isBlnReMail() {
		return blnReMail;
	}

	private void setBlnReMail(int actionType) {
		this.blnReMail = ((actionType & MailConstant.RE_MAIL) > 0) ? true
				: false; // 有回复邮件
	}

	private boolean isBlnRet() {
		return blnRet;
	}

	private void setBlnRet(int actionType) {
		this.blnRet = ((actionType & MailConstant.RETO_ME_MAIL) > 0) ? true
				: false; // 有回复邮件
	}

	private boolean isBlnSend() {
		return blnSend;
	}

	private void setBlnSend(boolean blnSend) {
		this.blnSend = blnSend;
	}

	private boolean isBSave() {
		return bSave;
	}

	private void setBSave(int actionType) {
		this.bSave = ((actionType & MailConstant.SAVE_ONLY_MAIL) > 0) ? true
				: false;
	}

	private boolean isBSaveBak() {
		return bSaveBak;
	}

	private void setBSaveBak(int actionType) {
		this.bSaveBak = ((actionType & MailConstant.SAVE_POST_MAIL) > 0) ? true
				: false;
	}

	private boolean isBSaveOnly() {
		return bSaveOnly;
	}

	private void setBSaveOnly(int actionType) {
		this.bSaveOnly = (actionType == 0x10 && this.bIncFW == true) ? true
				: false;
	}

	private String getMailTo() {
		return mailTo;
	}

	private void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	private List getAttachList() {
		return attachList;
	}

	public void setAttachList(List attachList) {
		this.attachList = attachList;
	}

	private void getCapacityDao() {
		this.capacityDao = MailMgrFactory.getInstance().creatMailCapacity(
				super.dbData);
	}

	private boolean isBlnAddSign() {
		return blnAddSign;
	}

	private void setBlnAddSign(int actionType) {
		this.blnAddSign = ((actionType & MailConstant.ADD_SIGN) > 0) ? true
				: false;
	}

	private void getMailInfoDao() {
		this.mailInfoDao = MailMgrFactory.getInstance().creatMailInfo(
				super.dbData);
	}

	private boolean isIsReplyWithOriginal() {
		return isReplyWithOriginal;
	}

	private void setIsReplyWithOriginal(boolean isReplyWithOriginal) {
		this.isReplyWithOriginal = isReplyWithOriginal;
	}

	private void setIsReplyWithOriginal(int actionType) {
		this.isReplyWithOriginal = ((actionType & MailConstant.RE_MAIL_WITH_ORIGINAL_MAIL) > 0) ? true
				: false;
	}

	private boolean isIsReplyToAll() {
		return isReplyToAll;
	}

	private void setIsReplyToAll(boolean isReplyToAll) {
		this.isReplyToAll = isReplyToAll;
	}

	private void setIsReplyToAll(int actionType) {
		this.isReplyToAll = ((actionType & MailConstant.RE_MAIL_TO_ALL) > 0) ? true
				: false;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Pop3ConfigVO getPop3ConfigVo() {
		return pop3ConfigVo;
	}

	public void setPop3ConfigVo(Pop3ConfigVO pop3ConfigVo) {
		this.pop3ConfigVo = pop3ConfigVo;
	}

	public String getMailNote() {
		return mailNote;
	}

	public void setMailNote(String mailNote) {
		this.mailNote = mailNote;
	}

	public List getErrorList() {
		return errorList;
	}

	public void setErrorList(List errorList) {
		this.errorList = errorList;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	/**
	 * 发送前检查邮箱大小，如果只保存
	 *
	 * @param mailSendTo
	 *            String
	 * @param mailVO
	 *            MailVO
	 * @param errorInfo
	 *            MailErrorInfoVO
	 * @throws DefaultException
	 * @return String
	 */
	/*
	 * public String preSendMail(MailVO mailVO,MailErrorInfoVO errorInfo) throws
	 * DefaultException{ String userToSand = ""; this.getCapacityDao();
	 * if(isSavaOwnFileOutOfCapacity (mailVO,errorInfo) == false){ userToSand =
	 * getSendableRecievers(this.getMailTo(), mailVO, errorInfo); } return
	 * userToSand; }
	 */

}
