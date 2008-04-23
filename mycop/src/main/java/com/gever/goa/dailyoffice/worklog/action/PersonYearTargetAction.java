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
import com.gever.goa.dailyoffice.worklog.dao.PersonYearTargetDao;
import com.gever.goa.dailyoffice.worklog.dao.PersonYearTargetFactory;
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
 * Title: 人年目标控制器
 * Description: 人年目标控制器
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */

public class PersonYearTargetAction
    extends BaseAction {
  PersonYearTargetDao personyeartargetDao; //定义接口
  public PersonYearTargetAction() {
  }

  /**
   * 初始化页面数据
   * @param cfg 当前的vo对象
   * @throws DefaultException
   * @throws Exception
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {
    PersonYearTargetForm myForm = (PersonYearTargetForm) cfg.getBaseForm(); //得到form变量
    personyeartargetDao = PersonYearTargetFactory.getInstance().
        createPersonYearTarget(super.dbData); //得到相对应dao的实例
    cfg.setBaseDao( (BaseDAO) personyeartargetDao); //cfg--Goa Action获取类--设置超类中的Dao
//容错处理,防止vo对象为null
    if (myForm.getVo() == null) {
      myForm.setVo(new PersonTargetVO()); //设置VO到Form中
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
    HttpSession session = cfg.getSession();
    OrganizationUtil systemMngUtil = new OrganizationUtil();
    PersonYearTargetForm myForm = (PersonYearTargetForm) cfg.getBaseForm(); //得到form变量
    String leftDeptType = request.getParameter("nodeid"); //获取树传过来的部门id
    String deptIdOfForm = myForm.getDeptnodeid();
    String curUserCode=myForm.getUserId();
    String user_dev = (String) session.getAttribute(Constant.USER_FILTER); //得到拥护级层
    boolean isFirstEnter = false;
    if (!StringUtils.isNull(leftDeptType)) {
      myForm.setDeptnodeid(leftDeptType); //将树传过来的部门id放入form中以便以后获取
      myForm.setSearchUserName("");
      myForm.setSearchYear("");
      myForm.setViewFlag("1");
    } else { //如果不是左边树传过来的类型,那么判断是否是修改或查看返回时的情况
      if (StringUtils.isNull(deptIdOfForm)) { //如果不是从修改或查看页面返回,说明是第一次进入列表
        isFirstEnter = true;
      } else { //如果是从修改或查看页面返回则要将部门id设置一下
        deptIdOfForm = myForm.getDeptnodeid();
        myForm.setDeptnodeid(deptIdOfForm);
        leftDeptType = deptIdOfForm;
      }
      myForm.setViewFlag("0");
    }
    String queryFlag = request.getParameter("queryFlag"); //获取查询条件
    session.setAttribute("curDept", leftDeptType); //将左边传过来的部门id放入session中-该处可能不生效

    String deptOfCurUser = "";
    String curYear = DateTimeUtils.getCurrentDate().substring(0, 4); //获取当前年份
    String curUser = cfg.getBaseForm().getUserId();
    ConstantSet.CurUserID = curUser; //设置当前用户到一个常量变量中以便以后获取
    StringBuffer sbSqlWhere = new StringBuffer();
    // sbSqlWhere.append("  ");
    if (StringUtils.isNull(myForm.getDeptnodeid())
        && StringUtils.isNull(leftDeptType)) { //如果当前部门为空的话默认使用当前用户所在部门
      String curDeptCodes = cfg.getBaseForm().getCurDeptCodes(); //获取当前用户对应部门ids-可能存在多部门
      //这里暂时只获取当前用户所在第一个部门，因为树结构传过来的只是一个部门而已
      int i = 0;
      StringTokenizer st = new StringTokenizer(curDeptCodes, " ");
      if (st.hasMoreTokens()) {
        i++;
        deptOfCurUser = st.nextToken(); //获取当前用户对应的部门-这里只取第一个
      }
      myForm.setDeptnodeid(deptOfCurUser); //将当前人所在部门设为树传过来的部门Id
    }
    String curListType = myForm.getDeptnodeid(); //获取当前树传过来的部门id-始终是有值的
    //得到当前部门下的所有子部门
    String tempDeptId = cfg.getBaseForm().getCurDeptCodes();
    System.out.println("The depteCode is:-----"+tempDeptId);

    /**
     * 如果当前人是多部门
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
     tempDeptId = tempDeptId.substring(0, 4) + ","; //如果多部门则取第一个部门
   }
    if ("true".equals(queryFlag)) { //如果点击了查询按钮
      String queryCurDept = myForm.getDeptnodeid(); //获取当前部门-树传过来的
      String searchYear = ( (PersonYearTargetForm) cfg.getBaseForm()).
          getSearchYear();
      String searchUserName = ( (PersonYearTargetForm) cfg.getBaseForm()).
          getSearchUserName();
      searchUserName = StringUtils.replaceText(searchUserName);
      if (!StringUtils.isNull(user_dev)&& !StringUtils.isNull(tempDeptId)) {
        if (Integer.parseInt(user_dev) <= 3) {
          sbSqlWhere.append(" and (").append(user_dev).append("<b.level"); //只显示用户级层小于等于当前用户的
          sbSqlWhere.append(" or user_code=").append(curUserCode).append(")");
        } else {
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
          //如果没有子部门
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
      sbSqlWhere.append(" and CURRENT_YEAR like '%" + searchYear + "%' ");
      if (!StringUtils.isNull(searchUserName)) { //如果输入了查询用户的话添加该条件
        String tempSearchUserCodes = "";
        String searchUserCodes = "";
        tempSearchUserCodes = systemMngUtil.getUserIdsByUserName(
            searchUserName);
        int tempSearchUserCodesSize = tempSearchUserCodes.length();
        if (!StringUtils.isNull(tempSearchUserCodes)) { //不为空时才截取,否则会出错
          searchUserCodes = tempSearchUserCodes.substring(0,
              tempSearchUserCodesSize - 1);
        } else {
          searchUserCodes = "";
        }
        if (!StringUtils.isNull(searchUserCodes)) {
          sbSqlWhere.append(" and USER_CODE in (" + searchUserCodes + ")");
        } else {
          sbSqlWhere.append(" and USER_CODE in (-1) ");
        }
      }
      sbSqlWhere.append(" and TARGET_TYPE=" +
                        Integer.parseInt(ConstantSet.PersonYearTargetType) +
                        " ");
      if (!this.isOracle()) {
        sbSqlWhere.append(" and CURRENT_MONTH='' and CURRENT_WORK='' ");
      } else {
        sbSqlWhere.append(
            " and CURRENT_MONTH IS NULL and CURRENT_WORK IS NULL ");
      }
      sbSqlWhere.append(" and DEPT_CODE like '%" + queryCurDept +
                        "," + "%' "); //只能处理单部门的情况
    } else { //不是点击查询时
      if (StringUtils.isNull(leftDeptType) == false) {
        //如果左边树结构传过来部门条件的话，则列出的记录只与传过来的部门有关，与当前用户无关
        if(StringUtils.isNull(tempDeptId)){
          sbSqlWhere.append(" and 1<>1");
        }
        sbSqlWhere.append(" and (").append(user_dev).append("<b.level"); //只显示用户级层小于等于当前用户的
        sbSqlWhere.append(" or user_code= '").append(curUserCode).append("')");
        sbSqlWhere.append(" and CURRENT_YEAR = '" + curYear + "' ");
        sbSqlWhere.append(" and TARGET_TYPE=" +
                          Integer.parseInt(ConstantSet.PersonYearTargetType) +
                          " ");
        if (!this.isOracle()) {
          sbSqlWhere.append(" and CURRENT_MONTH='' and CURRENT_WORK='' ");
        } else {
          sbSqlWhere.append(
              " and CURRENT_MONTH IS        NULL and CURRENT_WORK IS NULL ");
        }

        //sbSqlWhere.append(" and USER_CODE=" + Integer.parseInt(curUser) + " ");
        sbSqlWhere.append(" and ','||DEPT_CODE||',' like '%" + curListType +
                          "," + "%' ");
        //sbSqlWhere.append(" order by  CURRENT_YEAR DESC ");
        //按当前年度倒叙排列-由于只显示当前年度目标，所以不用排序
        //只能处理单部门的情况-curListType-树那边传过来的部门
      } else { //左边树没有传过部门来时默认查询当前人以及当前人所在部门的记录
        if (!StringUtils.isNull(user_dev)&& !StringUtils.isNull(tempDeptId)) {
          if (Integer.parseInt(user_dev) <= 3) {
            sbSqlWhere.append(" and (").append(user_dev).append("<b.level"); //只显示用户级层小于等于当前用户的
            sbSqlWhere.append(" or user_code=").append(curUserCode).append(")");
          } else {
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
        sbSqlWhere.append(" and CURRENT_YEAR = '" + curYear + "' ");
        sbSqlWhere.append(" and TARGET_TYPE=" +
                          Integer.parseInt(ConstantSet.PersonYearTargetType) +
                          " ");
        if (!this.isOracle()) {
          sbSqlWhere.append(" and CURRENT_MONTH='' and CURRENT_WORK='' ");
        } else {
          sbSqlWhere.append(
              " and CURRENT_MONTH IS NULL and CURRENT_WORK IS NULL ");
        }

        // sbSqlWhere.append(" and USER_CODE=" + Integer.parseInt(curUser) +
        //     " ");
        //sbSqlWhere.append(" and ','||DEPT_CODE||',' like '%" + curListType +
        //  "," + "%' ");
        //sbSqlWhere.append(" order by  CURRENT_YEAR DESC "); //按当前年度倒叙排列
        //只能处理单部门的情况-curDept-当前用户所在部门
      }
    }
    String deptNodeName = "";
    deptNodeName = systemMngUtil.getDeptNameByDeptID(myForm.getDeptnodeid());
    myForm.setDeptnodename(deptNodeName);

    cfg.getBaseForm().setSqlWhere(sbSqlWhere.toString());
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
      Exception { //注意这里如果一个用户同时对应多个部门的情况怎么办？？
    super.toEdit(cfg);
    String actionType = SumUtils.nullToString(cfg.getRequest().getParameter(
        "actionType"));
    HttpServletRequest request = cfg.getRequest();

    //得到修改标志
    String modifyFlag = request.getParameter("modifyflag");
    HttpSession session = cfg.getSession();
    User user = new DefaultUser();
    UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
    PersonYearTargetForm myForm = (PersonYearTargetForm) cfg.getBaseForm(); //得到form变量
    if (StringUtils.isNull(myForm.getReportTemplateName())) { //如果报表模板还没有设置
      IsStandardModelDao isStandardModelDao = IsCustomerFactory.getInstance().
          createIsStandardModel(super.dbData);
      IsStandardModelVO ismVO = (IsStandardModelVO) isStandardModelDao.
          getTemplate(ConstantSet.
                      PersonYearTargetTemplate);
      myForm.setReportTemplateName(ismVO.getFile_path());
    }

    PersonTargetVO vo = (PersonTargetVO) cfg.getBaseForm().getVo();
    String curUser = vo.getUser_code(); //取得当前用户ID
    if (StringUtils.isNull(curUser)) { //如果是新增
      curUser = cfg.getBaseForm().getUserId();
    }
    OrganizationUtil systemMngUtil = new OrganizationUtil();
    String curPostCode = systemMngUtil.getJobIDsByUser(Integer.parseInt(curUser));
    String curPostName = systemMngUtil.getJobNamesByUser(Integer.parseInt(
        curUser));
    myForm.setPostname(curPostName);

    String curDeptCode = (String) session.getAttribute("curDept");
    if ("".equals(curDeptCode) || curDeptCode == null) { //如果从session中获取的当前部门ID为空则从formbean中获取
      curDeptCode = myForm.getDeptnodeid();
    }
    //获取树传过来的部门
    if(!StringUtils.isNull(curDeptCode)){
      Department dept = new DefaultDepartment();
      DepartmentDAO deptDao = OrganizationFactory.getInstance().getDepartmentDAO();
      dept = deptDao.findDepartmentByID(Integer.parseInt(curDeptCode));
      String curDeptName = dept.getName();
      myForm.setDeptnodename(curDeptName); //设置当前部门中文名--对应当前传过来的部门
    }
    //获取当前用户对应的部门中文名称,将可能对应多部门
    String deptsCodeOfCurUser = systemMngUtil.getDepartmentIDsByUser(Integer.
        parseInt(curUser));
    String deptsNameOfCurUser = systemMngUtil.getDepartmentNamesByUser(Integer.
        parseInt(curUser));
    myForm.setDeptname(deptsNameOfCurUser);
    String curDate = vo.getCreate_date();
    if (StringUtils.isNull(curDate)) {
        curDate = DateTimeUtils.getCurrentDate(); //取得当前日期--对应创建日期
    }

    String curYear = "";
    String curMonth = "";
    String curWeek = "";
    if (!this.MODIFY_ACTION.equals(actionType)) { //如果是到新增页面-需要设置年月周
      curYear = DateTimeUtils.getCurrentDate().substring(0, 4); //获取当前年份
      vo.setCurrent_year(curYear);
      vo.setCurrent_month(curMonth);
      vo.setCurrent_work(curWeek);
    } else { //如果是到修改页面则当前周数要从数据库中获取
      System.out.println("modify");
      //可以不做什么操作，因为超类会从数据库中自动读取vo
    }

    vo.setDept_code(deptsCodeOfCurUser); //注意，新增时总是对应当前人的
    vo.setPost_code(curPostCode);
    vo.setUser_code(curUser);
    vo.setCreate_date(curDate);
    vo.setTarget_type(ConstantSet.PersonYearTargetType);
    user = userDao.findUserByID(Integer.parseInt(vo.getUser_code()));
    String user_code = vo.getUser_code(); //获取创建人
    String curName = user.getName();
    myForm.setUsername(curName); //设置当前用户中文名而不是账户--对应创建人

    int creatorFlag = 0;
    String auditor = vo.getAuditor(); //获得审核人　
    String audit_flag = vo.getAudit_flag(); //获得审批标志
    String curUseCode = cfg.getBaseForm().getUserId(); //当前人
    System.out.println("The audit_falg is:----" + audit_flag);
    creatorFlag = ConstantSet.judgeCreatorTag(curUseCode, user_code, auditor,
                                              audit_flag);
    if (!curUseCode.equals(auditor)) {
      myForm.setCreatorFlag(String.valueOf(creatorFlag)); //设置修改权限
    } else {
      myForm.setCreatorFlag("0");
    }

    // 得到审核人
    OrganizationUtil util = new OrganizationUtil();
    if (!StringUtils.isNull(auditor)) {
      int iAuditorid = Integer.parseInt(auditor);
      if (util.checkUser(iAuditorid)) {
        User userAuditor = new DefaultUser();
        userAuditor = userDao.findUserByID(iAuditorid);
        String auditorName = userAuditor.getName();
        myForm.setAuditorid(auditor); //重新设置一下FormBean中的审核人ID，否则数据库中将写入空值
        myForm.setAuditorname(auditorName); //设置数据库中获取的审核人显示到页面
      } else {
        myForm.setAuditorname("");
      }
    } else {
      myForm.setAuditorid(""); //如果是新增操作，则将上次的FormBean中的值重置为空
      myForm.setAuditorname("");
    }

    //判断审核权限
    int auditFlag = 0;
    System.out.println("The current user is:" + curUseCode);
    System.out.println("The auditor is :" + auditor);
    System.out.println("The audit_flag is:" + audit_flag);
    auditFlag = ConstantSet.judgeAuditFlag(curUseCode, auditor, audit_flag);
    if (!"true".equals(modifyFlag)) {
      myForm.setAuditFlag(String.valueOf(auditFlag));
    } else {
      myForm.setAuditFlag("0");
    }

    cfg.getBaseForm().setVo(vo);
    if ("modify".equals(actionType)) { //如果是修改操作
      makeCellField(cfg);
    }

    if (!this.MODIFY_ACTION.equals(actionType)) {
      cfg.getBaseForm().setActionType(super.ADD_ACTION);
    }
    return this.FORWORD_EDIT_PAGE;
  }

  /**
   * 新增动作--这里重载BaseAction里面的方法，实现添加worklog模板的功能
   * @param cfg 当前Action相关配置类
   * @param isBack 是否返回
   * @return forward地址
   * @throws DefaultException
   * @throws Exception
   */

  protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
      DefaultException,
      Exception {
    PersonYearTargetForm personYearTargetForm = (PersonYearTargetForm) cfg.
        getBaseForm();
    PersonTargetVO vo = (PersonTargetVO) personYearTargetForm.getVo();
    vo.setTarget_content(Codes.decode(personYearTargetForm.getCellcontent().
                                      toCharArray()));
    ConstantSet constantSet = new ConstantSet();
    //点击太快时有时vo不能获取所有数据的
    //vo.setTarget_type(constantSet.PersonYearTargetType);
    String current_year = vo.getCurrent_year();
    String current_month = vo.getCurrent_month();
    String current_week = vo.getCurrent_work();
    String target_type = vo.getTarget_type();
    String dept_code = vo.getDept_code();
    String user_code = vo.getUser_code();
    //用来判断用户填写的资料是否已经存在于数据库中了
    String isExistFlag = constantSet.queryPersonTargetIsExist(current_year,
        current_month, current_week,
        target_type, dept_code, user_code);
    if ("1".equals(isExistFlag)) {
      throw new DefaultException("您已经填写了该项，不能再新增了，可选择编辑该项！");
    }
    //设置审核人
    String auditor = personYearTargetForm.getAuditorid();
    vo.setAuditor(auditor);
    //审核初始值
    vo.setAudit_flag("0");
    String retVal = "";
    try {
      cfg.getBaseDao().insert(vo);
    } catch (DefaultException e) {
      e.printStackTrace();
      throw new DefaultException("插入目标出错");
    }

    //给审核审批人发送短消息
    MessageDao messageDao = MessageFactory.getInstance().
        createMessageDao(Constant.DATA_SOURCE);
    String userCode = vo.getUser_code(); //发送人是创建人
    String sendTime = DateTimeUtils.getCurrentDateTime();
    String auditorContent = "您好!有您要审核的人年目标!";
    String receiver1 = vo.getAuditor();
    String webUrl = "/dailyoffice/worklog/framepersonyeartarget.jsp?fid="+vo.getSerial_num();
    messageDao.sendMessage(userCode, sendTime, auditorContent, receiver1,
                           webUrl,"转向人年目标审核");
    if (isBack == true) {
      retVal = this.FORWORD_LIST_PAGE;
    } else {
      cfg.getBaseForm().setActionType(this.MODIFY_ACTION);
      this.makeCellField(cfg);
      retVal = this.FORWORD_EDIT_PAGE;
    }
    return retVal;
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
    PersonYearTargetForm personYearTargetForm = (PersonYearTargetForm) cfg.
        getBaseForm();
    PersonTargetVO vo = (PersonTargetVO) personYearTargetForm.getVo();
    vo.setTarget_content(Codes.decode(personYearTargetForm.getCellcontent().
                                      toCharArray()));
    //取消审核
    if ("1".equals(personYearTargetForm.getIsCancel())) {
      vo.setAudit_flag("0");
      vo.setAudit_date("");
      vo.setAudit_opinion("");
    }
    String auditorId = "";
    auditorId = personYearTargetForm.getAuditorid();
    vo.setAuditor(auditorId);
    super.doUpdate(cfg, isBack);
    String retVal = "";
    if (isBack == true) {
      retVal = this.FORWORD_LIST_PAGE;
    } else {
      this.makeCellField(cfg);
      retVal = this.FORWORD_EDIT_PAGE;
    }
    return retVal;
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
    PersonYearTargetForm myForm = (PersonYearTargetForm) cfg.getBaseForm(); //得到form变量
    PersonTargetVO vo = (PersonTargetVO) myForm.getVo();
    UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
    String curUser = cfg.getBaseForm().getUserId(); //取得当前用户ID
    String usercode = vo.getUser_code(); //获取创建人ID
    String deptnodeid = myForm.getDeptnodeid();
    //获取创建人所在部门和岗位
    OrganizationUtil systemMngUtil = new OrganizationUtil();
    //String curPostCode = systemMngUtil.getJobIDsByUser(Integer.parseInt(curUser));
    String curPostName = systemMngUtil.getJobNamesByUser(Integer.parseInt(
        usercode));
    myForm.setPostname(curPostName);
    //获取当前用户对应的部门中文名称,将可能对应多部门
    //String deptsCodeOfCurUser = systemMngUtil.getDepartmentIDsByUser(Integer.parseInt(curUser));
    String deptsNameOfCurUser = systemMngUtil.getDepartmentNamesByUser(Integer.
        parseInt(usercode));
    myForm.setDeptname(deptsNameOfCurUser);

    if (!"".equals(usercode) && usercode != null) {
      int iUsercode = Integer.parseInt(usercode);
      User user = new DefaultUser();
      user = userDao.findUserByID(iUsercode);
      String userName = user.getName();
      myForm.setUsername(userName); //设置创建人中文名
    }
    int creatorFlag = 0;
    //得到vo中得属性值
    String auditor = vo.getAuditor(); //获得审核人
    String audit_flag = vo.getAudit_flag(); //获得审核标志
    System.out.println("The curUser is:" + curUser);
    System.out.println("The usercode is:" + usercode);
    System.out.println("The auditor is:" + auditor);
    System.out.println("The audit_falg is:" + audit_flag);
    creatorFlag = ConstantSet.judgeCreatorTag(curUser, usercode, auditor,
                                              audit_flag);
    myForm.setCreatorFlag(String.valueOf(creatorFlag)); //设置修改权限

    //设置审核人
    if (!StringUtils.isNull(auditor)) {
      int iAuditor = Integer.parseInt(auditor);
      User user = new DefaultUser();
      user = userDao.findUserByID(iAuditor);
      String sAuditor = user.getName();
      myForm.setAuditorname(sAuditor);
    }
    //设置取消审核属性
    int isCancel = 0;
    System.out.println("THe isCancle comments-----start");
    System.out.println("The curUser is:---" + curUser);
    System.out.println("THe auditor is:---" + auditor);
    System.out.println("The audit_flag is:--" + audit_flag);
    System.out.println("The isCancle comments -------end");
    isCancel = ConstantSet.judgeIsCancel(curUser, auditor, "", audit_flag, "0");
    System.out.println("The isCancel is:----" + isCancel);
    myForm.setIsCancel(String.valueOf(isCancel)); //设置formBean中得属性

    //设置审核属性
    int auditFlag = 0;
    auditFlag = ConstantSet.judgeAuditFlag(curUser, auditor, audit_flag);
    myForm.setAuditFlag(String.valueOf(auditFlag));

    makeCellField(cfg);
    return forwardpath;
  }

  //创建临时文件以便查询
  private void makeCellField(GoaActionConfig cfg) throws
      DefaultException,
      Exception {
    TemplateUtils templateUtils = new TemplateUtils();
    PersonYearTargetForm personYearTargetForm = (PersonYearTargetForm) cfg.
        getBaseForm();
    PersonTargetVO vo = (PersonTargetVO) personYearTargetForm.getVo();
    String strPath = super.getServlet().getServletContext().getRealPath("/");
    String strFilename = "/cell/report/" +
        templateUtils.makeCellName(strPath + "/cell/report") + ".cll";
    personYearTargetForm.setCellname(strFilename);
    byte[] bcell = vo.getTarget_content();
    File file = new File(strPath + strFilename);
    OutputStream fos = new FileOutputStream(file);
    OutputStream os = new BufferedOutputStream(fos);
    os.write(bcell);
    os.close();
  }

  /**
   * 获取tree的数据
   * @param cfg 当前Action相关配置类
   * @return forward地址
   * @throws DefaultException
   * @throws Exception
   */

  public String doTreeData(GoaActionConfig cfg) throws DefaultException,
      Exception {
    String nodeid = StringUtils.nullToString(cfg.getRequest().getParameter(
        "nodeid"));
      String curDeptId=cfg.getBaseForm().getCurDeptCodes();//得到用户当前的部门
      HttpSession session=cfg.getSession();
      String user_dev = (String) session.getAttribute(Constant.USER_FILTER);//得到用户级层
      int iuser_dev;
      iuser_dev=Integer.parseInt(user_dev);
      if(!StringUtils.isNull(user_dev)){
        if (iuser_dev<=3) {
          cfg.getRequest().setAttribute("treeData",personyeartargetDao.getTreeData(nodeid));
        }else{
          cfg.getRequest().setAttribute("treeData",
                                        personyeartargetDao.getTreeData(nodeid, curDeptId));
        }
      }

    return TREE_PAGE;
  }

}
