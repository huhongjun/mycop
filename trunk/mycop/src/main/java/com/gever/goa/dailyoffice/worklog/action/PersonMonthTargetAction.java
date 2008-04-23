package com.gever.goa.dailyoffice.worklog.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.message.dao.MessageDao;
import com.gever.goa.dailyoffice.message.dao.MessageFactory;
import com.gever.goa.dailyoffice.worklog.dao.PersonMonthTargetDao;
import com.gever.goa.dailyoffice.worklog.dao.PersonMonthTargetFactory;
import com.gever.goa.dailyoffice.worklog.vo.PersonTargetVO;
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
import com.gever.util.SumUtils;

/**
 * Title: ����Ŀ�������
 * Description: ����Ŀ�������
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */

public class PersonMonthTargetAction
    extends BaseAction {
  PersonMonthTargetDao personmonthtargetDao = null; //����ӿ�
  public PersonMonthTargetAction() {
  }

  /**
   * ��ʼ��ҳ������
   * @param cfg ��ǰ��vo����
   * @throws DefaultException
   * @throws Exception
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {
    PersonMonthTargetForm myForm = (PersonMonthTargetForm) cfg.getBaseForm(); //�õ�form����
    personmonthtargetDao = PersonMonthTargetFactory.getInstance().
        createPersonMonthTarget(super.dbData); //�õ����Ӧdao��ʵ��
    cfg.setBaseDao( (BaseDAO) personmonthtargetDao); //cfg--Goa Action��ȡ��--���ó����е�Dao
//�ݴ���,��ֹvo����Ϊnull
    if (myForm.getVo() == null) {
      myForm.setVo(new PersonTargetVO()); //����VO��Form��
    }
  }

  /**
   * ���嵥ҳ��--����������BaseAction�е�toList����
   * �б�����--��ȡ��Щ��ǰ���(������ǲ�ѯ��������)
   * ����Ϊ4-���Ŀ�����Щ���Ŀ��
   * @param cfg ��ǰAction���������
   * @return forward��ַ
   * @throws DefaultException
   * @throws Exception
   */

  protected String toList(GoaActionConfig cfg) throws DefaultException,
      Exception {
    HttpServletRequest request = cfg.getRequest();
    HttpSession session = cfg.getSession();
    OrganizationUtil systemMngUtil = new OrganizationUtil();
    PersonMonthTargetForm myForm = (PersonMonthTargetForm) cfg.getBaseForm(); //�õ�form����
    String leftDeptType = request.getParameter("nodeid"); //��ȡ���������Ĳ���id
    //String curDept = request.getParameter("nodeid");
    String queryFlag = request.getParameter("queryFlag");
    session.setAttribute("curDept", leftDeptType);
    String user_dev = (String) session.getAttribute(Constant.USER_FILTER); //�õ�ӵ������
    String deptIdOfForm = myForm.getDeptnodeid();
    String curUserCode=myForm.getUserId();//�õ���ǰ�û�ID
    if (!StringUtils.isNull(leftDeptType)) {
      myForm.setDeptnodeid(leftDeptType);
      myForm.setSearchUserName("");
      myForm.setSearchYear("");
      myForm.setSearchMonth("");
      myForm.setViewFlag("1");
    }
    else { //������������������������,��ô�ж��Ƿ����޸Ļ�鿴����ʱ�����
      if (StringUtils.isNull(deptIdOfForm)) { //������Ǵ��޸Ļ�鿴ҳ�淵��
        myForm.setDeptnodeid("");
        //��������
      }
      else { //����Ǵ��޸Ļ�鿴ҳ�淵����Ҫ������id����һ��
        deptIdOfForm = myForm.getDeptnodeid();
        myForm.setDeptnodeid(deptIdOfForm);
        leftDeptType = deptIdOfForm;
      }
      myForm.setViewFlag("0");
    }
    String deptOfCurUser = "";
    if (StringUtils.isNull(myForm.getDeptnodeid())
        && StringUtils.isNull(leftDeptType)) { //�����ǰ����Ϊ�յĻ�Ĭ��ʹ�õ�ǰ����
      String curDeptCodes = cfg.getBaseForm().getCurDeptCodes();
      //������ʱֻ��ȡ��ǰ�û����ڵ�һ�����ţ���Ϊ���ṹ��������ֻ��һ�����Ŷ���
      StringTokenizer st = new StringTokenizer(curDeptCodes, " ");
      if (st.hasMoreTokens()) {
        deptOfCurUser = st.nextToken(); //��ȡ��ǰ�û���Ӧ�Ĳ���-����ֻȡ��һ��
      }
      myForm.setDeptnodeid(deptOfCurUser); //����ǰ�����ڲ�����Ϊ���������Ĳ���Id
    }
    String curYear = DateTimeUtils.getCurrentDate().substring(0, 4); //��ȡ��ǰ���
    String curMonth = DateTimeUtils.getCurrentDate().substring(5, 7); //��ȡ��ǰ�·�
    curMonth = isMonthLessTen(curMonth);
    String curUser = cfg.getBaseForm().getUserId();
    ConstantSet.CurUserID = curUser;
    StringBuffer sbSqlWhere = new StringBuffer();
    String curListType = myForm.getDeptnodeid(); //��ȡ��ǰ���������Ĳ���id-ʼ������ֵ��
    //�õ���ǰ�����µ������Ӳ���
    String tempDeptId = cfg.getBaseForm().getCurDeptCodes();
    System.out.println("The depteCode is:-----"+tempDeptId);

    /**
     * �����ǰ���Ƕಿ��
     */
    String curSonDept="";
    StringTokenizer stDept =new StringTokenizer(tempDeptId);
    while (stDept.hasMoreTokens()) {
      String curDeptId=stDept.nextToken();
      System.out.println("----------The curDeptId is:--------" + curDeptId);
      curSonDept=curSonDept+ systemMngUtil.getDepartmentSonIdById(curDeptId);
    }
    System.out.println("----------The curSonDept is:--------" + curSonDept);
    if(!StringUtils.isNull(tempDeptId)){
      tempDeptId = tempDeptId.substring(0, 4) + ","; //����ಿ����ȡ��һ������
    }
    if ("true".equals(queryFlag)) {
      String searchYear = ( (PersonMonthTargetForm) cfg.getBaseForm()).
          getSearchYear();
      String searchMonth = ( (PersonMonthTargetForm) cfg.getBaseForm()).
          getSearchMonth();
      String searchUserName = ( (PersonMonthTargetForm) cfg.getBaseForm()).
          getSearchUserName();
      searchUserName = StringUtils.replaceText(searchUserName);
      if(!StringUtils.isNull(user_dev) && !StringUtils.isNull(tempDeptId)){
        if (Integer.parseInt(user_dev) <=3) {
          sbSqlWhere.append(" and (").append(user_dev).append("<b.level"); //ֻ��ʾ�û�����С�ڵ��ڵ�ǰ�û���
          sbSqlWhere.append(" or user_code=").append(curUserCode).append(")");
        }
        else {
          StringTokenizer st = new StringTokenizer(curSonDept, ",");
          int stCount = st.countTokens();
          sbSqlWhere.append("and (");
          while (st.hasMoreTokens()) {
            sbSqlWhere.append(" dept_code like'%").append(st.nextToken() + ",");
            sbSqlWhere.append("%'");
            if (st.hasMoreTokens()) {
              sbSqlWhere.append(" or ");
            }
          }
          //���û���Ӳ���
          if (stCount > 0) {
            sbSqlWhere.append(" or ");
          }
          sbSqlWhere.append(" dept_code like '%").append(tempDeptId).append("%')");
          sbSqlWhere.append(" and (").append(user_dev).append("<b.level");
          sbSqlWhere.append(" or user_code=").append(curUserCode).append(")");
        }
      }else{
        sbSqlWhere.append(" and 1 <> 1");
      }

      sbSqlWhere.append(" and CURRENT_YEAR like '%" + searchYear +
                        "%' ");
      sbSqlWhere.append(" and CURRENT_MONTH like '%" + searchMonth + "%' ");
      if (!StringUtils.isNull(searchUserName)) { //��������˲�ѯ�û��Ļ���Ӹ�����
        String tempSearchUserCodes = "";
        String searchUserCodes = "";
        tempSearchUserCodes = systemMngUtil.getUserIdsByUserName(
            searchUserName);
        int tempSearchUserCodesSize = tempSearchUserCodes.length();
        if (!StringUtils.isNull(tempSearchUserCodes)) { //��Ϊ��ʱ�Ž�ȡ,��������
          searchUserCodes = tempSearchUserCodes.substring(0,
              tempSearchUserCodesSize - 1);
        }
        else {
          searchUserCodes = "";
        }
        if (!StringUtils.isNull(searchUserCodes)) {
          sbSqlWhere.append(" and USER_CODE in (" + searchUserCodes + ")");
        }
        else {
          sbSqlWhere.append(" and USER_CODE in (-1) ");
        }
      }
      sbSqlWhere.append(" and TARGET_TYPE=" +
                        Integer.parseInt(ConstantSet.PersonMonthTargetType) +
                        " ");
      if (!this.isOracle()) {
        sbSqlWhere.append(" and CURRENT_MONTH<>'' and CURRENT_WORK='' ");
      }
      else {
        sbSqlWhere.append(
            " and CURRENT_MONTH IS NOT NULL and CURRENT_WORK IS NULL ");
      }
      sbSqlWhere.append(" and ','||DEPT_CODE||',' like '%" + curListType + "," +
                        "%' ");
      //sbSqlWhere.append(" order by  CURRENT_MONTH DESC ");
    }
    else {
      if (StringUtils.isNull(leftDeptType) == false) {
        if(StringUtils.isNull(tempDeptId)){
          sbSqlWhere.append(" and 1<>1 ");
        }
        sbSqlWhere.append(" and (").append(user_dev).append("<b.level"); //ֻ��ʾ�û�����С�ڵ��ڵ�ǰ�û���
        sbSqlWhere.append(" or user_code= ").append(curUserCode).append(")");
        sbSqlWhere.append(" and CURRENT_YEAR = '" + curYear + "' ");
        //sbSqlWhere.append(" and CURRENT_MONTH = '" + curMonth + "' ");
        sbSqlWhere.append(" and TARGET_TYPE=" +
                          Integer.parseInt(ConstantSet.PersonMonthTargetType) +
                          " ");
        if (!this.isOracle()) {
          sbSqlWhere.append(" and CURRENT_MONTH<>'' and CURRENT_WORK='' ");
        }
        else {
          sbSqlWhere.append(
              " and CURRENT_MONTH IS NOT NULL and CURRENT_WORK IS NULL ");
        }

        sbSqlWhere.append(" and ','||DEPT_CODE||',' like '%" + curListType +
                          "," + "%' ");
        //sbSqlWhere.append(" order by  CURRENT_MONTH DESC ");
      }
      else { //�����û�д���������ʱĬ�ϲ�ѯ��ǰ���Լ���ǰ�����ڲ��ŵļ�¼
      if(!StringUtils.isNull(user_dev) && !StringUtils.isNull(tempDeptId)){
        if (Integer.parseInt(user_dev) <= 3) {
          sbSqlWhere.append(" and (").append(user_dev).append("< b.level"); //ֻ��ʾ�û�����С�ڵ��ڵ�ǰ�û���
          sbSqlWhere.append(" or user_code=").append(curUserCode).append(")");
        }
        else {
          StringTokenizer st = new StringTokenizer(curSonDept, ",");
          int stCount = st.countTokens();
          sbSqlWhere.append(" and (");
          while (st.hasMoreTokens()) {
            sbSqlWhere.append(" dept_code like'%").append(st.nextToken() + ",");
            sbSqlWhere.append("%'");
            if (st.hasMoreTokens()) {
              sbSqlWhere.append(" or");
            }
          }
          if (stCount > 0) {
            sbSqlWhere.append(" or");
          }
          sbSqlWhere.append(" dept_code like '%").append(tempDeptId).append(
              "%')");
          sbSqlWhere.append(" and (").append(user_dev).append("<b.level");
          sbSqlWhere.append(" or user_code=").append(curUserCode).append(")");
        }
      }else{
        sbSqlWhere.append(" and 1 <> 1");
      }
        sbSqlWhere.append("  and CURRENT_YEAR = '" + curYear + "' ");
        //sbSqlWhere.append(" and CURRENT_MONTH = '" + curMonth + "' ");
        sbSqlWhere.append(" and TARGET_TYPE=" +
                          Integer.parseInt(ConstantSet.PersonMonthTargetType) +
                          " ");
        if (!this.isOracle()) {
          sbSqlWhere.append(" and CURRENT_MONTH<>'' and CURRENT_WORK='' ");
        }
        else {
          sbSqlWhere.append(
              " and CURRENT_MONTH IS NOT NULL and CURRENT_WORK IS NULL ");
        }

        //sbSqlWhere.append(" and USER_CODE=" + Integer.parseInt(curUser) +
        //                  " ");
        //sbSqlWhere.append(" and ','||DEPT_CODE||',' like '%" + curListType +
        //                  "," + "%' ");
        //sbSqlWhere.append(" order by  CURRENT_MONTH DESC ");
      }
    }
    String deptNodeName = "";
    deptNodeName = systemMngUtil.getDeptNameByDeptID(myForm.getDeptnodeid());
    myForm.setDeptnodename(deptNodeName);

    cfg.getBaseForm().setSqlWhere(sbSqlWhere.toString());
    super.toList(cfg);
    return this.FORWORD_LIST_PAGE;
  }

  private String isMonthLessTen(String curMonth) {
    if ("0".equals(curMonth.substring(0, 1))) { //�¶�С��10ʱ
      curMonth = curMonth.substring(1, 2);
    }
    return curMonth;
  }

  /**
   * ���޸�ҳ��--����������BaseAction�е�toEdit����
   * �Ա��ʼ������ҳ���ϵ�һЩ�������ݣ��������룬ֻ���ȡ��
   * @param cfg ��ǰAction���������
   * @return forward��ַ
   * @throws DefaultException
   * @throws Exception
   */
  protected String toEdit(GoaActionConfig cfg) throws DefaultException,
      Exception {
    super.toEdit(cfg);
    HttpServletRequest request = cfg.getRequest();
    String actionType = SumUtils.nullToString(cfg.getRequest().getParameter(
        "actionType"));
    //�õ��޸ı�־
    String modifyFlag = request.getParameter("modifyflag");
    HttpSession session = cfg.getSession();
    User user = new DefaultUser();
    UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
    PersonMonthTargetForm myForm = (PersonMonthTargetForm) cfg.getBaseForm(); //�õ�form����
    if (StringUtils.isNull(myForm.getReportTemplateName())) { //�������ģ�廹û������
      IsStandardModelDao isStandardModelDao = IsCustomerFactory.getInstance().
          createIsStandardModel(super.dbData);
      IsStandardModelVO ismVO = (IsStandardModelVO) isStandardModelDao.
          getTemplate(ConstantSet.
                      PersonMonthTargetTemplate);
      myForm.setReportTemplateName(ismVO.getFile_path());
    }
    PersonTargetVO vo = (PersonTargetVO) cfg.getBaseForm().getVo();
    String curUser = vo.getUser_code(); //ȡ�õ�ǰ�û�ID
    if (StringUtils.isNull(curUser)) { //���Ϊ�մ���ʾ����
      curUser = cfg.getBaseForm().getUserId();
    }

    OrganizationUtil systemMngUtil = new OrganizationUtil();
    String curPostCode = systemMngUtil.getJobIDsByUser(Integer.parseInt(curUser));
    String curPostName = systemMngUtil.getJobNamesByUser(Integer.parseInt(
        curUser));
    myForm.setPostname(curPostName);
    String curDeptCode = (String) session.getAttribute("curDept");
    if ("".equals(curDeptCode) || curDeptCode == null) { //�����session�л�ȡ�ĵ�ǰ����IDΪ�����formbean�л�ȡ
      curDeptCode = myForm.getDeptnodeid();
    }
    //��ȡ���������Ĳ���
    if(!StringUtils.isNull(curDeptCode)){
      Department dept = new DefaultDepartment();
      DepartmentDAO deptDao = OrganizationFactory.getInstance().getDepartmentDAO();
      dept = deptDao.findDepartmentByID(Integer.parseInt(curDeptCode));
      String curDeptName = dept.getName();
      myForm.setDeptnodename(curDeptName); //���õ�ǰ����������--��Ӧ��ǰ�������Ĳ���
    }
    //��ȡ��ǰ�û���Ӧ�Ĳ�����������,�����ܶ�Ӧ�ಿ��
    String deptsCodeOfCurUser = systemMngUtil.getDepartmentIDsByUser(Integer.
        parseInt(curUser));
    String deptsNameOfCurUser = systemMngUtil.getDepartmentNamesByUser(Integer.
        parseInt(curUser));
    myForm.setDeptname(deptsNameOfCurUser);

    String curDate = vo.getCreate_date();
    if (StringUtils.isNull(curDate)) {
        curDate = DateTimeUtils.getCurrentDate(); //ȡ�õ�ǰ����--��Ӧ��������
    }

    String curYear = "";
    String curMonth = "";
    String curWeek = "";
    if (!this.MODIFY_ACTION.equals(actionType)) { //����ǵ�����ҳ��-��Ҫ����������
      curYear = DateTimeUtils.getCurrentDate().substring(0, 4); //��ȡ��ǰ���
      curMonth = DateTimeUtils.getCurrentDate().substring(5, 7); //��ȡ��ǰ�·�
      vo.setCurrent_year(curYear);
      vo.setCurrent_month(curMonth);
      vo.setCurrent_work(curWeek);
    }
    else { //����ǵ��޸�ҳ����ǰ����Ҫ�����ݿ��л�ȡ
      // System.out.println("modify");
      //���Բ���ʲô��������Ϊ���������ݿ����Զ���ȡvo
    }

    vo.setDept_code(deptsCodeOfCurUser); //ע�⣬����ʱ���Ƕ�Ӧ��ǰ�˵�
    vo.setPost_code(curPostCode);
    vo.setUser_code(curUser);
    vo.setCreate_date(curDate);
    vo.setTarget_type(ConstantSet.PersonMonthTargetType);
    String user_code = vo.getUser_code(); //��ȡ������
    //�õ����û����Ӧdao��ʵ��
    user = userDao.findUserByID(Integer.parseInt(vo.getUser_code()));
    String curName = user.getName();
    myForm.setUsername(curName); //���õ�ǰ�û��������������˻�--��Ӧ������

    int creatorFlag = 0;
    String auditor = vo.getAuditor(); //�������ˡ�
    String audit_flag = vo.getAudit_flag(); //���������־
    String curUseCode = cfg.getBaseForm().getUserId(); //��ǰ��
    System.out.println("The audit_falg is:----" + audit_flag);
    creatorFlag = ConstantSet.judgeCreatorTag(curUseCode, user_code, auditor,
                                              audit_flag);
    if (!curUseCode.equals(auditor)) {
      myForm.setCreatorFlag(String.valueOf(creatorFlag)); //�����޸�Ȩ��
    }
    else {
      myForm.setCreatorFlag("0");
    }

    // �õ������
    OrganizationUtil util = new OrganizationUtil();
    if (!StringUtils.isNull(auditor)) {
      int iAuditorid = Integer.parseInt(auditor);
      if (util.checkUser(iAuditorid)) {
        User userAuditor = new DefaultUser();
        userAuditor = userDao.findUserByID(iAuditorid);
        String auditorName = userAuditor.getName();
        myForm.setAuditorid(auditor); //��������һ��FormBean�е������ID���������ݿ��н�д���ֵ
        myForm.setAuditorname(auditorName); //�������ݿ��л�ȡ���������ʾ��ҳ��
      }
      else {
        myForm.setAuditorname("");
      }
    }
    else {
      myForm.setAuditorid(""); //������������������ϴε�FormBean�е�ֵ����Ϊ��
      myForm.setAuditorname("");
    }

    //�ж����Ȩ��
    int auditFlag = 0;
    System.out.println("The current user is:" + curUseCode);
    System.out.println("The auditor is :" + auditor);
    System.out.println("The audit_flag is:" + audit_flag);
    auditFlag = ConstantSet.judgeAuditFlag(curUseCode, auditor, audit_flag);
    if (!"true".equals(modifyFlag)) {
      myForm.setAuditFlag(String.valueOf(auditFlag));
    }
    else {
      myForm.setAuditFlag("0");
    }

    cfg.getBaseForm().setVo(vo);
    if ("modify".equals(actionType)) { //������޸Ĳ���
      makeCellField(cfg);
    }
    if (!this.MODIFY_ACTION.equals(actionType)) {
      cfg.getBaseForm().setActionType(super.ADD_ACTION);
    }
    return this.FORWORD_EDIT_PAGE;
  }

  /**
   * ��������--��������BaseAction����ķ�����ʵ�����ģ��Ĺ���
   * @param cfg ��ǰAction���������
   * @param isBack �Ƿ񷵻�
   * @return forward��ַ
   * @throws DefaultException
   * @throws Exception
   */

  protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
      DefaultException, Exception {
    PersonMonthTargetForm personmonthtargetForm = (PersonMonthTargetForm) cfg.
        getBaseForm();
    PersonTargetVO vo = (PersonTargetVO) personmonthtargetForm.getVo();
    vo.setTarget_content(Codes.decode(personmonthtargetForm.getCellcontent().
                                      toCharArray()));
    //�����ж��û���д�������Ƿ��Ѿ����������ݿ�����
    String current_year = vo.getCurrent_year();
    String current_month = vo.getCurrent_month();
    String current_week = vo.getCurrent_work();
    String target_type = vo.getTarget_type();
    String dept_code = vo.getDept_code();
    String user_code = vo.getUser_code();
    ConstantSet constantSet = new ConstantSet();
    String isExistFlag = constantSet.queryPersonTargetIsExist(current_year,
        current_month, current_week,
        target_type, dept_code, user_code);
    if ("1".equals(isExistFlag)) {
      throw new DefaultException("���Ѿ���д�˸�������������ˣ���ѡ��༭���");
    }
    //���������
    String auditor = personmonthtargetForm.getAuditorid();
    vo.setAuditor(auditor);
    //��˳�ʼֵ
    vo.setAudit_flag("0");
    String retVal = "";
    try {
      cfg.getBaseDao().insert(vo);
    }
    catch (DefaultException e) {
      throw new DefaultException("����Ŀ�����");
    }

    //����������˷��Ͷ���Ϣ
    MessageDao messageDao = MessageFactory.getInstance().
        createMessageDao(Constant.DATA_SOURCE);
    String userCode = vo.getUser_code(); //�������Ǵ�����
    String sendTime = DateTimeUtils.getCurrentDateTime();
    String auditorContent = "����!����Ҫ��˵�����Ŀ��!";
    String receiver1 = vo.getAuditor();
    String webUrl = "/dailyoffice/worklog/framepersonmonthtarget.jsp?fid="+vo.getSerial_num();
    messageDao.sendMessage(userCode, sendTime, auditorContent, receiver1,
                           webUrl,"ת������Ŀ�����");
    if (isBack == true) {
      retVal = this.FORWORD_LIST_PAGE;
    }
    else {
      cfg.getBaseForm().setActionType(this.MODIFY_ACTION);
      this.makeCellField(cfg);
      retVal = this.FORWORD_EDIT_PAGE;
    }
    return retVal;
  }

  /**
   * �޸Ķ���--��������BaseAction����ķ�����ʵ���޸�ģ��Ĺ���
   * @param cfg ��ǰAction���������
   * @param isBack �Ƿ񷵻�
   * @return forward��ַ
   * @throws DefaultException
   * @throws Exception
   */
  protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
      DefaultException,
      Exception {
    PersonMonthTargetForm personmonthtargetForm = (PersonMonthTargetForm) cfg.
        getBaseForm();
    PersonTargetVO vo = (PersonTargetVO) personmonthtargetForm.getVo();
    vo.setTarget_content(Codes.decode(personmonthtargetForm.getCellcontent().
                                      toCharArray()));
    //ȡ�����
    if ("1".equals(personmonthtargetForm.getIsCancel())) {
      vo.setAudit_flag("0");
      vo.setAudit_date("");
      vo.setAudit_opinion("");
    }
    String auditorId = "";
    auditorId = personmonthtargetForm.getAuditorid();
    vo.setAuditor(auditorId);
    super.doUpdate(cfg, isBack);
    String retVal = "";
    if (isBack == true) {
      retVal = this.FORWORD_LIST_PAGE;
    }
    else {
      this.makeCellField(cfg);
      retVal = this.FORWORD_EDIT_PAGE;
    }
    return retVal;
  }

  /**
   * ������ҳ��
   * @param cfg ��ǰAction���������
   * @return forward ��ַ
   * @throws DefaultException
   * @throws Exception
   */

  protected String toView(GoaActionConfig cfg) throws DefaultException,
      Exception {
    String forwardpath = super.toView(cfg);
    PersonMonthTargetForm myForm = (PersonMonthTargetForm) cfg.getBaseForm(); //�õ�form����
    PersonTargetVO vo = (PersonTargetVO) myForm.getVo();
    UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
    String curUser = cfg.getBaseForm().getUserId(); //ȡ�õ�ǰ�û�ID
    String usercode = vo.getUser_code(); //��ȡ������ID
    //��ȡ���������ڲ��ź͸�λ
    OrganizationUtil systemMngUtil = new OrganizationUtil();
    //String curPostCode = systemMngUtil.getJobIDsByUser(Integer.parseInt(curUser));
    String curPostName = systemMngUtil.getJobNamesByUser(Integer.parseInt(
        usercode));
    myForm.setPostname(curPostName);
    //��ȡ��ǰ�û���Ӧ�Ĳ�����������,�����ܶ�Ӧ�ಿ��
    //String deptsCodeOfCurUser = systemMngUtil.getDepartmentIDsByUser(Integer.parseInt(curUser));
    String deptsNameOfCurUser = systemMngUtil.getDepartmentNamesByUser(Integer.
        parseInt(usercode));
    myForm.setDeptname(deptsNameOfCurUser);

    if (!"".equals(usercode) && usercode != null) {
      int iUsercode = Integer.parseInt(usercode);
      User user = new DefaultUser();
      user = userDao.findUserByID(iUsercode);
      String userName = user.getName();
      myForm.setUsername(userName); //���ô�����������
    }
    int creatorFlag = 0;
    //�õ�vo�е�����ֵ
    String auditor = vo.getAuditor(); //��������
    String audit_flag = vo.getAudit_flag(); //�����˱�־
    System.out.println("The curUser is:" + curUser);
    System.out.println("The usercode is:" + usercode);
    System.out.println("The auditor is:" + auditor);
    System.out.println("The audit_falg is:" + audit_flag);
    creatorFlag = ConstantSet.judgeCreatorTag(curUser, usercode, auditor,
                                              audit_flag);
    myForm.setCreatorFlag(String.valueOf(creatorFlag)); //�����޸�Ȩ��

    //���������
    if (!StringUtils.isNull(auditor)) {
      int iAuditor = Integer.parseInt(auditor);
      User user = new DefaultUser();
      user = userDao.findUserByID(iAuditor);
      String sAuditor = user.getName();
      myForm.setAuditorname(sAuditor);
    }
    //����isCancel����
    int isCancel = 0;
    System.out.println("THe isCancle comments-----start");
    System.out.println("The curUser is:---" + curUser);
    System.out.println("THe auditor is:---" + auditor);
    System.out.println("The audit_flag is:--" + audit_flag);
    System.out.println("The isCancle comments -------end");
    isCancel = ConstantSet.judgeIsCancel(curUser, auditor, "", audit_flag, "0");
    System.out.println("The isCancel is:----" + isCancel);
    myForm.setIsCancel(String.valueOf(isCancel)); //����formBean�е�����

    //������˱�־
    int auditFlag = 0;
    auditFlag = ConstantSet.judgeAuditFlag(curUser, auditor, audit_flag);
    myForm.setAuditFlag(String.valueOf(auditFlag));
    makeCellField(cfg);
    return forwardpath;
  }

//������ʱ�ļ��Ա��ѯ
  private void makeCellField(GoaActionConfig cfg) throws DefaultException,
      Exception {
    TemplateUtils templateUtils = new TemplateUtils();
    PersonMonthTargetForm personmonthtargetForm = (PersonMonthTargetForm) cfg.
        getBaseForm();
    PersonTargetVO vo = (PersonTargetVO) personmonthtargetForm.getVo();
    String strPath = super.getServlet().getServletContext().getRealPath("/");
    String strFilename = "/cell/report/" +
        templateUtils.makeCellName(strPath + "/cell/report") + ".cll";
    personmonthtargetForm.setCellname(strFilename);
    byte[] bcell = vo.getTarget_content();
    File file = new File(strPath + strFilename);
    OutputStream fos = new FileOutputStream(file);
    OutputStream os = new BufferedOutputStream(fos);
    os.write(bcell);
    os.close();
  }

  /**
   * ��ȡtree������
   * @param cfg ��ǰAction���������
   * @return forward��ַ
   * @throws DefaultException
   * @throws Exception
   */

  public String doTreeData(GoaActionConfig cfg) throws DefaultException,
      Exception {
    String nodeid = StringUtils.nullToString(cfg.getRequest().getParameter(
        "nodeid"));
    String curDeptId=cfg.getBaseForm().getCurDeptCodes();//�õ��û���ǰ�Ĳ���
    HttpSession session=cfg.getSession();
    String user_dev = (String) session.getAttribute(Constant.USER_FILTER);//�õ��û�����
    int iuser_dev;
    iuser_dev=Integer.parseInt(user_dev);
    if(!StringUtils.isNull(user_dev)){
      if (iuser_dev<=3) {
        cfg.getRequest().setAttribute("treeData",personmonthtargetDao.getTreeData(nodeid));
      }else{
        cfg.getRequest().setAttribute("treeData",
                                      personmonthtargetDao.getTreeData(nodeid, curDeptId));
      }
    }

    return TREE_PAGE;
  }

}
