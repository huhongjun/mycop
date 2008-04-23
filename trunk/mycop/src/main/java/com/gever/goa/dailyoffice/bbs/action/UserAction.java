package com.gever.goa.dailyoffice.bbs.action;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.bbs.dao.BBSFactory;
import com.gever.goa.dailyoffice.bbs.dao.BBSMngDAO;
import com.gever.goa.dailyoffice.bbs.vo.UserVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.struts.form.BaseForm;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.dao.UserDAO;
import com.gever.sysman.organization.model.User;
import com.gever.util.StringUtils;

public class UserAction extends BaseAction {
    private static final String FORWORD_TOPIC_PAGE = "topic";
    protected void initData(GoaActionConfig cfg) throws DefaultException,
            Exception {

        BaseForm myForm = cfg.getBaseForm(); //得到form变量
        BBSMngDAO bbsdao = BBSFactory.getInstance().createMngDAO(super.dbData); //得到相对应dao的实例
        bbsdao.setQuest_Sql(BBSMngDAO.BBSUSER_QUERY_SQL);
        cfg.setBaseDao( (BaseDAO) bbsdao);
        //容错处理,防止vo对象为null
        if (myForm.getVo() == null)
            myForm.setVo(new UserVO());

//        String currentPath = (String) cfg.getSession().getAttribute(
//                "currentaction");
//        log.showLog("cfg.getSession().getAttribute(currentaction)*******" +
//                    currentPath);
//        log.showLog("cfg.getMapping().getInput()*******" +
//                    cfg.getMapping().getInput());
//        cfg.getMapping().getInputForward().getPath();
//        log.showLog("cfg.getMapping().getInputForward().getPath()*******" +
//                    cfg.getMapping().getInputForward().getPath());

    }

    public String toReg(GoaActionConfig cfg) throws
            DefaultException,
            Exception {

        BaseForm bForm = cfg.getBaseForm();
        UserDAO userDAO = OrganizationFactory.getInstance().
                getUserDAO();
        User user = userDAO.findUserByID(Integer.parseInt(bForm.getUserId()));
        UserVO vo = new UserVO();
        vo.setUser_code(bForm.getUserId());
        vo.setNickname(user.getName());
        vo.setSex_code(user.getGender());
        vo.setUser_state("0");
        bForm.setVo(vo);
        cfg.setBaseForm(bForm);
        //System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVv"+cfg.getBaseForm().getVo());
        return this.FORWORD_EDIT_PAGE;
    }

    /**
     * 在注册（插入用户信息）之后跳转到 重新登陆。。。
     * @param cfg GoaActionConfig
     * @param isBack boolean
     * @throws DefaultException
     * @throws Exception
     * @return String
     */
    public String doInsert(GoaActionConfig cfg, boolean isBack) throws
            DefaultException,
            Exception {
        UserForm form = (UserForm) cfg.getBaseForm();
        String forword = super.doInsert(cfg, isBack);
        String sqlWhere = form.getSqlWhere();
        log.showLog("-------sqlWhere--------" + sqlWhere);
        if (sqlWhere.equals("")) {
            return forword;
        } else {
            forword = this.FORWORD_TOPIC_PAGE;
        }
        return forword;
    }

    public String doUpdate(GoaActionConfig cfg, boolean isBack) throws
            DefaultException,
            Exception {
        UserForm form = (UserForm) cfg.getBaseForm();
        String forword = super.doUpdate(cfg, isBack);
        String sqlWhere = form.getSqlWhere();
        log.showLog("-------sqlWhere--------" + sqlWhere);
        if (sqlWhere.equals("")) {
            return forword;
        } else {
            forword = this.FORWORD_TOPIC_PAGE;
        }
        return forword;
    }

    protected String toList(GoaActionConfig cfg) throws
        DefaultException,
        Exception {
    String sqlWhere="";
    UserForm form=(UserForm)cfg.getBaseForm();
    String nickname=form.getSearchValue();
    if(!nickname.equals("")){
        nickname=StringUtils.replaceText(nickname);
        sqlWhere+=" and nickname like '%"+nickname+"%'";
    }
    form.setSqlWhere(sqlWhere);
    String forword = super.toList(cfg);
    return forword;
}

}
