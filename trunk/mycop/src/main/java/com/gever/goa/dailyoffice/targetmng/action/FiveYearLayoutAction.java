package com.gever.goa.dailyoffice.targetmng.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.message.dao.MessageDao;
import com.gever.goa.dailyoffice.message.dao.MessageFactory;
import com.gever.goa.dailyoffice.targetmng.dao.FiveYearLayoutDao;
import com.gever.goa.dailyoffice.targetmng.dao.FiveYearLayoutFactory;
import com.gever.goa.dailyoffice.targetmng.vo.FiveYearLayoutVO;
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
 * Title: 五年规划控制器
 * Description: 五年规划控制器
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */

public class FiveYearLayoutAction
    extends BaseAction {
  FiveYearLayoutDao fiveyearlayoutDao = null; //定义接口
  OrganizationUtil util = new OrganizationUtil();
  public FiveYearLayoutAction() {
  }

  /**
   * 初始化页面数据
   * @param cfg 当前的vo对象
   * @throws DefaultException
   * @throws Exception
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {
    FiveYearLayoutForm myForm = (FiveYearLayoutForm) cfg.getBaseForm(); //得到form变量
    fiveyearlayoutDao = FiveYearLayoutFactory.getInstance().
        createFiveYearLayout(super.dbData); //得到相对应dao的实例
    cfg.setBaseDao( (BaseDAO) fiveyearlayoutDao); //cfg--Goa Action获取类--设置超类中的Dao
//容错处理,防止vo对象为null
    if (myForm.getVo() == null) {
      myForm.setVo(new FiveYearLayoutVO()); //设置VO到Form中
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

    ConstantSet.CurUserID = curUserID;
    String queryFlag = request.getParameter("queryFlag");
    String curYear = DateTimeUtils.getCurrentDate().substring(0, 4); //获取当前年份
    StringBuffer sbSqlWhere = new StringBuffer();
    if ("true".equals(queryFlag)) {
      String searchYear = ( (FiveYearLayoutForm) cfg.getBaseForm()).getSearchYear();
      sbSqlWhere.append(" and current_year like '%" +searchYear + "%' ");
      sbSqlWhere.append(" and ( ").append(user_dev).append("<b.level");
      sbSqlWhere.append(" or user_code= ").append(curUserID).append(")");
    } else {
      sbSqlWhere.append(" and current_year = '" + curYear + "' ");
      sbSqlWhere.append(" and ( ").append(user_dev).append("<b.level");
      sbSqlWhere.append(" or user_code= ").append(curUserID).append(")");
    }
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
      Exception {
    super.toEdit(cfg);
    String actionType = SumUtils.nullToString(cfg.getRequest().getParameter(
        "actionType"));
    User user = new DefaultUser();
    UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
    FiveYearLayoutForm myForm = (FiveYearLayoutForm) cfg.getBaseForm(); //得到form变量
    if (StringUtils.isNull(myForm.getReportTemplateName())) { //如果报表模板还没有设置
      IsStandardModelDao isStandardModelDao = IsCustomerFactory.
          getInstance().createIsStandardModel(super.dbData);
      IsStandardModelVO ismVO = (IsStandardModelVO) isStandardModelDao.
          getTemplate(ConstantSet.
                      FiveYearLayoutTemplate);
      myForm.setReportTemplateName(ismVO.getFile_path());
    }

    String curUser = cfg.getBaseForm().getUserId();
    String curDate = DateTimeUtils.getCurrentDate(); //取得当前日期--对应创建日期
    FiveYearLayoutVO vo = (FiveYearLayoutVO) cfg.getBaseForm().getVo();
    String curYear = DateTimeUtils.getCurrentDate().substring(0, 4); //获取当前年份
    vo.setCurrent_year(curYear);
    String initUserCode = vo.getUser_code();
    if ("".equals(initUserCode) || initUserCode == null) {
      //如果初始用户为空(新增的情况)则设置当前用户为创建人
      vo.setUser_code(curUser);
    }


    //创建日期为新增时的日期
   if(!this.MODIFY_ACTION.equals(actionType)){
       vo.setCreate_date(curDate);
   }


    String approveid = vo.getApprove(); //获取批准人ID
    if (!"".equals(approveid) && approveid != null) {
      int iApproveid = Integer.parseInt(approveid);
      if (util.checkUser(iApproveid)) {
        User userApprove = new DefaultUser();
        userApprove = userDao.findUserByID(iApproveid);
        String approveName = userApprove.getName();
        myForm.setApproveid(approveid);
        myForm.setApprovename(approveName); //设置数据库中获取的批准人显示到页面
      } else {
        myForm.setApprovename("");
      }
    } else {
      myForm.setApproveid(""); //如果是新增操作，则将上次的FormBean中的值重置为空
      myForm.setApprovename("");
    }
    //得到与用户相对应dao的实例
    user = userDao.findUserByID(Integer.parseInt(vo.getUser_code()));
    //此时要获取创建人应该从数据库记录中获取，而不是当前人的ID
    String curName = user.getName();
    myForm.setUsername(curName); //设置创建人中文名而不是账户--对应创建人
    String approve = vo.getApprove(); //获取批准人ID
    //if ("".equals(initUserCode) || initUserCode == null) {
    String user_code = vo.getUser_code(); //获取创建人
    //}
    String approve_flag = vo.getApprove_flag(); //获取批准状态
    int approveFlag = 0;
    int editFlag = 0;
    int isReadOnlyTemplateFlag = 0;
    isReadOnlyTemplateFlag = ConstantSet.
        judgeFiveYearLayoutReadOnlyTemplateTag(curUser, user_code,
                                               approve_flag,
                                               approve);
    approveFlag = ConstantSet.judgeApproveTag(curUser, approve,
                                              approve_flag);
    if (!"".equals(approve_flag) && approve_flag != null) {
      //只有批准标记不为null才判断(说明是新增过的记录)，否则默认为可以选择批准人
      editFlag = ConstantSet.judgeEditTag(curUser, user_code,
                                          approve_flag);
    } else {
      editFlag = 1;
    }
    //用来判断是否显示审核或审批信息
    String flag = SumUtils.nullToString(cfg.getRequest().getParameter(
        "flag"));
    System.out.println("The flag is:" + flag);
    if (!flag.equals("modify")) { //如果用户点击修改按钮，则可以修改审核，审批人
      editFlag = 0;
    }
    if (flag.equals("modify")) {
      myForm.setApproveFlag("0");
      // myForm.setEditFlag("0");
    }
    if (approveFlag == 1 && !flag.equals("modify")) { //当前人是审批人时
      myForm.setApproveFlag("1"); //拥有批准权限--显示批准意见填写框和批准是否通过ordio
      vo.setApprove_date(curDate); //将批准日期设置为当前的日期2004-07-28
    } else if (approveFlag == 0) {
      myForm.setApproveFlag("0"); //不拥有批准权限--不显示批准意见填写框和批准是否通过ordio
    }
    if (editFlag == 1) { //当前人是可修改人时
      myForm.setEditFlag("1"); //拥有修改权限--显示批准人选择按钮
    } else if (editFlag == 0) {
      myForm.setEditFlag("0"); //不拥有修改权限--不显示批准人选择按钮
    }
    if (isReadOnlyTemplateFlag == 0) { //当前人是不可以修改目标模板时-只读
      myForm.setIsReadOnlyTemplateFlag("0"); //不拥有修改权限-将模板只读标志设为只读
    } else if (isReadOnlyTemplateFlag == 1) { //可写
      myForm.setIsReadOnlyTemplateFlag("1"); //拥有修改权限-不将模板只读标志设为不只读
    }

    ConstantSet cs = new ConstantSet();
    myForm.setUnitname(cs.getUnitName());
    cfg.getBaseForm().setVo(vo);
    if (!this.MODIFY_ACTION.equals(actionType)) {
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
    FiveYearLayoutForm fiveyearlayoutForm = (FiveYearLayoutForm) cfg.
        getBaseForm();
    FiveYearLayoutVO vo = (FiveYearLayoutVO) fiveyearlayoutForm.getVo();

    //判断五年规划是否存在
    vo.setContent(Codes.decode(fiveyearlayoutForm.getCellcontent().
                               toCharArray()));
    String approveid = fiveyearlayoutForm.getApproveid(); //获取批准人id
    vo.setApprove(approveid);
    vo.setApprove_flag("0"); //默认将批准标志置为0
    String retStr = "";
    cfg.getBaseDao().insert(vo);

    //给审核审批人发送短消息
    String isSendMsg = fiveyearlayoutForm.getIsSendMsg(); //是否发送短消息
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
  private void sendMsg(FiveYearLayoutVO vo) throws Exception {
    MessageDao messageDao = MessageFactory.getInstance().
        createMessageDao(Constant.DATA_SOURCE);
    String userCode = vo.getUser_code(); //发送人是创建人
    String sendTime = DateTimeUtils.getCurrentDateTime();
    String approveContent = "您好!目标管理中有您需要审批的五年规划!";
    String receiver = vo.getApprove();
    String webUrl = "/dailyoffice/targetmng/viewfiveyearlayout.do?fid="+vo.getSerial_num();
    messageDao.sendMessage(userCode, sendTime, approveContent, receiver,
                           webUrl,"转向五年规划审批");

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
    FiveYearLayoutForm fiveyearlayoutForm = (FiveYearLayoutForm) cfg.
        getBaseForm();
    FiveYearLayoutVO vo = (FiveYearLayoutVO) fiveyearlayoutForm.getVo();
    vo.setContent(Codes.decode(fiveyearlayoutForm.getCellcontent().
                               toCharArray()));

    String isCancel = fiveyearlayoutForm.getIsCancel();
    System.out.println("The isCancel is:***" + isCancel);
    //判断用户是否取消审批
    if ("2".equals(isCancel)) {
      vo.setApprove_flag("0");
      vo.setApprove_date("");
    }
    if (! ("1".equals(isCancel) || "2".equals(isCancel))) {
      String approveid = fiveyearlayoutForm.getApproveid(); //获取批准人id
      vo.setApprove(approveid);
    }
    super.doUpdate(cfg, isBack);
    //给审核审批人发送短消息
    String isSendMsg = fiveyearlayoutForm.getIsSendMsg(); //是否发送短消息
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

    FiveYearLayoutForm myForm = (FiveYearLayoutForm) cfg.getBaseForm();
    FiveYearLayoutVO vo = (FiveYearLayoutVO) myForm.getVo();
    UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
    String usercode = vo.getUser_code(); //获取创建人ID
    String approve = vo.getApprove(); //获取批准人ID
    if (!"".equals(usercode) && usercode != null) {
      int iUsercode = Integer.parseInt(usercode);
      User user = new DefaultUser();
      user = userDao.findUserByID(iUsercode);
      String userName = user.getName();
      myForm.setUsername(userName); //设置创建人中文名
    }
    if (!"".equals(approve) && approve != null) {
      int iApproveid = Integer.parseInt(approve);
      if (util.checkUser(iApproveid)) {
        User userApprove = new DefaultUser();
        userApprove = userDao.findUserByID(iApproveid);
        String approveName = userApprove.getName();
        myForm.setApprovename(approveName); //设置数据库中获取的批准人显示到页面
      } else {
        myForm.setApprovename("");
      }
    }else{
      myForm.setApprovename("");
    }
    int creatorFlag = 0;
    String curUser = cfg.getBaseForm().getUserId(); //取得当前用户ID
    String approve_flag = vo.getApprove_flag();
    String approver = vo.getApprove();
    //creatorFlag = ConstantSet.judgeFiveYearLayoutEditDeleteTag(curUser, usercode, approve_flag, approver);
    creatorFlag = ConstantSet.judgeFiveYearLayoutEditDeleteTagOfNew(curUser,
        usercode, approve_flag, approver);
    if (creatorFlag == 1) {
      myForm.setCreatorFlag("1");
    } else if (creatorFlag == 2) {
      myForm.setCreatorFlag("2"); //拥有审批权限
    } else if (creatorFlag == 0) {
      myForm.setCreatorFlag("0"); //不拥有修改删除权限
    }
    int modify_flag = ConstantSet.judgeFiveYearModifyFlag(curUser, usercode,
        approver, approve_flag);
    if (modify_flag == 1) {
      myForm.setModifyFlag("1");
    } else if (modify_flag == 0) {
      myForm.setModifyFlag("0");
    }
    System.out.println("The modify_falg is***:" + modify_flag);
    int isCancel_flag = ConstantSet.judgeFiveYearIsCancel(curUser, approver,
        approve_flag);
    if (isCancel_flag == 2) {
      myForm.setIsCancel("2");
    } else if (isCancel_flag == 0) {
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
    FiveYearLayoutForm fiveyearlayoutForm = (FiveYearLayoutForm) cfg.
        getBaseForm();
    FiveYearLayoutVO vo = (FiveYearLayoutVO) fiveyearlayoutForm.getVo();
    String strPath = super.getServlet().getServletContext().getRealPath("/");
    String strFilename = "/cell/report/" +
        templateUtils.makeCellName(strPath + "/cell/report") + ".cll";
    fiveyearlayoutForm.setCellname(strFilename);
    byte[] bcell = vo.getContent();
    File file = new File(strPath + strFilename);
    OutputStream fos = new FileOutputStream(file);
    OutputStream os = new BufferedOutputStream(fos);
    os.write(bcell);
    os.close();
  }

}
