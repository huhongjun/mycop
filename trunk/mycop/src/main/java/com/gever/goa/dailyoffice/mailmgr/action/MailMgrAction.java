package com.gever.goa.dailyoffice.mailmgr.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.dao.MailCapacityDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailConstant;
import com.gever.goa.dailyoffice.mailmgr.dao.MailDirectoryDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailInfoDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrFactory;
import com.gever.goa.dailyoffice.mailmgr.dao.Pop3ConfigDAO;
import com.gever.goa.dailyoffice.mailmgr.util.MailAttachUtil;
import com.gever.goa.dailyoffice.mailmgr.vo.MailAttchVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailCapacityVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailDirectoryVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailErrorInfoVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailInfoVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailVO;
import com.gever.goa.dailyoffice.mailmgr.vo.Pop3ConfigVO;
import com.gever.goa.dailyoffice.tools.dao.CardcaseDao;
import com.gever.goa.dailyoffice.tools.dao.ToolsFactory;
import com.gever.goa.web.util.UploadFile;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.Constant;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.struts.form.BaseForm;
import com.gever.sysman.api.OrganizationUtil;
import com.gever.util.StringUtils;

public class MailMgrAction extends BaseAction {
    MailMgrDAO mailMgrDao = null;
    MailCapacityDAO mailCapacityDao = null;
    MailDirectoryDAO mailDirectoryDao = null;
    MailInfoDAO mailInfoDao = null;
    Pop3ConfigDAO pop3ConfigDao = null;
    CardcaseDao cardcaseDAO = null;
    OrganizationUtil systemMngUtil = new OrganizationUtil();
    private String returnSeperator = "<br>";
    private String beginTime = " 00:00:00";
    private String endTime = " 23:59:59";
    private String serperator = ",";

    public static final String FORWARD_MAIL_LIST = "mailindex";
    public MailMgrAction() {
    }

    /**
     * 初始化页面数据
     * @param actionform 当前的vo对象
     * @return 当前所用的vo对象
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        MailMgrForm myForm = (MailMgrForm) cfg.getBaseForm(); //得到form变量
        mailMgrDao = MailMgrFactory.getInstance().createMailMgr(super.dbData); //得到相对应dao的实例
        cfg.setBaseDao( (BaseDAO) mailMgrDao);
        //setPageNumber(myForm);
        //super.setPageNumber(20);
        if (myForm.getVo() == null) {
            myForm.setVo(new MailVO());
        }
    }

    /**
     *
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @throws DefaultException
     * @throws Exception
     * @return String
     */
    public String toList(GoaActionConfig cfg) throws DefaultException,
        Exception {
        String userId = null;
        String realPath = null;
        MailCapacityVO capacityVo = null;
        MailDirectoryVO mailDirVo = null;
        List otherMailDirList = null;
        MailMgrForm mailMgrForm = (MailMgrForm) cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();
        HttpSession session = request.getSession();

        realPath = request.getRealPath("/");
        /** @todo 为便于调试暂时事用默认值 */
        //mailDirId = "0";
        userId = (String) session.getAttribute(Constant.USER_ID);
        StringBuffer sqlWhere = new StringBuffer();
        //userId = "8001";

        /** @todo 初始化列表页面查询SQL */
        initListPageSql(userId, mailMgrForm, request, sqlWhere);

        //System.out.println("--- 1111 mailDirId is : " + mailDirId);
        //System.out.println("--- GotoMailDirId is : (" + mailMgrForm.getGotoMailDirId() + ")");

        //System.out.println("--- 22222222 mailDirId is : " + mailDirId);
        String[] selectedMail = request.getParameterValues("fid");
       /* if (selectedMail != null) {
            System.out.println("--- selected mail length is " +
                               selectedMail.length);
        }*/
        if (request.getParameter("deleteMailId") != null) {
            selectedMail = new String[1];
            selectedMail[0] = request.getParameter("deleteMailId");
            /*System.out.println("--- moveMailToGarbage deleteMailId is " +
                               selectedMail[0]);*/

        }
        /** @todo 转移邮件 */
        moveMailToOtherDir(request, selectedMail);
        /** @todo 删除邮件到垃圾箱 或者 直接从查看页面删除邮件*/
        deleteMailToGarbage(request, selectedMail);
        /** @todo 还原邮件 */
        revertMailFromGarbage(request, selectedMail);
        /** @todo 还原垃圾箱中所有邮件 */
        revertAllMailFromGarbage(userId, request);
        /** @todo 清空所有垃圾箱邮件 */
        removeAllMailInGarbage(userId, realPath, request);
        /** @todo 侧底删除邮件 */
        removeMailForEver(realPath, request, selectedMail);

        //System.out.println("--- 33333 mailDirId is : " + mailDirId);

        mailDirVo = getUserCurrentMailDirInfo(mailMgrForm, userId);
        capacityVo = this.getUserMailCapacity(userId);
        //setPageNumber(mailMgrForm);
        mailMgrForm.setMailDirectoryVo(mailDirVo);
        mailMgrForm.setMailCapacityVo(capacityVo);
        mailMgrForm.setGotoMailDirId("");
        /** @todo 设置邮件信息设置 */
        //setMailInfoInMailMgrForm(mailMgrForm);
        otherMailDirList = this.mailDirectoryDao.queryAllExceptCurrentDir(
            mailMgrForm.getMailDirId(), userId);
        cfg.getBaseForm().setSqlWhere(sqlWhere.toString());
        mailMgrForm.setPageNumber("");
        this.setPageNumber(cfg);
        super.doPage(cfg, "");
        session.setAttribute("otherMailDirList", otherMailDirList);

        return super.FORWORD_LIST_PAGE;
    }

