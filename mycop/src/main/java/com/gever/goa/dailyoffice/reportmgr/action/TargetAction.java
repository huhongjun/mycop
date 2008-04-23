package com.gever.goa.dailyoffice.reportmgr.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.message.dao.MessageDao;
import com.gever.goa.dailyoffice.message.dao.MessageFactory;
import com.gever.goa.dailyoffice.reportmgr.dao.TargetDao;
import com.gever.goa.dailyoffice.reportmgr.dao.TargetFactory;
import com.gever.goa.dailyoffice.reportmgr.vo.TargetVO;
import com.gever.goa.dailyoffice.targetmng.util.TargetConstant;
import com.gever.goa.infoservice.dao.IsCustomerFactory;
import com.gever.goa.infoservice.dao.IsStandardModelDao;
import com.gever.goa.infoservice.vo.IsStandardModelVO;
import com.gever.goa.util.ConstantSet;
import com.gever.goa.web.util.TemplateUtils;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.Constant;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.sysman.api.OrganizationUtil;
import com.gever.sysman.organization.dao.DepartmentDAO;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.dao.UserDAO;
import com.gever.sysman.organization.model.Department;
import com.gever.sysman.organization.model.User;
import com.gever.sysman.organization.model.impl.DefaultDepartment;
import com.gever.sysman.organization.model.impl.DefaultUser;
import com.gever.util.Codes;
import com.gever.util.DateTimeUtils;
import com.gever.util.StringUtils;

