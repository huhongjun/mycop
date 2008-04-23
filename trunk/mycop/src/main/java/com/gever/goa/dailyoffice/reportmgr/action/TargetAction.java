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
 * <p>Title:Ŀ������������� </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class TargetAction extends BaseAction {
    public TargetAction() {
    }

    /**
     * ��ʼ��ҳ������
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

        //���ݲ�ͬ��Ŀ�����ͳ�ʼ����ͬ��ҳ��
        this.initPage(cfg);

    }

    /**
     * ��ʼ��ҳ��
     * @param cfg GoaActionConfig
     */
    private void initPage(GoaActionConfig cfg) {
        TargetForm myForm = (TargetForm) cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();
        TargetConstant tc = new TargetConstant(); //������
        String targetType = request.getParameter("targetType"); //�õ�Ŀ������
        System.out.println("The targetType is======:" + targetType);
        String nodeid = request.getParameter("nodeid"); //��������
        System.out.println("The node is=======" + nodeid);
        /**
         * ����Ŀ�����ͣ�����Ӧ������
         */
        if (!StringUtils.isNull(targetType)) {
            myForm.setTypeFlag(tc.getTargetType(targetType)); //���ñ������ͱ�־
            myForm.setTypeFlagName(tc.getTypeFlagName(targetType)); //���õ�һ��Ŀ¼����
            System.out.println("The typeFlagName is====" + myForm.getTypeFlagName());
            myForm.setTargetTypeName(tc.getTypeFlagName(targetType));
        }
        else if (!StringUtils.isNull(nodeid)) {
            myForm.setTargetType(nodeid); //���ñ�������
            myForm.setTargetTypeName(nodeid); //���ñ�����������
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
        OrganizationUtil systemMngUtil = new OrganizationUtil(); //������

        String deptCode = request.getParameter("nodeid"); //�õ�����ID
        String queryFlag = request.getParameter("queryFlag"); ; //�õ��Ƿ��ѯ��־
        String curYear = DateTimeUtils.getCurrentDate().substring(0, 4); //�õ���ǰ���

        String deptName = "";
        String curUserId = cfg.getBaseForm().getUserId(); //�õ���ǰ�û�ID
        ConstantSet.CurUserID = curUserId; //����ConstantSet userId
        StringBuffer sbSqlWhere = new StringBuffer();

        //�ж����nodiidΪ�գ�����ID����Ϊ��ǰ�û����ڲ���ID
        if (StringUtils.isNull(deptCode)) {
            deptCode = cfg.getBaseForm().getCurDeptCodes();
            System.out.println("----the current depe is-----" + deptCode);
        }

        //���ò�������
        deptName = systemMngUtil.getDeptNameByDeptID(deptCode);
        myForm.setDeptname(deptName);

        /**
         * �ж����queryFlagΪtrue���ʾ��ѯ����
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
            if (myForm.getTypeFlag().equals("2") || myForm.getTypeFlag().equals("1")) { //���ݱ���������ȷ������
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
            if (myForm.getTypeFlag().equals("2") || myForm.getTypeFlag().equals("1")) { //���ݱ���������ȷ������
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

        //�����ж��û���д�������Ƿ��Ѿ����������ݿ�����
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
            throw new DefaultException("���Ѿ���д�˸�������������ˣ���ѡ��༭���");
        }
        //�������������
        String auditorid = myForm.getAuditorid(); //��ȡ�����id
        String checkerid = myForm.getCheckerid(); //��ȡ������id
        vo.setAuditor(auditorid);
        vo.setChecker(checkerid);
        //Ĭ�Ͻ����������־��Ϊ0
        vo.setAudit_flag("0");
        vo.setCheck_flag("0");

        //���û�����������
        vo.setTarget_content(Codes.decode(myForm.getCellcontent().toCharArray()));
        try {
            cfg.getBaseDao().insert(vo);
            //super.doInsert(cfg, isBack);
        }
        catch (DefaultException e) {
            e.printStackTrace();
            throw new DefaultException("���Ѿ���д�˸�������������ˣ���ѡ��༭���");
        }
        //����������˷��Ͷ���Ϣ
        MessageDao messageDao = MessageFactory.getInstance().createMessageDao(
            Constant.DATA_SOURCE);
        String userCode = vo.getUser_code(); //�������Ǵ�����
        String sendTime = DateTimeUtils.getCurrentDateTime();
        String auditorContent = "����!���������������Ҫ��˵�"+myForm.getTargetTypeName()+"!";
        String checkerContent = "����!���������������Ҫ������"+myForm.getTargetTypeName()+"!";
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
        OrganizationUtil util = new OrganizationUtil(); //ϵͳ��������

        HttpServletRequest request = cfg.getRequest();
        TargetForm myForm = (TargetForm) cfg.getBaseForm();
        TargetVO targetVo = (TargetVO) cfg.getBaseForm().getVo();

        //��ʼ���û�
        UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
        User user = new DefaultUser();

        String actionType = request.getParameter("actionType");
        System.out.println("The actionTye is----------" + actionType);
        //�޸ı�־����
        String modifyFlag = request.getParameter("flag");
        String userId = cfg.getBaseForm().getUserId();

        /**
         * ����ҳ������
         */

        //���ݵ�ǰ�û����õ���ǰ�û����ڲ���ID�Ͳ�������

        if (!StringUtils.isNull(targetVo.getUser_code())) {
            userId = targetVo.getUser_code(); //���Ϊ�޸�
        }
        //����VO��user_code����
        targetVo.setUser_code(userId);
        String deptCodeOfCurUser = util.getDepartmentIDsByUser(Integer.parseInt(
            userId)).replaceAll(",", "");
        System.out.println("The current department is----" + deptCodeOfCurUser);
        System.out.println("The department length is----" + deptCodeOfCurUser.length());
        String deptNameOfCurUser = util.getDepartmentNamesByUser(Integer.
            parseInt(userId));
        //���ò�������
        myForm.setDeptname(deptNameOfCurUser);
        //����VO��dept_code����
        targetVo.setDept_code(deptCodeOfCurUser);

        //�����û�����
        user = userDao.findUserByID(Integer.parseInt(userId));
        String userName = user.getName();
        System.out.println("The make person is:-----" + userName);
        myForm.setUsername(userName); //����

        //���ù�˾����
        ConstantSet cs = new ConstantSet();
        myForm.setUnitname(cs.getUnitName());

        //ģ������
        TargetConstant tc = new TargetConstant();
        IsStandardModelDao isStandardModelDao = IsCustomerFactory.
            getInstance().createIsStandardModel(super.dbData);
        IsStandardModelVO ismVO = (IsStandardModelVO) isStandardModelDao.
            getTemplate(ConstantSet.ReportTargetTemplate,myForm.getTargetTypeName());
        myForm.setReportTemplateName(ismVO.getFile_path()); //����ģ��·��

        //���õ�ǰ�ܣ��£���
        String curDate = DateTimeUtils.getCurrentDate(); //��ǰ����
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
        //����VO��current_year,current_month,current_week����
        targetVo.setCurrent_year(curYear);
        targetVo.setCurrent_month(curMonth);
        targetVo.setCurrent_work(curWeek);
        System.out.println("The current year is:----" + curYear);
        System.out.println("The current Month is:----" + curMonth);
        System.out.println("The current week is:---" + curWeek);

        //��������
        String createDate = DateTimeUtils.getCurrentDate();
        if (!StringUtils.isNull(targetVo.getCheck_date())) {
            createDate = targetVo.getCheck_date();
        }
        //����vo��crate_date����
        targetVo.setCreate_date(createDate);

        //��������ˣ�������
        String auditor = "";
        String auditorCode = "";
        String checker = "";
        String checkerCode = "";
        //���������
        if (!StringUtils.isNull(targetVo.getAuditor())) {
            auditorCode = targetVo.getAuditor();
            if (util.checkUser(Integer.parseInt(auditorCode))) {
                user = userDao.findUserByID(Integer.parseInt(auditorCode));
                auditor = user.getName();
                myForm.setAuditorname(auditor);
                myForm.setAuditorid(auditorCode);
            }
            else {
                myForm.setAuditorname(auditor); //����Ϊ��
                myForm.setAuditorid(auditorCode);
            }
        }
        //����VO,form��auditor����,
        targetVo.setAuditor(auditorCode);
        myForm.setAuditorname(auditor);
        myForm.setAuditorid(auditorCode);
        //����������
        if (!StringUtils.isNull(targetVo.getChecker())) {
            checkerCode = targetVo.getChecker();
            if (util.checkUser(Integer.parseInt(checkerCode))) {
                user = userDao.findUserByID(Integer.parseInt(checkerCode));
                checker = user.getName();
                myForm.setCheckername(checker);
                myForm.setCheckerid(checkerCode);
            }
            else {
                myForm.setCheckername(checker); //��������Ϊ��
                myForm.setCheckerid(checkerCode);
            }
        }
        //����VO,form�е�checker����
        targetVo.setChecker(checkerCode);
        myForm.setCheckername(checker);
        myForm.setCheckerid(checkerCode);

        /**
         * ����Ȩ��
         */
        String isReadOnlyTemplateFlag = "1"; //����ֻ����־λ
        String editFlag = "1"; //�޸ı�־λ
        String auditFlag = "0"; //�������
        String checkFlag = "0"; //��������
        String audit_flag = "0"; //��˱�־λ
        String check_flag = "0"; //������־λ
        if (this.MODIFY_ACTION.equals(actionType)) {
            String curUserId = cfg.getBaseForm().getUserId();
            auditFlag = targetVo.getAudit_flag();
            checkFlag = targetVo.getCheck_flag();
            //�жϻ����Ƿ�λֻ��
            isReadOnlyTemplateFlag = String.valueOf(ConstantSet.
                judgeReadOnlyTemplateTag(curUserId,
                                         userId, checkFlag, checkerCode,
                                         auditFlag, auditorCode));
            //�ж�editFlag��־λ
            editFlag = String.valueOf(ConstantSet.judgeEditTag(curUserId, userId,
                auditFlag));

            //������޸Ķ������������������־ΪĬ��ֵ
            if (!"modify".equals(modifyFlag)) {
                //�ж���˱�־λ
                audit_flag = String.valueOf(ConstantSet.judgeAuditTag(curUserId,
                    auditorCode, auditFlag,
                    checkFlag));
                System.out.println("The curUser is:----" + curUserId);
                System.out.println("The curAuditor is:---" + auditor);
                //�ж�������־λ
                check_flag = String.valueOf(ConstantSet.judgeCheckTag(curUserId,
                    checkerCode, auditFlag,
                    checkFlag));
            }
            //������ʱ�ļ�
            makeCellField(cfg);
        }

        //����isReadOnlyTemplateFlag����
        myForm.setIsReadOnlyTemplateFlag(isReadOnlyTemplateFlag);
        //����auditFlag����
        if ("1".equals(audit_flag)) {
            targetVo.setAudit_date(curDate);
        }
        myForm.setAuditFlag(audit_flag);
        System.out.println("The audit flag is:-------" + audit_flag);
        //����checkFlag����
        if ("1".equals(check_flag)) {
            targetVo.setCheck_date(curDate);
        }
        myForm.setCheckFlag(check_flag);

        //�����޸ı�־λ
        myForm.setEditFlag(editFlag);
        System.out.println("The editFlag is:-----" + editFlag);
        //����Ŀ����������
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
     * ������ʱ�ļ��Ա��ѯ
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

        //���û���
        vo.setTarget_content(Codes.decode(myForm.getCellcontent().
                                          toCharArray()));
        String isCancel = myForm.getIsCancel();

        //�ж��û��Ƿ�ȡ����˻�����
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

        //����û�û�е��ȡ����˻�ȡ��������ť
        if (! ("1".equals(isCancel) || "2".equals(isCancel))) {
            String auditorid = myForm.getAuditorid(); //��ȡ�����
            String checkerid = myForm.getCheckerid(); //��ȡ������
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
        //��ʼ���û��ӿ�
        UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
        User user = new DefaultUser();
        OrganizationUtil util = new OrganizationUtil();

        //�õ�VO������ֵ
        String userId = targetVo.getUser_code();
        String deptId = targetVo.getDept_code();
        String auditor = targetVo.getAuditor();
        String checker = targetVo.getChecker();
        String auditFlag = targetVo.getAudit_flag();
        String checkFlag = targetVo.getCheck_flag();
        //�õ���ǰ�û�
        String curUserId = cfg.getBaseForm().getUserId();

        //����formBean���Ե�ҳ��
        //���ñ�����
        if (!StringUtils.isNull(userId)) {
            user = userDao.findUserByID(Integer.parseInt(userId));
            myForm.setUsername(user.getName());
        }
        //���������
        if (!StringUtils.isNull(auditor)) {
            //�ж�������Ƿ����
            if (util.checkUser(Integer.parseInt(auditor))) {
                user = userDao.findUserByID(Integer.parseInt(auditor));
                myForm.setAuditorname(user.getName());
            }
            else {
                myForm.setAuditorname("");
            }
        }
        //����������
        if (!StringUtils.isNull(checker)) {
            //�ж��������Ƿ����
            if (util.checkUser(Integer.parseInt(checker))) {
                user = userDao.findUserByID(Integer.parseInt(checker));
                myForm.setCheckername(user.getName());
            }
            else {
                myForm.setCheckername("");
            }
        }
        //���ò���
        if (!StringUtils.isNull(deptId)) {
            Department dept = new DefaultDepartment();
            DepartmentDAO deptDao = OrganizationFactory.getInstance().
                getDepartmentDAO();
            dept = deptDao.findDepartmentByID(Integer.parseInt(deptId));
            myForm.setDeptname(dept.getName());
        }

        /**
         * ����Ȩ��
         */
        //������˺�����Ȩ��
        int audit_check_flag = 0;
        audit_check_flag = ConstantSet.judgeEditTagForViewOfNew(curUserId, userId,
            checkFlag, checker, auditFlag, auditor);
        //����formBean������
        myForm.setCreatorFlag(String.valueOf(audit_check_flag));

        //�����޸�Ȩ��
        int modify_flag = 0;
        modify_flag = ConstantSet.judgeModify(curUserId, userId, auditor,
                                              checker, auditFlag,
                                              checkFlag);
        //����formBean������
        myForm.setModifyFlag(String.valueOf(modify_flag));

        //����ȡ���������Ȩ��
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
        //���ù�˾����
        myForm.setUnitname(cs.getUnitName());
        //������ʱ����
        makeCellField(cfg);

        //������Ϊ��ȱ���ʱ�����⴦��
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
