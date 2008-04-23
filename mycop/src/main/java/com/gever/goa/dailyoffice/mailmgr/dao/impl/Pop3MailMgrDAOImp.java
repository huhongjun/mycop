package com.gever.goa.dailyoffice.mailmgr.dao.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.dao.MailConstant;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrFactory;
import com.gever.goa.dailyoffice.mailmgr.dao.Pop3MailMgrDAO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailAttchVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailErrorInfoVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailVO;
import com.gever.goa.dailyoffice.mailmgr.vo.Pop3ConfigVO;
import com.gever.goa.web.util.UploadFile;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.IdMng;

public class Pop3MailMgrDAOImp extends BaseDAO implements Pop3MailMgrDAO {

	public Pop3MailMgrDAOImp(String dbData) {
		super(dbData);
	}

	private Store myStore; //邮件储存类

	public ArrayList aryErrList = new ArrayList(); //错误清单

	public ArrayList aryResult = new ArrayList(); //处理结果清单

	private String protocol = new String(""); //邮件类型

	private String userID = new String(""); //邮件登入用户ID

	private String password = new String(""); //密码

	private String server = new String(""); //服务器的地址

	private String port = new String("110"); //端口

	private boolean bIsDel = true;

	private String serverPath = new String(""); //当前服务器的所在的地址

	private static StringBuffer sbContent = new StringBuffer(); //处理内容

	private static int level = 0; //用于记录走到邮件内容的第几层//当前员工编号(主要是在存储附件时用)

	private static boolean showmsg = true;

	private String fileName = new String("");

	private StringBuffer strBuf = new StringBuffer();

	//private PublicFunction pf = PublicFunction.getInstance();
	private ArrayList attchList = new ArrayList();

	StringBuffer strBufErr = new StringBuffer();

	private UploadFile uploadFile = new UploadFile();

	private MailMgrDAO mailMgrDao = null;

	public static String getISOFileName(Part body) {
		//设置一个标志，判断文件名从Content-Disposition中获取还是从Content-Type中获取
		boolean flag = true;
		String[] cdis;
		/*
		 * if(body==null){ return null; }
		 */
		if (body != null) {
			try {
				cdis = body.getHeader("Content-Disposition");
				if (cdis == null) {
					flag = false;
					cdis = body.getHeader("Content-Type");
				}
			} catch (Exception e) {
				return null;
			}
			if (cdis == null) {
				return null;
			}
			if (cdis[0] == null) {
				return null;
			}
			//从Content-Disposition中获取文件名
			if (flag) {
				int pos = cdis[0].indexOf("filename=");
				if (pos < 0) {
					return null;
				}
				//如果文件名带引号
				if (cdis[0].charAt(cdis[0].length() - 1) == '"') {
					return cdis[0].substring(pos + 10, cdis[0].length() - 1);
				}
				return cdis[0].substring(pos + 9, cdis[0].length());
			} else {
				int pos = cdis[0].indexOf("name=");
				if (pos < 0) {
					return null;
				}
				//如果文件名带引号
				if (cdis[0].charAt(cdis[0].length() - 1) == '"') {
					return cdis[0].substring(pos + 6, cdis[0].length() - 1);
				}
				return cdis[0].substring(pos + 5, cdis[0].length());
			}
		} else {
			return null;
		}
	}

