package com.gever.goa.dailyoffice.targetmng.action;

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
import com.gever.goa.dailyoffice.targetmng.dao.YearTargetDao;
import com.gever.goa.dailyoffice.targetmng.dao.YearTargetFactory;
import com.gever.goa.dailyoffice.targetmng.vo.TargetVO;
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
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.dao.UserDAO;
import com.gever.sysman.organization.model.User;
import com.gever.sysman.organization.model.impl.DefaultUser;
import com.gever.util.Codes;
import com.gever.util.DateTimeUtils;
import com.gever.util.StringUtils;
import com.gever.util.SumUtils;

/**
 * Title: 年度目标控制器
 * Description: 年度目标控制器
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */

public class YearTargetAction
    extends BaseAction {
  YearTargetDao yeartargetDao = null; //定义接口
  OrganizationUtil util = new OrganizationUtil();
  public YearTargetAction() {
  }

  /**
   * 初始化页面数据
   * @param cfg 当前的vo对象
   * @throws DefaultException
   * @throws Exception
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {
    YearTargetForm myForm = (YearTargetForm) cfg.getBaseForm(); //得到form变量
    yeartargetDao = YearTargetFactory.getInstance().createYearTarget(super.
        dbData); //得到相对应dao的实例
    cfg.setBaseDao( (BaseDAO) yeartargetDao); //cfg--Goa Action获取类--设置超类中的Dao
    //容错处理,防止vo对象为null
    if (myForm.getVo() == null) {
      myForm.setVo(new TargetVO()); //设置VO到Form中
    }
  }

  /**
   * 到清单页面--这里是重载BaseAction中的toList方法
   * 列表条件--获取那些当前年度(如果不是查询输入的年度)
   * 类型为4-年度目标的那些年度目标
   * @param cfg 当前Action相关配置类
   * @return forward地址
   * @throws DefaultException
   * @throws Exception
   */

  protected String toList(GoaActionConfig cfg) throws DefaultException,
      Exception {
    HttpServletRequest request = cfg.getRequest();
    HttpSession session=cfg.getSession();
    String curUserID = cfg.getBaseForm().getUserId(); //取得当前用户ID
    String user_dev=(String) session.getAttribute(Constant.USER_FILTER);//得到用户级层
    String curDeptCodes = cfg.getBaseForm().getCurDeptCodes().trim();
     //得到子部门
    StringTokenizer stDept = new StringTokenizer(curDeptCodes);
    OrganizationUtil systemMngUtil=new OrganizationUtil();
    String curSonDept="";
    while (stDept.hasMoreTokens()) {
        String curDeptId = stDept.nextToken();
        System.out.println("The curDeptId is:--------" + curDeptId);
        curSonDept = curSonDept + systemMngUtil.getDepartmentSonIdById(curDeptId);
    }
    ConstantSet.CurUserID = curUserID;
    String queryFlag = request.getParameter("queryFlag");
    String curYear = DateTimeUtils.getCurrentDate().substring(0, 4); //获取当前年份
    StringBuffer sbSqlWhere = new StringBuffer();
    if ("true".equals(queryFlag)) {
      String searchYear = ( (YearTargetForm) cfg.getBaseForm()).getSearchYear();
      sbSqlWhere.append(" and CURRENT_YEAR like '%" +searchYear + "%' ");
      sbSqlWhere.append(" and TARGET_TYPE='" + ConstantSet.YearTargetType +"' ");
      sbSqlWhere.append(" and ").append(super.getIsNull("CURRENT_MONTH"));
      sbSqlWhere.append(" and ").append(super.getIsNull("CURRENT_WORK"));
      sbSqlWhere.append(" and ( ").append(user_dev).append("<b.level");
      sbSqlWhere.append(" or user_code= ").append(curUserID).append(")");
      if(!StringUtils.isNull(user_dev)&&!StringUtils.isNull(curDeptCodes)){
          if (Integer.parseInt(user_dev) > 3) {
              StringTokenizer st = new StringTokenizer(curSonDept, ",");
              int stCount = st.countTokens();
              sbSqlWhere.append("and (");
              while (st.hasMoreTokens()) {
                  sbSqlWhere.append(" dept_code like'%").append(st.nextToken());
                  sbSqlWhere.append(",%'");
                  if (st.hasMoreTokens()) {
                      sbSqlWhere.append(" or ");
                  }
              }
              //如果没有子部门
              if (stCount > 0) {
                  sbSqlWhere.append(" or ");
              }
              sbSqlWhere.append(" dept_code like '%").append(curDeptCodes).append(",%')");
          }
      }
    } else {
      sbSqlWhere.append(" and CURRENT_YEAR = '" + curYear +"' ");
      sbSqlWhere.append(" and TARGET_TYPE='" + ConstantSet.YearTargetType +"' ");
      sbSqlWhere.append(" and ").append(super.getIsNull("CURRENT_MONTH"));
      sbSqlWhere.append(" and ").append(super.getIsNull("CURRENT_WORK"));
      sbSqlWhere.append(" and ( ").append(user_dev).append("<b.level");
      sbSqlWhere.append(" or user_code= ").append(curUserID).append(")");
      if(!StringUtils.isNull(user_dev)&&!StringUtils.isNull(curDeptCodes)){
          if (Integer.parseInt(user_dev) > 3) {
              StringTokenizer st = new StringTokenizer(curSonDept, ",");
              int stCount = st.countTokens();
              sbSqlWhere.append("and (");
              while (st.hasMoreTokens()) {
                  sbSqlWhere.append(" dept_code like'%").append(st.nextToken());
                  sbSqlWhere.append(",%'");
                  if (st.hasMoreTokens()) {
                      sbSqlWhere.append(" or ");
                  }
              }
              //如果没有子部门
              if (stCount > 0) {
                  sbSqlWhere.append(" or ");
              }
              sbSqlWhere.append(" dept_code like '%").append(curDeptCodes).append(",%')");
          }
      }
    }
    if (!StringUtils.isNull(curYear)) {
      cfg.getBaseForm().setSqlWhere(sbSqlWhere.toString());
    }
    super.toList(cfg);
    return this.FORWORD_LIST_PAGE;
  }

  /**
   * 到修改页面--这里是重载BaseAction中的toEdit方法
   * 以便初始化新增页面上的一些常量数据（不用输入，只需读取）
   * @param cfg 当前Action相关配置类
   * @return forward地址
   * @throws DefaultException
   * @throws Exception
   */
  protected String toEdit(GoaActionConfig cfg) throws DefaultException,
      Exception {
    super.toEdit(cfg);
    String actionType = SumUtils.nullToString(cfg.getRequest().getParameter(
        "actionType"));
    YearTargetForm myForm = (YearTargetForm) cfg.getBaseForm(); //得到form变量
    if (StringUtils.isNull(myForm.getReportTemplateName())) { //如果报表模板还没有设置
      IsStandardModelDao isStandardModelDao = IsCustomerFactory.
          getInstance().createIsStandardModel(super.dbData);
      IsStandardModelVO ismVO = (IsStandardModelVO) isStandardModelDao.
          getTemplate(ConstantSet.YearTargetTemplate);
      myForm.setReportTemplateName(ismVO.getFile_path());
    }
    User user = new DefaultUser();
    UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
    OrganizationUtil systemMngUtil = new OrganizationUtil();
    String curUser = cfg.getBaseForm().getUserId(); //当前用户ID
    String deptCodesOfCurUser = systemMngUtil.getDepartmentIDsByUser(
        Integer.parseInt(curUser));
    //获取当前用户对应的部门代码，用逗号隔开
    //String deptNamesOfCurUser = systemMngUtil.getDepartmentNamesByUser(Integer.parseInt(curUser));
    //获取当前用户对应的部门名称，用逗号隔开
    String deptNamesOfCurUser = cfg.getBaseForm().getCurDeptNames();
    //获取当前用户对应的部门名称，用空格隔开
    int i = 0;
    StringTokenizer st = new StringTokenizer(deptCodesOfCurUser, ",");
    if (st.hasMoreTokens()) {
      i++;
      String curDeptCode = st.nextToken();
    }
    String curDate = DateTimeUtils.getCurrentDate(); //取得当前日期--对应创建日期
    TargetVO vo = (TargetVO) cfg.getBaseForm().getVo();
    String curYear = "";
    String curMonth = "";
    String curWeek = "";
    if (!this.MODIFY_ACTION.equals(actionType)) { //如果是到新增页面-需要设置年月周
      curYear = DateTimeUtils.getCurrentDate().substring(0, 4); //获取当前年份
      vo.setCurrent_year(curYear);
      vo.setCurrent_month(curMonth); //月空
      vo.setCurrent_work(curWeek); //周空
    } else { //如果是到修改页面则当前周数要从数据库中获取
      System.out.println("modify");
      //可以不做什么操作，因为超类会从数据库中自动读取vo
    }
    String initUserCode = vo.getUser_code();
    if ("".equals(initUserCode) || initUserCode == null) {
      //如果初始用户为空(新增的情况)则设置当前用户为创建人
      vo.setUser_code(curUser);
    }

    //部门和创建日期为新增时的部门和日期
   if(!this.MODIFY_ACTION.equals(actionType)){
       vo.setCreate_date(curDate);
       vo.setDept_code(deptCodesOfCurUser);
       myForm.setDeptname(deptCodesOfCurUser);
   }

    vo.setTarget_type(ConstantSet.YearTargetType);
    String auditorid = vo.getAuditor();
    String checkerid = vo.getChecker();
    if (!"".equals(auditorid) && auditorid != null) {
      int iAuditorid = Integer.parseInt(auditorid);
      if (util.checkUser(iAuditorid)) {
        User userAuditor = new DefaultUser();
        userAuditor = userDao.findUserByID(iAuditorid);
        String auditorName = userAuditor.getName();
        myForm.setAuditorid(auditorid); //重新设置一下FormBean中的审核人ID，否则数据库中将写入空值
        myForm.setAuditorname(auditorName); //设置数据库中获取的审核人显示到页面
      } else {
        myForm.setAuditorname("");
      }
    } else {
      myForm.setAuditorid(""); //如果是新增操作，则将上次的FormBean中的值重置为空
      myForm.setAuditorname("");
    }

    if (!"".equals(checkerid) && checkerid != null) {
      int iCheckerid = Integer.parseInt(checkerid);
      if (util.checkUser(iCheckerid)) {
        User userChecker = new DefaultUser();
        userChecker = userDao.findUserByID(iCheckerid);
        String checkerName = userChecker.getName();
        myForm.setCheckerid(checkerid); //重新设置一下FormBean中的审批人ID，否则数据库中将写入空值
        myForm.setCheckername(checkerName); //设置数据库中获取的审批人显示到页面
      } else {
        myForm.setCheckername("");
      }
    } else {
      myForm.setCheckerid(""); //如果是新增操作，则将上次的FormBean中的值重置为空
      myForm.setCheckername("");
    }

    //得到与用户相对应dao的实例
    user = userDao.findUserByID(Integer.parseInt(vo.getUser_code()));
    String curName = user.getName();
    myForm.setUsername(curName); //设置当前用户中文名而不是账户--对应创建人
    String auditor = vo.getAuditor(); //获取审核人
    String checker = vo.getChecker(); //获取审批人
    String user_code = vo.getUser_code(); //获取创建人
    String audit_flag = vo.getAudit_flag(); //获取审核状态
    String check_flag = vo.getCheck_flag(); //获取审批状态
    int auditFlag = 0;
    int checkFlag = 0;
    int editFlag = 0;
    int isReadOnlyTemplateFlag = 0;
    auditFlag = yeartargetDao.judgeAuditTag(curUser, auditor, audit_flag,
                                            check_flag);
    checkFlag = yeartargetDao.judgeCheckTag(curUser, checker, audit_flag,
                                            check_flag);
    isReadOnlyTemplateFlag = ConstantSet.judgeReadOnlyTemplateTag(curUser,
        user_code, check_flag, checker,
        audit_flag, auditor);
    //用来判断是否显示审核或审批信息
    String flag = SumUtils.nullToString(cfg.getRequest().getParameter(
        "flag"));
    System.out.println("The flag is:" + flag);
    if (flag.equals("modify")) {
      myForm.setAuditFlag("0");
      myForm.setCheckFlag("0");
    }
    if (!"".equals(audit_flag) && audit_flag != null) { //已经经过了审核时
      //只有审核标记不为null才判断(说明是新增过的记录)，否则默认为可以选择审核人和审批人
      editFlag = ConstantSet.judgeEditTag(curUser, user_code, audit_flag);
    } else { //还没有经过审核的情况下，一定是可以修改的
      editFlag = 1;
    }
    if (!flag.equals("modify")) { //如果用户点击修改按钮，则可以修改审核，审批人
      editFlag = 0;
    }
    if (auditFlag == 1 && !flag.equals("modify")) { //当前人是审核人时
      myForm.setAuditFlag("1"); //拥有审核权限--显示审核意见填写框和审核是否通过ordio
      vo.setAudit_date(curDate); //将审核日期设置为当前的日期2004-07-28
    } else if (auditFlag == 0) {
      myForm.setAuditFlag("0"); //不拥有审核权限--不显示审核意见填写框和审核是否通过ordio
    }
    if (checkFlag == 1 && !flag.equals("modify")) { //当前人是审批人时
      myForm.setCheckFlag("1"); //拥有审批权限--显示审批意见填写框和审批是否通过ordio
      vo.setCheck_date(curDate); //将审批日期设置为当前的日期2004-07-28
    } else if (checkFlag == 0) {
      myForm.setCheckFlag("0"); //不拥有审批权限--不显示审批意见填写框和审批是否通过ordio
    }
    if (isReadOnlyTemplateFlag == 0) { //当前人是不可以修改目标模板时-只读
      myForm.setIsReadOnlyTemplateFlag("0"); //不拥有修改权限-将模板只读标志设为只读
    } else if (isReadOnlyTemplateFlag == 1) { //可写
      myForm.setIsReadOnlyTemplateFlag("1"); //拥有修改权限-不将模板只读标志设为不只读
    }
    if (editFlag == 1) { //当前人是可修改人时
      myForm.setEditFlag("1"); //拥有修改权限--显示审核和审批人选择按钮
    } else if (editFlag == 0) {
      myForm.setEditFlag("0"); //不拥有修改权限--不显示审核和审批人选择按钮
    }

    //因为在处理取消审核和审批的时候，标志位isCancel会有冲突，所以在toEdit方法
    //里加以控制,设置值为0
    if (actionType.equals("modify")) {
      myForm.setIsCancel("0");
    }
    cfg.getBaseForm().setVo(vo);
    ConstantSet cs = new ConstantSet();
    myForm.setUnitname(cs.getUnitName());
    if ("modify".equals(actionType)) { //如果是修改操作
      makeCellField(cfg);
    }
    if (!this.MODIFY_ACTION.equals(actionType)) { //如果不是修改跳转的话跑到新增页面去
      myForm.setEditFlag("1"); //如果是跑到新增页面去的话，默认可以修改
      cfg.getBaseForm().setActionType(super.ADD_ACTION);
    }
    return this.FORWORD_EDIT_PAGE;
  }

  /**
   * 新增动作--这里重载BaseAction里面的方法，实现添加模板的功能
   * @param cfg 当前Action相关配置类
   * @param isBack 是否返回
   * @return forward地址
   * @throws DefaultException
   * @throws Exception
   */

  protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
      DefaultException, Exception {
    YearTargetForm yeartargetForm = (YearTargetForm) cfg.getBaseForm();
    TargetVO vo = (TargetVO) yeartargetForm.getVo();
    vo.setTarget_content(Codes.decode(yeartargetForm.getCellcontent().
                                      toCharArray()));

    //用来判断用户填写的资料是否已经存在于数据库中了
    String current_year = vo.getCurrent_year();
    String current_month = vo.getCurrent_month();
    String current_week = vo.getCurrent_work();
    String target_type = vo.getTarget_type();
    String dept_code = vo.getDept_code();
    String user_code = vo.getUser_code();
    ConstantSet constantSet = new ConstantSet();
    String isExistFlag = constantSet.queryTargetIsExist(current_year,
        current_month, current_week, target_type,
        dept_code, user_code);
    if ("1".equals(isExistFlag)) {
      throw new DefaultException("您已经填写了该项，不能再新增了，可选择编辑该项！");
    }
    String auditorid = yeartargetForm.getAuditorid(); //获取审核人id
    String auditorname = yeartargetForm.getAuditorname(); //获取审核人中文名
    String checkerid = yeartargetForm.getCheckerid(); //获取审批人id
    String checkername = yeartargetForm.getCheckername(); //获取审批人中文名
    vo.setAuditor(auditorid);
    vo.setChecker(checkerid);
    vo.setAudit_flag("0");
    vo.setCheck_flag("0"); //默认将审核审批标志置为0
    //vo.setAuditor(auditorname);//这里设置中文名-到时通过用户名(username字段)来判断权限
    //vo.setChecker(checkername);
    cfg.getBaseForm().setVo(vo);
    String retStr = "";
    try {
      cfg.getBaseDao().insert(vo);
    } catch (DefaultException e) {
      throw new DefaultException("您已经填写了该项，不能再新增了，可选择编辑该项！");
    }
    //给审核审批人发送短消息
    String isSendMsg = yeartargetForm.getIsSendMsg(); //是否发送短消息
    System.out.println("The isSendMsg is:----" + isSendMsg);
    if (!StringUtils.isNull(isSendMsg)) {
      if ("true".equalsIgnoreCase(isSendMsg)) {
        this.sendMsg(vo);
      }
    }

    if (isBack == true) {
      retStr = this.FORWORD_LIST_PAGE;
    } else {
      cfg.getBaseForm().setActionType(this.MODIFY_ACTION);
      this.makeCellField(cfg);
      retStr = this.FORWORD_EDIT_PAGE;
    }
    return retStr;
  }

  /**
   * 发送短消息
   * @return voie
   */
  private void sendMsg(TargetVO vo) throws Exception {
    MessageDao messageDao = MessageFactory.getInstance().
        createMessageDao(Constant.DATA_SOURCE);
    String userCode = vo.getUser_code(); //发送人是创建人
    String sendTime = DateTimeUtils.getCurrentDateTime();
    String auditorContent = "您好!目标管理中有您需要审核的年目标!";
    String checkerContent = "您好!目标管理中有您需要审批的年目标!";
    String receiver1 = vo.getAuditor();
    String receiver2 = vo.getChecker();
    String webUrl = "/dailyoffice/targetmng/viewyeartarget.do?fid="+vo.getSerial_num();
    System.out.println("the weburl is--"+webUrl);
    messageDao.sendMessage(userCode, sendTime, auditorContent, receiver1, webUrl,"转向年目标审核");
    messageDao.sendMessage(userCode, sendTime, checkerContent, receiver2, webUrl,"转向年目标审批");
  }

  /**
   * 修改动作--这里重载BaseAction里面的方法，实现修改模板的功能
   * @param cfg 当前Action相关配置类
   * @param isBack 是否返回
   * @return forward地址
   * @throws DefaultException
   * @throws Exception
   */
  protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
      DefaultException,
      Exception {
    YearTargetForm yeartargetForm = (YearTargetForm) cfg.getBaseForm();
    TargetVO vo = (TargetVO) yeartargetForm.getVo();
    vo.setTarget_content(Codes.decode(yeartargetForm.getCellcontent().
                                      toCharArray()));

    String isCancel = yeartargetForm.getIsCancel();
    System.out.println("The isCancel is:***" + isCancel);
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
    if (! ("1".equals(isCancel) || "2".equals(isCancel))) {
      String auditorid = yeartargetForm.getAuditorid(); //获取审核人
      String checkerid = yeartargetForm.getCheckerid(); //获取审批人
      vo.setAuditor(auditorid);
      vo.setChecker(checkerid);
    }

    super.doUpdate(cfg, isBack);
    //给审核审批人发送短消息
    String isSendMsg = yeartargetForm.getIsSendMsg(); //是否发送短消息
    System.out.println("The isSendMsg is:----" + isSendMsg);
    if (!StringUtils.isNull(isSendMsg)) {
      if ("true".equalsIgnoreCase(isSendMsg)) {
        this.sendMsg(vo);
      }
    }

    String retStr = "";
    if (isBack == true) {
      retStr = this.FORWORD_LIST_PAGE;
    } else {
      this.makeCellField(cfg);
      retStr = this.FORWORD_EDIT_PAGE;
    }
    return retStr;

  }

  /**
   * 到检视页面
   * @param cfg 当前Action相关配置类
   * @return forward 地址
   * @throws DefaultException
   * @throws Exception
   */

  protected String toView(GoaActionConfig cfg) throws DefaultException,
      Exception {
    String forwardpath = super.toView(cfg);
    YearTargetForm myForm = (YearTargetForm) cfg.getBaseForm(); //得到form变量
    TargetVO vo = (TargetVO) cfg.getBaseForm().getVo();
    UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
    String usercode = vo.getUser_code(); //获取创建人ID
    String deptcode = vo.getDept_code(); //获取部门ID-这里不再通过deptcode来获取deptname了
    String auditorid = vo.getAuditor(); //获取审核人ID
    String checkerid = vo.getChecker(); //获取审批人ID
    if (!"".equals(usercode) && usercode != null) {
      int iUsercode = Integer.parseInt(usercode);
      User user = new DefaultUser();
      user = userDao.findUserByID(iUsercode);
      String userName = user.getName();
      myForm.setUsername(userName); //设置创建人中文名
      OrganizationUtil systemMngUtil = new OrganizationUtil();
      String curDeptName = systemMngUtil.getDepartmentNamesByUser(
          iUsercode);
      myForm.setDeptname(curDeptName); //设置创建人所在部门中文名
    }
    /*
             if (!"".equals(deptcode) && deptcode != null) {
        int iDeptcode = Integer.parseInt(deptcode);
        Department dept = new DefaultDepartment();
         DepartmentDAO deptDao = OrganizationFactory.getInstance().getDepartmentDAO();
        dept = deptDao.findDepartmentByID(iDeptcode);
        String curDeptName = dept.getName();
        myForm.setDeptname(curDeptName); //设置当前部门中文名--对应当前传过来的部门
             }
     */
    if (!"".equals(auditorid) && auditorid != null) {
      int iAuditorid = Integer.parseInt(auditorid);
      if (util.checkUser(iAuditorid)) {
        User userAuditor = new DefaultUser();
        userAuditor = userDao.findUserByID(iAuditorid);
        String auditorName = userAuditor.getName();
        myForm.setAuditorname(auditorName);
        //设置数据库中获取的审核人显示到页面
      } else {
        myForm.setAuditorname("");
      }
    }else{
      myForm.setAuditorname("");
    }
    if (!"".equals(checkerid) && checkerid != null) {
      int iCheckerid = Integer.parseInt(checkerid);
      if (util.checkUser(iCheckerid)) {
        User userChecker = new DefaultUser();
        userChecker = userDao.findUserByID(iCheckerid);
        String checkerName = userChecker.getName();
        myForm.setCheckername(checkerName); //设置数据库中获取的审批人显示到页面
      } else {
        myForm.setCheckername("");
      }
    }else{
      myForm.setCheckername("");
    }

    int creatorFlag = 0;
    String curUser = cfg.getBaseForm().getUserId(); //取得当前用户ID
    //creatorFlag = ConstantSet.judgeCreatorTag(curUser, usercode);
    String auditflag = vo.getAudit_flag(); //获取审核标志
    String checkflag = vo.getCheck_flag(); //获取审批标志
    //这里不光是要通过是否为创建人来判断的，还需要考虑到当前记录是否经过审核和审批
    //creatorFlag = ConstantSet.judgeEditTagForView(curUser, usercode, checkflag, checkerid, auditflag, auditorid);
    creatorFlag = ConstantSet.judgeEditTagForViewOfNew(curUser, usercode,
        checkflag, checkerid, auditflag,
        auditorid);
    if (creatorFlag == 1) {
      myForm.setCreatorFlag("1");
    } else if (creatorFlag == 2) {
      myForm.setCreatorFlag("2"); //拥有审核权限
    } else if (creatorFlag == 3) {
      myForm.setCreatorFlag("3"); //拥有审批权限
    } else if (creatorFlag == 0) {
      myForm.setCreatorFlag("0"); //不拥有修改权限
    }
    int modify_flag = ConstantSet.judgeModify(curUser, usercode, auditorid,
                                              checkerid, auditflag,
                                              checkflag);
    if (modify_flag == 1) {
      myForm.setModifyFlag("1");
    } else if (modify_flag == 0) {
      myForm.setModifyFlag("0");
    }
    System.out.println("The modify_falg is***:" + modify_flag);
    //判断是否显示取消审核和取消审批
    int isCancel = ConstantSet.judgeIsCancel(curUser, auditorid, checkerid,
                                             auditflag, checkflag);
    if (isCancel == 1) {
      myForm.setIsCancel("1");
    } else if (isCancel == 2) {
      myForm.setIsCancel("2");
    } else if (isCancel == 0) {
      myForm.setIsCancel("0");
    }
    ConstantSet cs = new ConstantSet();
    myForm.setUnitname(cs.getUnitName());
    makeCellField(cfg);
    return forwardpath;
  }

//创建临时文件以便查询
  private void makeCellField(GoaActionConfig cfg) throws DefaultException,
      Exception {
    TemplateUtils templateUtils = new TemplateUtils();
    YearTargetForm yeartargetForm = (YearTargetForm) cfg.getBaseForm();
    TargetVO vo = (TargetVO) yeartargetForm.getVo();
    String strPath = super.getServlet().getServletContext().getRealPath("/");
    String strFilename = "/cell/report/" +
        templateUtils.makeCellName(strPath + "/cell/report") + ".cll";
    yeartargetForm.setCellname(strFilename);
    byte[] bcell = vo.getTarget_content();
    File file = new File(strPath + strFilename);
    OutputStream fos = new FileOutputStream(file);
    OutputStream os = new BufferedOutputStream(fos);
    os.write(bcell);
    os.close();
  }

}