    private int setPageNumberByUserId(MailMgrForm mailMgrForm) throws
        DefaultException {
        int resultNumber = -1;
        setMailInfoDao();
       // System.out.println("--- mail info user id is " + mailMgrForm.getUserId());
        if (mailMgrForm.getPageNumber() == null ||
            "".equals(mailMgrForm.getPageNumber())) {
            MailInfoVO infoVo = mailInfoDao.getMailInfoByUserId(mailMgrForm.
                getUserId());
            mailMgrForm.setIsPageSetuped(false);
            mailMgrDao.setPageNumber("");
            if (infoVo.getPagenumber().trim().length() > 0) {
                //mailMgrDao.setPageNumber(infoVo.getPagenumber());
                mailMgrForm.setIsPageSetuped(true);
                mailMgrForm.setPageNumber(infoVo.getPagenumber());
                resultNumber = Integer.parseInt(infoVo.getPagenumber());
            }
        } else {
            resultNumber = Integer.parseInt(mailMgrForm.getPageNumber());
        }
        return resultNumber;
    }

    private void setMailInfoDao() {
        mailInfoDao = MailMgrFactory.getInstance().creatMailInfo(super.dbData);
    }

    private void removeMailForEver(String realPath, HttpServletRequest request,
                                   String[] selectedMail) throws
        DefaultException {
        if (request.getParameter("deleteMailEver") != null) {
            mailMgrDao.removeMails(selectedMail, realPath);
        }
    }

    /**
     * 编写邮件，包括发送邮件，草稿夹编辑邮件
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @return String
     */
    public String toEdit(GoaActionConfig cfg) throws DefaultException {
        MailMgrForm mailMgrForm = (MailMgrForm) cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();
        //HttpSession session = request.getSession();
        String mailId = null;
        List pop3ConfigList = new ArrayList(); //pop3Config列表用于显示用户设置smtp服务器
        mailMgrForm.setCurerentMailType("innerMail");

        MailVO recivedMailVo = mailMgrForm.getMailVo();
        if (recivedMailVo != null) {
            mailId = recivedMailVo.getSerial_num();
        } //若是在本页面刷新,或者是在查看页面回复则只能从此取得邮件Id
        //outPrintAllParamInRequest(request);
        if (request.getParameter("mailSelect") != null) {
            mailId = request.getParameter("mailSelect");
            //System.out.println("--- mailId from mailList : " + mailId);
        } //若是从邮件列表页面回复则取得被回复邮件Id
        //MailVO currentMial = null;
        //System.out.println("--- mail id is " + mailId + " ---------------");
        if (mailId != null && "".equals(mailId) == false) {
         //   System.out.println("--- mail id is " + mailId);
            //if (recivedMailVo == null) {
            recivedMailVo = mailMgrDao.getMailinfoByPk(mailId);
            //System.out.println("--- reciever is " + recivedMailVo.getPost_address());
            //}
        } //取得邮件信息并根据信息判断是否为外部邮件
        //outPrintMailInfo(mailMgrForm, request, mailId, iReplyAcionType);//相关信息打印
        //if(mailMgrForm.getOldMailId().equals(mailId) == false) {

        MailVO writeMailVO = setWriteMailInfo(mailMgrForm, request, mailId, recivedMailVo);
        mailMgrForm.setMailVo(writeMailVO);

        /*if(writeMailVO != null) {
            System.out.println("--- writeMailVO original content : " +
                               writeMailVO.getOriginalContent());
            System.out.println("--- receive address in write mail name is " +
                               writeMailVO.getReceive_address_name());

            System.out.println(
                "--- receive address in write mail of mail form name is " +
                mailMgrForm.getMailVo().getReceive_address_name());
        }*/

        if (request.getParameter("exteriomail") != null) {
            mailMgrForm.setCurerentMailType("exterioMail");
        } else if (recivedMailVo != null && String.valueOf(MailConstant.POP3_MAIL).equals(recivedMailVo.
            getMail_type())) {
            mailMgrForm.setCurerentMailType("exterioMail");
        } else if (writeMailVO != null && String.valueOf(MailConstant.POP3_MAIL).equals(writeMailVO.
            getMail_type())) {
            mailMgrForm.setCurerentMailType("exterioMail");
            //pop3ConfigList = setPop3ConfigList(mailMgrForm);
        }

        if ("exterioMail".equals(mailMgrForm.getCurerentMailType())) {
            pop3ConfigList = setPop3ConfigList(mailMgrForm);
        }
        request.setAttribute("pop3ConfigList", pop3ConfigList);
        //System.out.println("--- pop3config list size is " + pop3ConfigList.size());
        //outPrintMailInfo(mailMgrForm.getMailInfo());

        if (mailMgrForm.getMailVo() == null) {
            //System.out.println("-------- 33333333333 mail vo is null -----");
            mailMgrForm.setMailVo(new MailVO());
        }

        /**System.out.println(
            "--- 222222 receive address in write mail of mail form name is " +
            mailMgrForm.getMailVo().getReceive_address_name());*/

        /** @todo 设置邮件信息设置 */
        setMailInfoInMailMgrForm(mailMgrForm);

        String forward = "";
        if (request.getParameter("writeexteriormail") != null) {

        } else {

        }
        /*System.out.println(
            "--- 33333333 receive address in write mail of mail form name is " +
            mailMgrForm.getMailVo().getReceive_address_name());*/
        log.showLog("---------- MailVO priority is : " + mailMgrForm.getMailVo().getPriority());

		this.backupAttachInfo(cfg);

        return this.FORWORD_EDIT_PAGE;
    }

