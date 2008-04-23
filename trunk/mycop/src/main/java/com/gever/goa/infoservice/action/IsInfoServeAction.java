package com.gever.goa.infoservice.action;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.infoservice.dao.IsCustomerFactory;
import com.gever.goa.infoservice.dao.IsDoTypeDao;
import com.gever.goa.infoservice.dao.IsInfoServeDao;
import com.gever.goa.infoservice.dao.IsStandardModelDao;
import com.gever.goa.infoservice.vo.IsDepartmentVO;
import com.gever.goa.infoservice.vo.IsDoTypeVO;
import com.gever.goa.infoservice.vo.IsInfoServeVO;
import com.gever.goa.infoservice.vo.IsStandardModelVO;
import com.gever.goa.web.util.TemplateUtils;
import com.gever.goa.web.util.UploadFile;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.Constant;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.util.Codes;
import com.gever.util.StringUtils;
import com.gever.util.SumUtils;


/**
 * <p>Title: 组织信息Action</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsInfoServeAction extends BaseAction {
    private IsInfoServeDao isInfoServeDao = null;

    public IsInfoServeAction() {
    }

    protected void initData(GoaActionConfig cfg)
        throws DefaultException, Exception {
        IsInfoServeForm myForm = (IsInfoServeForm) cfg.getBaseForm();
        isInfoServeDao = IsCustomerFactory.getInstance().createIsInfoServe(super.dbData);
        cfg.setBaseDao((BaseDAO) isInfoServeDao);

        if (myForm.getVo() == null) {
            myForm.setVo(new IsInfoServeVO());
        }

        isInfoServeDao.setRealPath(cfg.getRequest().getRealPath("/"));
        this.setVoSql(false);
    }

    protected String toList(GoaActionConfig cfg)
        throws DefaultException, Exception {
        HttpServletRequest request = cfg.getRequest();
        IsInfoServeForm form = (IsInfoServeForm) cfg.getBaseForm();
        String paraFlag = form.getParaFlag();
        String userCode = form.getUserId();
        String paraNodeID = (String) (cfg.getRequest().getParameter("nodeid"));

        //GW add
        if ((paraNodeID != null) && !"".equals(paraNodeID)) {
            form.setParaSimpleQuery("");
        }

        String nodeID = ((IsInfoServeForm) cfg.getBaseForm()).getNodeID();
        String paraSimpleQuery = form.getParaSimpleQuery();
        log.showLog("paraSimpleQuery"+paraSimpleQuery);
        paraSimpleQuery = StringUtils.replaceText(paraSimpleQuery);

        if (!StringUtils.isNull(paraNodeID)) {
            form.setNodeID(paraNodeID);
            nodeID = form.getNodeID();
        }

        if ((paraSimpleQuery != null) && !paraSimpleQuery.equals("")) {
            form.setSqlWhere(
                " AND (is_info_serve.SHOW_FLAG=1 OR (IS_INFO_SERVE.USER_CODE=" +
                userCode +
                " AND (IS_INFO_SERVE.show_flag=0 OR IS_INFO_SERVE.SEND_FLAG IS NULL))) AND IS_INFO_SERVE.INFO_FLAG=" +
                paraFlag + " AND IS_INFO_SERVE.TITLE LIKE '%" +
                paraSimpleQuery + "%' " + " AND IS_DOTYPE.INFO_TYPE='" +
                nodeID + "' ");
        } else {
            form.setSqlWhere(
                " AND (is_info_serve.SHOW_FLAG=1 OR (IS_INFO_SERVE.USER_CODE=" +
                userCode +
                " AND (IS_INFO_SERVE.show_flag=0 OR IS_INFO_SERVE.SEND_FLAG IS NULL))) AND IS_INFO_SERVE.INFO_FLAG=" +
                paraFlag + " AND IS_DOTYPE.INFO_TYPE='" + nodeID + "' ");
        }

        //gw add
        String deptid = form.getDeptid();
        System.out.println("部门id＝＝＝＝＝＝＝＝＝＝" + deptid);

        if ((deptid != null) && !deptid.equals("")) {
            form.setSqlWhere(form.getSqlWhere() + " and dept ='" + deptid +
                "'");
        }

        return super.toList(cfg);
    }

    protected String doInsert(GoaActionConfig cfg, boolean isBack)
        throws DefaultException, Exception {
        UploadFile uf = new UploadFile();
        uf.setModule("server");
        uf.saveOneFile(cfg.getRequest(), cfg.getBaseForm(),
            cfg.getBaseForm().getVo(), "file_path", "file_name");

        IsInfoServeForm iisForm = (IsInfoServeForm) cfg.getBaseForm();
        IsInfoServeVO iisVO = (IsInfoServeVO) cfg.getBaseForm().getVo();
        iisVO.setContent(Codes.decode(iisForm.getCellcontent().toCharArray()));

        //System.out.println("......content-content.........:"
        //+ iisVO.getContent());
        //插入HTML编辑器数据
        //System.out.println("...........word content..........:"
        //+ iisVO.getWord_content());
        //       iisVO.setWord_content(sos);
        String actionType = super.doInsert(cfg, isBack);
        this.initEditPage(cfg, actionType);

        return actionType;
    }

    protected String doUpdate(GoaActionConfig cfg, boolean isBack)
        throws DefaultException, Exception {
        UploadFile uf = new UploadFile();
        uf.setModule("server");
        uf.saveOneFile(cfg.getRequest(), cfg.getBaseForm(),
            cfg.getBaseForm().getVo(), "file_path", "file_name");

        IsInfoServeForm iisForm = (IsInfoServeForm) cfg.getBaseForm();
        IsInfoServeVO iisVO = (IsInfoServeVO) cfg.getBaseForm().getVo();
        iisVO.setContent(Codes.decode(iisForm.getCellcontent().toCharArray()));

        String actionType = super.doUpdate(cfg, isBack);
        this.initEditPage(cfg, actionType);

        return actionType;
    }

    protected String toEdit(GoaActionConfig cfg)
        throws DefaultException, Exception {
        IsInfoServeForm form = (IsInfoServeForm) cfg.getBaseForm();
        String strReturn = super.toEdit(cfg);
        String actionType = SumUtils.nullToString(cfg.getRequest().getParameter("actionType"));

        this.initEditPage(cfg, actionType);

        IsInfoServeVO vo = (IsInfoServeVO) form.getVo();
        String infotype = vo.getInfo_type();

        if ("".equals(infotype)) {
            vo.setInfo_type(form.getNodeID());
        }

        if (!this.MODIFY_ACTION.equals(actionType)) {
            //读取HTML模板
            IsStandardModelDao ismDao = IsCustomerFactory.getInstance()
                                                         .createIsStandardModel(Constant.DATA_SOURCE);
            IsStandardModelVO ismVO; // = (IsStandardModelVO) ismDao.getTemplate(13);

            if (form.getParaFlag().equals("2")) {
                log.showLog("加载组织信息模板!");
                ismVO = (IsStandardModelVO) ismDao.getTemplate(13,
                        form.getNodeID());
            } else if (form.getParaFlag().equals("5")) {
                log.showLog("加载行业新闻模板!");
                ismVO = (IsStandardModelVO) ismDao.getTemplate(16,
                        form.getNodeID());
            } else if (form.getParaFlag().equals("6")) {
                log.showLog("加载管理制度模板!");
                ismVO = (IsStandardModelVO) ismDao.getTemplate(17,
                        form.getNodeID());
            } else {
                log.showLog("加载部门信息模板!");
                ismVO = (IsStandardModelVO) ismDao.getTemplate(14,
                        form.getNodeID());
            }

            String filePath = ismVO.getFile_path();
            log.showLog("文件路径:" + filePath);

            String editorType = ismVO.getEditor_type();
            log.showLog("编辑器类别:" + editorType);

            // String editortype=ismVO.getEditor_type();
            if (!"".equals(editorType)) {
                vo.setEditor_type(editorType);
            }

            if (editorType.equals("1") && (filePath != null) &&
                    !filePath.equals("")) {
                String downloadPath = cfg.getRequest().getRealPath("/");
                java.io.FileReader reader = new java.io.FileReader(downloadPath +
                        filePath);
                char[] chars = new char[1024];
                int count = 0;
                String word_content = "";

                while ((count = reader.read(chars, 0, 1024)) != -1) {
                    word_content += new String(chars);
                }

                reader.close();
                ((IsInfoServeVO) cfg.getBaseForm().getVo()).setWord_content(word_content);
            }
        }

        return strReturn;
    }

    protected String toView(GoaActionConfig cfg)
        throws DefaultException, Exception {
        String forwardpath = super.toView(cfg);
        makeCellField(cfg);

        return forwardpath;
    }

    private void makeCellField(GoaActionConfig cfg)
        throws DefaultException, Exception {
        TemplateUtils templateUtils = new TemplateUtils();
        IsInfoServeForm iisForm = (IsInfoServeForm) cfg.getBaseForm();
        IsInfoServeVO iisVO = (IsInfoServeVO) iisForm.getVo();
        String strPath = super.getServlet().getServletContext().getRealPath("/");
        String strFilename = "/cell/report/" +
            templateUtils.makeCellName(strPath + "/cell/report") + ".cll";
        iisForm.setCellname(strFilename);

        byte[] bcell = iisVO.getContent();

        if (bcell != null) {
            java.io.File file = new java.io.File(strPath + strFilename);
            OutputStream fos = new FileOutputStream(file);
            OutputStream os = new BufferedOutputStream(fos);
            os.write(bcell);
            os.close();
        }
    }

    public String doDepTreeData(GoaActionConfig cfg)
        throws DefaultException, Exception {
        String userID = StringUtils.nullToString(cfg.getBaseForm().getUserId());

        if ("".equals(userID) || (userID == null)) {
            userID = "";
        }

        cfg.getRequest().setAttribute("treeData",
            isInfoServeDao.getDepTreeData(userID));

        return TREE_PAGE;
    }

    public String doTreeData(GoaActionConfig cfg)
        throws DefaultException, Exception {
        IsInfoServeForm form = (IsInfoServeForm) cfg.getBaseForm();
        String paraFlag = form.getParaFlag();
        String nodeID = StringUtils.nullToString(cfg.getRequest().getParameter("nodeid"));
        String deptid = StringUtils.nullToString(cfg.getRequest().getParameter("deptid"));
        cfg.getRequest().setAttribute("treeData",
            isInfoServeDao.getTreeData(paraFlag, nodeID, deptid));

        return TREE_PAGE;
    }

    protected String doPage(GoaActionConfig cfg, String pageType)
        throws DefaultException, Exception {
        String paraFlag = ((IsInfoServeForm) cfg.getBaseForm()).getParaFlag();
        String userCode = ((IsInfoServeForm) cfg.getBaseForm()).getUserId();
        String nodeID = ((IsInfoServeForm) cfg.getBaseForm()).getNodeID();

        IsInfoServeDao isInfoServeDao = IsCustomerFactory.getInstance()
                                                         .createIsInfoServe(super.dbData);
        isInfoServeDao.setSqlWhere(
            " AND (IS_INFO_SERVE.SEND_FLAG=1 OR (IS_INFO_SERVE.USER_CODE=" +
            userCode +
            " AND (IS_INFO_SERVE.SEND_FLAG=0 OR IS_INFO_SERVE.SEND_FLAG IS NULL))) AND IS_INFO_SERVE.INFO_FLAG=" +
            paraFlag + " AND IS_DOTYPE.INFO_TYPE='" + nodeID + "' ");

        String actionType = super.doPage(cfg, pageType);

        return actionType;
    }

    private GoaActionConfig initEditPage(GoaActionConfig cfg, String actionType)
        throws DefaultException, Exception {
        //        ((IsInfoServeVO) cfg.getBaseForm().getVo()).setDept(cfg.getBaseForm()
        //        .getCurDeptNames());
        if (!this.MODIFY_ACTION.equals(actionType)) {
            IsInfoServeVO iisVO = (IsInfoServeVO) cfg.getBaseForm().getVo();
            iisVO.setSend_flag("0");
            iisVO.setHint_flag("0");
            iisVO.setShow_flag("0");
            cfg.getBaseForm().setVo(iisVO);
        }

        //GW ADD DepTypeList 部门列表初始化
        //        SystemMngUtil smu = new SystemMngUtil();
        //        String depType = "";
        //        if (!this.MODIFY_ACTION.equals(actionType)) {
        //            depType = smu.getDepartmentNamesByUser(Integer.parseInt(cfg
        //            .getBaseForm().getUserId()));
        //        } else {
        //            depType = smu.getDepartmentNamesByUser(Integer
        //            .parseInt(((IsInfoServeVO) cfg.getBaseForm().getVo())
        //            .getUser_code()));
        //        }
        //        ((IsInfoServeForm) cfg.getBaseForm()).setDepType(depType);
        //类别初始化列表
        IsDoTypeDao sitDao = IsCustomerFactory.getInstance().createIsDoType(super.dbData);
        IsDoTypeVO sitVO = new IsDoTypeVO();
        IsInfoServeForm form = (IsInfoServeForm) cfg.getBaseForm();

        String paraFlag = form.getParaFlag();
        Collection IsDoTypeList = sitDao.queryByModuleFlag(paraFlag, sitVO);
        cfg.getRequest().setAttribute("IsDoTypeList", IsDoTypeList);

        //GW ADD DepTypeList 部门列表初始化
        IsInfoServeDao sisDao = IsCustomerFactory.getInstance()
                                                 .createIsInfoServe(super.dbData);
        IsDepartmentVO deptVO = new IsDepartmentVO();
        String userId = form.getUserId();
        Collection IsDeptTypeList = sisDao.queryByUserId(userId, deptVO);
        cfg.getRequest().setAttribute("IsDeptTypeList", IsDeptTypeList);

        IsStandardModelDao ismDao = IsCustomerFactory.getInstance()
                                                     .createIsStandardModel(super.dbData);
        IsStandardModelVO ismVO = null;

        if (((IsInfoServeForm) cfg.getBaseForm()).getParaFlag().equals("2")) {
            ismVO = (IsStandardModelVO) ismDao.getTemplate(13, form.getNodeID());
        } else if (((IsInfoServeForm) cfg.getBaseForm()).getParaFlag().equals("3")) {
            ismVO = (IsStandardModelVO) ismDao.getTemplate(14, form.getNodeID());
        } else if (((IsInfoServeForm) cfg.getBaseForm()).getParaFlag().equals("5")) {
            ismVO = (IsStandardModelVO) ismDao.getTemplate(16, form.getNodeID());
        } else {
            ismVO = (IsStandardModelVO) ismDao.getTemplate(17, form.getNodeID());
        }

        ((IsInfoServeForm) cfg.getBaseForm()).setTemplatePath(ismVO.getFile_path());

        return cfg;
    }
}
