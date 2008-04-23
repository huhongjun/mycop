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
 * Title: ����滮������
 * Description: ����滮������
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */

public class FiveYearLayoutAction
    extends BaseAction {
  FiveYearLayoutDao fiveyearlayoutDao = null; //����ӿ�
  OrganizationUtil util = new OrganizationUtil();
  public FiveYearLayoutAction() {
  }

  /**
   * ��ʼ��ҳ������
   * @param cfg ��ǰ��vo����
   * @throws DefaultException
   * @throws Exception
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {
    FiveYearLayoutForm myForm = (FiveYearLayoutForm) cfg.getBaseForm(); //�õ�form����
    fiveyearlayoutDao = FiveYearLayoutFactory.getInstance().
        createFiveYearLayout(super.dbData); //�õ����Ӧdao��ʵ��
    cfg.setBaseDao( (BaseDAO) fiveyearlayoutDao); //cfg--Goa Action��ȡ��--���ó����е�Dao
//�ݴ���,��ֹvo����Ϊnull
    if (myForm.getVo() == null) {
      myForm.setVo(new FiveYearLayoutVO()); //����VO��Form��
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
    HttpSession session=cfg.getSession();
    String curUserID = cfg.getBaseForm().getUserId(); //ȡ�õ�ǰ�û�ID
    String user_dev=(String) session.getAttribute(Constant.USER_FILTER);//�õ��û�����
    String curDeptCodes = cfg.getBaseForm().getCurDeptCodes().trim();

    ConstantSet.CurUserID = curUserID;
    String queryFlag = request.getParameter("queryFlag");
    String curYear = DateTimeUtils.getCurrentDate().substring(0, 4); //��ȡ��ǰ���
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
    String actionType = SumUtils.nullToString(cfg.getRequest().getParameter(
        "actionType"));
    User user = new DefaultUser();
    UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
    FiveYearLayoutForm myForm = (FiveYearLayoutForm) cfg.getBaseForm(); //�õ�form����
    if (StringUtils.isNull(myForm.getReportTemplateName())) { //�������ģ�廹û������
      IsStandardModelDao isStandardModelDao = IsCustomerFactory.
          getInstance().createIsStandardModel(super.dbData);
      IsStandardModelVO ismVO = (IsStandardModelVO) isStandardModelDao.
          getTemplate(ConstantSet.
                      FiveYearLayoutTemplate);
      myForm.setReportTemplateName(ismVO.getFile_path());
    }

    String curUser = cfg.getBaseForm().getUserId();
    String curDate = DateTimeUtils.getCurrentDate(); //ȡ�õ�ǰ����--��Ӧ��������
    FiveYearLayoutVO vo = (FiveYearLayoutVO) cfg.getBaseForm().getVo();
    String curYear = DateTimeUtils.getCurrentDate().substring(0, 4); //��ȡ��ǰ���
    vo.setCurrent_year(curYear);
    String initUserCode = vo.getUser_code();
    if ("".equals(initUserCode) || initUserCode == null) {
      //�����ʼ�û�Ϊ��(���������)�����õ�ǰ�û�Ϊ������
      vo.setUser_code(curUser);
    }


    //��������Ϊ����ʱ������
   if(!this.MODIFY_ACTION.equals(actionType)){
       vo.setCreate_date(curDate);
   }


    String approveid = vo.getApprove(); //��ȡ��׼��ID
    if (!"".equals(approveid) && approveid != null) {
      int iApproveid = Integer.parseInt(approveid);
      if (util.checkUser(iApproveid)) {
        User userApprove = new DefaultUser();
        userApprove = userDao.findUserByID(iApproveid);
        String approveName = userApprove.getName();
        myForm.setApproveid(approveid);
        myForm.setApprovename(approveName); //�������ݿ��л�ȡ����׼����ʾ��ҳ��
      } else {
        myForm.setApprovename("");
      }
    } else {
      myForm.setApproveid(""); //������������������ϴε�FormBean�е�ֵ����Ϊ��
      myForm.setApprovename("");
    }
    //�õ����û����Ӧdao��ʵ��
    user = userDao.findUserByID(Integer.parseInt(vo.getUser_code()));
    //��ʱҪ��ȡ������Ӧ�ô����ݿ��¼�л�ȡ�������ǵ�ǰ�˵�ID
    String curName = user.getName();
    myForm.setUsername(curName); //���ô������������������˻�--��Ӧ������
    String approve = vo.getApprove(); //��ȡ��׼��ID
    //if ("".equals(initUserCode) || initUserCode == null) {
    String user_code = vo.getUser_code(); //��ȡ������
    //}
    String approve_flag = vo.getApprove_flag(); //��ȡ��׼״̬
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
      //ֻ����׼��ǲ�Ϊnull���ж�(˵�����������ļ�¼)������Ĭ��Ϊ����ѡ����׼��
      editFlag = ConstantSet.judgeEditTag(curUser, user_code,
                                          approve_flag);
    } else {
      editFlag = 1;
    }
    //�����ж��Ƿ���ʾ��˻�������Ϣ
    String flag = SumUtils.nullToString(cfg.getRequest().getParameter(
        "flag"));
    System.out.println("The flag is:" + flag);
    if (!flag.equals("modify")) { //����û�����޸İ�ť��������޸���ˣ�������
      editFlag = 0;
    }
    if (flag.equals("modify")) {
      myForm.setApproveFlag("0");
      // myForm.setEditFlag("0");
    }
    if (approveFlag == 1 && !flag.equals("modify")) { //��ǰ����������ʱ
      myForm.setApproveFlag("1"); //ӵ����׼Ȩ��--��ʾ��׼�����д�����׼�Ƿ�ͨ��ordio
      vo.setApprove_date(curDate); //����׼��������Ϊ��ǰ������2004-07-28
    } else if (approveFlag == 0) {
      myForm.setApproveFlag("0"); //��ӵ����׼Ȩ��--����ʾ��׼�����д�����׼�Ƿ�ͨ��ordio
    }
    if (editFlag == 1) { //��ǰ���ǿ��޸���ʱ
      myForm.setEditFlag("1"); //ӵ���޸�Ȩ��--��ʾ��׼��ѡ��ť
    } else if (editFlag == 0) {
      myForm.setEditFlag("0"); //��ӵ���޸�Ȩ��--����ʾ��׼��ѡ��ť
    }
    if (isReadOnlyTemplateFlag == 0) { //��ǰ���ǲ������޸�Ŀ��ģ��ʱ-ֻ��
      myForm.setIsReadOnlyTemplateFlag("0"); //��ӵ���޸�Ȩ��-��ģ��ֻ����־��Ϊֻ��
    } else if (isReadOnlyTemplateFlag == 1) { //��д
      myForm.setIsReadOnlyTemplateFlag("1"); //ӵ���޸�Ȩ��-����ģ��ֻ����־��Ϊ��ֻ��
    }

    ConstantSet cs = new ConstantSet();
    myForm.setUnitname(cs.getUnitName());
    cfg.getBaseForm().setVo(vo);
    if (!this.MODIFY_ACTION.equals(actionType)) {
      myForm.setEditFlag("1"); //������ܵ�����ҳ��ȥ�Ļ���Ĭ�Ͽ����޸�
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
    FiveYearLayoutForm fiveyearlayoutForm = (FiveYearLayoutForm) cfg.
        getBaseForm();
    FiveYearLayoutVO vo = (FiveYearLayoutVO) fiveyearlayoutForm.getVo();

    //�ж�����滮�Ƿ����
    vo.setContent(Codes.decode(fiveyearlayoutForm.getCellcontent().
                               toCharArray()));
    String approveid = fiveyearlayoutForm.getApproveid(); //��ȡ��׼��id
    vo.setApprove(approveid);
    vo.setApprove_flag("0"); //Ĭ�Ͻ���׼��־��Ϊ0
    String retStr = "";
    cfg.getBaseDao().insert(vo);

    //����������˷��Ͷ���Ϣ
    String isSendMsg = fiveyearlayoutForm.getIsSendMsg(); //�Ƿ��Ͷ���Ϣ
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
   * ���Ͷ���Ϣ
   * @return voie
   */
  private void sendMsg(FiveYearLayoutVO vo) throws Exception {
    MessageDao messageDao = MessageFactory.getInstance().
        createMessageDao(Constant.DATA_SOURCE);
    String userCode = vo.getUser_code(); //�������Ǵ�����
    String sendTime = DateTimeUtils.getCurrentDateTime();
    String approveContent = "����!Ŀ�������������Ҫ����������滮!";
    String receiver = vo.getApprove();
    String webUrl = "/dailyoffice/targetmng/viewfiveyearlayout.do?fid="+vo.getSerial_num();
    messageDao.sendMessage(userCode, sendTime, approveContent, receiver,
                           webUrl,"ת������滮����");

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
    FiveYearLayoutForm fiveyearlayoutForm = (FiveYearLayoutForm) cfg.
        getBaseForm();
    FiveYearLayoutVO vo = (FiveYearLayoutVO) fiveyearlayoutForm.getVo();
    vo.setContent(Codes.decode(fiveyearlayoutForm.getCellcontent().
                               toCharArray()));

    String isCancel = fiveyearlayoutForm.getIsCancel();
    System.out.println("The isCancel is:***" + isCancel);
    //�ж��û��Ƿ�ȡ������
    if ("2".equals(isCancel)) {
      vo.setApprove_flag("0");
      vo.setApprove_date("");
    }
    if (! ("1".equals(isCancel) || "2".equals(isCancel))) {
      String approveid = fiveyearlayoutForm.getApproveid(); //��ȡ��׼��id
      vo.setApprove(approveid);
    }
    super.doUpdate(cfg, isBack);
    //����������˷��Ͷ���Ϣ
    String isSendMsg = fiveyearlayoutForm.getIsSendMsg(); //�Ƿ��Ͷ���Ϣ
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
   * ������ҳ��
   * @param cfg ��ǰAction���������
   * @return forward ��ַ
   * @throws DefaultException
   * @throws Exception
   */

  protected String toView(GoaActionConfig cfg) throws DefaultException,
      Exception {
    String forwardpath = super.toView(cfg);

    FiveYearLayoutForm myForm = (FiveYearLayoutForm) cfg.getBaseForm();
    FiveYearLayoutVO vo = (FiveYearLayoutVO) myForm.getVo();
    UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
    String usercode = vo.getUser_code(); //��ȡ������ID
    String approve = vo.getApprove(); //��ȡ��׼��ID
    if (!"".equals(usercode) && usercode != null) {
      int iUsercode = Integer.parseInt(usercode);
      User user = new DefaultUser();
      user = userDao.findUserByID(iUsercode);
      String userName = user.getName();
      myForm.setUsername(userName); //���ô�����������
    }
    if (!"".equals(approve) && approve != null) {
      int iApproveid = Integer.parseInt(approve);
      if (util.checkUser(iApproveid)) {
        User userApprove = new DefaultUser();
        userApprove = userDao.findUserByID(iApproveid);
        String approveName = userApprove.getName();
        myForm.setApprovename(approveName); //�������ݿ��л�ȡ����׼����ʾ��ҳ��
      } else {
        myForm.setApprovename("");
      }
    }else{
      myForm.setApprovename("");
    }
    int creatorFlag = 0;
    String curUser = cfg.getBaseForm().getUserId(); //ȡ�õ�ǰ�û�ID
    String approve_flag = vo.getApprove_flag();
    String approver = vo.getApprove();
    //creatorFlag = ConstantSet.judgeFiveYearLayoutEditDeleteTag(curUser, usercode, approve_flag, approver);
    creatorFlag = ConstantSet.judgeFiveYearLayoutEditDeleteTagOfNew(curUser,
        usercode, approve_flag, approver);
    if (creatorFlag == 1) {
      myForm.setCreatorFlag("1");
    } else if (creatorFlag == 2) {
      myForm.setCreatorFlag("2"); //ӵ������Ȩ��
    } else if (creatorFlag == 0) {
      myForm.setCreatorFlag("0"); //��ӵ���޸�ɾ��Ȩ��
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

//������ʱ�ļ��Ա��ѯ
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