/**
 *
 * <p>Title:目标管理动作控制类 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class TargetAction extends BaseAction {
    public TargetAction() {
    }

    /**
     * 初始化页面数据
     * @param cfg GoaActionConfig
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException {
        TargetForm form = (TargetForm) cfg.getBaseForm();
        TargetDao targetDao = TargetFactory.getInstance().createTarget(super.
            dbData);
        cfg.setBaseDao( (BaseDAO) targetDao);
        if (form.getVo() == null) {
            form.setVo(new TargetVO());
        }

        //根据不同的目标类型初始化不同的页面
        this.initPage(cfg);

    }

    /**
     * 初始化页面
     * @param cfg GoaActionConfig
     */
    private void initPage(GoaActionConfig cfg) {
        TargetForm myForm = (TargetForm) cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();
        TargetConstant tc = new TargetConstant(); //常量类
        String targetType = request.getParameter("targetType"); //得到目标类型
        System.out.println("The targetType is======:" + targetType);
        String nodeid = request.getParameter("nodeid"); //报告类型
        System.out.println("The node is=======" + nodeid);
        /**
         * 根据目标类型，做相应的设置
         */
        if (!StringUtils.isNull(targetType)) {
            myForm.setTypeFlag(tc.getTargetType(targetType)); //设置报告类型标志
            myForm.setTypeFlagName(tc.getTypeFlagName(targetType)); //设置第一级目录名字
            System.out.println("The typeFlagName is====" + myForm.getTypeFlagName());
            myForm.setTargetTypeName(tc.getTypeFlagName(targetType));
        }
        else if (!StringUtils.isNull(nodeid)) {
            myForm.setTargetType(nodeid); //设置报告类型
            myForm.setTargetTypeName(nodeid); //设置报告类型名字
        }
    }

    /**
     *
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @throws Exception
     * @return String
     */

    protected String toList(GoaActionConfig cfg) throws DefaultException,
        Exception {
        TargetForm myForm = (TargetForm) cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();
        OrganizationUtil systemMngUtil = new OrganizationUtil(); //工具类

        String deptCode = request.getParameter("nodeid"); //得到部门ID
        String queryFlag = request.getParameter("queryFlag"); ; //得到是否查询标志
        String curYear = DateTimeUtils.getCurrentDate().substring(0, 4); //得到当前年份

        String deptName = "";
        String curUserId = cfg.getBaseForm().getUserId(); //得到当前用户ID
        ConstantSet.CurUserID = curUserId; //设置ConstantSet userId
        StringBuffer sbSqlWhere = new StringBuffer();

        //判断如果nodiid为空，则部门ID设置为当前用户所在部门ID
        if (StringUtils.isNull(deptCode)) {
            deptCode = cfg.getBaseForm().getCurDeptCodes();
            System.out.println("----the current depe is-----" + deptCode);
        }

        //设置部门名称
        deptName = systemMngUtil.getDeptNameByDeptID(deptCode);
        myForm.setDeptname(deptName);

        /**
         * 判断如果queryFlag为true则表示查询动作
         */
        System.out.println("the queryFlag is:" + queryFlag);
        if ("true".equals(queryFlag)) {
            String searchYear = ( (TargetForm) cfg.getBaseForm()).getSearchYear();
            String searchMonth = ( (TargetForm) cfg.getBaseForm()).
                getSearchMonth();
            String searchWeek = ( (TargetForm) cfg.getBaseForm()).getSearchWeek();
            sbSqlWhere.append(" where 1=1 and CURRENT_YEAR like '%" + searchYear +
                              "%' ");
            if (myForm.getTypeFlag().equals("2")) {
                sbSqlWhere.append(" and CURRENT_MONTH like '%" + searchMonth + "%' ");
            }
            if (myForm.getTypeFlag().equals("1")) {
                sbSqlWhere.append(" and CURRENT_MONTH like '%" + searchMonth + "%' ");
                sbSqlWhere.append(" and CURRENT_WORK like '%" + searchWeek + "%' ");
            }
            sbSqlWhere.append(" and TARGET_TYPE = '" + myForm.getTargetTypeName() +"'");
            sbSqlWhere.append(" and ");
            if (myForm.getTypeFlag().equals("2") || myForm.getTypeFlag().equals("1")) { //根据报告类型来确定条件
                sbSqlWhere.append(super.getIsNotNull("CURRENT_MONTH"));
            }
            else {
                sbSqlWhere.append(super.getIsNull("CURRENT_MONTH"));
            }
            sbSqlWhere.append(" and ");
            if (myForm.getTypeFlag().equals("1")) {
                sbSqlWhere.append(super.getIsNotNull("CURRENT_WORK"));
            }
            else {
                sbSqlWhere.append(super.getIsNull("CURRENT_WORK"));
            }
        }
        else {
            sbSqlWhere.append(" where 1=1 and CURRENT_YEAR = '" + curYear + "' ");
            sbSqlWhere.append(" and TARGET_TYPE = '" + myForm.getTargetTypeName() +"'");
            sbSqlWhere.append(" and ");
            if (myForm.getTypeFlag().equals("2") || myForm.getTypeFlag().equals("1")) { //根据报告类型来确定条件
                sbSqlWhere.append(super.getIsNotNull("CURRENT_MONTH"));
            }
            else {
                sbSqlWhere.append(super.getIsNull("CURRENT_MONTH"));
            }
            sbSqlWhere.append(" and ");
            if (myForm.getTypeFlag().equals("1")) {
                sbSqlWhere.append(super.getIsNotNull("CURRENT_WORK"));
            }
            else {
                sbSqlWhere.append(super.getIsNull("CURRENT_WORK"));
            }
        }

        cfg.getBaseForm().setSqlWhere(sbSqlWhere.toString());
        return super.toList(cfg);
    }

    /**
     *
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @throws Exception
     * @return String
     */
    protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
        DefaultException, Exception {
        TargetForm myForm = (TargetForm) cfg.getBaseForm();
        TargetVO vo = (TargetVO) myForm.getVo();

        //用来判断用户填写的资料是否已经存在于数据库中了
        String current_year = vo.getCurrent_year();
        String current_month = vo.getCurrent_month();
        String current_week = vo.getCurrent_work();
        String target_type = vo.getTarget_type();
        String dept_code = vo.getDept_code();
        String user_code = vo.getUser_code();
        ConstantSet constantSet = new ConstantSet();
        TargetConstant tc=new TargetConstant();
        String isExistFlag = tc.judgeReportIsExsit(current_year,
            current_month, current_week, target_type);
        System.out.println("The isExistFlag is------" + isExistFlag);
        if ("1".equals(isExistFlag)) {
            throw new DefaultException("您已经填写了该项，不能再新增了，可选择编辑该项！");
        }
        //设置审核审批人
        String auditorid = myForm.getAuditorid(); //获取审核人id
        String checkerid = myForm.getCheckerid(); //获取审批人id
        vo.setAuditor(auditorid);
        vo.setChecker(checkerid);
        //默认将审核审批标志置为0
        vo.setAudit_flag("0");
        vo.setCheck_flag("0");

        //设置华表内容属性
        vo.setTarget_content(Codes.decode(myForm.getCellcontent().toCharArray()));
        try {
            cfg.getBaseDao().insert(vo);
            //super.doInsert(cfg, isBack);
        }
        catch (DefaultException e) {
            e.printStackTrace();
            throw new DefaultException("您已经填写了该项，不能再新增了，可选择编辑该项！");
        }
        //给审核审批人发送短消息
        MessageDao messageDao = MessageFactory.getInstance().createMessageDao(
            Constant.DATA_SOURCE);
        String userCode = vo.getUser_code(); //发送人是创建人
        String sendTime = DateTimeUtils.getCurrentDateTime();
        String auditorContent = "您好!报告管理中有您需要审核的"+myForm.getTargetTypeName()+"!";
        String checkerContent = "您好!报告管理中有您需要审批的"+myForm.getTargetTypeName()+"!";
        String receiver1 = vo.getAuditor();
        String receiver2 = vo.getChecker();
        String webUrl = "/dailyoffice/targetmng/listmonthtarget.do";
        messageDao.sendMessage(userCode, sendTime, auditorContent, receiver1,
                               webUrl);
        messageDao.sendMessage(userCode, sendTime, checkerContent, receiver2,
                               webUrl);
        String retStr = "";
        if (isBack == true) {
            retStr = this.FORWORD_LIST_PAGE;
        }
        else {
            cfg.getBaseForm().setActionType(this.MODIFY_ACTION);
            this.makeCellField(cfg);
            retStr = this.FORWORD_EDIT_PAGE;
        }
        return retStr;

    }

    /**
     *
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @throws Exception
     * @return String
     */
    protected String toEdit(GoaActionConfig cfg) throws DefaultException,
        Exception {
        super.toEdit(cfg);
        OrganizationUtil util = new OrganizationUtil(); //系统管理工具类

        HttpServletRequest request = cfg.getRequest();
        TargetForm myForm = (TargetForm) cfg.getBaseForm();
        TargetVO targetVo = (TargetVO) cfg.getBaseForm().getVo();

        //初始化用户
        UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
        User user = new DefaultUser();

        String actionType = request.getParameter("actionType");
        System.out.println("The actionTye is----------" + actionType);
        //修改标志属性
        String modifyFlag = request.getParameter("flag");
        String userId = cfg.getBaseForm().getUserId();

        /**
         * 设置页面属性
         */

        //根据当前用户名得到当前用户所在部门ID和部门名字

        if (!StringUtils.isNull(targetVo.getUser_code())) {
            userId = targetVo.getUser_code(); //如果为修改
        }
        //设置VO中user_code属性
        targetVo.setUser_code(userId);
        String deptCodeOfCurUser = util.getDepartmentIDsByUser(Integer.parseInt(
            userId)).replaceAll(",", "");
        System.out.println("The current department is----" + deptCodeOfCurUser);
        System.out.println("The department length is----" + deptCodeOfCurUser.length());
        String deptNameOfCurUser = util.getDepartmentNamesByUser(Integer.
            parseInt(userId));
        //设置部门名称
        myForm.setDeptname(deptNameOfCurUser);
        //设置VO中dept_code属性
        targetVo.setDept_code(deptCodeOfCurUser);

        //设置用户名称
        user = userDao.findUserByID(Integer.parseInt(userId));
        String userName = user.getName();
        System.out.println("The make person is:-----" + userName);
        myForm.setUsername(userName); //设置

        //设置公司名称
        ConstantSet cs = new ConstantSet();
        myForm.setUnitname(cs.getUnitName());

        //模板设置
        TargetConstant tc = new TargetConstant();
        IsStandardModelDao isStandardModelDao = IsCustomerFactory.
            getInstance().createIsStandardModel(super.dbData);
        IsStandardModelVO ismVO = (IsStandardModelVO) isStandardModelDao.
            getTemplate(ConstantSet.ReportTargetTemplate,myForm.getTargetTypeName());
        myForm.setReportTemplateName(ismVO.getFile_path()); //设置模板路径

        //设置当前周，月，年
        String curDate = DateTimeUtils.getCurrentDate(); //当前日期
        String curYear = TargetConstant.getCurDate(myForm.getTargetTypeName()).
            substring(0, 4);
        String curMonth = TargetConstant.getCurDate(myForm.getTargetTypeName()).
            substring(5, 7);
        String curWeek = TargetConstant.getCurDate(myForm.getTargetTypeName()).
            substring(8, 9);
        if (!StringUtils.isNull(targetVo.getCurrent_year())) {
            curYear = targetVo.getCurrent_year();
        }
        if (!StringUtils.isNull(targetVo.getCurrent_month())) {
            curMonth = targetVo.getCurrent_month();
        }
        if (!StringUtils.isNull(targetVo.getCurrent_work())) {
            curWeek = targetVo.getCurrent_work();
        }
        //设置VO中current_year,current_month,current_week属性
        targetVo.setCurrent_year(curYear);
        targetVo.setCurrent_month(curMonth);
        targetVo.setCurrent_work(curWeek);
        System.out.println("The current year is:----" + curYear);
        System.out.println("The current Month is:----" + curMonth);
        System.out.println("The current week is:---" + curWeek);

        //编制日期
        String createDate = DateTimeUtils.getCurrentDate();
        if (!StringUtils.isNull(targetVo.getCheck_date())) {
            createDate = targetVo.getCheck_date();
        }
        //设置vo中crate_date属性
        targetVo.setCreate_date(createDate);

        //设置审核人，审批人
        String auditor = "";
        String auditorCode = "";
        String checker = "";
        String checkerCode = "";
        //设置审核人
        if (!StringUtils.isNull(targetVo.getAuditor())) {
            auditorCode = targetVo.getAuditor();
            if (util.checkUser(Integer.parseInt(auditorCode))) {
                user = userDao.findUserByID(Integer.parseInt(auditorCode));
                auditor = user.getName();
                myForm.setAuditorname(auditor);
                myForm.setAuditorid(auditorCode);
            }
            else {
                myForm.setAuditorname(auditor); //否则为空
                myForm.setAuditorid(auditorCode);
            }
        }
        //设置VO,form中auditor属性,
        targetVo.setAuditor(auditorCode);
        myForm.setAuditorname(auditor);
        myForm.setAuditorid(auditorCode);
        //设置审批人
        if (!StringUtils.isNull(targetVo.getChecker())) {
            checkerCode = targetVo.getChecker();
            if (util.checkUser(Integer.parseInt(checkerCode))) {
                user = userDao.findUserByID(Integer.parseInt(checkerCode));
                checker = user.getName();
                myForm.setCheckername(checker);
                myForm.setCheckerid(checkerCode);
            }
            else {
                myForm.setCheckername(checker); //否则设置为空
                myForm.setCheckerid(checkerCode);
            }
        }
        //设置VO,form中的checker属性
        targetVo.setChecker(checkerCode);
        myForm.setCheckername(checker);
        myForm.setCheckerid(checkerCode);

        /**
         * 设置权限
         */
        String isReadOnlyTemplateFlag = "1"; //华表只读标志位
        String editFlag = "1"; //修改标志位
        String auditFlag = "0"; //审核属性
        String checkFlag = "0"; //审批属性
        String audit_flag = "0"; //审核标志位
        String check_flag = "0"; //审批标志位
        if (this.MODIFY_ACTION.equals(actionType)) {
            String curUserId = cfg.getBaseForm().getUserId();
            auditFlag = targetVo.getAudit_flag();
            checkFlag = targetVo.getCheck_flag();
            //判断华表是否位只读
            isReadOnlyTemplateFlag = String.valueOf(ConstantSet.
                judgeReadOnlyTemplateTag(curUserId,
                                         userId, checkFlag, checkerCode,
                                         auditFlag, auditorCode));
            //判断editFlag标志位
            editFlag = String.valueOf(ConstantSet.judgeEditTag(curUserId, userId,
                auditFlag));

            //如果是修改动作则设置审核审批标志为默认值
            if (!"modify".equals(modifyFlag)) {
                //判断审核标志位
                audit_flag = String.valueOf(ConstantSet.judgeAuditTag(curUserId,
                    auditorCode, auditFlag,
                    checkFlag));
                System.out.println("The curUser is:----" + curUserId);
                System.out.println("The curAuditor is:---" + auditor);
                //判断审批标志位
                check_flag = String.valueOf(ConstantSet.judgeCheckTag(curUserId,
                    checkerCode, auditFlag,
                    checkFlag));
            }
            //创建临时文件
            makeCellField(cfg);
        }

        //设置isReadOnlyTemplateFlag属性
        myForm.setIsReadOnlyTemplateFlag(isReadOnlyTemplateFlag);
        //设置auditFlag属性
        if ("1".equals(audit_flag)) {
            targetVo.setAudit_date(curDate);
        }
        myForm.setAuditFlag(audit_flag);
        System.out.println("The audit flag is:-------" + audit_flag);
        //设置checkFlag属性
        if ("1".equals(check_flag)) {
            targetVo.setCheck_date(curDate);
        }
        myForm.setCheckFlag(check_flag);

        //设置修改标志位
        myForm.setEditFlag(editFlag);
        System.out.println("The editFlag is:-----" + editFlag);
        //设置目标类型属性
        if (!this.MODIFY_ACTION.equals(actionType)) {
                targetVo.setTarget_type(String.valueOf(myForm.getTargetType()));
                cfg.getBaseForm().setActionType(this.ADD_ACTION);
        }
        else {
            cfg.getBaseForm().setActionType(this.MODIFY_ACTION);
            myForm.setTargetTypeName(targetVo.getTarget_type());
        }
        if (!StringUtils.isNull(myForm.getSaveExitFlag())) {
            cfg.getBaseForm().setActionType(this.MODIFY_ACTION);
        }

        System.out.println("The targetType is:" + myForm.getTargetType());
        return this.FORWORD_EDIT_PAGE;
    }

    /**
     * 创建临时文件以便查询
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @throws Exception
     */
    //
    private void makeCellField(GoaActionConfig cfg) throws DefaultException,
        Exception {
        TemplateUtils templateUtils = new TemplateUtils();
        TargetForm targetForm = (TargetForm) cfg.getBaseForm();
        TargetVO vo = (TargetVO) targetForm.getVo();
        String strPath = super.getServlet().getServletContext().getRealPath("/");
        String strFilename = "/cell/report/" +
            templateUtils.makeCellName(strPath + "/cell/report") + ".cll";
        targetForm.setCellname(strFilename);
        //targetForm.setReportTemplateName(strFilename);
        byte[] bcell = vo.getTarget_content();
        File file = new File(strPath + strFilename);
        OutputStream fos = new FileOutputStream(file);
        OutputStream os = new BufferedOutputStream(fos);
        os.write(bcell);
        os.close();
    }

    /**
     *
     * @param cfg GoaActionConfig
     * @param isBack boolean
     * @throws DefaultException
     * @throws Exception
     * @return String
     */
    protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
        DefaultException, Exception {
        TargetForm myForm = (TargetForm) cfg.getBaseForm();
        TargetVO vo = (TargetVO) myForm.getVo();

        //设置华表
        vo.setTarget_content(Codes.decode(myForm.getCellcontent().
                                          toCharArray()));
        String isCancel = myForm.getIsCancel();

        //判断用户是否取消审核或审批
        if ("1".equals(isCancel)) {
            vo.setAudit_flag("0");
            vo.setAudit_date("");
            vo.setAudit_opinion("");
        }
        if ("2".equals(isCancel)) {
            vo.setCheck_flag("0");
            vo.setCheck_date("");
            vo.setCheck_opinion("");
        }

        //如果用户没有点击取消审核或取消审批按钮
        if (! ("1".equals(isCancel) || "2".equals(isCancel))) {
            String auditorid = myForm.getAuditorid(); //获取审核人
            String checkerid = myForm.getCheckerid(); //获取审批人
            vo.setAuditor(auditorid);
            vo.setChecker(checkerid);
        }
        String retStr = "";
        super.doUpdate(cfg, isBack);
        if (isBack == true) {
            retStr = this.FORWORD_LIST_PAGE;
        }
        else {
            this.makeCellField(cfg);
            retStr = this.FORWORD_EDIT_PAGE;
        }
        return retStr;
    }

    /**
     *
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @throws Exception
     * @return String
     */
    protected String toView(GoaActionConfig cfg) throws
        DefaultException, Exception {
        super.toView(cfg);
        TargetForm myForm = (TargetForm) cfg.getBaseForm();
        TargetVO targetVo = (TargetVO) myForm.getVo();
        //初始化用户接口
        UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
        User user = new DefaultUser();
        OrganizationUtil util = new OrganizationUtil();

        //得到VO中属性值
        String userId = targetVo.getUser_code();
        String deptId = targetVo.getDept_code();
        String auditor = targetVo.getAuditor();
        String checker = targetVo.getChecker();
        String auditFlag = targetVo.getAudit_flag();
        String checkFlag = targetVo.getCheck_flag();
        //得到当前用户
        String curUserId = cfg.getBaseForm().getUserId();

        //设置formBean属性到页面
        //设置编制人
        if (!StringUtils.isNull(userId)) {
            user = userDao.findUserByID(Integer.parseInt(userId));
            myForm.setUsername(user.getName());
        }
        //设置审核人
        if (!StringUtils.isNull(auditor)) {
            //判断审核人是否存在
            if (util.checkUser(Integer.parseInt(auditor))) {
                user = userDao.findUserByID(Integer.parseInt(auditor));
                myForm.setAuditorname(user.getName());
            }
            else {
                myForm.setAuditorname("");
            }
        }
        //设置审批人
        if (!StringUtils.isNull(checker)) {
            //判断审批人是否存在
            if (util.checkUser(Integer.parseInt(checker))) {
                user = userDao.findUserByID(Integer.parseInt(checker));
                myForm.setCheckername(user.getName());
            }
            else {
                myForm.setCheckername("");
            }
        }
        //设置部门
        if (!StringUtils.isNull(deptId)) {
            Department dept = new DefaultDepartment();
            DepartmentDAO deptDao = OrganizationFactory.getInstance().
                getDepartmentDAO();
            dept = deptDao.findDepartmentByID(Integer.parseInt(deptId));
            myForm.setDeptname(dept.getName());
        }

        /**
         * 设置权限
         */
        //设置审核和审批权限
        int audit_check_flag = 0;
        audit_check_flag = ConstantSet.judgeEditTagForViewOfNew(curUserId, userId,
            checkFlag, checker, auditFlag, auditor);
        //设置formBean的属性
        myForm.setCreatorFlag(String.valueOf(audit_check_flag));

        //设置修改权限
        int modify_flag = 0;
        modify_flag = ConstantSet.judgeModify(curUserId, userId, auditor,
                                              checker, auditFlag,
                                              checkFlag);
        //设置formBean的属性
        myForm.setModifyFlag(String.valueOf(modify_flag));

        //设置取消审核审批权限
        int isCancel = 0;
        isCancel = ConstantSet.judgeIsCancel(curUserId, auditor, checker, auditFlag,
                                             checkFlag);
        myForm.setIsCancel(String.valueOf(isCancel));
        System.out.println("The curUserId is:---" + curUserId);
        System.out.println("The auditor is:-----" + auditor);
        System.out.println("The checker is:-----" + checker);
        System.out.println("The auditFlag is:----" + auditFlag);
        System.out.println("The checkFlag is:----" + checkFlag);
        System.out.println("The isCancel property is:----" + isCancel);

        ConstantSet cs = new ConstantSet();
        //设置公司名称
        myForm.setUnitname(cs.getUnitName());
        //创建临时华表
        makeCellField(cfg);

        //当报告为年度报告时的特殊处理
        if ("3".equals(myForm.getTypeFlag())) {
            myForm.setTargetTypeName(targetVo.getTarget_type());
        }
        return this.FORWORD_VIEW_PAGE;
    }

    /**
     *
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @throws Exception
     * @return String
     */
    public String doTreeData(GoaActionConfig cfg) throws DefaultException,
        Exception {
        TargetDao targetDao = (TargetDao) cfg.getBaseDao();
        TargetForm myForm = (TargetForm) cfg.getBaseForm();
        String nodeid = StringUtils.nullToString(myForm.getTypeFlagName());
        cfg.getRequest().setAttribute("treeData",
                                      targetDao.getTreeData(nodeid));
        return TREE_PAGE;

    }
}
