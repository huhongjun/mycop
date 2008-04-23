package com.gever.goa.infoservice.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.infoservice.dao.IsCustomerFactory;
import com.gever.goa.infoservice.dao.IsDoTypeDao;
import com.gever.goa.infoservice.dao.IsInfoManageDao;
import com.gever.goa.infoservice.vo.IsDoTypeVO;
import com.gever.goa.infoservice.vo.IsInfoManageVO;
import com.gever.goa.web.util.UploadFile;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.util.StringUtils;

/**
 * <p>Title: 公共信息Action</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class IsInfoManageAction extends BaseAction {
    private IsInfoManageDao isInfoManageDao = null;

    public IsInfoManageAction() {
    }

    protected void initData(GoaActionConfig cfg) throws DefaultException,
            Exception {
        IsInfoManageForm myForm = (IsInfoManageForm) cfg.getBaseForm();
        isInfoManageDao = IsCustomerFactory.getInstance().createIsInfoManage(
                super.dbData);
        cfg.setBaseDao((BaseDAO) isInfoManageDao);
        cfg.getBaseDao().setQueryTblName("IS_INFO_MANAGE");
        if (myForm.getVo() == null)
            myForm.setVo(new IsInfoManageVO());
        isInfoManageDao.setRealPath(cfg.getRequest().getRealPath("/"));
        this.setVoSql(false);
    }

    protected String toList(GoaActionConfig cfg) throws DefaultException,
            Exception {
        HttpServletRequest request = cfg.getRequest();
        IsInfoManageForm form = (IsInfoManageForm) cfg.getBaseForm();
        String paraFlag = form.getParaFlag();
        String paraNodeID = (String) (cfg.getRequest().getParameter("nodeid"));
        	log.console(this.getClass(),"toList()","paraFlag:"+paraFlag+",nodeID:"+paraNodeID);
        if (paraNodeID != null && !"".equals(paraNodeID)) {
            form.setParaSimpleQuery("");
        }
        
		IsDoTypeDao isTypeDao = IsCustomerFactory.getInstance()
        .createIsDoType(super.dbData);
		String remark = isTypeDao.getRemark(paraNodeID);
		((IsInfoManageForm) cfg.getBaseForm()).setNodeRemark(remark);
		
		String nodeID = ((IsInfoManageForm) cfg.getBaseForm()).getNodeID();
        String paraSimpleQuery = form.getParaSimpleQuery();
        paraSimpleQuery = StringUtils.replaceText(paraSimpleQuery);
        if (!StringUtils.isNull(paraNodeID)) {
            ((IsInfoManageForm) cfg.getBaseForm()).setNodeID(paraNodeID);
            nodeID = ((IsInfoManageForm) cfg.getBaseForm()).getNodeID();
        }

        if (paraSimpleQuery != null && !paraSimpleQuery.equals("")) {

            form.setSqlWhere(" AND IS_INFO_MANAGE.INFO_FLAG=" + paraFlag
                            + " AND IS_INFO_MANAGE.TITLE LIKE '%"
                            + paraSimpleQuery + "%' "
                            + " AND IS_INFO_MANAGE.INFO_TYPE='" + nodeID + "' ");
        } else {
            form.setSqlWhere(" AND IS_INFO_MANAGE.INFO_FLAG=" + paraFlag
                    + " AND IS_INFO_MANAGE.INFO_TYPE='" + nodeID + "' ");
        }

        return super.toList(cfg);
    }

    /**
     * 到树页面
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws java.lang.Exception
     */
    public String doOrderBy(GoaActionConfig cfg) throws DefaultException,
            Exception {
        cfg.getBaseDao().setQueryTblName("IS_INFO_MANAGE");
        doPage(cfg, ""); // 得到第一页的资料
        return FORWORD_LIST_PAGE;
    }

    protected String doInsert(GoaActionConfig cfg, boolean isBack)
            throws DefaultException, Exception {
        IsInfoManageForm form = (IsInfoManageForm) cfg.getBaseForm();
        UploadFile uf = new UploadFile();
        uf.setModule("manage");
        uf.saveOneFile(cfg.getRequest(), cfg.getBaseForm(), cfg.getBaseForm()
                .getVo(), "file_path", "file_name");

//        String actionType = super.doInsert(cfg, isBack);
        String actionType=null;
        HttpServletRequest request=cfg.getRequest();

        IsInfoManageVO vo = (IsInfoManageVO) form.getVo();
        String infotype = vo.getInfo_type();
        if ("".equals(infotype)) {
            vo.setInfo_type(form.getNodeID());
        }
        this.initEditPage(cfg, actionType);

//        return actionType;
        try {
        	actionType = super.doInsert(cfg, isBack);
        } catch (DefaultException e) {
            if (e.getMessage().equals("PK repeat!")) {
                request.setAttribute("ErrorMsg", "主题名称已存在,请重新输入！");

                return FORWORD_EDIT_PAGE;
            } else {
                throw e;
            }
        }

        return actionType;
    }

    /**
     * 文件上传
     * @param cfg
     * @param isBack
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */
    protected String doUpdate(GoaActionConfig cfg, boolean isBack)
            throws DefaultException, Exception {

        UploadFile uf = new UploadFile();
        uf.setModule("manage");
        uf.saveOneFile(cfg.getRequest(), cfg.getBaseForm(), cfg.getBaseForm()
                .getVo(), "file_path", "file_name");

        String actionType = super.doUpdate(cfg, isBack);

        this.initEditPage(cfg, actionType);

        return actionType;
    }

    protected String toEdit(GoaActionConfig cfg) throws DefaultException,
            Exception {
        IsInfoManageForm form = (IsInfoManageForm) cfg.getBaseForm();

        String actionType = super.toEdit(cfg);

        this.initEditPage(cfg, actionType);
        IsInfoManageVO vo = (IsInfoManageVO) form.getVo();
        String infotype = vo.getInfo_type();
        if ("".equals(infotype)) {
            vo.setInfo_type(form.getNodeID());
        }

        return actionType;
    }

    public String doTreeData(GoaActionConfig cfg) throws DefaultException,
            Exception {

        String paraFlag = ((IsInfoManageForm) cfg.getBaseForm()).getParaFlag();
        String nodeID = StringUtils.nullToString(cfg.getRequest().getParameter(
                "nodeid"));
        cfg.getRequest().setAttribute("treeData",
                isInfoManageDao.getTreeData(paraFlag, nodeID));
        return TREE_PAGE;
    }

    protected String doPage(GoaActionConfig cfg, String pageType)
            throws DefaultException, Exception {

        String paraFlag = ((IsInfoManageForm) cfg.getBaseForm()).getParaFlag();
        String userCode = ((IsInfoManageForm) cfg.getBaseForm()).getUserId();
        String nodeID = ((IsInfoManageForm) cfg.getBaseForm()).getNodeID();

        IsInfoManageDao isInfoServeDao = IsCustomerFactory.getInstance()
                .createIsInfoManage(super.dbData);
        isInfoManageDao.setSqlWhere(" AND IS_INFO_MANAGE.INFO_FLAG=" + paraFlag
                + " AND IS_INFO_MANAGE.INFO_TYPE='" + nodeID + "' ");

        String actionType = super.doPage(cfg, pageType);

        return actionType;
    }

    private GoaActionConfig initEditPage(GoaActionConfig cfg, String actionType)
            throws DefaultException, Exception {

        if (!this.MODIFY_ACTION.equals(actionType)) {
            IsInfoManageVO iimVO = (IsInfoManageVO) cfg.getBaseForm().getVo();
            String infolevle = iimVO.getInfo_levle();
            if ("".equals(infolevle)) {
                iimVO.setInfo_levle("1");
            } else {

            }

            cfg.getBaseForm().setVo(iimVO);
        }

        IsDoTypeDao idtDao = IsCustomerFactory.getInstance().createIsDoType(
                super.dbData);
        IsDoTypeVO idtVO = new IsDoTypeVO();
        IsInfoManageForm form = (IsInfoManageForm) cfg.getBaseForm();
        String paraFlag = form.getParaFlag();
        Collection IsDoTypeList = idtDao.queryByModuleFlag(paraFlag, idtVO);
        cfg.getRequest().setAttribute("IsDoTypeList", IsDoTypeList);

        return cfg;
    }

}