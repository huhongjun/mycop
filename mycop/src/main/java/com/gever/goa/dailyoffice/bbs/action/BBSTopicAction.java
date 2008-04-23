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
 * <p>Company: 天行软件</p>
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

        BBSTopicForm myForm = (BBSTopicForm) cfg.getBaseForm(); //得到form变量
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
     * 重载覆盖父类方法。。并调用父类方法
     * 主要作用在于调用父类方法前确定列表页面显示的是何种VO，及其对应的DAO。
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

	//确定使用哪种DAO，VO。。。
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
			throw new DefaultException("无法确定应该用哪种DAO，VO。。！！");
		}

		dao.setUserID(form.getUserId());
		cfg.setBaseDao(dao);
		form.setVo(vo);
	}

    protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
            DefaultException, Exception {
        String forword = null;
        //判断是否保存BBS用户信息，如果没有保存则重新登陆
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
     * 编辑主题（发贴）
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
        // return "EditTopic"; //编辑主题（发贴）的jsp页面(forword名)
    }

    /**
     * 列出所有主题（所有发贴）
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
        //设置排列顺序
        //分别为系统公告，精华贴，普通。。然后再按时间排序，新增的再前面；
        sqlwhere+=" order by appear_date desc ";
        form.setSqlWhere(sqlwhere);
        //form.setVo(new ViewTopicVO());
        String forword = super.toList(cfg);
        return forword; //列出所有主题（所有发贴）的JSP页面(forword名)
    }

    /**
     * 进入回复主题页面，可以回复，同时需要列出以前所有的回复（列出回帖）
     * 分页的VO里应保存ViewTopicListVO；
     * edit+list
     * @param cfg GoaActionConfig
     * @throws Exception
     * @return String
     */
    public String toEditTopicList(GoaActionConfig cfg) throws Exception {

        BBSTopicForm form = (BBSTopicForm) cfg.getBaseForm();
        //在此应load出viewtopicvo保存起来。。。

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

        return forword; //列出所有回复（所有回贴）的JSP页面
    }

    public String doDeleteTopic(GoaActionConfig cfg) throws Exception {
        throw new Exception("该方法尚未实现");
    }

    public String doDeleteTopicList(GoaActionConfig cfg) throws Exception {
        throw new Exception("该方法尚未实现");
    }

    public String doUpdateTopic(GoaActionConfig cfg) throws Exception {
        throw new Exception("该方法尚未实现");
    }

    public String doUpdateTopicList(GoaActionConfig cfg) throws Exception {
        throw new Exception("该方法尚未实现");
    }

    public String doSearchByTopic(GoaActionConfig cfg) throws Exception {
        throw new Exception("该方法尚未实现");
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
        //处理附件上传
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

        //将topiclistVO的数据保存入数据库；
        TopicListVO topiclistVO = form.getTopiclistVO();
        topiclistVO.setTopic_num(form.getViewtopicVO().getSerial_num());
        this.initVO(topiclistVO, cfg);
        //处理附件上传
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

        //假如提醒标志为“1”，向旧的话题用户发送短消息
        if ("1".equals(topiclistVO.getAwoke_flag())) {
            String user_code = form.getUserId();
            String send_time = DateTimeUtils.getCurrentDateTime();
            String content = "您好！您所参与讨论的由" + form.getViewtopicVO().getNickname() +
                    "发起的话题“" + form.getViewtopicVO().getTopic() +
                    "”，现有我新的留言。";
            String receiver = topicVO.getTopic_user();
            String web_url = "";
            MessageDaoImpl msgDao = new MessageDaoImpl(super.dbData);
            /*MessageDao msgDao=
             MessageFactory.getInstance().createMessageDao(super.dbData);*/
            msgDao.sendMessage(user_code, send_time, content, receiver, web_url);
        }

        //更新话题用户；将自己加入话题用户中；
        if (addTopicUser(topicVO, form.getUserVO().getUser_code())) {
            topicDAO.updateTopicUser(topicVO);
        }
        return "list"; //执行查询动作，列出所以回帖，返回回帖页面；
    }

    public String doUpdateTopic(GoaActionConfig cfg, boolean isBack) throws
            Exception {
        throw new Exception("该方法尚未实现");
    }

    public String doUpdateTopicList(GoaActionConfig cfg, boolean isBack) throws
            Exception {
        throw new Exception("该方法尚未实现");
    }

    /**
     * 给VO的某些属性付值。。。。。
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
            topicvo.setBbs_user_code(myBBSinfo.getBbs_user_code()); //用户BBS号。。。
            topicvo.setTopic_order("0");
            //topicvo.setTopic_type("0"); //系统公告不能回复：2＝公告；0＝非公告；1＝精华
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
        //判断是否存在该用户，如果存在，不用添加，否则添加
        //topic_user中保存的各用户以“，”开始，且以“，”隔开。。。
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
     * 覆盖父类方法。。设置分叶数
     * @param cfg GoaActionConfig
     */
    public void setPageNumber(GoaActionConfig cfg) {
        cfg.getPageHelper().setPageCount(20);

    }

}