    private MailVO setWriteMailInfo(MailMgrForm mailMgrForm,
                                  HttpServletRequest request, String mailId,
                                  MailVO recivedMailVo) throws
        NumberFormatException, DefaultException {
        MailVO writeMailVO = null;
        if (request.getParameter("fw") != null) {
            //setFwMial(mailMgrForm, recivedMailVo);
            System.out.println("----------------- fw -------------");
            writeMailVO = mailMgrDao.getReplyMailInfoByActionType(
                MailConstant.RE_MAIL_WITH_ORIGINAL_MAIL, recivedMailVo);
            writeMailVO.setTitle("FW:" + recivedMailVo.getTitle());
            writeMailVO.setReceive_address("");
            writeMailVO.setReceive_address_name("");
        } else if (request.getParameter("editDraft") != null) {
            System.out.println("----------------- editDraft -------------");
            mailId = request.getParameter("mailId");
            writeMailVO = mailMgrDao.getMailinfoByPk(mailId);
            //打开邮件处理
            mailMgrDao.openMail(writeMailVO);

            List attachList = mailMgrDao.setAttachListFromSender(writeMailVO);
            writeMailVO.setAttachList(attachList);
            setMailExtraProperties(writeMailVO);

            //mailMgrForm.setMailVo(writeMailVO);
            mailMgrForm.setActionType("modify");
        } else if (request.getParameter("replyActionType") != null) {
            //System.out.println(  "----------------- replyActionType -------------");
            int iReplyAcionType = 0; //回复动作类型
            String replyActionType = request.getParameter("replyActionType");
            iReplyAcionType = Integer.parseInt(replyActionType);
            if (iReplyAcionType > 0 && recivedMailVo != null) {
                //System.out.println("----- 11111111111 --------");
                //if ("".equals(recivedMailVo.getSerial_num()) ) {
                writeMailVO = mailMgrDao.getReplyMailInfoByActionType(
                    iReplyAcionType, recivedMailVo);
                /*} else {
                    System.out.println("----- 3333 ---------");
                    writeMailVO = mailMgrDao.getReplyMailInfoByActionType(
                        iReplyAcionType, recivedMailVo);
                                 }*/
                writeMailVO.setUser_code(recivedMailVo.getUser_code());
                setMailExtraProperties(writeMailVO);
                String title = "".equals(writeMailVO.getTitle()) ? "无标题" :
                    writeMailVO.getTitle();
                writeMailVO.setTitle("RE:" + title);

                if (writeMailVO != null && "2".equals(writeMailVO.getMail_type())) {
                    mailMgrForm.setCurerentMailType("exterioMail");
                }
                //mailMgrForm.setMailVo(writeMailVO);
                mailMgrForm.setOldMailDirId(mailId);

            }
            if (iReplyAcionType == 0) { //说明只为回复不作其他操作
                //System.out.println("-------- 2222222222 reply only-----");
                if (recivedMailVo != null && recivedMailVo.getPost_address() != null) {
                    //System.out.println("--- recivedMailVo ueser code : " + recivedMailVo.getUser_code());
                    //System.out.println("--- writeMailVO user code : " + writeMailVO.getUser_code());
                    writeMailVO = new MailVO();
                    writeMailVO.setReceive_address(recivedMailVo.
                        getPost_address());
                    writeMailVO.setReceive_address_name(recivedMailVo.getPost_username());
                    writeMailVO.setTitle("RE:" + recivedMailVo.getTitle());
                }
                //System.out.println("--- receive address name is " +  writeMailVO.getReceive_address_name());
            }

        }else if(request.getParameter("resend")!=null){//重发
            List attachList = null;
            if (recivedMailVo.getAttachList() != null
                && recivedMailVo.getAttachList().size() > 0) {
                attachList = recivedMailVo.getAttachList();
            }
            if (attachList.size() > 0) {
                recivedMailVo.setAttachList(attachList);
                recivedMailVo.setAttachcount(String.valueOf(attachList.size()));
            }
            return recivedMailVo;
        }
        return writeMailVO;
    }

    private void setMailInfoInMailMgrForm(MailMgrForm mailMgrForm) throws
        DefaultException {
        //if (mailMgrForm.getMailInfo() == null) {
            this.setMailInfoDao();
            //System.out.println("----- userId is " + mailMgrForm.getUserId());
            MailInfoVO mailInfo = mailInfoDao.getMailInfoByUserId(mailMgrForm.
                getUserId());
            mailMgrForm.setMailInfo(mailInfo);
       // }
        if (mailMgrForm.getMailInfo() != null) {
            mailMgrForm.getMailVo().setPriority(mailMgrForm.getMailInfo().
                                                getMail_level());
            System.out.println("---- user mail priority is " + mailMgrForm.getMailVo().getPriority());
        }

        //outPrintMailInfo(mailInfo);
    }