	/**
	 * 连接远程邮件服务器
	 * 
	 * @return 成功与否,true:为成功,false为失败
	 */
	public boolean mailConnection() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "pop3");
		props.put("mail.pop3.host", server);
		props.put("mail.pop3.port", String.valueOf(port));
		props.put("mail.pop3.auth", "true");
		// System.out.println("--- pop3 server name : " + server);
		// System.out.println("--- pop3 port: " + port);
		// System.out.println("--- pop3 userid : " + userID);
		// System.out.println("--- pop3 password : " + password);
		try {
			//System.out.println("--------- connectin ---- start -------");
			PopupAuthenticator auth = new PopupAuthenticator(userID, password);
			Session mailSession = Session.getInstance(props, auth);
			Store mailStore = mailSession.getStore("pop3");
			mailStore.connect(server, Integer.parseInt(this.port), userID,
					password);
			this.setMyStore(mailStore);
			//System.out.println("---------connectin ----- end ------------");
			return true;
		} catch (javax.mail.AuthenticationFailedException e) {
			//System.out.println("---------Connection false -----------------");
			e.printStackTrace();
			return false;
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
			//System.out.println("------MessagingException---false -----------");
			return false;
		}
	}

	/**
	 * 存储邮件到服务器
	 * 
	 * @return 成功与否
	 */
	public boolean saveMails() {
		return false;
	}

	/**
	 * 发送邮件
	 * 
	 * @param vo
	 *            邮件表映射类
	 * @param aryAttch
	 *            附件细项清单
	 * @return 成功与否
	 */
	public String sendMails(MailVO vo, List aryAttch) {
		return "";
	}

	/**
	 * 发送邮件
	 * 
	 * @param mailMap
	 *            邮件资料信息
	 * @param info
	 *            邮件表映射类
	 * @param aryList
	 *            附件细项清单
	 * @return 成功与否
	 */
	public String sendMails(HashMap mailMap, MailVO info, ArrayList aryList) {

		Properties props = new Properties();
		String smtpServer = new String("");
		String fromEmail = new String(); //发送邮件显示地址
		String toEmail = new String(); //收信人地址
		String ccEmail = new String(""); //抄送地址
		String bccEmail = new String(""); //密送地址
		String mailNote = new String("");
		String strRealPath = new String("");
		int iCheck = 0;
		Pop3ConfigVO configView = (Pop3ConfigVO) mailMap.get("view"); //设置发送POP3设置信息
		mailNote = (String) mailMap.get("mainNote");
		boolean bCheck = false;
		protocol = "smtp";
		smtpServer = setSmtpServer(configView);
		setSmtpPort(configView);
		bCheck = configView.getSmtp_auth().equals("1");
		setSendMailProperty(props, smtpServer, bCheck);

		fromEmail = configView.getShow_address();
		toEmail = info.getReceive_address();
		ccEmail = info.getCopy_send();
		bccEmail = info.getDense_send();
		HashMap errMap = new HashMap();
		errMap.put("server", configView.getSmtp_server());
		errMap.put("from", fromEmail);
		if (bCheck) {
			userID = configView.getSmtp_name();
			password = configView.getSmtp_pwd();
		}

		try {
			Session mailSession = null;
			mailSession = Session.getInstance(props, null);
			mailSession = setMailSession(props, bCheck);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(fromEmail));

			//设置发送地址
			if (!toEmail.equals("")) {
				++iCheck;
				msg.setRecipients(Message.RecipientType.TO, InternetAddress
						.parse(toEmail));
			}
			//设置抄送地址
			if (!ccEmail.equals("")) {
				++iCheck;
				msg.setRecipients(Message.RecipientType.CC, InternetAddress
						.parse(ccEmail));
			}
			//设置密送地址
			if (!bccEmail.equals("")) {
				++iCheck;
				msg.setRecipients(Message.RecipientType.BCC, InternetAddress
						.parse(bccEmail));
			}
			//没有收件人或抄送,密送人,不允许发送邮件.
			if (iCheck == 0)
				return "";

			msg.setSentDate(new Date()); //发送日期
			msg.setSubject(info.getTitle()); //邮件主题

			Multipart mult = new MimeMultipart();
			// 处理附件内容
			MimeBodyPart mBody = new MimeBodyPart();
			//  System.out.println("-- mail note is " + mailNote);
			mBody.setText(mailNote); //  邮件内容
			mult.addBodyPart(mBody);
			setAttachBodyPart(aryList, strRealPath, mult); //设置附件内容

			msg.setContent(mult);
			//Transport.send(msg);
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(smtpServer, Integer.parseInt(port), userID,
					password);
			transport.send(msg); //发送出去
			//pr ("------send OK-----" );
		} catch (javax.mail.SendFailedException e) {
			e.printStackTrace();
			boolean bErr = true;
			Address list[] = e.getInvalidAddresses();
			if (list != null) {
				errMap.put("list", list);
				bErr = false;
			}
			Address unlist[] = e.getValidUnsentAddresses();
			if (unlist != null) {
				errMap.put("unlist", unlist);
				bErr = false;
			}
			Address sentlist[] = e.getValidSentAddresses();
			if (sentlist != null) {
				errMap.put("sentlist", unlist);
				bErr = false;
			}
			this.procSmtpError(errMap, bErr);

		} catch (javax.mail.AuthenticationFailedException e) {
			e.printStackTrace();
			this.procSmtpError(errMap, true);

		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
			this.procSmtpError(errMap, true);
		}
		mailMap.put("errorMap", errMap);
		return this.strBufErr.toString();

	}

	/**
	 * 删除远程服务器的邮件
	 * 
	 * @param aryList
	 *            pop3邮件帐号清单
	 * @return 成功与否 :true为真,false为假
	 */
	public List deleteMails(List aryList) { //删除远程服务器的邮件
		this.procEmails((ArrayList) aryList, MailConstant.DELETE_ACTION);
		return this.aryResult;
	}

	/**
	 * 统计远程服务器的邮件个数
	 * 
	 * @param aryList
	 *            pop3邮件帐号清单
	 * @return 成功与否 :true为真,false为假
	 */
	public List countEmails(List aryList) {
		procEmails((ArrayList) aryList, MailConstant.COUNT_ACTION);
		return this.aryResult;
	}

	/**
	 * 处理服务器邮件接收邮件(包括删除,统计邮件)
	 * 
	 * @param aryList
	 *            所有的pop3的帐号
	 * @param action
	 *            动作类型
	 * @return 成功与否,true为真,false为假
	 */
	public boolean procEmails(ArrayList aryList, int action) {
		//HashMap mailMap = new HashMap();
		boolean bIsRead = true;
		int iCount = 0;
		aryResult = new ArrayList();
		//aryList = initProcEmailTestInfo(aryList);
		for (int idx = 0; idx < aryList.size(); idx++) {
			Pop3ConfigVO popView = new Pop3ConfigVO();
			HashMap resultMap = new HashMap();
			popView = (Pop3ConfigVO) aryList.get(idx);
			this.userID = popView.getPop3_account();
			this.protocol = "pop3";
			this.password = popView.getPop3_pwd();
			this.server = popView.getPop3_address();
			server = this.setPop3Server(popView);
			this.setPop3Port(popView);

			try {
				if (this.mailConnection() == false) { //登入失败,就换下一个邮件帐号
					resultMap.put("ID", String.valueOf(idx));
					resultMap.put("POP3SERVER", this.server);
					resultMap.put("COUNT", "连接失败");
					aryResult.add(resultMap);
					continue;
				}

				Folder folder = this.myStore.getDefaultFolder();
				if (folder == null) { //
					continue;
				}
				Folder popFolder = folder.getFolder("INBOX"); //默认邮件夹
				bIsRead = openPopFolder(popFolder);
				switch (action) {
				case MailConstant.DELETE_ACTION:
					Message[] msgList = popFolder.getMessages();
					if (bIsRead == true) {
						for (int iNext = 0; iNext < msgList.length; iNext++) {
							msgList[iNext].setFlag(Flags.Flag.DELETED, true);
						}
					}
					iCount = popFolder.getMessageCount();
					resultMap.put("ID", String.valueOf(idx));
					resultMap.put("POP3SERVER", this.server);
					resultMap.put("COUNT", (bIsRead == false) ? "不允许删除"
							: String.valueOf(iCount));
					aryResult.add(resultMap);
					popFolder.close(true);
					break;
				case MailConstant.COUNT_ACTION:

					iCount = popFolder.getMessageCount();

					resultMap.put("ID", String.valueOf(idx));
					resultMap.put("POP3SERVER", this.server);
					resultMap.put("COUNT", String.valueOf(iCount));
					aryResult.add(resultMap);
					popFolder.close(false);
					break;
				default:
					break;

				}

				this.myStore.close();

			} catch (MessagingException e) {
				e.printStackTrace();
				resultMap.put("ID", String.valueOf(idx));
				resultMap.put("POP3SERVER", this.server);
				resultMap.put("COUNT", "处理出错");
				aryResult.add(resultMap);
				continue;
			}
		}
		return true;
	}

	private ArrayList initProcEmailTestInfo(ArrayList aryList) {
		aryList = new ArrayList();
		Pop3ConfigVO testConfigVo = new Pop3ConfigVO();
		testConfigVo.setPop3_account("test");
		testConfigVo.setPop3_address("YFS007:110");
		testConfigVo.setPop3_name("test@YFS007");
		testConfigVo.setPop3_pwd("test");
		testConfigVo.setShow_address("test@YFS007");
		testConfigVo.setSmtp_server("YFS007:25");
		testConfigVo.setSmtp_auth("1");
		testConfigVo.setSmtp_name("test");
		testConfigVo.setSmtp_pwd("test");
		aryList.add(testConfigVo);
		return aryList;
	}

	/**
	 * 下载服务器邮件接收邮件
	 * 
	 * @param aryList
	 *            所有的pop3的帐号
	 * @return 返回错误提示信息
	 */
	public String downLoadEmails(List aryList) {
		aryResult = new ArrayList();
		//aryList = initDownLoadTestInfo(aryList);

		boolean bErr = false;
		for (int idx = 0; idx < aryList.size(); idx++) {
			
			HashMap errMap = new HashMap();
			Pop3ConfigVO popView = (Pop3ConfigVO) aryList.get(idx);
			this.userID = popView.getPop3_account();
			this.protocol = "pop3";
			this.password = popView.getPop3_pwd();
			//this.server = popView.getPop3_address();
			server = setPop3Server(popView);
			setPop3Port(popView);

			//setBIsDel(popView);
			if ("1".equals(popView.getDel_flag())){
				bIsDel = false;
			}
				
			errMap.put("ID", String.valueOf(idx));
			errMap.put("POP3SERVER", this.server);
			errMap.put("NAME", popView.getPop3_name());
			bErr = true;
			if (this.mailConnection() == false) { //登入失败,就换下一个邮件帐号
				errMap.put("Connection", "邮件连接失败");
				bErr = false;
				procPop3Error(errMap, bErr);
				continue;
			}
			errMap.put("Connection", "成功连接到POP3");
			// System.out.println("--- 成功联接 ---");
			try {
				Folder folder = this.myStore.getDefaultFolder();
				if (folder == null) { //
					errMap.put("ERROR", "没有邮件文件夹");
					//  System.out.println("--- 没有邮件文件夹 ---");
					bErr = false;
					procPop3Error(errMap, bErr);
					continue;
				}
				
				System.out.println("--- message count in folder is "
						+ folder.getMessageCount());
				Folder popFolder = folder.getFolder("INBOX"); //默认邮件夹
				this.openPopFolder(popFolder); //打开邮件夹
				
				

				errMap.put("Message", String.valueOf(popFolder
						.getMessageCount()));
				
				insertMail(popFolder.getMessages(), popView
						.getIncept_mail_dir(), popView.getUser_code(), errMap);
				popFolder.close(true);
				
				this.myStore.close();
				procPop3Error(errMap, bErr);
			} catch (MessagingException e) {
				e.printStackTrace();
				continue;
			} catch (DefaultException e) {
				e.printStackTrace();
			}
		}
		return strBufErr.toString();
	}

	private List initDownLoadTestInfo(List aryList) {
		aryList = new ArrayList();
		Pop3ConfigVO testConfigVo = new Pop3ConfigVO();
		testConfigVo.setPop3_account("test");
		testConfigVo.setPop3_address("YFS007:110");
		testConfigVo.setPop3_name("test@YFS007");
		testConfigVo.setPop3_pwd("test");
		testConfigVo.setShow_address("test@YFS007");
		testConfigVo.setSmtp_server("YFS007:25");
		testConfigVo.setSmtp_auth("1");
		testConfigVo.setSmtp_name("test");
		testConfigVo.setSmtp_pwd("test");
		testConfigVo.setIncept_mail_dir(MailConstant.DIR_RECIEVE_FOLDER);
		testConfigVo.setUser_code("1");
		aryList.add(testConfigVo);
		return aryList;
	}

	/**
	 * 下载至邮件
	 * 
	 * @param listMsg
	 *            所有邮件清单
	 * @param strMailType
	 *            存到哪个邮件位置
	 * @param iUserID
	 *            当前的用户名
	 * @param hMap
	 *            错误映射类
	 * @return 成功否 true 为真,false 为假
	 * @throws MessagingException
	 * @throws GeneralException
	 */
	private boolean insertMail(Message[] listMsg, String mailDirId,
			String strUserID, HashMap hMap) throws MessagingException,
			DefaultException {
		//MailVO recievedPop3MailInfo = new MailVO();

		//MailMngCmd mngCmd = new MailMngCmd();
		//int iMailID = 0;
		// System.out.println("--- in insertMail pop3MailMgrDaoImpl ---");
		// System.out.println("邮件数------------" + listMsg.length);
		int badMailCount = 0;
		int goodMailCount = 0;
		ArrayList aryErr = new ArrayList();
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		helper.setAutoClose(false);
		setMailMgrDao();
		try {
			helper.begin();
			for (int iNext = 0; iNext < listMsg.length; iNext++) {
				Message tempMessage = listMsg[iNext];
				MailVO recievedPop3MailInfo = initRecievedPop3MailInfo(
						mailDirId, strUserID, tempMessage);

				//  System.out.println("------I am -----" + info.getYJDX() +
				// "主题--"+info.getZHUTI());

				/*
				 * Connection conn = null; ResultSet rs = null; Statement st =
				 * null; conn = helper.getConnection();
				 */
				//HashMap errMap = mngCmd.preProcMail("", recievedPop3MailInfo,
				// 0x20);
				/** @todo 检测邮件空间 */
				MailErrorInfoVO errorInfo = new MailErrorInfoVO();
				mailMgrDao.isSavaOwnFileOutOfCapacity(recievedPop3MailInfo,
						errorInfo);
				String strRet = toString(errorInfo.getErrorMsg());

				if (strRet.length() > 0) { //如果空间不足时
					badMailCount++;
					strRet = "<BR>&nbsp;&nbsp;&nbsp;&nbsp;<font color=red>接收\""
							+ (toString(recievedPop3MailInfo.getTitle())
									.equals("") ? "没有主题" : recievedPop3MailInfo
									.getTitle()) + "\"的邮件时,你的邮件空间不足</font>";

					aryErr.add(strRet);
					continue;
				}

				try {
					//conn.setAutoCommit(false);
					sbContent.setLength(0);
					level = 0;
					//attchList = new ArrayList();
					userId = recievedPop3MailInfo.getUser_code();
					dumpPart(tempMessage);
					recievedPop3MailInfo.setContent(sbContent.toString()); //设置邮件内容
					System.out.println(" note--------> " + sbContent.toString());
					//记录web内容资料
					//mngCmd.setRealFilePath(this.getServerPath());
					//mngCmd.insertMail(recievedPop3MailInfo, conn, true,
					// MailConstant.POP3_TYPE);
					mailMgrDao.setAttachList(this.attchList);
					if (mailMgrDao.insertMail(recievedPop3MailInfo, userId,
							helper) == false) {
						++badMailCount;
					}
					this.setAttchList(new ArrayList());
					/*
					 * st = conn.createStatement(); rs = st.executeQ rs =
					 * st.executeQuery("values int( identity_val_local())"); if
					 * (rs.next()) { iMailID = rs.getInt(1); } rs.close();
					 * st.close(); //记录web附件资料 /*
					 * mngCmd.insertAttchFile(attchList, conn, iMailID,
					 * recievedPop3MailInfo.getYGBH(), false, MAIL_ISWEB);
					 * conn.commit();
					 */
					System.out.println("--------DELETED---------" +Flags.Flag.DELETED + " isdel = " + this.bIsDel); 
					if (bIsDel)
						tempMessage.setFlag(Flags.Flag.DELETED, true);
					++goodMailCount;
				} catch (IOException e) {
					++badMailCount;
					e.printStackTrace();
				} catch (MessagingException e) {
					++badMailCount;
					e.printStackTrace();
				}
				helper.commit();
				recievedPop3MailInfo = null;
			}
		} catch (DefaultException ex) {
			ex.printStackTrace();
			helper.rollback();
		} finally {
			helper.end();
		}

		//dealFinally(conn, rs, st);
		hMap.put("nosize", aryErr);
		hMap.put("goodMails", String.valueOf(goodMailCount));
		hMap.put("badMails", String.valueOf(badMailCount));
		return false;
	}

	private void setPop3Port(Pop3ConfigVO popView) {
		this.port = popView.getPop3_address().substring(
				popView.getPop3_address().indexOf(":") + 1);
	}

	private String setPop3Server(Pop3ConfigVO popView) {
		String pop3Server = "";
		pop3Server = popView.getPop3_address().substring(0,
				popView.getPop3_address().indexOf(":"));
		return pop3Server;
	}

	private boolean openPopFolder(Folder popFolder) throws MessagingException {
		boolean bIsRead = true;
		try {
			popFolder.open(Folder.READ_WRITE); //打开邮件夹
		} catch (MessagingException ex) {
			popFolder.open(Folder.READ_ONLY);
			bIsRead = false;
		}
		return bIsRead;
	}

	private void setMailMgrDao() {
		this.mailMgrDao = MailMgrFactory.getInstance().createMailMgr(
				super.dbData);
	}

	private MailVO initRecievedPop3MailInfo(String mailDirId, String strUserID,
			Message tempMessage) throws MessagingException {
		MailVO recievedPop3MailInfo = new MailVO();
		recievedPop3MailInfo.setMail_dir_id(mailDirId); //邮件夹Id
		recievedPop3MailInfo
				.setMail_size(String.valueOf(tempMessage.getSize())); //邮件大小
		recievedPop3MailInfo.setMail_type(String
				.valueOf(MailConstant.POP3_MAIL)); //为外部邮件
		recievedPop3MailInfo.setRe_flag("0"); //为外部邮件
		recievedPop3MailInfo.setRead_flag("0");
		recievedPop3MailInfo.setUser_code(strUserID); //当前的用户ID
		recievedPop3MailInfo.setReceive_address(String
				.valueOf(recievedPop3MailInfo.getUser_code())); //接收人地址
		try {
			recievedPop3MailInfo.setTitle(MimeUtility.decodeText(tempMessage
					.getSubject())); //主题
		} catch (java.io.UnsupportedEncodingException e) {
		}

		setPop3MailRelatedAddress(recievedPop3MailInfo, tempMessage);
		try {
			setPop3MailSendDate(recievedPop3MailInfo, tempMessage); //发送日期

		} catch (Exception xe) {

		}
		return recievedPop3MailInfo;
	}

	private void setPop3MailRelatedAddress(MailVO info, Message tempMessage)
			throws MessagingException {
		Address[] fromList = tempMessage.getFrom();
		if (fromList != null) {
			info.setPost_address(((InternetAddress) fromList[0]).getAddress()); //寄件
			info.setPost_username(((InternetAddress) fromList[0]).getAddress()); //寄件人姓名
		}
		Address[] ccList = tempMessage.getRecipients(Message.RecipientType.CC);
		if ((ccList != null) && ccList.length > 0) {
			info.setCopy_send(((InternetAddress) ccList[0]).getAddress()); //收件人地址
		}
		Address[] bccList = tempMessage
				.getRecipients(Message.RecipientType.BCC);
		if ((bccList != null) && bccList.length > 0) {
			info.setDense_send(((InternetAddress) bccList[0]).getAddress()); //收件人地址
		}
	}

	private void setPop3MailSendDate(MailVO info, Message tempMessage)
			throws MessagingException {
		Date date = tempMessage.getSentDate();
		System.out.println("-------");
		java.util.Calendar now = java.util.Calendar.getInstance();
		if (tempMessage.getSentDate() != null)
			now.setTime(tempMessage.getSentDate());
		int iMonth = date.getMonth();
		int iDate = date.getDate();
		String month = setNumGreaterThanNine(iMonth);
		String dateNum = setNumGreaterThanNine(iDate);
		StringBuffer strDate = new StringBuffer();
		strDate.setLength(0);
		strDate.append(1900 + date.getYear()).append("-");
		strDate.append(month).append("-").append(dateNum);
		strDate.append(" ").append(date.getHours()).append(":");
		strDate.append(date.getMinutes()).append(":").append(date.getSeconds());
		info.setSend_date(strDate.toString()); //发送日期
	}

	private String setNumGreaterThanNine(int iMonth) {
		String month = (iMonth < 9) ? "0" + String.valueOf(iMonth + 1) : String
				.valueOf(iMonth + 1);
		return month;
	}

	/**
	 * 解析邮件:该方法采用递归算法来来解析邮件的块之间的关系
	 * 
	 * @param p
	 *            属于邮件接口类(因MimeMessage,MultiPart都有实现这个接口)
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void dumpPart(Part p) throws MessagingException, IOException {

		String ct = p.getContentType();
		String strFile = new String();
		if ((p.getFileName() != null)) {
			if (!p.getFileName().equals("")) {
				//this.setFileName(toUnicode(
				// MimeUtility.decodeText(p.getFileName())));
				this.setFileName(MimeUtility.decodeText(p.getFileName()));
			}
		}
		/*
		 * 使用isMimeType来看这个块是属于什么类型
		 */
		Object content = p.getContent();
		String strValue = new String(""); //临时值
		if (p.isMimeType("text/plain")) {
			System.out.println("属于 Asccii码格式");
			System.out.println("---------------------------");
			String disp = toString(p.getDisposition());
			if (!disp.equals(Part.ATTACHMENT)) {
				strValue = MimeUtility.decodeText((String) p.getContent());
				sbContent.append(strValue);
			}
			strValue = null; //回收垃圾
			disp = null;
		} else if (p.isMimeType("text/html")) {
			System.out.println("属于  html 格式");
			System.out.println("---------------------------");
			String disp = toString(p.getDisposition());
			if (!disp.equals(Part.ATTACHMENT)) {

				strValue = MimeUtility.decodeText((String) p.getContent());
				sbContent.append(strValue);
			}
			strValue = null; //回收垃圾
			disp = null;
		} else if (p.isMimeType("multipart/*")) {
			System.out.println("---属于多个块的邮件-----------");
			System.out.println("---------------------------");
			Multipart mp = (Multipart) p.getContent();
			level++;
			int count = mp.getCount();
			for (int i = 0; i < count; i++)
				dumpPart(mp.getBodyPart(i));
			level--;
		} else if (p.isMimeType("message/rfc822")) { //属于Internet协议
			level++;
			System.out.println("This is a Nested Message");
			System.out.println("----------- 属于Internet协议 ----------------");
			dumpPart((Part) p.getContent());
			level--;
		} else {
			//看看这个类型属于什么如果是字串型加入sbContent,让它充当文件的主体内容
			Object o = p.getContent();
			if (o instanceof String) {
				System.out.println("是字串型");
				System.out.println("------------ String ---------------");
				//System.out.println((String)o);
				sbContent.append((String) o);
			} else { //不知道是什么类型
				System.out.println("属于不知道的类型");
				System.out.println("---------- unkown type-----------------");
				pr(o.toString());
				//sbContent.append((String)o);
			}
		}

		//如果是附件类型让它保存到文件当中
		if (level != 0 && !p.isMimeType("multipart/*")) {

			String disp = p.getDisposition();
			System.out.println("------> " + level + "  disp---> " + disp);
			// 如果是附件内容就下载
			if (disp != null
					&& (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp
							.equals(Part.INLINE))) {
				try {
					System.out.println("是附件---------------------------");
					strBuf.setLength(0);
					strBuf.append(serverPath).append(uploadFile.getDir())
							.append("\\mail_web\\");

					if (this.getFileName() == null)
						this.setFileName("附件");
					String fileDirName = strBuf.toString();
					System.out.println("--- file dir is " + fileDirName);
					//创建邮件目录
					uploadFile.createDirtory(fileDirName);
					String realFileName = IdMng.getModuleID(String
							.valueOf(userId))
							+ this.getExtName(this.getFileName());
					strBuf.append(realFileName);
					String filePathName = strBuf.toString();

					File file = new File(filePathName);
					if (file.exists())
						throw new IOException("文件已存在");
					OutputStream os = new BufferedOutputStream(
							new FileOutputStream(file));
					InputStream is = p.getInputStream();
					int c;
					while ((c = is.read()) != -1)
						os.write(c);
					os.close();
					//将当前附件内容先存入堆栈当中

					String insertFilePath = uploadFile.getDir() + "mail_web/"
							+ realFileName;
					this.addAttchList(this.getFileName(), insertFilePath);

				} catch (IOException ex) {
					System.out.println("------存储附件失败--------: " + ex);
				}
				System.out.println("---------------------------");
			}
		}
	}

	/*
	 * private void dumpPart(Part p) throws MessagingException, IOException {
	 * String ct = p.getContentType(); String strFile = new String();
	 * setFileNameThroughPart(p); /* 使用isMimeType来看这个块是属于什么类型
	 */
	/*
	 * Object content = p.getContent(); if (p.isMimeType("text/plain")) {
	 * addAscciiMessage(p); } else if (p.isMimeType("text/html")) { //System.out.println("属于
	 * html 格式"); //System.out.println("---------------------------"); addHtmlMessage(p); } else
	 * if (p.isMimeType("multipart/*")) { //System.out.println("---属于多个块的邮件-----------");
	 * //System.out.println("---------------------------"); Multipart mp = (Multipart)
	 * p.getContent(); level++; int count = mp.getCount(); for (int i = 0; i <
	 * count; i++) { dumpPart(mp.getBodyPart(i)); } level--; } else if
	 * (p.isMimeType("message/rfc822")) { //属于Internet协议 level++; //System.out.println("This is
	 * a Nested Message"); //System.out.println("----------- 属于Internet协议 ----------------");
	 * dumpPart( (Part) p.getContent()); level--; } else {
	 * //看看这个类型属于什么如果是字串型加入sbContent,让它充当文件的主体内容 Object o = p.getContent(); if
	 * (o instanceof String) { //System.out.println("是字串型"); //System.out.println("------------ String
	 * ---------------"); //System.out.println((String)o); sbContent.append(
	 * (String) o); } else { //不知道是什么类型 //System.out.println("属于不知道的类型"); //System.out.println("----------
	 * unkown type-----------------"); //pr(o.toString());
	 * //sbContent.append((String)o); } }
	 * 
	 * //如果是附件类型让它保存到文件当中 if (level != 0 && !p.isMimeType("multipart/*")) {
	 * 
	 * addAttachFile(p); } }
	 */

	private void addAttachFile(Part p) throws MessagingException {
		String disp = p.getDisposition();
		System.out.println("------> " + level + "  disp---> " + disp);
		// 如果是附件内容就下载
		int i = 0;
		if (disp != null
				&& (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp
						.equals(Part.INLINE))) {
			uploadOneAttachFile(p);
			System.out.println("-----------------" + i + "----------");
			i++;
		}
	}

	private void addHtmlMessage(Part p) throws UnsupportedEncodingException,
			IOException, MessagingException {
		String disp = toString(p.getDisposition());
		String strValue = new String(""); //临时值
		if (!disp.equals(Part.ATTACHMENT)) {

			strValue = MimeUtility.decodeText((String) p.getContent());
			sbContent.append(strValue);
		}
	}

	private void addAscciiMessage(Part p) throws UnsupportedEncodingException,
			IOException, MessagingException {
		System.out.println("属于 Asccii码格式");
		System.out.println("---------------------------");
		String disp = toString(p.getDisposition());
		String strValue = new String(""); //临时值
		if (!disp.equals(Part.ATTACHMENT)) {
			strValue = MimeUtility.decodeText((String) p.getContent());
			sbContent.append(strValue);
		}
	}

	private void setFileNameThroughPart(Part p)
			throws UnsupportedEncodingException, MessagingException {
		if ((p.getFileName() != null)) {
			if (!p.getFileName().equals("")) {
				//this.setFileName(toUnicode(
				// MimeUtility.decodeText(p.getFileName())));
				this.setFileName(MimeUtility.decodeText(p.getFileName()));
			}
		}
	}

	private void uploadOneAttachFile(Part p) throws MessagingException {
		try {
			System.out.println("是附件---------------------------");
			if (this.getFileName() == null) {
				this.setFileName("附件");
			}
			strBuf.setLength(0);
			strBuf.append(serverPath).append(uploadFile.getDir()).append(
					"\\mail_web\\");
			String fileDirName = strBuf.toString();
			System.out.println("--- file dir is " + fileDirName);
			System.out.println("--------------gggggggggggggggg----------");
			//创建邮件目录
			uploadFile.createDirtory(fileDirName);
			String realFileName = IdMng.getModuleID(String.valueOf(userId))
					+ this.getExtName(this.getFileName());
			strBuf.append(realFileName);
			String filePathName = strBuf.toString();
			//将附件上传到服务器
			uploadAttachFileToServer(p, filePathName);
			System.out
					.println("--------------fffffffffffffffffffff-----------------");
			System.out.println("--- dir in upload file : "
					+ uploadFile.getDir());
			String insertFilePath = uploadFile.getDir() + "\\mail_web\\"
					+ realFileName;
			//将当前附件内容先存入堆栈当中
			this.addAttchList(this.getFileName(), insertFilePath);

		} catch (IOException ex) {
			//System.out.println("------存储附件失败--------: " + ex);
		}
	}

	private void uploadAttachFileToServer(Part p, String filePathName)
			throws MessagingException, IOException {
		File file = new File(filePathName);
		if (file.exists())
			throw new IOException("文件已存在");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
		InputStream is = p.getInputStream();
		int c;
		while ((c = is.read()) != -1)
			os.write(c);
		os.close();
	}

	private Session setMailSession(Properties props, boolean bCheck) {
		Session mailSession;
		if (bCheck) {
			PopupAuthenticator auth = new PopupAuthenticator(userID, password);
			mailSession = Session.getInstance(props, auth);
		} else {
			mailSession = Session.getInstance(props, null);
		}
		return mailSession;
	}

	private void setSendMailProperty(Properties props, String smtpServer,
			boolean bCheck) {
		props.put("mail.transport.protocol", protocol);
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.port", String.valueOf(this.port));
		if (bCheck) {
			props.put("mail.smtp.auth", "true");
		}
	}

	private void setSmtpPort(Pop3ConfigVO configView) {
		this.port = configView.getSmtp_server().substring(
				configView.getSmtp_server().indexOf(":") + 1);
	}

	private String setSmtpServer(Pop3ConfigVO configView) {
		String smtpServer;
		smtpServer = configView.getSmtp_server().substring(0,
				configView.getSmtp_server().indexOf(":"));
		return smtpServer;
	}

	private void setAttachBodyPart(ArrayList aryList, String strRealPath,
			Multipart mult) throws MessagingException {

		for (int idx = 0; idx < aryList.size(); idx++) {
			MailAttchVO view = (MailAttchVO) aryList.get(idx);
			strRealPath = serverPath + view.getFile_path();
			System.out.println("--- file path : " + strRealPath);
			MimeBodyPart mAttch = new MimeBodyPart();
			mAttch.setDisposition(Part.ATTACHMENT);
			FileDataSource fds = new FileDataSource(strRealPath);
			mAttch.setDataHandler(new DataHandler(fds));
			try {
				mAttch.setFileName(view.getAttch_name());
				//String attachName = this.getISOFileName(mAttch);
				//mAttch.setFileName(attachName);
				//System.out.println("--- attach name : " +
				// view.getAttch_name());
			} catch (Exception e) {
				e.printStackTrace();
			}
			mult.addBodyPart(mAttch);
		}
	}

	/**
	 * 错误处理
	 * 
	 * @param errMap
	 *            错误映射 类
	 * @param bErr
	 *            是否正确
	 */
	private void procSmtpError(HashMap errMap, boolean bErr) {
		if (bErr == true) {
			strBufErr.append("&nbsp;&nbsp;&nbsp;&nbsp; 连接服务器出错");
			strBufErr.append("<BR><BR>");

		}
		Address[] list = (Address[]) errMap.get("list");
		if (list != null) {
			for (int idx = 0; idx < list.length; idx++) {
				strBufErr.append("&nbsp;&nbsp;&nbsp;&nbsp; 无效的邮件地址").append(
						list[idx]).append("<BR>");
				bErr = false;
			}
		}
		list = (Address[]) errMap.get("unlist");
		if (list != null) {
			for (int idx = 0; idx < list.length; idx++) {
				strBufErr.append("&nbsp;&nbsp;&nbsp;&nbsp; 无效的邮件地址").append(
						list[idx]).append("<BR>");
				bErr = false;
			}
		}
		list = (Address[]) errMap.get("sentlist");
		if (list != null) {
			for (int idx = 0; idx < list.length; idx++) {
				strBufErr.append("&nbsp;&nbsp;&nbsp;&nbsp; 无效的邮件地址").append(
						list[idx]).append("<BR>");
				bErr = false;
			}
		}
	}

	private void procPop3Error(HashMap errMap, boolean bErr) {
		strBufErr.append("  帐号名称:").append(((String) errMap.get("NAME")));
		strBufErr.append("&nbsp;&nbsp;&nbsp;&nbsp;  POP3邮件服务器:").append(
				((String) errMap.get("POP3SERVER")));
		strBufErr.append("&nbsp;&nbsp;&nbsp;&nbsp; 连接状况:").append(
				((String) errMap.get("Connection")));
		strBufErr.append("<BR>");
		if (bErr == true) {
			strBufErr.append("&nbsp;&nbsp;&nbsp;&nbsp;  邮件个数:").append(
					((String) errMap.get("Message")));
			strBufErr.append("&nbsp;&nbsp;&nbsp;&nbsp;  接收成功邮件个数:").append(
					((String) errMap.get("goodMails")));
			strBufErr.append(
					"&nbsp;&nbsp;&nbsp;&nbsp;  接收失败邮件个数:<font color=red>")
					.append(((String) errMap.get("badMails")))
					.append("</font>");
			ArrayList aryErr = new ArrayList();
			aryErr = (ArrayList) errMap.get("nosize");
			for (int idx = 0; idx < aryErr.size(); idx++) {
				strBufErr.append("&nbsp;&nbsp;&nbsp;&nbsp;").append(
						(String) aryErr.get(idx));
			}
		}
		strBufErr.append("<BR>");
		strBufErr.append("<BR>");
	}

	/**
	 * 得到文件扩展名
	 * 
	 * @param strFileName
	 *            文件名字
	 * @return 护展名
	 */
	private String getExtName(String strFileName) {
		if (strFileName == null)
			return "";
		int iPos = 0;
		iPos = strFileName.lastIndexOf(".");
		if (iPos < 0)
			return "";
		else
			return strFileName.substring(iPos);
	}

	/**
	 * 先存到堆践当中
	 * 
	 * @param strFileName
	 *            文件名
	 * @param strFilePath
	 *            文件路径
	 */
	private void addAttchList(String strFileName, String strFilePath) {
		MailAttchVO attachVO = new MailAttchVO();
		System.out.println("--- addAttchList file path : " + strFilePath);
		//view.setFJBZ("1"); //外部
		attachVO.setFile_path(strFilePath);
		attachVO.setAttch_name(strFileName);
		attchList.add(attachVO);
	}

	/**
	 * 将gbk字符转为unicode字符
	 * 
	 * @param s
	 *            源字串
	 * @return unicode的字串
	 */
	public static String toUnicode(String s) {

		String v = null;
		if (s != null) {
			try {
				byte[] bytes = s.getBytes("ISO-8859-1");
				v = new String(bytes, "gb2312");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return v;
	}

	/**
	 * null转成空字串
	 * 
	 * @param strValue
	 *            原字符串
	 * @return 字串
	 */
	private String toString(String strValue) {
		return ((strValue == null) ? "" : (String) strValue);
	}

	/**
	 * <p>
	 * Title:验证子类
	 * </p>
	 * <p>
	 * Description:发送时验用
	 * </p>
	 * <p>
	 * Copyright: Copyright (c) 2003
	 * </p>
	 * <p>
	 * Company: GEVER
	 * </p>
	 * 
	 * @author Hu.Walker
	 * @version 0.9
	 */
	class PopupAuthenticator extends Authenticator {
		private String m_username = null;

		private String m_userpass = null;

		public void setUsername(String username) {
			m_username = username;
		}

		public void setUserpass(String userpass) {
			m_userpass = userpass;
		}

		public PopupAuthenticator(String user, String pass) {
			super();
			setUsername(user);
			setUserpass(pass);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(m_username, m_userpass);
		}
	}

	static String indentStr = "                                            ";

	private String userId;

	public static void pr(String s) {
		if (showmsg)
			System.out.println(indentStr.substring(0, level * 2) + s);
	}

	private void setBIsDel(Pop3ConfigVO popView) {
		//1不删除getDel_flag
		this.bIsDel = ("1".equals(popView.getDel_flag()) ? false : true);
		System.out.println("-------del ---bIsDel--" + bIsDel + "---popView.getDel_flag()= "+popView.getDel_flag());
	}

	public ArrayList getAryErrList() {
		return aryErrList;
	}

	public ArrayList getAryResult() {
		return aryResult;
	}

	public ArrayList getAttchList() {
		return attchList;
	}

	public boolean isBIsDel() {
		return bIsDel;
	}

	private String getFileName() {
		return fileName;
	}

	public int getLevel() {
		return level;
	}

	public Store getMyStore() {
		return myStore;
	}

	public String getPassword() {
		return password;
	}

	public String getPort() {
		return port;
	}

	public String getProtocol() {
		return protocol;
	}

	public StringBuffer getSbContent() {
		return sbContent;
	}

	public String getServer() {
		return server;
	}

	public String getServerPath() {
		return serverPath;
	}

	public boolean isShowmsg() {
		return showmsg;
	}

	public StringBuffer getStrBuf() {
		return strBuf;
	}

	public StringBuffer getStrBufErr() {
		return strBufErr;
	}

	public void setAryErrList(ArrayList aryErrList) {
		this.aryErrList = aryErrList;
	}

	public void setAryResult(ArrayList aryResult) {
		this.aryResult = aryResult;
	}

	public void setAttchList(ArrayList attchList) {
		this.attchList = attchList;
	}

	public void setBIsDel(boolean bIsDel) {
		this.bIsDel = bIsDel;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setMyStore(Store myStore) {
		this.myStore = myStore;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setSbContent(StringBuffer sbContent) {
		this.sbContent = sbContent;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public void setShowmsg(boolean showmsg) {
		this.showmsg = showmsg;
	}

	public void setStrBuf(StringBuffer strBuf) {
		this.strBuf = strBuf;
	}

	public void setStrBufErr(StringBuffer strBufErr) {
		this.strBufErr = strBufErr;
	}

}