package com.gever.goa.dailyoffice.mailmgr.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.dao.MailCapacityDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailDirectoryDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrFactory;
import com.gever.goa.dailyoffice.mailmgr.vo.MailCapacityVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailDirectoryVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;


public class MailDirectoryAction extends BaseAction {
    MailDirectoryDAO mailDirectoryDao = null;
    MailCapacityDAO mailCapacityDao = null;

    public MailDirectoryAction() {
    }
    public static final String FORWARD_MAIL_DIRECTORY_LIST = "maildirectoryindex";
    /**
     * 初始化页面数据
     * @param actionform 当前的vo对象
     * @return 当前所用的vo对象
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        MailDirectoryForm myForm = (MailDirectoryForm) cfg.getBaseForm(); //得到form变量
        mailDirectoryDao = MailMgrFactory.getInstance().creatMailDirectory(super.dbData); //得到相对应dao的实例
        cfg.setBaseDao( (BaseDAO) mailDirectoryDao);
        //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao

        //容错处理,防止vo对象为null
        if (myForm.getVo() == null){
            myForm.setVo(new MailDirectoryVO());
        }
    }
    /**
     * toDirectoryList
     *
     * @param cfg GoaActionConfig
     * @return String
     */
    public String toList(GoaActionConfig cfg) throws DefaultException {
       // System.out.println("--- in MailDirectoryAction ---");
        HttpServletRequest request = cfg.getRequest();
        MailDirectoryForm dirForm = (MailDirectoryForm)cfg.getBaseForm();
        MailCapacityVO capacityVo = null;
        MailDirectoryVO directory = null;
        this.setMailCapacityDao();
        this.setMailDirectoryDao();
        String userId = dirForm.getUserId();
        /** @todo 用户Id因为无法登录而先定为默认 */
        //String userId = "8001";
      //  System.out.println("--- current userId is " + userId);
        String[] selectedMail = request.getParameterValues("fid");
        if (selectedMail != null) {
            System.out.println("--- selected mail length is " +
                               selectedMail.length);
        }

        if(request.getParameter("operate") != null) {
            dirForm.setOperate("true");
        }
        capacityVo = getUserMailCapacity(userId);
        dirForm.setMailCapacityVo(capacityVo);

        List userMailDirList = this.mailDirectoryDao.queryAllMailDir(userId);
        dirForm.setUserMailDirList(userMailDirList);
        //mailMgrForm.setMailDirectoryVo(directory);
        return super.FORWORD_LIST_PAGE;
    }

    private MailCapacityVO getUserMailCapacity(String userId) throws
        DefaultException {
        MailCapacityVO capacityVo;
        List userCapacityList = this.mailCapacityDao.getMailCapacityByUser(userId);
        capacityVo = (MailCapacityVO)userCapacityList.get(0);
        return capacityVo;
    }
    protected String doPage(GoaActionConfig cfg, String pageType) throws
        DefaultException,
        Exception {
        return this.toList(cfg);
    }

    private void setMailCapacityDao() {
        this.mailCapacityDao = MailMgrFactory.getInstance().creatMailCapacity(super.dbData);
    }
    private void setMailDirectoryDao() {
        this.mailDirectoryDao = MailMgrFactory.getInstance().creatMailDirectory(super.dbData);
    }

}
