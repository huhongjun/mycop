package com.gever.goa.dailyoffice.mailmgr.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.dao.MailDirectoryDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrFactory;
import com.gever.goa.dailyoffice.mailmgr.dao.Pop3ConfigDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.Pop3MailMgrDAO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailDirectoryVO;
import com.gever.goa.dailyoffice.mailmgr.vo.Pop3ConfigVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.struts.form.BaseForm;
import com.gever.struts.pager.PageControl;
import com.gever.util.SumUtils;
import com.gever.vo.VOInterface;

/**
 *
 * <p>Title: </p>
 * <p>Description: Pop3 邮件 Action类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class Pop3ConfigAction extends BaseAction {
    public Pop3ConfigAction() {
    }

    Pop3ConfigDAO pop3ConfigDao = null;
    MailDirectoryDAO mailDirectoryDao = MailMgrFactory.getInstance().
        creatMailDirectory(super.
                           dbData);
    Pop3MailMgrDAO pop3MailMgrDao = null;

    /**
     * 初始化页面数据
     * @param actionform 当前的vo对象
     * @return 当前所用的vo对象
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        Pop3ConfigForm myForm = (Pop3ConfigForm) cfg.getBaseForm(); //得到form变量
        pop3ConfigDao = MailMgrFactory.getInstance().createPop3Config(super.
            dbData); //得到相对应dao的实例
        cfg.setBaseDao( (BaseDAO) pop3ConfigDao);
        //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao
        //容错处理,防止vo对象为null
        if (myForm.getVo() == null) {
            myForm.setVo(new Pop3ConfigVO());
        }
    }

    /**
     * 到清单页面
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */

    protected String toList(GoaActionConfig cfg) throws DefaultException,
        Exception {
        StringBuffer sb = new StringBuffer();
        sb.append(" and POP3MAILSETUP.user_code=");
        sb.append(cfg.getBaseForm().getUserId());
        cfg.getBaseForm().setSqlWhere(sb.toString());
      //  System.out.println("The sqlWher is:"+cfg.getBaseForm().getSqlWhere());
       // super.toList(cfg);
        doPage(cfg, "");
        //VOInterface vo=cfg.getBaseForm().getVo();
        //VOInterface mail_dir_vo=mailDirectoryDao.queryMailDirByUserAndDirId(cfg.getBaseForm().getUserId(),vo.getValue("incept_mail_dir"));

       // System.out.println("the value of Incept_mail_dir is:"+vo.getValue("incept_mail_dir"));
        cfg.getBaseForm().setActionType(""); // 恢复默认动作类型((add,modify)
        return this.FORWORD_LIST_PAGE;
    }

    /**
     * 编辑pop3config
     * @param cfg
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */
    protected String toEdit(GoaActionConfig cfg) throws DefaultException,
        Exception {
        /*初始化邮件夹下拉列表*/
        MailDirectoryVO mailDirectoryVO = new MailDirectoryVO();
        List listDirectory = mailDirectoryDao.queryDirectoryByUserId(cfg.
            getBaseForm().getUserId());
        HttpSession session = cfg.getSession();
        session.setAttribute("dir_name", listDirectory);
        super.toEdit(cfg);
        /* 当新增的时候 初始化pop3_port 和smtp_port分别为110，25*/
        HttpServletRequest request = cfg.getRequest();
        String actionType = SumUtils.nullToString(request.getParameter(
            "actionType"));
       //System.out.println("------actionType:--------"+actionType);
        VOInterface pop3ConfigVO = null;
        if (!this.MODIFY_ACTION.equals(actionType)) {
            pop3ConfigVO= new Pop3ConfigVO();
            pop3ConfigVO.setValue("pop3_port", "110");
            pop3ConfigVO.setValue("smtp_port", "25");
            cfg.getBaseForm().setActionType(this.ADD_ACTION);
         //   System.out.println("---------step1-------");
        }
        else {
           // System.out.println("---------step2-------");
            //将pop3_address smtp_server 分别分成address 和port
            pop3ConfigVO=cfg.getBaseForm().getVo();
            String pop3_address = pop3ConfigVO.getValue("pop3_address");
            String smtp_server = pop3ConfigVO.getValue("smtp_server");
            if (pop3_address.indexOf(":") > -1) {
                pop3ConfigVO.setValue("pop3_address",
                                      pop3_address.substring(0,
                    pop3_address.indexOf(":")));
                pop3ConfigVO.setValue("pop3_port",
                                      pop3_address.substring(pop3_address.
                    indexOf(":") + 1));
            }
            else {
                pop3ConfigVO.setValue("pop3_address", pop3_address);
                pop3ConfigVO.setValue("pop3_port", "110");
            }
            if (smtp_server.indexOf(":") > -1) {
                pop3ConfigVO.setValue("smtp_server",
                                      smtp_server.substring(0,
                    smtp_server.indexOf(":")));
                pop3ConfigVO.setValue("smtp_port",
                                      smtp_server.substring(
                    smtp_server.indexOf(":") + 1));
            }
            else {
                pop3ConfigVO.setValue("smtp_server", "");
                pop3ConfigVO.setValue("smtp_port", "25");
            }
            //设置use_flag
            String use_flag = pop3ConfigVO.getValue("use_flag");
            if (use_flag.trim().equals("正常")) {
                pop3ConfigVO.setValue("use_flag", "0");
            }
            else if (use_flag.trim().equals("停用")) {
                pop3ConfigVO.setValue("use_flag", "1");
            }
            else if (use_flag.trim().equals("默认帐号")) {
                pop3ConfigVO.setValue("use_flag", "2");
            }
            //初始化smtp_auth
            String smtp_auth = pop3ConfigVO.getValue("smtp_auth");
            if (smtp_auth.trim().equals("0")) {
                pop3ConfigVO.setValue("smtp_name", "");
                pop3ConfigVO.setValue("smtp_pwd", "");
            }
            cfg.getBaseForm().setActionType(this.MODIFY_ACTION);
        }
        cfg.getBaseForm().setVo(pop3ConfigVO);
        return this.FORWORD_EDIT_PAGE;
    }

    /**
     * 新增pop3config信息
     * @param cfg
     * @param isBack
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */

    protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
        DefaultException,
        Exception {
        BaseForm bForm = cfg.getBaseForm();
        VOInterface vo = this.initPop3Config(cfg);
        cfg.getBaseDao().insert(vo);
        Pop3ConfigVO pop3VO = new Pop3ConfigVO();
        bForm.setVo( (VOInterface) pop3VO);
        if (isBack == true)
            return this.FORWORD_LIST_PAGE;
        else
            return this.toEdit(cfg);
    }

    /**
     * 查看pop3Config信息
     * @param cfg
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */

    protected String toView(GoaActionConfig cfg) throws DefaultException,
        Exception {
        super.toView(cfg);
        VOInterface pop3ConfigVO = cfg.getBaseForm().getVo();
        String del_flag = pop3ConfigVO.getValue("del_flag");
        String smtp_auth = pop3ConfigVO.getValue("smtp_auth");
        String use_flag = pop3ConfigVO.getValue("use_flag");
        if (del_flag.trim().equals("0")) {
            pop3ConfigVO.setValue("del_flag", "是");
        }
        else if (del_flag.trim().equals("1")) {
            pop3ConfigVO.setValue("del_flag", "否");
        }
        if (smtp_auth.trim().equals("0")) {
            pop3ConfigVO.setValue("smtp_auth", "不需要验证");
        }
        else if (smtp_auth.trim().equals("1")) {
            pop3ConfigVO.setValue("smtp_auth", "需要验证");
        }
        if (use_flag.trim().equals("0")) {
            pop3ConfigVO.setValue("use_flag", "正常");
        }
        else if (use_flag.trim().equals("1")) {
            pop3ConfigVO.setValue("use_flag", "停用");
        }
        else if (use_flag.trim().equals("2")) {
            pop3ConfigVO.setValue("use_flag", "默认帐号");
        }
        String pwd=pop3ConfigVO.getValue("pop3_pwd");
        String str="";
        for (int i = 0; i < pwd.length(); i++) {
            str+="*";
        }
        pop3ConfigVO.setValue("pop3_pwd",str);
        String incept_mail_dir=pop3ConfigVO.getValue("incept_mail_dir");
     //   System.out.println("incept_mail_dir is :----->"+incept_mail_dir);
        VOInterface vo=mailDirectoryDao.queryMailDirByUserAndDirId(cfg.getBaseForm().getUserId(),incept_mail_dir);
        pop3ConfigVO.setValue("incept_mail_dir",vo.getValue("mail_dir_name"));
        cfg.getBaseForm().setVo(pop3ConfigVO);
        return this.FORWORD_VIEW_PAGE;
    }

    protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
        DefaultException,
        Exception {
        VOInterface vo = this.initPop3Config(cfg);
        BaseForm bForm = cfg.getBaseForm();
        cfg.getBaseForm().setVo(vo);
        super.doUpdate(cfg, isBack);
        if(isBack)
            return this.FORWORD_LIST_PAGE;
        else{
            cfg.getBaseForm().setActionType("");
           // System.out.println("----this.toEdit(cfg)------");
            return this.toEdit(cfg);
        }
    }

    /**
     * 初始化pop3ConfigVO
     * @param cfg
     * @return
     */
    private VOInterface initPop3Config(GoaActionConfig cfg) {
        VOInterface vo = cfg.getBaseForm().getVo();
        BaseForm bForm = cfg.getBaseForm();
        String userID = bForm.getUserId(); //为了便于测试，现令useId 为8001
        //String userID = "8001";
        vo.setValue("user_code", userID);
        //cfg.getBaseDao().setUserID(userID);
        /* pop3_Address = 地址+端口*/
        String pop3_Address = vo.getValue("pop3_address") + ":" +
            vo.getValue("pop3_port");
        //System.out.println("pop3_port is:" + vo.getValue("pop3_port"));
        vo.setValue("pop3_address", pop3_Address);
        vo.setValue("auto_flag", "0");
        /* smtp_server = 地址+端口*/
        String smtp_Server = vo.getValue("smtp_server") + ":" +
            vo.getValue("smtp_port");
        //System.out.println("smtp_port is:" + vo.getValue("smtp_port"));
        vo.setValue("smtp_server", smtp_Server);
        return vo;

    }

    public String toInceptPop3List(GoaActionConfig cfg) throws DefaultException,
        Exception {
        System.out.println("--- in toInceptPop3List ---");
        Pop3ConfigForm pop3configForm = (Pop3ConfigForm) cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();
        setPop3MailMgrDao();
        if (request.getParameter("pop3ConfigeSelect") != null) {
            System.out.println("--- begein action ---");
            String[] pop3ConfigeSelect = null;
            List selectedList = null;
            List resultList = null;
            //取得所选邮件纪录
            selectedList = setSelectedList(request, pop3ConfigeSelect);
            //取得所有邮件纪录
            List allPageList = getPageList(cfg);
            //处理邮件统计事件
            if (request.getParameter("count") != null) {
                resultList = pop3MailMgrDao.countEmails(selectedList);
            }
            //处理外部邮件删除事件
            if (request.getParameter("delete") != null) {
                resultList = pop3MailMgrDao.deleteMails(selectedList);
            }
            //处理邮件接收事件
            if (request.getParameter("incept") != null) {
                pop3MailMgrDao.setServerPath(request.getRealPath("/"));
                String inceptResult = pop3MailMgrDao.downLoadEmails(
                    selectedList);
                pop3configForm.setInceptResult(inceptResult);
                pop3configForm.setInceptType("true");
                return super.FORWORD_LIST_PAGE;
            }
            //if (resultList != null && resultList.size() > 0) {
            setPageListByResultList(selectedList, resultList, allPageList);
            return super.FORWORD_LIST_PAGE;
            //}
        }
        StringBuffer sb = new StringBuffer();
        sb.append(" and POP3MAILSETUP.user_code=");
        sb.append(cfg.getBaseForm().getUserId());
        cfg.getBaseForm().setSqlWhere(sb.toString());

        super.doPage(cfg, "");

        long numberOfPop3Acount = pop3ConfigDao.queryByCount(new Pop3ConfigVO());
        pop3configForm.setNumberOfPop3Acount(numberOfPop3Acount);

        pop3configForm.setInceptType("false");
        return super.FORWORD_LIST_PAGE;
    }

    private List getPageList(GoaActionConfig cfg) {
        PageControl currentPageControl = cfg.getBaseForm().getPageControl();
        System.out.println("--- data in page control : " +
                           currentPageControl.getData().size());
        List allPageList = (List) currentPageControl.getData();
        return allPageList;
    }

    private List setSelectedList(HttpServletRequest request,
                                 String[] pop3ConfigeSelect) throws
        DefaultException {
        List selectedList;
        pop3ConfigeSelect = request.getParameterValues("pop3ConfigeSelect");
        for (int i = 0; i < pop3ConfigeSelect.length; i++) {
            pop3ConfigeSelect[i] = "'" + pop3ConfigeSelect[i] + "'";
        }
        selectedList = pop3ConfigDao.queryListByPK(pop3ConfigeSelect);
        return selectedList;
    }

    private void setPageListByResultList(List selectedList, List resultList,
                                         List allPageList) {
        for (int selectedListIndex = 0; selectedListIndex < selectedList.size();
             selectedListIndex++) {
            Pop3ConfigVO resultVo = (Pop3ConfigVO) selectedList.get(
                selectedListIndex);
            HashMap resultMap = (HashMap) resultList.get(selectedListIndex);
            resultVo.setExteriorMailCount( (String) resultMap.get("COUNT"));
            /** @todo 测试统计邮件数并在前台显示 */
            //resultVo.setExteriorMailCount("11111");
            //System.out.println("--- set exterior mail count ---111");
            for (int pageListIndex = 0; pageListIndex < allPageList.size();
                 pageListIndex++) {
                Pop3ConfigVO pageVO = (Pop3ConfigVO) allPageList.get(
                    pageListIndex);
                if (resultVo.getSerial_num().equals(pageVO.getSerial_num())) {
                    allPageList.set(pageListIndex, resultVo);
                    continue;
                }
            }
        }
    }

    private void setPop3MailMgrDao() {
        this.pop3MailMgrDao = MailMgrFactory.getInstance().creatPop3MailMgr(super.
            dbData);
    }
}