    private void outPrintMailInfo(MailInfoVO mailInfo) {
        System.out.println("--- mail info pagenumber : " +
                           mailInfo.getPagenumber());
        System.out.println("--- mail info Lable_name : " +
                           mailInfo.getLable_name());
        System.out.println("--- mail info mail lever : " +
                           mailInfo.getMail_level());
        System.out.println("--- mail info post flag : " + mailInfo.getPost_flag());
        System.out.println("--- mail info return flag : " +
                           mailInfo.getReturn_flag());
        System.out.println("--- mail info sign flag : " + mailInfo.getSign_flag());
        System.out.println("--- mail info user code : " + mailInfo.getUser_code());
    }

    private void outPrintAllParamInRequest(HttpServletRequest request) {
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            System.out.println("--- paramName : " + paramNames.nextElement());
        }
    }

    private void setFwMial(MailMgrForm mailMgrForm, MailVO recivedMailVo) {
        MailVO fwMailVo = new MailVO();
        fwMailVo.setTitle(recivedMailVo.getTitle());
        fwMailVo.setOriginalContent(recivedMailVo.getContent());
        fwMailVo.setAttachcount(recivedMailVo.getAttachcount());
        fwMailVo.setAttachList(recivedMailVo.getAttachList());
        mailMgrForm.setMailVo(fwMailVo);
    }

    private void outPrintMailInfo(MailMgrForm mailMgrForm,
                                  HttpServletRequest request, String mailId,
                                  int iReplyAcionType) {
        System.out.println("--- mailselect is " + request.getParameter("fid"));
        System.out.println("--- mailVO id is " + mailId);
        System.out.println("--- mailActionType : " +
                           mailMgrForm.getMailActionType());
        System.out.println("--- replyActionType : " + iReplyAcionType);
    }

    private List setPop3ConfigList(MailMgrForm mailMgrForm) throws
        DefaultException {
        List pop3ConfigList;
        setPop3ConfigDao();
        pop3ConfigList = pop3ConfigDao.getPop3ConfigByUserId(mailMgrForm.
            getUserId());
        return pop3ConfigList;
    }

    private void setPop3ConfigDao() {
        pop3ConfigDao = MailMgrFactory.getInstance().createPop3Config(super.
            dbData);
    }

    /**
     * 邮件查看
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @return String
     */
    public String toView(GoaActionConfig cfg) throws DefaultException {
        MailMgrForm mailMgrForm = (MailMgrForm) cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();
        HttpSession session = request.getSession();
        String forward = null;

        MailVO mailVO = null;
        String mailId = null;
        if (request.getParameter("fid") != null) {
            mailId = request.getParameter("fid");
            mailMgrForm.setFid(mailId);
            mailVO = mailMgrDao.getMailinfoByPk(mailId);
            System.out.println("--- current mail is already read : " +
                               (mailVO.getRead_flag().equals("1") ? true : false));
            mailMgrDao.openMail(mailVO);
            if (mailVO != null) {
                if (Integer.parseInt(mailVO.getAttachcount()) > 0) {
                    List attachList = mailMgrDao.setAttachListFromSender(mailVO);
                    mailVO.setAttachList(attachList);
                    System.out.println("------- attachlist.size in mailVo is " +
                                       mailVO.getAttachList().size());
                }
                //setMailViewExtraProperties(mailVO);
                setMailExtraProperties(mailVO);

            }
            if ("".equals(mailVO.getTitle())) {
                mailVO.setTitle("无标题");
            }
            mailMgrForm.setMailVo(mailVO);
            forward = this.FORWORD_VIEW_PAGE;
        }
        if (request.getParameter("back") != null) {
            System.out.println("------- goto List page ----------\n\n\n");
            forward = this.FORWORD_LIST_PAGE;
        }
        /** @todo 设置邮件信息设置 */
        setMailInfoInMailMgrForm(mailMgrForm);

        //outPrintMailInfo(mailMgrForm.getMailInfo());
        return forward;
    }

    /**
     * 修改草稿夹中邮件。删掉原件，新增修改后信件
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @return String
     */
    public String modifyDraft(GoaActionConfig cfg) throws DefaultException {
        MailMgrForm mailForm = (MailMgrForm) cfg.getBaseForm();
        MailVO mailVO = mailForm.getMailVo();
        String mailIds[] = {mailVO.getSerial_num()};
        mailMgrDao.removeMails(mailIds, this.getRealPath(cfg.getRequest()));
        return this.sendMail(cfg);
    }

    /**
     * 邮件发送
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @return String
     */
    public String sendMail(GoaActionConfig cfg) throws DefaultException {

    	this.restoreAttachInfo(cfg);

        HttpServletRequest request = cfg.getRequest();
        MailMgrForm mailForm = (MailMgrForm) cfg.getBaseForm();
        MailVO mailVO = mailForm.getMailVo();
        MailErrorInfoVO errorInfoVo = null;
        String userId = null;
        String forward = null;
        userId = mailForm.getUserId();
        System.out.println("--- currentMailtype is " +
                           mailForm.getCurerentMailType());
        System.out.println("--- mailVO priority is " + mailVO.getPriority());
        if ("exterioMail".equals(mailForm.getCurerentMailType())) {
            int actionType = mailForm.getMailActionType() |
                MailConstant.POP3_MAIL;
            System.out.println("--- 发送邮件类型为 : " + actionType);
            mailForm.setMailActionType(actionType);
            this.setPop3ConfigDao();
            Pop3ConfigVO pop3Info = pop3ConfigDao.queryPop3ConfigInfoByPK(
                mailForm.getSmtpState());
            //发送邮件测试
            //initTestSendPop3MailInfo(pop3Info);
            //发送邮件测试
            mailMgrDao.setPop3ConfigVo(pop3Info);
            //mailVO.setReceive_address("test@YFS007");//接收邮件测试，使用默认接收人
            mailMgrDao.setServerPath(request.getRealPath("/"));
        }
        mailVO.setUser_code(userId);
        /** @todo 无法从登录页面进入，暂时使用默认用户 */
        //userId = "8001";
        //mailVO.setUser_code(userId);
        mailVO.setPost_address(userId);
        OrganizationUtil systemMngUtil = new OrganizationUtil();
        mailVO.setPost_username(systemMngUtil.getUserNamesByUserdIDs(userId,serperator));
        /*if(mailForm.getMailDirId() != null) {
         System.out.println("-------- current maildirId is " + mailForm.getMailDirId());
                 } else {
                 }*/
        //发送邮件成功完毕后回到收件夹
        if (request.getParameter("mailDirId") == null) {
            mailForm.setMailDirId(MailConstant.DIR_RECIEVE_FOLDER);
        }
        outPrintWritedMailInfo(request, mailForm, mailVO);
        setAddtionalContent(mailVO);
        List attachList = this.saveAttchFiles(cfg.getRequest(), mailForm);
        mailVO.setAttachList(attachList);
        boolean isSaveOnly = ((mailForm.getMailActionType() & MailConstant.SAVE_ONLY_MAIL) > 0) ? true : false;
        boolean save = ( (mailForm.getMailActionType() & MailConstant.SAVE_POST_MAIL) > 0) ? true : false;
        Map map = mailMgrDao.sendMail(mailForm.getMailVo(), attachList,
                                      mailForm.getMailActionType());
        System.out.println("----- 222 mail priorityi is " + mailForm.getMailVo().getPriority());
        errorInfoVo = (MailErrorInfoVO) map.get("errorInfo");
        List errorList = (List) map.get("errorInfoList");
        String pop3MailResult = (String) map.get("pop3SendResult");
        System.out.println("--- error message is " + errorInfoVo.getErrorMsg());
        if(request.getParameter("saveToDraft") != null) {
            isSaveOnly = true;
        }
        mailForm.setErrorInfo(errorInfoVo);
        mailForm.setErrorList(errorList);
        mailForm.setPop3MailResult(pop3MailResult);
        mailForm.setSaveOnly(isSaveOnly);
        mailForm.setSave(save);
        mailForm.setMailActionType(0);
        /*System.out.println("----- save only mail is " + mailForm.isSaveOnly());
        System.out.println("----- save mail is " + mailForm.isSave());
        System.out.println("--- errorList size is " + errorList.size());
        System.out.println("--- pop3MailResult is " + pop3MailResult);*/
        if ("".equals(errorInfoVo.getErrorMsg())) {
            forward = "result";
        } else {
            forward = super.FORWORD_LIST_PAGE;
            mailForm.setMailVo(null);
        }
            //forward = "result";
        return forward;
    }

    public String toResult(GoaActionConfig cfg) {
        System.out.println("--- in toResult ---");
        return "result";
    }
    private void setAddtionalContent(MailVO mailVO) {
        String originalContent = mailVO.getOriginalContent();
        if (originalContent != null && originalContent.trim().length() > 0) {
            System.out.println("--- original content is " +
                               mailVO.getOriginalContent());
            String content = mailVO.getContent() + returnSeperator + "转发原文如下:" +
                returnSeperator + mailVO.getOriginalContent();
            mailVO.setContent(content);
        }
    }

    private void initTestSendPop3MailInfo(Pop3ConfigVO pop3Info) {
        pop3Info.setPop3_account("dong@gever");
        pop3Info.setPop3_address("YFS007:110");
        pop3Info.setPop3_name("dong");
        pop3Info.setPop3_pwd("test");
        pop3Info.setShow_address("dong@gever");
        pop3Info.setSmtp_server("YFS007:25");
        pop3Info.setSmtp_auth("1");
        pop3Info.setSmtp_name("dong");
        pop3Info.setSmtp_pwd("test");
    }

    private void outPrintWritedMailInfo(HttpServletRequest request,
                                        MailMgrForm mailForm, MailVO mailVO) {
        System.out.println("\n--- mail reciever : " + mailVO.getReceive_address());
        System.out.println("\n--- mail title : " + mailVO.getTitle());
        System.out.println("\n--- mial content : " + mailVO.getContent());
        System.out.println("\n--- mail post_address : " +
                           mailVO.getPost_address());
        System.out.println("\n--- mail action : " + mailForm.getMailActionType());
        System.out.println("\n--- mail priority : " + mailVO.getPriority());
        System.out.println("--- mail priority in requset : " +
                           request.getParameter("mailVO.priority") == null ? "" :
                           request.getParameter("mailVO.priority"));
        System.out.println("--- mail priority in mailform : " +
                           mailForm.getMailVo().getPriority());
    }

    /**
     * 初始化列表页面SQL语句
     * @param userId String
     * @param mailMgrForm MailMgrForm
     * @param request HttpServletRequest
     * @param sqlWhere StringBuffer
     */
    private void initListPageSql(String userId, MailMgrForm mailMgrForm,
                                 HttpServletRequest request,
                                 StringBuffer sqlWhere) {
        String mailDirId = null;
        HttpSession session = request.getSession();
        //System.out.println("--- maildirId in mailMgrForm: " + mailMgrForm.getMailDirId());
        if (request.getParameter("mailDirId") != null) {
            //System.out.println("--- mailDirId in requset is " + request.getParameter("maildirid"));
            mailDirId = request.getParameter("mailDirId"); //如果从邮件夹列表跳转过来
            mailMgrForm.setMailDirId(mailDirId);
            session.setAttribute("mailDirId", mailDirId);
            clearQueryParm(mailMgrForm);
        } else if ("".equals(mailMgrForm.getMailDirId()) == false) {
            mailDirId = mailMgrForm.getMailDirId(); //如果在当前页翻页
        }
        if (request.getParameter("gotoMailDir") != null) {
            mailDirId = mailMgrForm.getGotoMailDirId(); //如果要跳转到其他邮件夹
            clearQueryParm(mailMgrForm);
        }
        if (mailDirId == null) {
            mailDirId = (String) session.getAttribute("mailDirId");
        }
        //

        if (mailDirId != null) {
            sqlWhere.append(" and mail.mail_dir_id = '").append(mailDirId).
                append("' and mail.user_code = ").append(userId);
            mailMgrForm.setMailDirId(mailDirId);
        } else if (mailDirId == null) {
            mailDirId = "0";
            sqlWhere.append(" and mail.mail_dir_id = '").append(mailDirId).
                append("' and mail.user_code = ").append(userId);
            mailMgrForm.setMailDirId(mailDirId);
        }

        System.out.println("--- mail dir id is " + mailDirId);
        StringBuffer searchWhere = new StringBuffer("");
        if (sqlWhere.length() > 0) {
            searchWhere.append(" and ").append(" 1=1 ");
        } else {
            searchWhere.append(" and ").append(" 1=1 ");
        }
        setSearchWhere(request, searchWhere);
        String strSearchWhere = searchWhere.toString();
        String sortOrder = " order by serial_num desc ";
        sqlWhere.append(strSearchWhere).append(sortOrder);
    }

    private void clearQueryParm(MailMgrForm mailMgrForm) {
        mailMgrForm.setFindMailTitleQuery("");
        mailMgrForm.setFindNameQuery("");
        mailMgrForm.setMailTodayQuery("");
        mailMgrForm.setFindTimeQuery("");
        mailMgrForm.setUnergenceMailQuery("");
        mailMgrForm.setUnreadMailQuery("");
    }

    /**
     * 保存用户上传附件
     * @param request HttpServletRequest
     * @param mForm BaseForm
     * @throws DefaultException
     * @return List
     */
    private List saveAttchFiles(HttpServletRequest request,
                                BaseForm mForm
                                ) throws DefaultException {
        List attchList = null;
        MailMgrForm form = (MailMgrForm) mForm;
        attchList = initAttachList(form);
        String pathNameColumn = "FILE_PATH";
        String fileNameColumn = "ATTCH_NAME";
        UploadFile upload = new UploadFile();
        String moduleName = "mailbox";
        upload.setModule(moduleName);
        if (!form.getFileElements().equals("none") &&
            !form.getFileElements().equals("")) {
            StringTokenizer stkElementName = new StringTokenizer(form.
                getFileElements(), ","); //取得页面所有的附件属性名称
            System.out.println("--- stkElementName.size is " +
                               stkElementName.countTokens());
            while (stkElementName.hasMoreElements()) {
                MailAttchVO attchVO = new MailAttchVO();
                String elementName = stkElementName.nextToken();
                System.out.println("--- element name is " + elementName);
                upload.setElementName(elementName);
                upload.saveOneFile(request, mForm, attchVO, pathNameColumn,
                                   fileNameColumn); //将文件上传并给MailAttchVO的两个属性赋值
                System.out.println("--- upload file size is " +
                                   upload.getFileSize());
                if (upload.getFileSize() > 0) {
                    System.out.println("--- file path is " +
                                       attchVO.getFile_path());
                    System.out.println("--- attach name is " +
                                       attchVO.getAttch_name());
                    attchVO.setFileSize(upload.getFileSize());
                    attchList.add(attchVO);
                }
            }
        }
        return attchList; //将包含多个附件信息的attchList返回
    }

    /**
     * 初始化上传列表的方法
     * @param form MailMgrForm
     * @throws NumberFormatException
     * @return List
     */
    private List initAttachList(MailMgrForm form) throws NumberFormatException {
        List attchList;
        MailVO currentMailVo = form.getMailVo();
        int attachCount = (form.getMailVo().getAttachList().size());

        if (attachCount > 0) {
            attchList = currentMailVo.getAttachList();
            if (form.getDeleteFiles() != null && "".equals(form.getDeleteFiles()) == false) {
                //System.out.println("---- delete files : " + form.getDeleteFiles() + "-------------");
                String[] strDeletFileId = form.getDeleteFiles().split(",");
                for (int i = 0; i < attchList.size(); i++) {
                    MailAttchVO tempAttachVo = (MailAttchVO)attchList.get(i) ;
                    for(int j = 0 ; j < strDeletFileId.length ; j ++) {
                        if (strDeletFileId[j].equals(tempAttachVo.getSerial_num())) {
                            attchList.remove(i);
                            i--;
                            break;
                        }
                    }
                }
            }

        } else {
            attchList = new ArrayList();
        }
        return attchList;
    }

    /**
     * 设置邮件属性的方法
     * @param mailVO MailVO
     * @throws DefaultException
     */
    private void setMailExtraProperties(MailVO mailVO) throws DefaultException {
        if (mailVO.getReceive_address().length() > 0) {
            String recieverName = getRecieveAddressName(mailVO);
            mailVO.setReceive_address_name(recieverName);
        }
        if (mailVO.getCopy_send() != null &&
            mailVO.getCopy_send().equals("") == false) {
            String copySendName = getCopySendName(mailVO);
            //System.out.println("--- copySend name is " + copySendName);
            mailVO.setCopy_send_name(copySendName);
        }
    }

    /**
     * 设置邮件属性的方法
     * @param mailVO MailVO
     * @throws DefaultException
     */
    private void setMailViewExtraProperties(MailVO mailVO) throws
        DefaultException {
        if (mailVO.getReceive_address().length() > 0 ||
            mailVO.getCopy_send().length() > 0) {
            String recieveAddress = mailVO.getReceive_address() +
                (mailVO.getCopy_send().length() > 0 ? "," + mailVO.getCopy_send() :
                 "");
            mailVO.setReceive_address(recieveAddress);
            String recieverName = getRecieveAddressName(mailVO);
            mailVO.setReceive_address_name(recieverName);
        }
        if (mailVO.getCopy_send() != null &&
            mailVO.getCopy_send().equals("") == false) {
            String copySendName = getCopySendName(mailVO);
            //System.out.println("--- copySend name is " + copySendName);
            mailVO.setCopy_send_name(copySendName);
        }
    }

    private void removeAllMailInGarbage(String userId, String realPath,
                                        HttpServletRequest request) throws
        DefaultException {
        if (request.getParameter("removeAllMailFromGarbage") != null) {
            mailMgrDao.removeAllGarbageMails(userId, realPath);
        }
    }

    private void revertAllMailFromGarbage(String userId,
                                          HttpServletRequest request) throws
        DefaultException {
        if (request.getParameter("revertAllMailFromGarbage") != null) {
            mailMgrDao.revertAllMails(userId);
        }
    }

    private void revertMailFromGarbage(HttpServletRequest request,
                                       String[] selectedMail) throws
        DefaultException {
        if (request.getParameter("revertMailFromGarbage") != null) {
            mailMgrDao.revertMails(selectedMail);
        }
    }

    private void deleteMailToGarbage(HttpServletRequest request,
                                     String[] selectedMail) throws
        DefaultException {
        if (request.getParameter("moveMailToGarbage") != null ||
            request.getParameter("deleteMailId") != null) {
            System.out.println("--- moveMailToGarbage deleteMailId is " +
                               selectedMail[0]);
            mailMgrDao.deleteMails(selectedMail);
        }
    }

    private void moveMailToOtherDir(HttpServletRequest request,
                                    String[] selectedMail) throws
        DefaultException {
        if (request.getParameter("moveToDirId") != null) {
            String moveToDirId = request.getParameter("moveToDirId");
            mailMgrDao.moveMails(selectedMail, moveToDirId);
        }
    }

    /**
     * 设置列表页面查询条件的方法
     * @param request HttpServletRequest
     * @param searchWhere StringBuffer
     */
    private void setSearchWhere(HttpServletRequest request,
                                StringBuffer searchWhere) {
        if (request.getParameter("search") != null) {
            StringBuffer tempWhere = new StringBuffer();

            if (request.getParameter("findNameQuery") != null &&
                request.getParameter("findNameQuery").length() > 0) {
                System.out.println("--- findNameQuery : " +
                                   request.getParameter("findNameQuery"));
                String name = request.getParameter("findNameQuery");
                name = StringUtils.replaceText(name);
                String nameWhere = " POST_USERNAME like '%" + name + "%' ";
                tempWhere.append(" and ").append(nameWhere);
            }
            if (request.getParameter("findMailTitleQuery") != null &&
                request.getParameter("findMailTitleQuery").length() > 0) {
                System.out.println("--- findMailTitleQuery : " +
                                   request.getParameter("findMailTitleQuery"));
                String title = request.getParameter("findMailTitleQuery");
                title = StringUtils.replaceText(title);
                String titleWhere = " TITLE like '%" + title + "%' ";
                tempWhere.append(" and ").append(titleWhere);
            }
            if (request.getParameter("findTimeQuery") != null &&
                request.getParameter("findTimeQuery").length() > 0) {
                System.out.println("--- findTimeQuery : " +
                                   request.getParameter("findTimeQuery"));
                String date = request.getParameter("findTimeQuery");
                String dateWhere = " SEND_DATE between '" + date + beginTime +
                    "' and '" + date + endTime + "'";
                tempWhere.append(" and (").append(dateWhere).append(")");
            }
            /*if(request.getParameter("mailTodayQuery") != null && "".equals(request.getParameter("mailTodayQuery")) == false) {
                System.out.println("--- mailTodayQuery : " + request.getParameter("mailTodayQuery"));
                String today = request.getParameter("mailTodayQuery");
                String dateWhere = " SEND_DATE = '" + today + "' ";
                tempWhere.append(" and ").append(dateWhere);
                         }*/
            if (request.getParameter("unreadMailQuery") != null &&
                request.getParameter("unreadMailQuery").length() > 0) {
                System.out.println("--- unreadMailQuery : " +
                                   request.getParameter("unreadMailQuery"));
                String unreadMailWhere = " read_flag <> '1' ";
                tempWhere.append(" and ").append(unreadMailWhere);
            }
            if (request.getParameter("unergenceMailQuery") != null &&
                request.getParameter("unergenceMailQuery").length() > 0) {
                System.out.println("--- unergenceMailQuery : " +
                                   request.getParameter("unergenceMailQuery"));
                String unergenceMailWhere = " PRIORITY = '1' ";
                tempWhere.append(" and ").append(unergenceMailWhere);
            }
            if (request.getParameter("exterioMailQuery") != null &&
                request.getParameter("exterioMailQuery").length() > 0) {
                System.out.println("--- exterioMailQuery : " +
                                   request.getParameter("exterioMailQuery"));
                String unergenceMailWhere = " mail_type = '2' ";
                tempWhere.append(" and ").append(unergenceMailWhere);
            }

            if (tempWhere.length() > 0) {
                searchWhere.append(tempWhere);
            }
            System.out.println("--- search where is " + searchWhere);
        }
    }

    private MailDirectoryVO getUserCurrentMailDirInfo(MailMgrForm mailMgrForm,
        String userId) throws DefaultException {
        MailDirectoryVO mailDirVo;
        this.setMailDirectoryDao();
        mailDirVo = this.mailDirectoryDao.queryMailDirByUserAndDirId(userId,
            mailMgrForm.getMailDirId());
        return mailDirVo;
    }

    private String getCopySendName(MailVO mailVO) throws DefaultException {
        System.out.println("--- copySend id is " + mailVO.getCopy_send());
        String copySendName = "";
        if (mailVO.getCopy_send().indexOf("@") > -1) {
            setCardCaseDao();
            copySendName = cardcaseDAO.getNameByEmail(mailVO.getUser_code(),
                mailVO.getCopy_send());
        } else if ("".equals(mailVO.getCopy_send()) == false) {
            copySendName = systemMngUtil.getUserNamesByUserdIDs(
                mailVO.getCopy_send(),serperator);
        }
        return copySendName;
    }

    private String getRecieveAddressName(MailVO mailVO) throws DefaultException {
        String recieverName = "";
        System.out.println("--- mail type is " + mailVO.getMail_type());
        if (mailVO.getReceive_address().indexOf("@") > -1) {
            setCardCaseDao();
            recieverName = cardcaseDAO.getNameByEmail(mailVO.getUser_code(),
                mailVO.getReceive_address());
        } else if ("".equals(mailVO.getReceive_address()) == false) {
            recieverName = systemMngUtil.getUserNamesByUserdIDs(
                mailVO.getReceive_address(),serperator);
        }
        return recieverName;
    }

    private void setCardCaseDao() {
        cardcaseDAO = ToolsFactory.getInstance().createCardcaseDao(super.dbData);
    }

    private MailCapacityVO getUserMailCapacity(String userId) throws
        DefaultException {
        this.setMailCapacityDao();
        MailCapacityVO capacityVo;
        List userCapacityList = this.mailCapacityDao.getMailCapacityByUser(
            userId);
        capacityVo = (MailCapacityVO) userCapacityList.get(0);
        return capacityVo;
    }

    /**
     * 设置翻页每页记录个数
     * @param cfg GoaActionConfig
     */
    public void setPageNumber(GoaActionConfig cfg) {
        MailMgrForm mailMgrForm = (MailMgrForm) cfg.getBaseForm();
        int pageNum = -1;
        try {
            pageNum = setPageNumberByUserId(mailMgrForm);
        } catch (DefaultException ex) {
        }
        cfg.getPageHelper().setPageCount(pageNum);

    }

    private String getRealPath(HttpServletRequest request) {
        return request.getRealPath("/");
    }

    private void setMailCapacityDao() {
        this.mailCapacityDao = MailMgrFactory.getInstance().creatMailCapacity(super.
            dbData);
    }

    private void setMailDirectoryDao() {
        this.mailDirectoryDao = MailMgrFactory.getInstance().creatMailDirectory(super.
            dbData);
    }

	//zxy add 2004-12-03
	//以下两方法是为了解决打开两个邮件窗口时，附件信息会错乱的问题
	//保存邮件的附件信息；即将mailvo里包含MailAttchVO的List对象转化为附件的字符信息；
	private void backupAttachInfo(GoaActionConfig cfg){
		MailMgrForm form = ( MailMgrForm )cfg.getBaseForm();
		MailVO vo = form.getMailVo();
		vo.setAttachInfo( MailAttachUtil.attachListToString(vo.getAttachList()));
	}
	//还原邮件的附件信息；即将mailvo里的附件的字符信息转化为包含MailAttchVO的List对象；
	private void restoreAttachInfo(GoaActionConfig cfg){
		MailMgrForm form = ( MailMgrForm )cfg.getBaseForm();
		MailVO vo = form.getMailVo();
		vo.setAttachList( MailAttachUtil.stringToAttachList(vo.getAttachInfo()) );
	}
}
