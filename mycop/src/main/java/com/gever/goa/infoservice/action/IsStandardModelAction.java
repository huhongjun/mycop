package com.gever.goa.infoservice.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.gever.exception.DefaultException;
import com.gever.goa.infoservice.dao.IsCustomerFactory;
import com.gever.goa.infoservice.dao.IsStandardModelDao;
import com.gever.goa.infoservice.vo.IsStandardModelVO;
import com.gever.goa.web.util.UploadFile;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.sysman.privilege.util.PermissionUtil;
import com.gever.util.IdMng;
import com.gever.util.StringUtils;
import com.gever.util.SumUtils;
import com.gever.vo.BaseTreeVO;


/**
 * <p>Title: 标准模板Action</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsStandardModelAction extends BaseAction {
    private IsStandardModelDao isStandardModelDao = null;

    public IsStandardModelAction() {
    }

    protected void initData(GoaActionConfig cfg)
        throws DefaultException, Exception {
        IsStandardModelForm myForm = (IsStandardModelForm) cfg.getBaseForm();
        isStandardModelDao = IsCustomerFactory.getInstance()
                                              .createIsStandardModel(super.dbData);
        cfg.setBaseDao((BaseDAO) isStandardModelDao);

        if (myForm.getVo() == null) {
            myForm.setVo(new IsStandardModelVO());
        }

        isStandardModelDao.setRealPath(cfg.getRequest().getRealPath("/"));
        this.setVoSql(false);
    }

    protected String toList(GoaActionConfig cfg)
        throws DefaultException, Exception {
        IsStandardModelForm form = (IsStandardModelForm) cfg.getBaseForm();
        String sqlWhere = "";
        String paraFlag = form.getParaFlag();
        String paraSimpleQuery = form.getParaSimpleQuery();
        paraSimpleQuery = StringUtils.replaceText(paraSimpleQuery);

        if (paraSimpleQuery == null) {
            paraSimpleQuery = "";
        }

        String paraNodeID = (String) (cfg.getRequest().getParameter("nodeid"));
        String nodeID = form.getNodeID();

        if (!StringUtils.isNull(paraNodeID)) {
            form.setParaSimpleQuery("");
            form.setNodeID(paraNodeID);
            nodeID = form.getNodeID();
        }

        if (StringUtils.isNull(paraNodeID) && StringUtils.isNull(nodeID)) {
            form.setNodeID("1");
            nodeID = "1";
        }

        sqlWhere = " AND IS_STANDARD_MODEL.MODEL_NAME LIKE '%" +
            paraSimpleQuery + "%' " + " AND IS_STANDARD_MODEL.MODEL_TYPE=" +
            nodeID + " ";

        if (!ignoreInfoType(nodeID)) {
            String infotype = form.getInfotype();

            //判断动态树的LIST页面显示
            if (!"".equals(infotype)) {
                //            	if(!StringUtils.isNull(infotype))
                sqlWhere += (" AND is_dotype.info_type='" + infotype + "'");
            }
        }

        form.setSqlWhere(sqlWhere);

        return super.toList(cfg);
    }

    protected String toEdit(GoaActionConfig cfg)
        throws DefaultException, Exception {
        String actionToPage = super.toEdit(cfg);
        String actionType = SumUtils.nullToString(cfg.getRequest().getParameter("actionType"));

        if (!this.MODIFY_ACTION.equals(actionType)) {
            IsStandardModelVO ismVO = (IsStandardModelVO) cfg.getBaseForm()
                                                             .getVo();
            ismVO.setIssue_flag("0");
            cfg.getBaseForm().setVo(ismVO);
        } else {
            ((IsStandardModelForm) cfg.getBaseForm()).setParaInsert((((IsStandardModelVO) cfg.getBaseForm()
                                                                                             .getVo())).getEditor_type());
        }

        String filePath = ((IsStandardModelVO) cfg.getBaseForm().getVo()).getFile_path();
        String editorType = ((IsStandardModelVO) cfg.getBaseForm().getVo()).getEditor_type();

        if (editorType.equals("1") && (filePath != null) &&
                !filePath.equals("")) {
            String downloadPath = cfg.getRequest().getRealPath("/");
            java.io.FileReader reader = new java.io.FileReader(downloadPath +
                    filePath);
            char[] chars = new char[1024];
            int count = 0;
            String content = "";

            while ((count = reader.read(chars, 0, 1024)) != -1) {
                content += new String(chars);
            }

            reader.close();
            ((IsStandardModelVO) cfg.getBaseForm().getVo()).setContent(content);
        }

        return actionToPage;
    }

    protected String doInsert(GoaActionConfig cfg, boolean isBack)
        throws DefaultException, Exception {
        IsStandardModelForm form = (IsStandardModelForm) cfg.getBaseForm();
        IsStandardModelVO vo = (IsStandardModelVO) form.getVo();
        String paraInsert = form.getParaInsert();

        if (paraInsert.equals("0")) {
            UploadFile uf = new UploadFile();
            uf.setModule("template");
            uf.saveOneFile(cfg.getRequest(), form, vo, "file_path", "file_name");
        } else {
            String uploadPath = cfg.getRequest().getRealPath("/");
            String filePath = "uploadfiles";
            uploadPath += filePath;

            String fileName = IdMng.getModuleID(form.getUserId()) + ".html";

            String content = vo.getContent();

            java.io.FileWriter fw = new java.io.FileWriter(uploadPath + "\\" +
                    fileName);
            java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
            bw.write(content);
            bw.flush();

            fw.close();
            bw.close();

            vo.setFile_name(fileName);
            vo.setFile_path(filePath + "\\" + fileName);
        }

        vo.setEditor_type(paraInsert);
        vo.setContent("");
        vo.setModel_type(form.getNodeID());

        if (!ignoreInfoType(form.getNodeID())) {
            vo.setInfo_type(form.getInfotype());
        }

        return super.doInsert(cfg, isBack);
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
        String editorType = ((IsStandardModelVO) cfg.getBaseForm().getVo()).getEditor_type();

        if (editorType.equals("0")) {
            UploadFile uf = new UploadFile();
            uf.setModule("template");
            uf.saveOneFile(cfg.getRequest(), cfg.getBaseForm(),
                cfg.getBaseForm().getVo(), "file_path", "file_name");
        } else {
            String uploadPath = cfg.getRequest().getRealPath("/");
            String filePath = "uploadfiles";
            uploadPath += filePath;

            String fileName = IdMng.getModuleID(cfg.getBaseForm().getUserId()) +
                ".html";
            String content = ((IsStandardModelVO) cfg.getBaseForm().getVo()).getContent();

            java.io.FileWriter fw = new java.io.FileWriter(uploadPath + "\\" +
                    fileName);
            java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
            bw.write(content);
            bw.flush();

            fw.close();
            bw.close();

            ((IsStandardModelVO) cfg.getBaseForm().getVo()).setFile_name(fileName);
            ((IsStandardModelVO) cfg.getBaseForm().getVo()).setFile_path(filePath +
                "\\" + fileName);
        }

        ((IsStandardModelVO) cfg.getBaseForm().getVo()).setContent("");

        return super.doUpdate(cfg, isBack);
    }

    protected String toView(GoaActionConfig cfg)
        throws DefaultException, Exception {
        String actionType = super.toView(cfg);

        String paraInsert = ((IsStandardModelForm) cfg.getBaseForm()).getParaInsert();
        String filePath = ((IsStandardModelVO) cfg.getBaseForm().getVo()).getFile_path();
        String editorType = ((IsStandardModelVO) cfg.getBaseForm().getVo()).getEditor_type();

        if (editorType.equals("1") && (filePath != null) &&
                !filePath.equals("")) {
            String downloadPath = cfg.getRequest().getRealPath("/");
            java.io.FileReader reader = new java.io.FileReader(downloadPath +
                    filePath);
            char[] chars = new char[1024];
            int count = 0;
            String content = "";

            while ((count = reader.read(chars, 0, 1024)) != -1) {
                content += new String(chars);
            }

            reader.close();
            ((IsStandardModelVO) cfg.getBaseForm().getVo()).setContent(content);
        }

        return actionType;
    }

    protected String doPage(GoaActionConfig cfg, String pageType)
        throws DefaultException, Exception {
        String nodeID = ((IsStandardModelForm) cfg.getBaseForm()).getNodeID();

        IsStandardModelDao isStandardModelDao = IsCustomerFactory.getInstance()
                                                                 .createIsStandardModel(super.dbData);
        isStandardModelDao.setSqlWhere(" AND IS_STANDARD_MODEL.MODEL_TYPE=" +
            nodeID + " ");

        String actionType = super.doPage(cfg, pageType);

        return actionType;
    }

    public String toTree(GoaActionConfig cfg)
        throws DefaultException, Exception {
        String paraNodeID = (String) (cfg.getRequest().getParameter("nodeid"));
        String nodeID = ((IsStandardModelForm) cfg.getBaseForm()).getNodeID();

        if (!StringUtils.isNull(paraNodeID)) {
            ((IsStandardModelForm) cfg.getBaseForm()).setNodeID(paraNodeID);
            nodeID = ((IsStandardModelForm) cfg.getBaseForm()).getNodeID();
        }

        if (StringUtils.isNull(paraNodeID) && StringUtils.isNull(nodeID)) {
            ((IsStandardModelForm) cfg.getBaseForm()).setNodeID("1");
        }

        ((IsStandardModelDao) cfg.getBaseDao()).setSqlWhere(
            " AND IS_STANDARD_MODEL.MODEL_TYPE=" + nodeID + " ");

        String actionType = super.toTree(cfg);

        return actionType;
    }

    //静态数的生成
    public String doStaticTreeData(GoaActionConfig cfg)
        throws DefaultException, Exception {
        HttpSession session = cfg.getSession();
		List statictreeData = new ArrayList();
		
		if (PermissionUtil.checkPermissionByCode(session, "VIEW", "ZZXX")){
        BaseTreeVO staticVO1 = new BaseTreeVO();
        staticVO1.setNodeid("0");
        staticVO1.setNodename("公司动态");

        //staticVO1.setAction("");
        staticVO1.setAction("/infoservice/templatelist.do?nodeid=13");
        staticVO1.setIsfolder("1");
        staticVO1.setSrc(
            "/infoservice/templatetree.do?action=doTreeData&#38;paraFlag=2");
		statictreeData.add(staticVO1);
		}
		
		if (PermissionUtil.checkPermissionByCode(session, "VIEW", "BMXX")){
        BaseTreeVO staticVO2 = new BaseTreeVO();
        staticVO2.setNodeid("1");
        staticVO2.setNodename("部门信息");

        //staticVO2.setAction("");
        staticVO2.setAction("/infoservice/templatelist.do?nodeid=14");
        staticVO2.setIsfolder("1");
        staticVO2.setSrc(
            "/infoservice/templatetree.do?action=doTreeData&#38;paraFlag=3");
		statictreeData.add(staticVO2);
		}
		
		if (PermissionUtil.checkPermissionByCode(session, "VIEW", "BGGL")){
        BaseTreeVO staticVO3 = new BaseTreeVO();
        staticVO3.setNodeid("2");
        staticVO3.setNodename("报告管理");
        staticVO3.setAction("/infoservice/templatelist.do?nodeid=15");
        staticVO3.setIsfolder("1");
        staticVO3.setSrc(
            "/infoservice/templatetree.do?action=doTreeData&#38;paraFlag=4");
		statictreeData.add(staticVO3);
		}
		

        if (PermissionUtil.checkPermissionByCode(session, "VIEW", "HYXW")) {
			BaseTreeVO staticVO4 = new BaseTreeVO();
            staticVO4.setNodeid("3");
            staticVO4.setNodename("行业新闻");
            staticVO4.setAction("/infoservice/templatelist.do?nodeid=16");
            staticVO4.setIsfolder("1");
            staticVO4.setSrc(
                "/infoservice/templatetree.do?action=doTreeData&#38;paraFlag=5");
			statictreeData.add(staticVO4);
        }


        if (PermissionUtil.checkPermissionByCode(session, "VIEW", "GLZD")) {
			BaseTreeVO staticVO5 = new BaseTreeVO();
            staticVO5.setNodeid("4");
            staticVO5.setNodename("管理制度");
            staticVO5.setAction("/infoservice/templatelist.do?nodeid=17");
            staticVO5.setIsfolder("1");
            staticVO5.setSrc(
                "/infoservice/templatetree.do?action=doTreeData&#38;paraFlag=6");
			statictreeData.add(staticVO5);
        }

        cfg.getRequest().setAttribute("treeData", statictreeData);

        return TREE_PAGE;
    }

    //生成树
    public String doTreeData(GoaActionConfig cfg)
        throws DefaultException, Exception {
        String paraFlag = ((IsStandardModelForm) cfg.getBaseForm()).getParaFlag();
        String nodeID = StringUtils.nullToString(cfg.getRequest().getParameter("nodeid"));
        cfg.getRequest().setAttribute("treeData",
            isStandardModelDao.getTreeData(paraFlag, nodeID));

        return TREE_PAGE;
    }

    /**
     * 判断是否忽略infotype
     * 当nodeid>=13的时候返回false; 否则为true;
     * @param nodeid
     * @return
     */
    private boolean ignoreInfoType(String nodeID) {
        int intnodeid;

        try {
            intnodeid = Integer.parseInt(nodeID);
        } catch (NumberFormatException nfe) {
            intnodeid = 0;
        }

        if (intnodeid >= 13) {
            return false;
        } else {
            return true;
        }
    }
}
