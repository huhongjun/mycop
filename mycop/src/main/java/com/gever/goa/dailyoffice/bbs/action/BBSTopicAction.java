package com.gever.goa.dailyoffice.bbs.action;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.bbs.dao.BBSFactory;
import com.gever.goa.dailyoffice.bbs.dao.BBSTopicDAO;
import com.gever.goa.dailyoffice.bbs.dao.BBSTopicListDAO;
import com.gever.goa.dailyoffice.bbs.dao.ViewTopicDAO;
import com.gever.goa.dailyoffice.bbs.vo.TopicListVO;
import com.gever.goa.dailyoffice.bbs.vo.TopicVO;
import com.gever.goa.dailyoffice.bbs.vo.UserVO;
import com.gever.goa.dailyoffice.bbs.vo.ViewTopicVO;
import com.gever.goa.dailyoffice.bbs.vo.ViewTopiclistVO;
import com.gever.goa.dailyoffice.message.dao.impl.MessageDaoImpl;
import com.gever.goa.web.util.UploadFile;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.struts.form.BaseForm;
import com.gever.util.DateTimeUtils;
import com.gever.vo.VOInterface;

/**
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class BBSTopicAction extends BaseAction {
    //private BBSTopicDAO topicDAO;
    //private BBSTopicListDAO topiclistDAO;
    public BBSTopicAction() {
    }

    protected void initData(GoaActionConfig cfg) throws DefaultException,
            Exception {

        BBSTopicForm myForm = (BBSTopicForm) cfg.getBaseForm(); //�õ�form����
        if (myForm.getUserVO() == null) {
            myForm.setUserVO( (UserVO) cfg.getSession().getAttribute("bbsuser"));
        }
        if(myForm.getSboardID().equals("")){
            myForm.setSboardID((String)cfg.getSession().getAttribute("sboardID"));
        }
//        log.showLog("****cfg.getSession().getAttribute(sboardID)*********" +
//                    cfg.getSession().getAttribute("sboardID") +
//                    "******************");
//        log.showLog("****myForm.getBbsPageType()*********" +
//                    myForm.getBbsPageType() + "******************");
//        String currentPath = (String) cfg.getSession().getAttribute(
//               "currentaction");
//       log.showLog("cfg.getSession().getAttribute(currentaction)*******" +
//                   currentPath);
//       log.showLog("cfg.getMapping().getInput()*******" +
//                   cfg.getMapping().getInput());
//       cfg.getMapping().getInputForward().getPath();
//       log.showLog("cfg.getMapping().getInputForward().getPath()*******" +
//                   cfg.getMapping().getInputForward().getPath());

    }

    /**
     * ���ظ��Ǹ��෽�����������ø��෽��
     * ��Ҫ�������ڵ��ø��෽��ǰȷ���б�ҳ����ʾ���Ǻ���VO�������Ӧ��DAO��
     * @param cfg GoaActionConfig
     * @param pageType String
     * @throws DefaultException
     * @throws Exception
     * @return String
     */
    protected String doPage(GoaActionConfig cfg, String pageType) throws
            DefaultException,
            Exception {

		this.dispatchViewAction(cfg);

        String forword = super.doPage(cfg,pageType);

        return forword;
    }

	//ȷ��ʹ������DAO��VO������
	private void dispatchViewAction(GoaActionConfig cfg)
			throws DefaultException	{
		BaseForm form = cfg.getBaseForm();
		BaseDAO dao = null;
		VOInterface vo = null;
		org.apache.struts.action.ActionMapping mapping = cfg.getMapping();
		String path = mapping.getPath();
		log.showLog("path------------------" + path);
		if(path.indexOf("topiclist") != -1)	{
			dao = (BaseDAO)BBSFactory.getInstance().createViewTopiclistDAO(super.dbData);
			vo = new ViewTopiclistVO();
		} else	if(path.indexOf("Topic") != -1)	{
			dao = (BaseDAO)BBSFactory.getInstance().createViewTopicDAO(super.dbData);
			vo = new ViewTopicVO();
		} else{
			throw new DefaultException("�޷�ȷ��Ӧ��������DAO��VO��������");
		}

		dao.setUserID(form.getUserId());
		cfg.setBaseDao(dao);
		form.setVo(vo);
	}

    protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
            DefaultException, Exception {
        String forword = null;
        //�ж��Ƿ񱣴�BBS�û���Ϣ�����û�б��������µ�½
        BBSTopicForm form = (BBSTopicForm) cfg.getBaseForm();
        if (form.getUserVO() == null) {
            return "logon";
        }
        String page = ( (BBSTopicForm) cfg.getBaseForm()).getBbsPageType();
        if ("topic".equalsIgnoreCase(page)) {
            forword = this.doInsertTopic(cfg, isBack);
        } else {
            forword = this.doInsertTopicList(cfg, isBack);
        }
        return forword;
    }

    protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
            Exception {
        String forword = null;
        String page = ( (BBSTopicForm) cfg.getBaseForm()).getBbsPageType();
        if ("topic".equalsIgnoreCase(page)) {
            forword = this.doUpdateTopic(cfg, isBack);
        } else {
            forword = this.doUpdateTopic(cfg, isBack);
        }

        return forword;

    }

    /**
     * �༭���⣨������
     * @param cfg GoaActionConfig
     * @throws Exception
     * @return String
     */
    public String toEditTopic(GoaActionConfig cfg) throws Exception {
        //String forword = super.toEdit(cfg);
        BBSTopicForm form = (BBSTopicForm) cfg.getBaseForm();

        form.setTopicVO(new TopicVO());
        form.setTopiclistVO(new TopicListVO());
        return super.FORWORD_EDIT_PAGE;
        // return "EditTopic"; //�༭���⣨��������jspҳ��(forword��)
    }

    /**
     * �г��������⣨���з�����
     * @param cfg GoaActionConfig
     * @throws Exception
     * @return String
     */
    public String toListTopic(GoaActionConfig cfg) throws Exception {
        // cfg.setBaseDao((BaseDAO)topDAO);
        //ViewTopicDAO viewtopicDAO = BBSFactory.getInstance().createViewTopicDAO(super.dbData);
        // cfg.setBaseDao( (BaseDAO) viewtopicDAO);

        BBSTopicForm form = (BBSTopicForm) cfg.getBaseForm();
        log.showLog("******form.getSboardID()*********" + form.getSboardID() +
                    "*********");
        String sqlwhere = " and sboard_serial='" + form.getSboardID() + "'";
        //��������˳��
        //�ֱ�Ϊϵͳ���棬����������ͨ����Ȼ���ٰ�ʱ��������������ǰ�棻
        sqlwhere+=" order by appear_date desc ";
        form.setSqlWhere(sqlwhere);
        //form.setVo(new ViewTopicVO());
        String forword = super.toList(cfg);
        return forword; //�г��������⣨���з�������JSPҳ��(forword��)
    }

    /**
     * ����ظ�����ҳ�棬���Իظ���ͬʱ��Ҫ�г���ǰ���еĻظ����г�������
     * ��ҳ��VO��Ӧ����ViewTopicListVO��
     * edit+list
     * @param cfg GoaActionConfig
     * @throws Exception
     * @return String
     */
    public String toEditTopicList(GoaActionConfig cfg) throws Exception {

        BBSTopicForm form = (BBSTopicForm) cfg.getBaseForm();
        //�ڴ�Ӧload��viewtopicvo��������������

        ViewTopicVO viewtopicvo = new ViewTopicVO();
        viewtopicvo.setSerial_num(form.getFid());
        ViewTopicDAO viewtopicDAO = BBSFactory.getInstance().createViewTopicDAO(super.
                dbData);
        ( (BaseDAO) viewtopicDAO).setUserID(form.getUserId());
        form.setViewtopicVO( (ViewTopicVO) viewtopicDAO.queryByPK(viewtopicvo));

        TopicVO topicVO = form.getTopicVO();
        if (topicVO == null) {
            topicVO = new TopicVO();
        }
        topicVO.setSerial_num(form.getFid());
        BBSTopicDAO topicDAO =
                BBSFactory.getInstance().createTopicDAO(super.dbData);
        ( (BaseDAO) topicDAO).setUserID(form.getUserId());
        log.showLog("================> topicVO hit time: " + topicVO.getHit_times());
        topicDAO.addHitTimes(topicVO);

        log.showLog("******** and  topic_num= " + form.getFid() + ")*********");
        String sqlwhere = " and topic_num = '" + form.getFid() + "'";
        form.setSqlWhere(sqlwhere);

        String forword = super.toList(cfg);

        form.setTopiclistVO(new TopicListVO());

        return forword; //�г����лظ������л�������JSPҳ��
    }

    public String doDeleteTopic(GoaActionConfig cfg) throws Exception {
        throw new Exception("�÷�����δʵ��");
    }

    public String doDeleteTopicList(GoaActionConfig cfg) throws Exception {
        throw new Exception("�÷�����δʵ��");
    }

    public String doUpdateTopic(GoaActionConfig cfg) throws Exception {
        throw new Exception("�÷�����δʵ��");
    }

    public String doUpdateTopicList(GoaActionConfig cfg) throws Exception {
        throw new Exception("�÷�����δʵ��");
    }

    public String doSearchByTopic(GoaActionConfig cfg) throws Exception {
        throw new Exception("�÷�����δʵ��");
    }

    public String doInsertTopic(GoaActionConfig cfg, boolean isBack) throws
            Exception {
        return this.doInsertTopic(cfg);
    }

    public String doInsertTopic(GoaActionConfig cfg) throws Exception {
        BBSTopicForm form = (BBSTopicForm) cfg.getBaseForm();

        TopicVO topicVO = (TopicVO) form.getTopicVO();
        this.initVO(topicVO, cfg);

        BBSTopicDAO topicDAO = BBSFactory.getInstance().createTopicDAO(super.
                dbData);
        ( (BaseDAO) topicDAO).setUserID(form.getUserId());
        topicDAO.insert(topicVO);

        TopicListVO topiclistVO = (TopicListVO) form.getTopiclistVO();
        topiclistVO.setTopic_num(topicVO.getSerial_num());
        this.initVO(topiclistVO, cfg);
        //�������ϴ�
        UploadFile uf = new UploadFile();
        uf.setModule("bbs");
        uf.saveOneFile(cfg.getRequest(), form,
                       topiclistVO, "file_path", "file_name");

        BBSTopicListDAO topiclistDAO = BBSFactory.getInstance().
                createTopicLisDAO(super.dbData);
        ( (BaseDAO) topiclistDAO).setUserID(form.getUserId());
        topiclistDAO.insert(topiclistVO);
        //form.setTopicVO(new TopicVO());//??
        form.setTopiclistVO(new TopicListVO());
        return super.FORWORD_LIST_PAGE;

    }

    public String doInsertTopicList(GoaActionConfig cfg, boolean isBack) throws
            Exception {
        return this.doInsertTopicList(cfg);
    }

    public String doInsertTopicList(GoaActionConfig cfg) throws Exception {
        BBSTopicForm form = (BBSTopicForm) cfg.getBaseForm();

        //��topiclistVO�����ݱ��������ݿ⣻
        TopicListVO topiclistVO = form.getTopiclistVO();
        topiclistVO.setTopic_num(form.getViewtopicVO().getSerial_num());
        this.initVO(topiclistVO, cfg);
        //�������ϴ�
        UploadFile uf = new UploadFile();
        uf.setModule("bbs");
        uf.saveOneFile(cfg.getRequest(), form,
                       topiclistVO, "file_path", "file_name");

        BBSTopicListDAO topiclistDAO =
                BBSFactory.getInstance().createTopicLisDAO(super.dbData);
        ( (BaseDAO) topiclistDAO).setUserID(form.getUserId());
        topiclistDAO.insert(topiclistVO);

        TopicVO topicVO = form.getTopicVO();
        if (topicVO == null) {
            topicVO = new TopicVO();
        }
        topicVO.setSerial_num(form.getFid());
        BBSTopicDAO topicDAO =
                BBSFactory.getInstance().createTopicDAO(super.dbData);
        ( (BaseDAO) topicDAO).setUserID(form.getUserId());
        topicVO = (TopicVO) topicDAO.queryByPK(topicVO);

        //�������ѱ�־Ϊ��1������ɵĻ����û����Ͷ���Ϣ
        if ("1".equals(topiclistVO.getAwoke_flag())) {
            String user_code = form.getUserId();
            String send_time = DateTimeUtils.getCurrentDateTime();
            String content = "���ã������������۵���" + form.getViewtopicVO().getNickname() +
                    "����Ļ��⡰" + form.getViewtopicVO().getTopic() +
                    "�����������µ����ԡ�";
            String receiver = topicVO.getTopic_user();
            String web_url = "";
            MessageDaoImpl msgDao = new MessageDaoImpl(super.dbData);
            /*MessageDao msgDao=
             MessageFactory.getInstance().createMessageDao(super.dbData);*/
            msgDao.sendMessage(user_code, send_time, content, receiver, web_url);
        }

        //���»����û������Լ����뻰���û��У�
        if (addTopicUser(topicVO, form.getUserVO().getUser_code())) {
            topicDAO.updateTopicUser(topicVO);
        }
        return "list"; //ִ�в�ѯ�������г����Ի��������ػ���ҳ�棻
    }

    public String doUpdateTopic(GoaActionConfig cfg, boolean isBack) throws
            Exception {
        throw new Exception("�÷�����δʵ��");
    }

    public String doUpdateTopicList(GoaActionConfig cfg, boolean isBack) throws
            Exception {
        throw new Exception("�÷�����δʵ��");
    }

    /**
     * ��VO��ĳЩ���Ը�ֵ����������
     * @param vo VOInterface
     * @param cfg GoaActionConfig
     */
    private void initVO(VOInterface vo, GoaActionConfig cfg) {
        BBSTopicForm form = (BBSTopicForm) cfg.getBaseForm();
        UserVO myBBSinfo = form.getUserVO();
        String currenttime = DateTimeUtils.getCurrentDateTime();
        if (vo.getClass() == TopicListVO.class) {
            TopicListVO listvo = (TopicListVO) vo;

            listvo.setBbs_user_code(myBBSinfo.getBbs_user_code()); //
            //listvo.setTopic_num(form.getViewtopicVO().getSerial_num()); //
            listvo.setIp_address(cfg.getRequest().getRemoteAddr()); //
            listvo.setReply_date(currenttime);
            listvo.setIs_show("0");

        } else if (vo.getClass() == TopicVO.class) {
            TopicVO topicvo = (TopicVO) vo;

            topicvo.setAppear_date(currenttime);
            topicvo.setHit_times("1");
            topicvo.setIsblock("0");
            topicvo.setBbs_user_code(myBBSinfo.getBbs_user_code()); //�û�BBS�š�����
            topicvo.setTopic_order("0");
            //topicvo.setTopic_type("0"); //ϵͳ���治�ܻظ���2�����棻0���ǹ��棻1������
            topicvo.setSboard_serial(form.getSboardID());
            topicvo.setTopic_user("," + myBBSinfo.getUser_code() + ",");
            log.showLog("********topicUser********" + topicvo.getTopic_user());
            topicvo.setIs_show("0");
        }
    }

    private boolean addTopicUser(TopicVO topicVO, String newUserName) {
        String topicUser = topicVO.getTopic_user();
        log.showLog("********topicUser********" + topicUser);
        String userName = newUserName + ",";
        //�ж��Ƿ���ڸ��û���������ڣ�������ӣ��������
        //topic_user�б���ĸ��û��ԡ�������ʼ�����ԡ���������������
        if (topicUser.indexOf("," + userName) == -1) {
            topicUser += userName;
        } else {
            return false;
        }
        topicVO.setTopic_user(topicUser);
        log.showLog("********topicUser********" + topicUser);
        return true;
    }

    /**
     * ���Ǹ��෽���������÷�Ҷ��
     * @param cfg GoaActionConfig
     */
    public void setPageNumber(GoaActionConfig cfg) {
        cfg.getPageHelper().setPageCount(20);

    }

}
